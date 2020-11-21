package oop.cw.guifx;

import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GuiElements {
    private static DropShadow shadow = new DropShadow();

    public static AnchorPane anchor(){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle("-fx-background-color: #37003d; -fx-border-color: #f00;");
        return anchorPane;
    }

    public static StackPane stackPane(int width, int height) {
        StackPane stackPane = new StackPane();
        stackPane.setMinHeight(height);
        stackPane.setMinWidth(width);
        return stackPane;
    }

    public Scene scene(AnchorPane anchorPane, int width, int height, String file) {
        Scene scene = new Scene(anchorPane, width, height);
        String css = this.getClass().getResource(file).toExternalForm();
        scene.getStylesheets().add(css);
        return scene;
    }

    public static Button button(String btnText, int layX, int layY, String id){
        Button button = new Button();
        button.setText(btnText);
        button.setLayoutX(layX);
        button.setLayoutY(layY);
        button.setId(id);
        button.setCursor(Cursor.HAND);
        button.setEffect(shadow);
        return button;
    }

    public static List<TableColumn<FootballClub, String>> generatePointsTableColumns(TableView<FootballClub> tableView) {
        tableView.setMinHeight(500);
        TableColumn<FootballClub, String> columnClub = GuiElements.tableColumns(tableView, "Club", 0.3);
        columnClub.setCellValueFactory(new PropertyValueFactory<>("clubName"));

        TableColumn<FootballClub, String> columnMatches = GuiElements.tableColumns(tableView, "MP", 0.09);
        columnMatches.setStyle("-fx-alignment: CENTER");
        columnMatches.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getMatchesPlayed()))
        );

        TableColumn<FootballClub, String> columnWins = GuiElements.tableColumns(tableView, "W", 0.085);
        columnWins.setStyle("-fx-alignment: CENTER");
        columnWins.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getWins()))
        );

        TableColumn<FootballClub, String> columnDraws = GuiElements.tableColumns(tableView, "D", 0.085);
        columnDraws.setStyle("-fx-alignment: CENTER");
        columnDraws.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getDraws()))
        );

        TableColumn<FootballClub, String> columnLosses = GuiElements.tableColumns(tableView, "L", 0.085);
        columnLosses.setStyle("-fx-alignment: CENTER");
        columnLosses.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getDefeats()))
        );

        TableColumn<FootballClub, String> columnGoalsFor = GuiElements.tableColumns(tableView, "GF", 0.085);
        columnGoalsFor.setStyle("-fx-alignment: CENTER");
        columnGoalsFor.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getGoalsFor()))
        );

        TableColumn<FootballClub, String> columnGoalsAgainst = GuiElements.tableColumns(tableView, "GA", 0.085);
        columnGoalsAgainst.setStyle("-fx-alignment: CENTER");
        columnGoalsAgainst.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getGoalsAgainst()))
        );

        TableColumn<FootballClub, String> columnGoalDifference = GuiElements.tableColumns(tableView, "GD", 0.09);
        columnGoalDifference.setStyle("-fx-alignment: CENTER");
        columnGoalDifference.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getGoalDifference()))
        );

        TableColumn<FootballClub, String> columnPoints = GuiElements.tableColumns(tableView, "Pts", 0.09);
        columnPoints.setStyle("-fx-alignment: CENTER");
        columnPoints.setCellValueFactory(cellData ->
                new SimpleStringProperty(String.valueOf(cellData.getValue().getFootballClubTotalStatistics().getPoints()))
        );

        return new ArrayList<>(
                Arrays.asList(columnClub, columnMatches, columnWins, columnDraws, columnLosses, columnGoalsFor, columnGoalsAgainst,
                        columnGoalDifference, columnPoints
                )
        );
    }
    private static TableColumn<FootballClub, String> tableColumns(TableView<FootballClub> tableView, String columnName, double columnWidthMultiplier) {
        TableColumn<FootballClub, String> column = new TableColumn<>(columnName);
        column.prefWidthProperty().bind(tableView.widthProperty().multiply(columnWidthMultiplier));
        column.setResizable(false);
        return column;
    }

    public static ImageView imageViewLay(String imageFile, int layX, int layY, int height, int width){
        Image imageLay = new Image(imageFile);
        ImageView imageViewLay = new ImageView(imageLay);
        imageViewLay.setFitHeight(height);
        imageViewLay.setFitWidth(width);
        imageViewLay.setX(layX);
        imageViewLay.setY(layY);
        return imageViewLay;
    }
}
