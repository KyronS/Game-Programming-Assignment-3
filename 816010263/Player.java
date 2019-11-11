
/**
 * Write a description of class Player here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.Color;
import java.applet.Applet;
import java.applet.AudioClip;
import javax.swing.ImageIcon;
import java.awt.Image;

public class Player extends Sprite
{
    private float width = 48;
    private float height = 96;
    private float gravity = 38.5f;
    
    private final float maxSpeed = 20;
    
    private Handler handler;
    
    Graphics g;
    
    Texture tex = GameFrame.getInstance();
    //private Image playerImage;
    
    private Animation playerRun;
    
    
    AudioClip hitWallSound = null;
    
    public Player(float x, float y, Handler handler, ObjectId id){
        super(x,y,id);
        this.handler = handler;
        loadClips();
        
        playerRun = new Animation(10,tex.player[1],tex.player[2],tex.player[3],tex.player[4],tex.player[5],
        tex.player[6],tex.player[7],tex.player[8]);
        //playerImage = loadImage("images/zoro.jpg");
    }
    
    // public Image loadImage (String fileName) {
	// return new ImageIcon(fileName).getImage();
// }
	
    public void tick(LinkedList<Sprite> object){
        x += velX;
        y += velY;
        
        if(falling || jumping){
            velY += gravity;
            
            if(velY > maxSpeed)
                velY = maxSpeed;
        }
        
        Collision(object);
        
        playerRun.runAnimation();
    }
    
    public void Collision(LinkedList<Sprite> object){
        for(int i = 0; i <handler.object.size();i++){
            Sprite tempObject = handler.object.get(i);
            
            if(tempObject.getId() == ObjectId.Block){
                if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getY()+ (height/2);
                    velY = 0;
                    
                }
                
                if(getBounds().intersects(tempObject.getBounds())){
                    y = tempObject.getY()-height;
                    velY = 0;
                    falling = false;
                    jumping = false;
                }
                else{
                    falling = true;
                }
                
                if(getBoundsRight().intersects(tempObject.getBounds())){
                    x = tempObject.getX() - width;
                    playClip(1);
                    
                }
                
                // if(getBoundsLeft().intersects(tempObject.getBounds())){
                   // x = tempObject.getX()+width;
                   // playClip(1);
                    
                // }
             }
             
             if(tempObject.getId() == ObjectId.Mob){
                 if(getBoundsTop().intersects(tempObject.getBounds())){
                    y = tempObject.getY()+ (height/2);
                    velY = 0;
                    
                }
                
                if(getBoundsLeft().intersects(tempObject.getBounds())){
                   x = tempObject.getX()+39;
                   playClip(1);
                    
                }
                
                if(getBoundsRight().intersects(tempObject.getBounds())){
                    x = tempObject.getX() - width;
                    playClip(1);
                    
                }
             }
            
        }
    }
    
    
    
    public void render(Graphics g){
        g.setColor(Color.blue);
        if(velX != 0)
            playerRun.drawAnimation(g, (int)x,(int)y,64,96);
        else
            g.drawImage(tex.player[0], (int)x, (int)y,64,96,null);
        // Graphics2D g2 = (Graphics2D)g;
        // g2.drawImage (playerImage,(int) x,(int) y,(int) width, (int)height, null);
        // g2.setColor(Color.red);
        // g2.draw(getBounds());
        // g2.draw(getBoundsTop());
        // g2.draw(getBoundsLeft());
        // g2.draw(getBoundsRight());
    }
    
    public Rectangle getBounds(){
        
        return new Rectangle((int) ((int)x+(width/2)-(width/2)/2),(int) ((int)y+(height/2)),(int)width/2,(int)(height/2)-15);
    }
     
    public Rectangle getBoundsTop(){
        
        return new Rectangle((int) ((int)x+(width/2)-(width/2)/2),(int)y,(int)width/2,(int)height/2);
    }
    public Rectangle getBoundsRight(){
        
        return new Rectangle((int) ((int)x+width-5),(int)y+5,(int)5,(int)height-10);
    }
    
    public Rectangle getBoundsLeft(){
        
        return new Rectangle((int)x,(int)y+5,(int)5,(int)height-10);
    }
    
    public void loadClips() {

        try {
            hitWallSound = Applet.newAudioClip (
                        getClass().getResource("hitWall.au"));
        }
        catch (Exception e) {
            System.out.println ("Error loading sound file: " + e);
        }

    }

    public void playClip(int index) {

        if (index == 1 && hitWallSound != null)
            hitWallSound.play();
    }
    
    private void gameOverMessage(Graphics g) {

        Font font = new Font("SansSerif", Font.BOLD, 24);
        

        String msg = "Game Over. Thanks for playing!";

        int x = 500;
        int y = 500;

        g.setColor(Color.BLUE);
        g.setFont(font);
        g.drawString(msg, x, y);

    }
   
}
