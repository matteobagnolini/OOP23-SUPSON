package supson.view.api.game.world;

import java.util.List;

import javax.swing.JPanel;

import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.moveable.player.Player;

/**
 * The WorldView interface represents the view of the game world.
 * It provides methods to render the game entities in the world.
 */
public interface WorldView {
    /**
    * Renders the game entities in the world.
    * @param gameFrame the game frame to render the world on 
    * @param gameEntitiesList the list of game entities to render in the world
    * @param player the player entity to render the world around
    */
    void renderWorld(JPanel gameFrame, List<GameEntity> gameEntitiesList, Player player);
}
