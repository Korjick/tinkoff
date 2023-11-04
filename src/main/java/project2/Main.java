package project2;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import project2.entities.Maze;
import project2.generator.MazeGenerator;
import project2.generator.MazeGeneratorImpl;
import project2.renderer.MazeRenderer;
import project2.renderer.MazeRendererImpl;

public final class Main {

    private static final Logger LOGGER = LoggerContext.getContext().getLogger(Main.class);

    private Main() {

    }

    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        int mazeWidth = 8;
        int mazeHeight = 6;
        MazeGenerator mazeGenerator = new MazeGeneratorImpl();
        MazeRenderer mazeRenderer = new MazeRendererImpl();
        try {
            Maze maze = mazeGenerator.generate(mazeWidth, mazeHeight);
            mazeRenderer.render(maze);
        } catch (IllegalArgumentException e) {
            LOGGER.error(e.getMessage());
        }
    }
}
