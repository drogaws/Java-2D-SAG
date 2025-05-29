package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_LineBuilder extends SuperObject {
    
     public OBJ_LineBuilder() {
        solidArea = new Rectangle(0, 0, 48*scale, 96*scale);
        try {
            name = "LineBuilder";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/LineBuilder.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
