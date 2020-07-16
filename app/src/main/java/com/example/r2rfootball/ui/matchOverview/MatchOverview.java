package com.example.r2rfootball.ui.matchOverview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.r2rfootball.R;
import com.example.r2rfootball.respApi.TeamsRespon;
import com.example.r2rfootball.respApi.RestTeams;
import com.example.r2rfootball.restapi.RetrofitGson;
import com.example.r2rfootball.sqlite.DatabaseHelper;
import com.example.r2rfootball.ui.main.SectionsPagerAdapterMatchOverview;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MatchOverview extends AppCompatActivity {
    DatabaseHelper dbHelper;
    private Boolean isFavorite = false;
    private Menu menu;
    public boolean isFav;
    public String img_Home ="";
    public String img_Away ="";
    public ImageView img_teamHome;
    public ImageView img_teamAway;
    public TabLayout tabs;
    private int[] tabIcons = {
            R.drawable.ic_ball,
            R.drawable.ic_player,
            R.drawable.ic_cards
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
        return true;
    }

    private boolean checkForTableExists(String id){
        dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "SELECT * FROM TABLE_MATCH WHERE idEvent='"+id+"'";
        Cursor mCursor = db.rawQuery(sql, null);
        if (mCursor.getCount() > 0) {
            return true;
        }
        mCursor.close();
        return false;
    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem unfave = menu.findItem(R.id.mn_favorites);
        MenuItem fave = menu.findItem(R.id.action);

        fave.setVisible(isFav);
        unfave.setVisible(!isFav);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.mn_favorites) {
            menu.getItem(1).setVisible(true);
            menu.getItem(0).setVisible(false);
            dbHelper = new DatabaseHelper(this);
                Intent data = getIntent();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("insert into TABLE_MATCH (idEvent,strHomeTeam,dateEvent,strTime,intHomeScore,intAwayScore,strAwayTeam) values('"+
                        data.getStringExtra("Id")+"','"+
                        data.getStringExtra("strHomeTeam")+"','"+
                        data.getStringExtra("tgl")+"','"+
                        data.getStringExtra("jam")+"','"+
                        data.getStringExtra("homeScore")+"','"+
                        data.getStringExtra("awayScore")+"','"+
                        data.getStringExtra("strAwayTeam")+"')");
                Toast.makeText(getApplicationContext(), "Berhasil Di Tambahkan", Toast.LENGTH_LONG).show();
            return true;
        }else if (id == R.id.action) {
            dbHelper = new DatabaseHelper(this);
            Intent data = getIntent();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("delete from TABLE_MATCH WHERE idEvent = "+data.getStringExtra("Id")+"");
            Toast.makeText(getApplicationContext(), "Berhasil Di Hilangkan", Toast.LENGTH_LONG).show();
            menu.getItem(0).setVisible(true);
            menu.getItem(1).setVisible(false);
            return true;
        }

        return super.onOptionsItemSelected(item);


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_overview);
        SectionsPagerAdapterMatchOverview sectionsPagerAdapter = new SectionsPagerAdapterMatchOverview(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        tabs = findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);
        setupTabIcons();


        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        img_teamHome = findViewById(R.id.home_team);
        img_teamAway = findViewById(R.id.away_team);
        TextView tanggal = findViewById(R.id.tanggal);
        TextView nama_teamHome = findViewById(R.id.strTeam_home);
        TextView nama_teamAway = findViewById(R.id.strTeam_away);
        TextView score_teamHome = findViewById(R.id.scoreHome);
        TextView score_teamAway = findViewById(R.id.scoreAway);
        getTeamHome();
        getTeamAway();
        Intent data = getIntent();
        final SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        final SimpleDateFormat EEEddMMMyyyy = new SimpleDateFormat("EEE,dd/MMM", Locale.US);
        final SimpleDateFormat HHmmFormat = new SimpleDateFormat("HH:mm", Locale.US);
        final SimpleDateFormat hhmmampmFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        String tgl = parseDate(data.getStringExtra("tgl"),ymdFormat,EEEddMMMyyyy);
        String jam = parseDate(data.getStringExtra("jam"),HHmmFormat,hhmmampmFormat);
        String darealTanggal = tgl + " , "+jam;

        String iddata = data.getStringExtra("Id");
        if(iddata != null){
            nama_teamHome.setText(data.getStringExtra("strHomeTeam"));
            nama_teamAway.setText(data.getStringExtra("strAwayTeam"));
            score_teamHome.setText(data.getStringExtra("homeScore"));
            score_teamAway.setText(data.getStringExtra("awayScore"));
            score_teamAway.setText(data.getStringExtra("awayScore"));
            tanggal.setText(darealTanggal);
        }
        if (checkForTableExists(data.getStringExtra("Id"))){
            isFav = true;
        }
    }
    public static String parseDate(String inputDateString, SimpleDateFormat inputDateFormat, SimpleDateFormat outputDateFormat) {
        Date date = null;
        String outputDateString = null;
        try {
            date = inputDateFormat.parse(inputDateString);
            outputDateString = outputDateFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return outputDateString;
    }
    private void setupTabIcons(){
        tabs.getTabAt(0).setIcon(tabIcons[0]);
        tabs.getTabAt(1).setIcon(tabIcons[1]);
        tabs.getTabAt(2).setIcon(tabIcons[2]);
    }

    public String[] getGoals(){
        Intent data = getIntent();
        String goalHome = data.getStringExtra("goalsHome");
        String goalAway = data.getStringExtra("goalsAway");
        String arr[] = new String[2];
        arr[0] = goalHome;
        arr[1] = goalAway;
        return arr;
    }
    public String[] getLineUp(){
        Intent data = getIntent();
        String attHome = data.getStringExtra("forwardHome");
        String attAway = data.getStringExtra("forwardAway");
        String defHome = data.getStringExtra("defenseHome");
        String defAway = data.getStringExtra("defenseAway");
        String kepHome = data.getStringExtra("goalkeeperHome");
        String kepAway = data.getStringExtra("goalkeeperAway");
        String midHome = data.getStringExtra("midfieldHome");
        String midAway = data.getStringExtra("midfieldAway");
        String subHome = data.getStringExtra("substitusionHome");
        String subAway = data.getStringExtra("substitusionAway");
        String arr[] = new String[10];
        arr[0] = attHome;
        arr[1] = attAway;
        arr[2] = defHome;
        arr[3] = defAway;
        arr[4] = kepHome;
        arr[5] = kepAway;
        arr[6] = midHome;
        arr[7] = midAway;
        arr[8] = subHome;
        arr[9] = subAway;
        return arr;


    }

    public String[] getCards(){
        Intent data = getIntent();
        String redcardsHome = data.getStringExtra("redcardsHome");
        String yellowcardsHome = data.getStringExtra("yellowcardsHome");
        String redcardsAway = data.getStringExtra("redcardsAway");
        String yellowcardsAway = data.getStringExtra("yellowcardsAway");
        String arr[] = new String[4];
        arr[0] = redcardsHome;
        arr[1] = yellowcardsHome;
        arr[2] = redcardsAway;
        arr[3] = yellowcardsAway;
        return arr;
    }



    private void getTeamHome(){
        Intent data = getIntent();
        String iddata = data.getStringExtra("idHomeTeam");
        String URL = "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id="+iddata;
        HashMap<String, String> maps = new HashMap<>();
        Call<TeamsRespon> getEvent = RetrofitGson.getInstance().api().getDetailTeam(URL,maps);
        getEvent.enqueue(new Callback<TeamsRespon>() {
            @Override
            public void onResponse(Call<TeamsRespon> call, Response<TeamsRespon> response) {
                if(response.isSuccessful()){
                    List<RestTeams> resultList = response.body().getTeams();
                    for(RestTeams result : resultList){
                        Log.d("URL", result.getStrTeamBadge());
                        Picasso.get()
                                .load(Uri.parse(result.getStrTeamBadge()))
                                .into(img_teamHome);
                    }
                }

            }

            @Override
            public void onFailure(Call<TeamsRespon> call, Throwable t) {

            }
        });

    }
    private void getTeamAway(){
        Intent data = getIntent();
        String iddata = data.getStringExtra("idAwayTeam");
        String URL = "https://www.thesportsdb.com/api/v1/json/1/lookupteam.php?id="+iddata;
        HashMap<String, String> maps = new HashMap<>();
        Call<TeamsRespon> getEvent = RetrofitGson.getInstance().api().getDetailTeam(URL,maps);
        getEvent.enqueue(new Callback<TeamsRespon>() {
            @Override
            public void onResponse(Call<TeamsRespon> call, Response<TeamsRespon> response) {
                if(response.isSuccessful()){
                    List<RestTeams> resultList = response.body().getTeams();
                    for(RestTeams result : resultList){
                        Log.d("URL", result.getStrTeamBadge());
                        Picasso.get()
                                .load(Uri.parse(result.getStrTeamBadge()))
                                .into(img_teamAway);
                    }
                }

            }

            @Override
            public void onFailure(Call<TeamsRespon> call, Throwable t) {

            }
        });

    }



}