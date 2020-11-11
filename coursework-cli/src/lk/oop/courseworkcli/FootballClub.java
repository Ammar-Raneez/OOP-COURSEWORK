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
public class FootballClub extends SportsClub implements Serializable, Comparable<FootballClub> {
    private static final int NUMBER_OF_PLAYERS = 10;
    private FootballClubTotalStatistics footballClubTotalStatistics;
    private SingleMatchFootballClubStatistic singleMatchFootballClubStatistic;

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
    }

    /**
     * @return number of players in a football team
     */
    public static int getNumberOfPlayers() {
        return NUMBER_OF_PLAYERS;
    }

    /**
     * @return this clubs total statistic
     */
    public FootballClubTotalStatistics getFootballClubTotalStatistics() {
        return footballClubTotalStatistics;
    }

    /**
     * sets the total statistics for this club
     * @param footballClubTotalStatistics - this clubs total statistics
     */
    public void setFootballClubTotalStatistics(FootballClubTotalStatistics footballClubTotalStatistics) {
        this.footballClubTotalStatistics = footballClubTotalStatistics;
    }

    /**
     * @return this clubs statistic for a single match
     */
    public SingleMatchFootballClubStatistic getSingleMatchFootballClubStatistic() {
        return singleMatchFootballClubStatistic;
    }

    /**
     * sets the statistics for this club for a match
     * @param singleMatchFootballClubStatistic - this clubs statistics for a match
     */
    public void setSingleMatchFootballClubStatistic(SingleMatchFootballClubStatistic singleMatchFootballClubStatistic) {
        this.singleMatchFootballClubStatistic = singleMatchFootballClubStatistic;
    }

    /**
     * @return overrun toString() method
     */
    @Override
    public String toString() {
        return "FootballClub{" +
                super.toString() +
                "footballClubTotalStatistics=" + footballClubTotalStatistics +
                ", singleMatchFootballClubStatistic=" + singleMatchFootballClubStatistic +
                '}';
    }

    @Override
    public int compareTo(FootballClub o) {
        return this.getFootballClubTotalStatistics().getPoints() - o.getFootballClubTotalStatistics().getPoints();
    }
}
