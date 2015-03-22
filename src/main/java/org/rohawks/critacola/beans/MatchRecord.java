package org.rohawks.critacola.beans;

import java.io.Serializable;

/**
 * One team's performance in one match.
 */
public class MatchRecord implements Serializable {
    private Team team;
    private boolean noShow;
    private boolean nonFunctional;
    private String auto;
    private String stack;
    private String coop;
    private int litterAttempted;
    private int litterMade;
    private int humanPlayerRating;
    private int driveTrainRating;
    private int buildQualityRating;
    private String restrictions;
    private String comments;

    public MatchRecord( Team team,
                        boolean noShow,
                        boolean nonFunctional,
                        String auto,
                        String stack,
                        String coop,
                        int litterAttempted,
                        int litterMade,
                        int humanPlayerRating,
                        int driveTrainRating,
                        int buildQualityRating,
                        String restrictions,
                        String comments ) {
        this.team = team;
        this.noShow = noShow;
        this.nonFunctional = nonFunctional;
        this.auto = auto;
        this.stack = stack;
        this.coop = coop;
        this.litterAttempted = litterAttempted;
        this.litterMade = litterMade;
        this.humanPlayerRating = humanPlayerRating;
        this.driveTrainRating = driveTrainRating;
        this.buildQualityRating = buildQualityRating;
        this.restrictions = restrictions;
        this.comments = comments;
    }

    /**
     * Gets the value of team
     *
     * @return the value of team
     */
    public final Team getTeam() {
        return this.team;
    }

    /**
     * Sets the value of team
     *
     * @param argTeam Value to assign to this.team
     */
    public final void setTeam( final Team argTeam ) {
        this.team = argTeam;
    }

    /**
     * Gets the value of noShow
     *
     * @return the value of noShow
     */
    public final boolean isNoShow() {
        return this.noShow;
    }

    /**
     * Sets the value of noShow
     *
     * @param argNoShow Value to assign to this.noShow
     */
    public final void setNoShow( final boolean argNoShow ) {
        this.noShow = argNoShow;
    }

    /**
     * Gets the value of nonFunctional
     *
     * @return the value of nonFunctional
     */
    public final boolean isNonFunctional() {
        return this.nonFunctional;
    }

    /**
     * Sets the value of nonFunctional
     *
     * @param argNonFunctional Value to assign to this.nonFunctional
     */
    public final void setNonFunctional( final boolean argNonFunctional ) {
        this.nonFunctional = argNonFunctional;
    }

    /**
     * Gets the value of auto
     *
     * @return the value of auto
     */
    public final String getAuto() {
        return this.auto;
    }

    /**
     * Sets the value of auto
     *
     * @param argAuto Value to assign to this.auto
     */
    public final void setAuto( final String argAuto ) {
        this.auto = argAuto;
    }

    /**
     * Gets the value of stack
     *
     * @return the value of stack
     */
    public final String getStack() {
        return this.stack;
    }

    /**
     * Sets the value of stack
     *
     * @param argStack Value to assign to this.stack
     */
    public final void setStack( final String argStack ) {
        this.stack = argStack;
    }

    /**
     * Gets the value of coop
     *
     * @return the value of coop
     */
    public final String getCoop() {
        return this.coop;
    }

    /**
     * Sets the value of coop
     *
     * @param argCoop Value to assign to this.coop
     */
    public final void setCoop( final String argCoop ) {
        this.coop = argCoop;
    }

    /**
     * Gets the value of litterAttempted
     *
     * @return the value of litterAttempted
     */
    public final int getLitterAttempted() {
        return this.litterAttempted;
    }

    /**
     * Sets the value of litterAttempted
     *
     * @param argLitterAttempted Value to assign to this.litterAttempted
     */
    public final void setLitterAttempted( final int argLitterAttempted ) {
        this.litterAttempted = argLitterAttempted;
    }

    /**
     * Gets the value of litterMade
     *
     * @return the value of litterMade
     */
    public final int getLitterMade() {
        return this.litterMade;
    }

    /**
     * Sets the value of litterMade
     *
     * @param argLitterMade Value to assign to this.litterMade
     */
    public final void setLitterMade( final int argLitterMade ) {
        this.litterMade = argLitterMade;
    }

    /**
     * Gets the value of humanPlayerRating
     *
     * @return the value of humanPlayerRating
     */
    public final int getHumanPlayerRating() {
        return this.humanPlayerRating;
    }

    /**
     * Sets the value of humanPlayerRating
     *
     * @param argHumanPlayerRating Value to assign to this.humanPlayerRating
     */
    public final void setHumanPlayerRating( final int argHumanPlayerRating ) {
        this.humanPlayerRating = argHumanPlayerRating;
    }

    /**
     * Gets the value of driveTrainRating
     *
     * @return the value of driveTrainRating
     */
    public final int getDriveTrainRating() {
        return this.driveTrainRating;
    }

    /**
     * Sets the value of driveTrainRating
     *
     * @param argDriveTrainRating Value to assign to this.driveTrainRating
     */
    public final void setDriveTrainRating( final int argDriveTrainRating ) {
        this.driveTrainRating = argDriveTrainRating;
    }

    /**
     * Gets the value of buildQualityRating
     *
     * @return the value of buildQualityRating
     */
    public final int getBuildQualityRating() {
        return this.buildQualityRating;
    }

    /**
     * Sets the value of buildQualityRating
     *
     * @param argBuildQualityRating Value to assign to this.buildQualityRating
     */
    public final void setBuildQualityRating( final int argBuildQualityRating ) {
        this.buildQualityRating = argBuildQualityRating;
    }

    /**
     * Gets the value of restrictions
     *
     * @return the value of restrictions
     */
    public final String getRestrictions() {
        return this.restrictions;
    }

    /**
     * Sets the value of restrictions
     *
     * @param argRestrictions Value to assign to this.restrictions
     */
    public final void setRestrictions( final String argRestrictions ) {
        this.restrictions = argRestrictions;
    }

    /**
     * Gets the value of comments
     *
     * @return the value of comments
     */
    public final String getComments() {
        return this.comments;
    }

    /**
     * Sets the value of comments
     *
     * @param argComments Value to assign to this.comments
     */
    public final void setComments( final String argComments ) {
        this.comments = argComments;
    }

}
