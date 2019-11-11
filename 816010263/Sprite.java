
/**
 * Abstract class GameObject - write a description of the class here
 *
 * @author (your name here)
 * @version (version number or date here)
 */

import java.util.LinkedList;
import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Sprite
{
    protected float x,y;
    protected ObjectId id;
    protected float velX = 0, velY = 0;
    protected boolean falling = true;
    protected boolean jumping = false;
    
    public Sprite(float x , float y, ObjectId id){
        this.x = x;
        this.y = y;
        this.id = id;
    }
    
    public abstract void tick(LinkedList<Sprite> object);
    public abstract void render(Graphics g);
    public abstract Rectangle getBounds();
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
    
    public float getVelX(){
        return velX;
    }
    
    public float getVelY(){
        return velY;
    }
    
    public void setVelX(float velX){
        this.velX = velX;
    }
    
    public void setVelY(float velY){
        this.velY = velY;
    }
    
    public boolean isFalling(){
        return falling;
    }
    
    public void setFalling(boolean falling){
        this.falling = falling;
    }
    
    public boolean isJumping(){
        return jumping;
    }
    
    public void setJumping(boolean jumping){
        this.jumping = jumping;
    }
    
    public ObjectId getId(){
        return id;
    }
    
}
