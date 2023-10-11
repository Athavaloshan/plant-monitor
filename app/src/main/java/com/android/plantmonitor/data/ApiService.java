package com.android.plantmonitor.data;

import retrofit2.Call;
import retrofit2.http.GET;
import  okhttp3.ResponseBody;
// This interface defines an API
// service for getting random jokes.
public interface ApiService {
    // This annotation specifies that the HTTP method
    // is GET and the endpoint URL is "jokes/random".
    @GET("/")
    // This method returns a Call object with a generic
    // type of DataModel, which represents
    // the data model for the response.
    Call<String> getGreeting();

};

