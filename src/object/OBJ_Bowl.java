package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Bowl extends SuperObject {
    
    public OBJ_Bowl() {
        try {
            name = "Bowl";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Bowl.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
