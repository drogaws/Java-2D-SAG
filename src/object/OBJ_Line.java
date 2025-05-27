package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Line extends SuperObject {
    
     public OBJ_Line() {
        solidArea = new Rectangle(0, 0, 92, 48);
        try {
            name = "Line";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Line.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }

    public OBJ_Line(boolean x) {
        solidArea = new Rectangle(0, 0, 92, 48);
        try {
            name = "Line";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/FlipLine.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
