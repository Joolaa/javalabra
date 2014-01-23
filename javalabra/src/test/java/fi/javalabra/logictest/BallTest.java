package fi.javalabra.logictest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import fi.javalabra.logic.Ball;

public class BallTest {
    
    Ball b;
    
    @Before
    public void setUp() {
        b = new Ball(10, 11, 4, 5);
    }
    
    @Test
    public void constructsProperly() {
        assertEquals(b.getX(), 4);
        assertEquals(b.getY(), 5);
        assertEquals(b.getHeight(), 10);
        assertEquals(b.getWidth(), 11);
        assertEquals(b.getDX(), 0);
        assertEquals(b.getDY(), 0);
    }
    
    @Test
    public void correctDimensions() {
        assertEquals(b.getHighEdgeY(), b.getY());
        assertEquals(b.getLowEdgeY(), b.getY() + b.getHeight() - 1);
        assertEquals(b.getRightEdgeX(), b.getX() + b.getWidth() - 1);
        assertEquals(b.getLeftEdgeX(), b.getX());
        
        b.setHeightAndWidth(1, 9);
        
        assertEquals(b.getHeight(), 1);
        assertEquals(b.getWidth(), 9);
    }
    
    @Test
    public void equivalenceTestPositive() {
        
        Ball c = new Ball(10, 11, 4, 5);
        
        assertEquals(b, c);
        
        b.setVelocityVector(34, 7);
        c.setVelocityVector(34, 7);
        
        assertEquals(b, c);
    }
    
    @Test
    public void equivalenceTestNegative() {
        
        Ball c = new Ball(10, 11, 3, 5);
        
        assertFalse(c.equals(b));
        
        c.setLocation(4, 5);
        
        c.setVelocityVector(0, -1);
        
        assertFalse(c.equals(b));
        
        assertFalse(b.equals(null));
        
        assertFalse(b.equals(new int[]{1, 3}));
    }
    
}
