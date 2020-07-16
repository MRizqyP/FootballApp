
package com.example.r2rfootball.respApi;

        import java.util.List;

        import com.example.r2rfootball.respApi.RestLastMatch;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class LastMatchRespon {
    public LastMatchRespon(List<RestLastMatch> events) {
        this.events = events;
    }

    @SerializedName("events")
    @Expose
    private List<RestLastMatch> events = null;

    public List<RestLastMatch> getEvents() {
        return events;
    }

    public void setEvents(List<RestLastMatch> events) {
        this.events = events;
    }


}