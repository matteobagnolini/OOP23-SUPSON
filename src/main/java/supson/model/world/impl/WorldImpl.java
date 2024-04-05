package supson.model.world.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Optional;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import supson.common.api.Pos2d;
import supson.common.impl.Pos2dImpl;
import supson.model.block.BlockType;
import supson.model.block.api.BlockEntity;
import supson.model.block.impl.BlockEntityImpl;
import supson.model.entity.api.MoveableEntity;
import supson.model.entity.impl.Player;
import supson.model.world.api.World;

/**
 * Implementation of the World interface.
 */
public class WorldImpl implements World {

    private final List<BlockEntity> blocks;
    private final List<MoveableEntity> enemies;
    private final Player player;

    /**
    * Constructs a new instance of the WorldImpl class.
    * Initializes the lists for blocks, enemies and palyer.
    */
    public WorldImpl() {
        this.blocks = new ArrayList<>();
        this.enemies = new ArrayList<>();
        this.player = new Player(null, 0, 0, null, 0); //todo : add parameters to the constructor
    }

    // non viene gestito l'insrimento di sonic ed  enimis
    @Override
    public final void loadWorld(final String filePath) {
        Map<Integer, BlockType> worldElementMap = new HashMap<>();
        worldElementMap.put(1, BlockType.TERRAIN);
        worldElementMap.put(2, BlockType.POWER_UP);
        worldElementMap.put(3, BlockType.RING);
        worldElementMap.put(4, BlockType.TRAP);  

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int y = 0;
            while ((line = reader.readLine()) != null) {
                String[] tokens = line.split(" ");
                for (int x = 0; x < tokens.length; x++) {
                    int worldElement = Integer.parseInt(tokens[x]);
                    Pos2d pos = new Pos2dImpl(x, y);
                    Optional<BlockType> optionalType = Optional.ofNullable(worldElementMap.get(worldElement));
                    optionalType.ifPresent(type -> {
                        this.addBlock(pos, type);
                    });
                }
                y++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
}

    @Override
    public final void reset() {
        this.blocks.removeAll(this.blocks);
        this.enemies.removeAll(this.enemies);
        this.loadWorld(null); //todo : add file path
    }

    private final void addBlock(final Pos2d pos, final BlockType type) {
        this.blocks.add(new BlockEntityImpl(pos, type));
    }

    @Override
    public final void removeBlock(final BlockEntity block) {
        this.blocks.remove(block);
    }
    
    private final void addEnemy(final Pos2d pos) {
        this.enemies.add(new Player(pos, 0, 0, null, 0)); //todo : add parameters to the constructor
    }

    @Override
    public final void removeEnemy(final MoveableEntity enemy) {
        this.enemies.remove(enemy);
    }

    @Override
    public List<BlockEntity> getBlocks() {
        return new ArrayList<>(blocks); //verificare che sia giusto
    }

    @Override
    public List<MoveableEntity> getEnemies() {
        return new ArrayList<>(enemies); //verificare che sia giusto
    }

    @Override
    public Pos2d getPlayerPosition() {
        return player.getPosition();
    }

}
