package com.example.r2rfootball.restapi;

import com.example.r2rfootball.respApi.LastMatchRespon;
import com.example.r2rfootball.respApi.NextMatchRespon;
import com.example.r2rfootball.respApi.TeamsRespon;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface ApiService {

    @GET("eventsnextleague.php?id=4328")
    Call<NextMatchRespon> getEvent(@QueryMap Map<String, String> maps);

    @GET("eventspastleague.php?id=4328")
    Call<LastMatchRespon> getLast(@QueryMap Map<String, String> maps);


    @GET("lookup_all_teams.php?id=4328")
    Call<TeamsRespon> getTeam(@QueryMap Map<String, String> maps);

    @GET
    Call<TeamsRespon> getDetailTeam(@Url String url, @QueryMap Map<String, String> maps);


}
