package fi.javalabra.logic;

public class Paddle {
    
    private int height;
    private int width;
    
    private int x;
    private int y;
    
    public Paddle(int height, int width, int x, int y) {
        this.height = height;
        this.width = width;
        
        this.x = x; 
        this.y = y;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
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
