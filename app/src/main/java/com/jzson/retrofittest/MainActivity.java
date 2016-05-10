package com.jzson.retrofittest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private UserListAdapter userListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        final EditText userNameTextView = (EditText) findViewById(R.id.user_name_edit_text);

        final Button addUser = (Button) findViewById(R.id.add_user_button);
        addUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Editable username = userNameTextView.getText();
                addUser(view, username.toString());
            }
        });

        List<User> list = new ArrayList<>();
        userListAdapter = new UserListAdapter(this, android.R.layout.simple_list_item_1, list);

        final ListView listView = (ListView) findViewById(R.id.user_list_view);
        listView.setAdapter(userListAdapter);

        final Button reloadUserListBtn = (Button)findViewById(R.id.reload_button);
        reloadUserListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reloadUserList(view);
            }
        });

        final Button deleteAllBtn = (Button) findViewById(R.id.delete_all_button);
        deleteAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAll(v);
            }
        });
    }

    private void deleteAll(final View view) {
        Provider.getProvider().removeAllUsers(new ServiceCallback<Void>(view) {
            @Override
            protected void onSuccessResponse(Response<Void> response) {
                userListAdapter.clear();
                userListAdapter.notifyDataSetChanged();
            }
        });

    }

    private void reloadUserList(final View view) {
        Provider.getProvider().getUserList(new ServiceCallback<List<User>>(view) {
            @Override
            protected void onSuccessResponse(Response<List<User>> response) {
                final List<User> users = response.body();
                userListAdapter.clear();
                userListAdapter.addAll(users);
                userListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void addUser(final View view, String username) {
        User user = new User(username);

        Provider.getProvider().addUser(user, new ServiceCallback<User>(view) {
            @Override
            protected void onSuccessResponse(Response<User> response) {
                final User user = response.body();
                userListAdapter.add(user);
                userListAdapter.notifyDataSetChanged();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
