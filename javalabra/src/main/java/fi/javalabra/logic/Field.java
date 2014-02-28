package fi.javalabra.logic;

import java.util.Random;

/**
 * Field class which is responsible for wrapping up data associated 
 * with game logic and essential game actions like collisions, moving the ball,
 * and moving the paddle
 * @author jola
 */
public class Field {
    
    private int height;
    private int width;
    
    private Ball ball;
    private Paddle paddle;
    
    private Blocks blocks;
    
    private boolean gameOver;
    
    private boolean randomizeVelocity;
    
    /**
     * Construct the playing field by specifying the height
     * and width of the field
     * @param height Height of the field
     * @param width Width of the field
     * @param ball Current ball on the field
     * @param paddle Paddle to play with
     */
    public Field(int height, int width, Ball ball, Paddle paddle,
            Blocks blocks) {
        
        this.height = height;
        this.width = width;
        this.ball = ball;
        this.paddle = paddle;
        this.blocks = blocks;
        
        this.randomizeVelocity = false;
        
        this.gameOver = false;
        
    }
    
    //Only for testing purposes
    public Field(int height, int width, Ball ball) {
        
        this(height, width, ball, null, null);
    }
    
    /**
     * Get the velocity of the ball and advance the ball to the position
     * it is supposed to be in the next frame
     */
    public void moveBall() {
        moveBall(ball.getDX(), ball.getDY());
    }
    
    /**
     * Move the ball to a new position, also check for collisions and
     * check if the game is over.
     * @param dx amount to move the ball along the X-axis
     * @param dy amount to move the ball along the Y-axis
     */
    public void moveBall(int dx, int dy) {
        
        int futureLeft = ball.getLeftEdgeX() + dx;
        int futureRight = ball.getRightEdgeX() + dx;
        
        int futureHigh = ball.getHighEdgeY() + dy;
        int futureLow = ball.getLowEdgeY() + dy;
        
        //System.out.println("1. ball: (" + ball.getX() + ", "
        //        + ball.getY() + ") velocity: (" + dx + ", " + dy + ")");
        
        if(blocks != null && blocks.getFirst() != null) {
            boolean hitBlock = handleBlockCollisions(ball, dy, dx);
            blocks.unlock();
            if(hitBlock) {
                velocityRandomizer(ball);
                return;
            }
        }
        
        
        if(futureHigh < 0) {
            
            //reverse the remaining vertical velocity vector
            //curdy = - futureHigh;
            //split the horizontal velocity vector with the same ratio as
            //the verical one
            //curdx = ratioOfVectors(dx, Math.abs(ball.getHighEdgeY()),
            //        Math.abs(futureHigh));
            
            //place the ball next to the wall
            //ball.setLocation(ball.getX() + (dx - curdx), 0);
            
            handleVerticalCollision(ball, 0, dy, dx);
            velocityRandomizer(ball);
            
        } else if(futureLeft < 0) {
            
            
            handleHorizontalCollision(ball, 0, dy, dx);
            
        } else if(futureLow >= height) {
            
            gameOver = true;
            
        } else if(futureRight >= width) {
                     
            handleHorizontalCollision(ball, width - 1, dy, dx);
        } else if(paddle != null && paddle
                .collidesWithHighEdge(ball.getLowEdgeY(), futureLow, 
                futureLeft, futureRight)) {
            
            handleVerticalCollision(ball, paddle.getY(), dy, dx);
        } else {
            ball.setLocation(ball.getX() + dx, ball.getY() + dy);
        }
        
    }
    
    /**
     * Helper function for splitting a line to two parts with some
     * certain ratio
     * @param splittee the line to split
     * @param numerator numerator of the ratio
     * @param denominator denominator of the ratio
     * @return the part of the line with length proportional to the denominator
     */
    private int ratioOfVectors(int splittee, int numerator, int denominator) {
        
        if(numerator + denominator == 0)
            return splittee;
        return splittee * denominator / (denominator + numerator);
    }
    
    /**
     * Handle a block collision situation
     * @param ball ball to check the collision for
     * @param verticalMomentum vertical momentum of the ball
     * @param horizontalMomentum horizontal momentum of the ball
     * @return true if a collision happened
     */
    private boolean handleBlockCollisions(Ball ball, int verticalMomentum,
            int horizontalMomentum) {
        
        blocks.lock();
        
        
        blocks.rewind();
        
        Block current = blocks.getNext();
        
        while(current != null) {
            
            if(handleBlockLowCollision(ball, current,
                    verticalMomentum, horizontalMomentum) ||
                handleBlockHighCollision(ball, current,
                    verticalMomentum, horizontalMomentum) ||
                handleBlockLeftCollision(ball, current,
                    verticalMomentum, horizontalMomentum) ||
                handleBlockRightCollision(ball, current,
                    verticalMomentum, horizontalMomentum)) {
                
                blocks.unlock();
                return true;
            }
            
            current = blocks.getNext();
        }
        
        blocks.rewind();
        
        blocks.unlock();
        return false;
        
    }
    
    /**
     * Handle collision with the low edge of a block
     * @param ball ball to check the collision for
     * @param current the block to check collision with
     * @param verticalMomentum vertical momentum of the ball
     * @param horizontalMomentum horizontal momentum of the ball
     * @return true if a collision happened
     */
    private boolean handleBlockLowCollision(Ball ball, Block current,
            int verticalMomentum, int horizontalMomentum) {
        
        if(current.collidesWithLowEdge(ball.getHighEdgeY(),
                ball.getHighEdgeY() + verticalMomentum,
                ball.getLeftEdgeX() + horizontalMomentum,
                ball.getRightEdgeX() + horizontalMomentum)) {

            blocks.deleteCurrent();

            handleVerticalCollision(ball, current.getLowEdgeY(),
                    verticalMomentum, horizontalMomentum);

            blocks.rewind();
            return true;
        }
        
        return false;
    }
    
    /**
     * Handle collision with the high edge of a block
     * @param ball ball to check the collision for
     * @param current the block to check collision with
     * @param verticalMomentum vertical momentum of the ball
     * @param horizontalMomentum horizontal momentum of the ball
     * @return true if a collision happened
     */
    private boolean handleBlockHighCollision(Ball ball, Block current,
            int verticalMomentum, int horizontalMomentum) {
        
        if(current.collidesWithHighEdge(ball.getLowEdgeY(),
                ball.getLowEdgeY() + verticalMomentum,
                ball.getLeftEdgeX() + horizontalMomentum,
                ball.getRightEdgeX() + horizontalMomentum)) {

            blocks.deleteCurrent();
            handleVerticalCollision(ball, current.getHighEdgeY(),
                    verticalMomentum, horizontalMomentum);

            blocks.rewind();
            return true;

        }
        
        return false;
    }
    
    /**
     * Handle collision with the left edge of a block
     * @param ball ball to check the collision for
     * @param current the block to check collision with
     * @param verticalMomentum vertical momentum of the ball
     * @param horizontalMomentum horizontal momentum of the ball
     * @return true if a collision happened
     */
    private boolean handleBlockLeftCollision(Ball ball, Block current,
            int verticalMomentum, int horizontalMomentum) {
        
        if(current.collidesWithLeftEdge(ball.getRightEdgeX(),
                ball.getRightEdgeX() + horizontalMomentum,
                ball.getLowEdgeY() + verticalMomentum,
                ball.getHighEdgeY() + verticalMomentum)) {

            blocks.deleteCurrent();
            handleHorizontalCollision(ball, current.getLeftEdgeX(),
                    verticalMomentum, horizontalMomentum);

            blocks.rewind();
            return true;
        }

        return false;
    }
    
    /**
     * Handle collision with the right edge of a block
     * @param ball ball to check the collision for
     * @param current the block to check collision with
     * @param verticalMomentum vertical momentum of the ball
     * @param horizontalMomentum horizontal momentum of the ball
     * @return true if a collision happened
     */
    private boolean handleBlockRightCollision(Ball ball, Block current,
            int verticalMomentum, int horizontalMomentum) {
        
        if(current.collidesWithRightEdge(ball.getLeftEdgeX(),
                ball.getLeftEdgeX() + horizontalMomentum,
                ball.getLowEdgeY() + verticalMomentum,
                ball.getHighEdgeY() + verticalMomentum)) {

            blocks.deleteCurrent();
            handleHorizontalCollision(ball, current.getRightEdgeX(),
                    verticalMomentum, horizontalMomentum);

            blocks.rewind();
            return true;
        }
        
        return false;
    }
    
    /**
     * Handle collision with a vertical line
     * @param ball the ball which collides
     * @param lineY the position of the vertical line
     * @param verticalMomentum vertical momentum of the ball
     * @param horizontalMomentum horizontal momentum of the ball
     */
    private void handleVerticalCollision(Ball ball, int lineY, 
            int verticalMomentum, int horizontalMomentum) {
        
        //the dimensions of the ball have to be taken into account
        int edgeOffset = ball.getDY() <= 0 ? 0 : ball.getHeight() - 1;
        
        //calculate intra-tick movement vector
        int curdy = - (ball.getY() + verticalMomentum - (lineY - edgeOffset));
        int curdx = ratioOfVectors(horizontalMomentum,
                Math.abs(ball.getY() + edgeOffset), 
                Math.abs(ball.getY() + edgeOffset + verticalMomentum));
        
        
        ball.setLocation(ball.getX() + (horizontalMomentum - curdx),
                lineY - edgeOffset);
        ball.setVelocityVector(ball.getDX(), - ball.getDY());
        
        //System.out.println("(" + curdx + ", " + curdy + ")");
        
        moveBall(curdx, curdy);
        
    }
    
    /**
     * Handle collision with a horizontal line
     * @param ball the ball which collides
     * @param lineX the position of the horizontal line
     * @param verticalMomentum vertical momentum of the ball
     * @param horizontalMomentum horizontal momentum of the ball
     */
    private void handleHorizontalCollision(Ball ball, int lineX,
            int verticalMomentum, int horizontalMomentum) {
        
        int edgeOffset = ball.getDX() <= 0 ? 0 : ball.getWidth() - 1;
        
        int curdx = - (ball.getX() + horizontalMomentum - (lineX - edgeOffset));
        int curdy = ratioOfVectors(verticalMomentum,
                Math.abs(ball.getX() + edgeOffset), 
                Math.abs(ball.getX() + edgeOffset + horizontalMomentum));
        
        ball.setLocation(lineX - edgeOffset, 
                ball.getY() + (verticalMomentum - curdy));
        ball.setVelocityVector(- ball.getDX(), ball.getDY());
        
        
        moveBall(curdx, curdy);
    }
    
    /**
     * Move the paddle along the x-axis by the amount specified.
     * Can't move the paddle out of bounds.
     * @param dx negative value to move left, positive value to move right
     */
    public void movePaddle(int dx) {
        
        if(paddle.getX() + dx < 0)
            paddle.setX(0);
        else if(paddle.getX() + paddle.getWidth() + dx >= width)
            paddle.setX(width - paddle.getWidth());
        else
            paddle.setX(paddle.getX() + dx);
    }
    
    /**
     * Move the paddle to specified x-coordinate.
     * Can't move the paddle out of bounds
     * @param x the x-coordinate to move the paddle to
     */
    public void placePaddle(int x) {
        
        if(x < 0)
            paddle.setX(0);
        else if(x + paddle.getWidth() >= width)
            paddle.setX(width - paddle.getWidth());
        else
            paddle.setX(x);
    }
    
    /**
     * Get the height of the field
     * @return height of the field
     */
    public int getHeight() {
        return height;
    }
    
    /**
     * Get the width of the field
     * @return 
     */
    public int getWidth() {
        return width;
    }
    
    /**
     * Get the ball
     * @return the ball
     */
    public Ball getBall() {
        return ball;
    }
    
    /**
     * Get the paddle
     * @return the paddle
     */
    public Paddle getPaddle() {
        
        return paddle;
    }
    
    /**
     * Get the blocks container
     * @return blocks container
     */
    public Blocks getBlocks() {
        
        return blocks;
    }
    
    /**
     * Specifies whether to use randomized velocity
     * @param randomizeVelocity true to enable velocity randomization
     */
    public void setRandomizeVelocity(boolean randomizeVelocity) {
        this.randomizeVelocity = randomizeVelocity;
    }
    
    /**
     * Method to randomize the velocity of the ball
     * @param ball ball for which to set the randomized velocity
     */
    public void velocityRandomizer(Ball ball) {
        
        Random rng = new Random();
        int randY = rng.nextInt(5);
        int randX = rng.nextInt(3);
        
        if(randY == 0){
            if (ball.getDY() < 0)
                 ball.setVelocityVector(ball.getDX(), ball.getDY() - 1);
            else 
                 ball.setVelocityVector(ball.getDX(), ball.getDY() + 1);
        }
        if(randX == 0)
            ball.setVelocityVector(ball.getDX() - 1, ball.getDY());
        else if(randX == 2)
            ball.setVelocityVector(ball.getDX() + 1, ball.getDY());
    }
    
    /**
     * Set a flag which tells that the game is over
     * @param gameOver true if the game is over
     */
    public void setGameOver(boolean gameOver) {
        
        this.gameOver = gameOver;
    }
    
    /**
     * Get the information about whether the game is over
     * @return true if the game is over
     */
    public boolean getGameOver() {
        
        return gameOver;
    }
}
