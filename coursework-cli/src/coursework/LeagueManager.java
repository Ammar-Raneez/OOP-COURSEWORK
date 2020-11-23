package coursework;/*
 * oop.cw.LeagueManager
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.awt.*;

/**
 * oop.cw.LeagueManager interface, which will be used to hold generic methods that applies to any sport league
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public interface LeagueManager {
    void addClub(String clubTypeInput, String clubNameInput, String clubLocationInput, String clubOwnerInput,
                 String clubSponsorInput, Color colorTop, Color colorShort, String netWorth);
    SportsClub deleteClub(String clubNameInput);
    void displaySelectedClub(String clubNameInput);
    void addPlayedMatch();
    void displayPointsTable();
    void displayMatchResults();
    void displaySelectedMatchStatistics(String firstTeamInput, String secondTeamInput);
    void saveData();
    void loadData();
}
