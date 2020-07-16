package com.example.r2rfootball.ui.matchOverview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.r2rfootball.R;
import com.example.r2rfootball.ui.matchOverview.MatchOverview;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MatchLineupFragment extends Fragment {

    View v;
    private TextView attHome,attAway,defHome,defAway,midHome,midAway,kepHome,kepAway,subHome,subAway;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_match_lineup,container,false);
        MatchOverview ac = (MatchOverview) getActivity();
        String[] id = ac.getLineUp();
        String waw = id[0];
        String waw1 = id[1];
        String waw2 = id[2];
        String waw3 = id[3];
        String waw4 = id[4];
        String waw5 = id[5];
        String waw6 = id[6];
        String waw7 = id[7];
        String waw8 = id[8];
        String waw9 = id[9];
        if(waw == null){
            attHome = v.findViewById(R.id.attHome);
            attAway = v.findViewById(R.id.attAway);
            defHome = v.findViewById(R.id.defHom);
            defAway = v.findViewById(R.id.defAway);
            midHome = v.findViewById(R.id.midHome);
            midAway = v.findViewById(R.id.midAway);
            kepHome = v.findViewById(R.id.kepHome);
            kepAway = v.findViewById(R.id.kepAway);
            subHome = v.findViewById(R.id.subHome);
            subAway = v.findViewById(R.id.subAway);
            attHome.setText("-");
            attAway.setText("-");
            defHome.setText("-");
            defAway.setText("-");
            midHome.setText("-");
            midAway.setText("-");
            kepHome.setText("-");
            kepAway.setText("-");
            subHome.setText("-");
            subAway.setText("-");
        }else{
            String newString = waw.replace("; ", "\n");
            String newString1 = waw1.replace("; ", "\n");
            String newString2 = waw2.replace("; ", "\n");
            String newString3 = waw3.replace("; ", "\n");
            String newString4 = waw4.replace("; ", "\n");
            String newString5 = waw5.replace("; ", "\n");
            String newString6 = waw6.replace("; ", "\n");
            String newString7 = waw7.replace("; ", "\n");
            String newString8 = waw8.replace("; ", "\n");
            String newString9 = waw9.replace("; ", "\n");

            attHome = v.findViewById(R.id.attHome);
            attAway = v.findViewById(R.id.attAway);
            defHome = v.findViewById(R.id.defHom);
            defAway = v.findViewById(R.id.defAway);
            midHome = v.findViewById(R.id.midHome);
            midAway = v.findViewById(R.id.midAway);
            kepHome = v.findViewById(R.id.kepHome);
            kepAway = v.findViewById(R.id.kepAway);
            subHome = v.findViewById(R.id.subHome);
            subAway = v.findViewById(R.id.subAway);
            attHome.setText(newString);
            attAway.setText(newString1);
            defHome.setText(newString2);
            defAway.setText(newString3);
            midHome.setText(newString4);
            midAway.setText(newString5);
            kepHome.setText(newString6);
            kepAway.setText(newString7);
            subHome.setText(newString8);
            subAway.setText(newString9);
        }







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
