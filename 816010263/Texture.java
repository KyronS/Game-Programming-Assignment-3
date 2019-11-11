/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author 816010263
 */

import java.awt.image.BufferedImage;

public class Texture {
    
    SpriteSheet bs,ps,ms;
    private BufferedImage block_sheet = null;
    private BufferedImage player_sheet = null;
    private BufferedImage mob_sheet = null;
    
    public BufferedImage[] block = new BufferedImage[2];
    public BufferedImage[] player = new BufferedImage[9];
    public BufferedImage[] mob = new BufferedImage[2];
    
    public Texture(){
        
        BufferedImageLoader loader = new BufferedImageLoader();
        
        try{
            block_sheet = loader.loadImage("images/block_sheet2.png");
            player_sheet = loader.loadImage("images/player_sheet.png");
            mob_sheet = loader.loadImage("images/block_sheet2.png");
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        bs = new SpriteSheet(block_sheet);
        ps = new SpriteSheet(player_sheet);
        ms = new SpriteSheet(mob_sheet);
        getTextures();
    }
    
    private void getTextures(){
        block[0] = bs.grabImage(10,1,32,32); //grass block
        block[1] = bs.grabImage(2,1,16,16); //stone block
        
        player[0] = ps.grabImage(1,1,46,50); //idle player
        player[1] = ps.grabImage(1,4,46,50); // running for player
        player[2] = ps.grabImage(2,4,46,50); // running for player
        player[3] = ps.grabImage(3,4,46,50); // running for player
        player[4] = ps.grabImage(4,4,46,50); // running for player
        player[5] = ps.grabImage(5,4,46,50); // running for player
        player[6] = ps.grabImage(6,4,46,50); // running for player
        player[7] = ps.grabImage(7,4,46,50); // running for player
        player[8] = ps.grabImage(8,4,46,50); // running for player
        
        mob[0] = ms.grabImage(8,14,32,32);
        mob[1] = ms.grabImage(8,16,32,32);
    }
    
}
