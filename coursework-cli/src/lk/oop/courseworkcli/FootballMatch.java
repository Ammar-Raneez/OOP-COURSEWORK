/*
 * FootballMatch
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

package lk.oop.courseworkcli;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * FootballMatch class, which will be used to represent any match between two Football clubs
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
class FootballMatch implements Serializable {
    private static Random random = new Random();
    private FootballClub footballClub1;
    private FootballClub footballClub2;
    private Date matchDate;

    /**
     * initializes a match object
     * @param footballClub1 - first team
     * @param footballClub2 - second team
     * @param matchDate - date of a match
     */
    public FootballMatch(FootballClub footballClub1, FootballClub footballClub2, Date matchDate) {
        this.footballClub1 = footballClub1;
        this.footballClub2 = footballClub2;
        this.matchDate = matchDate;
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



    //*************************************PLAY MATCH METHOD BETWEEN TWO TEAMS***************************************//
    public void playMatch() {
        int firstTeamPossession = FootballMatch.random.nextInt(100 + 1);
        List<Integer> firstTeamRandomStats = singleMatchRandomGeneratedStats();
        SingleMatchFootballClubStatistic firstTeamSingleMatchStats = footballClub1.getSingleMatchFootballClubStatistic();
        updateSingleMatchTeamStats(firstTeamSingleMatchStats, firstTeamRandomStats);
        firstTeamSingleMatchStats.setPossession(firstTeamPossession);
        footballClub1.setSingleMatchFootballClubStatistic(firstTeamSingleMatchStats);

        List<Integer> secondTeamRandomStats = singleMatchRandomGeneratedStats();
        SingleMatchFootballClubStatistic secondTeamSingleMatchStats = footballClub2.getSingleMatchFootballClubStatistic();
        updateSingleMatchTeamStats(secondTeamSingleMatchStats, secondTeamRandomStats);
        secondTeamSingleMatchStats.setPossession((100 - firstTeamPossession));
        footballClub2.setSingleMatchFootballClubStatistic(secondTeamSingleMatchStats);
    }

    /**
     * private helper method that generates random values for the stats of a team for a match
     * @return list of randomly generated values
     */
    private List<Integer> singleMatchRandomGeneratedStats() {
        int corners = FootballMatch.random.nextInt(30 - 5 + 1) + 5;
        int fouls = FootballMatch.random.nextInt(15 - 5 + 1) + 5;
        int goals = FootballMatch.random.nextInt(10 + 1);
        int offsides = FootballMatch.random.nextInt(10 - 5 + 1) + 5;
        int passes = FootballMatch.random.nextInt(700 - 300 + 1) + 300;
        int passAccuracy = FootballMatch.random.nextInt(100 + 1);
        int redCards = FootballMatch.random.nextInt(3 + 1);
        int shots = FootballMatch.random.nextInt(50 - 20 + 1) + 20;
        int shotsOnTarget = FootballMatch.random.nextInt(50 + 1);
        int yellowCards = FootballMatch.random.nextInt(10 - 5 + 1) + 5;
        return Arrays.asList(corners, fouls, goals, offsides, passes, passAccuracy, redCards, shots, shotsOnTarget,
                yellowCards);
    }

    /**
     * private method
     * helper method that uses the list of values to set the stats for each team of the match
     */
    private void updateSingleMatchTeamStats(SingleMatchFootballClubStatistic singleMatchFootballClubStatistic,
                                            List<Integer> teamRandomStats) {
        singleMatchFootballClubStatistic.setCorners(teamRandomStats.get(0));
        singleMatchFootballClubStatistic.setGoals(teamRandomStats.get(1));
        singleMatchFootballClubStatistic.setFouls(teamRandomStats.get(2));
        singleMatchFootballClubStatistic.setOffsides(teamRandomStats.get(3));
        singleMatchFootballClubStatistic.setPasses(teamRandomStats.get(4));
        singleMatchFootballClubStatistic.setPassAccuracy(teamRandomStats.get(5));
        singleMatchFootballClubStatistic.setRedCards(teamRandomStats.get(6));
        singleMatchFootballClubStatistic.setShots(teamRandomStats.get(7));
        singleMatchFootballClubStatistic.setShotsOnTarget(teamRandomStats.get(8));
        singleMatchFootballClubStatistic.setYellowCards(teamRandomStats.get(9));
    }
    //*************************************END PLAY MATCH METHOD BETWEEN TWO TEAMS************************************//



    /**
     * @return overrun toString() method
     */
    @Override
    public String toString() {
        return "FootballMatch{" +
                "footballClub1=" + footballClub1 +
                ", footballClub2=" + footballClub2 +
                ", matchDate=" + matchDate +
                '}';
    }
}
