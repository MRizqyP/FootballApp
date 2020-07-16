package com.example.r2rfootball.favorit;

import android.content.Intent;
import android.os.Bundle;

import com.example.r2rfootball.ui.matchOverview.Match;
import com.example.r2rfootball.R;
import com.example.r2rfootball.ui.teamOverview.Teams;
import com.example.r2rfootball.ui.main.SectionsPagerAdapterFavorites;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;

public class Favorit extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorit);
        SectionsPagerAdapterFavorites sectionsPagerAdapter = new SectionsPagerAdapterFavorites(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setSelectedItemId(R.id.nav_favorites);

        bottomnav.setOnNavigationItemSelectedListener(new com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_match:
                        startActivity(new Intent(getApplicationContext(), Match.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.nav_favorites:

                        return true;

                    case R.id.nav_teams:
                        startActivity(new Intent(getApplicationContext(), Teams.class));
                        overridePendingTransition(0, 0);
                        return true;

                }
                return false;
            }
        });
    }
}