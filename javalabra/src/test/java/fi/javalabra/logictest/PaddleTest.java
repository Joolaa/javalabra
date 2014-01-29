package fi.javalabra.logictest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import fi.javalabra.logic.Paddle;

public class PaddleTest {
    
    Paddle p;
    
    @Before
    public void setUp() {
        
        p = new Paddle(5, 20, 70, 35);
    }
    
    @Test
    public void constructsProperly() {
        
        assertEquals(5, p.getHeight());
        assertEquals(20, p.getWidth());
        assertEquals(70, p.getX());
        assertEquals(35, p.getY());
    }
    
    @Test
    public void settersWork() {
        
        p.setX(7);
        assertEquals(7, p.getX());
        p.setY(13);
        assertEquals(13, p.getY());
    }
    
    @Test
    public void collisionWorks1() {
        
        assertEquals(p.collidesWithHighEdge(35, 89, 92), true);

    }
    
    @Test
    public void collisionWorks2() {
        
        assertEquals(p.collidesWithHighEdge(35, 65, 71), true);
        
        
    }
    
    @Test
    public void collisionWorks3() {
        
        assertEquals(p.collidesWithHighEdge(34, 75, 80), false);
        
        
    }
    
    @Test
    public void collisionWorks4() {
        
        assertEquals(p.collidesWithHighEdge(36, 65, 69), false);
    }
}
