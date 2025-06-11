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
    boolean builderSideComplete = false;
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

    public void finisherSkillCheck() {
        active = false;

        if (!gp.player.hasBowl) {
            gp.ui.setMessage("You need a bowl to build a salad!");
            return;
        }
        if (gp.orderGenerator.order.isEmpty()) {
            gp.ui.setMessage("You have no order to build!");
            return;
        }
        if (!builderSideComplete) {
            gp.ui.setMessage("You must build salad before adding finisher toppings.");
            return;
        }
        premiumSkillCheck();
    }




    
    public void lettuceSkillCheck() {
        skillCheck = 0;
        if (buildWheel(gp.orderGenerator.lettuce, gp.orderGenerator.lettuceOrder)) {
            active = true;
        } else {
            classicSkillCheck();     // already complete → jump to next stage
        }
    }


    public void classicSkillCheck()  {
    skillCheck = 1;
    if (buildWheel(gp.orderGenerator.classics,
                   gp.orderGenerator.classicsOrder)) {
        active = true;
    } else {
        premiumSkillCheck();     // nothing left to add here
    }
}

public void premiumSkillCheck()  {
    skillCheck = 2;
    if (buildWheel(gp.orderGenerator.premiums,
                   gp.orderGenerator.premiumsOrder)) {
        active = true;
    } else {
        protineSkillCheck();
    }
}

public void protineSkillCheck()  {
    skillCheck = 3;
    if (buildWheel(gp.orderGenerator.protine,
                   gp.orderGenerator.protineOrder)) {
        active = true;
    } else {
        dressingSkillCheck();
    }
}

public void dressingSkillCheck() {
    skillCheck = 4;
    if (buildWheel(gp.orderGenerator.dressings,
                   gp.orderGenerator.dressingsOrder)) {
        active = true;
    } else {
        //finishSalad();           // every category done
    }
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

    // 0 ≤ normalizedAngle < 2π
    double twoPi = 2 * Math.PI;
    double normalizedAngle = (angle % twoPi + twoPi) % twoPi;

    int totalSections = 8;
    double sliceAngle = twoPi / totalSections;

    // which 45° slice is the cursor in?
    int hitSection = (int) (normalizedAngle / sliceAngle);   // 0‥7

    /* ───────────────────────────────────
       COLOURED SLICES ARE ODD (1,3,5,7)
       ─────────────────────────────────── */
    if (hitSection % 2 == 1) {                     // *** coloured slice ***
        int colorIndex = hitSection / 2;           // 0‥3

        if (colorIndex < ingredients.size()) {
            String selectedIngredient = ingredients.get(colorIndex);

            boolean isCorrect = switch (skillCheck) {
                case 0 -> gp.orderGenerator.lettuceOrder.contains(selectedIngredient);
                case 1 -> gp.orderGenerator.classicsOrder.contains(selectedIngredient);
                case 2 -> gp.orderGenerator.premiumsOrder.contains(selectedIngredient);
                case 3 -> gp.orderGenerator.protineOrder.contains(selectedIngredient);
                case 4 -> gp.orderGenerator.dressingsOrder.contains(selectedIngredient);
                default -> false;
            };

            if (isCorrect) {
                gp.player.playerBowl.add(selectedIngredient);
                gp.ui.setMessage("Correct! Added " + selectedIngredient);
                gp.player.points+=10;

                if (categoryFinished(skillCheck)) {      // all classics done?
                    switch (skillCheck) {                // move to the next stage
                        case 0 -> classicSkillCheck();
                        case 1 -> builderSideComplete();
                        case 2 -> protineSkillCheck();
                        case 3 -> dressingSkillCheck();
                        case 4 -> finishSalad();         // salad complete
                    }
                } else {
                    /* 👉 ***NOT finished yet***  — build a fresh wheel that
                        still guarantees at least one of the remaining classics.  */
                    switch (skillCheck) {
                        case 0 -> buildWheel(gp.orderGenerator.lettuce,
                                            gp.orderGenerator.lettuceOrder);
                        case 1 -> buildWheel(gp.orderGenerator.classics,
                                            gp.orderGenerator.classicsOrder);
                        case 2 -> buildWheel(gp.orderGenerator.premiums,
                                            gp.orderGenerator.premiumsOrder);
                        case 3 -> buildWheel(gp.orderGenerator.protine,
                                            gp.orderGenerator.protineOrder);
                        case 4 -> buildWheel(gp.orderGenerator.dressings,
                                            gp.orderGenerator.dressingsOrder);
                    }
                }
            } else {
                gp.player.points-=5;
                failSkillCheck();   // wrong ingredient
            }

        }
    } else {
        /* hit a black slice */
        gp.player.points-=10;
        failSkillCheck();
    }
}






    
                    
                    
    private void failSkillCheck() {
        active = false;
        gp.player.hasBowl = false;
        gp.player.playerBowl.clear();
        gp.ui.setMessage("Failed! Bowl dropped.");
    }






    private boolean buildWheel(List<String> categoryPool, List<String> orderList) {

        // 1.  Which items from this category are STILL missing?
        List<String> missing = new ArrayList<>(orderList);
        missing.removeAll(gp.player.playerBowl);

        if (missing.isEmpty()) {        // category already finished
            return false;
        }

        ingredients.clear();

        /* 2.  Guarantee at least ONE correct item.
            (Pick a random one so the game doesn’t become predictable.)    */
        String mustAppear = missing.get(gp.random.nextInt(missing.size()));
        ingredients.add(mustAppear);

        /* 3.  Fill the remaining slots with decoys,
            avoiding duplicates and shuffling for variety.                 */
        List<String> decoys = new ArrayList<>(categoryPool);
        decoys.removeAll(ingredients);          // no duplicates
        java.util.Collections.shuffle(decoys);

        while (ingredients.size() < 4 && !decoys.isEmpty()) {
            ingredients.add(decoys.remove(0));
        }

        java.util.Collections.shuffle(ingredients);   // random order on the wheel
        return true;
    }




    private boolean categoryFinished(int category) {
        return switch (category) {
            case 0 -> gp.player.playerBowl.containsAll(gp.orderGenerator.lettuceOrder);
            case 1 -> gp.player.playerBowl.containsAll(gp.orderGenerator.classicsOrder);
            case 2 -> gp.player.playerBowl.containsAll(gp.orderGenerator.premiumsOrder);
            case 3 -> gp.player.playerBowl.containsAll(gp.orderGenerator.protineOrder);
            case 4 -> gp.player.playerBowl.containsAll(gp.orderGenerator.dressingsOrder);
            default -> true;
        };
    }

    private void finishSalad() {
        gp.ui.setMessage("SALAD COMPLETE!");
        gp.player.points+=100;
        active = false;

    }

    private void builderSideComplete() {
        gp.ui.setMessage("Builder side completed!");
        gp.player.points+=50;
        active = false;
        builderSideComplete = true;
    }

}
