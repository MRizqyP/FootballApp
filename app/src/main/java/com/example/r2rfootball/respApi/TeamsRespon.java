
package com.example.r2rfootball.respApi;

        import java.util.List;

        import com.example.r2rfootball.respApi.RestTeams;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

public class TeamsRespon {
    public TeamsRespon(List<RestTeams> teams) {
        this.teams = teams;
    }

    @SerializedName("teams")
    @Expose
    private List<RestTeams> teams = null;

    public List<RestTeams> getTeams() {
        return teams;
    }

    public void setTeams(List<RestTeams> teams) {
        this.teams = teams;
    }

}
