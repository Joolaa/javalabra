package fi.javalabra.rendering;

import fi.javalabra.logic.Paddle;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This class handles drawing of the paddle
 * @author jola
 */
public class PaddleDrawer {
    
    private Paddle paddle;
    
    public PaddleDrawer(Paddle paddle) {
        
        this.paddle = paddle;
    }
    
    public void drawPaddle(Graphics graphics) {
        
        graphics.setColor(Color.getHSBColor(0.66f, 0.15f, 1.f));
        graphics.fillRect(paddle.getX(), paddle.getY(),
                paddle.getWidth(), paddle.getHeight());
    }
    
}
