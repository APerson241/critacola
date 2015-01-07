package org.rohawks.critacola.beans;

import java.io.Serializable;
import java.util.*;

public class Competition implements Serializable {
    private String name;
    private String location;
    private List<Match> matches;

    public Competition() {
        this( "", "" );
    }

    public Competition( String name ) {
        this( name, "" );
    }

    public Competition( String name, String location ) {
        this.name = name;
        this.location = location;
        matches = new ArrayList<Match>();
    }

    public String getName() {
        return name;
    }

    public void setName( String name ) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation( String location ) {
        this.location = location;
    }

    public void addMatch( Match match ) {
        matches.add( match );
    }

    public List<Match> getMatches() {
        return matches;
    }

    @Override
    public String toString() {
        return "Competition[name=" + getName() + ", " +
            "location=" + getLocation() + "]";
    }
}

