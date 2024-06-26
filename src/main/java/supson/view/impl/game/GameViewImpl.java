package supson.view.impl.game;


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.KeyListener;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import supson.model.entity.api.GameEntity;
import supson.model.entity.impl.moveable.player.Player;
import supson.model.hud.api.Hud;
import supson.view.api.game.GameView;
import supson.view.api.game.HudView;
import supson.view.api.game.world.WorldView;
import supson.view.impl.common.ImagePanel;
import supson.view.impl.game.world.WorldViewImpl;

/**
 * Represents the main game view that displays the game world and HUD.
 */
@SuppressFBWarnings(
    value = {
        "EI2",
    },
    justification = "JFrame is intentionally shared among the views"
)
public final class GameViewImpl implements GameView {

    private static final String BG_PATH = "sprite/background.jpg";
    private static final int WIDTH = 948;
    private static final int HEIGHT = 720;

    private final WorldView worldView;
    private final HudView hudView;
    private final JFrame gameFrame;
    private final JPanel mainPanel;
    private final ImagePanel backgroundPanel;

    /**
     * Constructs a new `GameViewImpl` with the specified main game frame.
     *
     * @param frame The main game frame.
     */
    public GameViewImpl(final JFrame frame) {
        this.gameFrame = frame;
        this.worldView = new WorldViewImpl();
        this.hudView = new HudViewImpl();
        this.mainPanel = new JPanel();
        this.backgroundPanel = new ImagePanel(BG_PATH);
    }

    @Override
    public void initView() {

        backgroundPanel.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        backgroundPanel.setLayout(new BorderLayout());

        mainPanel.setOpaque(false);
        mainPanel.setLayout(new BorderLayout());

        final JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setPreferredSize(new Dimension(WIDTH, HEIGHT));

        backgroundPanel.setBounds(0, 0, WIDTH, HEIGHT);
        mainPanel.setBounds(0, 0, WIDTH, HEIGHT);

        layeredPane.add(backgroundPanel, JLayeredPane.DEFAULT_LAYER);
        layeredPane.add(mainPanel, JLayeredPane.PALETTE_LAYER);

        gameFrame.getContentPane().removeAll();
        gameFrame.setContentPane(layeredPane);
        gameFrame.revalidate();
        gameFrame.repaint();

    }

    @Override
    public void renderView(final List<GameEntity> gameEntitiesList, final Player player, final Hud hud) {
        mainPanel.removeAll();
        worldView.renderWorld(mainPanel, gameEntitiesList, player);
        hudView.renderHud(mainPanel, hud);
        mainPanel.repaint();
    }

    @Override
    public void addKeyListener(final KeyListener listener) {
        this.gameFrame.addKeyListener(listener);
    }

}
