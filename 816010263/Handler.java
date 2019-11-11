
/**
 * Write a description of class Handler here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.util.LinkedList;
import java.awt.Graphics;

public class Handler
{
    public LinkedList<Sprite> object = new LinkedList<Sprite>();
    
    private Sprite tempObject;
    
    public void tick(){
        for(int i=0;i<object.size();i++){
            tempObject = object.get(i);
            tempObject.tick(object);
        }
    }
    
    public void render(Graphics g){
         for(int i=0;i<object.size();i++){
            tempObject = object.get(i);
            tempObject.render(g);
        }
    }
    
    public void addObject(Sprite object){
        this.object.add(object);
    }
    
    public void removeObject(Sprite object){
        this.object.remove(object);
    }
    
    // public void createLevel(){
        // for(int xx = 0; xx < Game.WIDTH+32; xx += 32)
            // addObject(new Block(xx, Game.HEIGHT-32, ObjectId.Block));
		
	// for(int yy = 0; yy < Game.WIDTH+32; yy += 32)
	   // addObject(new Block(Game.HEIGHT+166, yy, ObjectId.Block));
		
	// for(int zz = 0; zz < Game.WIDTH+32; zz += 32)
	   // addObject(new Block(Game.HEIGHT-609, zz, ObjectId.Block));
     // }   
    
}
