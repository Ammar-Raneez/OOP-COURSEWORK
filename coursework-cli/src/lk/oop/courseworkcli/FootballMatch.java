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
 * @author Ammar Raneez | 2019163 | W1761196
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
                "footballClub1=" + footballClub1 +
                ", footballClub2=" + footballClub2 +
                ", matchDate=" + matchDate +
                '}';
    }
}
