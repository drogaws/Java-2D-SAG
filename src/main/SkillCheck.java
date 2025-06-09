package main;

import java.awt.Color;
import java.awt.Graphics2D;
public class SkillCheck {

    GamePanel gp;

    private static boolean correct;

    // Skill Check Variables
    boolean active = false;
    double angle = 0;
    double speed = 0.05;
    int centerX, centerY, radius, diameter;
    Color[] colors = {
        Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE
    };
    int sectionCount = 4;
    double anglePerSection = 2 * Math.PI / sectionCount;
    double gapAngle = anglePerSection / 2;


    public SkillCheck(GamePanel gp) {
        this.gp = gp;

        centerX = gp.screenWidth / 2;
        centerY = gp.screenHeight / 2;
        radius = 100;
        diameter = radius * 2;
    }

    
    public void builderSkillCheck() {
        if (!gp.player.hasBowl) {
            gp.ui.setMessage("You need a bowl to build a salad!");
            return;
        }
        if (gp.order.isEmpty()) {
            gp.ui.setMessage("You have no order to build!");
            return;
        }

        active = true;
        correct = false;

        lettuceSkillCheck();
        if (correct) {
            classicSkillCheck();
        }
        
        return;
    }

    public void finisherSkillCheck() {
        if (!gp.player.hasBowl) {
            gp.ui.setMessage("You need a bowl to finish the salad!");
            return;
        }

        active = true;
        correct = false;

        premiumSkillCheck();
        if (correct) {
            dressingSkillCheck();
        }

        return;
    }



    public void lettuceSkillCheck() {
        
    }

    public void classicSkillCheck() {
        
    }

    public void premiumSkillCheck() {
        
    }

    public void dressingSkillCheck() {
        
    }        
    

    public void update() {
        if(!active) return;
        
        angle += speed;
        if (angle >= 2 * Math.PI) {
            angle = 0;
        }
    }

    public void draw(Graphics2D g2) {
        if(!active) return;

        // Base Circle
        g2.setColor(Color.BLACK);
        g2.fillOval(centerX - radius, centerY - radius, diameter, diameter);

        //Draw Sections
        for (int i = 0; i < sectionCount; i++) {

            // Math
            double startAngle = (i * Math.PI / 2) ;
            double endAngle = anglePerSection - gapAngle;

            // Math to Degrees
            int degreesStart = (int) Math.toDegrees(startAngle);
            int degreesEnd = (int) Math.toDegrees(endAngle);

            // Draw Arc
            g2.setColor(colors[i]);
            g2.fillArc(centerX - radius, centerY - radius, diameter, diameter, degreesStart, degreesEnd);
        }

        // Draw cursor
        int cursorX = centerX + (int) (radius * Math.cos(angle));
        int cursorY = centerY + (int) (radius * Math.sin(angle));
        g2.setColor(Color.WHITE);
        g2.drawLine(centerX, centerY, cursorX, cursorY);
    }


    public void handleKeyPress() {
        if (!active) return;

        double normalizedAngle = (angle + 2 * Math.PI) % (2 * Math.PI);

        //Draw Sections
        for (int i = 0; i < sectionCount; i++) {

            // Math * 
            double startAngle = (i * Math.PI / 2) ;
            double endAngle = startAngle + anglePerSection - gapAngle; 

            if(normalizedAngle >= startAngle && normalizedAngle < endAngle) {
                correct = true;
                active = false;
                gp.ui.setMessage("Skill Check Passed!");
                return;
            }
        }
        correct = false;
        active = false;
        gp.ui.setMessage("Skill Check Failed!");
        
    }
}