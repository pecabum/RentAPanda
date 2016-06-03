package com.rentapanda.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rentapanda.R;
import com.rentapanda.models.Job;

import java.text.SimpleDateFormat;

public class JobDetailFragment extends Fragment {

    private static final String ARG_JOB = "param1";

    private Job job;

    public JobDetailFragment() {
        // Required empty public constructor
    }

    public static JobDetailFragment newInstance(Job job) {
        JobDetailFragment fragment = new JobDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_JOB, job);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            job = getArguments().getParcelable(ARG_JOB);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_job_detail, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        TextView tvCustomerName = (TextView) view.findViewById(R.id.tvCustomerName);
        TextView tvStatus = (TextView) view.findViewById(R.id.tvStatus);
        TextView tvCity = (TextView) view.findViewById(R.id.tvCity);
        TextView tvDate = (TextView) view.findViewById(R.id.tvDate);
        TextView tvStreet = (TextView) view.findViewById(R.id.tvStreet);
        TextView tvPrice = (TextView) view.findViewById(R.id.tvPrice);
        TextView tvOrderTime = (TextView) view.findViewById(R.id.tvOrder);
        TextView tvMethod = (TextView) view.findViewById(R.id.tvMethod);
        TextView tvRecurrence = (TextView) view.findViewById(R.id.tvRecurrence);
        TextView tvDistance = (TextView) view.findViewById(R.id.tvDistance);

        tvCustomerName.setText(job.getCustomer_name());
        tvStatus.setText(job.getStatus());
        tvCity.setText(job.getJobCity());

        if (!job.getDistance().equals("")) {
            tvDistance.setText(String.format(getString(R.string.km), job.getDistance()));
        } else {
            tvDistance.setText(getString(R.string.unknown));
        }

        SimpleDateFormat formatter5=new SimpleDateFormat("dd.MM.yyyy");
        String formats1 = formatter5.format(job.getJobDate());
        tvDate.setText(formats1);



        tvStreet.setText(job.getJobStreet());
        tvPrice.setText(job.getPrice());
        tvOrderTime.setText(job.getOrderTime());
        tvMethod.setText(job.getPaymentMethod());
        tvRecurrence.setText(job.getRecurrency(getActivity()));
        tvCustomerName.setText(job.getCustomer_name());

    }
}
