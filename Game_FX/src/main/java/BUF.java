import Levels.LevelData;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;

//public class BUF {

//import Levels.LevelData;
//import javafx.animation.AnimationTimer;
//import javafx.application.Application;
//import javafx.geometry.Point2D;
//import javafx.scene.Node;
//import javafx.scene.Scene;
//import javafx.scene.input.KeyCode;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.shape.Rectangle;
//import javafx.stage.Stage;
//
//
//import java.util.ArrayList;
//import java.util.HashMap;
//
//    public class Main extends Application {
//
//        HashMap<KeyCode, Boolean> keys = new HashMap<>();                //состояние клавиш
//        ArrayList<Node> platforms = new ArrayList<>();                  // массив платформ для логики столкновения
//
//        private final Pane appRoot = new Pane();                        // это рут всего приложения
//        private final Pane gameRoot = new Pane();                       // это рут для механики игры
//
//
//        int levelWidth;
//        private Node player;
//        Point2D playerVelocity = new Point2D(0,0);
//        boolean canJump;
//
//        public static final int maxScenes = 2;
//        public static final int gameScene = 0;
//        public static final int creditsScene = 1;
//
//        void initContent(){                                     //это функция для предворительного саздания объектов
//            Rectangle bg = new Rectangle(720, 300);
//            levelWidth = LevelData.level[0].length() * 60;
//
//            for (int i = 0; i < LevelData.level.length; i++) {      //рисуем локацию
//                String line = LevelData.level[i];
//                for (int j = 0; j < line.length(); j++) {
//                    switch (line.charAt(j)){
//                        case '0':
//                            break;
//                        case '1':
//                            Node platform = createEntity(j*60, i*60+60,60, 60, Color.GREEN);
//                            platforms.add(platform);                       //создаем платформы
//                            break;
//                    }
//
//                }
//
//            }
//            player = createEntity(100, 100,40,40,Color.YELLOW);           // создаем игрока
//
//            player.translateXProperty().addListener((obs, old, newValue) ->{        // это функция отвечает
//                // за передвижение камеры
//                int offset = newValue.intValue();
//
//                if (offset > 360 && offset < levelWidth - 360){
//                    gameRoot.setLayoutX(-(offset-360));
//                }
//            });
//            appRoot.getChildren().addAll(bg, gameRoot);
//        }
//
//        void update() {                                                      //тут проверка на нажатие клавиш
//
//            if (player.getTranslateY() >= 300){
//                System.exit(0);
//
//            }
//            if (isPressed(KeyCode.SPACE) && player.getTranslateY() >= 5){
//                jumpPlayer();
//            }
//            if (isPressed(KeyCode.A) && player.getTranslateX()>= 5){
//                movePlayerX(-5);
//            }
//            if (isPressed(KeyCode.D) && player.getTranslateX() + 40 <= levelWidth - 5){
//                movePlayerX(5);
//            }
//            if (playerVelocity.getY() < 10){
//                playerVelocity = playerVelocity.add(0,1);
//            }
//            movePlayerY((int) playerVelocity.getY());
//            System.out.println(playerVelocity.getY());
//
//        }
//
//        boolean isPressed(KeyCode key){
//            return keys.getOrDefault(key,false);
//        }
//
//        void movePlayerX(int value){                        //здесь проверка на столкновение с платформой и передвижение
//            boolean moveRight = value > 0;
//
//            for (int i = 0; i < Math.abs(value); i++)   {
//                for (Node platform : platforms){
//                    if (player.getBoundsInParent().intersects(platform.getBoundsInParent())){
//                        if(moveRight){
//                            if (player.getTranslateX() + 40 == platform.getTranslateX()){
//                                player.setTranslateX(player.getTranslateX() - 1);
//                                return;
//                            }
//                        }else {
//                            if (player.getTranslateX() - 60 == platform.getTranslateX() ){
//                                player.setTranslateX(player.getTranslateX() + 1);
//                                return;
//                            }
//                        }
//                    }
//                }
//                player.setTranslateX(player.getTranslateX() + (moveRight ? 1:-1));
//            }
//
//        }
//        void movePlayerY(int value){
//            boolean moveDown = value > 0;
////        System.out.println(playerVelocity.getY());
//            for (int i = 0; i < Math.abs(value); i++) {
//                for (Node platform : platforms){
//                    if (player.getBoundsInParent().intersects(platform.getBoundsInParent())){
//                        if(moveDown){
//                            if (player.getTranslateY() + 40 == platform.getTranslateY()){
//                                player.setTranslateY(player.getTranslateY() - 1);       // - 1 вроде как для поднятия игрока от застриванийн но это не точно
//                                canJump = true;
//                                return;
//                            }
//                        }else {
//                            if (player.getTranslateY() - 60 == platform.getTranslateY() ){
//                                return;
//                            }
//                        }
//                    }
//                }
//                player.setTranslateY(player.getTranslateY() + (moveDown ? 1:-1));
//            }
//        }
//
//        void jumpPlayer(){
//            if (canJump){
//                playerVelocity = playerVelocity.add(0,-30);     //-30 скорость прыжка
//                canJump = false;
//            }
//        }
//
//        public Node createEntity(int x, int y, int w, int h, Color color){
//            Rectangle entity = new Rectangle(w, h);
//            entity.setTranslateX(x);
//            entity.setTranslateY(y);
//            entity.setFill(color);
//
//            gameRoot.getChildren().addAll(entity);
//            return entity;
//        }
//
//        @Override
//        public void start(Stage stage) {
//            initContent();
//
//            Scene scene = new Scene(appRoot);
//            scene.setOnKeyPressed(keyEvent -> keys.put(keyEvent.getCode(), true));
//            scene.setOnKeyReleased(keyEvent -> keys.put(keyEvent.getCode(), false));
//            stage.setTitle("Egoschin the best!!!");
//            stage.setScene(scene);
//            stage.show();
//
//            AnimationTimer timer = new AnimationTimer() {
//                @Override
//                public void handle(long l) {
//                    update();
//
//
//                }
//            };
//            timer.start();
//        }
//        public static void main(String[] args) {
//            launch(args);
//        }
//}
