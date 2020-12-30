package services;

/*
 * PremierLeagueManagerTest
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import coursework.models.FootballClub;
import coursework.services.PremierLeagueManager;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.awt.*;

import static org.junit.Assert.*;

/**
 * PremierLeagueManagerTest class, unit testing class for methods in PremierLeagueManager
 * @version 1.x December 30th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class PremierLeagueManagerTest {
    PremierLeagueManager premierLeagueManager;

    @Before
    public void testASetUp() {
        this.premierLeagueManager = new PremierLeagueManager();
    }

    @Test
    public void testBAdd() {
        this.premierLeagueManager.addClub("league", "abc", "abc", "abc",
                    "abc", "abc", new Color(255, 0, 0), new Color(255, 0, 0),
                    "abc"
                );

        this.premierLeagueManager.addClub("league", "bcd", "bcd", "bcd",
                "bcd", "bcd", new Color(255, 0, 0), new Color(255, 0, 0),
                "bcd"
        );

        try {
            PremierLeagueManager.getAllFootballClubs().get(0);
        } catch (IndexOutOfBoundsException ignored) {
            fail("club has not been added");
        }

        assertEquals("abc", PremierLeagueManager.getAllFootballClubs().get(0).getClubName());
    }

    @Test
    public void testCDelete() {
        FootballClub deletedClub = this.premierLeagueManager.deleteClub("bcd");
        try {
            assertEquals("bcd", deletedClub.getClubName());
        } catch (Exception ignored) {
            fail("[ERROR] ==> Problem in delete method, club bcd exists, but was not obtained");
        }

        FootballClub nextDeletedClub = this.premierLeagueManager.deleteClub("non existent club");
        try {
            assertNull(nextDeletedClub);
        } catch (Exception ignored) {
            fail("[ERROR] ==> Problem in delete method, club must be null");
        }
    }

    @Test
    public void testDDisplaySelectedClub() {
        FootballClub selectedClub = this.premierLeagueManager.displaySelectedClub("abc");
        assertEquals("abc", selectedClub.getClubName());

        FootballClub nextSelectedClub = this.premierLeagueManager.displaySelectedClub("non existent club");
        assertNull(nextSelectedClub);
    }

    @Test
    public void testEAddPlayedMatch() {

    }
}
