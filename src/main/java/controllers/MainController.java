package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
}
