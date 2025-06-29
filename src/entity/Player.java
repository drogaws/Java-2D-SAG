package entity;

import java.awt.Graphics2D;
import java.util.List;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {
    GamePanel gp;
    KeyHandler keyH;
    public final int screenX;
    public final int screenY;
    public boolean hasBowl = false;
    boolean canPressE = true;
    boolean canPressSpace = true;
    
    public boolean takesOrder = false;
    public int points = 0;
    public List<String> playerBowl = new ArrayList<>();

    

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        hasBowl = false;
        screenX = gp.screenWidth/2 - (gp.tileSize/2);
        screenY = gp.screenHeight/2 - (gp.tileSize/2);

        // Collision box
        solidArea = new Rectangle();
        solidArea.x = gp.tileSize / 4;
        solidArea.y = gp.tileSize / 2;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.height = gp.tileSize / 2;
        solidArea.width = gp.tileSize / 2;

        setDefaultValues();
        getPlayerImage();
    }
    
    public void setDefaultValues() {
        worldX = gp.tileSize * 14; // Starting POS
        worldY = gp.tileSize * 20; // ^  ^   ^   ^
        speed = 3;
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
        int objIndex;
        collisionOn = false;
        takesOrder = false;
        gp.cChecker.checkTile(this);
        objIndex = gp.cChecker.checkObject(this, true);
        //pickUpObject(objIndex);
        
        // UP
        if (keyH.upPressed) {
            direction = "up";
            collisionOn = false;
            gp.cChecker.checkTile(this);
            objIndex = gp.cChecker.checkObject(this, true);
            if (!collisionOn) {
                worldY -= speed;
            }
        }
    
        // DOWN
        if (keyH.downPressed) {
            direction = "down";
            collisionOn = false;
            gp.cChecker.checkTile(this);
            objIndex = gp.cChecker.checkObject(this, true);
            if (!collisionOn) {
                worldY += speed;
            }
        }
    
        // LEFT
        if (keyH.leftPressed) {
            direction = "left";
            collisionOn = false;
            gp.cChecker.checkTile(this);
            objIndex = gp.cChecker.checkObject(this, true);
            if (!collisionOn) {
                worldX -= speed;
            } else {
                if (keyH.upPressed) {
                    direction = "up";
                }
                if (keyH.downPressed) {
                    direction = "down";
                }
            }
        }
    
        // RIGHT
        if (keyH.rightPressed) {
            direction = "right";
            collisionOn = false;
            gp.cChecker.checkTile(this);
            objIndex = gp.cChecker.checkObject(this, true);
            if (!collisionOn) {
                worldX += speed;
            } else {
                if (keyH.upPressed) {
                    direction = "up";
                }
                if (keyH.downPressed) {
                    direction = "down";
                }
            }
        }


        // Extra Speed Logic
        if (keyH.sideWalking) {
            speed = 2;
        }
        if (!keyH.sideWalking) {
            speed = 3;
        }

        
        

        // E
        if (canPressE && keyH.ePressed) {
            
            canPressE = false;
            interactWithObject(objIndex);
        }
        if(keyH.ePressed == false) {
            canPressE = true;
        }
        
        // SPACE
        if (canPressSpace && keyH.spacePressed) {
             
            canPressSpace = false;
            gp.skillCheck.handleKeyPress();
        }
        if(keyH.spacePressed == false) {
            canPressSpace = true;
        }
        
    
        // Animation
        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            spriteCounter++;
            if (spriteCounter > 24) {
                if (spriteNum == 1) {
                    spriteNum = 2;
                } 
                else if(spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }
    
    public void interactWithObject(int i) {

        if(i != 999) {

            String objectName = gp.obj[i].name;

            switch(objectName) {
                case "BowlTable":

                    hasBowl = true;
                    
                    
                    System.out.println(hasBowl);
                    
                    break;
                case "LineBuilder":
                    gp.skillCheck.builderSkillCheck();
                    
                    
                    break;

                case "LineFinisher":
                    gp.skillCheck.finisherSkillCheck();

                    break;
                
                case "Window":
                    gp.skillCheck.turnInSalad();
                    break;

                case "POS":
                    gp.orderGenerator.generateOrder();    

                    
                    break;
                
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
