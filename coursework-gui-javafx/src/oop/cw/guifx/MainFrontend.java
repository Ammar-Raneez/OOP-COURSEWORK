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
        Button winSorter = GuiElements.button("WIN SORT", 580, 590, "pointsTable__winSorter");
        Button displayMatch = GuiElements.button("DISPLAY ALL MATCHES", 490, 510, "pointsTable__displayMatch");
        Scene displayMatchScene = displayAllMatches(allMatches, guiElements);
        displayMatch.setOnAction(event -> window.setScene(displayMatchScene));
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

        AnchorPane anchorPane = GuiElements.anchor();
        StackPane stackPane = GuiElements.stackPane(1366, 500);
        stackPane.getChildren().add(tableView);
        anchorPane.getChildren().addAll(stackPane, eplLion, eplText, goalSorter, winSorter, playMatch, displayMatch, eplRef);
        Scene scene = guiElements.scene(anchorPane, 1366, 700, "style.css");
        window.setScene(scene);
        window.setTitle("PREMIER LEAGUE MANAGER");
        window.show();
    }

    public static Scene displayAllMatches(List<FootballMatch> allMatches, GuiElements guiElements) {
        ImageView eplLion2 = GuiElements.imageViewLay(
                "file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-lion2.png",
                500, 20, 220, 350);


        VBox vBoxContainer = new VBox();
        for (FootballMatch match : allMatches) {
            VBox vBoxFirstTeam = GuiElements.vBox(300);
            HBox firstTeamNameContainer = GuiElements.hBox();
            HBox firstTeamGoalContainer = GuiElements.hBox();
            Label firstTeamName = GuiElements.matchViewLabels(match.getFirstTeam().getClubName(), "allMatches__firstTeamName");
            Label firstTeamGoals = GuiElements.matchViewLabels(String.valueOf(match.getFirstTeamSingleMatchStats().getGoals()),
                    "allMatches__firstTeamGoals");
            firstTeamNameContainer.getChildren().add(firstTeamName);
            firstTeamGoalContainer.getChildren().add(firstTeamGoals);
            vBoxFirstTeam.getChildren().addAll(firstTeamNameContainer, firstTeamGoalContainer);

            VBox vBoxDate = GuiElements.vBox(100);
            Label labelVS = GuiElements.matchViewLabels("VS", "allMatches__vs");
            Label labelDate = GuiElements.matchViewLabels(String.valueOf(match.getMatchDate()), "allMatches__matchDate");
            vBoxDate.getChildren().addAll(labelVS, labelDate);

            VBox vBoxSecondTeam = GuiElements.vBox(300);
            HBox secondTeamNameContainer = GuiElements.hBox();
            HBox secondTeamGoalsContainer = GuiElements.hBox();
            Label secondTeamName = GuiElements.matchViewLabels(match.getSecondTeam().getClubName(), "allMatches__secondTeamName");
            Label secondTeamGoals = GuiElements.matchViewLabels(String.valueOf(match.getSecondTeamSingleMatchStats().getGoals()),
                    "allMatches__secondTeamGoals");
            secondTeamNameContainer.getChildren().add(secondTeamName);
            secondTeamGoalsContainer.getChildren().add(secondTeamGoals);
            vBoxSecondTeam.getChildren().addAll(secondTeamNameContainer, secondTeamGoalsContainer);

            HBox hBoxEachRow = GuiElements.hBox();
            hBoxEachRow.getChildren().addAll(vBoxFirstTeam, vBoxDate, vBoxSecondTeam);

            vBoxContainer.getChildren().addAll(hBoxEachRow);
        }

        ScrollPane scrollPaneContainer = GuiElements.scrollPane(700, 420, 330, 250);
        scrollPaneContainer.setContent(vBoxContainer);

        AnchorPane anchorPane = GuiElements.anchor();
        anchorPane.getChildren().addAll(eplLion2, scrollPaneContainer);
        return guiElements.scene(anchorPane, 1366, 700, "style.css");
    }

    public static void main(String[] args) {
        launch(args);
    }
}
