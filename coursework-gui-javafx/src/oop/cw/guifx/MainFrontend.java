package oop.cw.guifx;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Collections;
import java.util.List;

public class MainFrontend extends Application {
    @Override
    public void start(Stage primaryStage) {
        ConsoleApplication.loadData();
        List<FootballClub> allClubs = PremierLeagueManager.getAllFootballClubs();
        allClubs.sort(Collections.reverseOrder());
        primaryStage.getIcons().add(new Image("file:/C:/Users/Ammuuu/Downloads/learning/UNI/OOP-Module/Coursework/OOP-COURSEWORK/images/PL-lion.png"));

        displayTable(allClubs, primaryStage);
    }

    public static void displayTable(List<FootballClub> allClubs, Stage window) {
        TableView<FootballClub> tableView = new TableView<>();
        List<TableColumn<FootballClub, String>> allColumns = GuiElements.generateTableColumns(tableView);

        for (TableColumn<FootballClub, String> eachColumn : allColumns) {
            tableView.getColumns().add(eachColumn);
        }

        for (FootballClub footballClub : allClubs) {
            tableView.getItems().add(footballClub);
        }

        AnchorPane anchorPane = GuiElements.anchor();
        VBox vbox = GuiElements.vbox(1366);
        vbox.getChildren().add(tableView);
        anchorPane.getChildren().add(vbox);
        Scene scene = new Scene(anchorPane, 1366, 700);
        window.setScene(scene);
        window.setTitle("PREMIER LEAGUE MANAGER");
        window.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
