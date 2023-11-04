package project2.generator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;
import project2.entities.Cell;
import project2.entities.Maze;
import project2.utils.Constants;

public class MazeGeneratorImpl implements MazeGenerator {

    private final Random random;

    public MazeGeneratorImpl() {
        random = new Random();
    }

    @Override
    public Maze generate(int width, int height) throws IllegalArgumentException {

        if (height < 0 || width < 0) {
            throw new IllegalArgumentException("Maze size should be positive");
        }

        List<Cell> cellList = initMaze(width);
        for (int mazeIdx = 0; mazeIdx <= height - 1; mazeIdx++) {
            List<Cell> subList = cellList.subList(mazeIdx * width, mazeIdx * width + width);

            if (mazeIdx == height - 1) {
                reorderLast(subList);
                break;
            }

            if (mazeIdx != 0) {
                reorderIntermedia(subList);
            }
            combineClusters(subList);
            setDown(subList);

            List<Cell> copy = subList.stream().map(n -> {
                Cell newCell = new Cell(n);
                newCell.setUp(n.isDown());
                return newCell;
            }).toList();
            cellList.addAll(copy);
        }

        Cell[][] resultArray = new Cell[height][width];
        // 8 * 6
        for (int i = 0; i < cellList.size(); i++) {
            resultArray[i / width][i % width] = cellList.get(i);
        }

        return new Maze(resultArray, width, height);
    }

    private List<Cell> initMaze(int mazeSize) {
        List<Cell> cellList = new ArrayList<>();
        for (int i = 0; i < mazeSize; i++) {
            Cell newCell = new Cell(i, false, false, false, true);
            if (i == 0) {
                newCell.setLeft(true);
            } else if (i == mazeSize - 1) {
                newCell.setRight(true);
            }
            cellList.add(newCell);
        }

        return cellList;
    }

    private void combineClusters(List<Cell> cellList) {
        for (int i = 0; i < cellList.size() - 1; i++) {
            Cell left = cellList.get(i);
            Cell right = cellList.get(i + 1);
            if (left.getClusterId() == right.getClusterId()) {
                left.setRight(true);
                right.setLeft(true);
            } else {
                if (random.nextFloat() < Constants.WALL_PROBABILITY) {
                    left.setRight(true);
                    right.setLeft(true);
                } else {
                    right.setClusterId(left.getClusterId());
                }
            }
        }
    }

    private void setDown(List<Cell> cellList) {
        Map<Integer, List<Cell>> clusters = cellList.stream().collect(Collectors.groupingBy(Cell::getClusterId));
        clusters.forEach((k, v) -> {
            if (v.size() == 1) {
                return;
            }

            int downCount = random.nextInt(1, v.size());
            Collections.shuffle(v);
            for (int i = 0; i < downCount; i++) {
                v.get(i).setDown(true);
            }
        });
    }

    private void reorderIntermedia(List<Cell> cellList) {
        Set<Integer> clusters = cellList.stream().map(Cell::getClusterId).collect(Collectors.toSet());
        for (int i = 0; i < cellList.size(); i++) {
            Cell tmpCell = cellList.get(i);
            if (i != cellList.size() - 1) {
                tmpCell.setRight(false);
            }
            if (i != 0) {
                tmpCell.setLeft(false);
            }
            if (tmpCell.isDown()) {
                tmpCell.setDown(false);
                int randCluster;
                do {
                    randCluster = random.nextInt(cellList.size());
                }
                while (clusters.contains(randCluster));
                tmpCell.setClusterId(randCluster);
                clusters.add(randCluster);
            }
        }
    }

    private void reorderLast(List<Cell> cellList) {
        for (int i = 0; i < cellList.size() - 1; i++) {
            Cell left = cellList.get(i);
            Cell right = cellList.get(i + 1);
            left.setDown(true);
            right.setDown(true);
            if (left.getClusterId() != right.getClusterId()) {
                left.setRight(false);
                right.setLeft(false);
                right.setClusterId(left.getClusterId());
            }
        }
    }
}
