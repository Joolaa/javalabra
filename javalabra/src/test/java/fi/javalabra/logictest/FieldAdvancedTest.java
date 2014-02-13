package fi.javalabra.logictest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import fi.javalabra.logic.Field;
import fi.javalabra.logic.Ball;
import fi.javalabra.logic.Paddle;
import fi.javalabra.logic.Block;
import fi.javalabra.logic.Blocks;

public class FieldAdvancedTest {
    
    Field f;
    Ball b;
    Paddle p;
    
    Block b0;
    Block b1;
    
    Blocks bs;
    
    @Before
    public void setUp() {
        
        b = new Ball(2, 2, 5, 5);
        
        p = new Paddle(2, 4, 7, 8);
        
        b0 = new Block(2, 4, 6, 2);
        b1 = new Block(2, 4, 2, 2);
        
        bs = new Blocks();
        
        bs.insert(b0);
        bs.insert(b1);
        
        f = new Field(12, 12, b, p, bs);
        
    }
    
    @Test
    public void collidesWithBlock() {
       
        assertTrue(bs.getFirst() != null);
        assertTrue(bs.getLast() != null);
        
        assertEquals(bs.getNext(), b1);
        assertEquals(bs.getNext(), b0);
        bs.rewind();
        
        b.setVelocityVector(0, -3);
        
        f.moveBall();
        
        assertEquals(b.getY(), 6);
        //NOTE: assumes if ball hits two blocks simultaneously, only
        //one will be destroyed
        assertEquals(bs.size(), 1);
        assertEquals(bs.getFirst(), b0);
    }
    
    @Test
    public void movePaddleTest() {
        
        int prevloc = p.getX();
        
        f.movePaddle(-2);
        
        assertEquals(p.getX(), prevloc - 2);
        
        f.placePaddle(9999999);
        
        assertEquals(p.getX(), f.getWidth() - p.getWidth());
        
        f.placePaddle(-1);
        
        assertEquals(p.getX(), 0);
        f.movePaddle(-1);
        assertEquals(p.getX(), 0);
        f.movePaddle(9999);
        assertEquals(p.getX(), f.getWidth()- p.getWidth());
    }
    
    @Test
    public void gameEnds() {
        
        assertTrue(!f.getGameOver());
        
        b.setVelocityVector(0, 6);
        f.moveBall();
        
        assertTrue(f.getGameOver());
    }
    
}
