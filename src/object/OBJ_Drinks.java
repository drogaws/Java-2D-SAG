package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Drinks extends SuperObject {
    
    public OBJ_Drinks() {
        solidArea = new Rectangle(0, 0, 64*scale, 32*scale);
        try {
            name = "Drinks";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Drinks.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
