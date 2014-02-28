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
    private TextDisplayer textDisplayer;
    
    private boolean screenPaused;
    

    /**
     * Construct an instance of the renderer
     * @param blockDrawer drawer to handle drawing of the blocks
     * @param ballDrawer drawer to handle the drawing of the ball
     * @param paddleDrawer drawer to handle the drawing of the paddle
     * @param textDisplayer text displayer for pause messages
     */
    public Renderer(BlockDrawer blockDrawer, BallDrawer ballDrawer,
            PaddleDrawer paddleDrawer, TextDisplayer textDisplayer) {
        
        super.setBackground(Color.BLACK);
        this.blockDrawer = blockDrawer;
        this.ballDrawer = ballDrawer;
        this.paddleDrawer = paddleDrawer;
        
        this.textDisplayer = textDisplayer;
        
        this.screenPaused = false;
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        
        super.paintComponent(graphics);
        
        if(!screenPaused) {
            blockDrawer.drawBlocks(graphics);
            ballDrawer.drawBall(graphics);
            paddleDrawer.drawPaddle(graphics);
        }
        
        if(textDisplayer.hasString()) {
            textDisplayer.drawText(graphics, this.getWidth(), this.getHeight());
        }
    }
    
    /**
     * Set the flag for pausing the screen
     * @param b true to pause the screen
     */
    public void setScreenPaused(boolean b) {
        
        screenPaused = b;
    }
    
    /**
     * Get whether the screen is currently paused
     * @return true if the screen is paused
     */
    public boolean getScreenPaused() {
        
        return screenPaused;
    }
    
    /**
     * Get the text displayer object
     * @return the text displayer object
     */
    public TextDisplayer getTextDisplayer() {
        return textDisplayer;
    }
}
