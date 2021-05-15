/*
 * PremierLeagueManager
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.util.*;

/**
 * PremierLeagueManager class
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class PremierLeagueManager {
    private static List<FootballMatch> allMatches = new ArrayList<>();
    private static List<FootballClub> allFootballClubs = new ArrayList<>();

    /**
     * @return list containing all the matches
     */
    public static List<FootballMatch> getAllMatches() {
        return allMatches;
    }

    /**
     * @return list containing all the football clubs
     */
    public static List<FootballClub> getAllFootballClubs() {
        return allFootballClubs;
    }
}
