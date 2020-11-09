/*
 * FootballClub
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

package lk.oop.courseworkcli;

import java.io.Serializable;

/**
 * FootballClub class, which will be used to represent any football club (sub class of SportsClub)
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class FootballClub extends SportsClub implements Serializable {
    private static final int NUMBER_OF_PLAYERS = 10;
    private FootballClubTotalStatistics footballClubTotalStatistics;
    private SingleMatchFootballClubStatistic singleMatchFootballClubStatistic;
    private int matchPossession;
    private int matchScore;

    /**
     * Constructor - takes in values and initializes a Football club object
     * (common attributes initialized through super class)
     * match score required as a temporary placeholder for a match score
     * footballClubTotalStatistics - another helper class that contains all club statistics
     * singleMatchFootballClubStatistic - another helper class that will holds statistics of a club of a single match
     * @param clubName - name of club
     * @param clubLocation - location of club
     * @param clubOwner - owner of club
     * @param kit - club kit (a helper class)
     * @return no return (constructor)
     */
    public FootballClub(String clubName, String clubLocation, String clubOwner, SportsClubKit kit) {
        super(clubName, clubLocation, clubOwner, kit, NUMBER_OF_PLAYERS);
        this.footballClubTotalStatistics = new FootballClubTotalStatistics();
        this.singleMatchFootballClubStatistic = new SingleMatchFootballClubStatistic();
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
    public FootballClubTotalStatistics getFootballClubTotalStatistics() {
        return footballClubTotalStatistics;
    }

    /**
     * sets the statistics for this club
     * @param footballClubTotalStatistics - this clubs statistics
     */
    public void setFootballClubTotalStatistics(FootballClubTotalStatistics footballClubTotalStatistics) {
        this.footballClubTotalStatistics = footballClubTotalStatistics;
    }

    /**
     * @return match possession of this club
     */
    public int getMatchPossession() {
        return matchPossession;
    }

    /**
     * sets a match possession for this club
     * @param matchPossession - possession for a specific match
     */
    public void setMatchPossession(int matchPossession) {
        this.matchPossession = matchPossession;
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
                ", clubStatistics=" + footballClubTotalStatistics +
                '}';
    }
}
