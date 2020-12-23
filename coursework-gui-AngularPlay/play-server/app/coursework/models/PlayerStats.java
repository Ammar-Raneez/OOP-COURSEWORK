package coursework.models;

/*
 * PlayerStats
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.io.Serializable;

/**
 * PlayerStats class, which will be used to represent any Players stats
 * @version 1.x November 17th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class PlayerStats implements Serializable {
    private final int ACCURACY;
    private final int CONTROL;
    private final int CROSSING;
    private final int GOAL_KEEPING_HANDLING;
    private final int GOAL_KEEPING_PRESSURE;
    private final int GOAL_KEEPING_REACTION;
    private final int HEADING;
    private final int PASSING;
    private int overall;
    private final int SHOOTING;
    private final int SPEED;
    private final int STAMINA;
    private final int STRENGTH;
    private final int TACKLING;

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
        this.ACCURACY = accuracy;
        this.CONTROL = control;
        this.CROSSING = crossing;
        this.GOAL_KEEPING_HANDLING = goalKeeperHandling;
        this.GOAL_KEEPING_PRESSURE = goalKeepingPressure;
        this.GOAL_KEEPING_REACTION = goalKeepingReaction;
        this.HEADING = heading;
        this.PASSING = passing;
        this.SHOOTING = shooting;
        this.SPEED = speed;
        this.STAMINA = stamina;
        this.STRENGTH = strength;
        this.TACKLING = tackle;
    }

    /**
     * @return - this players accuracy
     */
    public int getAccuracy() {
        return ACCURACY;
    }

    /**
     * @return - this players control
     */
    public int getControl() {
        return CONTROL;
    }

    /**
     * @return - this players crossing
     */
    public int getCrossing() {
        return CROSSING;
    }

    /**
     * @return - this players GKH
     */
    public int getGoalKeeperHandling() {
        return GOAL_KEEPING_HANDLING;
    }

    /**
     * @return - this players GKP
     */
    public int getGoalKeepingPressure() {
        return GOAL_KEEPING_PRESSURE;
    }

    /**
     * @return - this players GKR
     */
    public int getGoalKeepingReaction() {
        return GOAL_KEEPING_REACTION;
    }

    /**
     * @return - this players heading
     */
    public int getHeading() {
        return HEADING;
    }

    /**
     * @return - this players passing
     */
    public int getPassing() {
        return PASSING;
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
        this.overall = (this.ACCURACY + this.CONTROL + this.CROSSING + this.GOAL_KEEPING_HANDLING +
                this.GOAL_KEEPING_PRESSURE + this.GOAL_KEEPING_REACTION + this.HEADING + this.PASSING +
                this.SHOOTING + this.SPEED + this.STAMINA + this.STRENGTH + this.TACKLING) / 13;
    }

    /**
     * @return - this players speed
     */
    public int getSpeed() {
        return SPEED;
    }

    /**
     * @return - this players stamina
     */
    public int getStamina() {
        return STAMINA;
    }

    /**
     * @return - this players strength
     */
    public int getStrength() {
        return STRENGTH;
    }

    /**
     * @return - this players tackling
     */
    public int getTackle() {
        return TACKLING;
    }

    /**
     * @return - this players shooting
     */
    public int getShooting() {
        return SHOOTING;
    }

    /**
     * @return - overrun toString() method
     */
    @Override
    public String toString() {
        return "PlayerStats{" +
                "accuracy=" + ACCURACY +
                ", control=" + CONTROL +
                ", crossing=" + CROSSING +
                ", goalKeeperHandling=" + GOAL_KEEPING_HANDLING +
                ", goalKeepingPressure=" + GOAL_KEEPING_PRESSURE +
                ", goalKeepingReaction=" + GOAL_KEEPING_REACTION +
                ", heading=" + HEADING +
                ", passing=" + PASSING +
                ", overall=" + overall +
                ", shooting=" + SHOOTING +
                ", speed=" + SPEED +
                ", stamina=" + STAMINA +
                ", strength=" + STRENGTH +
                ", tackle=" + TACKLING +
                '}';
    }
}
