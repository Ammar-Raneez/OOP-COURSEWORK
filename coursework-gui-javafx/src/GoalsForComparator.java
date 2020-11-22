/*
 * GoalsForComparator
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.util.Comparator;

/**
 * GoalsForComparator class, which will be used to sort the list of football clubs based on goals
 * scored
 * @version 1.x November 20th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class GoalsForComparator implements Comparator<FootballClub> {
    /**
     * overridden compare method, that can compare any two football clubs based on any attribute
     * @param footballClub1 - first football club
     * @param footballClub2 - second football club
     * @return - positive value if club1 goals for > club2 goals for. Negative if vice-versa. And 0 if equal
     */
    @Override
    public int compare(FootballClub footballClub1, FootballClub footballClub2) {
        return footballClub1.getFootballClubTotalStatistics().getGoalsFor() -
                footballClub2.getFootballClubTotalStatistics().getGoalsFor();
    }
}
