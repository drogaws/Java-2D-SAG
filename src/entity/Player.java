package entity;

import java.awt.Color;
import java.awt.Graphics2D;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        setDefaultValues();
    }
    
    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 2;
    }

    public void update() {
        if(keyH.upPressed == true) {
            y -= speed;
        }
        if(keyH.leftPressed == true) {
            x -= speed;
        }
        if(keyH.downPressed == true) {
            y += speed;
        }
        if(keyH.rightPressed == true) {
            x += speed;
        }
    }

    public void draw(Graphics2D g2) {
        
        g2.setColor(Color.PINK);

        g2.fillOval(x, y, gp.tileSize, gp.tileSize);
    }
}
