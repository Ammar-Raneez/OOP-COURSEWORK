package oop.cw.guifx;

/*
 * oop.cw.guifx.SingleMatchFootballClubStatistic
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.io.Serializable;

/**
 * oop.cw.guifx.SingleMatchFootballClubStatistic class, which will be used to represent any football clubs single match statistic
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class SingleMatchFootballClubStatistic implements Serializable {
    private int corners;
    private int fouls;
    private int goals;
    private int offsides;
    private int passes;
    private int passAccuracy;
    private int possession;
    private int redCards;
    private int shots;
    private int shotsOnTarget;
    private int yellowCards;

    /**
     * initializes a single match club statistic object
     * all attributes initialized to 0, since a new club will not have any record
     */
    public SingleMatchFootballClubStatistic() { }

    /**
     * @return total corners of this club for a match
     */
    public int getCorners() {
        return corners;
    }

    /**
     * sets total corners of this club for a match
     * @param corners - number of corners of this club for a match
     */
    public void setCorners(int corners) {
        this.corners = corners;
    }

    /**
     * @return total fouls of this club for a match
     */
    public int getFouls() {
        return fouls;
    }

    /**
     * sets total fouls of this club for a match
     * @param fouls - number of fouls of this club for a match
     */
    public void setFouls(int fouls) {
        this.fouls = fouls;
    }

    /**
     * @return total goals of this club for a match
     */
    public int getGoals() {
        return goals;
    }

    /**
     * sets total goals of this club for a match
     * @param goals - number of goals of this club for a match
     */
    public void setGoals(int goals) {
        this.goals = goals;
    }

    /**
     * @return total offsides of this club for a match
     */
    public int getOffsides() {
        return offsides;
    }

    /**
     * sets total offsides of this club for a match
     * @param offsides - number of offsides of this club for a match
     */
    public void setOffsides(int offsides) {
        this.offsides = offsides;
    }

    /**
     * @return total passes of this club for a match
     */
    public int getPasses() {
        return passes;
    }

    /**
     * sets total passes of this club for a match
     * @param passes - number of passes of this club for a match
     */
    public void setPasses(int passes) {
        this.passes = passes;
    }

    /**
     * @return pass accuracy of this club for a match
     */
    public int getPassAccuracy() {
        return passAccuracy;
    }

    /**
     * sets pass accuracy of this club for a match
     * @param passAccuracy - pass accuracy of this club for a match
     */
    public void setPassAccuracy(int passAccuracy) {
        this.passAccuracy = passAccuracy;
    }

    /**
     * @return possession of this club for a match
     */
    public int getPossession() {
        return possession;
    }

    /**
     * sets possession of this club for a match
     * @param possession - possession of this club for a match
     */
    public void setPossession(int possession) {
        this.possession = possession;
    }

    /**
     * @return total red of this club for a match
     */
    public int getRedCards() {
        return redCards;
    }

    /**
     * sets total red cards of this club for a match
     * @param redCards - number of red cards this club for a match
     */
    public void setRedCards(int redCards) {
        this.redCards = redCards;
    }

    /**
     * @return total shots of this club for a match
     */
    public int getShots() {
        return shots;
    }

    /**
     * sets total shots of this club for a match
     * @param shots - number of shots this club for a match
     */
    public void setShots(int shots) {
        this.shots = shots;
    }

    /**
     * @return total shots on target of this club for a match
     */
    public int getShotsOnTarget() {
        return shotsOnTarget;
    }

    /**
     * sets total of shorts on target of this club for a match
     * @param shotsOnTarget - number of shots on target this club for a match
     */
    public void setShotsOnTarget(int shotsOnTarget) {
        this.shotsOnTarget = shotsOnTarget;
    }

    /**
     * @return total yellow cards of this club for a match
     */
    public int getYellowCards() {
        return yellowCards;
    }

    /**
     * sets total yellow cards of this club for a match
     * @param yellowCards - number of yellow cards this club for a match
     */
    public void setYellowCards(int yellowCards) {
        this.yellowCards = yellowCards;
    }

    /**
     * @return overrun toString() method
     */
    //TODO, improve toString() methods
    @Override
    public String toString() {
        return "oop.cw.guifx.SingleMatchFootballClubStatistic{" +
                "corners=" + corners +
                ", fouls=" + fouls +
                ", goals=" + goals +
                ", offsides=" + offsides +
                ", passes=" + passes +
                ", passAccuracy=" + passAccuracy +
                ", possession=" + possession +
                ", redCards=" + redCards +
                ", shots=" + shots +
                ", shotsOnTarget=" + shotsOnTarget +
                ", yellowCards=" + yellowCards +
                '}';
    }
}
