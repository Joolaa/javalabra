package fi.javalabra.logic;

/**
 * This class is mainly responsible for holding data associated with 
 * the paddle and collision detection
 * @author jola
 */
public class Paddle {
    
    private int height;
    private int width;
    
    private int x;
    private int y;
   
    /**
     * Constructor for a paddle instance
     * @param height height of the paddle
     * @param width width of the paddle
     * @param x X-coordinate of the upper-left corner of the paddle
     * @param y Y-coordinate of the upper-left corner of the paddle
     */
    public Paddle(int height, int width, int x, int y) {
        this.height = height;
        this.width = width;
        
        this.x = x; 
        this.y = y;
    }
    
    /**
     * Get the height of the paddle
     * @return the height of the paddle
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Get the width of the paddle
     * @return the width of the paddle
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Get the X-coordinate of the upper-left corner of the paddle
     * @return the coordinate
     */
    public int getX() {
        return x;
    }
    
    /**
     * Get the Y-coordinate of the upper-left corner of the paddle
     * @return the coordinate
     */
    public int getY() {
        return y;
    }
    
    /**
     * Set the X-coordinate of the upper-left corner of the paddle
     * @param x the new coordinate
     */
    public void setX(int x) {
        this.x = x;
    }
    
    /**
     * Set the Y-coordinate of the upper-left corner of the paddle
     * @param y the new coordinate
     */
    public void setY(int y) {
        this.y = y;
    }
    
    /**
     * Returns true if the ball is about to collide with the high edge
     * of the paddle.
     * @param prevBallLow coordinate before collision
     * @param ballLow coordinate of the low edge of the ball
     * @param ballLeft coordinate of the left edge of the ball
     * @param ballRight coordinate of the right edge of the ball
     * @return true if collision is about to happen, false otherwise
     */
    public boolean collidesWithHighEdge(int prevBallLow, int ballLow,
            int ballLeft, int ballRight) {
        
        if(prevBallLow > y + height)
            return false;
        
        if(ballLow >= y + 1) {
            if(ballRight > x && ballRight < x + width)
                return true;
            else if(ballLeft < x + width && ballLeft > x)
                return true;
        }
        
        return false;
    }
    
    
}
