package fi.javalabra.logic;


public class Block {

    private int height;
    private int width;
    private int x;
    private int y;

    public Block(int height, int width, int x, int y) {
        
        this.height = height;
        this.width = width;
        this.x = x;
        this.y = y;
    }
    
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
}
