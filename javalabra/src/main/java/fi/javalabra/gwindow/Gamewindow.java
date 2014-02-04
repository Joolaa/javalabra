package fi.javalabra.gwindow;

import java.awt.Container;
import java.awt.Dimension;
import javax.swing.WindowConstants;
import javax.swing.JFrame;
import fi.javalabra.rendering.Renderer;
import fi.javalabra.rendering.BlockDrawer;

public class Gamewindow implements Runnable {
    
    private JFrame frame;
    private Renderer renderer;
    
    public Gamewindow(Renderer renderer) {
        
        this.renderer = renderer;
    }
    
    @Override
    public void run() {
        frame = new JFrame("Breakout");
        frame.setPreferredSize(new Dimension(640, 480));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        createComponents(frame.getContentPane());
        
        frame.pack();
        frame.setVisible(true);
    }
    
    private void createComponents(Container container) {
        
        container.add(renderer);
        
    }
}
