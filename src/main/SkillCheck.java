package main;

import java.awt.*;

public class SkillCheck {

    GamePanel gp;
    boolean active = false;
    int centerX, centerY, radius;

    double angle = 0;
    double speed = 0.025;
    
    boolean resultGiven = false;
    Font font = new Font("Arial", Font.BOLD, 20);


    public SkillCheck(GamePanel gp) {
        this.gp = gp;
        this.centerX = gp.screenWidth/2;
        this.centerY = gp.screenHeight/2;
        this.radius = 100;
    }

    public void start() {
        //lettuceCheck();
        //ingredientCheck();

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

        // Draw the sections of the arc
        int orderSize = gp.order.size();
        double anglePerSection = 2 * Math.PI / orderSize; 

        for (int i = 0; i < orderSize; i++) {
            // Calculate start and end angles for this section
            double startAngle = i * anglePerSection - Math.PI / 2;
            double endAngle = startAngle + anglePerSection;

            
    
            // Convert angles to degrees for fillArc
            int arcStart = (int) Math.toDegrees(startAngle);
            int arcAngle = (int) Math.toDegrees(endAngle - startAngle);
    
            // Assign a color (cycle through colors if there are more than 4 sections)
            Color[] colors = {Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE};
            g2.setColor(colors[i % colors.length]);
    
            // Draw the arc section
            g2.fillArc(arcX, arcY, diameter, diameter, arcStart, arcAngle);
    
            // Draw the ingredient text
            g2.setFont(font);
            g2.setColor(Color.WHITE);
    
            // Calculate text position (middle of the section)
            double textAngle = startAngle + anglePerSection / 2;
            int textX = centerX + (int) (Math.cos(textAngle) * (radius));
            int textY = centerY + (int) (Math.sin(textAngle) * (radius));
            g2.drawString(gp.order.get(i), textX, textY);
        }
    
        // Draw moving cursor
        int needleX = centerX + (int) (Math.cos(angle) * radius);
        int needleY = centerY + (int) (Math.sin(angle) * radius);
        g2.setColor(Color.BLACK);

        
        g2.setStroke(new BasicStroke(5));
        g2.drawLine(centerX, centerY, needleX, needleY);
    }

    public void handleKeyPress() {
        if (!active || resultGiven) return;

        int orderSize = gp.order.size();
        double anglePerSection = 2 * Math.PI / orderSize;

        double normalizedAngle = (angle + 2 * Math.PI) % (2 * Math.PI);

        for(int i = 0; i < orderSize; i++) {
            double startAngle = (i * anglePerSection - Math.PI / 2 + 2 * Math.PI) % (2 * Math.PI);
            double endAngle = (startAngle + anglePerSection) % (2 * Math.PI);

            if (startAngle < endAngle) {
                if (normalizedAngle >= startAngle && normalizedAngle < endAngle) {
                    System.out.println("HIT:" + gp.order.get(i));
                    gp.player.hasBowl = true;
                    gp.player.points += 10;
                    resultGiven = true;
                    active = false;
                    return;
                }
            } else {
                if (normalizedAngle >= startAngle || normalizedAngle < endAngle) {
                    System.out.println("HIT:" + gp.order.get(i));
                    gp.player.hasBowl = true;
                    gp.player.points += 10;
                    resultGiven = true;
                    active = false;
                    return;
                }
            }
        }

        System.out.println("MISS");
        gp.player.hasBowl = false;
        gp.player.points -= 5;
        resultGiven = true;
        active = false;
    }
}