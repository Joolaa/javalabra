package fi.javalabra.javalabra;

import fi.javalabra.gwindow.Gamewindow;
import javax.swing.SwingUtilities;

public class App {
    public static void main( String[] args ) {
        
        Gamewindow w = new Gamewindow();
        SwingUtilities.invokeLater(w);
        
    }
}
