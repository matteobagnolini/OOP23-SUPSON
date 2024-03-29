package supson.model.entity.impl;

import supson.common.api.Pos2d;
import supson.common.api.Vect2d;
import supson.common.impl.Vect2dImpl;

/**
 * This class, which extends the abstract class MoveableEntity, models
 * the player of the game.
 */
public final class Player extends AbstractMoveableEntity {

    private static final int MAX_SPEED = 10;
    private static final double ACC_SPEED = 0.01;
    private static final int JUMP_FORCE = 12;
    private static final double GRAVITY = 0.05;

    private static final int HEIGHT = 2;
    private static final int WIDTH = 1;

    private boolean left, right, jump;
    private boolean isJumping;

    /**
     * The constructor of the player class.
     * @param pos the starting positon of the player
     * @param heigth the height of the player
     * @param width the width of the player
     * @param vel the starting velocity of the player
     * @param life the number of life of the player
     */
    public Player(final Pos2d pos, final int heigth, final int width,
    final Vect2d vel, final int life) {
        super(pos, HEIGHT, WIDTH, vel, life);
    } 

    @Override
    public void updateVelocity() {
        if (left) {
            moveLeft();
        } else if (right) {
            moveRight();
        }
        if (jump || isJumping) {
            jump();
        }
    }

    /**
     * This method update the velocity vector, causing the player
     * to move right.
     */
    private void moveRight() {
        final Vect2d oldVel = getVelocity();
        double newXVel = oldVel.x();
        if (oldVel.x() < 0) {   //were moving left

            stopOrizVel();

        } else if (oldVel.x() < MAX_SPEED) {

            newXVel += ACC_SPEED;  //accelerate

            if (newXVel >= MAX_SPEED) {

                newXVel = MAX_SPEED;    //top speed reached

            }
        }
        setVelocity(new Vect2dImpl(newXVel, oldVel.y()));
    }

    /**
     * This method update the velocity vector, causing the player
     * to move left.
     */
    private void moveLeft() {
        final Vect2d oldVel = getVelocity();
        double newXVel = oldVel.x();
        if (oldVel.x() > 0) {   //were moving right

            stopOrizVel();

        } else if (oldVel.x() > -MAX_SPEED) {

            newXVel -= ACC_SPEED;   //accelerate

            if (newXVel <= -MAX_SPEED) {

                newXVel = -MAX_SPEED;  //top speed reached

            }
        }
        setVelocity(new Vect2dImpl(newXVel, oldVel.y()));
    }

    /**
     * This method stops the player on the X axis. It is called everytime the 
     * player changes direction (from left to right and vice versa).
     */
    private void stopOrizVel() {
        setVelocity(new Vect2dImpl(0.0, getVelocity().y()));
    }

    /**
     * This method update the velocity vector, causing the player to jump.
     */
    private void jump() {
        final Vect2d oldVel = getVelocity();
        double newY = oldVel.y();
        if (!isJumping) {       //jump starting now

            newY = JUMP_FORCE;

        } else {

            newY -= GRAVITY;

        }
        setVelocity(new Vect2dImpl(oldVel.x(), newY));
    }

    /**
     * This method set all the movement flags to false.
     * This method should be called by the controller when the player
     * stops moving in the X axis.
     */
    public void setMovesToFalse() {
        this.left = false;
        this.right = false;
    }

    /**
     * This method sets the right flag. It should be used when 
     * the player moves right.
     */
    public void setMoveRight(boolean flag) {
        this.right = flag;
    }

    /**
     * This method sets the left flag. It should be used when 
     * the player moves left.
     */
    public void setMoveLeft(boolean flag) {
        this.left = flag;
    }

    /**
     * This method sets the jump flag. It should be used when 
     * the player jumps.
     */
    public void setJump(boolean flag) {
        this.jump = flag;
    }

}
