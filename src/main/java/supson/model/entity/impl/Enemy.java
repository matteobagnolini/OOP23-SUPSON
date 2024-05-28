package supson.model.entity.impl;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.api.Vect2d;
import supson.model.entity.player.Player;
import supson.model.physics.impl.PhysicsImpl;

/**
 * This class, which extends the abstract class MoveableEntity, models
 * the base enemy of the game.
 */
public final class Enemy extends AbstractMoveableEntity {

    private static final int MAX_SPEED = 5;
    private static final double ACC_SPEED = 0.4;
    private static final double FRICTION = 0.4;
    private static final int JUMP_FORCE = 0;
    private static final double GRAVITY = 0.05;


    private static final int HEIGHT = 2;
    private static final int WIDTH = 1;

    private static final GameEntityType TYPE = GameEntityType.ENEMY;

    private boolean forward;
    private Pos2d initialPos;
    private double range;

    /**
     * The constructor of the enemy class.
     * @param pos the starting positon of the enemy
     * @param vel the starting velocity of the enemy
     * @param life the number of life of the enemy
     * @param range the range of movement of the enemy
     */
    public Enemy(Pos2d pos, Vect2d vel, int life, final int range) {
        super(pos, HEIGHT, WIDTH, TYPE, vel, life, new PhysicsImpl(MAX_SPEED, ACC_SPEED, 0,
                                                    FRICTION, JUMP_FORCE, GRAVITY));
        this.forward = true;
        this.initialPos = pos;
        this.range = range;
    }


    @Override
    protected void updateVelocity() {
        if (forward) {
            getPhysicsComponent().moveLeft(this);
            forward = getXDistanceFromSpawn() > range ? true : false;
        } else {
        getPhysicsComponent().moveRight(this);
        forward = getXDistanceFromSpawn() > range ? false : true;
        }
    }

    public void applyDamage(Player player){
        player.setLife(player.getLife()-1);
    }

    private double getXDistanceFromSpawn() {
        return Math.abs(this.getPosition().x() - initialPos.x());
    }

}
