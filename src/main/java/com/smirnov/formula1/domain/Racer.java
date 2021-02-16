package com.smirnov.formula1.domain;

import java.time.LocalTime;
import java.util.Objects;

public class Racer {
    private final String name;
    private final String team;
    private final LocalTime bestLap;

    public Racer(String name, String team, LocalTime bestLap) {
        this.name = name;
        this.team = team;
        this.bestLap = bestLap;
    }

    public String getName() {
        return name;
    }

    public String getTeam() {
        return team;
    }

    public LocalTime getBestLap() {
        return bestLap;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Racer racer = (Racer) o;
        return Objects.equals(name, racer.name) &&
                Objects.equals(team, racer.team) &&
                Objects.equals(bestLap, racer.bestLap);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, team, bestLap);
    }

    @Override
    public String toString() {
        return "Racer{" +
                "name='" + name + '\'' +
                ", team='" + team + '\'' +
                ", start=" + bestLap +
                '}';
    }
}
