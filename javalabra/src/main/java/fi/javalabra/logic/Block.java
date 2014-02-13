package fi.javalabra.logic;

import java.util.Random;

/**
 * Class which mainly holds the data associated with blocks and 
 * collision detection
 * @author jola
 */
public class Block {

    private int height;
    private int width;
    private int x;
    private int y;
    
    private float hueCode;

    public Block(int height, int width, int x, int y) {
        
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        
        Random random = new Random();
        
        this.hueCode = random.nextFloat();
    }
    
    /**
     * Check for collision with the low edge of the block
     * @param prevBallHigh previous high edge Y-coordinate of the ball
     * @param ballHigh high edge Y-coordinate of the ball in the next frame
     * @param ballLeft left edge X-coordinate of the ball in the next frame
     * @param ballRight right edge X-coordinate of the ball in the next frame
     * @return true if collision happened, false otherwise
     */
    public boolean collidesWithLowEdge(int prevBallHigh, int ballHigh,
            int ballLeft, int ballRight) {
        
        if(prevBallHigh < y + height)
            return false;
        
        if(ballHigh < y + height) {
            if(ballRight >= x && ballRight < x + width)
                return true;
            else if(ballLeft < x + width && ballLeft >= x)
                return true;
        }
        
        return false;
    }
    
    /**
     * Check for collision with the high edge of the block
     * @param prevBallLow previous low edge Y-coordinate of the ball
     * @param ballLow low edge Y-coordinate of the ball in the next frame
     * @param ballLeft left edge X-coordinate of the ball in the next frame
     * @param ballRight right edge X-coordinate of the ball in the next frame
     * @return true if collision happened, false otherwise
     */
    public boolean collidesWithHighEdge(int prevBallLow, int ballLow, 
            int ballLeft, int ballRight) {
        
        if(prevBallLow >= y)
            return false;
        
        if(ballLow >= y) {
            if(ballRight >= x && ballRight < x + width)
                return true;
            else if(ballLeft < x + width && ballLeft >= x)
                return true;
        }
        
        return false;
    }
    
    /**
     * Check for collision with the left edge of the block
     * @param prevBallRight previous right edge X-coordinate of the ball
     * @param ballRight right edge X-coordinate of the ball in the next frame
     * @param ballLow low edge Y-coordinate of the ball in the next frame
     * @param ballHigh hight edge Y-coordinate of the ball in the next frame
     * @return true if collision happened, false otherwise
     */
    public boolean collidesWithLeftEdge(int prevBallRight, int ballRight,
            int ballLow, int ballHigh) {
        
        if(prevBallRight >= x)
            return false;
        
        if(ballRight >= x) {
            if(ballHigh >= y && ballHigh < y + height)
                return true;
            else if(ballLow >= y && ballLow < y + height)
                return true;
        }
        
        return false;
    }
    
    /**
     * Check for collision with the right edge of the block
     * @param prevBallLeft previous left edge X-coordinate of the ball
     * @param ballLeft left edge X-coordinate of the ball in the next frame
     * @param ballLow low edge Y-coordinate of the ball in the next frame
     * @param ballHigh hight edge Y-coordinate of the ball in the next frame
     * @return true if collision happened, false otherwise
     */
    public boolean collidesWithRightEdge(int prevBallLeft, int ballLeft, 
            int ballLow, int ballHigh) {
        
        if(prevBallLeft < x + width)
            return false;
        
        if(ballLeft < x + width) {            
            if(ballHigh >= y && ballHigh < y + height)
                return true;
            else if(ballLow >= y && ballLow < y + height)
                return true;
        }
        
        return false;
    }
    
    public int getLowEdgeY() {
        return y + height;
    }
    
    public int getHighEdgeY() {
        return y;
    }
    
    public int getLeftEdgeX() {
        return x;
    }
    
    public int getRightEdgeX() {
        return x + width;
    }
    
    public int getWidth() {
        
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public float getHueCode() {
        return hueCode;
    }
}
