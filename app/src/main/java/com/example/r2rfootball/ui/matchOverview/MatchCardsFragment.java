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

public class MatchCardsFragment extends Fragment {

    View v;
    private TextView redHome,redAway,yelHome,yelAway;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_match_cards,container,false);
        MatchOverview ac = (MatchOverview) getActivity();
        String[] id = ac.getCards();
        String waw = id[0];
        String waw1= id[1];
        String waw2 = id[2];
        String waw4= id[3];


        if (waw == null) {
            redHome = v.findViewById(R.id.redHome);
            yelHome = v.findViewById(R.id.yellowHome);
            redAway = v.findViewById(R.id.redAway);
            yelAway = v.findViewById(R.id.yellowAway);
            redHome.setText("-");
            yelHome.setText("-");
            redAway.setText("-");
            yelAway.setText("-");

        }else{
            redHome = v.findViewById(R.id.redHome);
            yelHome = v.findViewById(R.id.yellowHome);
            redAway = v.findViewById(R.id.redAway);
            yelAway = v.findViewById(R.id.yellowAway);
            String newString = waw.replace(";", "\n");
            String newString1 = waw1.replace(";", "\n");
            String newString2 = waw2.replace(";", "\n");
            String newString3 = waw4.replace(";", "\n");
            redHome.setText(newString);
            yelHome.setText(newString1);
            redAway.setText(newString2);
            yelAway.setText(newString3);
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
