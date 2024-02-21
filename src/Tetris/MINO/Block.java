package Tetris.MINO;

import java.awt.*;

public class Block extends Rectangle {
    public int x,y;
    public static final int size = 30;
    public Color c;

    public Block(Color c){
        this.c = c;
    }

    public void draw(Graphics2D g2d){
        g2d.setColor(c);
        g2d.fillRect(x,y,size,size);
    }
}
