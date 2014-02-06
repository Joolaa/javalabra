package fi.javalabra.gwindow;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import fi.javalabra.rendering.Renderer;
import fi.javalabra.input.KeyboardControls;

/**
 * Visual display window of the game
 * @author jola
 */
public class Gamewindow implements Runnable {
    
    private JFrame frame;
    private Renderer renderer;
    private KeyboardControls keyboardControls;
    
    private int height;
    private int width;
    
    public Gamewindow(int height, int width, Renderer renderer,
            KeyboardControls keyboardControls) {
        
        this.renderer = renderer;
        
        this.height = height;
        this.width = width;
        
        this.keyboardControls = keyboardControls;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Breakout");
        frame.setPreferredSize(new Dimension(width, height));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void createComponents(Container container) {
        
        container.add(renderer);
        
        frame.addKeyListener(keyboardControls);
        
    }
}
