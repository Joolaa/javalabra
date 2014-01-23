package fi.javalabra.logic;


public class Field {
    
    private int height;
    private int width;
    
    private Ball ball;
    
    /**
     * Construct the playing field by specifying the height
     * and width of the field
     * @param height Height of the field
     * @param width Width of the field
     * @param ball Current ball on the field
     */
    public Field(int height, int width, Ball ball) {
        
        this.height = height;
        this.width = width;
        this.ball = ball;
        
    }
    
    public void moveBall() {
        moveBall(ball.getDX(), ball.getDY());
    }
    
    //under construction
    public void moveBall(int dx, int dy) {
        
        int futureLeft = ball.getLeftEdgeX() + dx;
        int futureRight = ball.getRightEdgeX() + dx;
        
        int futureHigh = ball.getHighEdgeY() + dy;
        int futureLow = ball.getLowEdgeY() + dy;
        
        int curdx = dx;
        int curdy = dy;
        
        if(futureHigh <= 0) {
            
            //reverse the remaining vertical velocity vector
            curdy = - futureHigh;
            //split the horizontal velocity vector with the same ratio as
            //the verical one
            curdx = ratioOfVectors(dx, Math.abs(ball.getHighEdgeY()),
                    Math.abs(futureHigh));
            
            //place the ball next to the wall
            ball.setLocation(ball.getX() + (dx - curdx), 0);
            
            //calculate rest of the movement of the ball
            moveBall(curdx, curdy);
            
        } else {
            
            ball.setLocation(ball.getX() + dx, ball.getY() + dy);
        }
        
    }
    
    //return a perpendicular vector which is split with this ratio
    private int ratioOfVectors(int splittee, int numerator, int denominator) {
        
        return splittee * denominator / (denominator + numerator);
    }
    
}
