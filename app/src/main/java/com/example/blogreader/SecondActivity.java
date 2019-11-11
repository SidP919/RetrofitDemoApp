package com.example.blogreader;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.blogreader.adapter.PostAdapter;
import com.example.blogreader.model.PostList;
import com.google.android.material.navigation.NavigationView;

public class SecondActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;

    ActionBarDrawerToggle actionBarDrawerToggle;

    NavigationView navigationView;

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        setUpToolbar();

        navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.nav_home:
                        Toast.makeText(SecondActivity.this, "Home Screen", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_one_piece:
                        Toast.makeText(SecondActivity.this, "One Piece Blog", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_naruto:
                        Toast.makeText(SecondActivity.this, "Naruto Blog", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });

        recyclerView = findViewById(R.id.postList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        getData();
    }

    private void setUpToolbar() {
        drawerLayout = findViewById(R.id.drawerLayout);
        toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);

        actionBarDrawerToggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.app_name,
                R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

    private void getData() {
        Call<PostList> postListCall = BloggerAPI.getPostService().getPostList();
        postListCall.enqueue(new Callback<PostList>() {
            @Override
            public void onResponse(Call<PostList> call, Response<PostList> response) {
                PostList list = response.body();
                recyclerView.setAdapter(new PostAdapter(SecondActivity.this, list.getItems()));
                Toast.makeText(SecondActivity.this, "Success", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<PostList> call, Throwable t) {
                Toast.makeText(SecondActivity.this, "Error occurred", Toast.LENGTH_SHORT).show();
            }
        });
    }

}
