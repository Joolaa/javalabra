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
    
    /**
     * The constructor initializes everything to false
     */
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
    
    /**
     * Check if the paddle should be moved left
     * @return True if the paddle should be moved left
     */
    public boolean getMoveLeft() {
        return paddleMovesLeft;
    }
    
    /**
     * Check if the paddle should be moved right
     * @return True if the paddle should be moved right
     */
    public boolean getMoveRight() {
        return paddleMovesRight;
    }
    
    /**
     * Check if p of esc was pressed
     * @return True if p or esc was pressed
     */
    public boolean getPauseButton() {
        
        return escIsPressed || pIsPressed;
    }
    
    /**
     * Check if any key was pressed
     * @return True if some key was pressed
     */
    public boolean getAnyKeyIsPressed() {
        
        return anyKeyIsPressed;
    }
    
    /**
     * Check if space was pressed
     * @return True if space was pressed
     */
    public boolean getSpaceIsPressed() {
        
        return spaceIsPressed;
    }
}
