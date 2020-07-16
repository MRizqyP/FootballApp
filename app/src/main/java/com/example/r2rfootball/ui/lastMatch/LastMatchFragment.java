package com.example.r2rfootball.ui.lastMatch;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.r2rfootball.respApi.LastMatchRespon;
import com.example.r2rfootball.R;
import com.example.r2rfootball.adapter.lastMatchAdapter;
import com.example.r2rfootball.respApi.RestLastMatch;
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

public class LastMatchFragment  extends Fragment {


    private RecyclerView recyclerView;
    private List<RestLastMatch> listLastMatch = new ArrayList<>();
    private lastMatchAdapter adapter;
    View v;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_lastmatch,container,false);
        recyclerView = v.findViewById(R.id.lastmatch_recyclerview);
        adapter = new lastMatchAdapter(listLastMatch, getContext());
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
        listLastMatch = new ArrayList<>();

    }
    private void listMatch(){
        HashMap<String, String> maps = new HashMap<>();
        Call<LastMatchRespon> getLast = RetrofitGson.getInstance().api().getLast(maps);
        getLast.enqueue(new Callback<LastMatchRespon>() {
            @Override
            public void onResponse(Call<LastMatchRespon> call, Response<LastMatchRespon> response) {
                if(response.isSuccessful()){
                    LastMatchRespon resp = response.body();
                    listLastMatch.addAll(resp.getEvents());
                    adapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<LastMatchRespon> call, Throwable t) {

            }
        });

    }
}
