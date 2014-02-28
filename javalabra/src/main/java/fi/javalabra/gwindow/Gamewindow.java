package fi.javalabra.gwindow;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import fi.javalabra.rendering.Renderer;
import fi.javalabra.input.KeyboardControls;
import fi.javalabra.rendering.TextDisplayer;

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
    
    /**
     * Construct the game window for displaying the game state visually
     * @param height height in pixels
     * @param width width in pixels
     * @param renderer renderer instance to use for drawing
     * @param keyboardControls keyboard controls instance for input
     */
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
    
    /**
     * Specify the renderer instance and keyboard control instance for the
     * window
     * @param container the container to add the components to
     */
    private void createComponents(Container container) {
        
        container.add(renderer);
        
        frame.addKeyListener(keyboardControls);
        
    }
}
