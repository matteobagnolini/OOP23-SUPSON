package supson.core.impl;

import supson.core.api.GameEngine;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.view.ViewEvent;
import supson.view.impl.MainView;
import supson.view.impl.common.InputManager;

/**
 * This class represents the main engine of the game.
 */
public final class GameEngineImpl implements GameEngine {

    private static final String WORLD_FILE_PATH = "/level/level_1.txt";

    private static final long REFRESH_RATE = 20;

    private GameState state;

    private final World model;
    private final MainView view;
    private final InputManager input;

    /**
     * GameEngine constructor.
     */
    public GameEngineImpl() {
        this.model = new WorldImpl();
        this.view = new MainView(this);
        this.input = new InputManager();
        this.view.addInputManager(input);
        this.state = GameState.LAUNCHER;
    }

    @Override
    public void initGame() {
        this.model.loadWorld(WORLD_FILE_PATH);
        this.view.showGameView();
    }

    @Override
    public void mainControl() {
        if (state.equals(GameState.LAUNCHER)) {
            this.view.showMenu();
        }
        while (state.equals(GameState.RUNNING)) {
            gameLoop();
        }
        if (state.equals(GameState.GAMEOVER)) {      
            this.view.showEndGame(this.model.getHud().getScore(),this.model.getHud().getTime(),this.model.isWon());
        }
    }


    private void gameLoop() {
        long previousCycleStartTime = System.currentTimeMillis();
        while (!this.model.isGameOver()) {
            final long currentCycleStartTime = System.currentTimeMillis();
            final long elapsed = currentCycleStartTime - previousCycleStartTime;
            processInput();
            updateGame(elapsed);
            render();
            waitForNextFrame(currentCycleStartTime);
            previousCycleStartTime = currentCycleStartTime;
        }
        this.state = GameState.GAMEOVER;
    }


    @Override
    public void processInput() {
        this.model.setPlayerFlags(input.isRight(), input.isLeft(), input.isJump());
    }

    @Override
    public void updateGame(final long elapsed) {
        this.model.updateGame(elapsed);
    }

    @Override
    public void render() {
        this.view.renderGameView(this.model.getGameEntities(), this.model.getPlayer(), this.model.getHud());
    }

    private void waitForNextFrame(final long cycleStartTime) {
        final long dt = System.currentTimeMillis() - cycleStartTime;
        if (dt < REFRESH_RATE) {
            try {
                Thread.sleep(REFRESH_RATE - dt);
            } catch (InterruptedException ex) { }
        }
   }

   @Override
   public void onNotifyFromView(final ViewEvent event) {
    switch (event) {
        case START_GAME -> {
            //this.view.closeMenu();
            this.state = GameState.RUNNING;
            this.initGame();
            new Thread(this::mainControl).start();
        }
        case CLOSE_GAME -> {
            //this.view.closeGameView();
            this.state = GameState.GAMEOVER;
            mainControl();
        }
        case RESTART -> {
            //this.view.closeEndMenu();
            this.state = GameState.RUNNING;
            this.model.reset(WORLD_FILE_PATH);
            this.initGame();
            new Thread(this::mainControl).start();
        }
        case MENU ->{
            this.state = GameState.LAUNCHER;
            this.model.reset(WORLD_FILE_PATH);
            mainControl();
        }
        case EXIT -> {
            System.exit(0);
        }
        default -> { }
    }
}

    /**
     * This enum represent the state of the game.
     */
    private enum GameState {

        LAUNCHER,
        RUNNING,
        GAMEOVER,

    }

}

