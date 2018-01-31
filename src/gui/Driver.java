package gui;

import core.Board;
import core.Cell;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

public class Driver {

    private double boardWidth;
    private double boardHeight;
    private int boardRows;
    private int boardColumns;
    private double cellWidth;
    private double cellHeight;
    private GridPane gridPane;

    public Driver(double width, double height, int rows, int columns, Board board) {
        boardWidth = width;
        boardHeight = height;
        boardRows = rows;
        boardColumns = columns;
        cellWidth = Math.floor(boardWidth / boardRows);
        cellHeight = Math.floor(boardHeight / boardColumns);

        gridPane = new GridPane();
        gridPane.setPadding(Insets.EMPTY);
        gridPane.setHgap(0);
        gridPane.setVgap(0);
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setMinSize(boardWidth, boardHeight);
        gridPane.setPrefSize(boardWidth, boardHeight);
        gridPane.setMaxSize(boardWidth, boardHeight);

        Cell[][] cells = board.getGrid();

        for (int y = 0; y < boardRows; y++) {
            for (int x = 0; x < boardColumns; x++) {
                Color color = cells[y][x].getState() ? Color.STEELBLUE : Color.WHITE;
                Rectangle rectangle = new Rectangle(cellWidth, cellHeight, color);
                rectangle.setStrokeType(StrokeType.INSIDE);
                rectangle.setStrokeWidth(0.25d);
                rectangle.setStroke(Color.GRAY);
                gridPane.add(rectangle, x, y);
                attachListeners(rectangle, cells[y][x]);
            }
        }
    }

    public void displayBoard(Board board) {
        gridPane.getChildren().clear();
        Cell[][] cells = board.getGrid();
        for (int y = 0; y < boardRows; y++) {
            for (int x = 0; x < boardColumns; x++) {
                Color color = cells[y][x].getState() ? Color.STEELBLUE : Color.WHITE;
                Rectangle rectangle = new Rectangle(cellWidth, cellHeight, color);
                rectangle.setStrokeType(StrokeType.INSIDE);
                rectangle.setStrokeWidth(0.25d);
                rectangle.setStroke(Color.GRAY);
                gridPane.add(rectangle, x, y);
            }
        }
    }

    public GridPane getGridPane() {
        return gridPane;
    }

    private void attachListeners(Rectangle rectangle, Cell cell) {
        rectangle.setOnMousePressed(event -> rectangle.setFill(Color.GRAY));
        rectangle.setOnMouseClicked(event -> {
            rectangle.setFill(cell.getState() ? Color.STEELBLUE : Color.WHITE);
            cell.setNewState(!cell.getState());
            cell.updateState();
        });
    }
}
