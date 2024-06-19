package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import supson.common.impl.Pos2dImpl;
import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.moveable.enemy.Enemy;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.hud.api.Hud;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;

/**
 * This class contains unit tests for the World class.
 */
public class TestWorld {

    private static final String FILE_PATH = "/world.txt";
    private World world;

    /**
     * Sets up the test environment by creating a new instance of World and loading the world from the specified file.
     */
    @BeforeEach
    void setUp() {
        world = new WorldImpl();
        world.loadWorld(FILE_PATH);
    }

    /**
     * Tests the getBlocks() method of the World class.
     * It checks if the returned list of blocks is not null and not empty.
     */
    @Test
    void testGetBlocks() {
        List<GameEntity> blocks = world.getBlocks();
        assertNotNull(blocks);
        assertFalse(blocks.isEmpty());
    }

    /**
     * Tests the getEnemies() method of the World class.
     * It checks if the returned list of enemies is not null and verifies the initial conditions based on the world.txt file.
     */
    @Test
    void testGetEnemies() {
        List<Enemy> enemies = world.getEnemies();
        assertNotNull(enemies);
        assertFalse(enemies.isEmpty());
    }

    /**
     * Tests the getPlayer() method of the World class.
     * It checks if the returned player object is not null and matches the expected initial position.
     */
    @Test
    void testGetPlayer() {
        Player player = world.getPlayer();
        assertNotNull(player);
        final int y = 7;
        assertEquals(new Pos2dImpl(0, y), player.getPosition());
    }

    /**
     * Tests the reset() method of the World class.
     * It checks if the world is reset properly by verifying that the lists of blocks and enemies are not empty,
     * and the player position is set to the default position.
     */
    @Test
    void testReset() {
        world.reset(FILE_PATH);
        assertNotNull(world.getBlocks());
        assertFalse(world.getBlocks().isEmpty());
        assertFalse(world.getEnemies().isEmpty());
        assertNotNull(world.getPlayer());
        final int y = 7;
        assertEquals(new Pos2dImpl(0, y), world.getPlayer().getPosition());
    }

    /**
     * Tests the removeBlock() method of the World class.
     * It checks if a block is successfully removed from the world by verifying that the number of blocks is decreased by one.
     * This assumes that the initial number of blocks is not zero.
     */
    @Test
    void testRemoveBlock() {
        List<GameEntity> blocks = world.getBlocks();
        int initialBlockCount = blocks.size();
        if (initialBlockCount > 0) {
            world.removeBlock(blocks.get(0));
            assertEquals(initialBlockCount - 1, world.getBlocks().size());
        }
    }

    /**
     * Tests the removeEnemy() method of the World class.
     * It checks if an enemy is successfully removed from the world by verifying that the number of enemies is decreased by one.
     * This assumes that the initial number of enemies is not zero.
     */
    @Test
    void testRemoveEnemy() {
        List<Enemy> enemies = world.getEnemies();
        int initialEnemyCount = enemies.size();
        if (initialEnemyCount > 0) {
            world.removeEnemy(enemies.get(0));
            assertEquals(initialEnemyCount - 1, world.getEnemies().size());
        }
    }

    /**
     * Tests the updateGame() method of the World class.
     * It checks if the game entities are updated correctly.
     */
    @Test
    void testUpdateGame() {
        long elapsed = 1000;
        world.updateGame(elapsed);
        Player player = world.getPlayer();
        assertNotNull(player);
    }

    /**
     * Tests the getGameEntities() method of the World class.
     * It checks if the returned list of game entities contains blocks, enemies, and the player.
     */
    @Test
    void testGetGameEntities() {
        List<GameEntity> entities = world.getGameEntities();
        assertNotNull(entities);
        assertTrue(entities.contains(world.getPlayer()));
        assertTrue(entities.containsAll(world.getBlocks()));
        assertTrue(entities.containsAll(world.getEnemies()));
    }

    /**
     * Tests the getHud() method of the World class.
     * It checks if the HUD is correctly initialized and updated.
     */
    @Test
    void testGetHud() {
        Hud hud = world.getHud();
        assertNotNull(hud);
        assertEquals(world.getPlayer().getScore(), hud.getScore());
        assertEquals(world.getPlayer().getLife(), hud.getLives());
    }

    /**
     * Tests the isGameOver() method of the World class.
     * It checks if the game over condition is correctly identified.
     */
    @Test
    void testIsGameOver() {
        Player player = world.getPlayer();
        player.setLife(-1);
        world.updateGame(0);
        assertTrue(world.isGameOver());
    }

    /**
     * Tests the loadWorld() method of the World class.
     * It checks if the world is loaded correctly from the file.
     */
    @Test
    void testLoadWorld() {
        world.loadWorld(FILE_PATH);
        List<GameEntity> blocks = world.getBlocks();
        List<Enemy> enemies = world.getEnemies();
        assertNotNull(blocks);
        assertFalse(blocks.isEmpty());
        assertNotNull(enemies);
        assertFalse(enemies.isEmpty());
    }
}
