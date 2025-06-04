package main;

import java.awt.*;

public class SkillCheck {

    GamePanel gp;
    boolean active = false;
    int centerX, centerY, radius;

    double angle = 0;
    double speed = 0.05;

    double checkStartOne = Math.toRadians(-25);
    double checkEndOne = checkStartOne + Math.toRadians(50);
    double checkStartTwo = Math.toRadians(-180);
    double checkEndTwo = checkStartTwo + Math.toRadians(-160);
    
    boolean resultGiven = false;


    public SkillCheck(GamePanel gp) {
        this.gp = gp;
        this.centerX = 300; // Center X position
        this.centerY = 300; // Center Y position
        this.radius = 60; // Radius of the skill check circle
    }

    public void start() {
        angle = 0;
        active = true;
        resultGiven = false;
    }

    public void update() {
        if (active) {
            angle += speed;
            if (angle > 2 * Math.PI) {
                angle -= 2 * Math.PI;
            }
        }
    }

    public void draw(Graphics2D g2) {
        if (!active) return;

        // Draw background bar
        g2.setColor(Color.DARK_GRAY);
        g2.fillOval(centerX - radius, centerY - radius, radius * 2, radius * 2);

        // Draw success zone
        int arcX = centerX - radius;
        int arcY = centerY - radius;
        int arcStart = (int) Math.toDegrees(checkStartOne - Math.PI / 2);
        int arcAngle = (int) Math.toDegrees(checkEndOne - checkStartOne);
        g2.setColor(Color.WHITE);
        g2.fillArc(arcX, arcY, radius * 2, radius * 2, arcStart, arcAngle);

        // Second success zone
        int arcStart1 = (int) Math.toDegrees(checkStartTwo - Math.PI / 2);
        int arcAngle1 = (int) Math.toDegrees(checkEndTwo - checkEndTwo);
        g2.setColor(Color.WHITE);
        g2.fillArc(arcX, arcY, radius * 2, radius * 2, arcStart1, arcAngle1);

        // Draw moving cursor
        int needleX = centerX + (int)(Math.cos(angle) * radius);
        int needleY = centerY + (int)(Math.sin(angle) * radius);    
        g2.setColor(Color.RED);
        g2.drawLine(centerX, centerY, needleX, needleY);
    }

    public void handleKeyPress() {
        if (!active || resultGiven) return;

        if (angle >= checkStartOne && angle <= checkEndOne) {
            System.out.println("✅ SUCCESS!");
            gp.player.hasBowl = true;
            gp.player.points += 10;

        } else {
            System.out.println("❌ FAIL!");
            gp.player.hasBowl = false;
        }
        resultGiven = true;
        active = false;
        
    }
}
