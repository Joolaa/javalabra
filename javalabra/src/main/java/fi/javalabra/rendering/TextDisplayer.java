package fi.javalabra.rendering;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 * This class is for displaying messages for the player.
 * @author jola
 */
public class TextDisplayer extends JPanel {
    
    private static Font FONT;
    private String string;
    
    /**
     * Construct an instance of the text displayer
     * @param string the text to display
     */
    public TextDisplayer(String string) {
        
        super.setBackground(Color.BLACK);
        FONT = new Font("Serif", Font.PLAIN, 20);
        this.string = string;
    }
    
    /**
     * Draw the text represented by the string-field.
     * @param graphics the object which is used for drawing
     * @param x the X-coordinate of the first character
     * @param y the Y-coordinate of the first character
     */
    public void drawText(Graphics graphics, int x, int y) {
        
        Graphics2D graphics2D = (Graphics2D) graphics;
        
        graphics2D.setPaint(Color.WHITE);
        graphics2D.setFont(FONT);
        
        String[] lines = string.split("\n");
        for(int i = 0; i < lines.length; i++) {
            graphics2D.drawString(lines[i], x / 4, y / 4 +
                    i * (graphics2D.getFontMetrics().getHeight()));
        }
    }
    
    /**
     * Check whether the text displayer has a string to display
     * @return true if there is a string to display
     */
    public boolean hasString() {
        
        if(string == null || string.isEmpty())
            return false;
        return true;
    }
    
    /**
     * Set the message to display
     * @param string the string to be displayed
     */
    public void setString(String string) {
        
        this.string = string;
    }
    
    /**
     * Nullify the string, hasString() will return false after calling this
     */
    public void nullifyString() {
        
        this.string = null;
    }
    
}
