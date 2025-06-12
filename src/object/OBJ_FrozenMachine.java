package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_FrozenMachine extends SuperObject {
    
    public OBJ_FrozenMachine() {
        solidArea = new Rectangle(0, 0, 48*scale, 32*scale);
        try {
            name = "FrozenMachine";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/FrozenMachine.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
