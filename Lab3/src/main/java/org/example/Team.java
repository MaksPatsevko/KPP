package org.example;
import java.io.Serializable;
import java.util.List;

public class Team implements Serializable {
    private String teamName;
    private List<Athlete> athletes;

    public Team(String teamName, List<Athlete> athletes) {
        this.teamName = teamName;
        this.athletes = athletes;
    }

    public String getTeamName() {
        return teamName;
    }

    public List<Athlete> getAthletes() {
        return athletes;
    }

    @Override
    public String toString() {
        return "Team{" +
                "teamName='" + teamName + '\'' +
                ", athletes=" + athletes +
                '}';
    }
}
