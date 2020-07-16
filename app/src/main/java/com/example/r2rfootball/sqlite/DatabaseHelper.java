package com.example.r2rfootball.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    public static final String DBNAME ="football";
    public static final String TABLE_TEAM = "TABLE_TEAM";
    public static final String TABLE_MATCH = "TABLE_MATCH";

    private String idEvent ="idEvent";
    private String strHomeTeam= "strHomeTeam";
    private String strAwayTeam ="strAwayTeam";
    private String dateEvent = "dateEvent";
    private String strTime = "strTime";
    private String intHomeScore = "intHomeScore";
    private String intAwayScore= "intAwayScore";

    private String idTeam= "idTeam";
    private String strTeam = "strTeam";
    private String thnTeam ="thnTeam";
    private String strStadium = "strStadium";
    private String str_image = "str_image";
    private String des_team = "str_desteam";


    private Context context;


    public DatabaseHelper(@Nullable Context context) {
        super(context , DBNAME , null ,VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableMatch = "CREATE TABLE " +TABLE_MATCH + " (" +
                idEvent + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                strHomeTeam +" TEXT," +
                dateEvent +" TEXT," +
                strTime +" TEXT," +
                intHomeScore +" INTEGER," +
                intAwayScore +" INTEGER," +
                strAwayTeam + " TEXT" +


                ")";

        String createTableTeam = "CREATE TABLE " +TABLE_TEAM + " (" +
                idTeam + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                strTeam +" TEXT," +
                thnTeam +" INTEGER," +
                strStadium +" TEXT," +
                str_image + " TEXT)";

        db.execSQL(createTableMatch);
        db.execSQL(createTableTeam);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_MATCH);
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_TEAM);
        onCreate(db);
    }




     public Cursor readAllDataTeam(){
        String query = "SELECT * FROM "+TABLE_TEAM;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }
    public Cursor readAllDataMatch(){
        String query = "SELECT * FROM "+TABLE_MATCH;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

}
