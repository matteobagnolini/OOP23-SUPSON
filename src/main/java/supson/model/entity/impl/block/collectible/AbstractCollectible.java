package supson.model.entity.impl.block.collectible;

import supson.common.GameEntityType;
import supson.common.api.Pos2d;
import supson.model.entity.api.block.collectible.Collectible;
import supson.model.entity.impl.AbstractGameEntity;

/**
 * An abstract implementation of the Collectible interface.
 * This class provides a base implementation for collectible blocks in the game.
 */
public abstract class AbstractCollectible extends AbstractGameEntity implements Collectible {

    private static final int HEIGHT = 1;
    private static final int WIDTH = 1;

    /**
     * Constructs a new AbstractCollectibleImpl object with the specified position and block type.
     *
     * @param pos  the position of the collectible block
     * @param type the type of the collectible block
     */
    public AbstractCollectible(final Pos2d pos, final GameEntityType type) {
        super(pos, HEIGHT, WIDTH, type);
    }
}
