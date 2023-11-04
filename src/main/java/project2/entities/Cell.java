package project2.entities;

public class Cell {

    private int clusterId;
    private boolean down;
    private boolean left;
    private boolean right;
    private boolean up;

    public Cell(
        int clusterId,
        boolean down,
        boolean left,
        boolean right,
        boolean up
    ) {
        this.clusterId = clusterId;
        this.down = down;
        this.left = left;
        this.right = right;
        this.up = up;
    }

    public Cell(Cell another) {
        this.clusterId = another.clusterId;
        this.down = another.down;
        this.left = another.left;
        this.right = another.right;
        this.up = another.up;
    }

    public int getClusterId() {
        return clusterId;
    }

    public void setClusterId(int clusterId) {
        this.clusterId = clusterId;
    }

    public boolean isDown() {
        return down;
    }

    public void setDown(boolean down) {
        this.down = down;
    }

    public void setLeft(boolean left) {
        this.left = left;
    }

    public void setRight(boolean right) {
        this.right = right;
    }

    public boolean isLeft() {
        return left;
    }

    public boolean isRight() {
        return right;
    }

    public boolean isUp() {
        return up;
    }

    public void setUp(boolean up) {
        this.up = up;
    }
}
