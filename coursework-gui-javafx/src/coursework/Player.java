package coursework;

/*
 * Player
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.io.Serializable;

/**
 * Player class, which will be used to represent any Player
 * @version 1.x November 17th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class Player implements Serializable {
    private static final long serialVersionUID = 5625338058257534947L;
    private final double HEIGHT;
    private final String NAME;
    private final String NATIONALITY;
    private final PlayerStats PLAYER_STATS;
    private final String POSITION;
    private final String PREFERRED_FOOT;
    private final int SHIRT_NUMBER;

    /**
     * initializes all info of a Player
     * @param height - player height
     * @param name - player name
     * @param nationality - player nationality
     * @param playerStats - player Stats
     * @param position - player position
     * @param preferredFoot - player preferred foot
     * @param shirtNumber - player shirt number
     */
    public Player(double height, String name, String nationality, PlayerStats playerStats, String position,
                  String preferredFoot, int shirtNumber) {
        this.HEIGHT = height;
        this.NAME = name;
        this.NATIONALITY = nationality;
        this.PLAYER_STATS = playerStats;
        this.POSITION = position;
        this.PREFERRED_FOOT = preferredFoot;
        this.SHIRT_NUMBER = shirtNumber;
    }

    /**
     * @return - this players height
     */
    public double getHeight() {
        return HEIGHT;
    }

    /**
     * @return - this players name
     */
    public String getName() {
        return NAME;
    }

    /**
     * @return - this players nationality
     */
    public String getNationality() {
        return NATIONALITY;
    }

    /**
     * @return - this players stats
     */
    public PlayerStats getPlayerStats() {
        return PLAYER_STATS;
    }

    /**
     * @return - this players position
     */
    public String getPosition() {
        return POSITION;
    }

    /**
     * @return - this players preferred foot
     */
    public String getPreferredFoot() {
        return PREFERRED_FOOT;
    }

    /**
     * @return - this players shirt number
     */
    public int getShirtNumber() {
        return SHIRT_NUMBER;
    }

    /**
     * @return - overrun toString() method
     */
    @Override
    public String toString() {
        return "Player{" +
                "height=" + HEIGHT +
                ", name='" + NAME + '\'' +
                ", nationality='" + NATIONALITY + '\'' +
                ", playerStats=" + PLAYER_STATS +
                ", position='" + POSITION + '\'' +
                ", preferredFoot='" + PREFERRED_FOOT + '\'' +
                ", shirtNumber=" + SHIRT_NUMBER +
                '}';
    }
}
