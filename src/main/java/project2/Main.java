package project2;

import project2.entities.Maze;
import project2.generator.MazeGenerator;
import project2.generator.MazeGeneratorImpl;
import project2.renderer.MazeRenderer;
import project2.renderer.MazeRendererImpl;

public class Main {
    public static void main(String[] args) {
        int mazeWidth = 8, mazeHeight = 6;
        MazeGenerator mazeGenerator = new MazeGeneratorImpl();
        MazeRenderer mazeRenderer = new MazeRendererImpl();
        try {
            Maze maze = mazeGenerator.generate(mazeWidth, mazeHeight);
            mazeRenderer.render(maze);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
    }
}
