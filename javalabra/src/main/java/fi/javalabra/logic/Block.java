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

    /**
     * Construct a new block
     * @param height height of the block
     * @param width width of the block
     * @param x X-coordinate of the upper-left corner of the block
     * @param y Y-coordinate of the upper-left corner of the block
     */
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
    
    /**
     * Get the low edge Y-coordinate
     * @return the low edge Y-coordinate
     */
    public int getLowEdgeY() {
        return y + height;
    }
    
    /**
     * Get the high edge Y-coordinate
     * @return the hight edge Y-coordinate
     */
    public int getHighEdgeY() {
        return y;
    }
    
    /**
     * Get the left edge X-coordinate
     * @return the left edge X-coordinate
     */
    public int getLeftEdgeX() {
        return x;
    }
    
    /**
     * Get the right edge X-coordinate
     * @return the right edge X-coordinate
     */
    public int getRightEdgeX() {
        return x + width;
    }
    
    /**
     * Get the width of the block
     * @return width of the block
     */
    public int getWidth() {
        
        return width;
    }
    
    /**
     * Get the height of the block
     * @return height of the block
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Get the hue code of the block, this information is used
     * for drawing the block
     * @return the hue code of the block
     */
    public float getHueCode() {
        return hueCode;
    }
}
