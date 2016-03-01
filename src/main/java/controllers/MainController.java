package controllers;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import start.Setting;
import start.Statistic;

/**
 * Created by 4 on 28.02.2016.
 */
public class MainController {

    public void clickOnNewGame(ActionEvent actionEvent) {
        System.out.println("new game lets go");
    }

    public void clickOnStore(ActionEvent actionEvent) {
        System.out.println("store lets go");
    }

    public void clickOnSetting(ActionEvent actionEvent) {
        System.out.println("setting lets go");
        Setting setting = new Setting();
        setting.ShowScene();
    }

    public void clickOnStatistic(ActionEvent actionEvent) {
        System.out.println("Statistic lets go");
        Statistic statistic = new Statistic();
        statistic.ShowScene();
    }
}
