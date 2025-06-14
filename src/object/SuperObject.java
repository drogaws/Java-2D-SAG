package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;

public class SuperObject{
    
    public BufferedImage image;
    public String name;
    public int scale = GamePanel.SCALE;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0,0,16*scale,16*scale);;
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    
    public void draw(Graphics2D g2, GamePanel gp) {
        
        int screenX = worldX - gp.player.worldX + gp.player.screenX;
        int screenY = worldY - gp.player.worldY + gp.player.screenY;

        if(worldX + solidArea.width > gp.player.worldX - gp.player.screenX &&
            worldX - solidArea.width < gp.player.worldX + gp.player.screenX &&
            worldY + solidArea.height > gp.player.worldY - gp.player.screenY &&
            worldY - solidArea.height < gp.player.worldY + gp.player.screenY) {

            g2.drawImage(image, screenX, screenY, solidArea.width, solidArea.height, null);
        }
    }
}
