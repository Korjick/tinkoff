package project2.renderer;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.LoggerContext;
import project2.entities.Cell;
import project2.entities.Maze;
import project2.utils.Constants;

public class MazeRendererImpl implements MazeRenderer {

    private static final Logger LOGGER = LoggerContext.getContext().getLogger(MazeRendererImpl.class);

    private final Map<BorderType, Character[][]> borderMap = new HashMap<>();

    public MazeRendererImpl() {
        borderMap.put(BorderType.NONE, new Character[][] {
            {Constants.SPACE, Constants.SPACE, Constants.SPACE},
            {Constants.SPACE, Constants.SPACE, Constants.SPACE},
            {Constants.SPACE, Constants.SPACE, Constants.SPACE},
        });
        borderMap.put(BorderType.LEFT, new Character[][] {
            {Constants.WALL, Constants.SPACE, Constants.SPACE},
            {Constants.WALL, Constants.SPACE, Constants.SPACE},
            {Constants.WALL, Constants.SPACE, Constants.SPACE},
        });
        borderMap.put(BorderType.RIGHT, new Character[][] {
            {Constants.SPACE, Constants.SPACE, Constants.WALL},
            {Constants.SPACE, Constants.SPACE, Constants.WALL},
            {Constants.SPACE, Constants.SPACE, Constants.WALL},
        });
        borderMap.put(BorderType.UP, new Character[][] {
            {Constants.WALL, Constants.WALL, Constants.WALL},
            {Constants.SPACE, Constants.SPACE, Constants.SPACE},
            {Constants.SPACE, Constants.SPACE, Constants.SPACE},
        });
        borderMap.put(BorderType.DOWN, new Character[][] {
            {Constants.SPACE, Constants.SPACE, Constants.SPACE},
            {Constants.SPACE, Constants.SPACE, Constants.SPACE},
            {Constants.WALL, Constants.WALL, Constants.WALL},
        });
    }

    private static Character[][] combineArrays(Character[][] array1, Character[][] array2) {
        int rows = array1.length;
        int cols = array1[0].length;
        Character[][] result = new Character[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] =
                    array1[i][j].equals(Constants.WALL) || array2[i][j].equals(Constants.WALL)
                        ? Constants.WALL
                        : Constants.SPACE;
            }
        }

        return result;
    }

    @SuppressWarnings("NestedForDepth")
    @Override
    public void render(Maze maze) {
        Character[][] arr = new Character[maze.getHeight() * (Constants.CELL_RENDER_SIZE - 1) + 1][
            maze.getWidth() * (Constants.CELL_RENDER_SIZE - 1) + 1];
        for (Character[] line : arr) {
            Arrays.fill(line, Constants.SPACE);
        }

        Cell[][] mazeArr = maze.getMaze();
        for (int i = 0; i < maze.getHeight(); i++) {
            for (int j = 0; j < maze.getWidth(); j++) {
                Cell tmpCell = mazeArr[i][j];

                Character[][] initial = borderMap.get(BorderType.NONE);
                if (tmpCell.isUp()) {
                    initial = combineArrays(initial, borderMap.get(BorderType.UP));
                }
                if (tmpCell.isDown()) {
                    initial = combineArrays(initial, borderMap.get(BorderType.DOWN));
                }
                if (tmpCell.isLeft()) {
                    initial = combineArrays(initial, borderMap.get(BorderType.LEFT));
                }
                if (tmpCell.isRight()) {
                    initial = combineArrays(initial, borderMap.get(BorderType.RIGHT));
                }

                for (int k = 0; k < Constants.CELL_RENDER_SIZE; k++) {
                    for (int l = 0; l < Constants.CELL_RENDER_SIZE; l++) {
                        if (arr[i * (Constants.CELL_RENDER_SIZE - 1) + k][j * (Constants.CELL_RENDER_SIZE - 1) + l]
                            .equals(Constants.WALL)) {
                            continue;
                        }
                        arr[i * 2 + k][j * 2 + l] = initial[k][l];
                    }
                }
            }
        }

        for (Character[] line : arr) {
            LOGGER.info(Arrays.toString(line));
        }
    }
}
