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
    //*serialization happened to refer to a different serial id for objects of FootballMatch, therefore*//
    //*the expected value shown up on the terminal was hardcoded*//
    private static final long serialVersionUID = 1900807394549689165L;
    private static Random random = new Random();
    private FootballClub firstTeam;
    private FootballClub secondTeam;
    private Date matchDate;
    private SingleMatchFootballClubStatistic firstTeamSingleMatchStats = new SingleMatchFootballClubStatistic();
    private SingleMatchFootballClubStatistic secondTeamSingleMatchStats = new SingleMatchFootballClubStatistic();

    /**
     * initializes a match object
     * @param firstTeam - first team
     * @param secondTeam - second team
     * @param matchDate - date of a match
     */
    public FootballMatch(FootballClub firstTeam, FootballClub secondTeam, Date matchDate) {
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.matchDate = matchDate;
    }



    //*************************************PLAY MATCH METHOD BETWEEN TWO TEAMS***************************************//
    /**
     * Main function that handles playing of a single match
     * This function is called in the PremierLeagueManager class in the addMatch() method
     * Which handles all the necessary updates
     */
    public void playMatch() {
        //*randomly generated values of statistics for a single match*//
        List<Integer> firstTeamRandomStats = singleMatchRandomGeneratedStats();
        List<Integer> secondTeamRandomStats = singleMatchRandomGeneratedStats();
        //*total club statistics of the two teams*//
        FootballClubTotalStatistics firstTeamTotalStats = firstTeam.getFootballClubTotalStatistics();
        FootballClubTotalStatistics secondTeamTotalStats = secondTeam.getFootballClubTotalStatistics();
        //*single match statistics -> values need not be saved*//

        //*not randomly generate for both teams, since teams two's possession = 100 - team one's possession*//
        int firstTeamPossession = FootballMatch.random.nextInt(100 + 1);

        firstTeamSingleMatchStats.setPossession(firstTeamPossession);
        //*use the randomly generated values to update the attributes of a club*//
        updateSingleMatchTeamStats(firstTeamSingleMatchStats, firstTeamRandomStats, firstTeamTotalStats);

        //*second team's possession = 100 - first team's*//
        secondTeamSingleMatchStats.setPossession((100 - firstTeamPossession));
        updateSingleMatchTeamStats(secondTeamSingleMatchStats, secondTeamRandomStats, secondTeamTotalStats);

        //*to prevent matches from having the same results*//
        List<FootballMatch> footballMatches = PremierLeagueManager.getAllMatches();
        boolean uniqueGoal;
        do {
            uniqueGoal = true;
            for (FootballMatch footballMatch : footballMatches) {
                if ((footballMatch.getFirstTeamSingleMatchStats().getGoals() == firstTeamSingleMatchStats.getGoals() &&
                        footballMatch.getSecondTeamSingleMatchStats().getGoals() == secondTeamSingleMatchStats.getGoals()) ||
                        (footballMatch.getSecondTeamSingleMatchStats().getGoals() == firstTeamSingleMatchStats.getGoals() &&
                                footballMatch.getFirstTeamSingleMatchStats().getGoals() == secondTeamSingleMatchStats.getGoals())) {
                    //*changing one teams goal will be sufficient for generating a new result*//
                    firstTeamSingleMatchStats.setGoals(FootballMatch.random.nextInt(15 + 1));
                    uniqueGoal = false;
                }
            }
        } while (!uniqueGoal);

        //method that updates the total overall statistics (the above methods generate stats of a team in a match)
        updateClubTotalStatistics();
    }

    /**
     * updateClubTotalStatistics()
     * Private helper method that uses the private helper method setTeamTotalStats()
     * To update the stats of a club
     */
    private void updateClubTotalStatistics() {
        FootballClubTotalStatistics firstTeamTotalStats = firstTeam.getFootballClubTotalStatistics();
        FootballClubTotalStatistics secondTeamTotalStats = secondTeam.getFootballClubTotalStatistics();
        int firstTeamMatchGoal = firstTeamSingleMatchStats.getGoals();
        int secondTeamMatchGoal = secondTeamSingleMatchStats.getGoals();

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
        firstTeam.setFootballClubTotalStatistics(firstTeamTotalStats);
        secondTeam.setFootballClubTotalStatistics(secondTeamTotalStats);
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
        int goals = FootballMatch.random.nextInt(15 + 1);
        int fouls = FootballMatch.random.nextInt(15 - 5 + 1) + 5;
        int offsides = FootballMatch.random.nextInt(10 - 5 + 1) + 5;
        int passes = FootballMatch.random.nextInt(700 - 300 + 1) + 300;
        int passAccuracy = FootballMatch.random.nextInt(100 + 1);
        int redCards = FootballMatch.random.nextInt(3 + 1);
        int shots = FootballMatch.random.nextInt(50 - 20 + 1) + 20;
        int shotsOnTarget = FootballMatch.random.nextInt(50 + 1);
        int yellowCards = FootballMatch.random.nextInt(5 + 1);
        return Arrays.asList(corners, goals, fouls, offsides, passes, passAccuracy, redCards, shots, shotsOnTarget,
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

        //*also update the overall stats (total red and yellow cards obtained by a club)*//
        footballClubTotalStatistics.setTotalRedCards(footballClubTotalStatistics.getTotalRedCards() +
                                                     teamRandomStats.get(6));
        footballClubTotalStatistics.setTotalYellowCards(footballClubTotalStatistics.getTotalYellowCards() +
                teamRandomStats.get(9));
    }
    //*************************************END PLAY MATCH METHOD BETWEEN TWO TEAMS************************************//

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

    /**
     * Overrun equals method to check for any FootballMatch equality
     * Special equals method, that checks first team against first and second, and does the same for second team
     * This is done since the first team and second team could be in different ordering, but the match it refers to is
     * still the same
     * Not all attributes are checked against, since they can be duplicated
     * @param o - compare this FootballMatch with o
     * @return - t/f on whether the equality is satisfied
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FootballMatch that = (FootballMatch) o;
        return (Objects.equals(firstTeam, that.firstTeam) &&
                Objects.equals(secondTeam, that.secondTeam)) ||
                (Objects.equals(secondTeam, that.firstTeam) &&
                Objects.equals(firstTeam, that.secondTeam));
    }

    /**
     * If the above equals method returns true, the objects must have the same hashcode as well
     * @return - a hash value for the objects
     */
    @Override
    public int hashCode() {
        return Objects.hash(firstTeam, secondTeam);
    }
}
