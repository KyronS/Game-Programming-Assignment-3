
/**
 * Write a description of class Camera here.
 *
 * @author (your name)
 * @version (a version number or a date)
 */
public class Camera
{
    private float x,y;
    
    public Camera(float x, float y){
        this.x = x;
        this.y = y;
    }
    
    public void tick(Sprite player){
        x--; //moves camera to the right
        
        //x = player.getX()+
    }
    
    public void setX(float x){
        this.x = x;
    }
    
    public void setY(float y){
        this.y = y;
    }
    
    public float getX(){
        return x;
    }
    
    public float getY(){
        return y;
    }
}
