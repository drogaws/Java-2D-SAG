package main;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Random;
import java.awt.Color;

import javax.swing.JPanel;

import entity.Player;
import object.SuperObject;
import tile.TileManager;

public class GamePanel extends JPanel implements Runnable{

    // SCREEN SETTINGS
    final int originalTileSize = 16; // 16x16 tile
    public final static int SCALE = 3;

    public final int tileSize = originalTileSize * SCALE; // 48x48 tile
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = tileSize * maxScreenCol; // 256 * scale pixels wide
    public final int screenHeight = tileSize * maxScreenRow; // 192 * scale pixels tall

    // WORLD SETTIGS (37 Col;39 Row)
    public final int maxWorldCol = 29;
    public final int maxWorldRow = 32;
    public final int worldWidth = tileSize * maxWorldCol;
    public final int worldHeight = tileSize * maxWorldRow;

    // FPS
    final int FPS = 120;


    // Objects
    TileManager tileM = new TileManager(this);
    KeyHandler keyH = new KeyHandler();
    
    public  CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    public UI ui = new UI(this);
    Thread gameThread;
    public Player player = new Player(this, keyH);
    public SuperObject obj[] = new SuperObject[100];
    public SkillCheck skillCheck = new SkillCheck(this);
    public OrderGenerator orderGenerator = new OrderGenerator(this);
    public Random random = new Random();

    
    // Constructor
    public GamePanel() {
        
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    // Order

    // SETUP GAME
    public void setupGame() {
        aSetter.setObject();
    }

    // START GAME
    public void startGameThread() {
        gameThread = new Thread(this); // assignes this class to gamethread
        gameThread.start(); // Call the run method
    }
    @Override
    public void run() {

        double drawInterval = 1000000000/FPS; // 0.0166667 seconds
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        long timer = 0;
        long orderInterval = 0; 
        long drawCount = 0;
        
        
        while(gameThread != null) { // Loop: while the Thread exist loop continues.

            currentTime = System.nanoTime();
            
            delta += (currentTime - lastTime) / drawInterval;
            timer += (currentTime - lastTime);
            lastTime = currentTime;

            if(delta >= 1) {
                update();
                repaint();
                delta--;
                drawCount++;
                
                
            }

            if(timer >= 1000000000) {
                System.out.println("FPS = " + drawCount);
                drawCount = 0;
                timer = 0;
                orderInterval++;
                if(orderInterval >= 30) {
                    orderGenerator.carsInLine++;
                    ui.setMessage("*Ding Ding*: " + orderGenerator.carsInLine + " cars in line.");
                    orderInterval = 0;
                }
            }
        }
    }
    public void update() {

        
        player.update();
        skillCheck.update();
        

    }
    public void paintComponent(Graphics g) {
        
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D)g;

        // TILE
        tileM.draw(g2);

        // OBJECT
        for(int i = 0; i < obj.length; i++) {
            if(obj[i] != null) {
                obj[i].draw(g2, this);
            }
        }

        // PLAYER
        player.draw(g2);

        // UI
        ui.draw(g2);
        ui.drawMessages(g2);
        ui.displayOrder(g2);
        ui.carCount(g2);
        
        // Skill Check
        skillCheck.draw(g2);

        // Dispose
        g2.dispose();
    }

    public void popUpMessage(String message) {
        ui.setMessage(message);
    }
}
