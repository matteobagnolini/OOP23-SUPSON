package supson.view.api.game;

import java.awt.event.KeyListener;
import java.util.List;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.hud.api.Hud;

/**
 * The GameView interface represents the view component of the game.
 * It provides methods for initializing and rendering the game.
 */
public interface GameView {

    /**
     * Initializes the view.
     */
    void initView();

    /**
     * Renders the view.
     * 
     * @param gameEntitiesList the list of game entities to render
     * @param player the player object to render
     * @param hud the hud object to render
     */
    void renderView(List<GameEntity> gameEntitiesList, Player player, Hud hud);

    /**
     * Add the key lissener.
     * 
     * @param listener the lissener to add
     */
    void addKeyListener(KeyListener listener);
}
