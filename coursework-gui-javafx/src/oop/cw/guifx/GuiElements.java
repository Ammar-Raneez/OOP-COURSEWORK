package oop.cw.guifx;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuiElements {
    public static AnchorPane anchor(){
        return new AnchorPane();
    }

    public static VBox vbox(int minWidth) {
        VBox vBox = new VBox();
        vBox.setMinWidth(minWidth);
        return vBox;
    }

    private static TableColumn<FootballClub, String> tableColumns(TableView<FootballClub> tableView, String columnName, double columnWidthMultiplier) {
        TableColumn<FootballClub, String> column = new TableColumn<>(columnName);
        column.prefWidthProperty().bind(tableView.widthProperty().multiply(columnWidthMultiplier));
        column.setResizable(false);
        return column;
    }

    public static List<TableColumn<FootballClub, String>> generateTableColumns(TableView<FootballClub> tableView) {
        TableColumn<FootballClub, String> columnClub = GuiElements.tableColumns(tableView, "Club", 0.3);
        columnClub.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        TableColumn<FootballClub, String> columnMatches = GuiElements.tableColumns(tableView, "MP", 0.09);
        columnMatches.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getMatchesPlayed()))
        );

        TableColumn<FootballClub, String> columnWins = GuiElements.tableColumns(tableView, "W", 0.085);
        columnWins.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getWins()))
        );

        TableColumn<FootballClub, String> columnDraws = GuiElements.tableColumns(tableView, "D", 0.085);
        columnDraws.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getDraws()))
        );

        TableColumn<FootballClub, String> columnLosses = GuiElements.tableColumns(tableView, "L", 0.085);
        columnLosses.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getDefeats()))
        );

        TableColumn<FootballClub, String> columnGoalsFor = GuiElements.tableColumns(tableView, "GF", 0.085);
        columnGoalsFor.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getGoalsFor()))
        );

        TableColumn<FootballClub, String> columnGoalsAgainst = GuiElements.tableColumns(tableView, "GA", 0.085);
        columnGoalsAgainst.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getGoalsAgainst()))
        );

        TableColumn<FootballClub, String> columnGoalDifference = GuiElements.tableColumns(tableView, "GD", 0.085);
        columnGoalDifference.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getGoalDifference()))
        );

        TableColumn<FootballClub, String> columnPoints = GuiElements.tableColumns(tableView, "Pts", 0.09);
        columnPoints.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getPoints()))
        );

        return new ArrayList<>(
                Arrays.asList(columnClub, columnMatches, columnWins, columnDraws, columnLosses, columnGoalsFor, columnGoalsAgainst,
                        columnGoalDifference, columnPoints
                )
        );
    }
}
