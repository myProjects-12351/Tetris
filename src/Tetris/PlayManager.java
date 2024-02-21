package Tetris;

import Tetris.MINO.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class PlayManager {
    final int WIDTH = 360;
    final int HEIGHT = 600;
    public static int left_x;
    public static int right_x;
    public static int top_y;
    public static int bottom_y;

    boolean gameOver;

    Mino currnetMino;
    final int MINO_START_X;
    final int MINO_START_Y;
    Mino nextMino;
    final int NEXTMINO_X;
    final int NEXTMINO_Y;
    public static ArrayList<Block> staticBlocks = new ArrayList<>();
    public static int dropInterval = 60;

    public PlayManager(){
        left_x = (GamePanel.WIDTH/2) - (WIDTH/2);
        right_x = left_x + WIDTH;
        top_y = 50;
        bottom_y = top_y + HEIGHT;

        MINO_START_X = left_x + (WIDTH/2) - Block.size;
        MINO_START_Y = top_y + Block.size;

        NEXTMINO_X = right_x + 175;
        NEXTMINO_Y = top_y + 500;

        currnetMino = pickMino();
        currnetMino.setXY(MINO_START_X, MINO_START_Y);
        nextMino = pickMino();
        nextMino.setXY(NEXTMINO_X, NEXTMINO_Y);
    }
    public void update() {
        if (currnetMino.active) {
            currnetMino.update();
        }else{
            staticBlocks.add(currnetMino.b[0]);
            staticBlocks.add(currnetMino.b[1]);
            staticBlocks.add(currnetMino.b[2]);
            staticBlocks.add(currnetMino.b[3]);

            currnetMino.deactivating = false;

            if(currnetMino.b[0].x == MINO_START_X && currnetMino.b[0].y == MINO_START_Y){
                gameOver = true;
            }

            currnetMino = nextMino;
            currnetMino.setXY(MINO_START_X, MINO_START_Y);
            nextMino = pickMino();
            nextMino.setXY(NEXTMINO_X,NEXTMINO_Y);

            checkDelete();
        }
    }
    public Mino pickMino(){
        Mino mino = null;
        int i = new Random().nextInt(7);

        switch (i){
            case 0 -> mino = new Mino_L1();
            case 1 -> mino = new Mino_L2();
            case 2 -> mino = new Mino_Square();
            case 3 -> mino = new Mino_Bar();
            case 4 -> mino = new Mino_T();
            case 5 -> mino = new Mino_Z1();
            case 6 -> mino = new Mino_Z2();
        }

        return mino;
    }

    public void checkDelete(){
        int x = left_x;
        int y = top_y;
        int countBlocks = 0;

        while(x < right_x && y < bottom_y){
            for(int i=0; i<staticBlocks.size(); i++){
                if(staticBlocks.get(i).x == x && staticBlocks.get(i).y == y){
                    countBlocks++;
                }
            }

            x+=Block.size;
            if(x == right_x){
                if(countBlocks == 12){
                    for(int i=staticBlocks.size()-1; i>=0; i--){
                        if(staticBlocks.get(i).y == y){
                            staticBlocks.remove(i);
                        }
                    }
                    for(int i=0; i<staticBlocks.size(); i++){
                        if(staticBlocks.get(i).y < y){
                            staticBlocks.get(i).y += Block.size;
                        }
                    }
                }
                countBlocks = 0;
                x = left_x;
                y+=Block.size;
            }
        }
    }
    public void draw(Graphics2D g2d){
        //PLAY AREA
        g2d.setColor(Color.WHITE);
        g2d.setStroke(new BasicStroke(4f));
        g2d.drawRect(left_x-4,top_y-4,WIDTH+8,HEIGHT+8);

        //Mino frame
        int x = right_x + 100;
        int y = bottom_y - 200;
        g2d.drawRect(x,y,200,200);
        g2d.setFont(new Font("Arial", Font.PLAIN, 30));
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        g2d.drawString("NEXT", x+60, y+60);

        //drawMino
        if(currnetMino != null){
            currnetMino.draw(g2d);
        }

        //nexMino
        nextMino.draw(g2d);

        //draw pause
        if(KeyHandler.pause){
           g2d.setColor(Color.yellow);
           g2d.setFont(new Font("Arial", Font.BOLD, 50));
           x = left_x+70;
           y = top_y+320;
           g2d.drawString("PAUSE", x, y);
        }

        //gameOver
        if(gameOver){
            g2d.setColor(Color.yellow);
            g2d.setFont(new Font("Arial", Font.BOLD, 50));
            x = left_x+70;
            y = top_y+320;
            g2d.drawString("GAME OVER", x, y);
        }

        //draw list
        for(int i=0; i<staticBlocks.size(); i++){
            staticBlocks.get(i).draw(g2d);
        }
    }
}