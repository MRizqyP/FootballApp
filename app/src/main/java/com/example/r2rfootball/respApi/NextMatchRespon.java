package com.example.r2rfootball.respApi;

import com.example.r2rfootball.respApi.RestNextMatch;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;

public class NextMatchRespon {
    public NextMatchRespon(List<RestNextMatch> events) {
        this.events = events;
    }

    @SerializedName("events")
    @Expose
    private List<RestNextMatch> events = null;

    public List<RestNextMatch> getEvents() {
        return events;
    }

    public void setEvents(List<RestNextMatch> events) {
        this.events = events;
    }

}
