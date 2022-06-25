package Scenes;

import GameEngine.Main;
import javafx.animation.AnimationTimer;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javafx.scene.text.FontWeight;

public class CreditsScene extends MainScene {

    public CreditsScene() {
        super();
    }

    private void showCredits(){
        Font font = Font.font("Arial", FontWeight.NORMAL, 24);
        getGc().setFont(font);
        getGc().setFill(Color.GHOSTWHITE);
        getGc().fillText("You lost \npress ESC", 330, 150);

    }

    @Override
    public void draw() {

        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {

                getGc().setFill(Color.BLACK);
                getGc().fillRect(0,0, getGameWidth(), getGameHeight());
                showCredits();

                if (isPressed(KeyCode.ESCAPE)){
                    this.stop();
                    Main.exit();
                }
            }
        };
        timer.start();

    }
}
