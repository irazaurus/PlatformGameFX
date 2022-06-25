package Scenes;

import GameEngine.Main;
import Levels.LevelData;
import Sprites.MainCharacter;
import javafx.animation.AnimationTimer;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;


public class GameScene extends MainScene {

    private static final String BG_image = "src/main/java/Images/ГК.jpg";
    private Image background;
    private static Node player;
    private static MainCharacter mainCharacter;
    private static Point2D playerVelocity = new Point2D(0,0);
    private static boolean canJump;
    private static final ArrayList<Node> platforms = new ArrayList<>();
    private static int levelWidth;
    private static ImageView imageView;

    public GameScene() {
        super();

        try {
            background = new Image(Files.newInputStream(Paths.get(BG_image)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        mainCharacter = new MainCharacter();
        levelWidth = LevelData.level[0].length() * 60;
        imageView = new ImageView(background);



    }

    @Override
    public void draw() {
        Rectangle bgSky = new Rectangle(levelWidth, 300 );
        bgSky.setFill(Color.rgb(20,79,160));
         root.getChildren().addAll(bgSky, imageView);


        for (int i = 0; i < LevelData.level.length; i++) {      //рисуем локацию
            String line = LevelData.level[i];
            for (int j = 0; j < line.length(); j++) {
                switch (line.charAt(j)){
                    case '0':
                        break;
                    case '1':
                        Node platform = mainCharacter.createEntity(j*60, i*60+60,60, 60, Color.rgb(62,43,72));
                        root.getChildren().addAll(platform);
                        platforms.add(platform);                       //создаем платформы
                        break;
                }

            }


        }
        player = mainCharacter.createEntity(200, 200, 40, 40, Color.YELLOWGREEN);         // создаем игрока
        player.translateXProperty().addListener((obs, old, newValue) -> {        // это функция отвечает
            int offset = newValue.intValue();

                if (offset > 360 && offset < levelWidth - 360){
                    root.setLayoutX(-(offset-360));
                }
        });


        root.getChildren().addAll(player);


        AnimationTimer timer = new AnimationTimer() {
            @Override
            public void handle(long l) {
                if (player.getTranslateY() >= 300){

                    Main.setScene(1);
                }

                if (isPressed(KeyCode.SPACE) && player.getTranslateY() >= 5 ){
                    mainCharacter.jumpPlayer();
                }
                if (isPressed(KeyCode.A) && player.getTranslateX() >= 5){
                    mainCharacter.movePlayerX(-5);
                }
                if (isPressed(KeyCode.D) && player.getTranslateX() + 40 <= levelWidth - 5){
                    mainCharacter.movePlayerX(5);
                }
                if (playerVelocity.getY() < 10){
                    playerVelocity = playerVelocity.add(0, 1);
                }
                mainCharacter.movePlayerY((int) playerVelocity.getY());



            }
        };
        timer.start();

    }

    public static Point2D getPlayerVelocity() {
        return playerVelocity;
    }

    public static void setPlayerVelocity(Point2D playerVelocity) {
        GameScene.playerVelocity = playerVelocity;
    }

    public static boolean isCanJump() {
        return canJump;
    }

    public static void setCanJump(boolean canJump) {
        GameScene.canJump = canJump;
    }

    public static Node getPlayer() {
        return player;
    }
    public static ArrayList<Node> getPlatforms(){
        return platforms;
    }
}
