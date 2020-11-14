/*
 * LeagueManager
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

package lk.oop.courseworkcli;

/**
 * LeagueManager interface, which will be used to hold generic methods that applies to any sport league
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public interface LeagueManager {
    void addClub() throws ClassNotFoundException, IllegalAccessException;
    SportsClub deleteClub();
    void displaySelectedClub();
    void addPlayedMatch();
    void displayPointsTable();
    void displayMatchResults();
    void displaySelectedMatchStatistics();
    void saveData();
    void loadData();
}
