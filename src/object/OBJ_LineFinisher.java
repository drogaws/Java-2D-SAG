package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_LineFinisher extends SuperObject {
    
     public OBJ_LineFinisher() {
        solidArea = new Rectangle(0, 0, 48*scale, 96*scale);
        try {
            name = "LineFinisher";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/LineFinisher.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
