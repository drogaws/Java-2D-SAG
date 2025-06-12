package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Table extends SuperObject {
    
     public OBJ_Table() {
        solidArea = new Rectangle(0, 0, 160*scale, 32*scale);
        try {
            name = "Table";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Table.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
