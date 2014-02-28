package fi.javalabra.game;

import fi.javalabra.gwindow.Gamewindow;
import fi.javalabra.rendering.BlockDrawer;
import fi.javalabra.logic.Blocks;
import fi.javalabra.logic.Block;
import fi.javalabra.rendering.Renderer;
import fi.javalabra.logic.Ball;
import fi.javalabra.rendering.BallDrawer;
import fi.javalabra.logic.Paddle;
import fi.javalabra.rendering.PaddleDrawer;
import fi.javalabra.logic.Field;
import fi.javalabra.gwindow.Gamewindow;
import fi.javalabra.input.KeyboardControls;
import fi.javalabra.rendering.TextDisplayer;

/**
 * Class which wraps up different components of the system
 * @author jola
 */
public class Game {
    
    private int height;
    private int width;
    
    private Renderer renderer;
    
    private Field field;
    private Ball ball;
    private Blocks blocks;
    private Paddle paddle;
    
    private KeyboardControls keyboardControls;
    
    private long timeOfLastPause;
    
    /*
    public Game(int height, int width, Renderer renderer, Field field,
            Ball ball, Blocks blocks, Paddle paddle,
            KeyboardControls keyboardControls) {
        
        this.height = height;
        this.width = width;
        this.renderer = renderer;
        this.ball = ball;
        this.blocks = blocks;
        
        this.field = field;
        this.paddle = paddle;
        
        this.timeOfLastPause = -1;
        
        this.keyboardControls = keyboardControls;
        
    }
    
    public Game(int height, int width, Field field,
            Ball ball, Blocks blocks, Paddle paddle) {
        
        this(height, width, new Renderer(new BlockDrawer(blocks),
                new BallDrawer(ball), new PaddleDrawer(paddle), 
                new TextDisplayer(null)),
                field, ball, blocks, paddle, new KeyboardControls());
    }
    */
    
    /**
     * Constuct the game. Dimensions are in pixels
     * @param height Height of the game field
     * @param width  Width of the game field
     */
    public Game(int height, int width) {
        
        this.height = height;
        this.width = width;
        
        initializeDefault(height, width);
    }
    
    /**
     * Default initialization routine for the game
     * @param height height of the field
     * @param width width of the field
     */
    private void initializeDefault(int height, int width) {
        
        field = new Field(height, width,
                new Ball(height / 24, height/ 24, (width / 2) - (height / 48),
                (height / 24) * 15),
                new Paddle(height / 24, width / 8, (width / 2) - (width / 16),
                (height / 24) * 20),
                new Blocks());
        
        ball = field.getBall();
        
        paddle = field.getPaddle();
        
        blocks = field.getBlocks();
        
        renderer = new Renderer(new BlockDrawer(blocks),
                new BallDrawer(ball), new PaddleDrawer(paddle),
                new TextDisplayer(null));
        
        keyboardControls = new KeyboardControls();
    }
    
    /**
     * Initialize the game properly.
     * @return the game window
     */
    
    public Gamewindow initGame() {
        
        
        resetGame();
        
        Gamewindow window = new Gamewindow(height, width, renderer, 
                keyboardControls);
        
        field.setRandomizeVelocity(true);
        
        return window;
           
    }
    
    /**
     * Cleanup and initialization of new blocks
     */
    private void resetGame() {
        
        initBlocks(blocks);
        
        ball.setVelocityVector(0, -1);
        ball.setLocation((width / 2) - (height / 48), (height / 24) * 15);
        
        paddle.setX((width / 2) - (width / 16));
        paddle.setY((height / 24) * 20);
        
        field.setGameOver(false);
    }
    
    /**
     * Display a message and pause the game
     * @param message Message to display
     */
    private void displayPausedMessage(String message) {
        
        renderer.setScreenPaused(true);
        
        renderer.getTextDisplayer().setString(message);
        
        renderer.repaint();
        
        delay(1000);
        
        while(!keyboardControls.getAnyKeyIsPressed()) {
            
            delay(30);
        }
        
        renderer.getTextDisplayer().nullifyString();
        
        renderer.setScreenPaused(false);
    }
    
    /**
     * Pause the game and display the pause message
     */
    private void pauseGame() {
        
        if(timeOfLastPause != -1 &&
                System.nanoTime() - timeOfLastPause < 500000000)
            return;
        
        displayPausedMessage("Paused. \nPress <esc> or p to continue.");
        System.gc();
        
        while(!keyboardControls.getPauseButton()) {
            
            delay(30);
        }
        
        timeOfLastPause = System.nanoTime();
    }
    
    /**
     * Display the game start message
     */
    private void displayStartMessage() {
        displayPausedMessage("Press any key to start game. \n" + 
                "Press p or <esc> during the game to pause. \n" +
                "Press <left> or <right> to move. \n" + 
                "Hold <space> to move faster.");
    }
    
    /**
     * Display a message when the player is defeated
     */
    private void displayDefeatMessage() {
        
        displayPausedMessage("You have been defeated! \n" + 
                "(Press any key to start a new game)");
    }
    
    /**
     * Display a message when the player wins the game
     */
    private void displayVictoryMessage() {
        
        displayPausedMessage("You are victorious! \n" +
                "(Press any key to start a new game)");
    }
    
    /**
     * Put the game to sleep
     * @param time time to sleep in milliseconds
     */
    private void delay(long time) {
        
        try {
            Thread.sleep(time);
        } catch(InterruptedException e) {
            System.out.println(e);
        }
    }
    
    /**
     * Starts the game. Display start message and then start the main loop.
     * @param window the game window to draw to
     */
    
    public void startGame(Gamewindow window) {
        
        displayStartMessage();
        
        while(true) {
            
            update();
            
            if(field.getGameOver()) {
                displayDefeatMessage();
                resetGame();
                System.gc();
            } else if(field.getBlocks().size() == 0) {
                displayVictoryMessage();
                resetGame();
                System.gc();
            } else if(keyboardControls.getPauseButton()) {
                pauseGame();
            }
            
        }
    }
    
    /**
     * Update the game state and draw the next frame
     */
    private void update() {
        
        long frameTime = System.nanoTime();
        
        controlPaddle();
        
        field.moveBall();
        renderer.repaint();
        
        if(timeOfLastPause != -1 &&
                System.nanoTime() - timeOfLastPause > 500000000)
            timeOfLastPause = -1;
        
        frameTime = System.nanoTime() - frameTime;
        
        int frameMs = (int) frameTime / 1000000;
        
        if(frameMs < 15) {
            delay(15 - frameMs);
        }
    }
    
    /**
     * Receive input and move the paddle on the field
     */
    private void controlPaddle() {
        
        final int moveConstant = (width / 240) + 1;
        final int boostConstant = keyboardControls.getSpaceIsPressed() ? 4 : 1;
        
        if(keyboardControls.getMoveLeft()) {
            field.movePaddle((- moveConstant) * boostConstant);
        } else if(keyboardControls.getMoveRight()) {
            field.movePaddle(moveConstant * boostConstant);
        }
    }
    
    /**
     * Default initialization routine for the blocks
     * @param blocks the container for the blocks
     */
    private void initBlocks(Blocks blocks) {
        
        blocks.nullifyBlockList();
        System.gc();
        
        final int blockheight = height / 24;
        final int blockwidth = width / 8;
        
        for(int j = 0; j < 10; j++) {
            for(int i = 0; i < 8; i++) {
                
                blocks.insert(new Block(blockheight, blockwidth, 
                        i * blockwidth, (3 * blockheight) + (j * blockheight)));
            }
        }
    }
    
}
