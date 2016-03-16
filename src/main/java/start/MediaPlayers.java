package start;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.net.URL;
//
/**
 * Created by Егор on 16.03.2016.
 */
public class MediaPlayers {

    final URL resourceGame = getClass().getResource("/music/dnb.mp3");
    final Media mediaGame = new Media(resourceGame.toString());
    final MediaPlayer nGame = new MediaPlayer(mediaGame);

    final URL resource = getClass().getResource("/music/rufus.mp3");
    final Media mediaMenu = new Media(resource.toString());
    final MediaPlayer menu = new MediaPlayer(mediaMenu);

    void playNewGame() {
        nGame.play();
    }

    void playMenu() {
        menu.play();
    }

    void stopNewGame() {
        nGame.stop();
    }

    void stopMenu() {
        menu.stop();
    }

    void pauseMenu(){
        menu.pause();
    }

    void pauseNewGame(){
        nGame.pause();
    }
}
