package com.example.r2rfootball.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.r2rfootball.ui.matchOverview.MatchOverview;
import com.example.r2rfootball.R;
import com.example.r2rfootball.respApi.RestLastMatch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class lastMatchAdapter extends RecyclerView.Adapter<lastMatchAdapter.MyViewHolder> {
    List<RestLastMatch> listLastMatch;
    Context context;

    public lastMatchAdapter(List<RestLastMatch> listLastMatch, Context context) {
        this.listLastMatch = listLastMatch;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.match,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
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

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        final SimpleDateFormat EEEddMMMyyyy = new SimpleDateFormat("EEE,dd/MMM yyyy", Locale.US);
        final SimpleDateFormat HHmmFormat = new SimpleDateFormat("HH:mm", Locale.US);
        final SimpleDateFormat hhmmampmFormat = new SimpleDateFormat("hh:mm a", Locale.US);

        String tgl = parseDate(listLastMatch.get(position).getDateEvent(),ymdFormat,EEEddMMMyyyy) +"\n"+ parseDate(listLastMatch.get(position).getStrTime(),HHmmFormat,hhmmampmFormat);
        holder.tHome.setText(listLastMatch.get(position).getStrHomeTeam());
        holder.tAway.setText(listLastMatch.get(position).getStrAwayTeam());
        holder.Tanggal.setText(tgl);
//        holder.jam.setText(parseDate(listLastMatch.get(position).getStrTime(),HHmmFormat,hhmmampmFormat));
        holder.scoreAway.setText(listLastMatch.get(position).getIntAwayScore());
        holder.scoreHome.setText(listLastMatch.get(position).getIntHomeScore());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(context, MatchOverview.class);
                goInput.putExtra("Id", listLastMatch.get(position).getIdEvent());
                goInput.putExtra("strHomeTeam", listLastMatch.get(position).getStrHomeTeam());
                goInput.putExtra("strAwayTeam",listLastMatch.get(position).getStrAwayTeam());
                goInput.putExtra("homeScore",listLastMatch.get(position).getIntHomeScore());
                goInput.putExtra("awayScore",listLastMatch.get(position).getIntAwayScore());
                goInput.putExtra("idHomeTeam",listLastMatch.get(position).getIdHomeTeam());
                goInput.putExtra("idAwayTeam",listLastMatch.get(position).getIdAwayTeam());
                goInput.putExtra("jam",listLastMatch.get(position).getStrTime());
                goInput.putExtra("tgl",listLastMatch.get(position).getDateEvent());
                goInput.putExtra("goalsHome",listLastMatch.get(position).getStrHomeGoalDetails());
                goInput.putExtra("goalsAway",listLastMatch.get(position).getStrAwayGoalDetails());
                goInput.putExtra("redcardsHome",listLastMatch.get(position).getStrHomeRedCards());
                goInput.putExtra("yellowcardsHome",listLastMatch.get(position).getStrHomeYellowCards());
                goInput.putExtra("redcardsAway",listLastMatch.get(position).getStrAwayRedCards());
                goInput.putExtra("yellowcardsAway",listLastMatch.get(position).getStrAwayYellowCards());
                goInput.putExtra("goalkeeperHome",listLastMatch.get(position).getStrHomeLineupGoalkeeper());
                goInput.putExtra("defenseHome",listLastMatch.get(position).getStrHomeLineupDefense());
                goInput.putExtra("midfieldHome",listLastMatch.get(position).getStrHomeLineupMidfield());
                goInput.putExtra("forwardHome",listLastMatch.get(position).getStrHomeLineupForward());
                goInput.putExtra("substitusionHome",listLastMatch.get(position).getStrHomeLineupSubstitutes());
                goInput.putExtra("goalkeeperAway",listLastMatch.get(position).getStrAwayLineupGoalkeeper());
                goInput.putExtra("defenseAway",listLastMatch.get(position).getStrAwayLineupDefense());
                goInput.putExtra("midfieldAway",listLastMatch.get(position).getStrAwayLineupMidfield());
                goInput.putExtra("forwardAway",listLastMatch.get(position).getStrAwayLineupForward());
                goInput.putExtra("substitusionAway",listLastMatch.get(position).getStrAwayLineupSubstitutes());

                context.startActivity(goInput);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listLastMatch.size();
    }

    public  static class MyViewHolder extends  RecyclerView.ViewHolder{

        private TextView tHome,tAway,Tanggal,scoreHome,scoreAway,jam;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tHome = itemView.findViewById(R.id.home_team);
            tAway = itemView.findViewById(R.id.away_team);
            Tanggal = itemView.findViewById(R.id.tanggal);
            scoreHome = itemView.findViewById(R.id.scoreHome);
            scoreAway = itemView.findViewById(R.id.scoreAway);
//            jam = itemView.findViewById(R.id.jam);
        }
    }
}