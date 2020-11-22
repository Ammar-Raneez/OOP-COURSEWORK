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
    private double height;
    private String name;
    private String nationality;
    private PlayerStats playerStats;
    private String position;
    private String preferredFoot;
    private int shirtNumber;

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
        this.height = height;
        this.name = name;
        this.nationality = nationality;
        this.playerStats = playerStats;
        this.position = position;
        this.preferredFoot = preferredFoot;
        this.shirtNumber = shirtNumber;
    }

    /**
     * @return - this players height
     */
    public double getHeight() {
        return height;
    }

//    /**
//     * sets player height
//     * @param height - player height
//     */
//    public void setHeight(double height) {
//        this.height = height;
//    }

    /**
     * @return - this players name
     */
    public String getName() {
        return name;
    }

//    /**
//     * sets player name
//     * @param name - player name
//     */
//    public void setName(String name) {
//        this.name = name;
//    }

    /**
     * @return - this players nationality
     */
    public String getNationality() {
        return nationality;
    }

//    /**
//     * sets player nationality
//     * @param nationality - player nationality
//     */
//    public void setNationality(String nationality) {
//        this.nationality = nationality;
//    }

    /**
     * @return - this players stats
     */
    public PlayerStats getPlayerStats() {
        return playerStats;
    }

//    /**
//     * sets player stats
//     * @param playerStats - player stats
//     */
//    public void setPlayerStats(PlayerStats playerStats) {
//        this.playerStats = playerStats;
//    }

    /**
     * @return - this players position
     */
    public String getPosition() {
        return position;
    }

//    /**
//     * sets this players position
//     * @param position - player position
//     */
//    public void setPosition(String position) {
//        this.position = position;
//    }

    /**
     * @return - this players preferred foot
     */
    public String getPreferredFoot() {
        return preferredFoot;
    }

//    /**
//     * sets this players preferred foot
//     * @param preferredFoot - player preferred foot
//     */
//    public void setPreferredFoot(String preferredFoot) {
//        this.preferredFoot = preferredFoot;
//    }

    /**
     * @return - this players shirt number
     */
    public int getShirtNumber() {
        return shirtNumber;
    }

//    /**
//     * sets this players shirt number
//     * @param shirtNumber - player shirt number
//     */
//    public void setShirtNumber(int shirtNumber) {
//        this.shirtNumber = shirtNumber;
//    }

    /**
     * @return - overrun toString() method
     */
    @Override
    public String toString() {
        return "Player{" +
                "height=" + height +
                ", name='" + name + '\'' +
                ", nationality='" + nationality + '\'' +
                ", playerStats=" + playerStats +
                ", position='" + position + '\'' +
                ", preferredFoot='" + preferredFoot + '\'' +
                ", shirtNumber=" + shirtNumber +
                '}';
    }
}
