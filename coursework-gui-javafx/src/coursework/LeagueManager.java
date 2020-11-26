package coursework;

/*
 * LeagueManager
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.awt.*;

/**
 * LeagueManager interface, which will be used to hold generic methods that applies to any sport league
 * @version 1.x November 9th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public interface LeagueManager {
    void addClub(String clubTypeInput, String lecOrTeachInput, String clubNameInput, String clubLocationInput,
                 String clubOwnerInput, String clubSponsorInput, Color colorTop, Color colorShort, String netWorth);
    SportsClub deleteClub(String clubNameInput);
    SportsClub displaySelectedClub(String clubNameInput);
    FootballMatch displaySelectedMatch(String firstTeamInput, String secondTeamInput);
    void addPlayedMatch();
    void displayPointsTable();
    void displayMatchResults();
    void saveData();
    void loadData();
}
