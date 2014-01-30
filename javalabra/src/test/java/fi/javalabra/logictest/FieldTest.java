package fi.javalabra.logictest;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import fi.javalabra.logic.Ball;
import fi.javalabra.logic.Field;

public class FieldTest {
    
    Ball b;
    Field f;
    
    @Before
    public void setUp() {
        
        b = new Ball(3, 3, 3, 3);
        f = new Field(9, 9, b);
        
    }
    
    @Test
    public void constructsProperly() {
        
        assertEquals(f.getWidth(), 9);
        assertEquals(f.getHeight(), 9);
        
        Ball c = new Ball(3, 3, 3, 3);
        
        assertEquals(f.getBall(), c);
    }
    
    @Test
    public void ballMoves() {
        
        b.setVelocityVector(1, -1);
        
        f.moveBall();
        
        assertEquals(b.getX(), 4);
        assertEquals(b.getY(), 2);
    }
    
    @Test
    public void ballCollidesWithWall() {
        
        b.setVelocityVector(0, - 3);
        
        f.moveBall();
        
        assertEquals(b.getX(), 3);
        assertEquals(b.getY(), 0);
        
        b.setVelocityVector(1, -5);
        
        f.moveBall();
               
        assertEquals(b.getX(), 4);
        assertEquals(b.getY(), 5);
       
    }
    
    @Test
    public void ballCollidesWithWallFromAngle() {
        b.setVelocityVector(- 3, 0);
        
        f.moveBall();
        
        b.setVelocityVector(6, 6);
        
        f.moveBall();
        
        assertEquals(b.getX(), 6);
        assertEquals(b.getY(), 3);
    }
    
    @Test
    public void ballCollidesSeveralTimes() {
        

        b.setVelocityVector(- 11, 0);
        
        f.moveBall();
        

        assertEquals(b.getX(), 4);
        assertEquals(b.getY(), 3);
        
    }
    
    @Test
    public void ballCollidesSteepAngle() {
        System.out.println("------");
        b.setLocation(1, 1);
        
        b.setVelocityVector(-2, 4);
        
        f.moveBall();
        
        assertEquals(b.getX(), 1);
        assertEquals(b.getY(), 5);
    }
    
}
