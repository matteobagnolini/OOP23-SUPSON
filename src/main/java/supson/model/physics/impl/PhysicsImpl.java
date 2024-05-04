package supson.model.physics.impl;

import supson.common.api.Vect2d;
import supson.common.impl.Vect2dImpl;
import supson.model.entity.api.MoveableEntity;
import supson.model.physics.api.Physics;

/**
 * This class, which implements the Physics interface, models the physics of
 * an entity of the game. This object should be instantiated in every moveable
 * entity to controll its physics.
 * This class moves the entity by modifying the velocity vector of the entity 
 * it is attached to. 
 */
public final class PhysicsImpl implements Physics {

    private final int maxSpeed;
    private final double accSpeed;
    private final int jumpForce;
    private final double gravity;


    /**
     * This is the constructor of the PhysicsImpl class.
     * @param maxSpeed the max speed of the entity
     * @param accSpeed the acceleration factor of the entity
     * @param jumpForce the jump force of the entity
     * @param gravity the gravity factor of the entity
     */
    public PhysicsImpl(final int maxSpeed, final double accSpeed,
        final int jumpForce, final double gravity) {
        this.maxSpeed = maxSpeed;
        this.accSpeed = accSpeed;
        this.jumpForce = jumpForce;
        this.gravity = gravity;
    }

    @Override
    public void moveRight(MoveableEntity entity) {
        final Vect2d oldVel = entity.getVelocity();
        double newXVel = oldVel.x();
        if (oldVel.x() < 0) {   //were moving left

            newXVel = 0;        //the player stops moving

        } else if (oldVel.x() < maxSpeed) {

            newXVel += accSpeed;  //accelerate

            if (newXVel >= maxSpeed) {

                newXVel = maxSpeed;    //top speed reached

            }
        }
        entity.setVelocity(new Vect2dImpl(newXVel, oldVel.y()));
    }

    @Override
    public void moveLeft(MoveableEntity entity) {
        final Vect2d oldVel = entity.getVelocity();
        double newXVel = oldVel.x();
        if (oldVel.x() > 0) {   //were moving right

            newXVel = 0;        //the player stops moving

        } else if (oldVel.x() > -maxSpeed) {

            newXVel -= accSpeed;   //accelerate

            if (newXVel <= -maxSpeed) {

                newXVel = -maxSpeed;  //top speed reached

            }
        }
        entity.setVelocity(new Vect2dImpl(newXVel, oldVel.y()));
    }

    @Override
    public void startJumping(MoveableEntity entity) {
        entity.setVelocity(new Vect2dImpl(entity.getVelocity().x(), jumpForce));
    }

    @Override
    public void applyGravity(MoveableEntity entity) {
        entity.setVelocity(new Vect2dImpl(entity.getVelocity().x(), entity.getVelocity().y() - gravity));
    }

}
