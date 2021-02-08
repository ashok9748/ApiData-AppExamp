package com.example.apidata;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("users")
    Call<Pojo> getdata();
}
