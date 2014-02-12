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
        assertEquals(bs.size(), 3);
        bs.deleteCurrent();
        assertEquals(bs.size(), 2);
        
        bs.rewind();
        
        assertEquals(bs.getNext(), b2);
        assertEquals(bs.getNext(), b0);
        
        bs.deleteCurrent();
        assertEquals(bs.size(), 1);
        assertEquals(bs.getLast(), b2);
    }
    
    @Test
    public void lowCollision() {
        
        assertTrue(b0.collidesWithLowEdge(14, 13, 13, 13));
        assertTrue(!b0.collidesWithLowEdge(13, 12, 13, 13));
        assertTrue(!b0.collidesWithLowEdge(14, 13, 12, 12));
    }
    /*
     *              height, width, x, y
        b0 = new Block(3, 5, 13, 11);
        b1 = new Block(5, 7, 0, 17);
        b2 = new Block(7, 13, 17, 29);
    */
    
    @Test
    public void highCollision() {
        
        assertTrue(b1.collidesWithHighEdge(16, 17, 6, 6));
        assertTrue(!b1.collidesWithHighEdge(17, 16, 6, 6));
        assertTrue(!b1.collidesWithHighEdge(16, 17, 7, 7));
    }
    
    @Test
    public void leftCollision() {
        
        assertTrue(b2.collidesWithLeftEdge(16, 17, 29, 29));
        assertTrue(!b2.collidesWithLeftEdge(16, 17, 28, 28));
        assertTrue(!b2.collidesWithLeftEdge(17, 18, 29, 29));
    }
    
    @Test
    public void rightCollision() {
        
        assertTrue(b0.collidesWithRightEdge(18, 17, 11, 11));
        assertTrue(!b0.collidesWithRightEdge(18, 17, 10, 10));
        assertTrue(!b0.collidesWithRightEdge(17, 18, 11, 11));
    }
    
    @Test
    
    public void length() {
        
        assertEquals(bs.size(), 0);
        
        bs.insert(b0);
        bs.insert(b1);
        
        assertEquals(bs.size(), 2);
    }
    
}
