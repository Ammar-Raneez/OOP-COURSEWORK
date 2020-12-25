package coursework.models;

/*
 * FootballMatch
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import coursework.services.PremierLeagueManager;

import java.io.Serializable;
import java.time.LocalDate;
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
    private static final Random RANDOM = new Random();
    private final FootballClub FIRST_TEAM;
    private final FootballClub SECOND_TEAM;
    private final LocalDate MATCH_DATE;
    private final SingleMatchFootballClubStatistic FIRST_TEAM_SINGLE_MATCH_STATISTICS = new SingleMatchFootballClubStatistic();
    private final SingleMatchFootballClubStatistic SECOND_TEAM_SINGLE_MATCH_STATISTICS = new SingleMatchFootballClubStatistic();

    /**
     * initializes a match object
     * @param firstTeam - first team
     * @param secondTeam - second team
     * @param matchDate - date of a match
     */
    public FootballMatch(FootballClub firstTeam, FootballClub secondTeam, LocalDate matchDate) {
        this.FIRST_TEAM = firstTeam;
        this.SECOND_TEAM = secondTeam;
        this.MATCH_DATE = matchDate;
    }

    /**
     * private helper method for both methods of match play
     * Will set the statistics of the match
     * @param  firstTeamScore - first teams score
     * @param  secondTeamScore - second teams score
     */
    private void playMatchCommonCode(int firstTeamScore, int secondTeamScore) {
        //*randomly generated values of statistics for a single match*//
        List<Integer> firstTeamRandomStats = singleMatchRandomGeneratedStats(firstTeamScore);
        List<Integer> secondTeamRandomStats = singleMatchRandomGeneratedStats(secondTeamScore);

        //*total club statistics of the two teams*//
        FootballClubTotalStatistics firstTeamTotalStats = FIRST_TEAM.getFootballClubTotalStatistics();
        FootballClubTotalStatistics secondTeamTotalStats = SECOND_TEAM.getFootballClubTotalStatistics();
        int firstTeamPossession = FootballMatch.RANDOM.nextInt(100 + 1);

        //*not randomly generated for both teams, since teams two's possession = 100 - team one's possession*//
        FIRST_TEAM_SINGLE_MATCH_STATISTICS.setPossession(firstTeamPossession);

        //*use the randomly generated values to update the attributes of a club*//
        updateSingleMatchTeamStats(FIRST_TEAM_SINGLE_MATCH_STATISTICS, firstTeamRandomStats, firstTeamTotalStats);

        //*second team's possession = 100 - first team's*//
        SECOND_TEAM_SINGLE_MATCH_STATISTICS.setPossession((100 - firstTeamPossession));
        updateSingleMatchTeamStats(SECOND_TEAM_SINGLE_MATCH_STATISTICS, secondTeamRandomStats, secondTeamTotalStats);
    }

    //************************************MANUAL PLAY MATCH BETWEEN TWO TEAMS*****************************************//
    /**
     * Main function that handles playing of a manual match.
     * This function is called in the PremierLeagueManager class in addPlayedMatch() method
     * Handles the necessary updates
     * @param firstTeamScore - first teams score
     * @param secondTeamScore - second teams score
     */
    public void playMatch(int firstTeamScore, int secondTeamScore) {
        this.playMatchCommonCode(firstTeamScore, secondTeamScore);
        updateClubTotalStatistics();
    }
    //**********************************END MANUAL PLAY MATCH BETWEEN TWO TEAMS***************************************//

    //*********************************RANDOM PLAY MATCH METHOD BETWEEN TWO TEAMS*************************************//
    /**
     * Main function that handles playing of a single match randomly
     * This function is called in the PremierLeagueManager class in the addPlayedMatchRandom() method
     * Which handles all the necessary updates
     */
    public void playMatchRandom() {
//        List<Integer> firstTeamRandomStats = singleMatchRandomGeneratedStats(-1);
//        List<Integer> secondTeamRandomStats = singleMatchRandomGeneratedStats(-1);
//        FootballClubTotalStatistics firstTeamTotalStats = FIRST_TEAM.getFootballClubTotalStatistics();
//        FootballClubTotalStatistics secondTeamTotalStats = SECOND_TEAM.getFootballClubTotalStatistics();
//        int firstTeamPossession = FootballMatch.RANDOM.nextInt(100 + 1);
//        FIRST_TEAM_SINGLE_MATCH_STATISTICS.setPossession(firstTeamPossession);
//        updateSingleMatchTeamStats(FIRST_TEAM_SINGLE_MATCH_STATISTICS, firstTeamRandomStats, firstTeamTotalStats);
//        SECOND_TEAM_SINGLE_MATCH_STATISTICS.setPossession((100 - firstTeamPossession));
//        updateSingleMatchTeamStats(SECOND_TEAM_SINGLE_MATCH_STATISTICS, secondTeamRandomStats, secondTeamTotalStats);
        this.playMatchCommonCode(-1, -1);

        //*to prevent matches from having the same score results*//
        List<FootballMatch> footballMatches = PremierLeagueManager.getAllMatches();
        boolean uniqueGoal;
        //*the logic here is to continuously loop and generate goals until this match's score is unique*//
        //*checking this football match's goals against all the FootballMatches in the list*//
        do {
            uniqueGoal = true;
            for (FootballMatch footballMatch : footballMatches) {
                if ((footballMatch.getFirstTeamSingleMatchStats().getGoals() == FIRST_TEAM_SINGLE_MATCH_STATISTICS.getGoals() &&
                        footballMatch.getSecondTeamSingleMatchStats().getGoals() == SECOND_TEAM_SINGLE_MATCH_STATISTICS.getGoals()) ||
                        (footballMatch.getSecondTeamSingleMatchStats().getGoals() == FIRST_TEAM_SINGLE_MATCH_STATISTICS.getGoals() &&
                                footballMatch.getFirstTeamSingleMatchStats().getGoals() == SECOND_TEAM_SINGLE_MATCH_STATISTICS.getGoals())) {
                    //*changing one teams goal will be sufficient for generating a new result*//
                    FIRST_TEAM_SINGLE_MATCH_STATISTICS.setGoals(FootballMatch.RANDOM.nextInt(15 + 1));
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
        FootballClubTotalStatistics firstTeamTotalStats = FIRST_TEAM.getFootballClubTotalStatistics();
        FootballClubTotalStatistics secondTeamTotalStats = SECOND_TEAM.getFootballClubTotalStatistics();
        int firstTeamMatchGoal = FIRST_TEAM_SINGLE_MATCH_STATISTICS.getGoals();
        int secondTeamMatchGoal = SECOND_TEAM_SINGLE_MATCH_STATISTICS.getGoals();

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
        FIRST_TEAM.setFootballClubTotalStatistics(firstTeamTotalStats);
        SECOND_TEAM.setFootballClubTotalStatistics(secondTeamTotalStats);
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

        //*if points = 3, then a team has won*//
        if (points == 3) {
            firstTeam.setPoints(firstTeam.getPoints() + points);
            firstTeam.setWins(firstTeam.getWins() + 1);
            secondTeam.setDefeats(secondTeam.getDefeats() + 1);
        //*if not, it was a draw*//
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
    private List<Integer> singleMatchRandomGeneratedStats(int goals) {
        //generate random values within reasonable ranges for each stat recorded in a match**//
        int corners = FootballMatch.RANDOM.nextInt(30 - 5 + 1) + 5;
        if (goals == -1) {
            goals = FootballMatch.RANDOM.nextInt(15 + 1);
        }
        int fouls = FootballMatch.RANDOM.nextInt(15 - 5 + 1) + 5;
        int offsides = FootballMatch.RANDOM.nextInt(10 - 5 + 1) + 5;
        int passes = FootballMatch.RANDOM.nextInt(700 - 300 + 1) + 300;
        int passAccuracy = FootballMatch.RANDOM.nextInt(100 + 1);
        int redCards = FootballMatch.RANDOM.nextInt(3 + 1);
        int shots = FootballMatch.RANDOM.nextInt(50 - 20 + 1) + 20;
        int shotsOnTarget = FootballMatch.RANDOM.nextInt(50 + 1);
        int yellowCards = FootballMatch.RANDOM.nextInt(5 + 1);
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
        return FIRST_TEAM_SINGLE_MATCH_STATISTICS;
    }

    /**
     * Returns the second team's statistics for this match
     * @return - second team match stats
     */
    public SingleMatchFootballClubStatistic getSecondTeamSingleMatchStats() {
        return SECOND_TEAM_SINGLE_MATCH_STATISTICS;
    }

    /**
     * @return date of a match
     */
    public LocalDate getMatchDate() {
        return MATCH_DATE;
    }

//    /**
//     * sets date of a match
//     * @param matchDate - date of a specific match
//     */
//    public void setMatchDate(LocalDate matchDate) {
//        this.matchDate = matchDate;
//    }

    /**
     * @return football club 1
     */
    public FootballClub getFirstTeam() {
        return FIRST_TEAM;
    }

    /**
     * @return football club 2
     */
    public FootballClub getSecondTeam() {
        return SECOND_TEAM;
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
    @Override
    public String toString() {
        return "FootballMatch{" +
                "footballClub1=" + FIRST_TEAM +
                ", footballClub1 match stats=" + FIRST_TEAM_SINGLE_MATCH_STATISTICS +
                ", footballClub2=" + SECOND_TEAM +
                ", footballClub2 match stats=" + SECOND_TEAM_SINGLE_MATCH_STATISTICS +
                ", matchDate=" + MATCH_DATE +
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
        return (Objects.equals(FIRST_TEAM, that.FIRST_TEAM) &&
                Objects.equals(SECOND_TEAM, that.SECOND_TEAM)) ||
                (Objects.equals(SECOND_TEAM, that.FIRST_TEAM) &&
                Objects.equals(FIRST_TEAM, that.SECOND_TEAM));
    }

    /**
     * If the above equals method returns true, the objects must have the same hashcode as well
     * @return - a hash value for the objects
     */
    @Override
    public int hashCode() {
        return Objects.hash(FIRST_TEAM, SECOND_TEAM);
    }
}
