package fi.javalabra.gwindow;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import fi.javalabra.rendering.Renderer;

public class Gamewindow implements Runnable {
    
    private JFrame frame;
    private Renderer renderer;
    
    private int height;
    private int width;
    
    public Gamewindow(int height, int width, Renderer renderer) {
        
        this.renderer = renderer;
        
        this.height = height;
        this.width = width;
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
        
    }
}
