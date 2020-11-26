package coursework;

/*
 * GuiElements
 * Copyright © 2020 Ammar Raneez. All Rights Reserved.
 */

import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * GuiElements class, class that handles creation of GUI elements
 * @version 1.x November 22 2020
 * @author Ammar Raneez | 2019163 | W1761196
 */
public class GuiElements {
    //*Shadow Effect*//
    private static final DropShadow DROP_SHADOW = new DropShadow();

    /**
     * Returns a styled anchorPane
     * @param style - style to apply
     * @return - preferred anchorPane
     */
    public static AnchorPane anchor(String style){
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.setStyle(style);
        return anchorPane;
    }

    /**
     * Returns a stackPane with preferred size
     * @param width - preferred width
     * @param height - preferred height
     * @return - preferred stackPane
     */
    public static StackPane stackPane(int width, int height) {
        StackPane stackPane = new StackPane();
        stackPane.setMinHeight(height);
        stackPane.setMinWidth(width);
        return stackPane;
    }

    /**
     * Returns a scrollPane with preferred properties
     * @param width - preferred width
     * @param height - preferred height
     * @param layX - preferred X position
     * @param layY - preferred Y position
     * @param style - style to apply
     * @return - preferred stylePane
     */
    public static ScrollPane scrollPane(int width, int height, int layX, int layY, String style) {
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setPrefSize(width, height);
        scrollPane.setLayoutX(layX);
        scrollPane.setLayoutY(layY);
        scrollPane.setStyle(style);
        return scrollPane;
    }

    /**
     * Returns a scene with preferred properties
     * @param anchorPane - what pane should the scene display
     * @param width - preferred width
     * @param height - preferred height
     * @param file - path to css file
     * @return - preferred scene
     */
    public Scene scene(AnchorPane anchorPane, int width, int height, String file) {
        Scene scene = new Scene(anchorPane, width, height);
        String css = this.getClass().getResource(file).toExternalForm();
        scene.getStylesheets().add(css);
        return scene;
    }

    /**
     * Returns a stage with preferred properties
     * @param scene - scene to display
     * @param layX - x position
     * @param layY - y position
     * @return - preferred stage
     */
    public static Stage stage(Scene scene, int layX, int layY) {
        Stage innerWindow = new Stage();
        innerWindow.setScene(scene);
        innerWindow.setX(layX);
        innerWindow.setY(layY);
        innerWindow.setTitle("PREMIER LEAGUE MANAGER");
        innerWindow.getIcons().add(new Image("file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-lion.png"));
        return innerWindow;
    }

    /**
     * Returns a preferred HBox with center alignments
     * @param id - id to target in css
     * @return - preferred HBox
     */
    public static HBox hBox(String id) {
        HBox hBox = new HBox();
        hBox.setId(id);
        hBox.setAlignment(Pos.CENTER);
        return hBox;
    }

    /**
     * Returns a preferred VBox with center alignments
     * @param minWidth - min width the VBox will take
     * @return - preferred VBox
     */
    public static VBox vBox(int minWidth) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinWidth(minWidth);
        return vBox;
    }

    /**
     * Returns a Button with preferred properties
     * @param btnText - the content of the button
     * @param layX - X position
     * @param layY - Y position
     * @param id - id to target in css
     * @return - preferred Button
     */
    public static Button button(String btnText, int layX, int layY, String id){
        Button button = new Button();
        button.setText(btnText);
        button.setLayoutX(layX);
        button.setLayoutY(layY);
        button.setId(id);
        button.setCursor(Cursor.HAND);
        button.setEffect(DROP_SHADOW);
        return button;
    }

    /**
     * Returns a TextField with preferred properties
     * @param placeholder - placeholder text
     * @param width - preferred width
     * @param height - preferred height
     * @param layX - X position
     * @param layY - Y position
     * @param id - id to target in css
     * @return - preferred TextField
     */
    public static TextField textField(String placeholder, int width, int height, int layX, int layY, String id) {
        TextField textField = new TextField();
        textField.setId(id);
        textField.setPromptText(placeholder);
        textField.setLayoutX(layX);
        textField.setLayoutY(layY);
        textField.setPrefSize(width, height);
        return textField;
    }

    /**
     * Returns a Label with preferred properties
     * @param text - the content of the label
     * @param id - id to target in css
     * @return - preferred Label
     */
    public static Label matchViewLabels(String text, String id) {
        Label label = new Label(text);
        label.setId(id);
        return label;
    }

    /**
     * Returns a ImageView that can be used to display an Image
     * @param imageFile - path to image
     * @param layX - X position
     * @param layY - Y position
     * @param height - preferred height
     * @param width - preferred width
     * @return - preferred ImageView
     */
    public static ImageView imageViewLay(String imageFile, int layX, int layY, int height, int width){
        Image imageLay = new Image(imageFile);
        ImageView imageViewLay = new ImageView(imageLay);
        imageViewLay.setFitHeight(height);
        imageViewLay.setFitWidth(width);
        imageViewLay.setX(layX);
        imageViewLay.setY(layY);
        return imageViewLay;
    }

    /**
     * Main table method, that populates the table data
     * @param tableView - what TableView it is to manipulate
     * @return - all Columns to populate the TableView with
     */
    public static List<TableColumn<FootballClub, String>> generatePointsTableColumns(TableView<FootballClub> tableView) {
        tableView.setMinHeight(500);
        TableColumn<FootballClub, String> columnClub = GuiElements.tableColumns(tableView, "Club", 0.3);
        columnClub.setCellValueFactory(cellData ->
                new SimpleStringProperty(cellData.getValue().getClubName().toUpperCase())
        );

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

    /**
     * Private helper method that is used to create the header row columns
     * @param tableView - the TableView the columns will be placed in
     * @param columnName - value of the column header
     * @param columnWidthMultiplier - width of the column
     * @return - modified TableColumn
     */
    private static TableColumn<FootballClub, String> tableColumns(TableView<FootballClub> tableView, String columnName, double columnWidthMultiplier) {
        TableColumn<FootballClub, String> column = new TableColumn<>(columnName);
        column.prefWidthProperty().bind(tableView.widthProperty().multiply(columnWidthMultiplier));
        column.setResizable(false);
        return column;
    }

    /**
     * Returns a custom confirmation Alert with a graphic
     * @return - custom confirmation Alert
     */
    public static Alert closeWindowCommon(){
        ImageView imageConfirm = imageViewLay("file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-lionAlert.png",
                0, 0, 100, 100);
        Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION, null, ButtonType.YES, ButtonType.NO);
        closeAlert.setHeaderText("Do you REALLY want to exit?");
        closeAlert.setGraphic(imageConfirm);
        closeAlert.setTitle("Premier League Manager");
        return closeAlert;
    }

    /**
     * Displays a custom Error Alert with a graphic
     * @param text - text to display in the Alert
     */
    public static void errorAlert(String text){
        ImageView imageConfirm = imageViewLay("file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-lionAlert.png",
                0, 0, 100, 100);
        Alert closeAlert = new Alert(Alert.AlertType.ERROR, null, ButtonType.OK);
        closeAlert.setHeaderText(text);
        closeAlert.setGraphic(imageConfirm);
        closeAlert.setTitle("Premier League Manager");
        closeAlert.showAndWait();
        closeAlert.close();
    }
}
