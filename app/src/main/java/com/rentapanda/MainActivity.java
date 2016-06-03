package com.rentapanda;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rentapanda.fragments.JobDetailFragment;
import com.rentapanda.fragments.JobListFragment;
import com.rentapanda.interfaces.OnListFragmentInteractionListener;
import com.rentapanda.models.Job;
import com.rentapanda.net.RestApi;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.Callback;
import retrofit.GsonConverterFactory;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity implements OnListFragmentInteractionListener {

    private ProgressBar progressBar;
    private RelativeLayout rlError;
    private RestApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeContent();

        initializeRetrofitConfig();

        makeRequest();

    }

    private void initializeRetrofitConfig() {
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'").create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(RestApi.BASE_URL).addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RestApi.class);
    }

    private void initializeContent() {
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        rlError = (RelativeLayout) findViewById(R.id.rlError);
        Button btnRetry = (Button) findViewById(R.id.btnRetry);
        if (btnRetry != null) {
            btnRetry.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    rlError.setVisibility(View.GONE);
                    progressBar.setVisibility(View.VISIBLE);
                    makeRequest();
                }
            });
        }
    }

    /**
     * Getting the data from the server
     */
    private void makeRequest() {
        Call<ArrayList<Job>> call = service.getJobList();

        call.enqueue(new Callback<ArrayList<Job>>() {

            @Override
            public void onResponse(Response<ArrayList<Job>> response, Retrofit retrofit) {
                int statusCode = response.code();

                if (statusCode != 200) {
                    showTapToRetry();
                    return;
                }

                ArrayList<Job> jobsList = response.body();
                progressBar.setVisibility(View.GONE);
                getFragmentManager().beginTransaction().replace(R.id.container, JobListFragment.newInstance(jobsList)).commit();
            }

            @Override
            public void onFailure(Throwable t) {
                t.printStackTrace();
                progressBar.setVisibility(View.GONE);
                showTapToRetry();
            }
        });

    }

    private void showTapToRetry() {
        rlError.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onJobItemPressed(Job item) {
        getFragmentManager().beginTransaction()
                .replace(R.id.container, JobDetailFragment.newInstance(item))
                .addToBackStack(null)
                .commit();
    }
}
