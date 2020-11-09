/*
 * FootballClub
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

package lk.oop.courseworkcli;

import java.io.Serializable;

/**
 * FootballClub class, which will be used to represent any football club (sub class of SportsClub)
 * @version 1.x November 9th 2020
 * @author Ammar Raneez
 */
public class FootballClub extends SportsClub implements Serializable {
    private static final int NUMBER_OF_PLAYERS = 10;
    private FootballClubStatistics footballClubStatistics;
    private int matchScore;

    /**
     * Constructor - takes in values and initializes a Football club object
     * (common attributes initialized through super class)
     * match score required as a temporary placeholder for a match score
     * footballClubStatistics - another helper class that contains all club statistics
     * @param clubName - name of club
     * @param clubLocation - location of club
     * @param clubOwner - owner of club
     * @param kit - club kit (a helper class)
     * @return no return (constructor)
     */
    public FootballClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit) {
        super(clubName, clubLocation, clubOwner, kit, NUMBER_OF_PLAYERS);
        this.footballClubStatistics = new FootballClubStatistics();
        this.matchScore = 0;
    }

    /**
     * @return number of players in a football team
     */
    public static int getNumberOfPlayers() {
        return NUMBER_OF_PLAYERS;
    }

    /**
     * @return this clubs statistic
     */
    public FootballClubStatistics getFootballClubStatistics() {
        return footballClubStatistics;
    }

    /**
     * sets the statistics for this club
     * @param footballClubStatistics - this clubs statistics
     */
    public void setFootballClubStatistics(FootballClubStatistics footballClubStatistics) {
        this.footballClubStatistics = footballClubStatistics;
    }

    /**
     * @return match score of this club
     */
    public int getMatchScore() {
        return matchScore;
    }

    /**
     * sets a match score for this club
     * @param matchScore - score for a specific match
     */
    public void setMatchScore(int matchScore) {
        this.matchScore = matchScore;
    }

    /**
     * @return overrun toString() method
     */
    @Override
    public String toString() {
        return "FootballClub{" +
                super.toString() +
                ", clubStatistics=" + footballClubStatistics +
                '}';
    }
}
