package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {
    
    public OBJ_Door() {
        try {
            name = "Door";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Door.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        //collision = true;
    }
}
