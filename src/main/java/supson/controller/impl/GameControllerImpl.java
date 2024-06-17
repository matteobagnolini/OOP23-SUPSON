package supson.controller.impl;

import javax.swing.JFrame;

import supson.controller.api.GameController;
import supson.model.world.api.World;
import supson.model.world.impl.WorldImpl;
import supson.view.api.GameView;
import supson.view.impl.GameViewImpl;
import supson.view.impl.InputManager;

/**
 * This class, which implements the GameController interface, models the game controller.
 * It is the coordinator between the view and the model.
 */
public final class GameControllerImpl implements GameController {

    private static final int WHIDTH = 948;
    private static final int HEIGHT = 720;

    private static final String WORLD_FILE_PATH = "/level_1.txt";

    private final World model;
    private final GameView view;
    private final InputManager input;
    private final JFrame mainFrame;

    /**
     * This is the GameControllerImpl constructor.
     */
    public GameControllerImpl() {
        this.model = new WorldImpl();
        this.mainFrame = new JFrame("SUPER-SONIC");
        this.mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.mainFrame.setSize(WHIDTH, HEIGHT);
        this.view = new GameViewImpl(mainFrame);
        this.input = new InputManager();
        this.mainFrame.addKeyListener(input);
    }


    @Override
    public void processInput() {
        this.model.getPlayer().setMoveLeft(input.isLeft());
        this.model.getPlayer().setMoveRight(input.isRight());
        this.model.getPlayer().setJump(input.isJump());
    }

    @Override
    public void update(final long elapsed) {
        this.model.updateGame(elapsed);
        if(this.model.isGameOver()){
            System.out.println("Game Over");
            System.out.println("Score: "+this.model.getHud().getScore()+"\nTime: "+ this.model.getHud().getTime());
        }
    }

    @Override
    public void render() {
        this.view.renderView(this.model.getGameEntities(), this.model.getPlayer(), this.model.getHud());
    }

    @Override
    public void initGame() {
        this.model.loadWorld(WORLD_FILE_PATH);
        this.view.initView();
    }

}
