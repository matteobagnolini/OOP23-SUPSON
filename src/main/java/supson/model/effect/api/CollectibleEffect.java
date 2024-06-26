package supson.model.effect.api;

import supson.model.entity.impl.moveable.player.Player;

/**
 * The CollectibleTimer interface represents a timer that can activate and terminate effects.
 * It extends the Runnable interface, allowing it to be executed as a thread.
 */
public interface CollectibleEffect extends Runnable {

    /**
     * Activates the effect associated with this timer.
     * 
     * @param player the taget of the effect
     */
    void activateEffect(Player player);

    /**
     * Terminates the effect associated with this timer.
     * 
     * @param player the taget of the effect
     */
    void terminateEffect(Player player);

    @Override
    void run();

}
