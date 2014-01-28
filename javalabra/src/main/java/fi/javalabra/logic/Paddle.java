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
    
}
