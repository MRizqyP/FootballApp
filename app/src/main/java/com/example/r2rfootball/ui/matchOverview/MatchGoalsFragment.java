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

public class MatchGoalsFragment extends Fragment {

    View v;
    private TextView des,des2;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_match_goals,container,false);
        MatchOverview ac = (MatchOverview) getActivity();
        String id[] = ac.getGoals();
        String waw = id[0];
        String waw1= id[1];
//        String nyob = ac.getGoalHome();
        if(waw == null) {
            des = v.findViewById(R.id.goalsHome);
            des.setText("-");
            des2 = v.findViewById(R.id.goalsAway);
            des2.setText("-");
        }else{
            String newString = waw.replace(";", "\n");
            String newString1 = waw1.replace(";", "\n");
            des = v.findViewById(R.id.goalsHome);
            des.setText(newString);
            des2 = v.findViewById(R.id.goalsAway);
            des2.setText(newString1);
        }

//        Log.d("WAW", Arrays.deepToString(arr));




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
