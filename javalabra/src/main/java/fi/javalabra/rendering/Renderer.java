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
    
    public void setScreenPaused(boolean b) {
        
        screenPaused = b;
    }
    
    public boolean getScreenPaused() {
        
        return screenPaused;
    }
    
    public TextDisplayer getTextDisplayer() {
        return textDisplayer;
    }
}
