package fi.javalabra.gwindow;

import javax.swing.JFrame;
import java.awt.Dimension;
import javax.swing.WindowConstants;

public class Gamewindow implements Runnable {
    
    private JFrame frame;
    
    public Gamewindow(){}
    
    @Override
    public void run() {
        frame = new JFrame("Breakout");
        frame.setPreferredSize(new Dimension(640, 480));
        
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        frame.pack();
        frame.setVisible(true);
    }
}
