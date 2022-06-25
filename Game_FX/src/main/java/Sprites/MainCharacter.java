package Sprites;

import Scenes.GameScene;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import static Scenes.GameScene.getPlayerVelocity;

public class MainCharacter {


    public MainCharacter() {
    }

    public Node createEntity(int x, int y, int w, int h, Color color) {
        Rectangle entity = new Rectangle(w, h);
        entity.setTranslateX(x);
        entity.setTranslateY(y);
        entity.setFill(color);

        return entity;
    }

    public void jumpPlayer(){
        if (GameScene.isCanJump()){
            GameScene.setPlayerVelocity(getPlayerVelocity().add(0,-30));      //-30 скорость прыжка
            GameScene.setCanJump(false);
        }
    }

    public void movePlayerX(int value){                        //здесь проверка на столкновение с платформой и передвижение
        boolean moveRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++)   {
            for (Node platform : GameScene.getPlatforms()){
                if (GameScene.getPlayer().getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(moveRight){
                        if (GameScene.getPlayer().getTranslateX() + 40 == platform.getTranslateX()){
                            GameScene.getPlayer().setTranslateX(GameScene.getPlayer().getTranslateX() - 1);
                            return;
                        }
                    }else {
                        if (GameScene.getPlayer().getTranslateX() - 60 == platform.getTranslateX() ){
                            GameScene.getPlayer().setTranslateX(GameScene.getPlayer().getTranslateX() + 1);
                            return;
                        }
                    }
                }
            }
            GameScene.getPlayer().setTranslateX(GameScene.getPlayer().getTranslateX() + (moveRight ? 1:-1));
        }

    }
    public void movePlayerY(int value){
        boolean moveDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : GameScene.getPlatforms()){
                if (GameScene.getPlayer().getBoundsInParent().intersects(platform.getBoundsInParent())){
                    if(moveDown){

                        if (GameScene.getPlayer().getTranslateY() + 40 == platform.getTranslateY()){
                            GameScene.getPlayer().setTranslateY(GameScene.getPlayer().getTranslateY() - 1);       // - 1 вроде как для поднятия игрока от застриванийн но это не точно
                            GameScene.setCanJump(true);
                            return;
                        }
                    }else {
                        if (GameScene.getPlayer().getTranslateY() - 60 == platform.getTranslateY() ){
                            return;
                        }
                    }
                }
            }
            GameScene.getPlayer().setTranslateY(GameScene.getPlayer().getTranslateY() + (moveDown ? 1:-1));
        }
    }
}
