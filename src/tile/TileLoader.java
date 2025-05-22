package tile;

import java.io.IOException;
import javax.imageio.ImageIO;

public class TileLoader {

    public static void loadTiles(Tile[] tile) {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/Floor.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/Grass.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/Sidewalk.png"));

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/LeftWall.png"));
            tile[3].collision = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/RightWall.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/BottomWall.png"));
            tile[5].collision = true;

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/TopWall.png"));
            tile[6].collision = true;

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/BLWall.png"));
            tile[7].collision = true;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/BRWall.png"));
            tile[8].collision = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/TLWall.png"));
            tile[9].collision = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/TRWall.png"));
            tile[10].collision = true;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/TLCWall.png"));
            tile[11].collision = true;

            tile[12] = new Tile();
            tile[12].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/TopSWall.png"));
            tile[12].collision = true;

            tile[13] = new Tile();
            tile[13].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/InnerVWall.png"));
            tile[13].collision = true;

            tile[14] = new Tile();
            tile[14].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/InnerHWall.png"));
            tile[14].collision = true;

            tile[15] = new Tile();
            tile[15].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/BLWalkIn.png"));
            tile[15].collision = true;

            tile[16] = new Tile();
            tile[16].image = ImageIO.read(TileLoader.class.getResourceAsStream("/res/tiles/BRWalkIn.png"));
            tile[16].collision = true;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
