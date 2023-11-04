package project2.entities;

public class Maze {

    private final Cell[][] maze;
    private final int width;
    private final int height;

    public Maze(Cell[][] maze, int width, int height) {
        this.maze = maze;
        this.width = width;
        this.height = height;
    }

    public Cell[][] getMaze() {
        return maze;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
