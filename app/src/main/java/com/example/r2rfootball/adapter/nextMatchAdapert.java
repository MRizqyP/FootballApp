package com.example.r2rfootball.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.r2rfootball.R;
import com.example.r2rfootball.respApi.RestNextMatch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class nextMatchAdapert extends RecyclerView.Adapter<nextMatchAdapert.MyViewHolder> {
    List<RestNextMatch> listNextMatch;

    Context context;


    public nextMatchAdapert(List<RestNextMatch> listNextMatch, Context context) {
        this.listNextMatch = listNextMatch;

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
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tHome.setText(listNextMatch.get(position).getStrHomeTeam());
        holder.tAway.setText(listNextMatch.get(position).getStrAwayTeam());

       final SimpleDateFormat ymdFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.US);
        final SimpleDateFormat EEEddMMMyyyy = new SimpleDateFormat("EEE,dd/MMM yyyy", Locale.US);
        final SimpleDateFormat HHmmFormat = new SimpleDateFormat("HH:mm", Locale.US);
        final SimpleDateFormat hhmmampmFormat = new SimpleDateFormat("hh:mm a", Locale.US);
        String tgl = parseDate(listNextMatch.get(position).getDateEvent(),ymdFormat,EEEddMMMyyyy) +"\n"+ parseDate(listNextMatch.get(position).getStrTime(),HHmmFormat,hhmmampmFormat);

        holder.Tanggal.setText(tgl);
//        holder.jam.setText(parseDate(listNextMatch.get(position).getStrTime(),HHmmFormat,hhmmampmFormat));


    }

    @Override
    public int getItemCount() {
        return listNextMatch.size();
    }

    public  static class MyViewHolder extends  RecyclerView.ViewHolder{

        private TextView tHome,tAway,Tanggal,jam;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tHome = itemView.findViewById(R.id.home_team);
            tAway = itemView.findViewById(R.id.away_team);
            Tanggal = itemView.findViewById(R.id.tanggal);
//            jam = itemView.findViewById(R.id.jam);

        }
    }



}
