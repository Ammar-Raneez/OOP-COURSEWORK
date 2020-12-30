package services;

/*
 * PremierLeagueManagerTest
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import coursework.models.FootballClub;
import coursework.models.FootballMatch;
import coursework.services.PremierLeagueManager;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
            fail("[ERROR] ==> Problem in delete method, club bcd exists, but was not deleted and obtained");
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
        try {
            assertEquals("abc", selectedClub.getClubName());
        } catch (Exception ignored) {
            fail("[ERROR] ==> Problem with display selected club method, club abc exists, but was not obtained");
        }

        FootballClub nextSelectedClub = this.premierLeagueManager.displaySelectedClub("non existent club");
        try {
            assertNull(nextSelectedClub);
        } catch (Exception ignored) {
            fail("[ERROR] ==> Problem with display selected club method, club must be null");
        }
    }

    @Test
    public void testEAddPlayedMatch() {
        this.premierLeagueManager.addClub("league", "efg", "efg", "efg",
                "efg", "efg", new Color(255, 0, 0), new Color(255, 0, 0),
                "efg"
        );

        this.premierLeagueManager.addPlayedMatch("2020", "10/10", "abc", "efg", 5, 5);
        try {
            assertEquals(PremierLeagueManager.getAllMatches().get(0),
                    new FootballMatch(PremierLeagueManager.getAllFootballClubs().get(0), PremierLeagueManager.getAllFootballClubs().get(1),
                            LocalDate.parse("10/10/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            );
        } catch (Exception ignored) {
            fail("[ERROR] ==> problem with add played match method, match was not played");
        }

        this.premierLeagueManager.addPlayedMatch("2020", "10/10", "abc", "efg", 5, 5);
        try {
            assertThrows(IndexOutOfBoundsException.class, () -> {
                PremierLeagueManager.getAllMatches().get(1);
            });
        } catch (Exception ignored) {
            fail("[ERROR] ==> problem with add played match method, a match was not supposed to have been played, since this match has already been played");
        }
    }

    @Test
    public void testFAddPlayedMatchRandom() {

    }

    @Test
    public void testGDisplaySelectedMatch() {
        try {
            assertEquals(this.premierLeagueManager.displaySelectedMatch("abc", "efg"), PremierLeagueManager.getAllMatches().get(0));
        } catch (Exception ignored) {
            fail("[ERROR] ==> problem with display selected match method, this match has been played");
        }

        try {
            assertEquals(this.premierLeagueManager.displaySelectedMatch("efg", "abc"), PremierLeagueManager.getAllMatches().get(0));
        } catch (Exception ignored) {
            fail("[ERROR] ==> problem with display selected match method, this match has been played, " +
                    "there's a problem with the equals method, the order of the clubs involved in the match should not matter");
        }
    }
}
