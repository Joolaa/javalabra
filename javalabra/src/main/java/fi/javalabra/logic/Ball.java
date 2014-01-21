package fi.javalabra.logic;


public class Ball {
    
    private int height;
    private int width;
    
    private int x;
    private int y;
    
    private int dx;
    private int dy;
    
    /**
     * Height, width and location of the ball
     * @param height
     * @param width 
     * @param x horizontal location of the ball
     * @param y vertical location of the ball
     */
    public Ball(int height, int width, int x, int y) {
        
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
        
        this.dx = 0;
        this.dy = 0;
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
    
    /**
     * Get the velocity of the ball along the X-axis
     * @return Horizontal velocity
     */
    public int getDX() {
        return dx;
    }
    
    /**
     * Get the velocity of the ball along the Y-axis
     * @return Vertical velocity
     */
    public int getDY() {
        return dy;
    }
    
    /**
     * Get Y coordinate of high edge of the ball
     * @return Vertical coordinate
     */
    public int getHighEdgeY() {
        return y;
    }
    
    /**
     * Get Y coordinate of the low edge of the ball
     * @return Vertical coordinate
     */
    public int getLowEdgeY() {
        return y + height;
    }
    
    /**
     * Get X coordinate of the right edge of the ball
     * @return Horizontal coordinate
     */
    public int getRightEdgeX() {
        return x;
    }
    
    /**
     * Get X coordinate of the left edge of the ball
     * @return Horizontal coordinate
     */
    public int getLeftEdgeX() {
        return x + width;
    }
    
    public void setHeightAndWidth(int height, int width) {
        this.height = height;
        this.width = width;
    }
    
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    public void setVelocityVector(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    
    
}
