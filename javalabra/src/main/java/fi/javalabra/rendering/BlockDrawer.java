package fi.javalabra.rendering;

import fi.javalabra.logic.Blocks;
import fi.javalabra.logic.Block;
import java.awt.Color;
import java.awt.Graphics;

/**
 * This class handles drawing of the blocks
 * @author jola
 */
public class BlockDrawer {
    
    private Blocks blocks;
    
    public BlockDrawer(Blocks blocks) {
        
        this.blocks = blocks;
    }
    
    /**
     * Draw all the blocks as rectangles
     * @param graphics the object which is used for drawing
     */
    public void drawBlocks(Graphics graphics) {
        
        if(blocks.getLocked())
            return;
        
        if(blocks.size() < 1)
            return;
        
        blocks.rewind();
        
        Block curblock = blocks.getNext();
        
        int size = blocks.size();
        
        
        for(int i = 0; i < size && curblock != null; i++) {
            
            drawBlock(graphics, curblock);
            curblock = blocks.getNext();
            
        }
        
        blocks.rewind();
    }
    
    private void drawBlock(Graphics graphics, Block block) {
        graphics.setColor(Color.getHSBColor(block.getHueCode(),
                .1f, 1.f));
        graphics.fillRect(block.getLeftEdgeX(), block.getHighEdgeY(),
                block.getWidth(), block.getHeight());
    }
    
}
