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
    //*any 4 digit number*//
    private static final Pattern SEASON_PATTERN = Pattern.compile("^\\d{4}");
    //*validation to ensure month is 1-12, and date 1-31*//
    private static final Pattern MANUAL_DATE_ENTRY_PATTERN = Pattern.compile("(0[1-9]|1[0-9]|2[0-9]|3[0-1])-(0[1-9]|1[0-2])");
    private static final PremierLeagueManager PREMIER_LEAGUE_MANAGER = new PremierLeagueManager();

    //***********************************************HELPER METHODS***************************************************//
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
     * public helper method that is used for the date or season input validation
     * @param regex - the Pattern regex to use
     * @param inputMsg - sentence to use as an initial prompt
     * @param validationMsg  sentence to use as the retry prompt
     * @return a valid inputted date or season
     */
    public static String validateDateAndSeasonInput(Pattern regex, String inputMsg, String validationMsg) {
        String dateOrSeason = ConsoleController.userInputValidation(inputMsg, validationMsg);
        while (true) {
            if (regex.matcher(dateOrSeason).matches()) {
                break;
            } else {
                dateOrSeason = getUserInput(validationMsg);
            }
        }
        return dateOrSeason;
    }
    //**********************************************END HELPER METHODS************************************************//



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
                    clubNameInput = userInputValidation("[ERROR] ==> " + clubNameInput + " already exists! Please try again",
                            "Please Enter a Club name!");
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
            //*display all stats of the match, in a simple formatted way*//
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
        //*this if condition checks whether there are enough teams to play a match in the first place*//
        if (PremierLeagueManager.getAllFootballClubs().size() < 2) {
            System.out.println("[ERROR] ==> There isn't enough teams to play a match!");
            return;
        }

        boolean allMatchesPlayed = PremierLeagueManager.validatePlayableMatches(season);
        //*if all matches are already played, then the method can stop immediately*//
        if (allMatchesPlayed) {
            System.out.println("[ERROR] ==> All Possible Matches have already been played!");
            return;
        }

        //*validate date against regex*//
        String date = ConsoleController.validateDateAndSeasonInput(MANUAL_DATE_ENTRY_PATTERN, "Please enter a date (dd-mm)",
                "Invalid input! Please specify a date! (dd-mm)");

        //*get validated inputs associated with the clubs and their score for the match, loop continuously till a valid club name is entered*//
        String firstTeam; /*= ConsoleController.userInputValidation("Please enter the first clubs name", "Please specify a club name!");*/
        boolean validFirstTeam; /* = validateAddPlayedMatchClubName(firstTeam);*/
//        if (!validFirstTeam) {
//            return;
//        }
        do {
            firstTeam = ConsoleController.userInputValidation("Please enter the first clubs name", "Please specify a club name!");
            validFirstTeam = validateAddPlayedMatchClubName(firstTeam);
        } while (!validFirstTeam);

        String firstTeamScore = ConsoleController.userInputValidation("Please enter the first clubs score", "Please specify a score!");
        int validatedFirstTeamScore = validateAddPlayedMatchClubScore(firstTeamScore);

        String secondTeam; /*= ConsoleController.userInputValidation("Please enter the second clubs name", "Please specify a club name!");*/
        boolean validSecondTeam; /*= validateAddPlayedMatchClubName(secondTeam);*/
//        if (!validSecondTeam) {
//            return;
//        }
        do {
            secondTeam = ConsoleController.userInputValidation("Please enter the second clubs name", "Please specify a club name!");
            validSecondTeam = validateAddPlayedMatchClubName(secondTeam);
        } while (!validSecondTeam);

        //*the same club can't play against themselves*//
        if (firstTeam.equals(secondTeam)) {
            System.out.println("[ERROR] ==> the same club cannot play against itself!");
            return;
        }
        String secondTeamScore = ConsoleController.userInputValidation("Please enter the second clubs score", "Please specify a score!");
        int validatedSecondTeamScore = validateAddPlayedMatchClubScore(secondTeamScore);

        //*if the program were to reach this statement, all inputs are valid, and hence a match is playable*//
        PREMIER_LEAGUE_MANAGER.addPlayedMatch(season, date, firstTeam, secondTeam, validatedFirstTeamScore, validatedSecondTeamScore);
    }
    /**
     * private helper method of addPlayedMatch()
     * To validate the club name input
     * @param clubName - input club name
     * @return t/f whether club entered is actually a club in the Premier League
     */
    private static boolean validateAddPlayedMatchClubName(String clubName) {
        boolean validFirstTeam = false;
        //*check whether the list of clubs contain the specified club or not, if not, the method is terminated*//
        for (FootballClub club : PremierLeagueManager.getAllFootballClubs()) {
            if (club.getClubName().equals(clubName)) {
                validFirstTeam = true;
                break;
            }
        }

        if (!validFirstTeam) {
            System.out.println("[ERROR] ==> club specified does not exist!");
        }
        return validFirstTeam;
    }
    /**
     *  private helper method of addPlayedMatch()
     *  To validate club score input
     * @param score - input club score
     * @return the score parsed into an integer
     */
    private static int validateAddPlayedMatchClubScore(String score) {
        int parsedScore;
        while (true) {
            //*Try to convert the input to integer, if errored, ask score input again*//
            try {
                parsedScore = Integer.parseInt(score);
                //*only integers greater than 0 is accepted*//
                while(parsedScore < 0) {
                    parsedScore = Integer.parseInt(getUserInput("Goal cannot be less than 0!"));
                }
                break;
            } catch (Exception e) {
                score = getUserInput("Please specify an Integer as the score!");
            }
        }
        return parsedScore;
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
    public static void displayPointsTable() { PREMIER_LEAGUE_MANAGER.displayPointsTable(); }

    /**
     * static method, that handles the displaying of all match results
     * Calls the displayMatchResults() method of PremierLeagueManager
     */
    public static void displayMatchResults() { PREMIER_LEAGUE_MANAGER.displayMatchResults(); }

    /**
     * static method, that handles the saving of data
     * Calls the saveData() method of PremierLeagueManager
     * @param season - user season input
     */
    public static void saveData(String season) { PREMIER_LEAGUE_MANAGER.saveData(season); }

    /**
     * static method, that handles the loading of data
     * Calls the loadData() method of PremierLeagueManager
     * @param season - user season input
     */
    public static void loadData(String season) { PREMIER_LEAGUE_MANAGER.loadData(season); }

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
        String userSeasonChoice = validateDateAndSeasonInput(SEASON_PATTERN, "Please enter the season (Ex 2020 or 2021)",
                "Please enter the season (Ex: 2020 OR 2021) appropriately!");
        PremierLeagueManager.setSeasonFile(userSeasonChoice);
        return userSeasonChoice;
    }

    /**
     * Main method, brain of the program, keep running the program, displaying the menu after each method, for a continuous endless loop
     * Only end if explicitly commanded to, upon prompted for a choice on the menu
     * @param args - command line arguments
     * @throws IllegalAccessException - thrown in get() method of Field
     * @throws ClassNotFoundException - thrown in Color input
     */
    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException {
        String season = getSeasonInput();
        printDisplay();
        String userChoice = userInputValidation("Please choose an option", "Please choose an option!");
        infiniteLoop:
        while (true) {
            switch (userChoice) {
                case "a":
                    //*save and load before and after each method respectively. So that the content is always updated*//
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
                case "l":
                    loadData(season);
                    addPlayedMatch(season);
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
