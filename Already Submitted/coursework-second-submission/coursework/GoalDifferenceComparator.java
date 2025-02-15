package coursework;/*
 * oop.cw.GoalDifferenceComparator
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.util.Comparator;

/**
 * oop.cw.GoalDifferenceComparator class, which will be used to sort the list of football clubs based on GD
 * Needed for an additional sorting of FootballClubs that have the same amount of points
 * @version 1.x November 11th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class GoalDifferenceComparator implements Comparator<FootballClub> {
    /**
     * overridden compare method, that can compare any two football clubs based on any attribute
     * @param footballClub1 - first football club
     * @param footballClub2 - second football club
     * @return - positive value if club1 GD > club2 GD. Negative if vice-versa. And 0 if equal
     */
    @Override
    public int compare(FootballClub footballClub1, FootballClub footballClub2) {
        return footballClub1.getFootballClubTotalStatistics().getGoalDifference() -
               footballClub2.getFootballClubTotalStatistics().getGoalDifference();
    }
}
