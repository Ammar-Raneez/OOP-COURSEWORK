package oop.cw.guifx;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.List;

public class MainFrontend extends Application {
    @Override
    public void start(Stage primaryStage) {
        ConsoleApplication.loadData();
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();

        TableView tableView = new TableView();

        TableColumn<FootballClub, String> columnClub = new TableColumn<>("Club");
        columnClub.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        TableColumn<FootballClub, String> columnMatches = new TableColumn<>("MP");
        columnMatches.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getMatchesPlayed()))
        );

        TableColumn<FootballClub, String> columnWins = new TableColumn<>("W");
        columnWins.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getWins()))
        );

        TableColumn<FootballClub, String> columnDraws = new TableColumn<>("D");
        columnDraws.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getDraws()))
        );

        TableColumn<FootballClub, String> columnLosses = new TableColumn<>("L");
        columnLosses.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getDefeats()))
        );

        TableColumn<FootballClub, String> columnGoalsFor = new TableColumn<>("GF");
        columnGoalsFor.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getGoalsFor()))
        );

        TableColumn<FootballClub, String> columnGoalsAgainst = new TableColumn<>("GA");
        columnGoalsAgainst.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getGoalsAgainst()))
        );

        TableColumn<FootballClub, String> columnGoalDifference = new TableColumn<>("GD");
        columnGoalDifference.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getGoalDifference()))
        );

        TableColumn<FootballClub, String> columnPoints = new TableColumn<>("Pts");
        columnPoints.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getPoints()))
        );

        tableView.getColumns().add(columnClub);
        tableView.getColumns().add(columnMatches);
        tableView.getColumns().add(columnWins);
        tableView.getColumns().add(columnDraws);
        tableView.getColumns().add(columnLosses);
        tableView.getColumns().add(columnGoalsFor);
        tableView.getColumns().add(columnGoalsAgainst);
        tableView.getColumns().add(columnGoalDifference);
        tableView.getColumns().add(columnPoints);

        for (FootballClub footballClub : allClubs) {
            tableView.getItems().add(footballClub);
        }

        VBox vbox = new VBox(tableView);
        Scene scene = new Scene(vbox);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
