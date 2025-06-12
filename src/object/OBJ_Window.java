package object;

import java.awt.Rectangle;
import java.io.IOException;

import javax.imageio.ImageIO;

public class OBJ_Window extends SuperObject {
    
    
    public OBJ_Window() {
        solidArea = new Rectangle(0, 0, 16*scale, 32*scale);
        try {
            name = "Window";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/Window.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
        //collision = true;
    }
    public OBJ_Window(boolean x) {
        solidArea = new Rectangle(0, 0, 32*scale, 16*scale);
        try {
            name = "OloWindow";
            image = ImageIO.read(getClass().getResourceAsStream("/res/objects/OloWindow.png"));
        }catch(IOException e) {
            e.printStackTrace();
        }
    }
}
