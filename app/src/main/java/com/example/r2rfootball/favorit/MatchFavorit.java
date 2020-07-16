package com.example.r2rfootball.favorit;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.r2rfootball.R;
import com.example.r2rfootball.respApi.RestLastMatch;
import com.example.r2rfootball.adapter.lastMatchAdapter;
import com.example.r2rfootball.sqlite.DatabaseHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MatchFavorit extends Fragment {

    View v;
    private TextView des;
    RecyclerView recyclerView;
    DatabaseHelper myDB;
    ArrayList<RestLastMatch> al = new ArrayList<RestLastMatch>();

    private String idEvent;
    private String strHomeTeam;
    private String strAwayTeam ;
    private String intHomeScore;
    private String intAwayScore;
    private String dateEvent;
    private String strTime;
    private String imgHome;
    private String imgAway;
    Cursor c;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_matchfavorit,container,false);
        recyclerView = v.findViewById(R.id.recyclerview_matchfavorit);
        myDB = new DatabaseHelper(getContext());
        c = myDB.readAllDataMatch();
        if(c.getCount() > 0){
            if(c.moveToFirst()){
                do{
                    idEvent=c.getString(0);
                    strHomeTeam=c.getString(1);
                    dateEvent=c.getString(2);
                    strTime=c.getString(3);
                    intHomeScore=c.getString(4);
                    intAwayScore=c.getString(5);
                    strAwayTeam=c.getString(6);


                    RestLastMatch pm = new RestLastMatch(idEvent,strHomeTeam,strAwayTeam,intHomeScore,intAwayScore,dateEvent,strTime);
                    al.add(pm);

                }while (c.moveToNext());
            }
        }

        lastMatchAdapter customAdapter = new lastMatchAdapter(al,getContext());
        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        return v;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }



}
