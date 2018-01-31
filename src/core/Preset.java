package core;

public class Preset {

    private Preset() {}

    public static Board gosperGliderGun(int rows, int columns) {
        Cell[][] grid = new Cell[rows][columns];

        for (int row = 0; row < grid.length; row++) {
            for (int col = 0; col < grid[row].length; col++) {
                grid[row][col] = new Cell();
            }
        }

        grid[5][1].setNewState(true);
        grid[5][1].updateState();
        grid[5][2].setNewState(true);
        grid[5][2].updateState();
        grid[6][1].setNewState(true);
        grid[6][1].updateState();
        grid[6][2].setNewState(true);
        grid[6][2].updateState();
        grid[5][11].setNewState(true);
        grid[5][11].updateState();
        grid[6][11].setNewState(true);
        grid[6][11].updateState();
        grid[7][11].setNewState(true);
        grid[7][11].updateState();
        grid[4][12].setNewState(true);
        grid[4][12].updateState();
        grid[3][13].setNewState(true);
        grid[3][13].updateState();
        grid[3][14].setNewState(true);
        grid[3][14].updateState();
        grid[8][12].setNewState(true);
        grid[8][12].updateState();
        grid[9][13].setNewState(true);
        grid[9][13].updateState();
        grid[9][14].setNewState(true);
        grid[9][14].updateState();
        grid[6][15].setNewState(true);
        grid[6][15].updateState();
        grid[4][16].setNewState(true);
        grid[4][16].updateState();
        grid[5][17].setNewState(true);
        grid[5][17].updateState();
        grid[6][17].setNewState(true);
        grid[6][17].updateState();
        grid[7][17].setNewState(true);
        grid[7][17].updateState();
        grid[6][18].setNewState(true);
        grid[6][18].updateState();
        grid[8][16].setNewState(true);
        grid[8][16].updateState();
        grid[3][21].setNewState(true);
        grid[3][21].updateState();
        grid[4][21].setNewState(true);
        grid[4][21].updateState();
        grid[5][21].setNewState(true);
        grid[5][21].updateState();
        grid[3][22].setNewState(true);
        grid[3][22].updateState();
        grid[4][22].setNewState(true);
        grid[4][22].updateState();
        grid[5][22].setNewState(true);
        grid[5][22].updateState();
        grid[2][23].setNewState(true);
        grid[2][23].updateState();
        grid[6][23].setNewState(true);
        grid[6][23].updateState();
        grid[1][25].setNewState(true);
        grid[1][25].updateState();
        grid[2][25].setNewState(true);
        grid[2][25].updateState();
        grid[6][25].setNewState(true);
        grid[6][25].updateState();
        grid[7][25].setNewState(true);
        grid[7][25].updateState();
        grid[3][35].setNewState(true);
        grid[3][35].updateState();
        grid[4][35].setNewState(true);
        grid[4][35].updateState();
        grid[3][36].setNewState(true);
        grid[3][36].updateState();
        grid[4][36].setNewState(true);
        grid[4][36].updateState();

        return new Board(grid);
    }
}
