package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        solidArea = new Rectangle();
        solidArea.x = 8;
        solidArea.y = 8;
        solidArea.height = gp.tileSize * (2/3);
        solidArea.width = gp.tileSize * (2/3);

        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues() {
        worldX = gp.tileSize * 2; // Starting POS
        worldY = gp.tileSize * 2; // ^  ^   ^   ^
        speed = 5;
        direction = "down";
    }
    public void getPlayerImage() {
        
        try {

            up1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Prisoner_W1.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Prisoner_W2.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Prisoner_S1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Prisoner_S2.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Prisoner_A1.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Prisoner_A2.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/res/player/Prisoner_D1.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/res/player/Prisoner_D2.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if(keyH.upPressed == true || keyH.downPressed == true || keyH.leftPressed == true || keyH.rightPressed == true) {
            if(keyH.upPressed == true) {
                direction = "up";
                
            }
            if(keyH.downPressed == true) {
                direction = "down";
                
            }
            if(keyH.leftPressed == true) {
                direction = "left";
            }
            if(keyH.rightPressed == true) {
                direction = "right";
            }
            
            
    
            // CHECK TILE COLLISION
            collisionOn = false;
            gp.cChecker.checkTile(this);

            // IF COLLISION IS FALSE, PLAYER CAN MOVE
            if(collisionOn == false) {

                if(direction == "up") {
                    worldY -= speed;
                }
                if(direction == "down") {
                    worldY += speed;
                }
                if(direction == "left") {
                    worldX -= speed;
                }
                if(direction == "right") {
                    worldX += speed;
                }
            }

            spriteCounter++;
            if(spriteCounter > 24) {
                if(spriteNum == 1) {
                    spriteNum = 2;
                }
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;
        
        switch (direction) {
            case "up":
                if(spriteNum == 1) {
                    image = up1;
                }
                if(spriteNum == 2) {
                    image = up2;
                }
                break;
            case "down":
                if(spriteNum == 1) {
                    image = down1;
                }
                if(spriteNum == 2) {
                    image = down2;
                }
                break;
            case "left":
                if(spriteNum == 1) {
                    image = left1;
                }
                if(spriteNum == 2) {
                    image = left2;
                }
                break;
            case "right":
                if(spriteNum == 1) {
                    image = right1;
                }
                if(spriteNum == 2) {
                    image = right2;
                }
                break;
        }

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}
