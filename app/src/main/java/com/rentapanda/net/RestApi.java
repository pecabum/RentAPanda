package com.rentapanda.net;

import com.rentapanda.models.Job;

import java.util.ArrayList;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;

public interface RestApi {

    String BASE_URL = "http://private-14c693-rentapanda.apiary-mock.com/";
    @Headers({
            "Content-Type: application/json"
    })
    @GET("jobs")
    Call<ArrayList<Job>> getJobList();

}