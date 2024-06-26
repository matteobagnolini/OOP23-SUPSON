package supson;

import supson.core.api.GameEngine;
import supson.core.impl.GameEngineImpl;

/**
 * This class is the entry point of the application.
 */
public final class SonicLaunch {

    private SonicLaunch() { }

    /**
     * This is the main method to start the game.
     * @param args the arguments
     */
    public static void main(final String[] args) {
        System.setProperty("sun.java2d.opengl", "true");
        final GameEngine ge = new GameEngineImpl();
        ge.mainControl();
    }

}
