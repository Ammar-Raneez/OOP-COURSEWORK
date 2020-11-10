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
        List<Integer> firstTeamRandomStats = singleMatchRandomGeneratedStats();
        SingleMatchFootballClubStatistic firstTeamSingleMatchStats = footballClub1.getSingleMatchFootballClubStatistic();
        FootballClubTotalStatistics firstTeamTotalStats = footballClub1.getFootballClubTotalStatistics();
        int firstTeamPossession = FootballMatch.random.nextInt(100 + 1);

        firstTeamSingleMatchStats.setPossession(firstTeamPossession);
        updateSingleMatchTeamStats(firstTeamSingleMatchStats, firstTeamRandomStats, firstTeamTotalStats);
        footballClub1.setSingleMatchFootballClubStatistic(firstTeamSingleMatchStats);


        List<Integer> secondTeamRandomStats = singleMatchRandomGeneratedStats();
        SingleMatchFootballClubStatistic secondTeamSingleMatchStats = footballClub2.getSingleMatchFootballClubStatistic();
        FootballClubTotalStatistics secondTeamTotalStats = footballClub2.getFootballClubTotalStatistics();

        secondTeamSingleMatchStats.setPossession((100 - firstTeamPossession));
        updateSingleMatchTeamStats(secondTeamSingleMatchStats, secondTeamRandomStats, secondTeamTotalStats);
        footballClub2.setSingleMatchFootballClubStatistic(secondTeamSingleMatchStats);

        updateClubTotalStatistics();
    }

    private void updateClubTotalStatistics() {
        FootballClubTotalStatistics firstTeamTotalStats = footballClub1.getFootballClubTotalStatistics();
        FootballClubTotalStatistics secondTeamTotalStats = footballClub2.getFootballClubTotalStatistics();
        int firstTeamMatchGoal = footballClub1.getSingleMatchFootballClubStatistic().getGoals();
        int secondTeamMatchGoal = footballClub2.getSingleMatchFootballClubStatistic().getGoals();

        if (firstTeamMatchGoal > secondTeamMatchGoal) {
            firstTeamTotalStats.setGoalsAgainst(firstTeamTotalStats.getGoalsAgainst() + secondTeamMatchGoal);
            firstTeamTotalStats.setGoalsFor(firstTeamTotalStats.getGoalsFor() + firstTeamMatchGoal);
            firstTeamTotalStats.setMatchesPlayed(firstTeamTotalStats.getMatchesPlayed() + 1);
            firstTeamTotalStats.setPoints(firstTeamTotalStats.getPoints() + 3);
            firstTeamTotalStats.setWins(firstTeamTotalStats.getWins() + 1);

            secondTeamTotalStats.setGoalsAgainst(secondTeamTotalStats.getGoalsAgainst() + firstTeamMatchGoal);
            secondTeamTotalStats.setGoalsFor(secondTeamTotalStats.getGoalsFor() + secondTeamMatchGoal);
            secondTeamTotalStats.setMatchesPlayed(secondTeamTotalStats.getMatchesPlayed() + 1);
            secondTeamTotalStats.setDefeats(secondTeamTotalStats.getDefeats() + 1);
        } else if (secondTeamMatchGoal > firstTeamMatchGoal) {
            secondTeamTotalStats.setGoalsAgainst(secondTeamTotalStats.getGoalsAgainst() + firstTeamMatchGoal);
            secondTeamTotalStats.setGoalsFor(secondTeamTotalStats.getGoalsFor() + secondTeamMatchGoal);
            secondTeamTotalStats.setMatchesPlayed(secondTeamTotalStats.getMatchesPlayed() + 1);
            secondTeamTotalStats.setPoints(secondTeamTotalStats.getPoints() + 3);
            secondTeamTotalStats.setWins(secondTeamTotalStats.getWins() + 1);

            firstTeamTotalStats.setGoalsAgainst(firstTeamTotalStats.getGoalsAgainst() + secondTeamMatchGoal);
            firstTeamTotalStats.setGoalsFor(firstTeamTotalStats.getGoalsFor() + firstTeamMatchGoal);
            firstTeamTotalStats.setMatchesPlayed(firstTeamTotalStats.getMatchesPlayed() + 1);
            firstTeamTotalStats.setDefeats(firstTeamTotalStats.getDefeats() + 1);
        } else {
            firstTeamTotalStats.setMatchesPlayed(firstTeamTotalStats.getMatchesPlayed() + 1);
            firstTeamTotalStats.setPoints(firstTeamTotalStats.getPoints() + 1);
            firstTeamTotalStats.setDraws(firstTeamTotalStats.getDraws() + 1);

            secondTeamTotalStats.setMatchesPlayed(secondTeamTotalStats.getMatchesPlayed() + 1);
            secondTeamTotalStats.setPoints(secondTeamTotalStats.getPoints() + 1);
            secondTeamTotalStats.setDraws(secondTeamTotalStats.getDraws() + 1);
        }

        footballClub1.setFootballClubTotalStatistics(firstTeamTotalStats);
        footballClub2.setFootballClubTotalStatistics(secondTeamTotalStats);
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
                                            List<Integer> teamRandomStats,
                                            FootballClubTotalStatistics footballClubTotalStatistics) {
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

        footballClubTotalStatistics.setTotalRedCards(footballClubTotalStatistics.getTotalRedCards() +
                                                     teamRandomStats.get(6));
        footballClubTotalStatistics.setTotalYellowCards(footballClubTotalStatistics.getTotalYellowCards() +
                teamRandomStats.get(9));
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
