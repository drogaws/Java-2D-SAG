package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;

public class UI {

    GamePanel gp;
    Font arial_40, popUpFont, orderFont, headerFont, bodyFont;
    String currentPopUp = "";
    int messageTimer = 0;
    int s = GamePanel.SCALE;
    private final int MESSAGE_DURATION = 240;
    

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_40 = new Font("Arial", Font.BOLD, s * 15);
        popUpFont = new Font("Arial", Font.BOLD, s * 12);
        orderFont = new Font("Forte", Font.BOLD, s * 8);
        headerFont = orderFont.deriveFont(Font.BOLD, s * 5); 
        bodyFont   = new Font("Courier New", Font.BOLD, s * 4);
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




    public void carCount(Graphics2D g2) {
        g2.setFont(arial_40);
        g2.setColor(Color.WHITE);
        g2.drawString("Cars in Line: " + gp.orderGenerator.carsInLine, 25, 100);
    }



    
    public void displayOrder(Graphics2D g2) {

        if (gp.orderGenerator.order.isEmpty()) return;

        java.util.List<String> lines = new java.util.ArrayList<>();
        lines.add("Customer Order");
        lines.addAll(gp.orderGenerator.order);

        int padding   = 5 * s;   // was 2
        int lineGap   = 2 * s;   // was 1
        int cornerArc = 8 * s;   // was 2

        FontMetrics fmH = g2.getFontMetrics(headerFont);
        FontMetrics fmB = g2.getFontMetrics(bodyFont);

        int maxW = fmH.stringWidth(lines.get(0));
        for (int i = 1; i < lines.size(); i++)
            maxW = Math.max(maxW, fmB.stringWidth(lines.get(i)));

        int receiptW = maxW + padding * 2;
        int receiptH = padding                               // top
                    + fmH.getHeight()
                    + lineGap
                    + (fmB.getHeight() + lineGap) * (lines.size() - 1)
                    + padding;                              // bottom

        /* 6️⃣  Position: top‑right with a small margin */
        int margin = 2 * s;          // space from screen edges
        int x = gp.screenWidth  - margin - receiptW;
        int y = margin;

        /* 7️⃣  Paper background */
        Color paperCol = new Color(255, 255, 245);
        g2.setColor(paperCol);
        g2.fillRoundRect(x, y, receiptW, receiptH, cornerArc, cornerArc);

        /* Dashed border (scaled) */
        float strokeW = 0.3f * s;         // a hair thicker
        float dashLen = 1.8f * s;
        java.awt.Stroke old = g2.getStroke();
        g2.setColor(Color.GRAY);
        g2.setStroke(new BasicStroke(
                strokeW, BasicStroke.CAP_BUTT, BasicStroke.JOIN_MITER,
                10f, new float[]{dashLen}, 0f));
        g2.drawRoundRect(x, y, receiptW, receiptH, cornerArc, cornerArc);
        g2.setStroke(old);

        /* 8️⃣  Header */
        int curY = y + padding + fmH.getAscent();
        g2.setFont(headerFont);
        g2.setColor(Color.BLACK);
        g2.drawString(lines.get(0), x + padding, curY);

        /* Divider */
        curY += lineGap;
        g2.drawLine(x + padding, curY, x + receiptW - padding, curY);
        curY += lineGap;

        /* 9️⃣  Ingredients */
        g2.setFont(bodyFont);
        for (int i = 1; i < lines.size(); i++) {
            curY += fmB.getHeight();
            g2.drawString(lines.get(i), x + padding, curY);
            curY += lineGap;
        }
    }


}
