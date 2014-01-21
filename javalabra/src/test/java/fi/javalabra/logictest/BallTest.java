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
        assertEquals(b.getLowEdgeY(), b.getY() + b.getHeight());
        assertEquals(b.getRightEdgeX(), b.getX() + b.getWidth());
        assertEquals(b.getLeftEdgeX(), b.getX());
    }
    
}
