package Tetris.MINO;

import java.awt.*;

public class Mino_Bar extends Mino{
    public Mino_Bar(){
        create(Color.blue);
    }

    public void setXY(int x, int y) {
        //
        //   o o o o
        //

        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x-Block.size;
        b[1].y = b[0].y;
        b[2].x = b[0].x+Block.size;
        b[2].y = b[0].y;
        b[3].x = b[0].x+Block.size*2;
        b[3].y = b[0].y;
    }

    public void getDirecion1(){
        //
        //   o o o o
        //

        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x-Block.size;
        tempB[1].y = b[0].y;
        tempB[2].x = b[0].x+Block.size;
        tempB[2].y = b[0].y;
        tempB[3].x = b[0].x+Block.size*2;
        tempB[3].y = b[0].y;

        updateXY('1');
    }
    public void getDirecion2(){
        //   o
        //   o
        //   o
        //   o

        tempB[0].x = b[0].x;
        tempB[0].y = b[0].y;
        tempB[1].x = b[0].x;
        tempB[1].y = b[0].y-Block.size;
        tempB[2].x = b[0].x;
        tempB[2].y = b[0].y+Block.size;
        tempB[3].x = b[0].x;
        tempB[3].y = b[0].y+Block.size*2;

        updateXY('2');
    }
    public void getDirecion3(){
        getDirecion1();
    }
    public void getDirecion4(){
        getDirecion2();
    }
}
