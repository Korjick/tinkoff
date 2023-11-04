package project2.generator;

import project2.entities.Maze;

public interface MazeGenerator {
    Maze generate(int width, int height) throws IllegalArgumentException;
}
