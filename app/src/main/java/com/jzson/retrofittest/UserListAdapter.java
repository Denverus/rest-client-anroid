package com.jzson.retrofittest;

import android.content.Context;
import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;

import java.util.List;

/**
 * Created by Denis on 5/9/2016.
 */
public class UserListAdapter extends ArrayAdapter<User> {

    public UserListAdapter(Context context, int resource, List<User> objects) {
        super(context, resource, objects);
    }
}
