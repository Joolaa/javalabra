package fi.javalabra.rendering;

import javax.swing.JPanel;
import java.awt.Graphics;
import fi.javalabra.logic.Blocks;
import java.awt.Color;

public class Renderer extends JPanel{
    
    private BlockDrawer blockDrawer;
    private BallDrawer ballDrawer;
    

    public Renderer(BlockDrawer blockDrawer, BallDrawer ballDrawer) {
        super.setBackground(Color.BLACK);
        this.blockDrawer = blockDrawer;
        this.ballDrawer = ballDrawer;
        
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        
        super.paintComponent(graphics);
        blockDrawer.drawBlocks(graphics);
        ballDrawer.drawBall(graphics);
        //graphics.fillRect(200, 300, 10, 50);
    }
    
    
}
