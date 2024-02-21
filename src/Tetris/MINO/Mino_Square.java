package Tetris.MINO;

import java.awt.*;

public class Mino_Square extends Mino{
    public Mino_Square(){
        create(Color.red);
    }

    public void setXY(int x, int y) {
        //   o o
        //   o o

        b[0].x = x;
        b[0].y = y;
        b[1].x = b[0].x+Block.size;
        b[1].y = b[0].y;
        b[2].x = b[0].x;
        b[2].y = b[0].y+Block.size;
        b[3].x = b[0].x+Block.size;
        b[3].y = b[0].y+Block.size;
    }

    public void getDirecion1(){

    }
    public void getDirecion2(){

    }
    public void getDirecion3(){

    }
    public void getDirecion4(){

    }
}
