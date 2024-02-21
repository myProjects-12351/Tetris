package Tetris.MINO;

import java.awt.*;

import Tetris.KeyHandler;
import Tetris.PlayManager;

public class Mino {
    public Block[] b = new Block[4];
    public Block[] tempB = new Block[4];
    int autoDropCounter=0;
    public char direction = '1'; // '1', '2', '3', '4'
    boolean leftColision, rightColision, bottomColision;
    public boolean active = true;
    public boolean deactivating;
    int deactivatingCounter=0;

    public void create(Color c){
        b[0] = new Block(c);
        b[1] = new Block(c);
        b[2] = new Block(c);
        b[3] = new Block(c);
        tempB[0] = new Block(c);
        tempB[1] = new Block(c);
        tempB[2] = new Block(c);
        tempB[3] = new Block(c);
    }
    public void setXY(int x, int y){

    }
    public void updateXY(char direction){

        checkRotationColision();

        if(!leftColision && !rightColision && !bottomColision) {
            this.direction = direction;
            b[0].x = tempB[0].x;
            b[0].y = tempB[0].y;
            b[1].x = tempB[1].x;
            b[1].y = tempB[1].y;
            b[2].x = tempB[2].x;
            b[2].y = tempB[2].y;
            b[3].x = tempB[3].x;
            b[3].y = tempB[3].y;
        }
    }
    public void getDirecion1(){}
    public void getDirecion2(){}
    public void getDirecion3(){}
    public void getDirecion4(){}
    public void checkMovementColision() {
        leftColision = false;
        rightColision = false;
        bottomColision = false;

        checkStaticBlocksColisions();

        for(int i=0; i<b.length ; i++){
            if(b[i].x == PlayManager.left_x){
                leftColision = true;
            }
            if(b[i].x+Block.size == PlayManager.right_x){
                rightColision = true;
            }
            if(b[i].y + Block.size == PlayManager.bottom_y){
                bottomColision = true;
            }
        }
    }
    public void checkRotationColision() {
        leftColision = false;
        rightColision = false;
        bottomColision = false;

        checkStaticBlocksColisions();

        for(int i=0; i<b.length ; i++){
            if(tempB[i].x < PlayManager.left_x){
                leftColision = true;
            }
            if(tempB[i].x+Block.size > PlayManager.right_x){
                rightColision = true;
            }
            if(tempB[i].y + Block.size > PlayManager.bottom_y){
                bottomColision = true;
            }
        }
    }
    public void checkStaticBlocksColisions(){
        leftColision = false;
        rightColision = false;
        bottomColision = false;

        for(int i=0; i<PlayManager.staticBlocks.size(); i++){
            int target_X = PlayManager.staticBlocks.get(i).x;
            int target_Y = PlayManager.staticBlocks.get(i).y;

            for(int j=0; j<b.length; j++){
                if(b[j].y+Block.size == target_Y && b[j].x == target_X){
                    bottomColision = true;
                }
                if(b[j].x-Block.size == target_X && b[j].y == target_Y){
                    leftColision = true;
                }
                if(b[j].x+Block.size == target_X && b[j].y == target_Y){
                    rightColision = true;
                }
            }
        }
    }
    public void update(){
        if(deactivating)
            deactivating();

        checkMovementColision();

        if(KeyHandler.up){
            switch(direction){
                case '1' -> getDirecion2();
                case '2' -> getDirecion3();
                case '3' -> getDirecion4();
                case '4' -> getDirecion1();
            }
            KeyHandler.up = false;
        }
        if(KeyHandler.down){
            if (!bottomColision){
                b[0].y += Block.size;
                b[1].y += Block.size;
                b[2].y += Block.size;
                b[3].y += Block.size;

                autoDropCounter = 0;

                KeyHandler.down = false;
            }
        }
        if(KeyHandler.left){
            if(!leftColision) {
                b[0].x -= Block.size;
                b[1].x -= Block.size;
                b[2].x -= Block.size;
                b[3].x -= Block.size;

                autoDropCounter = 0;

                KeyHandler.left = false;
            }
        }
        if(KeyHandler.right){
            if(!rightColision) {
                b[0].x += Block.size;
                b[1].x += Block.size;
                b[2].x += Block.size;
                b[3].x += Block.size;

                autoDropCounter = 0;

                KeyHandler.right = false;
            }
        }

        if(bottomColision){
            deactivating = true;
        }else {
            autoDropCounter++;
            if (autoDropCounter == PlayManager.dropInterval) {
                b[0].y += Block.size;
                b[1].y += Block.size;
                b[2].y += Block.size;
                b[3].y += Block.size;
                autoDropCounter = 0;
            }
        }
    }
    public void deactivating(){
        deactivatingCounter++;
        if (deactivatingCounter == 45){

            deactivatingCounter = 0;
            checkMovementColision();

            if(bottomColision){
                active = false;
            }
        }
    }
    public void draw(Graphics2D g2d){
        g2d.setColor(b[0].c);
        g2d.fillRect(b[0].x,b[0].y,Block.size,Block.size);
        g2d.fillRect(b[1].x,b[1].y,Block.size,Block.size);
        g2d.fillRect(b[2].x,b[2].y,Block.size,Block.size);
        g2d.fillRect(b[3].x,b[3].y,Block.size,Block.size);
    }
}
