package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_BowlTable extends SuperObject {
    
    public OBJ_BowlTable() {
        solidArea = new Rectangle(0, 0, 64*scale, 32*scale);
        try {
            name = "BowlTable";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/BowlTable.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
