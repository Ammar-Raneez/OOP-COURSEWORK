package oop.cw.guifx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

public class MainFrontend extends Application {
    @Override
    public void start(Stage primaryStage) {
        ConsoleApplication.loadData();
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        List<FootballMatch> allMatches = PremierLeagueManager.getAllMatches();
        GuiElements guiElements = new GuiElements();
        allClubs.sort(Collections.reverseOrder());
        Collections.sort(allMatches);
        primaryStage.getIcons().add(new Image("file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-lion.png"));
        displayPointsTableWindow(primaryStage, allClubs, allMatches, guiElements);
    }

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
        Button goalSorter = GuiElements.button("GOAL SORT", 400, 590, "pointsTable__goalSorter");
        Button winSorter = GuiElements.button("WIN SORT", 640, 590, "pointsTable__winSorter");
        Button displayMatch = GuiElements.button("ALL MATCHES >>>", 510, 510, "pointsTable__displayMatch");
        Button playMatch = GuiElements.button("P L A Y!", 1180, 580, "pointsTable__playMatch");
        TableView<FootballClub> tableView = new TableView<>();
        List<TableColumn<FootballClub, String>> allColumns = GuiElements.generatePointsTableColumns(tableView);
        //*end gui elements*//

        goalSorter.setOnAction(event -> {
            allClubs.sort(new GoalsForComparator().reversed());
            for (int i=0; i<tableView.getItems().size(); i++) {
                tableView.getItems().set(i, allClubs.get(i));
            }
        });

        winSorter.setOnAction(event -> {
            allClubs.sort(new WinComparator().reversed());
            for (int i=0; i<tableView.getItems().size(); i++) {
                tableView.getItems().set(i, allClubs.get(i));
            }
        });


        for (TableColumn<FootballClub, String> eachColumn : allColumns) {
            tableView.getColumns().add(eachColumn);
        }

        for (FootballClub footballClub : allClubs) {
            tableView.getItems().add(footballClub);
        }

        AnchorPane anchorPane = GuiElements.anchor("-fx-background-color: #37003d; -fx-border-color: #f00;");
        StackPane stackPane = GuiElements.stackPane(1366, 500);
        stackPane.getChildren().add(tableView);
        anchorPane.getChildren().addAll(stackPane, eplLion, eplText, goalSorter, winSorter, playMatch, displayMatch, eplRef);

        Scene pointsTableScene = guiElements.scene(anchorPane, 1366, 700, "style.css");
        Scene displayMatchScene = displayAllMatches(window, allMatches, guiElements, pointsTableScene);
        displayMatch.setOnAction(event -> window.setScene(displayMatchScene));

        window.setScene(pointsTableScene);
        window.setTitle("PREMIER LEAGUE MANAGER");
        window.show();
    }

    public static Scene displayAllMatches(Stage window, List<FootballMatch> allMatches, GuiElements guiElements, Scene pointsTableScene) {
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
        TextField dateInputField = GuiElements.textField("yyyy-mm-dd", 200, 40, 840, 150, "allMatches__dateInput");
        Button searchDateBtn = GuiElements.button("Search", 1045, 150, "allMatches__searchDate");
        Button resetSearchBtn = GuiElements.button("Reset", 1176, 150, "allMatches__searchReset");
        Button pointsTableBtn = GuiElements.button("<<< POINTS TABLE", 40, 630, "allMatches__pointsTable");
        pointsTableBtn.setOnAction(event -> window.setScene(pointsTableScene));
        VBox vBoxContainer = new VBox();

        for (FootballMatch match : allMatches) {
            allMatchDisplayAndFilter(vBoxContainer, match);
        }

        ScrollPane scrollPaneContainer = GuiElements.scrollPane(700, 420, 330, 250,
                "-fx-background: #222; -fx-border-color: #f00;");
        scrollPaneContainer.setContent(vBoxContainer);

        searchDateBtn.setOnAction(event -> {
            vBoxContainer.getChildren().clear();
            String userInput = dateInputField.getText();
            for (FootballMatch footballMatch: allMatches) {
                if (String.valueOf(footballMatch.getMatchDate()).equals(userInput)) {
                    allMatchDisplayAndFilter(vBoxContainer, footballMatch);
                }
            }
        });

        resetSearchBtn.setOnAction(event -> {
            dateInputField.setText("");
            vBoxContainer.getChildren().clear();
            for (FootballMatch match : allMatches) {
                allMatchDisplayAndFilter(vBoxContainer, match);
            }
        });

        AnchorPane anchorPane = GuiElements.anchor("-fx-background-color: #222; -fx-border-color: #f00;");
        anchorPane.getChildren().addAll(eplLion2, eplTrophy, eplBall, eplBoot, scrollPaneContainer, pointsTableBtn, dateInputField,
                searchDateBtn, resetSearchBtn);

        return guiElements.scene(anchorPane, 1366, 700, "style.css");
    }

    private static void allMatchDisplayAndFilter(VBox vBoxContainer, FootballMatch footballMatch) {
        VBox vBoxFirstTeam = GuiElements.vBox(300);
        HBox firstTeamNameContainer = GuiElements.hBox("");
        HBox firstTeamGoalContainer = GuiElements.hBox("");
        Label firstTeamName = GuiElements.matchViewLabels(footballMatch.getFirstTeam().getClubName().toUpperCase(), "allMatches__firstTeamName");
        Label firstTeamGoals = GuiElements.matchViewLabels(String.valueOf(footballMatch.getFirstTeamSingleMatchStats().getGoals()),
                "allMatches__firstTeamGoals");
        firstTeamNameContainer.getChildren().add(firstTeamName);
        firstTeamGoalContainer.getChildren().add(firstTeamGoals);
        vBoxFirstTeam.getChildren().addAll(firstTeamNameContainer, firstTeamGoalContainer);

        VBox vBoxDate = GuiElements.vBox(100);
        Label labelDate = GuiElements.matchViewLabels(String.valueOf(footballMatch.getMatchDate()), "allMatches__matchDate");
        Label labelVS = GuiElements.matchViewLabels("VS", "allMatches__vs");
        vBoxDate.getChildren().addAll(labelDate, labelVS);

        VBox vBoxSecondTeam = GuiElements.vBox(300);
        HBox secondTeamNameContainer = GuiElements.hBox("");
        HBox secondTeamGoalsContainer = GuiElements.hBox("");
        Label secondTeamName = GuiElements.matchViewLabels(footballMatch.getSecondTeam().getClubName().toUpperCase(), "allMatches__secondTeamName");
        Label secondTeamGoals = GuiElements.matchViewLabels(String.valueOf(footballMatch.getSecondTeamSingleMatchStats().getGoals()),
                "allMatches__secondTeamGoals");
        secondTeamNameContainer.getChildren().add(secondTeamName);
        secondTeamGoalsContainer.getChildren().add(secondTeamGoals);
        vBoxSecondTeam.getChildren().addAll(secondTeamNameContainer, secondTeamGoalsContainer);

        HBox hBoxEachRow = GuiElements.hBox("allMatches__eachRow");
        hBoxEachRow.getChildren().addAll(vBoxFirstTeam, vBoxDate, vBoxSecondTeam);

        vBoxContainer.getChildren().addAll(hBoxEachRow);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
