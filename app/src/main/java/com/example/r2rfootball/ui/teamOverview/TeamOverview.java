package com.example.r2rfootball.ui.teamOverview;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;

import com.example.r2rfootball.R;
import com.example.r2rfootball.sqlite.DatabaseHelper;
import com.example.r2rfootball.ui.main.SectionsPagerAdapterTeamOverview;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.tabs.TabLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.r2rfootball.ui.main.SectionsPagerAdapter;
import com.squareup.picasso.Picasso;

public class TeamOverview extends AppCompatActivity {
    DatabaseHelper dbHelper;
    private Boolean isFavorite = false;
    private Menu menu;
    public boolean isFav;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.menu = menu;
        getMenuInflater().inflate(R.menu.menu_favorites, menu);
        return true;
    }

    private boolean checkForTableExists(String id){
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        String sql = "SELECT * FROM TABLE_TEAM WHERE idTeam='"+id+"'";
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
                db.execSQL("insert into TABLE_TEAM (idTeam,strTeam,thnTeam,strStadium,str_image) values('"+
                        data.getStringExtra("Id")+"','"+
                        data.getStringExtra("nm_team")+"','"+
                        data.getStringExtra("thn_team")+"','"+
                        data.getStringExtra("satdium")+"','"+
                        data.getStringExtra("img_team")+"')");
                Toast.makeText(getApplicationContext(), "Berhasil Di Tambahkan", Toast.LENGTH_LONG).show();
            return true;
        }else if (id == R.id.action) {
            dbHelper = new DatabaseHelper(this);
            Intent data = getIntent();
            SQLiteDatabase db = dbHelper.getWritableDatabase();
            db.execSQL("delete from TABLE_TEAM WHERE idTeam = "+data.getStringExtra("Id")+"");
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
        setContentView(R.layout.activity_team_overview);
        SectionsPagerAdapterTeamOverview sectionsPagerAdapter = new SectionsPagerAdapterTeamOverview(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tab_layout);
        tabs.setupWithViewPager(viewPager);

        androidx.appcompat.widget.Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        ImageView img_team = findViewById(R.id.iv_team);
        TextView nama_team = findViewById(R.id.tv_name);
        TextView tahun_team = findViewById(R.id.tv_year);
        TextView stadium_team = findViewById(R.id.tv_stadium);

        Intent data = getIntent();
        dbHelper = new DatabaseHelper(this);

        String iddata = data.getStringExtra("Id");
        if(iddata != null){
            nama_team.setText(data.getStringExtra("nm_team"));
            tahun_team.setText(data.getStringExtra("thn_team"));
            stadium_team.setText(data.getStringExtra("satdium"));
            Picasso.get()
                    .load(Uri.parse(data.getStringExtra("img_team")))
                    .into(img_team);

        }
        if (checkForTableExists(data.getStringExtra("Id"))){
            isFav = true;
        }
    }

    public String getMyid(){
        Intent data = getIntent();
        String strDes = data.getStringExtra("des_team");
        return strDes;
    }



}