package Scenes;

import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

import java.util.HashMap;

public abstract class MainScene extends Scene {

    private static final int gameWidth = 720;
    private static final int gameHeight = 300;
    protected final Pane root;
    private final HashMap<KeyCode, Boolean> keys;
    private static GraphicsContext gc;


    public MainScene() {
        super(new StackPane(), gameWidth, gameHeight);
        root = new Pane();
        this.setRoot(root);
        keys = new HashMap<>();

        Canvas canvas = new Canvas(gameWidth, gameHeight);
        root.getChildren().addAll(canvas);
        gc = canvas.getGraphicsContext2D();

        this.setOnKeyPressed(keyEvent -> keys.put(keyEvent.getCode(), true));
        this.setOnKeyReleased(keyEvent -> keys.put(keyEvent.getCode(), false));


    }

    public static GraphicsContext getGc() {
        return gc;
    }

    public static void setGc(GraphicsContext gc) {
        MainScene.gc = gc;
    }

    public abstract void draw();

    boolean isPressed(KeyCode key){
        return this.keys.getOrDefault(key,false);
    }

    public static int getGameWidth(){
        return gameWidth;
    }
    public static int getGameHeight(){
        return gameHeight;
    }
}

