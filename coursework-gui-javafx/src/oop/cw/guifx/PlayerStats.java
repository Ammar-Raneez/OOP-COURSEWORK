package oop.cw.guifx;

/*
 * oop.cw.guifx.PlayerStats
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.io.Serializable;

/**
 * oop.cw.guifx.PlayerStats class, which will be used to represent any Players stats
 * @version 1.x November 17th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class PlayerStats implements Serializable {
    private int accuracy;
    private int control;
    private int crossing;
    private int goalKeeperHandling;
    private int goalKeepingPressure;
    private int goalKeepingReaction;
    private int heading;
    private int passing;
    private int overall;
    private int shooting;
    private int speed;
    private int stamina;
    private int strength;
    private int tackle;

    /**
     * Initializes all the player stats
     * @param accuracy - player accuracy
     * @param control - player control
     * @param crossing - player crossing
     * @param goalKeeperHandling - player GKH
     * @param goalKeepingPressure - player GKP
     * @param goalKeepingReaction - player GKR
     * @param heading - player heading
     * @param passing - player passing
     * @param shooting - player shooting
     * @param speed - player speed
     * @param stamina - player stamina
     * @param strength - player strength
     * @param tackle - player tackling
     */
    public PlayerStats(int accuracy, int control, int crossing, int goalKeeperHandling, int goalKeepingPressure,
                       int goalKeepingReaction, int heading, int passing, int shooting, int speed, int stamina,
                       int strength, int tackle) {
        this.accuracy = accuracy;
        this.control = control;
        this.crossing = crossing;
        this.goalKeeperHandling = goalKeeperHandling;
        this.goalKeepingPressure = goalKeepingPressure;
        this.goalKeepingReaction = goalKeepingReaction;
        this.heading = heading;
        this.passing = passing;
        this.shooting = shooting;
        this.speed = speed;
        this.stamina = stamina;
        this.strength = strength;
        this.tackle = tackle;
    }

    /**
     * @return - this players accuracy
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * @return - this players control
     */
    public int getControl() {
        return control;
    }

    /**
     * @return - this players crossing
     */
    public int getCrossing() {
        return crossing;
    }

    /**
     * @return - this players GKH
     */
    public int getGoalKeeperHandling() {
        return goalKeeperHandling;
    }

    /**
     * @return - this players GKP
     */
    public int getGoalKeepingPressure() {
        return goalKeepingPressure;
    }

    /**
     * @return - this players GKR
     */
    public int getGoalKeepingReaction() {
        return goalKeepingReaction;
    }

    /**
     * @return - this players heading
     */
    public int getHeading() {
        return heading;
    }

    /**
     * @return - this players passing
     */
    public int getPassing() {
        return passing;
    }

    /**
     * @return - this players overall
     */
    public int getOverall() {
        return overall;
    }

    /**
     * sets overall stat of the player
     */
    public void setOverall() {
        this.overall = (this.accuracy + this.control + this.crossing + this.goalKeeperHandling +
                this.goalKeepingPressure + this.goalKeepingReaction + this.heading + this.passing +
                this.shooting + this.speed + this.stamina + this.strength + this.tackle) / 13;
    }

    /**
     * @return - this players speed
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * @return - this players stamina
     */
    public int getStamina() {
        return stamina;
    }

    /**
     * @return - this players strength
     */
    public int getStrength() {
        return strength;
    }

    /**
     * @return - this players tackling
     */
    public int getTackle() {
        return tackle;
    }

    /**
     * @return - this players shooting
     */
    public int getShooting() {
        return shooting;
    }

    /**
     * @return - overrun toString() method
     */
    @Override
    public String toString() {
        return "oop.cw.guifx.PlayerStats{" +
                "accuracy=" + accuracy +
                ", control=" + control +
                ", crossing=" + crossing +
                ", goalKeeperHandling=" + goalKeeperHandling +
                ", goalKeepingPressure=" + goalKeepingPressure +
                ", goalKeepingReaction=" + goalKeepingReaction +
                ", heading=" + heading +
                ", passing=" + passing +
                ", overall=" + overall +
                ", shooting=" + shooting +
                ", speed=" + speed +
                ", stamina=" + stamina +
                ", strength=" + strength +
                ", tackle=" + tackle +
                '}';
    }
}
