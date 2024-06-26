package supson.model.entity.impl.moveable.enemy;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.common.api.Vect2d;
import supson.common.impl.Vect2dImpl;
import supson.model.entity.api.enemy.Enemy;
import supson.model.entity.impl.moveable.AbstractMoveableEntity;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.physics.api.Physics;
import supson.model.physics.impl.PhysicsImpl;

/**
 * This class, which extends the abstract class MoveableEntity, models
 * the base enemy of the game.
 */
public final class GroundEnemy extends AbstractMoveableEntity implements Enemy {

    private static final int MAX_SPEED = 2;
    private static final double ACC_SPEED = 0.4;
    private static final Vect2d VELOCITY = new Vect2dImpl(0, 0);
    private static final double FRICTION = 0.4;
    private static final int JUMP_FORCE = 0;
    private static final double GRAVITY = 0.05;

    private static final int LIFE = 1;
    private static final int RANGE = 3;

    private static final int HEIGHT = 2;
    private static final int WIDTH = 1;

    private static final GameEntityType TYPE = GameEntityType.ENEMY;

    private boolean forward;
    private final Pos2d initialPos;
    private final double range;

    /**
     * The constructor of the base enemy class.
     * @param pos the starting positon of the enemy
     */
    public GroundEnemy(final Pos2d pos) {
        super(pos, HEIGHT, WIDTH, TYPE, VELOCITY, LIFE, new PhysicsImpl(MAX_SPEED, ACC_SPEED, ACC_SPEED,
                                                    FRICTION, JUMP_FORCE, GRAVITY));
        this.forward = false;
        this.initialPos = pos;
        this.range = RANGE;
    }

    @Override
    protected void updateVelocity() {
        final Physics physicComponent = getPhysicsComponent();
        if (this.forward) {
            physicComponent.moveLeft(this);
            if (this.initialPos.x() - this.getPosition().x() >= range) {
                this.forward = false;
            }
        } else {
            physicComponent.moveRight(this);
            if (this.initialPos.x() - this.getPosition().x() <= 0) {
                this.forward = true;
            }
        }
    }

    @Override
    public void applyDamage(final Player player) {
        player.setLife(player.getLife() - 1);
    }

}
