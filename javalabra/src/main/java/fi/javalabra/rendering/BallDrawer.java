package fi.javalabra.rendering;

import fi.javalabra.logic.Ball;
import java.awt.Color;
import java.awt.Graphics;

public class BallDrawer {
    
    private Ball ball;
    
    public BallDrawer(Ball ball) {
        
        this.ball = ball;
    }
    
    public void drawBall(Graphics graphics) {
        
        graphics.setColor(Color.getHSBColor(0.5f, 0.1f, 1.f));
        graphics.fillRect(ball.getX(), ball.getY(),
                ball.getWidth(), ball.getHeight());
    }
}
