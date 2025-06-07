package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator {
    
    Random random = new Random();
    GamePanel gp;
    List<String> ingredients = new ArrayList<>();
    List<String> order = new ArrayList<>();

    int wantsItem;

    public OrderGenerator(GamePanel gp) {
        this.gp = gp;

        ingredients.add("Tomate");
        ingredients.add("Carrot");
        ingredients.add("Cucumber");
        ingredients.add("Red Onion");
        ingredients.add("Red Pepper");
        ingredients.add("Olive");

        
    }

    public List<String> generateOrder() {
        order.clear();
        order.add("Lettuce");
        for(int i = 0; i < ingredients.size(); i++) {
            wantsItem = random.nextInt(2);
            if(wantsItem == 0) {
                order.add(ingredients.get(i));
            }
            
        }
        System.out.println(order);
        return order;
    }
}
