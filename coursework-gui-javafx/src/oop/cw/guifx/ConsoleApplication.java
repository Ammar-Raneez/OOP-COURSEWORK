package oop.cw.guifx;

/*
 * ConsoleApplication
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import java.awt.*;
import java.lang.reflect.Field;

/**
 * ConsoleApplication class, the main runner class
 * @version 1.x November 15th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class ConsoleApplication {
    private static PremierLeagueManager premierLeagueManager = new PremierLeagueManager();

    /**
     * static method, that handles the adding of a club
     * Calls the addClub() method of PremierLeagueManager, passing the inputs obtained as parameters
     * @throws ClassNotFoundException - thrown in Color input
     * @throws IllegalAccessException - thrown in the get() method of Field
     * @throws InterruptedException - thrown in the sleep() function
     */
    public static void addClub() throws ClassNotFoundException, IllegalAccessException, InterruptedException {
        String clubTypeInput = PremierLeagueManager.getUserInput("Please enter the type of club " +
                "(University /School /" + "League level)");

        //*validate club type input ==> allow only 3 values, loop till a valid input has been given*//
        while (true) {
            if (clubTypeInput.equals("university") || clubTypeInput.equals("school") || clubTypeInput.equals("league")) {
                break;
            } else {
                clubTypeInput = PremierLeagueManager.getUserInput("Please specify appropriately! " +
                        "(university/school/league)");
            }
        }

        //*validate all inputs*//
        String clubNameInput = PremierLeagueManager.getUserInput("Enter Club's name");
        while (true) {
            if (clubNameInput.equals("")) {
                clubNameInput = PremierLeagueManager.getUserInput("Please enter a club name!");
            } else {
                break;
            }
        }

        //*allow only unique club names (unique clubs)*//
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
        while (true) {
            if (clubLocationInput.equals("")) {
                clubLocationInput = PremierLeagueManager.getUserInput("Please enter a club location!");
            } else {
                break;
            }
        }

        String clubOwnerInput = PremierLeagueManager.getUserInput("Enter club owner");
        while (true) {
            if (clubOwnerInput.equals("")) {
                clubOwnerInput = PremierLeagueManager.getUserInput("Please enter a club Owner!");
            } else {
                break;
            }
        }

        String clubSponsorInput = PremierLeagueManager.getUserInput("Enter club sponsor");
        while (true) {
            if (clubSponsorInput.equals("")) {
                clubSponsorInput = PremierLeagueManager.getUserInput("Please enter a club Sponsor!");
            } else {
                break;
            }
        }

        Color colorTop;
        Color colorShort;
        Field fieldTop;
        Field fieldBottom;

        //*color top validation ==> allow only specific colors*//
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

        //*color short validation ==> allow only specific colors*//
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

        String clubNetWorth = PremierLeagueManager.getUserInput("Please enter club net worth");
        while (true) {
            if (clubNetWorth.equals("")) {
                clubNetWorth = PremierLeagueManager.getUserInput("Please specify a club net worth!");
            } else {
                break;
            }
        }

        premierLeagueManager.addClub(clubTypeInput, clubNameInput, clubLocationInput, clubOwnerInput, clubSponsorInput,
                                     colorTop, colorShort, clubNetWorth);
        System.out.print("Now adding Football Club " + clubNameInput);
        PremierLeagueManager.threeDotSuspense();
        Thread.sleep(500);
        System.out.println(clubNameInput + " was successfully Promoted to the Premier League!");
    }

    /**
     * static method, that handles the deletion of a club
     * Calls the deleteClub() method of PremierLeagueManager, passing the club name obtained as the parameter
     * @throws InterruptedException - thrown in the sleep() method
     */
    public static void deleteClub() throws InterruptedException {
        String clubNameInput = PremierLeagueManager.getUserInput("Enter Club Name you wish to delete");
        SportsClub deletedClub = premierLeagueManager.deleteClub(clubNameInput);
        if (deletedClub != null) {
            System.out.print("Now deleting " + clubNameInput);
            PremierLeagueManager.threeDotSuspense();
            Thread.sleep(500);
            System.out.println(clubNameInput + " was successfully Relegated from the Premier League!");
        }
    }

    /**
     * static method, that handles the displaying of a selected club
     * Calls the displaySelectedClub() method of PremierLeagueManager, passing the club name obtained as a parameter
     */
    public static void displaySelectedClub() {
        String clubNameInput = PremierLeagueManager.getUserInput("Enter club name to display");
        premierLeagueManager.displaySelectedClub(clubNameInput);
    }

    /**
     * static method, that handles the playing of a match
     * Calls the addPlayedMatch() method of PremierLeagueManager
     */
    public static void addPlayedMatch() {
        premierLeagueManager.addPlayedMatch();
    }

    /**
     * static method, that handles the displaying of the points table
     * Calls the displayPointsTable() method of PremierLeagueManager
     */
    public static void displayPointsTable() {
        premierLeagueManager.displayPointsTable();
    }

    /**
     * static method, that handles the displaying of all match results
     * Calls the displayMatchResults() method of PremierLeagueManager
     */
    public static void displayMatchResults() {
        premierLeagueManager.displayMatchResults();
    }

    /**
     * static method, that handles the displaying of statistics of a selected match
     * Calls the displaySelectedMatchStatistics() method of PremierLeagueManager passing the two clubs involved in a
     * match as its parameters
     */
    public static void displaySelectedMatchStatistics() {
        String firstTeamInput = PremierLeagueManager.getUserInput("Enter First Club's Name:");
        String secondTeamInput = PremierLeagueManager.getUserInput("Enter Second Club's Name:");
        premierLeagueManager.displaySelectedMatchStatistics(firstTeamInput, secondTeamInput);
    }

    /**
     * static method, that handles the saving of data
     * Calls the saveData() method of PremierLeagueManager
     */
    public static void saveData() {
        premierLeagueManager.saveData();
    }

    /**
     * static method, that handles the loading of data
     * Calls the loadData() method of PremierLeagueManager
     */
    public static void loadData() {
        premierLeagueManager.loadData();
    }

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException, InterruptedException {
        loadData();
        System.out.println("***************************************************");
        System.out.println("WELCOME TO THE PREMIER LEAGUE");
        System.out.println("***************************************************");
        System.out.println("Enter a to promote a club to the Premier League");
        System.out.println("Enter d to relegate a club from the Premier League");
        System.out.println("Enter p to play a match");
        System.out.println("Enter z to display the current Premier League Standings");
        System.out.println("Enter c to display all Match Scores of the Premier League");
        System.out.println("Enter x to display a selected club");
        System.out.println("Enter s to display a selected match statistic");
        System.out.println("Enter q to exit");

        String userChoice = PremierLeagueManager.getUserInput("Please choose an option");
        infiniteLoop:
        while (true) {
            switch (userChoice) {
                case "a":
                    addClub();
                    userChoice = PremierLeagueManager.getUserInput("Please choose an option");
                    break;
                case "d":
                    deleteClub();
                    userChoice = PremierLeagueManager.getUserInput("Please choose an option");
                    break;
                case "p":
                    addPlayedMatch();
                    userChoice = PremierLeagueManager.getUserInput("Please choose an option");
                    break;
                case "z":
                    displayPointsTable();
                    userChoice = PremierLeagueManager.getUserInput("Please choose an option");
                    break;
                case "c":
                    displayMatchResults();
                    userChoice = PremierLeagueManager.getUserInput("Please choose an option");
                    break;
                case "x":
                    displaySelectedClub();
                    userChoice = PremierLeagueManager.getUserInput("Please choose an option");
                    break;
                case "s":
                    displaySelectedMatchStatistics();
                    userChoice = PremierLeagueManager.getUserInput("Please choose an option");
                    break;
                case "q":
                    saveData();
                    break infiniteLoop;
                default:
                    userChoice = PremierLeagueManager.getUserInput("Please choose a valid option");
            }
        }
    }
}
