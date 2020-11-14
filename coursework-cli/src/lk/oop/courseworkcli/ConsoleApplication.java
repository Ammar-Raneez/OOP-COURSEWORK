/*
 * ConsoleApplication
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

package lk.oop.courseworkcli;

import java.util.Scanner;

/**
 * ConsoleApplication class, the main runner class
 * @version 1.x November 15th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class ConsoleApplication {
    private static PremierLeagueManager premierLeagueManager = new PremierLeagueManager();
    private static Scanner sc = new Scanner(System.in);

    /**
     * Private helper method that displays a sentence and returns user's input
     * Marked static since it's common for any object created
     * @param printSentence - sentence to use as a prompt
     * @return - the input of the user
     */
    private static String getUserInput(String printSentence) {
        System.out.println(printSentence);
        return sc.nextLine().toLowerCase();
    }

    public static void addClub() {

    }

    public static void deleteClub() {
        String clubNameInput = getUserInput("Enter Club Name you wish to delete");
        SportsClub deletedClub = premierLeagueManager.deleteClub(clubNameInput);
        System.out.println(deletedClub + " was successfully deleted!");
    }

    public static void displaySelectedClub() {
        String clubNameInput = getUserInput("Enter club name to display");
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
        String firstTeamInput = getUserInput("Enter First Club's Name:");
        String secondTeamInput = getUserInput("Enter Second Club's Name:");
        premierLeagueManager.displaySelectedMatchStatistics(firstTeamInput, secondTeamInput);
    }

    public static void saveData() {
        premierLeagueManager.saveData();
    }

    public static void loadData() {
        premierLeagueManager.loadData();
    }

    public static void main(String[] args) {

    }
}
