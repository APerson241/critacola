package org.rohawks.critacola.beans;

import java.io.Serializable;
import java.util.*;

public class Team implements Serializable {
    private int number;
    private List<MatchRecord> matches;

    private static Map<Integer, Team> lookup = new HashMap<Integer, Team>();

    public static Team get( int num ) {
        if( lookup.containsKey( num ) ) {
            return lookup.get( num );
        } else {
            Team newTeam = new Team( num );
            lookup.put( num, newTeam );
            return newTeam;
        }
    }

    private Team( int number ) {
        this.number = number;
        matches = new ArrayList<MatchRecord>();
    }

    public int getNumber() {
        return number;
    }

    public void addMatchRecord( MatchRecord record ) {
        matches.add( record );
    }

    public List<MatchRecord> getMatchRecords() {
        return matches;
    }
}
