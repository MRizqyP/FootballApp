package com.example.r2rfootball.ui.matchOverview;

import android.content.Intent;
import android.os.Bundle;

import com.example.r2rfootball.R;
import com.example.r2rfootball.ui.teamOverview.Teams;
import com.example.r2rfootball.favorit.Favorit;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.MenuItem;
import android.widget.Button;

import com.example.r2rfootball.ui.main.SectionsPagerAdapter;

public class Match extends AppCompatActivity {
    Button button ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        BottomNavigationView bottomnav = findViewById(R.id.bottom_navigation);
        bottomnav.setSelectedItemId(R.id.nav_match);


        bottomnav.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_match:
                        return true;

                    case R.id.nav_favorites:

                        startActivity(new Intent(getApplicationContext(), Favorit.class));
                        overridePendingTransition(0, 0);
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