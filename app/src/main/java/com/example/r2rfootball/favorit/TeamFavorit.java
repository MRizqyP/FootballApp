package com.example.r2rfootball.favorit;

import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.r2rfootball.R;
import com.example.r2rfootball.adapter.TeamsAdapter;
import com.example.r2rfootball.respApi.RestTeams;
import com.example.r2rfootball.sqlite.DatabaseHelper;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TeamFavorit extends Fragment {

    View v;
    private TextView des;
    RecyclerView recyclerView;
    DatabaseHelper myDB;
    ArrayList<RestTeams> al = new ArrayList<>();

    private String idTeam;
    private String strTeam;
    private String thnTeam ;
    private String strStadium;
    private String str_image;
    Cursor c;
    String nama,alamat,id;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_teamfavorit,container,false);
        recyclerView = v.findViewById(R.id.recyclerview_teamsfavorit);
        myDB = new DatabaseHelper(getContext());
        c = myDB.readAllDataTeam();
        if(c.getCount() > 0){
            if(c.moveToFirst()){
                do{
                    idTeam=c.getString(0);
                    strTeam=c.getString(1);
                    thnTeam=c.getString(2);
                    strStadium=c.getString(3);
                    str_image=c.getString(4);

                    RestTeams pm = new RestTeams(idTeam,strTeam,thnTeam,strStadium,str_image);
                    al.add(pm);

                }while (c.moveToNext());
            }
        }

        TeamsAdapter customAdapter = new TeamsAdapter(al,getContext());
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
