package coursework;

/*
 * FootballClubStatistics
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.io.Serializable;

/**
 * FootballClubStatistics class, which will be used to represent any football clubs total statistics
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class FootballClubTotalStatistics implements Serializable {
    private static final long serialVersionUID = -30110101487621832L;
    private int defeats;
    private int draws;
    private int goalsAgainst;
    private int goalsFor;
    private int matchesPlayed;
    private int points;
    private int totalRedCards;
    private int totalYellowCards;
    private int wins;

    /**
     * initializes a club statistic object
     * all attributes initialized to 0, since a new club will not have any record
     */
    public FootballClubTotalStatistics() { }

    /**
     * @return total defeats of this club
     */
    public int getDefeats() {
        return defeats;
    }

    /**
     * sets defeats of this club
     * @param defeats - number of defeats of this club
     */
    public void setDefeats(int defeats) {
        this.defeats = defeats;
    }

    /**
     * @return total draws of this club
     */
    public int getDraws() {
        return draws;
    }

    /**
     * sets draws of this club
     * @param draws - number of draws of this club
     */
    public void setDraws(int draws) {
        this.draws = draws;
    }

    /**
     * @return total goals scored against this club
     */
    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    /**
     * sets goals scored against this club
     * @param goalsAgainst - total number of goals scored against this club
     */
    public void setGoalsAgainst(int goalsAgainst) {
        this.goalsAgainst = goalsAgainst;
    }

    /**
     * @return total goal difference scored against this club
     */
    public int getGoalDifference() { return goalsFor - goalsAgainst; }

    /**
     * @return total goals scored by this club
     */
    public int getGoalsFor() {
        return goalsFor;
    }

    /**
     * sets goals scored by this club
     * @param goalsFor - number of goals scored by this club
     */
    public void setGoalsFor(int goalsFor) {
        this.goalsFor = goalsFor;
    }

    /**
     * @return total matches played by this club
     */
    public int getMatchesPlayed() {
        return matchesPlayed;
    }

    /**
     * sets matches played by this club
     * @param matchesPlayed - total number of matches played by this club
     */
    public void setMatchesPlayed(int matchesPlayed) {
        this.matchesPlayed = matchesPlayed;
    }

    /**
     * @return total points of this club
     */
    public int getPoints() {
        return points;
    }

    /**
     * sets points obtained by this club
     * @param points - total number of points obtained by this club
     */
    public void setPoints(int points) {
        this.points = points;
    }

    /**
     * @return total red cards obtained by this club
     */
    public int getTotalRedCards() {
        return totalRedCards;
    }

    /**
     * sets red cards obtained by this club
     * @param totalRedCards - total number of red cards obtained by this club
     */
    public void setTotalRedCards(int totalRedCards) {
        this.totalRedCards = totalRedCards;
    }

    /**
     * @return total yellow cards obtained by this club
     */
    public int getTotalYellowCards() {
        return totalYellowCards;
    }

    /**
     * sets yellow cards obtained by this club
     * @param totalYellowCards - total number of yellow cards obtained by this club
     */
    public void setTotalYellowCards(int totalYellowCards) {
        this.totalYellowCards = totalYellowCards;
    }

    /**
     * @return total wins of this club
     */
    public int getWins() {
        return wins;
    }

    /**
     * sets wins of this club
     * @param wins - number of wins of this club
     */
    public void setWins(int wins) {
        this.wins = wins;
    }

    /**
     * @return overrun toString() method
     */
    //TODO, improve toString() methods
    @Override
    public String toString() {
        return "FootballClubStatistics{" +
                "wins=" + wins +
                ", draws=" + draws +
                ", defeats=" + defeats +
                ", goalsFor=" + goalsFor +
                ", goalsAgainst=" + goalsAgainst +
                ", points=" + points +
                ", matchesPlayed=" + matchesPlayed +
                ", totalRedCards=" + totalRedCards +
                '}';
    }
}
