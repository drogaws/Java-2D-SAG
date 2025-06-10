package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.util.List;
import java.util.ArrayList;
public class SkillCheck {

    GamePanel gp;

    int skillCheck;

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




    
    public void lettuceSkillCheck() {
        
        skillCheck = 0;
        ingredients.clear();
        ingredients.addAll(gp.orderGenerator.lettuce);
        active = true;
        
    }

    public void classicSkillCheck() {
        
        skillCheck = 1;
        ingredients.clear();
        for(int i = 0; i < Math.min(4, gp.orderGenerator.classics.size()); i++) {
            ingredients.add(gp.orderGenerator.classics.get(i));

        }
        active = true;
    }
    
    public void premiumSkillCheck() {
        skillCheck = 2;
        ingredients.clear();
        // Get first 4 premiums from the order
        for(int i = 0; i < Math.min(4, gp.orderGenerator.premiumsOrder.size()); i++) {
            ingredients.add(gp.orderGenerator.premiumsOrder.get(i));
        }
        active = true;
    }

    public void protineSkillCheck() {
        skillCheck = 3;
        ingredients.clear();
        // Get first 4 proteins from the order
        for(int i = 0; i < Math.min(4, gp.orderGenerator.protine.size()); i++) {
            ingredients.add(gp.orderGenerator.protine.get(i));
        }
        active = true;
    }
    
    public void dressingSkillCheck() {
        skillCheck = 4;
        ingredients.clear();
        ingredients.addAll(gp.orderGenerator.dressingsOrder);
        active = true;
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
            double startAngleRad = (2* i + 1) * sliceAngle;
            double textAngle = startAngleRad + sliceAngle / 2;
            
            // Calculate text position
            int textX = centerX + (int)((radius * 1.2) * Math.cos(textAngle));
            int textY = centerY + (int)((radius * 1.1) * Math.sin(textAngle));
            
            // Save the current transform
            java.awt.geom.AffineTransform oldTransform = g2.getTransform();
            
            // Set text properties
            g2.setColor(Color.WHITE);
            g2.setFont(new Font("Arial Black", Font.BOLD, 18));
            
            // Get the text to draw\
            if(i < ingredients.size()) {
                String ingredientName = ingredients.get(i);
                double rotationAngle = textAngle;
                
                if (textY > centerY) {
                    rotationAngle += Math.PI;
                }
                g2.rotate(rotationAngle + Math.PI/2, textX, textY);
                
                // Center the text
                int textWidth = g2.getFontMetrics().stringWidth(ingredientName);
                g2.drawString(ingredientName, textX - textWidth/2, textY);
            
            }

            
           
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
        int hitSection = (int)((normalizedAngle +  sliceAngle/2) / sliceAngle) % totalSections; 
    
        if (hitSection % 2 == 0) {
            int colorIndex = (hitSection - 1) / 2;
            if(colorIndex < ingredients.size()) {
                String selectedIngredient = ingredients.get(colorIndex);
                
                boolean isCorrect = false;
                switch(skillCheck) {
                    case 0: // Lettuce Skill Check
                        isCorrect = gp.orderGenerator.lettuceOrder.contains(selectedIngredient);
                        break;
                    case 1: // Classic Skill Check
                        isCorrect = gp.orderGenerator.classicsOrder.contains(selectedIngredient);    
                        break;
                    case 2: // Premium Skill Check
                        isCorrect = gp.orderGenerator.premiumsOrder.contains(selectedIngredient);
                        break;
                    case 3: // Protine Skill Check
                        isCorrect = gp.orderGenerator.protineOrder.contains(selectedIngredient);
                        break;
                    case 4: // Dressing Skill Check
                        isCorrect = gp.orderGenerator.dressingsOrder.contains(selectedIngredient);
                        break;
                }

                if (isCorrect) {
                    gp.player.playerBowl.add(selectedIngredient);
                    gp.ui.setMessage("Correct! Added " + selectedIngredient);
                    
                    // Check completion based on current skill check
                    boolean completed = false;
                    switch(skillCheck) {
                        case 0: // Lettuce
                            completed = gp.player.playerBowl.containsAll(gp.orderGenerator.lettuceOrder);
                            if(completed) classicSkillCheck();
                            break;
                        case 1: // Classics
                            completed = gp.player.playerBowl.containsAll(gp.orderGenerator.classicsOrder);
                            if(completed) premiumSkillCheck();
                            break;
                        case 2: // Premium
                            completed = gp.player.playerBowl.containsAll(gp.orderGenerator.premiumsOrder);
                            if(completed) protineSkillCheck();
                            break;
                        case 3: // Protine
                            completed = gp.player.playerBowl.containsAll(gp.orderGenerator.protine);
                            if(completed) dressingSkillCheck();
                            break;
                        case 4: // Dressing
                            completed = gp.player.playerBowl.containsAll(gp.orderGenerator.dressingsOrder);
                            if(completed) {
                                gp.ui.setMessage("Salad complete!");
                                gp.player.points += 100;
                            }
                            break;
                    }
                } else {
                    // Incorrect
                    failSkillCheck();
                }
            }
        } else {
            //MISSED
            failSkillCheck();
        }
    
        
    }
    
    private void failSkillCheck() {
        active = false;
        gp.player.hasBowl = false;
        gp.player.playerBowl.clear();
        gp.ui.setMessage("Failed! Bowl dropped.");
    }
}