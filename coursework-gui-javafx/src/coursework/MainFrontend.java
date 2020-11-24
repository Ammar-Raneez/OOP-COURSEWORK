package coursework;

/*
 * MainFrontend
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.regex.Pattern;

/**
 * MainFrontend class, the main GUI runner class
 * @version 1.x November 22th 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class MainFrontend extends Application {
    private static final Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    @Override
    public void start(Stage primaryStage) {
        ConsoleApplication.loadData();
        GuiElements guiElements = new GuiElements();
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        List<FootballMatch> allMatches = PremierLeagueManager.getAllMatches();
        allClubs.sort(Collections.reverseOrder());                                  //**to display clubs sorted by points**//
        primaryStage.getIcons().add(new Image("file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-lion.png"));
        displayPointsTableWindow(primaryStage, allClubs, allMatches, guiElements);
    }

    /**
     * Method that handles the display of the first scene aka the Points table
     * @param window - the stage
     * @param allClubs - list of clubs
     * @param allMatches - list of matches
     * @param guiElements - object of GuiElements class
     */
    public static void displayPointsTableWindow(Stage window, List<FootballClub> allClubs, List<FootballMatch> allMatches, GuiElements guiElements) {
        //*all gui elements*//
        ImageView eplLion = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-lion.png",
                0, 500, 220, 290);
        ImageView eplText = GuiElements.imageViewLay(
                        "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-text.png",
                200, 555, 100, 180);
        ImageView eplRef = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-ref.png",
                870, 525, 150, 180);
        Button goalSorter = GuiElements.button("GOAL SORT", 400, 590, "pointsTable__goalSorterBtn");
        Button winSorter = GuiElements.button("WIN SORT", 640, 590, "pointsTable__winSorterBtn");
        Button displayMatch = GuiElements.button("ALL MATCHES >>>", 510, 510, "pointsTable__displayMatchBtn");
        Button playMatch = GuiElements.button("P L A Y!", 1180, 580, "pointsTable__playMatchBtn");
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

        playMatch.setOnAction(event -> {
            if ((allMatches.size() == (allClubs.size() * (allClubs.size() - 1) / 2)) ||
                    (allMatches.size() > (allClubs.size() * (allClubs.size() - 1) / 2))) {
                GuiElements.errorAlert("All Possible Matches have Already been Played!");
                return;
            }

            ConsoleApplication.addPlayedMatch();
            List<FootballMatch> updatedMatches = PremierLeagueManager.getAllMatches();
            for (int i=0; i<updatedMatches.size(); i++) {
                allMatches.set(i, updatedMatches.get(i));
            }
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
            event.consume();
            closeScenes(window);
        });

        AnchorPane anchorPane = GuiElements.anchor("-fx-background-color: #37003d; -fx-border-color: #f00;");
        StackPane stackPane = GuiElements.stackPane(1366, 500);
        stackPane.getChildren().add(tableView);
        anchorPane.getChildren().addAll(stackPane, eplLion, eplText, goalSorter, winSorter, playMatch, displayMatch, eplRef);

        Scene pointsTableScene = guiElements.scene(anchorPane, 1366, 700, "style.css");
        //*on click of this button, change scene to display all matches*//
        displayMatch.setOnAction(event -> {
            Scene displayMatchScene = displayAllMatches(window, allMatches, guiElements, pointsTableScene);
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
     * @param guiElements - a GuiElements object
     * @param pointsTableScene - Points Table scene passed for button to switch scenes
     * @return scene - the gui scene is returned and used in the points table scene
     */
    public static Scene displayAllMatches(Stage window, List<FootballMatch> allMatches, GuiElements guiElements, Scene pointsTableScene) {
        Collections.sort(allMatches);              //**to display all matches ascending order of date**//
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
            allMatchDisplayAndFilter(vBoxContainer, match, guiElements);
        }

        ScrollPane scrollPaneContainer = GuiElements.scrollPane(700, 420, 330, 250,
                "-fx-background: #222; -fx-border-color: #f00;");
        scrollPaneContainer.setContent(vBoxContainer);

        //*on click of this button, displays the matches with date specified*//
        searchDateBtn.setOnAction(event -> {
            scrollPaneContainer.setContent(vBoxContainer);
            String userInput = dateInputField.getText();
            if (DATE_PATTERN.matcher(userInput).matches()) {    //*checking input against a regex*//
                vBoxContainer.getChildren().clear();
                boolean hasMatch = false;
                //*the VBox is emptied and only the matches with date specified are added*//
                for (FootballMatch footballMatch: allMatches) {
                    if (String.valueOf(footballMatch.getMatchDate()).equals(userInput)) {
                        allMatchDisplayAndFilter(vBoxContainer, footballMatch, guiElements);
                        hasMatch = true;
                    }
                }
                if (!hasMatch) {
                    //*display a label is there are not any matches with specified date*//
                    HBox noMatchHBox = GuiElements.hBox("");
                    Label noMatchLabel = GuiElements.matchViewLabels("\t\t" + "   " +  "⚠\n\t" + "  " + "NO MATCHES\n" + "\t" +
                            "\tHAVE\n\t" + "  " + "BEEN PLAYED\n\t\t" + "   " + "⚠", "allMatches__noMatchesLbl");
                    noMatchHBox.getChildren().add(noMatchLabel);
                    scrollPaneContainer.setContent(noMatchHBox);
                }
            } else {
                GuiElements.errorAlert("Please specify a VALID date with format yyyy-mm-dd!");
            }
        });

        //**on click of this button, reset the search and display all matches//
        resetSearchBtn.setOnAction(event -> {
            scrollPaneContainer.setContent(vBoxContainer);
            dateInputField.setText("");
            vBoxContainer.getChildren().clear();
            for (FootballMatch match : allMatches) {
                allMatchDisplayAndFilter(vBoxContainer, match, guiElements);
            }
        });

        window.setOnCloseRequest(event -> {
            event.consume();
            closeScenes(window);
        });

        AnchorPane anchorPane = GuiElements.anchor("-fx-background-color: #222; -fx-border-color: #f00;");
        anchorPane.getChildren().addAll(eplLion2, eplTrophy, eplBall, eplBoot, scrollPaneContainer, pointsTableBtn, dateInputField,
                searchDateBtn, resetSearchBtn);

        return guiElements.scene(anchorPane, 1366, 700, "style.css");
    }
    /**
     * Private helper method that is used to display the list of matches
     * @param vBoxContainer - main container
     * @param footballMatch - footballMatch to add into the container
     */
    private static void allMatchDisplayAndFilter(VBox vBoxContainer, FootballMatch footballMatch, GuiElements guiElements) {
        VBox vBoxFirstTeam = GuiElements.vBox(300);
        HBox firstTeamNameContainer = GuiElements.hBox("");
        HBox firstTeamGoalContainer = GuiElements.hBox("");
        Label firstTeamName = GuiElements.matchViewLabels(footballMatch.getFirstTeam().getClubName().toUpperCase(), "allMatches__firstTeamNameLbl");
        Label firstTeamGoals = GuiElements.matchViewLabels(String.valueOf(footballMatch.getFirstTeamSingleMatchStats().getGoals()),
                "allMatches__firstTeamGoalsLbl");
        firstTeamNameContainer.getChildren().add(firstTeamName);
        firstTeamGoalContainer.getChildren().add(firstTeamGoals);
        vBoxFirstTeam.getChildren().addAll(firstTeamNameContainer, firstTeamGoalContainer);

        VBox vBoxDate = GuiElements.vBox(100);
        Label labelDate = GuiElements.matchViewLabels(String.valueOf(footballMatch.getMatchDate()), "allMatches__matchDateLbl");
        Label labelVS = GuiElements.matchViewLabels("VS", "allMatches__vsLbl");
        vBoxDate.getChildren().addAll(labelDate, labelVS);

        VBox vBoxSecondTeam = GuiElements.vBox(300);
        HBox secondTeamNameContainer = GuiElements.hBox("");
        HBox secondTeamGoalsContainer = GuiElements.hBox("");
        Label secondTeamName = GuiElements.matchViewLabels(footballMatch.getSecondTeam().getClubName().toUpperCase(), "allMatches__secondTeamNameLbl");
        Label secondTeamGoals = GuiElements.matchViewLabels(String.valueOf(footballMatch.getSecondTeamSingleMatchStats().getGoals()),
                "allMatches__secondTeamGoalsLbl");
        secondTeamNameContainer.getChildren().add(secondTeamName);
        secondTeamGoalsContainer.getChildren().add(secondTeamGoals);
        vBoxSecondTeam.getChildren().addAll(secondTeamNameContainer, secondTeamGoalsContainer);

        HBox hBoxEachRow = GuiElements.hBox("allMatches__eachRowHBox");
        hBoxEachRow.setOnMouseClicked(event -> specificMatchDisplay(footballMatch, guiElements));
        hBoxEachRow.getChildren().addAll(vBoxFirstTeam, vBoxDate, vBoxSecondTeam);
        vBoxContainer.getChildren().addAll(hBoxEachRow);
        vBoxContainer.setCursor(Cursor.HAND);
    }
    /**
     * private helper method of allMatchDisplayAndFilter() that is used to display a selected match statistic
     * @param footballMatch - chosen footballMatch
     * @param guiElements - object of GuiElements class
     */
    private static void specificMatchDisplay(FootballMatch footballMatch, GuiElements guiElements) {
        /*upper hBox (the logo)*/
        HBox hBoxUpperContainer = GuiElements.hBox("match__upperContainer");
        ImageView eplLionText = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-LionText-transparent.png",
                500, 20, 220, 700);
        hBoxUpperContainer.getChildren().add(eplLionText);
        /*end of upper hBox*/

        /*main middle hBox (all club content)*/
        VBox vBoxFirstTeam = GuiElements.vBox(300);
        VBox vBoxMiddleBar = GuiElements.vBox(300);
        VBox vBoxSecondTeam = GuiElements.vBox(300);

        HBox firstTeamName = specificMatchColumn(footballMatch.getFirstTeam().getClubName().toUpperCase(), "match__firstTeamNameLbl");
        vBoxFirstTeam.getChildren().add(firstTeamName);
        List<String> firstTeamDetailsContent = getClubDetailsContent(footballMatch.getFirstTeamSingleMatchStats());
        for (String eachRow : firstTeamDetailsContent) {
            HBox row = specificMatchColumn(eachRow, "match__firstTeamDetailsLbl");
            vBoxFirstTeam.getChildren().add(row);
        }

        List<String> middleBarEachRowContent = new ArrayList<>(Arrays.asList(
                "TEAM STATS", "Goals", "Shots", "Shots on target", "Possession", "Passes", "Pass accuracy", "Fouls", "Yellow cards", "Red cards",
                "Offsides", "Corners"
        ));
        for (String eachRow : middleBarEachRowContent) {
            HBox row = specificMatchColumn(eachRow, "match__middleBarLbl");
            vBoxMiddleBar.getChildren().add(row);
        }

        HBox secondTeamName = specificMatchColumn(footballMatch.getSecondTeam().getClubName().toUpperCase(), "match__secondTeamNameLbl");
        vBoxSecondTeam.getChildren().add(secondTeamName);
        List<String> secondTeamDetailsContent = getClubDetailsContent(footballMatch.getSecondTeamSingleMatchStats());
        for (String eachRow : secondTeamDetailsContent) {
            HBox row = specificMatchColumn(eachRow, "match__secondTeamDetailsLbl");
            vBoxSecondTeam.getChildren().add(row);
        }

        HBox hBoxMiddleContainer = GuiElements.hBox("");
        hBoxMiddleContainer.getChildren().addAll(vBoxFirstTeam, vBoxMiddleBar, vBoxSecondTeam);
        /*end of main middle hBox*/

        /*Bottom information hBox*/
        HBox hBoxLowerContainer = GuiElements.hBox("match__lowerContainer");
        String labelText =
                footballMatch.getFirstTeamSingleMatchStats().getGoals() > footballMatch.getSecondTeamSingleMatchStats().getGoals() ?
                        "\n" + footballMatch.getFirstTeam().getClubName() + " beat " + footballMatch.getSecondTeam().getClubName() + ", scoring " +
                                footballMatch.getFirstTeamSingleMatchStats().getGoals() + " goals with a possession of " + footballMatch.getFirstTeamSingleMatchStats().getPossession() + "%"
                        :
                        footballMatch.getFirstTeamSingleMatchStats().getGoals() < footballMatch.getSecondTeamSingleMatchStats().getGoals() ?
                                "\n" + footballMatch.getSecondTeam().getClubName() + " beat " + footballMatch.getFirstTeam().getClubName() + ", scoring " +
                                        footballMatch.getSecondTeamSingleMatchStats().getGoals() + " goals with a possession of " + footballMatch.getSecondTeamSingleMatchStats().getPossession() + "%"
                                :
                                "\n" + "The match ended in a draw, with both " + footballMatch.getFirstTeam().getClubName() + " and " + footballMatch.getSecondTeam().getClubName() +
                                        "scoring " + footballMatch.getFirstTeamSingleMatchStats().getGoals() + " goals each.";

        Text infoText = new Text(labelText);
        infoText.setId("match__infoText");
        hBoxLowerContainer.getChildren().add(infoText);
        /*end of bottom information hBox*/

        VBox hBoxMainContainer = GuiElements.vBox(0);
        hBoxMainContainer.getChildren().addAll(hBoxUpperContainer, hBoxMiddleContainer, hBoxLowerContainer);
        AnchorPane anchorPane = GuiElements.anchor("-fx-background-color: #222; -fx-border-color: red");
        anchorPane.getChildren().addAll(hBoxMainContainer);
        Scene scene = guiElements.scene(anchorPane, 900, 700, "style.css");
        Stage innerWindow = GuiElements.stage(scene, 220, 0);
        innerWindow.showAndWait();
    }
    /**
     * private helper method of specificMatchDisplay() that is used to display each column
     * @param labelValue - what the label must display
     * @param id - to be targeted by css
     * @return - an hBox that holds each row of each column
     */
    private static HBox specificMatchColumn(String labelValue, String id) {
        HBox hBoxContainer = GuiElements.hBox("");
        Label eachLabel = GuiElements.matchViewLabels(labelValue, id);
        hBoxContainer.getChildren().add(eachLabel);
        return hBoxContainer;
    }
    /**
     * private helper method of specificMatchDisplay() that is used to return a list of all the club detail values
     * @param singleMatchFootballClubStatistic - a football clubs single match statistics
     * @return - list of statistics
     */
    private static List<String> getClubDetailsContent(SingleMatchFootballClubStatistic singleMatchFootballClubStatistic) {
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
     * private method that handles closing of a window
     * @param window - stage
     */
    private static void closeScenes(Stage window) {
        Alert closeAlert = GuiElements.closeWindowCommon();
        closeAlert.initOwner(window);
        closeAlert.showAndWait();
        if (closeAlert.getResult() == ButtonType.YES) {
            window.close();
            ConsoleApplication.saveData();
//            ConsoleApplication.printDisplay();
        } else {
            closeAlert.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
