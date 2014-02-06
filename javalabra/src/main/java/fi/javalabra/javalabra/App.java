package fi.javalabra.javalabra;

import fi.javalabra.gwindow.Gamewindow;
import javax.swing.SwingUtilities;

/*
import fi.javalabra.rendering.BlockDrawer;
import fi.javalabra.logic.Blocks;
import fi.javalabra.logic.Block;
import fi.javalabra.rendering.Renderer;
import fi.javalabra.logic.Ball;
import fi.javalabra.rendering.BallDrawer;
import fi.javalabra.logic.Paddle;
import fi.javalabra.rendering.PaddleDrawer;
import fi.javalabra.logic.Field;
*/

import fi.javalabra.game.Game;

public class App {
    
    public static void main( String[] args ) {
        
        /*
        Blocks blocks = new Blocks();
        
        
        Ball ball = new Ball(20, 20, 310, 300);
        
        Paddle paddle = new Paddle(20, 80, 280, 340);
        
        PaddleDrawer paddleDrawer = new PaddleDrawer(paddle);
        
        Field field = new Field(480, 640, ball, paddle, blocks);
        
        BlockDrawer blockDrawer = new BlockDrawer(blocks);
        BallDrawer ballDrawer = new BallDrawer(ball);
        
        Renderer renderer = new Renderer(blockDrawer, ballDrawer, paddleDrawer);
        

        Gamewindow window = new Gamewindow(480, 640, renderer);
        SwingUtilities.invokeLater(window);
        
        while(true) {
            update(renderer, field);

        }
        */
        
        Game game = new Game(480, 640);
        
        Gamewindow window = game.initGame();
        
        SwingUtilities.invokeLater(window);
        
        game.startGame(window);
    }
    
    /*
    public static void update(Renderer renderer, Field field) {
        
        field.moveBall();
        renderer.repaint();
        
        try {
            Thread.sleep(15);
        } catch(Exception e) {
            System.out.println(e);
        }
        
        
    }
    */
}
