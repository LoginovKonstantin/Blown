package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.DialogPane;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import start.NewGame;
import start.Setting;
import start.Statistic;
import start.Store;

import javax.swing.*;

/**
 * Created by 4 on 28.02.2016.
 */
public class MainController {

    public void clickOnNewGame(ActionEvent actionEvent) {
        NewGame newGame = new NewGame();
        newGame.showScene();
    }

    public void clickOnStore(ActionEvent actionEvent) {
        Store store = new Store();
        store.showScene();
    }

    public void clickOnSetting(ActionEvent actionEvent) {
        Setting setting = new Setting();
        setting.showScene();
    }

    public void clickOnStatistic(ActionEvent actionEvent) {
        Statistic statistic = new Statistic();
        statistic.showScene();
    }

    public void clickOnWhat(ActionEvent actionEvent) {
        Text headBar = new Text("\n DEVELOPERS\n");
        headBar.setStyle("-fx-fill: #027093;");
        Text created = new Text("LOGINOV KONSTANTIN AND ERUSLANOV EGOR.\n" +
                "CREATED IN 2016.\n" +
                "RUSSIA, PERM.");
        created.setStyle("-fx-fill: #027093;");


        Alert alertWhat = new Alert(Alert.AlertType.INFORMATION);
        alertWhat.setTitle("Informations");
        alertWhat.setHeaderText("DEVELOPERS");

        DialogPane dialogPane = alertWhat.getDialogPane();
        dialogPane.getStylesheets().add(
                getClass().getResource("/styles/main.css").toExternalForm());
        dialogPane.getStyleClass().add("myDialog");
        dialogPane.setContent(new TextFlow(created));
        dialogPane.setHeader(new TextFlow(headBar));

        alertWhat.show();
    }
}
