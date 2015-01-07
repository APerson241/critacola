package org.rohawks.critacola.beans;

import java.io.Serializable;
import java.util.*;

public class CompetitionList implements Serializable {
    private List<Competition> competitions;

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
}
