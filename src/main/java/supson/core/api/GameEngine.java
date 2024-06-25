package supson.core.api;

/**
 * The GameEngine interface represents a game engine that controls the game flow.
 */
public interface GameEngine {

    /**
     * Initializes the game.
     */
    void initGame();

    /**
     * The main control method of the game.
     */
    void mainControl();

    /**
     * Processes the user input.
     */
    void processInput();

    /**
     * Updates the game state based on the elapsed time.
     *
     * @param elapsed The elapsed time since the last update.
     */
    void updateGame(long elapsed);

    /**
     * Renders the game.
     */
    void render();

    /**
     * Waits for the next frame to start.
     *
     * @param cycleStartTime The start time of the current frame.
     */
    void waitForNextFrame(long cycleStartTime);

}
