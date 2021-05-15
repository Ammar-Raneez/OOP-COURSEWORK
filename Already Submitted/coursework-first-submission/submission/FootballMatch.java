/*
 * FootballMatch
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.io.Serializable;
import java.util.*;

/**
 * FootballMatch class, which will be used to represent any match between two Football clubs
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class FootballMatch implements Serializable, Comparable<FootballMatch> {
    private FootballClub firstTeam;
    private FootballClub secondTeam;
    private Date matchDate;
    private SingleMatchFootballClubStatistic firstTeamSingleMatchStats = new SingleMatchFootballClubStatistic();
    private SingleMatchFootballClubStatistic secondTeamSingleMatchStats = new SingleMatchFootballClubStatistic();

    /**
     * Returns the first team's statistics for this match
     * @return - first team match stats
     */
    public SingleMatchFootballClubStatistic getFirstTeamSingleMatchStats() {
        return firstTeamSingleMatchStats;
    }

    /**
     * Returns the second team's statistics for this match
     * @return - second team match stats
     */
    public SingleMatchFootballClubStatistic getSecondTeamSingleMatchStats() {
        return secondTeamSingleMatchStats;
    }

    /**
     * @return date of a match
     */
    public Date getMatchDate() {
        return matchDate;
    }

    /**
     * sets date of a match
     * @param matchDate - date of a specific match
     */
    public void setMatchDate(Date matchDate) {
        this.matchDate = matchDate;
    }

    /**
     * @return football club 1
     */
    public FootballClub getFirstTeam() {
        return firstTeam;
    }

    /**
     * @return football club 2
     */
    public FootballClub getSecondTeam() {
        return secondTeam;
    }

    /**
     * overridden compareTo() method - to sort the list of matches based on date
     * Needed for the displayMatchResults() method - to display most recent on top (descending order of date)
     * @param o - compares this date with o's date
     * @return - if this date > o's date returns a +ve value, a -ve vice versa, and 0 if they equal
     */
    @Override
    public int compareTo(FootballMatch o) {
        return this.getMatchDate().compareTo(o.getMatchDate());
    }

    /**
     * @return overrun toString() method
     */
    //TODO, improve toString() methods
    @Override
    public String toString() {
        return "FootballMatch{" +
                "footballClub1=" + firstTeam +
                ", footballClub1 match stats=" + firstTeamSingleMatchStats +
                ", footballClub2=" + secondTeam +
                ", footballClub2 match stats=" + secondTeamSingleMatchStats +
                ", matchDate=" + matchDate +
                '}';
    }


}
