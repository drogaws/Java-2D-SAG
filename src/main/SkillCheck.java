package main;

import java.awt.*;

public class SkillCheck {
    boolean active = false;
    int centerX, centerY, radius;

    double angle = 0;
    double speed = 0.05;
    double successStartOne = Math.PI / 4;
    double successEndOne = successStartOne + Math.toRadians(20);
    boolean resultGiven = false;


    public SkillCheck() {
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
        int arcStart = (int) Math.toDegrees(successStartOne);
        int arcAngle = (int) Math.toDegrees(successEndOne - successStartOne);
        g2.setColor(Color.GREEN);
        g2.fillArc(arcX, arcY, radius * 2, radius * 2, -arcStart, -arcAngle);

        //g2.setColor(Color.BLUE);
        //g2.fillRect(successStartTwo, y, itemOne, height);

        //g2.setColor(Color.PINK);
        //g2.fillRect(successStartThree, y, itemOne, height);

        // Draw moving cursor
        int needleX = centerX + (int)(Math.cos(angle) * radius);
        int needleY = centerY + (int)(Math.sin(angle) * radius);    
        g2.setColor(Color.RED);
        g2.drawLine(centerX, centerY, needleX, needleY);
    }

    public void handleKeyPress() {
        if (!active || resultGiven) return;

        if (angle >= successStartOne && angle <= successEndOne) {
            System.out.println("✅ SUCCESS!");
        } else {
            System.out.println("❌ FAIL!");
        }

        resultGiven = true;
        active = false;
    }
}
