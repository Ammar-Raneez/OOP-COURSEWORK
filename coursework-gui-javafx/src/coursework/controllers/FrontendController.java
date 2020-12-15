package coursework.controllers;

/*
 * FrontendController
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import coursework.models.FootballClub;
import coursework.models.FootballMatch;
import coursework.services.PremierLeagueManager;
import coursework.utils.FrontendUtils;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * FrontendController class, will be main running class, will call methods from ConsoleController and
 * FrontendUtils
 * @version 1.x December 1st 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class FrontendController extends Application {
    //*season regex*//
    private static final Pattern SEASON_PATTERN = Pattern.compile("^\\d{4}");

    @Override
    public void start(Stage primaryStage) throws IllegalAccessException, InterruptedException, ClassNotFoundException {
        //*initially asks user to input year of season, validated using the regex defined initially*//
        String userSeasonChoice = ConsoleController.getUserInput("Please enter the season (Ex: 2020 OR 2021)");
        while (true) {
            //*if and only if the input matches the pattern will the program continue*//
            if (SEASON_PATTERN.matcher(userSeasonChoice).matches()) {
                break;
            } else {
                System.out.println("INVALID Input!");
                userSeasonChoice = ConsoleController.getUserInput("Please enter the season (Ex: 2020 OR 2021) appropriately!");
            }
        }

        ConsoleController.loadData(userSeasonChoice);
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        List<FootballMatch> allMatches = PremierLeagueManager.getAllMatches();
        //**to display clubs sorted by points**//
        allClubs.sort(Collections.reverseOrder());
        primaryStage.getIcons().add(new Image("file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-Lion-transparent.png"));

        displayMenu(primaryStage, allClubs, allMatches, userSeasonChoice);
    }

    /**
     * The main menu
     * @param window - primary stage
     * @param allClubs - list of all clubs
     * @param allMatches - list of all matches
     * @param userSeasonChoice - season of tournament
     * @throws IllegalAccessException - thrown in Color input of the addClub() method
     * @throws InterruptedException - thrown during Thread.sleep
     * @throws ClassNotFoundException - thrown in the get() method of Field of the addClub() method
     */
    public static void displayMenu(Stage window, List<FootballClub> allClubs, List<FootballMatch> allMatches, String userSeasonChoice) throws
            IllegalAccessException, InterruptedException, ClassNotFoundException {
        ConsoleController.printDisplay();
        String userChoice = ConsoleController.getUserInput("Please choose an option");

        /*match input choice with respective method calls, and recall the menu for an endless recursive loop*/
        switch (userChoice) {
            case "a":
                ConsoleController.addClub();
                displayMenu(window, allClubs, allMatches, userSeasonChoice);
                break;
            case "d":
                ConsoleController.deleteClub();
                displayMenu(window, allClubs, allMatches, userSeasonChoice);
                break;
            case "p":
                ConsoleController.addPlayedMatch(userSeasonChoice);
                displayMenu(window, allClubs, allMatches, userSeasonChoice);
                break;
            case "z":
                ConsoleController.displayPointsTable();
                displayMenu(window, allClubs, allMatches, userSeasonChoice);
                break;
            case "c":
                ConsoleController.displayMatchResults();
                displayMenu(window, allClubs, allMatches, userSeasonChoice);
                break;
            case "x":
                ConsoleController.displaySelectedClub();
                displayMenu(window, allClubs, allMatches, userSeasonChoice);
                break;
            case "s":
                ConsoleController.displaySelectedMatch();
                displayMenu(window, allClubs, allMatches, userSeasonChoice);
                break;
            case "g":
                FrontendUtils.displayPointsTableWindow(window, allClubs, allMatches, userSeasonChoice);
                break;
            case "q":
                ConsoleController.saveData(userSeasonChoice);
                break;
            default:
                System.out.println("Please enter a VALID option");
                displayMenu(window, allClubs, allMatches, userSeasonChoice);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
