package main;

import java.util.Random;

public class OrderGenerator {
    
    Random random = new Random();
    GamePanel gp;
    String[] ingreidents = {"Corn", "Tomatoe", "Beans", "Lettuce"};
    String order = "";
    int wantsItem;

    public OrderGenerator(GamePanel gp) {
        this.gp = gp;
        


    }

    public void generateOrder() {
        
        for(int i = 0; i < ingreidents.length; i++) {
            wantsItem = random.nextInt(2);
            if(wantsItem == 0) {
                order += ingreidents[i] + " ";
            }
            
        }
    }
}
