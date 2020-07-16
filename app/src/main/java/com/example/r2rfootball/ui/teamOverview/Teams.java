package com.example.r2rfootball.ui.teamOverview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.r2rfootball.R;
import com.example.r2rfootball.adapter.TeamsAdapter;
import com.example.r2rfootball.favorit.Favorit;
import com.example.r2rfootball.respApi.RestTeams;
import com.example.r2rfootball.respApi.TeamsRespon;
import com.example.r2rfootball.restapi.RetrofitGson;
import com.example.r2rfootball.ui.matchOverview.Match;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Teams extends AppCompatActivity {
    private RecyclerView recyclerView;
    private List<RestTeams> listTeams = new ArrayList<>();
    private TeamsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teams);
        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setSelectedItemId(R.id.nav_teams);
        recyclerView = findViewById(R.id.recyclerview_teams);
        adapter = new TeamsAdapter(listTeams, Teams.this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        listTeam();
        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_match:
                        startActivity(new Intent(getApplicationContext(), Match.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_favorites:
                        startActivity(new Intent(getApplicationContext(), Favorit.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_teams:

                        return true;

                }
                return false;
            }
        });

    }

    private void listTeam(){
        HashMap<String, String> maps = new HashMap<>();
        Call<TeamsRespon> getEvent = RetrofitGson.getInstance().api().getTeam(maps);
        getEvent.enqueue(new Callback<TeamsRespon>() {
            @Override
            public void onResponse(Call<TeamsRespon> call, Response<TeamsRespon> response) {
                if(response.isSuccessful()){
                    TeamsRespon resp = response.body();
                    listTeams.addAll(resp.getTeams());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<TeamsRespon> call, Throwable t) {

            }
        });

    }

    @Override
    protected void onPause() {
        super.onPause();
        listTeam();
    }
}
