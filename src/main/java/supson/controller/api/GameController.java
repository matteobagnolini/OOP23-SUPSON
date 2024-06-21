package supson.controller.api;

/**
 * The GameController interface represents a game controller.
 * It provides methods for processing input, updating the game state, and rendering the game.
 */
public interface GameController {

    /**
     * Processes the input for the game.
     */
    void processInput();

    /**
     * Updates the game state.
     * @param elapsed the time delta on which update the game state
     */
    void update(long elapsed);

    /**
     * Renders the game.
     */
    void render(); //il render è gia presente in GameEngine vedere se tenerlo o no

    /**
     * Initialises the game.
     */
    void initGame();

    void startGame();

    void showScore();

    void quitGame();

}
