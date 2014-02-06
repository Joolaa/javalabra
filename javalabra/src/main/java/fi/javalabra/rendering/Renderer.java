package fi.javalabra.rendering;

import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;

/**
 * This class wraps up classes which draw individual elements and
 * is responsible for providing the visualization of the game situation
 * to the game window
 * @author jola
 */
public class Renderer extends JPanel{
    
    private BlockDrawer blockDrawer;
    private BallDrawer ballDrawer;
    private PaddleDrawer paddleDrawer;
    

    public Renderer(BlockDrawer blockDrawer, BallDrawer ballDrawer,
            PaddleDrawer paddleDrawer) {
        super.setBackground(Color.BLACK);
        this.blockDrawer = blockDrawer;
        this.ballDrawer = ballDrawer;
        this.paddleDrawer = paddleDrawer;
        
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        
        super.paintComponent(graphics);
        
        blockDrawer.drawBlocks(graphics);
        ballDrawer.drawBall(graphics);
        paddleDrawer.drawPaddle(graphics);
    }
    
    
}
