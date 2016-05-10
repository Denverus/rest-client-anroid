package com.jzson.retrofittest;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Denis on 4/13/2016.
 */
public class Provider {

    private TestService service;

    private static Provider INSTANCE;

    private TestService createService() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:3000")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(TestService.class);
    }

    private Provider() {
        service = createService();
    }

    public static Provider getProvider() {
        if (INSTANCE == null) {
            INSTANCE = new Provider();
        }
        return INSTANCE;
    }

    private TestService getService() {
        return service;
    }

    public void getUserList(Callback<List<User>> callback) {
        final Call<List<User>> call = getService().getUserList();
        call.enqueue(callback);
    }

    public void addUser(User user, Callback<User> callback) {
        final Call<User> call = getService().addUser(user);
        call.enqueue(callback);
    }

    public void removeAllUsers(Callback<Void> callback) {
        final Call<Void> call = getService().removeAllUsers();
        call.enqueue(callback);
    }
}
