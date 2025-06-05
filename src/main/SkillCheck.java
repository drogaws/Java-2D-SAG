package main;

import java.awt.*;

public class SkillCheck {

    GamePanel gp;
    boolean active = false;
    int centerX, centerY, radius;

    double angle = 0;
    double speed = 0.05;

    double SC_Bottom = Math.toRadians(0);
    double SC_Right = Math.toRadians(90);
    double SC_Top = Math.toRadians(180);
    double SC_Left = Math.toRadians(270);
    double skillCheckLength = 45;
    double SC_BottomEnd = SC_Bottom + Math.toRadians(skillCheckLength);
    double SC_RightEnd = SC_Right + Math.toRadians(skillCheckLength);
    double SC_TopEnd = SC_Top + Math.toRadians(skillCheckLength);
    double SC_LeftEnd = SC_Left + Math.toRadians(skillCheckLength);
    
    boolean resultGiven = false;


    public SkillCheck(GamePanel gp) {
        this.gp = gp;
        this.centerX = gp.screenWidth/2; // Center X position
        this.centerY = gp.screenHeight/2; // Center Y position
        this.radius = 100; // Radius of the skill check circle
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

        int arcX = centerX - radius;
        int arcY = centerY - radius;
        int diameter = radius * 2;

        // Draw background bar
        g2.setColor(Color.DARK_GRAY);
        g2.fillOval(arcX, arcY, diameter, diameter);

        // Draw success zone

        // Bottom
        int arcStart1 = (int) Math.toDegrees(SC_Bottom - Math.PI / 2);
        int arcAngle1 = (int) Math.toDegrees(SC_BottomEnd - SC_Bottom);
        g2.setColor(Color.RED);
        g2.fillArc(arcX, arcY, diameter, diameter, arcStart1, arcAngle1);

        //g2.setFont("Arial", Font.BOLD, 40);
        g2.setColor(Color.WHITE);
        g2.drawString("Corn", centerX + 100, centerY);

        // Right
        int arcStart2 = (int) Math.toDegrees(SC_Right - Math.PI / 2);
        int arcAngle2 = (int) Math.toDegrees(SC_RightEnd - SC_Right);
        g2.setColor(Color.YELLOW);
        g2.fillArc(arcX, arcY, diameter, diameter, arcStart2, arcAngle2);

        // Top
        int arcStart3 = (int) Math.toDegrees(SC_Top - Math.PI / 2);
        int arcAngle3 = (int) Math.toDegrees(SC_TopEnd - SC_Top);
        g2.setColor(Color.GREEN);
        g2.fillArc(arcX, arcY, diameter, diameter, arcStart3, arcAngle3);

        // Left
        int arcStart4 = (int) Math.toDegrees(SC_Left - Math.PI / 2);
        int arcAngle4 = (int) Math.toDegrees(SC_LeftEnd - SC_Left);
        g2.setColor(Color.BLUE);
        g2.fillArc(arcX, arcY, diameter, diameter, arcStart4, arcAngle4);

        // Draw moving cursor
        int needleX = centerX + (int)(Math.cos(angle) * radius);
        int needleY = centerY + (int)(Math.sin(angle) * radius);    
        g2.setColor(Color.BLACK);
        g2.drawLine(centerX, centerY, needleX, needleY);
    }

    public void handleKeyPress() {
        if (!active || resultGiven) return;

        if (angle >= SC_Bottom && angle <= SC_BottomEnd) {
            System.out.println("BOTTOM!");
            gp.player.hasBowl = true;
            gp.player.points += 10;

        } else if (angle >= SC_Right && angle <= SC_RightEnd) {
            System.out.println("RIGHT!");
            gp.player.hasBowl = true;
            gp.player.points += 10;

        } else if (angle >= SC_Top && angle <= SC_TopEnd) {
            System.out.println("TOP!");
            gp.player.hasBowl = true;
            gp.player.points += 10;

        } else if (angle >= SC_Left && angle <= SC_LeftEnd) {
            System.out.println("LEFT!");
            gp.player.hasBowl = true;
            gp.player.points += 10;

        } else {
            System.out.println("❌ FAIL!");
            gp.player.hasBowl = false;
            gp.player.points -= 5;
        }
        resultGiven = true;
        active = false;
        
    }
}
