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

    /**
     * Run this method first
     */
    @Before
    public void testASetUp() {
        this.premierLeagueManager = new PremierLeagueManager();
    }

    /**
     * Test method for add method of PremierLeagueManager
     * A couple of clubs are added, and checked whether they have actually been added
     */
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

        //*attempt to get the first added club, if it throws an IndexOutOfBoundsException, it means a club*//
        //*had not been added*//
        try {
            PremierLeagueManager.getAllFootballClubs().get(0);
        } catch (IndexOutOfBoundsException ignored) {
            fail("club has not been added");
        }

        //*is expected equal to actual*//
        assertEquals("abc", PremierLeagueManager.getAllFootballClubs().get(0).getClubName());
    }

    /**
     * Test method for delete
     * A club is deleted and it is checked whether the deleted club is what it is supposed to be
     */
    @Test
    public void testCDelete() {
        FootballClub deletedClub = this.premierLeagueManager.deleteClub("bcd");
        //*is the deleted club equal to the expected deleted club*//
        try {
            assertEquals("bcd", deletedClub.getClubName());
        } catch (Exception ignored) {
            fail("[ERROR] ==> Problem in delete method, club bcd exists, but was not deleted and obtained");
        }

        //*check whether it was actually removed*//
        FootballClub fakeDeletedClub = this.premierLeagueManager.deleteClub("bcd");
        try {
            assertNull(fakeDeletedClub);
        } catch (Exception ignored) {
            fail("[ERROR] ==> Problem in delete method, club bcd does not exist anymore.");
        }

        //**check to see whether the program can handle deleting on non-existent clubs*//
        FootballClub nextDeletedClub = this.premierLeagueManager.deleteClub("non existent club");
        try {
            assertNull(nextDeletedClub);
        } catch (Exception ignored) {
            fail("[ERROR] ==> Problem in delete method, club must be null");
        }
    }

    /**
     * Test method for display selected club
     * A preferred clubs name is passed, and it is checked whether the returned club is the specified one
     */
    @Test
    public void testDDisplaySelectedClub() {
        FootballClub selectedClub = this.premierLeagueManager.displaySelectedClub("abc");
        try {
            assertEquals("abc", selectedClub.getClubName());
        } catch (Exception ignored) {
            fail("[ERROR] ==> Problem with display selected club method, club abc exists, but was not obtained");
        }

        //*null checks*//
        FootballClub nextSelectedClub = this.premierLeagueManager.displaySelectedClub("non existent club");
        try {
            assertNull(nextSelectedClub);
        } catch (Exception ignored) {
            fail("[ERROR] ==> Problem with display selected club method, club must be null");
        }
    }

    /**
     * Test method for the manual addition of a match
     */
    @Test
    public void testEAddPlayedMatch() {
        this.premierLeagueManager.addClub("league", "efg", "efg", "efg",
                "efg", "efg", new Color(255, 0, 0), new Color(255, 0, 0),
                "efg"
        );

        //*test to check whether the specified match has been played*//
        this.premierLeagueManager.addPlayedMatch("2020", "10/10", "abc", "efg", 5, 5);
        try {
            assertEquals(PremierLeagueManager.getAllMatches().get(0),
                    new FootballMatch(PremierLeagueManager.getAllFootballClubs().get(0), PremierLeagueManager.getAllFootballClubs().get(1),
                            LocalDate.parse("10/10/2020", DateTimeFormatter.ofPattern("dd/MM/yyyy")))
            );
        } catch (Exception ignored) {
            fail("[ERROR] ==> problem with add played match method, match was not played");
        }

        this.premierLeagueManager.addClub("league", "hij", "hij", "hij",
                "hij", "hij", new Color(255, 0, 0), new Color(255, 0, 0),
                "hij"
        );

        //**check to see whether the program handles the prevention of repeated matches
        this.premierLeagueManager.addPlayedMatch("2020", "10/10", "abc", "efg", 5, 5);
        try {
            assertThrows(IndexOutOfBoundsException.class, () -> {
                PremierLeagueManager.getAllMatches().get(1);
            });
        } catch (Exception ignored) {
            fail("[ERROR] ==> problem with add played match method, a match was not supposed to have been played, since this match has already been played");
        }
    }

    /**
     * Test method for the randomly generated match
     */
    @Test
    public void testFAddPlayedMatchRandom() {
        //*check to see whether all possible matches do play*//
        this.premierLeagueManager.addPlayedMatchRandom("2020");
        try {
            assertNotNull(PremierLeagueManager.getAllMatches().get(1));
        } catch (Exception ignored) {
            fail("[ERROR] ==> problem with add played match method there are 3 clubs, therefore there are 3 total playable matches, 2 more can be played");
        }

        this.premierLeagueManager.addPlayedMatchRandom("2020");
//        System.out.println(PremierLeagueManager.getAllMatches().size());
        try {
            assertNotNull(PremierLeagueManager.getAllMatches().get(2));
        } catch (Exception ignored) {
            fail("[ERROR] ==> problem with add played match method there are 3 clubs, therefore there are 3 total playable matches, 1 more can be played");
        }

        //**check to see whether the program knows when all matches have already been played*//
        this.premierLeagueManager.addPlayedMatchRandom("2020");
        try {
            assertThrows(IndexOutOfBoundsException.class, () -> {
                PremierLeagueManager.getAllMatches().get(3);
            });
        } catch (Exception ignored) {
            fail("[ERROR] ==> problem with add played match method, a match was not supposed to have been played, since all matches hav already been played");
        }
    }

    /**
     * Test for displaying of a preferred match
     */
    @Test
    public void testGDisplaySelectedMatch() {
        //*tests to see whether a played match exists*//
        try {
            assertEquals(this.premierLeagueManager.displaySelectedMatch("abc", "efg"), PremierLeagueManager.getAllMatches().get(0));
        } catch (Exception ignored) {
            fail("[ERROR] ==> problem with display selected match method, this match has been played");
        }

        //*check to see whether the order of club inputs do not matter*//
        try {
            assertEquals(this.premierLeagueManager.displaySelectedMatch("efg", "abc"), PremierLeagueManager.getAllMatches().get(0));
        } catch (Exception ignored) {
            fail("[ERROR] ==> problem with display selected match method, this match has been played, " +
                    "there's a problem with the equals method, the order of the clubs involved in the match should not matter");
        }

        try {
            assertNull(this.premierLeagueManager.displaySelectedMatch("non-existent", "non-existent-2"));
        } catch (Exception ignored) {
            fail("[ERROR] ==> problem with display selected match method, this match has not been played, and" +
                    " is supposed to return null ");
        }
    }
}
