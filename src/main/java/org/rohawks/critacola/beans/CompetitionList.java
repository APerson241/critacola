package org.rohawks.critacola.beans;

import java.io.Serializable;
import java.util.*;

public class CompetitionList implements Serializable {
    private List<Competition> competitions;
    private Competition selectedCompetition;

    public CompetitionList() {
        competitions = new ArrayList<Competition>();
    }

    public void addCompetition( Competition competition ) {
        competitions.add( competition );
    }

    public List<Competition> getCompetitions() {
        return competitions;
    }

    public boolean isEmpty() {
        return competitions.isEmpty();
    }

    public Competition getSelectedCompetition() {
        return selectedCompetition;
    }

    public void setSelectedCompetition( Competition c ) {
        if( !competitions.contains( c ) ) {
            throw new IllegalArgumentException();
        }
        selectedCompetition = c;
    }

    public void clearSelectedCompetition() {
        setSelectedCompetition( null );
    }
}
