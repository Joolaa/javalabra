package fi.javalabra.logic;


public class Field {
    
    private int height;
    private int width;
    
    private Ball ball;
    private Paddle paddle;
    
    /**
     * Construct the playing field by specifying the height
     * and width of the field
     * @param height Height of the field
     * @param width Width of the field
     * @param ball Current ball on the field
     */
    public Field(int height, int width, Ball ball, Paddle paddle) {
        
        this.height = height;
        this.width = width;
        this.ball = ball;
        this.paddle = paddle;
        
    }
    
    //Only for testing purposes
    public Field(int height, int width, Ball ball) {
        
        this(height, width, ball, null);
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
        
        if(futureHigh < 0) {
            
            //reverse the remaining vertical velocity vector
            curdy = - futureHigh;
            //split the horizontal velocity vector with the same ratio as
            //the verical one
            curdx = ratioOfVectors(dx, Math.abs(ball.getHighEdgeY()),
                    Math.abs(futureHigh));
            
            //place the ball next to the wall
            ball.setLocation(ball.getX() + (dx - curdx), 0);
            
        } else if(futureLeft < 0) {
            
            curdx = -futureLeft;
            curdy = ratioOfVectors(dy, Math.abs(ball.getLeftEdgeX()), 
                    Math.abs(futureLeft));
            
            ball.setLocation(0, ball.getY() + (dy - curdy));
            
        } else if(futureLow >= height) {
            
            curdy = - (futureLow - (height - 1));
            curdx = ratioOfVectors(dx, 
                    Math.abs((height - 1) - ball.getLowEdgeY()), 
                    Math.abs((height - 1) - futureLow));
            
            ball.setLocation(ball.getX() + (dx - curdx),
                    height - ball.getHeight());
            
            //System.out.println(ball.getX() + ", " + ball.getY());
            //System.out.println(dx + ", " + dy);
            //System.out.println(curdx + ", " + curdy);
            
        } else if(futureRight >= width) {
            
            curdx = - (futureRight - (width - 1));
            curdy = ratioOfVectors(dy,
                    Math.abs((width - 1) - ball.getRightEdgeX()), 
                    Math.abs((width - 1) - futureRight));
            
            ball.setLocation(width - ball.getWidth(),
                    ball.getY() + (dy - curdy));
            
            
        } else if(futureLow >= paddle.getY() && 
                ((futureRight > paddle.getX() &&
                futureRight < paddle.getX() + paddle.getWidth()) ||
                (futureLeft < paddle.getX() + paddle.getWidth() &&
                futureLeft > paddle.getX()))) {
            
            curdy = - (futureLow - paddle.getY());
            curdx = ratioOfVectors(dx, 
                    Math.abs(paddle.getY() - ball.getLowEdgeY()),
                    Math.abs(paddle.getY() - futureLow));
            ball.setLocation(ball.getX() + (dx - curdx),
                    paddle.getY() - ball.getHeight() - 1);
        } else { 
            
            ball.setLocation(ball.getX() + dx, ball.getY() + dy);
            
            return;
        }
        
        //calculate rest of the movement of the ball
        ball.setVelocityVector(curdx, curdy);
        moveBall();
        
    }
    
    //return a perpendicular vector which is split with this ratio
    private int ratioOfVectors(int splittee, int numerator, int denominator) {
        
        if(numerator + denominator == 0)
            return splittee;
        return splittee * denominator / (denominator + numerator);
    }
    
    /**
     * Move the paddle along the x-axis by the amount specified.
     * Can't move the paddle out of bounds.
     * @param dx negative value to move left, positive value to move right
     */
    public void movePaddle(int dx) {
        
        if(paddle.getX() + dx < 0)
            paddle.setX(0);
        else if(paddle.getX() + paddle.getWidth() + dx >= width)
            paddle.setX(width - paddle.getWidth());
        else
            paddle.setX(paddle.getX() + dx);
    }
    
    /**
     * Move the paddle to specified x-coordinate.
     * Can't move the paddle out of bounds
     * @param x the x-coordinate to move the paddle to
     */
    public void placePaddle(int x) {
        
        if(x < 0)
            paddle.setX(0);
        else if(x + paddle.getWidth() >= width)
            paddle.setX(width - paddle.getWidth());
        else
            paddle.setX(x);
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getWidth() {
        return width;
    }
    
    public Ball getBall() {
        return ball;
    }
    
}
