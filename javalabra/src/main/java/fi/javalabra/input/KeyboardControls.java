package fi.javalabra.input;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

/**
 * Class for handling keyboard input
 * @author jola
 */
public class KeyboardControls implements KeyListener {
    
    private boolean paddleMovesLeft;
    private boolean paddleMovesRight;
    private boolean spaceIsPressed;
    private boolean pIsPressed;
    private boolean anyKeyIsPressed;
    private boolean escIsPressed;
    
    public KeyboardControls() {
        
        this.paddleMovesLeft = false;
        this.paddleMovesRight = false;
        this.anyKeyIsPressed = false;
        this.pIsPressed = false;
        this.escIsPressed = false;
        this.spaceIsPressed = false;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        anyKeyIsPressed = true;
        
        switch(e.getKeyCode()) {
            
            case(KeyEvent.VK_LEFT):
                paddleMovesLeft = true;
                break;
                
            case(KeyEvent.VK_RIGHT):
                paddleMovesRight = true;
                break;
            
            case(KeyEvent.VK_P):
                pIsPressed = true;
                break;
             
            case(KeyEvent.VK_ESCAPE):
                escIsPressed = true;
                break;
            case(KeyEvent.VK_SPACE):
                spaceIsPressed = true;
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
        anyKeyIsPressed = false;
        
        switch(e.getKeyCode()) {
            
            case(KeyEvent.VK_LEFT):
                paddleMovesLeft = false;
                break;
                
            case(KeyEvent.VK_RIGHT):
                paddleMovesRight = false;
                break;
                
            case(KeyEvent.VK_P):
                pIsPressed = false;
                break;
                
            case(KeyEvent.VK_ESCAPE):
                escIsPressed = false;
                break;
            case(KeyEvent.VK_SPACE):
                spaceIsPressed = false;
                break;
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {}
    
    public boolean getMoveLeft() {
        return paddleMovesLeft;
    }
    
    public boolean getMoveRight() {
        return paddleMovesRight;
    }
    
    public boolean getPauseButton() {
        
        return escIsPressed || pIsPressed;
    }
    
    public boolean getAnyKeyIsPressed() {
        
        return anyKeyIsPressed;
    }
    
    public boolean getSpaceIsPressed() {
        
        return spaceIsPressed;
    }
}
