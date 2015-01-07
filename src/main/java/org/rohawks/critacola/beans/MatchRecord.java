package org.rohawks.critacola.beans;

import java.io.Serializable;

/**
 * One team's performance in one match.
 */
public class MatchRecord implements Serializable {
    private int pickupRating;
    private int driveTrainRating;
    private int driverRating;
    private String comments;

    public MatchRecord( int pickupRating,
                        int driveTrainRating,
                        int driverRating,
                        String comments ) {
        this.pickupRating = pickupRating;
        this.driveTrainRating = driveTrainRating;
        this.driverRating = driverRating;
        this.comments = comments;
    }

    public int getPickupRating() {
        return pickupRating;
    }

    public int getDriveTrainRating() {
        return driveTrainRating;
    }

    public int getDriverRating() {
        return driverRating;
    }
}
