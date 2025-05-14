
package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;

import javax.swing.JPanel;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    final int scale = 3;

    final int tileSize = originalTileSize * scale; // 48x48 tile
    final int maxScreenCol = 16;
    final int maxScreenRow = 12;
    final int screenWidth = tileSize * maxScreenCol; // 768 pixels wide
    final int screenHeight = tileSize * maxScreenRow; // 576 pixels tall

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    // Player Default Settings
    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 2;

    // FPS
    final int FPS = 120;

    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    // GAME LOOP & LOGIC

    public void startGameThreadd() {
        gameThread = new Thread(this); // assignes this class to gamethread
        gameThread.start(); // Call the run method
    }
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.0166667 seconds
        
        while(gameThread != null) { // Loop: while the Thread exist loop continues.

            update();

            repaint();

        }
    }
    public void update() {

        if(keyH.upPressed == true) {
            playerY -= playerSpeed;
        }
        if(keyH.leftPressed == true) {
            playerX -= playerSpeed;
        }
        if(keyH.downPressed == true) {
            playerY += playerSpeed;
        }
        if(keyH.rightPressed == true) {
            playerX += playerSpeed;
        }

    }
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        g2.setColor(Color.PINK);
        g2.fillOval(playerX, playerY, tileSize, tileSize);
        g2.dispose();
    }
}
