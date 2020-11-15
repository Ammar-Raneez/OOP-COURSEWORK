/*
 * ConsoleApplication
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

package lk.oop.courseworkcli;

import java.awt.*;
import java.lang.reflect.Field;

/**
 * ConsoleApplication class, the main runner class
 * @version 1.x November 15th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class ConsoleApplication {
    private static PremierLeagueManager premierLeagueManager = new PremierLeagueManager();

    public static void addClub() throws ClassNotFoundException, IllegalAccessException {
        String clubTypeInput = PremierLeagueManager.getUserInput("Please enter the type of club " +
                "(University /School /" + "League level)");

        while (true) {
            if (clubTypeInput.equals("university") || clubTypeInput.equals("school") || clubTypeInput.equals("league")) {
                break;
            } else {
                clubTypeInput = PremierLeagueManager.getUserInput("Please specify appropriately! " +
                        "(university/school/league)");
            }
        }

        String clubNameInput = PremierLeagueManager.getUserInput("Enter Club's name");
        boolean clubExists = false;

        while (true) {
            for (FootballClub footballClub : PremierLeagueManager.getAllFootballClubs()) {
                if (footballClub.getClubName().equals(clubNameInput)) {
                    clubExists = true;
                    clubNameInput = PremierLeagueManager.getUserInput("[ERROR] ==> " + clubNameInput +
                            " already exists! Please try again");
                    break;
                }
            }
            if (!clubExists) {
                break;
            } else {
                clubExists = false;
            }
        }

        String clubLocationInput = PremierLeagueManager.getUserInput("Enter club location");
        String clubOwnerInput = PremierLeagueManager.getUserInput("Enter club owner");
        String clubSponsorInput = PremierLeagueManager.getUserInput("Enter club sponsor");

        Color colorTop;
        Color colorShort;
        Field fieldTop;
        Field fieldBottom;

        while (true) {
            try {
                String topColorInput = PremierLeagueManager.getUserInput("Enter club kit top color");
                fieldTop = Class.forName("java.awt.Color").getField(topColorInput);
                colorTop = (Color) fieldTop.get(null);
                break;
            } catch (NoSuchFieldException e) {
                System.out.println("[ERROR] ==> Please choose one of the valid colors! [black, blue, cyan, gray, green, "
                        + "magenta, orange, pink, red, white, yellow]");
            }
        }

        while (true) {
            try {
                String shortColorInput = PremierLeagueManager.getUserInput("Enter club kit short color");
                fieldBottom = Class.forName("java.awt.Color").getField(shortColorInput);
                colorShort = (Color) fieldBottom.get(null);
                break;
            } catch (NoSuchFieldException e) {
                System.out.println("[ERROR] ==> Please choose one of the valid colors! [black, blue, cyan, gray, green, "
                        + "magenta, orange, pink, red, white, yellow]");
            }
        }

        premierLeagueManager.addClub(clubTypeInput, clubNameInput, clubLocationInput, clubOwnerInput, clubSponsorInput,
                                     colorTop, colorShort);
    }

    public static void deleteClub() {
        String clubNameInput = PremierLeagueManager.getUserInput("Enter Club Name you wish to delete");
        SportsClub deletedClub = premierLeagueManager.deleteClub(clubNameInput);
        if (deletedClub != null) {
            System.out.println(clubNameInput + " was successfully deleted!");
        }
    }

    public static void displaySelectedClub() {
        String clubNameInput = PremierLeagueManager.getUserInput("Enter club name to display");
        premierLeagueManager.displaySelectedClub(clubNameInput);
    }

    public static void addPlayedMatch() {
        premierLeagueManager.addPlayedMatch();
    }

    public static void displayPointsTable() {
        premierLeagueManager.displayPointsTable();
    }

    public static void displayMatchResults() {
        premierLeagueManager.displayMatchResults();
    }

    public static void displaySelectedMatchStatistics() {
        String firstTeamInput = PremierLeagueManager.getUserInput("Enter First Club's Name:");
        String secondTeamInput = PremierLeagueManager.getUserInput("Enter Second Club's Name:");
        premierLeagueManager.displaySelectedMatchStatistics(firstTeamInput, secondTeamInput);
    }

    public static void saveData() {
        premierLeagueManager.saveData();
    }

    public static void loadData() {
        premierLeagueManager.loadData();
    }

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException {
        loadData();
//        addClub();
//        addClub();
        addPlayedMatch();
        displayPointsTable();
        displayMatchResults();
        displaySelectedMatchStatistics();
        saveData();
    }
}
