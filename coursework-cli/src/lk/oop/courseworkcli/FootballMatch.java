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
        List<Integer> secondTeamRandomStats = singleMatchRandomGeneratedStats();
        FootballClubTotalStatistics firstTeamTotalStats = footballClub1.getFootballClubTotalStatistics();
        FootballClubTotalStatistics secondTeamTotalStats = footballClub2.getFootballClubTotalStatistics();
        SingleMatchFootballClubStatistic firstTeamSingleMatchStats = footballClub1.getSingleMatchFootballClubStatistic();
        SingleMatchFootballClubStatistic secondTeamSingleMatchStats = footballClub2.getSingleMatchFootballClubStatistic();

        //*not randomly generate for both teams, since teams two's possession = 100 - team one's possession*//
        int firstTeamPossession = FootballMatch.random.nextInt(100 + 1);

        firstTeamSingleMatchStats.setPossession(firstTeamPossession);
        //*generating random values for a team playing a match*//
        updateSingleMatchTeamStats(firstTeamSingleMatchStats, firstTeamRandomStats, firstTeamTotalStats);
        footballClub1.setSingleMatchFootballClubStatistic(firstTeamSingleMatchStats);

        //*second team's possession = 100 - first team's*//
        secondTeamSingleMatchStats.setPossession((100 - firstTeamPossession));
        updateSingleMatchTeamStats(secondTeamSingleMatchStats, secondTeamRandomStats, secondTeamTotalStats);
        footballClub2.setSingleMatchFootballClubStatistic(secondTeamSingleMatchStats);

        updateClubTotalStatistics();
    }

    /**
     * updateClubTotalStatistics()
     * Private helper method that uses the private helper method setTeamTotalStats()
     * To update the stats of a club
     */
    private void updateClubTotalStatistics() {
        FootballClubTotalStatistics firstTeamTotalStats = footballClub1.getFootballClubTotalStatistics();
        FootballClubTotalStatistics secondTeamTotalStats = footballClub2.getFootballClubTotalStatistics();
        int firstTeamMatchGoal = footballClub1.getSingleMatchFootballClubStatistic().getGoals();
        int secondTeamMatchGoal = footballClub2.getSingleMatchFootballClubStatistic().getGoals();

        //*if first team wins, they get 3 points, if they lose the second teams gets 3, if its a draw*//
        //*both get a point each*//
        //*According to the team that is passes as the first argument the logic above occurs*//
        if (firstTeamMatchGoal > secondTeamMatchGoal) {
            setTeamTotalStats(firstTeamTotalStats, firstTeamMatchGoal, secondTeamTotalStats, secondTeamMatchGoal,
                       3);
        } else if (secondTeamMatchGoal > firstTeamMatchGoal) {
            setTeamTotalStats(secondTeamTotalStats, secondTeamMatchGoal, firstTeamTotalStats, firstTeamMatchGoal,
                       3);
        } else {
            setTeamTotalStats(firstTeamTotalStats, firstTeamMatchGoal, secondTeamTotalStats, secondTeamMatchGoal,
                       1);
        }
        footballClub1.setFootballClubTotalStatistics(firstTeamTotalStats);
        footballClub2.setFootballClubTotalStatistics(secondTeamTotalStats);
    }
    /**
     * A private helper method of updateClubTotalStatistics() that will set the stats of each team
     * To avoid duplication of code, since both teams values have to be set
     * @param firstTeam first teams total statistics
     * @param firstTeamMatchGoal first teams goals scored for this match
     * @param secondTeam second teams goals scored for this match
     * @param secondTeamMatchGoal second teams total statistics
     * @param points points of first team (to classify between wins and losses)
     */
    private void setTeamTotalStats(FootballClubTotalStatistics firstTeam, int firstTeamMatchGoal,
                                   FootballClubTotalStatistics secondTeam, int secondTeamMatchGoal, int points) {
        firstTeam.setGoalsAgainst(firstTeam.getGoalsAgainst() + secondTeamMatchGoal);
        firstTeam.setGoalsFor(firstTeam.getGoalsFor() + firstTeamMatchGoal);
        firstTeam.setMatchesPlayed(firstTeam.getMatchesPlayed() + 1);

        if (points == 3) {
            firstTeam.setPoints(firstTeam.getPoints() + points);
            firstTeam.setWins(firstTeam.getWins() + 1);
            secondTeam.setDefeats(secondTeam.getDefeats() + 1);
        } else {
            firstTeam.setPoints(firstTeam.getPoints() + 1);
            secondTeam.setPoints(secondTeam.getPoints() + 1);
            firstTeam.setDraws(firstTeam.getDraws() + 1);
            secondTeam.setDraws(secondTeam.getDraws() + 1);
        }

        secondTeam.setGoalsAgainst(secondTeam.getGoalsAgainst() + firstTeamMatchGoal);
        secondTeam.setGoalsFor(secondTeam.getGoalsFor() + secondTeamMatchGoal);
        secondTeam.setMatchesPlayed(secondTeam.getMatchesPlayed() + 1);
    }

    /**
     * private helper method of playMatch() that generates random values for the stats of a team for a match
     * @return list of randomly generated values
     */
    private List<Integer> singleMatchRandomGeneratedStats() {
        //generate random values within reasonable ranges for each stat recorded in a match**//
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
     * private helper method of playMatch() that uses the list of values to set the stats for each team of the match
     * @param singleMatchFootballClubStatistic - a clubs statistics for a single match (ex: shots, shots on target,
     *                                          fouls etc...)
     * @param teamRandomStats - the list that holds all the randomly generated values (generated from the above helper
     *                          function
     * @param footballClubTotalStatistics - a clubs total overall statistics (required here to modify total number of
     *                                      yellow and red cards obtained by a team
     */
    private void updateSingleMatchTeamStats(SingleMatchFootballClubStatistic singleMatchFootballClubStatistic,
                                            List<Integer> teamRandomStats,
                                            FootballClubTotalStatistics footballClubTotalStatistics) {
        //*method that uses the randomly generated values to set each field*//
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
