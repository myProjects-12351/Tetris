package Tetris;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {
    static final int WIDTH = 1280;
    static final int HEIGHT = 720;
    final int FPS = 60;

    Thread thread;
    PlayManager playManager;

    GamePanel(){
        this.setBackground(Color.black);
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setLayout(null);
        this.addKeyListener(new KeyHandler());
        this.setFocusable(true);

        playManager = new PlayManager();
    }

    public void startGame(){
        thread = new Thread(this);
        thread.start();
    }

    private void update(){
        if(!KeyHandler.pause && !playManager.gameOver) {
            playManager.update();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g;
        playManager.draw(g2d);
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;  // 1 000 000 000 / fps
        double delta = 0;
        long lastTime = System.nanoTime();
        long currTime;

        while(thread != null){
            currTime = System.nanoTime();

            delta += (currTime - lastTime) / drawInterval;
            lastTime = currTime;

            if(delta > 1){
                update();
                repaint();
                delta--;
            }
        }
    }
}
