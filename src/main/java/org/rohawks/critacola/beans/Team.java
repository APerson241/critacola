package org.rohawks.critacola.beans;

import java.io.Serializable;
import java.util.*;

public class Team implements Serializable {
    private int number;
    private List<MatchRecord> matches;

    public Team( int number ) {
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
