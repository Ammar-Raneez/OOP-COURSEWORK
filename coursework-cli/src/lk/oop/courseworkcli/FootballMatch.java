/*
 * FootballMatch
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

package lk.oop.courseworkcli;

import java.io.Serializable;
import java.util.Date;

/**
 * FootballMatch class, which will be used to represent any match between two Football clubs
 * @version 1.x November 9th 2020
 * @author Ammar Raneez
 */
class FootballMatch implements Serializable {
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
     * @return score of the first team
     */
    public int getFootballClub1Score() {
        return this.footballClub1.getMatchScore();
    }

    /**
     * sets first teams goal scored in a match
     * @param footballClub1Score - first teams goal scored
     */
    public void setFootballClub1Score(int footballClub1Score) {
        this.footballClub1.setMatchScore(footballClub1Score);
    }

    /**
     * @return score of the second team
     */
    public int getFootballClub2Score() {
        return this.footballClub2.getMatchScore();
    }

    /**
     * sets second teams goal scored in a match
     * @param footballClub2Score - second teams goal scored
     */
    public void setFootballClub2Score(int footballClub2Score) {
        this.footballClub2.setMatchScore(footballClub2Score);
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
     * @return overrun toString() method
     */
    @Override
    public String toString() {
        return "FootballMatch{" +
                "footballClub1=[" + footballClub1.getClubName() + " Score=" + footballClub1.getMatchScore() +
                "], footballClub2=[" + footballClub2.getClubName() + " Score=" + footballClub2.getMatchScore() +
                "], matchDate=" + matchDate +
                '}';
    }
}
