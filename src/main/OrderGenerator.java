package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class OrderGenerator {
    
    Random random = new Random();
    GamePanel gp;

    List<String> lettuce = new ArrayList<>();
    List<String> classics = new ArrayList<>();
    List<String> premiums = new ArrayList<>();
    List<String> protine = new ArrayList<>();
    List<String> dressings = new ArrayList<>();

    List<String> lettuceOrder = new ArrayList<>();
    List<String> classicsOrder = new ArrayList<>();
    List<String> premiumsOrder = new ArrayList<>();
    List<String> protineOrder = new ArrayList<>();
    List<String> dressingsOrder = new ArrayList<>();

    List<String> order = new ArrayList<>();

    int wantsItem;

    public OrderGenerator(GamePanel gp) {
        this.gp = gp;
        resetIngreidents();

    }

    public void resetIngreidents() {
        lettuce.clear();
        classics.clear();
        premiums.clear();
        protine.clear();
        dressings.clear();

        addLettuce();
        addClassics();
        addPremiums();
        addProtines();
        addDressings();
    }
    public void resetOrder() {
        order.clear();
        lettuceOrder.clear();
        classicsOrder.clear();
        premiumsOrder.clear();
        protineOrder.clear();
        dressingsOrder.clear();
    }
    public void addLettuce() {
        lettuce.add("Romaine");
        lettuce.add("Mixed Greens");
        lettuce.add("Spinach");
        lettuce.add("Arugula");
    }
    public void addClassics() {
        classics.add("Tomato");
        classics.add("Cucumber");
        classics.add("Carrot");
        classics.add("Red Onion");
        classics.add("Red Pepper");
        classics.add("Croutons");
        classics.add("Tortilla Strips");
        classics.add("Corn");
        classics.add("Pico de Gallo");
        classics.add("Black Beans");
        classics.add("Poblano Pepper");
        classics.add("Red Cabbage");
        classics.add("Banna Pepper");
        classics.add("Pickled Onion");
        classics.add("Crainberries");
    }
    public void addPremiums() {
        premiums.add("Avocado");
        premiums.add("Feta Cheese");
        premiums.add("Bacon Bits");
        premiums.add("Egg Slices");
    }
    public void addProtines() {
        protine.add("Chicken");
        protine.add("Steak");
        protine.add("Tofu");
        protine.add("Buffalo Chicken");
    }
    public void addDressings() {
        dressings.add("Ranch");
        dressings.add("Caesar");
        dressings.add("Greek");
        dressings.add("BBQ Ranch");
    }


    public void generateOrder() {
        int itemNum;
        
        resetOrder();
        
        
        for(int i = 0; i < 5; i++) {
            
            switch (i) {
                case 0: // Lettuce
                    wantsItem = random.nextInt(3) + 1; // 1-3
                    for(int j = 0; j < wantsItem; j++) {
                        itemNum = random.nextInt(lettuce.size());
                        order.add(lettuce.get(itemNum));
                        lettuceOrder.add(lettuce.get(itemNum));
                        lettuce.remove(itemNum);
                    }
                    
                    break;
                case 1: // Classic
                    wantsItem = random.nextInt(5); // 0-4
                    if (wantsItem == 0) {
                        break;
                    }
                    for(int j = 0; j < wantsItem; j++) {
                        itemNum = random.nextInt(classics.size());
                        order.add(classics.get(itemNum));
                        classicsOrder.add(classics.get(itemNum));
                        classics.remove(itemNum);
                    }
                    break;
                case 2: // Premium
                    wantsItem = random.nextInt(3); // 0-2
                    if (wantsItem == 0) {
                        break;
                    }
                    for(int j = 0; j < wantsItem; j++) {
                        itemNum = random.nextInt(premiums.size());
                        order.add(premiums.get(itemNum));
                        premiums.remove(itemNum);
                    }
                    break;
                case 3: // Protein
                    order.add(protine.get(random.nextInt(protine.size()))); // 1
                    break;
                case 4: // Dressing
                    order.add(dressings.get(random.nextInt(dressings.size()))); // 1
                    break;
            }
        }
        System.out.println(order);
        resetIngreidents();
        return;
    }
}
