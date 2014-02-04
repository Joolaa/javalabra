package fi.javalabra.rendering;

import javax.swing.JPanel;
import java.awt.Graphics;
import fi.javalabra.logic.Blocks;
import java.awt.Color;

public class Renderer extends JPanel{
    
    private BlockDrawer blockDrawer;
    

    public Renderer(BlockDrawer blockDrawer) {
        super.setBackground(Color.BLACK);
        this.blockDrawer = blockDrawer;
        
    }
    
    @Override
    protected void paintComponent(Graphics graphics) {
        
        super.paintComponent(graphics);
        blockDrawer.drawBlocks(graphics);
        //graphics.fillRect(200, 300, 10, 50);
    }
    
    
}
