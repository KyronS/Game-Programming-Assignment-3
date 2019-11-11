
/**
 * Write a description of class KeyInput here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter
{
    Handler handler;
    
    public KeyInput(Handler handler){
        this.handler = handler;
    }
    
    public void keyPressed(KeyEvent e){
        int key = e.getKeyCode();
        Sprite tempObject1 = handler.object.get(0);
        for(int i=0; i<handler.object.size(); i++){
            Sprite tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ObjectId.Player){
                if (key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(-5);
                }

                if (key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(5);
                }
                
                if (key == KeyEvent.VK_UP && !tempObject.isJumping()) {
                    tempObject.setJumping(true);
                    tempObject.setVelY(-60);
                }

            }
        }
        if(key == KeyEvent.VK_ESCAPE){
            System.exit(1);
        }
        
    }
    
    public void keyReleased(KeyEvent e){
        int key = e.getKeyCode();
        
        for(int i=0; i<handler.object.size(); i++){
            Sprite tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ObjectId.Player){
                if (key == KeyEvent.VK_LEFT) {
                    tempObject.setVelX(0);
                }

                if (key == KeyEvent.VK_RIGHT) {
                    tempObject.setVelX(0);
                }

            }
        }
    }
    
    
}
