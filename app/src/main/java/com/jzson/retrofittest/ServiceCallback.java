package com.jzson.retrofittest;

import android.support.design.widget.Snackbar;
import android.view.View;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Denis on 5/9/2016.
 */
public abstract class ServiceCallback <T> implements Callback<T> {

    private final View view;

    public ServiceCallback(View view) {
        this.view = view;
    }

    protected abstract void onSuccessResponse(Response<T> response);

    @Override
    public void onResponse(Call<T> call, Response<T> response) {
        if (response.isSuccessful()) {
            onSuccessResponse(response);
            Snackbar.make(view, "Success", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        } else {
            Snackbar.make(view, "Response is not success", Snackbar.LENGTH_SHORT)
                    .setAction("Action", null).show();
        }
    }

    @Override
    public void onFailure(Call<T> call, Throwable t) {
        Snackbar.make(view, "Error", Snackbar.LENGTH_SHORT)
                .setAction("Action", null).show();
    }
}
