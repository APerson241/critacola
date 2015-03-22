package org.rohawks.critacola.beans;

import java.io.Serializable;

public class Match implements Serializable {
    private String name;
    private AllianceColor whoWon;
    private boolean coopertitionAchieved;
    private MatchRecord[] matchRecords;

    public Match( String name,
                  AllianceColor whoWon,
                  boolean coopertitionAchieved,
                  MatchRecord[] matchRecords ) {
        this.name = name;
        this.whoWon = whoWon;
        this.coopertitionAchieved = coopertitionAchieved;
        this.matchRecords = matchRecords;
    }

    public AllianceColor getWhoWon() {
        return whoWon;
    }

    public boolean getCoopertitionAchieved() {
        return coopertitionAchieved;
    }

    public MatchRecord[] getMatchRecords() {
        return matchRecords;
    }

    public String getName() {
        return name;
    }
}
