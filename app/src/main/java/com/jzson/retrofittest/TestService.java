package com.jzson.retrofittest;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by Denis on 4/13/2016.
 */
public interface TestService {
    @GET("users")
    Call<List<User>> getUserList();

    @POST("users")
    Call<User> addUser(@Body User user);

    @DELETE("users")
    Call<Void> removeAllUsers();
}
