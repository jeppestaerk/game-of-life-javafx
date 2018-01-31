package gui;

import core.Board;
import core.Preset;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Slider;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;


public class Main extends Application {

    private double appWidth = 800;
    private double appHeight = 600;
    private double gridWidth = appHeight;
    private double gridHeight = gridWidth;
    private double panelWidth = appWidth - gridWidth;
    private double panelHeight = appHeight;
    private double frameDuration = 100;
    private int gridSize = 60;
    private int frameCount;

    private Timeline loop = null;
    private Board board;
    private Driver driver;

    private Label panelLabel;
    private Label gridSizeLabel;
    private Label frameDurationLabel;
    private Label frameCountLabel;

    private Slider gridSizeSlider;
    private Slider frameDurationSlider;

    private Button startButton;
    private Button stopButton;
    private Button randomButton;
    private Button clearButton;
    private Button gosperGliderGunButton;
    private Button wikipediaButton;

    private GridPane gridPane;
    private VBox panel;
    private HBox root;
    private Scene scene;


    @Override
    public void start(Stage primaryStage) {

        initializeUI();
        onCreate();

        primaryStage.setScene(scene);
        primaryStage.setTitle("Game of Life");
        primaryStage.setWidth(appWidth);
        primaryStage.setHeight(appHeight);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    private void initializeUI() {

        panelLabel = new Label("Game of Life");

        gridSizeLabel = new Label("Grid Size");

        gridSizeSlider = new Slider(30, 90, gridSize);
        gridSizeSlider.setPrefWidth(Double.MAX_VALUE);
        gridSizeSlider.setShowTickLabels(true);
        gridSizeSlider.setShowTickMarks(true);
        gridSizeSlider.setSnapToTicks(true);
        gridSizeSlider.setMajorTickUnit(30);
        gridSizeSlider.setMinorTickCount(30);
        gridSizeSlider.setBlockIncrement(30);
        gridSizeSlider.setOrientation(Orientation.HORIZONTAL);
        gridSizeSlider.valueProperty().addListener(onSizeChange());

        frameDurationLabel = new Label("Frame Duration (ms)");

        frameDurationSlider = new Slider(100, 300, frameDuration);
        frameDurationSlider.setPrefWidth(Double.MAX_VALUE);
        frameDurationSlider.setShowTickLabels(true);
        frameDurationSlider.setShowTickMarks(true);
        frameDurationSlider.setSnapToTicks(true);
        frameDurationSlider.setMajorTickUnit(100);
        frameDurationSlider.setMinorTickCount(100);
        frameDurationSlider.setBlockIncrement(100);
        frameDurationSlider.setOrientation(Orientation.HORIZONTAL);
        frameDurationSlider.valueProperty().addListener(onSpeedChange());

        startButton = new Button("Start");
        startButton.setPrefWidth(Double.MAX_VALUE);
        startButton.setOnAction(event -> onStart());

        stopButton = new Button("Stop");
        stopButton.setPrefWidth(Double.MAX_VALUE);
        stopButton.setDisable(false);
        stopButton.setOnAction(event -> onStop());

        clearButton = new Button("Clear");
        clearButton.setPrefWidth(Double.MAX_VALUE);
        clearButton.setOnAction(event -> onClear());

        randomButton = new Button("Random");
        randomButton.setPrefWidth(Double.MAX_VALUE);
        randomButton.setOnAction(event -> onCreate());

        gosperGliderGunButton = new Button("Gosper glider gun");
        gosperGliderGunButton.setPrefWidth(Double.MAX_VALUE);
        gosperGliderGunButton.setOnAction(event -> onGosperGliderGun());

        wikipediaButton = new Button("Wikipedia");
        wikipediaButton.setPrefWidth(Double.MAX_VALUE);
        wikipediaButton.setOnAction(event -> getHostServices().showDocument("https://en.wikipedia.org/wiki/Conway%27s_Game_of_Life"));

        frameCount = 0;

        frameCountLabel = new Label();
        frameCountLabel.setAlignment(Pos.BOTTOM_LEFT);
        frameCountLabel.setText("Frame: " + frameCount);

        panel = new VBox();
        panel.setAlignment(Pos.TOP_CENTER);
        panel.setSpacing(10d);
        panel.setPadding(new Insets(10d));
        panel.minWidth(panelWidth);
        panel.minHeight(panelHeight);
        panel.setPrefWidth(panelWidth);
        panel.setPrefHeight(panelHeight);
        panel.maxWidth(panelWidth);
        panel.maxHeight(panelHeight);
        panel.getChildren().addAll(
                panelLabel,
                new Separator(),
                gridSizeLabel,
                gridSizeSlider,
                frameDurationLabel,
                frameDurationSlider,
                new Separator(),
                startButton,
                stopButton,
                new Separator(),
                clearButton,
                randomButton,
                gosperGliderGunButton,
                new Separator(),
                wikipediaButton,
                new Separator(),
                frameCountLabel);

        toggleButtons(true);

        gridPane = new GridPane();
        gridPane.setAlignment(Pos.CENTER);

        root = new HBox(panel, gridPane);
        root.minWidth(appWidth);
        root.minHeight(appHeight);
        root.setPrefWidth(appWidth);
        root.setPrefHeight(appHeight);
        root.maxWidth(appWidth);
        root.maxHeight(appHeight);

        scene = new Scene(root, appWidth, appHeight);
    }

    private ChangeListener<Number> onSpeedChange() {
        return (observable, oldValue, newValue) -> {
            frameDuration = newValue.intValue();
            frameDurationSlider.setValue(frameDuration);
        };
    }

    private ChangeListener<Number> onSizeChange() {
        return (observable, oldValue, newValue) -> {
            gridSize = newValue.intValue();
            gridSizeSlider.setValue(gridSize);
            onCreate();
        };
    }

    private void onStop() {
        loop.stop();
        toggleButtons(true);
    }

    private void onStart() {
        toggleButtons(false);
        loop = new Timeline(new KeyFrame(Duration.millis(frameDuration), e -> {
            board.update();
            driver.displayBoard(board);
            frameCountLabel.setText("Frame: " + ++frameCount);
        }));
        loop.setCycleCount(Animation.INDEFINITE);
        loop.play();
    }

    private void toggleButtons(boolean enable) {
        gridSizeSlider.setDisable(!enable);
        frameDurationSlider.setDisable(!enable);
        startButton.setDisable(!enable);
        stopButton.setDisable(enable);
        clearButton.setDisable(!enable);
        randomButton.setDisable(!enable);
        gosperGliderGunButton.setDisable(!enable);
        frameCountLabel.setDisable(enable);
    }

    private void onCreate() {
        frameCount = 0;
        frameCountLabel.setText("Frame: " + frameCount);
        gridPane.getChildren().clear();
        board = new Board(gridSize, gridSize, 0.15d);
        driver = new Driver(gridWidth, gridHeight, gridSize, gridSize, board);
        gridPane.getChildren().add(new Group(driver.getGridPane()));
    }

    private void onClear() {
        frameCount = 0;
        frameCountLabel.setText("Frame: " + frameCount);
        gridPane.getChildren().clear();
        board = new Board(gridSize, gridSize, 0);
        driver = new Driver(gridWidth, gridHeight, gridSize, gridSize, board);
        gridPane.getChildren().add(new Group(driver.getGridPane()));
    }

    private void onGosperGliderGun() {
        frameCount = 0;
        frameCountLabel.setText("Frame: " + frameCount);
        gridSize = 60;
        gridSizeSlider.setValue(gridSize);
        gridPane.getChildren().clear();
        board = Preset.gosperGliderGun(gridSize, gridSize);
        driver = new Driver(gridWidth, gridHeight, gridSize, gridSize, board);
        gridPane.getChildren().add(new Group(driver.getGridPane()));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
