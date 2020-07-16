package com.example.r2rfootball.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.r2rfootball.R;
import com.example.r2rfootball.respApi.RestTeams;
import com.example.r2rfootball.ui.teamOverview.TeamOverview;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class TeamsAdapter extends RecyclerView.Adapter<TeamsAdapter.MyViewHolder> {
    List<RestTeams> listTeam;
    Context context;

    public TeamsAdapter(List<RestTeams> listTeam, Context context) {
        this.listTeam = listTeam;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v;
        v = LayoutInflater.from(context).inflate(R.layout.teams,parent,false);
        MyViewHolder vHolder = new MyViewHolder(v);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {

        holder.namaTeam.setText(listTeam.get(position).getStrTeam());
//        holder.imgTeam.setImageURI(listTeam.get(position).getStrTeamBadge());

        Picasso.get()
                .load(Uri.parse(listTeam.get(position).getStrTeamBadge()))
                .into(holder.imgTeam);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInput = new Intent(context, TeamOverview.class);
                goInput.putExtra("Id", listTeam.get(position).getIdTeam());
                goInput.putExtra("nm_team", listTeam.get(position).getStrTeam());
                goInput.putExtra("thn_team",listTeam.get(position).getIntFormedYear());
                goInput.putExtra("satdium",listTeam.get(position).getStrStadium());
                goInput.putExtra("img_team",listTeam.get(position).getStrTeamBadge());
                goInput.putExtra("des_team",listTeam.get(position).getStrDescriptionEN());
                context.startActivity(goInput);
            }
        });

    }

    @Override
    public int getItemCount() {
        return listTeam.size();
    }

    public  static class MyViewHolder extends  RecyclerView.ViewHolder{

        private TextView namaTeam;
        private ImageView imgTeam;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            namaTeam = itemView.findViewById(R.id.nama_team);
            imgTeam = itemView.findViewById(R.id.image_team);

        }
    }
}
