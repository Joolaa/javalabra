package fi.javalabra.logictest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import fi.javalabra.logic.Blocks;
import fi.javalabra.logic.Block;

public class BlocksTest {
    
    Blocks bs;
    Block b0;
    Block b1;
    Block b2;
    
    @Before
    public void setUp() {
        
        bs = new Blocks();
        
        b0 = new Block(3, 5, 13, 11);
        b1 = new Block(5, 7, 0, 17);
        b2 = new Block(7, 13, 17, 29);
        
    }
    
    @Test
    public void constructsProperly() {
        
        assertEquals(null, bs.getNext());
        assertEquals(3, b0.getLowEdgeY() - b0.getHighEdgeY());
        assertEquals(7, b1.getRightEdgeX() - b1.getLeftEdgeX());
        assertEquals(0, b1.getLeftEdgeX());
        assertEquals(29, b2.getHighEdgeY());
        
    }
    
    @Test
    public void insertionWorks() {
        
        bs.insert(b0);
        assertEquals(11, bs.getPrev().getHighEdgeY());
        bs.rewind();
        bs.insert(b1);
        bs.getNext();
        assertEquals(11, bs.getNext().getHighEdgeY());
        assertEquals(0, bs.getPrev().getLeftEdgeX());
        bs.insert(b2);
        bs.rewind();
        
        assertEquals(bs.getNext().getHighEdgeY(), 29);
        assertEquals(bs.getNext().getLeftEdgeX(), 0);
        assertEquals(bs.getNext().getHighEdgeY(), 11);
    }
    
    @Test
    public void deletionWorks() {
        
        bs.insert(b0);
        bs.insert(b1);
        bs.insert(b2);
        
        bs.getNext();
        bs.getNext();
        
        bs.deleteCurrent();
        
        bs.rewind();
        
        assertEquals(bs.getNext(), b2);
        assertEquals(bs.getNext(), b0);
        
        bs.deleteCurrent();
        
        assertEquals(bs.getLast(), b2);
    }
    
    @Test
    public void lowCollision() {
        
        assertEquals(b0.collidesWithLowEdge(15, 11, 14, 16), true);
        assertEquals(b0.collidesWithLowEdge(13, 11, 14, 16), false);
    }
    
    @Test
    public void highCollision() {
        
        assertEquals(b1.collidesWithHighEdge(16, 18, 1, 3), true);
        assertEquals(b1.collidesWithHighEdge(16, 18, 7, 9), false);
    }
    
    @Test
    public void leftCollision() {
        
        assertEquals(b2.collidesWithLeftEdge(16, 17, 31, 29), true);
    }
    
}
