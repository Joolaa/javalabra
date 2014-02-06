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
    
    public KeyboardControls() {
        
        this.paddleMovesLeft = false;
        this.paddleMovesRight = false;
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
        
        switch(e.getKeyCode()) {
            
            case(KeyEvent.VK_LEFT):
                paddleMovesLeft = true;
                break;
                
            case(KeyEvent.VK_RIGHT):
                paddleMovesRight = true;
                break;
        }
    }
    
    @Override
    public void keyReleased(KeyEvent e) {
        
        switch(e.getKeyCode()) {
            
            case(KeyEvent.VK_LEFT):
                paddleMovesLeft = false;
                break;
                
            case(KeyEvent.VK_RIGHT):
                paddleMovesRight = false;
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
}
