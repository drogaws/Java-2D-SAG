package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

public class UI {

    GamePanel gp;
    Font arial_40, popUpFont;
    String currentPopUp = "";
    int messageTimer = 0;
    int scale = GamePanel.SCALE;
    private final int MESSAGE_DURATION = 240;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.BOLD, scale * 15);
        popUpFont = new Font("Arial", Font.BOLD, scale * 12);
    }

    public void draw(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawString("Points: " + gp.player.points, 25, 50);

       
    }



    public void setMessage(String message) {
        currentPopUp = message;
        messageTimer = MESSAGE_DURATION;
    }

    public void drawMessages(Graphics2D g2) {
        if (messageTimer > 0) {
            g2.setFont(popUpFont);
            g2.setColor(Color.WHITE);
            g2.drawString(currentPopUp, gp.screenWidth / 2 - (300), gp.screenHeight / 2 + (250));
            messageTimer--;
        }
    }
}
