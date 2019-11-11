import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.Rectangle;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.util.Random;
import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Image;
import java.util.LinkedList;
import java.awt.image.BufferedImage;
import java.awt.geom.AffineTransform;
import java.lang.Math;

public class Mob extends Sprite {

    private static final int XSIZE = 32;
    private static final int YSIZE = 32;
    private static int DX = -10;

    private JFrame window;
    //private Bat bat;
    private Random random;
    
    Texture tex = GameFrame.getInstance();
    // private int x;
    // private int y;
    private Image ballImage;

    AudioClip hitBatSound;
    AudioClip fallOffSound;

    Graphics2D g2;
    private Color backgroundColor;
    private final Dimension dimension;
    
    private Handler handler;
    float angle, angleChange;
    private BufferedImage spriteImage;
    private BufferedImage copy;

    public Mob (JFrame f,float x, float y, Handler handler, ObjectId id) {
        super(x,y,id);
        this.handler = handler;
        window = f;
        
        GraphicsDevice device;
        window = (JFrame)device.getFullScreenWindow();
        
        dimension = null;
        w = window.getWidth();
        
        dimension = window.getSize();
       
        backgroundColor = window.getBackground ();

        random = new Random();
        setPosition();                  // set initial position of ball
        
        angle = 10;             // set to 10 degrees
    angleChange = 10;           // change to angle each time in update()

    //spriteImage = loadImage("transparent.png");
        //ballImage = loadImage ("images/ball.gif");
        loadClips();
    }

    // public Image loadImage (String fileName) {
        // return new ImageIcon(fileName).getImage();
    // }

    public void setPosition () {
        y = random.nextInt(dimension.height - XSIZE);    // randomly generate x location
        x = random.nextInt(dimension.width -XSIZE);                      // set y to top of the panel
    }

    public void render (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor (Color.CYAN);
        BufferedImage dest = new BufferedImage (32, 32,
                            BufferedImage.TYPE_INT_ARGB);

        Graphics2D g2d = dest.createGraphics();

    AffineTransform origAT = g2d.getTransform(); 
                            // save original transform
  
            // rotate the coordinate system of the destination image around its center
    
    AffineTransform rotation = new AffineTransform(); 
        rotation.rotate(Math.toRadians(angle), 32/2, 32/2); 
        g2d.transform(rotation); 

        g2d.drawImage(tex.mob[0], 0,0, null);
        
        g2d.setTransform(origAT);           // restore original transform

        g2.drawImage(dest, (int)x, (int)y, XSIZE, YSIZE, null);

    g2d.dispose();
    
        //g2.drawImage(ballImage, (int)x, (int)y, XSIZE, YSIZE, null);
    }

    public void erase (Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor (backgroundColor);
        g2.fill (new Ellipse2D.Double (x, y, XSIZE, YSIZE));
    }

    public Rectangle getBounds() {
        return new Rectangle((int)x, (int)y, XSIZE, YSIZE);
    }

    // public boolean batHitsBall () {

        // Rectangle rectBall = getBounds();
        // Rectangle rectPlayer = player.getBounds();
        
        // if (rectBall.intersects(rectPlayer))
            // return true;
        // else
            // return false;
    // }

    public boolean isOffScreen () {

        if (x + YSIZE < 0)
            return true;
        else
            return false;
    }


    public void tick (LinkedList<Sprite> object) {

        if (!window.isVisible ()) return;
    
        // erase();                 // no need to erase with background image

        
        
        if(isOffScreen())
            DX = DX-10;

        x = x + DX;
        
        angle = angle + angleChange;

    if (angle >= 360)           // reset to 10 degrees if 360 degrees reached
       angle = 10;

        if (isOffScreen()) {
                             // play clip if ball falls out at bottom
                playClip (2);
            

            try {                   // take a rest if bat hits ball or
                Thread.sleep (10);        //   ball falls out of play.
            }
            catch (InterruptedException e) {};

            setPosition ();  
        }// re-set position of ball
    
        
    }

    public void loadClips() {

        try {

            hitBatSound = Applet.newAudioClip (
                    getClass().getResource("sounds/hitBat.au"));

            fallOffSound = Applet.newAudioClip (
                    getClass().getResource("sounds/fallOff.au"));

        }
        catch (Exception e) {
            System.out.println ("Error loading sound file: " + e);
        }

    }

    public void playClip (int index) {

        if (index == 1 && hitBatSound != null)
            hitBatSound.play();
        else
        if (index == 2 && fallOffSound != null)
            fallOffSound.play();

    }

}