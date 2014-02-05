package fi.javalabra.javalabra;

import fi.javalabra.gwindow.Gamewindow;
import javax.swing.SwingUtilities;
import fi.javalabra.rendering.BlockDrawer;
import fi.javalabra.logic.Blocks;
import fi.javalabra.logic.Block;
import fi.javalabra.rendering.Renderer;
import fi.javalabra.logic.Ball;
import fi.javalabra.rendering.BallDrawer;

public class App {
    public static void main( String[] args ) {
        
        
        Blocks blocks = new Blocks();
        
        Block test1 = new Block(20, 30, 200, 300);
        Block test2 = new Block(10, 5, 50, 10);
        Block test3 = new Block(60, 3, 300, 100);
        
        blocks.insert(test3);
        blocks.insert(test1);
        blocks.insert(test2);
        
        Ball ball = new Ball(30, 30, 300, 300);
        
        BlockDrawer blockDrawer = new BlockDrawer(blocks);
        BallDrawer ballDrawer = new BallDrawer(ball);
        
        Renderer renderer = new Renderer(blockDrawer, ballDrawer);
        

        Gamewindow window = new Gamewindow(renderer);
        SwingUtilities.invokeLater(window);
        
        
    }
}
