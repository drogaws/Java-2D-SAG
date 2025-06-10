package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
public class SkillCheck {

    GamePanel gp;

    boolean allLettuceCollected;
    private static boolean correct = false;

    // Skill Check Variables
    boolean active = false;
    double angle = 0;
    double speed = 0.05;
    int centerX, centerY, radius, diameter;
    Color[] colors = {
        Color.RED, Color.YELLOW, Color.GREEN, Color.BLUE
    };
    List<String> ingredients = new ArrayList<>();

    public SkillCheck(GamePanel gp) {
        this.gp = gp;

        centerX = gp.screenWidth / 2;
        centerY = gp.screenHeight / 2;
        radius = 100;
        diameter = radius * 2;
    }

    
    public void builderSkillCheck() {
        active = false;
        ingredients.clear();
        correct = false;

        if (!gp.player.hasBowl) {
            gp.ui.setMessage("You need a bowl to build a salad!");
            return;
        }
        if (gp.orderGenerator.order.isEmpty()) {
            gp.ui.setMessage("You have no order to build!");
            return;
        }

        lettuceSkillCheck();
    }

    /*public void finisherSkillCheck() {
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
    }*/



    
    public void lettuceSkillCheck() {
        

        ingredients.addAll(gp.orderGenerator.lettuce);
        active = true;
        
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

        int totalSections = 8;
        double sliceAngle = 2 * Math.PI / totalSections;

        for (int i = 0; i < totalSections; i++) {
            double startAngleRad = i * sliceAngle;
            int startAngleDeg = (int) Math.toDegrees(-startAngleRad); // Java rotates clockwise from 3 o'clock
            int arcAngleDeg = (int) Math.toDegrees(-sliceAngle);

            if (i % 2 == 0) {
                g2.setColor(Color.BLACK); // black slices
            } else {
                g2.setColor(colors[(i / 2) % colors.length]);
            }

            g2.fillArc(centerX - radius, centerY - radius, diameter, diameter, startAngleDeg, arcAngleDeg);
                
        }

        for (int i = 0; i < totalSections/2; i++) {
            // Adjust the angle calculation to spread text evenly
            double startAngleRad = (i * 2 + 1) * sliceAngle;
            double textAngle = startAngleRad + sliceAngle / 2;
            
            // Calculate text position
            int textX = centerX + (int)((radius * 1.2) * Math.cos(textAngle));
            int textY = centerY + (int)((radius * 1.1) * Math.sin(textAngle));
            
            // Save the current transform
            java.awt.geom.AffineTransform oldTransform = g2.getTransform();
            
            // Set text properties
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial Black", Font.BOLD, 18));
            
            // Get the text to draw
            String ingredientName = ingredients.get(i);
            
            // Rotate the text - flip bottom text
            double rotationAngle;
            if (textY > centerY) {
                // Bottom half - rotate opposite direction
                rotationAngle = textAngle - Math.PI/2;
            } else {
                // Top half - normal rotation
                rotationAngle = textAngle + Math.PI/2;
            }
            g2.rotate(rotationAngle, textX, textY);
            
            // Center the text
            int textWidth = g2.getFontMetrics().stringWidth(ingredientName);
            g2.drawString(ingredientName, textX - textWidth/2, textY);
            
            // Restore the original transform
            g2.setTransform(oldTransform);
        }
        

        // Cursor
        int cursorX = centerX + (int)(radius * Math.cos(angle));
        int cursorY = centerY + (int)(radius * Math.sin(angle));
        g2.setColor(Color.WHITE);
        g2.drawLine(centerX, centerY, cursorX, cursorY);
    }




    public void handleKeyPress() {
        if (!active) return;
    
        double normalizedAngle = (angle + 2 * Math.PI) % (2 * Math.PI);
        int totalSections = 8;
        double sliceAngle = 2 * Math.PI / totalSections;
        int hitSection = (int)(normalizedAngle / sliceAngle);
    
        if (hitSection % 2 == 1) {
            int colorIndex = hitSection / 2;
            if(colorIndex < ingredients.size()) {
                String selectedIngredient = ingredients.get(colorIndex);
                
                // Check if the selected ingredient is in the order
                if (gp.orderGenerator.lettuceOrder.contains(selectedIngredient)) {
                    gp.player.playerBowl.add(selectedIngredient);
                    gp.ui.setMessage("Correct! Added " + selectedIngredient);
                    
                    // Check if all required lettuce is now in the bowl
                    
                    for (String lettuce : gp.orderGenerator.lettuceOrder) {
                        if (!gp.player.playerBowl.contains(lettuce)) {
                            allLettuceCollected = false;
                            break;
                        }
                    }
                    
                    if (allLettuceCollected) {
                        correct = true;
                        gp.ui.setMessage("All lettuce collected!");
                        classicSkillCheck();
                    }
                } else {
                    // Wrong ingredient selected
                    gp.player.hasBowl = false;
                    gp.player.playerBowl.clear();
                    correct = false;
                    gp.ui.setMessage("Wrong ingredient! Bowl dropped.");
                }
            }
        } else {
            // Hit black section
            gp.player.hasBowl = false;
            gp.player.playerBowl.clear();
            correct = false;
            gp.ui.setMessage("Missed! Bowl dropped.");
        }
    
        ingredients.clear();
        active = false;
    }
    






        
} 

