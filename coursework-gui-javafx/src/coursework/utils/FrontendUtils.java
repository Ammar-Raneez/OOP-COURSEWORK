package coursework.utils;

/*
 * FrontendUtils
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import coursework.controllers.ConsoleController;
import coursework.controllers.FrontendController;
import coursework.models.FootballClub;
import coursework.models.FootballMatch;
import coursework.models.SingleMatchFootballClubStatistic;
import coursework.services.PremierLeagueManager;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * FrontendUtils class, holds all the GUI content
 * @version 1.x November 22th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public abstract class FrontendUtils {
    //*Date regex*//
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    /**
     * Method that handles the display of the first scene aka the Points table
     * @param window - the stage
     * @param allClubs - list of clubs
     * @param allMatches - list of matches
     * @param userSeasonChoice - season of tournament
     */
    public static void displayPointsTableWindow(Stage window, List<FootballClub> allClubs, List<FootballMatch> allMatches, String userSeasonChoice) {
        //*all gui elements*//
        ImageView eplLion = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-lion.png",
                0, 500, 220, 290);
        ImageView eplText = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-text.png",
                200, 555, 100, 180);
        ImageView eplPlayer = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-player.png",
                850, 500, 210, 180);
        ImageView eplPlayerTwo = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-player2.png",
                1000, 500, 210, 180);
        Button goalSorter = GuiElements.button("GOAL SORT", 400, 590, "pointsTable__goalSorterBtn");
        Button winSorter = GuiElements.button("WIN SORT", 640, 590, "pointsTable__winSorterBtn");
        Button displayMatch = GuiElements.button("ALL MATCHES >>>", 510, 510, "pointsTable__displayMatchBtn");
        Button playMatch = GuiElements.button("P L A Y !", 1180, 580, "pointsTable__playMatchBtn");
        TableView<FootballClub> tableView = new TableView<>();
        List<TableColumn<FootballClub, String>> allColumns = GuiElements.generatePointsTableColumns(tableView);
        //*end gui elements*//

        //*on click of this button, sort the list of clubs by goals for*//
        goalSorter.setOnAction(event -> {
            allClubs.sort(new GoalsForComparator().reversed());
            for (int i=0; i<tableView.getItems().size(); i++) {
                tableView.getItems().set(i, allClubs.get(i));
            }
        });

        //**on click of this button, sort the list of clubs by wins**//
        winSorter.setOnAction(event -> {
            allClubs.sort(new WinComparator().reversed());
            for (int i=0; i<tableView.getItems().size(); i++) {
                tableView.getItems().set(i, allClubs.get(i));
            }
        });

        //*some fun*//
        playMatch.setOnMouseEntered(event -> {
            String musicFile = "sound.mp3";
            Media sound = new Media(new File(musicFile).toURI().toString());
            MediaPlayer mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.play();
        });

        //*on click of this button, a match is played*//
        playMatch.setOnAction(event -> {
            //*this if condition checks whether there are enough teams to play a match in the first place*//
            if (allClubs.size() < 2) {
                GuiElements.errorAlert("[ERROR] ==> There isn't enough teams to play a match!", window);
                return;
            }
            //*max number of matches playable*//
            if ((allMatches.size() == (allClubs.size() * (allClubs.size() - 1) / 2)) ||
                    (allMatches.size() > (allClubs.size() * (allClubs.size() - 1) / 2))) {
                GuiElements.errorAlert("All Possible Matches have Already been Played!", window);
                return;
            }

            try {
                ConsoleController.addPlayedMatch(userSeasonChoice);
            } catch (InterruptedException e) {
                System.out.println("[ERROR] ==> " + e.getMessage());
            }
            //*getting the list of updated matches from the console-side*//
            List<FootballMatch> updatedMatches = PremierLeagueManager.getAllMatches();
            //*use that to update the matches in the frontend*//
            for (int i=0; i<updatedMatches.size(); i++) {
                allMatches.set(i, updatedMatches.get(i));
            }
            //*since club references are passed, upon match list update, individual club details are updated too*//
            //*therefore the new updated club list can be used to update the frontend table*//
            for (int i=0; i<allClubs.size(); i++) {
                tableView.getItems().set(i, allClubs.get(i));
            }
        });

        //*using the table helper methods to populate the TableView*//
        for (TableColumn<FootballClub, String> eachColumn : allColumns) {
            tableView.getColumns().add(eachColumn);
        }
        for (FootballClub footballClub : allClubs) {
            tableView.getItems().add(footballClub);
        }

        window.setOnCloseRequest(event -> {
            //*call helper closeScenes method to handle the closing of the window*//
            event.consume();
            try {
                closeScenes(window, allClubs, allMatches, userSeasonChoice);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        AnchorPane anchorPane = GuiElements.anchor("-fx-background-color: #222;");
        StackPane stackPane = GuiElements.stackPane(1366, 500);
        stackPane.getChildren().add(tableView);
        ScrollPane scrollPane = GuiElements.scrollPane(1366, 500, 0, 0, "-fx-background-color: #222; -fx-border-color: #222");
        //*add the table into a stackPane to enable scrollable functionality*//
        //*add the stackPane to a scrollPane to provide the scrollable functionality*//
        scrollPane.setContent(stackPane);
        anchorPane.getChildren().addAll(scrollPane, eplLion, eplText, goalSorter, winSorter, playMatch, displayMatch, eplPlayer, eplPlayerTwo);

        Scene pointsTableScene = GuiElements.scene(anchorPane, 1366, 700);
        //*on click of this button, change scene to display all matches*//
        displayMatch.setOnAction(event -> {
            Scene displayMatchScene = displayAllMatches(window, allClubs, allMatches, pointsTableScene, userSeasonChoice);
            window.setScene(displayMatchScene);
        });

        window.setScene(pointsTableScene);
        window.setTitle("PREMIER LEAGUE MANAGER");
        window.show();
    }


    /**
     * Method that handles the display of all matches
     * @param window - the stage
     * @param allMatches = list of matches
     * @param pointsTableScene - Points Table scene passed for button to switch scenes
     * @param userSeasonChoice - season of tournament
     * @return scene - the gui scene is returned and used in the points table scene
     */
    public static Scene displayAllMatches(Stage window, List<FootballClub> allClubs, List<FootballMatch> allMatches, Scene pointsTableScene,
                                          String userSeasonChoice) {
        //**to display all matches ascending order of date**//
        Collections.sort(allMatches);

        //**all gui elements**//
        ImageView eplLion2 = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-lion2.png",
                500, 20, 220, 350);
        ImageView eplTrophy = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-trophy.png",
                0, 250, 350, 320);
        ImageView eplBall = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-ball.png",
                1100, 250, 200, 200);
        ImageView eplBoot = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-boot.png",
                1100, 450, 200, 200);
        TextField dateInputField = GuiElements.textField("yyyy-mm-dd", 200, 40, 840, 150, "allMatches__dateInputTF");
        Button searchDateBtn = GuiElements.button("Search", 1045, 150, "allMatches__searchDateBtn");
        Button resetSearchBtn = GuiElements.button("Reset", 1176, 150, "allMatches__searchResetBtn");
        Button pointsTableBtn = GuiElements.button("<<< POINTS TABLE", 40, 630, "allMatches__pointsTableBtn");
        pointsTableBtn.setOnAction(event -> window.setScene(pointsTableScene));
        VBox vBoxContainer = new VBox();
        //**end all gui elements**//

        for (FootballMatch match : allMatches) {
            //*use the helper method to display all the matches*//
            allMatchDisplayAndFilter(vBoxContainer, match);
        }

        ScrollPane scrollPaneContainer = GuiElements.scrollPane(700, 420, 330, 250,
                "-fx-background: #222; -fx-border-color: #f00;");
        scrollPaneContainer.setContent(vBoxContainer);

        //*on click of this button, it displays the matches with date specified*//
        searchDateBtn.setOnAction(event -> {
            scrollPaneContainer.setContent(vBoxContainer);
            String userInput = dateInputField.getText();
            //*checking input against a regex, therefore only the specific format is accepted*//
            if (DATE_PATTERN.matcher(userInput).matches()) {
                vBoxContainer.getChildren().clear();
                boolean hasMatch = false;
                //*the VBox is emptied and only the matches with date specified are added*//
                for (FootballMatch footballMatch: allMatches) {
                    if (String.valueOf(footballMatch.getMatchDate()).equals(userInput)) {
                        //*use the helper method to display the matches whose date is equal to specified*//
                        allMatchDisplayAndFilter(vBoxContainer, footballMatch);
                        hasMatch = true;
                    }
                }
                if (!hasMatch) {
                    //*display a label is there are not any matches played on the specified date*//
                    HBox noMatchHBox = GuiElements.hBox("");
                    Label noMatchLabel = GuiElements.label("\t\t" + "   " +  "⚠\n\t" + "  " + "NO MATCHES\n" + "\t" +
                            "\tHAVE\n\t" + "  " + "BEEN PLAYED\n\t\t" + "   " + "⚠", "allMatches__noMatchesLbl");
                    noMatchHBox.getChildren().add(noMatchLabel);
                    scrollPaneContainer.setContent(noMatchHBox);
                }
            } else {
                GuiElements.errorAlert("Please specify a VALID date with format yyyy-mm-dd!", window);
            }
        });

        //*on click of this button, reset the search and display back all matches*//
        resetSearchBtn.setOnAction(event -> {
            scrollPaneContainer.setContent(vBoxContainer);
            dateInputField.setText("");
            vBoxContainer.getChildren().clear();
            for (FootballMatch match : allMatches) {
                allMatchDisplayAndFilter(vBoxContainer, match);
            }
        });

        window.setOnCloseRequest(event -> {
            event.consume();
            try {
                closeScenes(window, allClubs, allMatches, userSeasonChoice);
            } catch (Exception e) {
                System.out.println("[ERROR] ==> something went wrong " + e.getMessage());
            }
        });

        AnchorPane anchorPane = GuiElements.anchor("-fx-background-color: #222; -fx-border-color: #f00;");
        anchorPane.getChildren().addAll(eplLion2, eplTrophy, eplBall, eplBoot, scrollPaneContainer, pointsTableBtn, dateInputField,
                searchDateBtn, resetSearchBtn);

        return GuiElements.scene(anchorPane, 1366, 700);
    }
    /**
     * Private helper method of displayAllMatches() that is used to display the list of matches
     * @param vBoxContainer - main container that will hold all the matches
     * @param footballMatch - footballMatch to add into the main container
     */
    private static void allMatchDisplayAndFilter(VBox vBoxContainer, FootballMatch footballMatch) {
        //*the idea here is to use VBoxes and HBoxes as div tags, to achieve CSS FlexBox functionality*//
        //*In order to align the content perfectly*//
        //*Each match will be a HBox inside the main VBox container (the container that will hold all the matches*//
        //*Each match will then hold three VBoxes aligned next to each other, one for each team who have participated the specific match*//
        //*And the other for the middle bar (the date container)*//
        VBox vBoxFirstTeam = allMatchesDisplayAndFilterEachTeam(footballMatch.getFirstTeam(), footballMatch.getFirstTeamSingleMatchStats(),
                "allMatches__firstTeamNameLbl", "allMatches__firstTeamGoalsLbl");
        VBox vBoxSecondTeam = allMatchesDisplayAndFilterEachTeam(footballMatch.getSecondTeam(), footballMatch.getSecondTeamSingleMatchStats(),
                "allMatches__secondTeamNameLbl", "allMatches__secondTeamGoalsLbl");

        VBox vBoxDate = GuiElements.vBox(100);
        Label labelDate = GuiElements.label(String.valueOf(footballMatch.getMatchDate()), "allMatches__matchDateLbl");
        Label labelVS = GuiElements.label("VS", "allMatches__vsLbl");
        vBoxDate.getChildren().addAll(labelDate, labelVS);

        //*The HBox that will hold each match*//
        HBox hBoxEachRow = GuiElements.hBox("allMatches__eachRowHBox");
        //*calls another scene to display the specific match statistics*//
        hBoxEachRow.setOnMouseClicked(event -> specificMatchDisplay(footballMatch));
        hBoxEachRow.getChildren().addAll(vBoxFirstTeam, vBoxDate, vBoxSecondTeam);
        vBoxContainer.setCursor(Cursor.HAND);
        vBoxContainer.getChildren().addAll(hBoxEachRow);
    }
    /**
     * private helper method of allMatchDisplayAndFilter() that is used to display each team participating in a match
     * @param footballClub - one of the clubs that participated in a match
     * @param teamMatchStat - the stat the specific club obtained for the match
     * @param nameId - an id for the name label, to be styled through CSS
     * @param  goalId - an id for the goal label, to be styled through CSS
     * @return - the VBox of each team holding the necessary details
     */
    private static VBox allMatchesDisplayAndFilterEachTeam(FootballClub footballClub, SingleMatchFootballClubStatistic teamMatchStat, String nameId,
                                                           String goalId) {
        VBox vBoxSpecificTeam = GuiElements.vBox(300);
        HBox teamNameContainer = GuiElements.hBox("");
        HBox teamGoalContainer = GuiElements.hBox("");
        Label teamName = GuiElements.label(footballClub.getClubName().toUpperCase(), nameId);
        Label teamGoals = GuiElements.label(String.valueOf(teamMatchStat.getGoals()), goalId);
        teamNameContainer.getChildren().add(teamName);
        teamGoalContainer.getChildren().add(teamGoals);
        vBoxSpecificTeam.getChildren().addAll(teamNameContainer, teamGoalContainer);
        return vBoxSpecificTeam;
    }
    /**
     * private helper method of allMatchDisplayAndFilter() that is used to display a selected match statistic
     * @param footballMatch - chosen footballMatch
     */
    private static void specificMatchDisplay(FootballMatch footballMatch) {
        /*upper hBox (the logo)*/
        HBox hBoxUpperContainer = GuiElements.hBox("match__upperContainer");
        ImageView eplLionText = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-LionText-transparent.png",
                500, 20, 220, 700);
        hBoxUpperContainer.getChildren().add(eplLionText);
        /*end of upper hBox*/

        /*main middle hBox (all match statistic content)*/
        VBox vBoxFirstTeam = GuiElements.vBox(300);
        VBox vBoxMiddleBar = GuiElements.vBox(300);
        VBox vBoxSecondTeam = GuiElements.vBox(300);

        //*column that will hold and display the stats of the first team that has participated in the specific match*//
        HBox firstTeamName = specificMatchTeamStat(footballMatch.getFirstTeam().getClubName().toUpperCase(), "match__firstTeamNameLbl");
        vBoxFirstTeam.getChildren().add(firstTeamName);
        List<String> firstTeamStats = getSpecificMatchTeamStats(footballMatch.getFirstTeamSingleMatchStats());
        for (String eachRow : firstTeamStats) {
            HBox row = specificMatchTeamStat(eachRow, "match__firstTeamDetailsLbl");
            vBoxFirstTeam.getChildren().add(row);
        }

        //*the middle bar that will display what each stat is*//
        List<String> middleBarEachRowStat = new ArrayList<>(Arrays.asList(
                "TEAM STATS", "Goals", "Shots", "Shots on target", "Possession", "Passes", "Pass accuracy", "Fouls", "Yellow cards", "Red cards",
                "Offsides", "Corners"
        ));
        for (String eachRow : middleBarEachRowStat) {
            HBox row = specificMatchTeamStat(eachRow, "match__middleBarLbl");
            vBoxMiddleBar.getChildren().add(row);
        }

        //*column that will hold and display the stats of the second team that has participated in the specific match*//
        HBox secondTeamName = specificMatchTeamStat(footballMatch.getSecondTeam().getClubName().toUpperCase(), "match__secondTeamNameLbl");
        vBoxSecondTeam.getChildren().add(secondTeamName);
        List<String> secondTeamDetailsContent = getSpecificMatchTeamStats(footballMatch.getSecondTeamSingleMatchStats());
        for (String eachRow : secondTeamDetailsContent) {
            HBox row = specificMatchTeamStat(eachRow, "match__secondTeamDetailsLbl");
            vBoxSecondTeam.getChildren().add(row);
        }

        HBox hBoxMiddleContainer = GuiElements.hBox("");
        hBoxMiddleContainer.getChildren().addAll(vBoxFirstTeam, vBoxMiddleBar, vBoxSecondTeam);
        /*end of main middle hBox*/

        /*Bottom information hBox*/
        HBox hBoxLowerContainer = GuiElements.hBox("match__lowerContainer");
        String labelText =
            //*format a text based on who won, or if it was a draw, based on the goals scored*//
            footballMatch.getFirstTeamSingleMatchStats().getGoals() > footballMatch.getSecondTeamSingleMatchStats().getGoals() ?
                "\n" + footballMatch.getFirstTeam().getClubName() + " beat " + footballMatch.getSecondTeam().getClubName() + ", scoring " +
                footballMatch.getFirstTeamSingleMatchStats().getGoals() + " goals with a possession of " +
                footballMatch.getFirstTeamSingleMatchStats().getPossession() + "%"
            :
                footballMatch.getFirstTeamSingleMatchStats().getGoals() < footballMatch.getSecondTeamSingleMatchStats().getGoals() ?
                    "\n" + footballMatch.getSecondTeam().getClubName() + " beat " + footballMatch.getFirstTeam().getClubName() + ", scoring " +
                    footballMatch.getSecondTeamSingleMatchStats().getGoals() + " goals with a possession of " +
                    footballMatch.getSecondTeamSingleMatchStats().getPossession() + "%"
                :
                    "\n" + "The match ended in a draw, with both " + footballMatch.getFirstTeam().getClubName() + " and " +
                    footballMatch.getSecondTeam().getClubName() + "scoring " +
                    footballMatch.getFirstTeamSingleMatchStats().getGoals() + " goals each.";

        Text infoText = new Text(labelText);
        infoText.setId("match__infoText");
        hBoxLowerContainer.getChildren().add(infoText);
        /*end of bottom information hBox*/

        VBox hBoxMainContainer = GuiElements.vBox(0);
        hBoxMainContainer.getChildren().addAll(hBoxUpperContainer, hBoxMiddleContainer, hBoxLowerContainer);
        AnchorPane anchorPane = GuiElements.anchor("-fx-background-color: #222; -fx-border-color: red");
        anchorPane.getChildren().addAll(hBoxMainContainer);
        Scene scene = GuiElements.scene(anchorPane, 900, 700);
        Stage innerWindow = GuiElements.stage(scene, 220, 0);
        innerWindow.showAndWait();
    }
    /**
     * private helper method of specificMatchDisplay() that is used to return a list of all the club detail values
     * @param singleMatchFootballClubStatistic - a football clubs single match statistics
     * @return - list of statistics
     */
    private static List<String> getSpecificMatchTeamStats(SingleMatchFootballClubStatistic singleMatchFootballClubStatistic) {
        return new ArrayList<>(Arrays.asList(
                String.valueOf(singleMatchFootballClubStatistic.getGoals()), String.valueOf(singleMatchFootballClubStatistic.getShots()),
                String.valueOf(singleMatchFootballClubStatistic.getShotsOnTarget()), String.valueOf(singleMatchFootballClubStatistic.getPossession()),
                String.valueOf(singleMatchFootballClubStatistic.getPasses()), String.valueOf(singleMatchFootballClubStatistic.getPassAccuracy()),
                String.valueOf(singleMatchFootballClubStatistic.getFouls()), String.valueOf(singleMatchFootballClubStatistic.getYellowCards()),
                String.valueOf(singleMatchFootballClubStatistic.getRedCards()), String.valueOf(singleMatchFootballClubStatistic.getOffsides()),
                String.valueOf(singleMatchFootballClubStatistic.getCorners())
        ));
    }
    /**
     * private helper method of specificMatchDisplay() that is used to display each column
     * @param labelValue - what the label must display
     * @param id - to be targeted by css
     * @return - an hBox that holds each row of each column
     */
    private static HBox specificMatchTeamStat(String labelValue, String id) {
        HBox hBoxContainer = GuiElements.hBox("");
        Label eachLabel = GuiElements.label(labelValue, id);
        hBoxContainer.getChildren().add(eachLabel);
        return hBoxContainer;
    }

    /**
     * Private method that handles closing of the window
     * @param window - primary stage
     * @param allClubs  - list of all clubs
     * @param allMatches - list of all matches
     * @param userSeasonChoice - season of tournament
     * @throws IllegalAccessException - thrown in Color input of the addClub() method
     * @throws InterruptedException - thrown during Thread.sleep
     * @throws ClassNotFoundException - thrown in the get() method of Field of the addClub() method
     */
    private static void closeScenes(Stage window, List<FootballClub> allClubs, List<FootballMatch> allMatches, String userSeasonChoice)
            throws IllegalAccessException, InterruptedException, ClassNotFoundException {
        Alert closeAlert = GuiElements.confirmAlert();
        closeAlert.initOwner(window);
        closeAlert.showAndWait();
        if (closeAlert.getResult() == ButtonType.YES) {
            window.close();
            /*call the menu again upon window closure, to continue the program*/
            FrontendController.displayMenu(window, allClubs, allMatches, userSeasonChoice);
        } else {
            closeAlert.close();
        }
    }
}
