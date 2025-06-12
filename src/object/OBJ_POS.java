package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_POS extends SuperObject {
    
    public OBJ_POS() {
        solidArea = new Rectangle(0, 0, 48*scale, 32*scale);
        try {
            name = "POS";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/POS.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
