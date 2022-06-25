package GameEngine;


import Scenes.CreditsScene;
import Scenes.GameScene;
import Scenes.MainScene;
import javafx.application.Application;

import javafx.stage.Stage;


public class Main extends Application {


    private static final int maxScenes = 2;

    private static final MainScene[] scenes =
            new MainScene[maxScenes];

    private static Stage stage;


    @Override
    public void start(Stage stage) {

        Main.stage = stage;
        scenes[0] = new GameScene();
        scenes[1] = new CreditsScene();
        stage.setTitle("The Egoschin Game");
        setScene(0);
        stage.show();

    }

    public static void setScene(int numScene) {
        stage.setScene(scenes[numScene]);
        scenes[numScene].draw();
    }

    public static void exit() {
        stage.hide();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
