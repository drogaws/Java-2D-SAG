package main;

import object.OBJ_Bowl;
import object.OBJ_Door;
import object.OBJ_SideStorage;

public class AssetSetter {
    
    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObject() {

        gp.obj[0] = new OBJ_Bowl();
        gp.obj[0].worldX = 24 * gp.tileSize;
        gp.obj[0].worldY = 22 * gp.tileSize;

        //Col 24
        //Line 22

        gp.obj[1] = new OBJ_Door();
        gp.obj[1].worldX = 2 * gp.tileSize;
        gp.obj[1].worldY = 27 * gp.tileSize;

        gp.obj[2] = new OBJ_SideStorage();
        gp.obj[2].worldX = 9 * gp.tileSize;
        gp.obj[2].worldY = 19 * gp.tileSize;

        gp.obj[3] = new OBJ_SideStorage();
        gp.obj[3].worldX = 9 * gp.tileSize;
        gp.obj[3].worldY = 18 * gp.tileSize;

        gp.obj[4] = new OBJ_SideStorage();
        gp.obj[4].worldX = 9 * gp.tileSize;
        gp.obj[4].worldY = 17 * gp.tileSize;

        gp.obj[5] = new OBJ_SideStorage();
        gp.obj[5].worldX = 9 * gp.tileSize;
        gp.obj[5].worldY = 16 * gp.tileSize;

        gp.obj[6] = new OBJ_SideStorage();
        gp.obj[6].worldX = 9 * gp.tileSize;
        gp.obj[6].worldY = 15 * gp.tileSize;

        gp.obj[7] = new OBJ_SideStorage();
        gp.obj[7].worldX = 9 * gp.tileSize;
        gp.obj[7].worldY = 14 * gp.tileSize;

        gp.obj[8] = new OBJ_SideStorage();
        gp.obj[8].worldX = 9 * gp.tileSize;
        gp.obj[8].worldY = 13 * gp.tileSize;

        gp.obj[9] = new OBJ_SideStorage();
        gp.obj[9].worldX = 9 * gp.tileSize;
        gp.obj[9].worldY = 12 * gp.tileSize;

        gp.obj[10] = new OBJ_SideStorage();
        gp.obj[10].worldX = 12 * gp.tileSize;
        gp.obj[10].worldY = 12 * gp.tileSize;

        gp.obj[11] = new OBJ_SideStorage();
        gp.obj[11].worldX = 12 * gp.tileSize;
        gp.obj[11].worldY = 13 * gp.tileSize;

        gp.obj[12] = new OBJ_SideStorage();
        gp.obj[12].worldX = 12 * gp.tileSize;
        gp.obj[12].worldY = 14 * gp.tileSize;

        gp.obj[13] = new OBJ_SideStorage();
        gp.obj[13].worldX = 12 * gp.tileSize;
        gp.obj[13].worldY = 15 * gp.tileSize;

        gp.obj[14] = new OBJ_SideStorage();
        gp.obj[14].worldX = 12 * gp.tileSize;
        gp.obj[14].worldY = 16 * gp.tileSize;

        gp.obj[15] = new OBJ_SideStorage();
        gp.obj[15].worldX = 12 * gp.tileSize;
        gp.obj[15].worldY = 17 * gp.tileSize;

        gp.obj[16] = new OBJ_SideStorage();
        gp.obj[16].worldX = 12 * gp.tileSize;
        gp.obj[16].worldY = 18 * gp.tileSize;

        gp.obj[17] = new OBJ_SideStorage();
        gp.obj[17].worldX = 12 * gp.tileSize;
        gp.obj[17].worldY = 19 * gp.tileSize;

        gp.obj[18] = new OBJ_SideStorage();
        gp.obj[18].worldX = 12 * gp.tileSize;
        gp.obj[18].worldY = 20 * gp.tileSize;

        gp.obj[19] = new OBJ_SideStorage();
        gp.obj[19].worldX = 12 * gp.tileSize;
        gp.obj[19].worldY = 21 * gp.tileSize;

        gp.obj[20] = new OBJ_SideStorage();
        gp.obj[20].worldX = 12 * gp.tileSize;
        gp.obj[20].worldY = 22 * gp.tileSize;

        gp.obj[21] = new OBJ_SideStorage("Direction");
        gp.obj[21].worldX = 10 * gp.tileSize;
        gp.obj[21].worldY = 11 * gp.tileSize;

        gp.obj[22] = new OBJ_SideStorage("Direction");
        gp.obj[22].worldX = 11 * gp.tileSize;
        gp.obj[22].worldY = 11 * gp.tileSize;
    }
}
