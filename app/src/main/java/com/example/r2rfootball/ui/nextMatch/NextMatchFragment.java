package com.example.r2rfootball.ui.nextMatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r2rfootball.R;
import com.example.r2rfootball.respApi.NextMatchRespon;
import com.example.r2rfootball.respApi.RestNextMatch;
import com.example.r2rfootball.adapter.nextMatchAdapert;
import com.example.r2rfootball.restapi.RetrofitGson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NextMatchFragment extends Fragment {


    private RecyclerView recyclerView;
    private List<RestNextMatch> listNextMatch = new ArrayList<>();
    private nextMatchAdapert adapter;
    public String idHome;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_nextmatch,container,false);
        recyclerView = v.findViewById(R.id.nextmatch_recyclerview);
        adapter = new nextMatchAdapert(listNextMatch, getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        return v;


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        listMatch();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        listMatch();
        listNextMatch = new ArrayList<>();

    }
    private void listMatch(){
        HashMap<String, String> maps = new HashMap<>();
        Call<NextMatchRespon> getEvent = RetrofitGson.getInstance().api().getEvent(maps);
        getEvent.enqueue(new Callback<NextMatchRespon>() {
            @Override
            public void onResponse(Call<NextMatchRespon> call, Response<NextMatchRespon> response) {
                if(response.isSuccessful()){
                    NextMatchRespon resp = response.body();
                    listNextMatch.addAll(resp.getEvents());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<NextMatchRespon> call, Throwable t) {

            }
        });

    }

}
