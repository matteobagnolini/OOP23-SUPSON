package supson.model.entity.impl;

import supson.common.api.Pos2d;
import supson.common.api.Vect2d;
import supson.model.entity.api.MoveableEntity;

/**
 * This abstract class, which extends AbstractGame and implements the interface MoveableEntity,
 *  models a generic moveable entity of the game.
 * This class is used as a template to create other more specific classes, such as enemies and the player.
 */
public abstract class AbstractMoveableEntity extends AbstractGameEntity implements MoveableEntity {

    private static final double VEL_MULT_FACTOR = 0.001;

    private Vect2d vel;
    private int life;

    /**
     * The constructor of a generic moveable entity.
     * @param pos the position of the entity
     * @param height the height of the entity
     * @param width the width of the entity
     * @param vel the starting velocity of the entity
     * @param life the number of life of the entity
     */
    public AbstractMoveableEntity(final Pos2d pos, final int height, final int width,
     final Vect2d vel, final int life) {
        super(pos, height, width);
        this.vel = vel;
        this.life = life;
        }

    @Override
    public final Vect2d getVelocity() {
        return this.vel;
    }

    @Override
    public final void setVelocity(final Vect2d vel) {
        this.vel = vel;
    }

    @Override
    public final int getLife() {
        return this.life;
    }

    @Override
    public final void setLife(final int numLife) {
        this.life = numLife;
    }

    @Override
    public final void move(final long timeDelta) {
        updateVelocity();
        setPosition(getPosition().sum(vel.mul(VEL_MULT_FACTOR * timeDelta)));
    }

    /**
     * This method should be overrided.
     */
    protected abstract void updateVelocity();

}
