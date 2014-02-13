package fi.javalabra.rendering;

import fi.javalabra.logic.Ball;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This class handles drawing of the ball
 * @author jola
 */
public class BallDrawer {
    
    private Ball ball;
    
    public BallDrawer(Ball ball) {
        
        this.ball = ball;
    }
    
    /**
     * Draw the ball as a rectangle
     * @param graphics the object which is used for drawing
     */
    public void drawBall(Graphics graphics) {
        
        graphics.setColor(Color.getHSBColor(0.5f, 0.1f, 1.f));
        graphics.fillRect(ball.getX(), ball.getY(),
                ball.getWidth(), ball.getHeight());
    }
}
