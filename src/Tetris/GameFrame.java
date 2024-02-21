package Tetris;

import javax.swing.*;

public class GameFrame extends JFrame {
    GameFrame(){
        GamePanel gamePanel = new GamePanel();
        gamePanel.startGame();
        this.add(gamePanel);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("TETRIS");
        this.pack();
        this.setVisible(true);

    }
}