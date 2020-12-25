package coursework.controllers;

/*
 * ConsoleController
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */


import coursework.models.FootballClub;
import coursework.models.FootballMatch;
import coursework.services.PremierLeagueManager;

import java.awt.*;
import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Scanner;
import java.util.regex.Pattern;

/**
 * ConsoleController class, the main cli runner class
 * @version 1.x November 15th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class ConsoleController {
    private static final Scanner SCANNER = new Scanner(System.in);
    //*regex for season input validation*//
    private static final Pattern SEASON_PATTERN = Pattern.compile("^\\d{4}");
    private static final PremierLeagueManager PREMIER_LEAGUE_MANAGER = new PremierLeagueManager();

    /**
     * Public helper method that displays a sentence and returns user's input transformed to lowercase and whitespaces
     * trimmed
     * Marked static since it's common for any object created
     * @param printSentence - sentence to use as a prompt
     * @return - the input of the user
     */
    public static String getUserInput(String printSentence) {
        System.out.println(printSentence);
        return SCANNER.nextLine().toLowerCase().trim();
    }

    /**
     * public helper method that is used for the common input string validation
     * @param printMessage - sentence to use as an initial prompt
     * @param retryMessage - sentence to use as the retry prompt (even after the initial one failed)
     * @return - valid user input
     */
    public static String userInputValidation(String printMessage, String retryMessage) {
        String userInput = getUserInput(printMessage);
        while (true) {
            if (userInput.equals("")) {
                userInput = getUserInput(retryMessage);
            } else {
                break;
            }
        }
        return userInput;
    }


    /**
     * static method, that handles the adding of a club
     * Calls the addClub() method of PremierLeagueManager, passing the inputs obtained as parameters
     * @throws ClassNotFoundException - thrown in Color input
     * @throws IllegalAccessException - thrown in the get() method of Field
     */
    public static void addClub() throws ClassNotFoundException, IllegalAccessException {
        //*only 20 teams can be added in the Premier League*//
        if (PremierLeagueManager.getAllMatches().size() == PremierLeagueManager.getMaxSize()) {
            System.out.println("[ERROR] ==> There cannot be more than 20 teams in the premier league!");
	    //*prevent further execution of program*//
            return;
        }

        //*validate club type input ==> allow only 3 values, loop till a valid input has been given*//
        //*loop endlessly, until a proper input has been given(non empty empty)*//
        String clubTypeInput = getUserInput("Please enter the type of club " +
                "(University /School /" + "League level)");
        while (true) {
            if (clubTypeInput.equals("university") || clubTypeInput.equals("school") || clubTypeInput.equals("league")) {
                break;
            } else {
                clubTypeInput = getUserInput("Please specify appropriately! " +
                        "(university/school/league)");
            }
        }
        //*based on type of club input, club type based inputs are taken for school and university*//
        String lecOrTeachInput = null;
        if (clubTypeInput.equals("university") || clubTypeInput.equals("school")) {
            String formattedPrint = clubTypeInput.equals("university") ? "Please enter the lecturer in charge" :
                    "Please enter the teacher in charge";
            lecOrTeachInput = getUserInput(formattedPrint);
            while (true) {
                if (lecOrTeachInput.equals("")) {
                    lecOrTeachInput = getUserInput(formattedPrint + "!");
                } else {
                    break;
                }
            }
        }

        //*club name validation - validate all inputs the same way*//
        String clubNameInput = userInputValidation("Enter Club name", "Please Enter a Club name!");
        //*allow only unique club names (unique clubs)*//
        boolean clubExists = false;
        while (true) {
            for (FootballClub footballClub : PremierLeagueManager.getAllFootballClubs()) {
                if (footballClub.getClubName().equals(clubNameInput)) {
                    clubExists = true;
                    //*club already exists*//
                    clubNameInput = userInputValidation("[ERROR] ==> " + 		    clubNameInput + " already exists! Please try again", "Please Enter a Club name!");
                    //*stop continuous looping, after a club has been found, to prevent unnecessary continuous looping*//
                    break;
                }
            }
            if (!clubExists) {
                break;
            } else {
                clubExists = false;
            }
        }

        //*club location validation*//
        String clubLocationInput = userInputValidation("Enter club location", "Please Enter a Club location!");
        //*club owner validation*//
        String clubOwnerInput = userInputValidation("Enter club owner", "Please Enter a Club owner!");
        //*club sponsor validation*//
        String clubSponsorInput = userInputValidation("Enter club sponsor", "Please Enter a Club sponsor!");

        //*Color and Field variables necessary for sports kit*//
        Color colorTop;
        Color colorShort;
        Field fieldTop;
        Field fieldShort;
        //*color top validation ==> only specific colors are allowed*//
        while (true) {
            try {
                String topColorInput = getUserInput("Enter club kit top color");
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
                String shortColorInput = getUserInput("Enter club kit short color");
                fieldShort = Class.forName("java.awt.Color").getField(shortColorInput);
                colorShort = (Color) fieldShort.get(null);
                break;
            } catch (NoSuchFieldException e) {
                System.out.println("[ERROR] ==> Please choose one of the valid colors! [black, blue, cyan, gray, green, "
                        + "magenta, orange, pink, red, white, yellow]");
            }
        }

        //*club net worth validation*//
        String clubNetWorth = userInputValidation("Enter club net worth", "Please Enter the Clubs net worth!");

        //*call add method passing all the inputs as arguments*//
        PREMIER_LEAGUE_MANAGER.addClub(clubTypeInput, lecOrTeachInput, clubNameInput, clubLocationInput, clubOwnerInput,
                                     clubSponsorInput, colorTop, colorShort, clubNetWorth);
        System.out.println("Now adding Football Club " + clubNameInput);
        System.out.println(clubNameInput + " was successfully Promoted to the Premier League!");
    }

    /**
     * static method, that handles the deletion of a club
     * Calls the deleteClub() method of PremierLeagueManager, passing the club name obtained as the parameter
-     */
    public static void deleteClub() {
        String clubNameInput = userInputValidation("Enter Club Name you wish to delete", "Please Enter a Club name!");
        FootballClub deletedClub = PREMIER_LEAGUE_MANAGER.deleteClub(clubNameInput);

        if (deletedClub != null) {
            System.out.println("Now deleting " + clubNameInput);
            System.out.println("Size decreased: " + PremierLeagueManager.getAllFootballClubs().size());
            System.out.println(clubNameInput + " was successfully Relegated from the Premier League!");
        } else {
            System.out.println("[ERROR] ==> No Such Club Exists");
        }
    }

    /**
     * static method, that handles the displaying of a selected club
     * Calls the displaySelectedClub() method of PremierLeagueManager, passing the club name obtained as a parameter
     * Obtains the club and prints the details
     */
    public static void displaySelectedClub() {
        String clubNameInput = userInputValidation("Enter club name to display", "Please Enter a Club name!");
        FootballClub foundClub = PREMIER_LEAGUE_MANAGER.displaySelectedClub(clubNameInput);

        if (foundClub != null) {
            System.out.println(
                    "Club: " + foundClub.getClubName() + " | Location: " + foundClub.getClubLocation() + " | Owner: " +
                            foundClub.getClubOwner() + " | Net Worth: " + foundClub.getClubNetWorth() + " | Points: " +
                            foundClub.getFootballClubTotalStatistics().getPoints() + " | Matches Played: " +
                            foundClub.getFootballClubTotalStatistics().getMatchesPlayed() + " | Wins: " +
                            foundClub.getFootballClubTotalStatistics().getWins() + " | Draws: " +
                            foundClub.getFootballClubTotalStatistics().getDraws() + " | Defeats: " +
                            foundClub.getFootballClubTotalStatistics().getDefeats()
            );
        } else {
            System.out.println("[ERROR] ==> No Such Club Exists");
        }
    }

    /**
     * static method, that handles the displaying of statistics of a selected match
     * Calls the displaySelectedMatchStatistics() method of PremierLeagueManager passing the two clubs involved in a match as its parameters
     * obtains the match and prints the statistics
     */
    public static void displaySelectedMatch() {
        String firstTeamInput = userInputValidation("Enter First Club's Name:", "Please Enter the first Clubs name!");
        String secondTeamInput = userInputValidation("Enter Second Club's Name:", "Please Enter the second Clubs name!");
        FootballMatch foundFootballMatch = PREMIER_LEAGUE_MANAGER.displaySelectedMatch(firstTeamInput, secondTeamInput);

        if (foundFootballMatch != null) {
            //*display all stats of the match, in a simple formatted string*//
            System.out.println("===================================================================");
            System.out.format("%-27s %-10s %28s", foundFootballMatch.getFirstTeam().getClubName().toUpperCase(),
                    "TEAM STATS", foundFootballMatch.getSecondTeam().getClubName().toUpperCase());
            System.out.println();
            System.out.format("%-30s %-5s %30s", foundFootballMatch.getFirstTeamSingleMatchStats().getGoals(),
                    "Goals", foundFootballMatch.getSecondTeamSingleMatchStats().getGoals());
            System.out.println();
            System.out.format("%-30s %-5s %30s", foundFootballMatch.getFirstTeamSingleMatchStats().getShots(),
                    "Shots", foundFootballMatch.getSecondTeamSingleMatchStats().getShots());
            System.out.println();
            System.out.format("%-25s %-15s %25s", foundFootballMatch.getFirstTeamSingleMatchStats().getShotsOnTarget(),
                    "Shots on target", foundFootballMatch.getSecondTeamSingleMatchStats().getShotsOnTarget());
            System.out.println();
            System.out.format("%-27s %-10s %28s", foundFootballMatch.getFirstTeamSingleMatchStats().getPossession(),
                    "Possession", foundFootballMatch.getSecondTeamSingleMatchStats().getPossession());
            System.out.println();
            System.out.format("%-29s %-6s %30s", foundFootballMatch.getFirstTeamSingleMatchStats().getPasses(),
                    "Passes", foundFootballMatch.getSecondTeamSingleMatchStats().getPasses());
            System.out.println();
            System.out.format("%-26s %-13s %26s", foundFootballMatch.getFirstTeamSingleMatchStats().getPassAccuracy(),
                    "Pass accuracy", foundFootballMatch.getSecondTeamSingleMatchStats().getPassAccuracy());
            System.out.println();
            System.out.format("%-30s %-5s %30s", foundFootballMatch.getFirstTeamSingleMatchStats().getFouls(),
                    "Fouls", foundFootballMatch.getSecondTeamSingleMatchStats().getFouls());
            System.out.println();
            System.out.format("%-26s %-12s %27s", foundFootballMatch.getFirstTeamSingleMatchStats().getYellowCards(),
                    "Yellow cards", foundFootballMatch.getSecondTeamSingleMatchStats().getYellowCards());
            System.out.println();
            System.out.format("%-28s %-9s %28s", foundFootballMatch.getFirstTeamSingleMatchStats().getRedCards(),
                    "Red cards", foundFootballMatch.getSecondTeamSingleMatchStats().getRedCards());
            System.out.println();
            System.out.format("%-28s %-8s %29s", foundFootballMatch.getFirstTeamSingleMatchStats().getOffsides(),
                    "Offsides", foundFootballMatch.getSecondTeamSingleMatchStats().getOffsides());
            System.out.println();
            System.out.format("%-29s %-7s %29s", foundFootballMatch.getFirstTeamSingleMatchStats().getCorners(),
                    "Corners", foundFootballMatch.getSecondTeamSingleMatchStats().getCorners());
            System.out.println("\n===================================================================");
        } else {
            System.out.println("[ERROR] ==> No such Football Match exists!");
        }
    }
    /**
     * static method, that handles the playing of a manually entered match
     * @param season - user season input
     */
    public static void addPlayedMatch(String season) {

    }

    /**
     * static method, that handles the playing of a randomly generated match
     * Calls the addPlayedMatchRandom() method of PremierLeagueManager
     * @param season - user season input
     */
    public static void addPlayedMatchRandom(String season) { PREMIER_LEAGUE_MANAGER.addPlayedMatchRandom(season); }

    /**
     * static method, that handles the displaying of the points table
     * Calls the displayPointsTable() method of PremierLeagueManager
     */
    public static void displayPointsTable() {
        PREMIER_LEAGUE_MANAGER.displayPointsTable();
    }

    /**
     * static method, that handles the displaying of all match results
     * Calls the displayMatchResults() method of PremierLeagueManager
     */
    public static void displayMatchResults() {
        PREMIER_LEAGUE_MANAGER.displayMatchResults();
    }

    /**
     * static method, that handles the saving of data
     * Calls the saveData() method of PremierLeagueManager
     * @param season - user season input
     */
    public static void saveData(String season) {
        PREMIER_LEAGUE_MANAGER.saveData(season);
    }

    /**
     * static method, that handles the loading of data
     * Calls the loadData() method of PremierLeagueManager
     * @param season - user season input
     */
    public static void loadData(String season) {
        PREMIER_LEAGUE_MANAGER.loadData(season);
    }

    /**
     * static method, that handles opening of the localhost of angular for the GUI
     */
    public static void openGui() {
        Desktop desktop = Desktop.getDesktop();

        try {
            desktop.browse(new URI("http://localhost:4200/"));
        } catch (URISyntaxException e) {
            System.out.println("URI was incorrect!");
        } catch (IOException e) {
            System.out.println("Something went wrong!");
        }
    }

    /**
     * Displays the Menu on the console
     */
    public static void printDisplay() {
        System.out.println("***************************************************");
        System.out.println("WELCOME TO THE PREMIER LEAGUE");
        System.out.println("***************************************************");
        System.out.println("Enter a to promote a club to the Premier League");
        System.out.println("Enter d to relegate a club from the Premier League");
        System.out.println("Enter p to play a match (Random Generation)");
        System.out.println("Enter l to play a match (Manual Entry)");
        System.out.println("Enter z to display the current Premier League Standings");
        System.out.println("Enter c to display all Match Scores of the Premier League");
        System.out.println("Enter x to display a selected club");
        System.out.println("Enter s to display a selected match statistic");
        System.out.println("Enter g to display GUI");
        System.out.println("Enter q to exit");
    }

    /**
     * Gets and returns the user input of the season year
     * Also calls the method that will write the season input to a file (Needed for Play to get access)
     * @return - the season year
     */
    public static String getSeasonInput() {
        String userSeasonChoice = getUserInput("Please enter the season (Ex: 2020 OR 2021)");

        //*loop infinitely till the inout matches the 4 digit regex defined initially*//
        while (true) {
            //*if and only if the input matches the pattern will the program continue*//
            if (SEASON_PATTERN.matcher(userSeasonChoice).matches()) {
                break;
            } else {
                System.out.println("INVALID Input!");
                userSeasonChoice = getUserInput("Please enter the season (Ex: 2020 OR 2021) appropriately!");
            }
        }
        PremierLeagueManager.setSeasonFile(userSeasonChoice);
        return userSeasonChoice;
    }

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException {
        String season = getSeasonInput();
        printDisplay();
        String userChoice = getUserInput("Please choose an option");
        infiniteLoop:
        while (true) {
            switch (userChoice) {
                case "a":
                    loadData(season);
                    addClub();
                    saveData(season);
                    printDisplay();
                    userChoice = getUserInput("Please choose an option");
                    break;
                case "d":
                    loadData(season);
                    deleteClub();
                    saveData(season);
                    printDisplay();
                    userChoice = getUserInput("Please choose an option");
                    break;
                case "p":
                    loadData(season);
                    addPlayedMatchRandom(season);
                    saveData(season);
                    printDisplay();
                    userChoice = getUserInput("Please choose an option");
                    break;
                case "z":
                    loadData(season);
                    displayPointsTable();
                    saveData(season);
                    printDisplay();
                    userChoice = getUserInput("Please choose an option");
                    break;
                case "c":
                    loadData(season);
                    displayMatchResults();
                    saveData(season);
                    printDisplay();
                    userChoice = getUserInput("Please choose an option");
                    break;
                case "x":
                    loadData(season);
                    displaySelectedClub();
                    saveData(season);
                    printDisplay();
                    userChoice = getUserInput("Please choose an option");
                    break;
                case "s":
                    loadData(season);
                    displaySelectedMatch();
                    saveData(season);
                    printDisplay();
                    userChoice = getUserInput("Please choose an option");
                    break;
                case "g":
                    openGui();
                    printDisplay();
                    userChoice = getUserInput("Please choose an option");
                    break;
                case "q":
                    saveData(season);
                    break infiniteLoop;
                default:
                    printDisplay();
                    userChoice = getUserInput("Please choose a valid option");
            }
        }
    }
}
