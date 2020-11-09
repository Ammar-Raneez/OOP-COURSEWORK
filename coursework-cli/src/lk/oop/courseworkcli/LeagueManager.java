/*
 * LeagueManager
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

package lk.oop.courseworkcli;

/**
 * LeagueManager interface, which will be used to hold generic methods that applies to any sport league
 * @version 1.x November 9th 2020
 * @author Ammar Raneez
 */
public interface LeagueManager {
    void addClub() throws ClassNotFoundException, IllegalAccessException;
    SportsClub deleteClub();
    void displaySelectedClub();
    void addPlayedMatch();
    void displayPointsTable();
    void saveData();
    void loadData();
}
