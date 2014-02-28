package fi.javalabra.logic;

/**
 * Class which mainly holds the data associated with the ball
 * @author jola
 */
public class Ball {
    
    private int height;
    private int width;
    
    private int x;
    private int y;
    
    private int dx;
    private int dy;
    
    /**
     * Height, width and location of the ball
     * @param height height of the ball
     * @param width width of the ball
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
    
    /**
     * Get the height of the ball
     * @return height of the ball
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Get the width of the ball
     * @return width of the ball
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Get the X-coordinate of the upper-left corner of the ball
     * @return the X-coordinate of the upper-left corner of the ball
     */
    public int getX() {
        return x;
    }
    
    /**
     * Get the Y-coordinate of the upper-left corner of the ball
     * @return the Y-coordinate of the upper-left corner of the ball
     */
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
        return y + height - 1;
    }
    
    /**
     * Get X coordinate of the right edge of the ball
     * @return Horizontal coordinate
     */
    public int getRightEdgeX() {
        return x + width - 1;
    }
    
    /**
     * Get X coordinate of the left edge of the ball
     * @return Horizontal coordinate
     */
    public int getLeftEdgeX() {
        return x;
    }
    
    /**
     * Set the height and width of the ball
     * @param height new height of the ball
     * @param width new width of the ball
     */
    public void setHeightAndWidth(int height, int width) {
        this.height = height;
        this.width = width;
    }
    
    /**
     * Set a new location for the ball
     * @param x the new X-coordinate of the upper-left corner of the ball
     * @param y the new Y-coordinate of the upper-left corner of the ball
     */
    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
    /**
     * Set a new velocity vector for the ball
     * @param dx velocity along the X-axis
     * @param dy velocity along the Y-axis
     */
    public void setVelocityVector(int dx, int dy) {
        this.dx = dx;
        this.dy = dy;
    }
    
    @Override
    public boolean equals(Object o) {
        
        if(o == null || !o.getClass().equals(this.getClass()))
            return false;
        
        Ball b = (Ball) o;
        
        if(b.getX() == x && b.getY() == y && b.getHeight() == height &&
                b.getWidth() == width && b.getDX() == dx && b.getDY() == dy)
            return true;
        
        return false;
        
    }
    
    
}
