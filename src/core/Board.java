package core;

public class Board {

    private Cell[][] grid;
    private int rows;
    private int columns;

    public Board(Cell[][] grid) {
        this.grid = grid;
        rows = columns = grid.length;
    }

    public Board(int rows, int columns, double p) {
        this.rows = rows;
        this.columns = columns;
        grid = new Cell[rows][columns];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new Cell();
                if (Math.random() <= p) {
                    grid[row][col].setNewState(true);
                    grid[row][col].updateState();
                }
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    private int neighboursCountAt(int row, int col) {
        int sum = 0;

        if (row != 0 && col != 0 && isAlive(row - 1, col - 1)) sum++;
        if (row != 0 && isAlive(row - 1, col)) sum++;
        if (row != 0 && col != columns - 1 && isAlive(row - 1, col + 1)) sum++;
        if (col != 0 && isAlive(row, col - 1)) sum++;
        if (col != columns - 1 && isAlive(row, col + 1)) sum++;
        if (row != rows - 1 && col != 0 && isAlive(row + 1, col - 1)) sum++;
        if (row != rows - 1 && isAlive(row + 1, col)) sum++;
        if (row != rows - 1 && col != columns - 1 && isAlive(row + 1, col + 1)) sum++;

        return sum;
    }

    private boolean isAlive(int row, int col) {
        return grid[row][col].getState();
    }

    public void update() {
        prepare();
        commit();
    }

    private void prepare() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                int nr = neighboursCountAt(row, col);
                if (nr < 2) {
                    grid[row][col].setNewState(false);
                } else if (nr > 3) {
                    grid[row][col].setNewState(false);
                } else if (nr == 3) {
                    grid[row][col].setNewState(true);
                } else if (nr == 2) {
                    grid[row][col].setNewState(grid[row][col].getState());
                }
            }
        }
    }

    private void commit() {
        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col].updateState();
            }
        }
    }
}
