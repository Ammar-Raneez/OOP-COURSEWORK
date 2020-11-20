package oop.cw.guifx;

/*
 * oop.cw.guifx.WinComparator
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.util.Comparator;

/**
 * oop.cw.guifx.WinComparator class, which will be used to sort the list of football clubs based on wins
 * @version 1.x November 20th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class WinComparator implements Comparator<FootballClub> {
    /**
     * overridden compare method, that can compare any two football clubs based on any attribute
     * @param footballClub1 - first football club
     * @param footballClub2 - second football club
     * @return - positive value if club1 wins > club2 wins. Negative if vice-versa. And 0 if equal
     */
    @Override
    public int compare(FootballClub footballClub1, FootballClub footballClub2) {
        return footballClub1.getFootballClubTotalStatistics().getWins() -
                footballClub2.getFootballClubTotalStatistics().getWins();
    }
}
