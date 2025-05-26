package object;

import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_SideStorage extends SuperObject{
    
    public OBJ_SideStorage() {
        try {
            name = "SideStorage";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/SideStorage.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }

    public OBJ_SideStorage(boolean x) {
        try {
            name = "SideStorage";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/BackStorage.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
