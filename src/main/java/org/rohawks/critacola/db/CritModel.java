package org.rohawks.critacola.db;

import org.rohawks.critacola.beans.CompetitionList;

/**
 * The Model part of the MVC. Manages CRUD for the CompetitionList.
 */
public class CritModel {
    private CompetitionList competitionList;

    public CritModel() {
        competitionList = new CompetitionList();
    }

    public CompetitionList getCompetitionList() {
        return competitionList;
    }
}
