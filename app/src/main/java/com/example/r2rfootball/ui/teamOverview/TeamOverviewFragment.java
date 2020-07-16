package com.example.r2rfootball.ui.teamOverview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.r2rfootball.R;
import com.example.r2rfootball.ui.teamOverview.TeamOverview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class TeamOverviewFragment extends Fragment {

    View v;
    private TextView des;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_team_overview,container,false);
        TeamOverview ac = (TeamOverview) getActivity();
        String id = ac.getMyid();
        des = v.findViewById(R.id.section_label);
        des.setText(id);





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
