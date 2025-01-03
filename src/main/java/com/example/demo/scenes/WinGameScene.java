package com.example.demo.scenes;

import com.example.demo.interfaces.SceneState;
import com.example.demo.levels.logic.SceneManager;import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * WinGameScene class that implements SceneState interface handles the creation of the Win Game scene.
 */
public class WinGameScene implements SceneState {

    private static final String WIN_IMAGE = "/com/example/demo/images/scenes/win_game.png";
    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/background/background2.jpg";

    /**
     * SceneManager object reference.
     */
    private final SceneManager sceneManager;

    /**
     * JavaFX scene object for storing the scene.
     */
    private Scene scene;

    public WinGameScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Creates the Win Game scene and stores it in {@code scene}.
     */
    @Override
    public void loadScene() {

        ImageView backgroundImage = new ImageView(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE)).toExternalForm());
        backgroundImage.setFitHeight(backgroundImage.getFitHeight());
        backgroundImage.setFitWidth(backgroundImage.getFitWidth());
        backgroundImage.setPreserveRatio(false);

        VBox root = new VBox(50);
        root.setAlignment(Pos.CENTER);

        ImageView winImage = new ImageView(Objects.requireNonNull(getClass().getResource(WIN_IMAGE)).toExternalForm());

        Button menuButton = new Button("Main Menu");
        menuButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4caf93; -fx-text-fill: white;");
        menuButton.setOnAction(event -> sceneManager.onEventChange((String.valueOf(SceneType.MAIN_MENU))));

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4caf93; -fx-text-fill: white;");
        exitButton.setOnAction(event -> System.exit(0));

        root.getChildren().addAll(winImage, menuButton, exitButton);

        StackPane stackPane = new StackPane();
        stackPane.getChildren().addAll(backgroundImage, root);

        this.scene = new Scene(stackPane, SceneManager.getStage().getWidth(), SceneManager.getStage().getHeight());
        scene.addEventFilter(KeyEvent.KEY_PRESSED, event -> {
            if (event.getCode() == KeyCode.SPACE) {
                event.consume();
            }
        });
    }

    /**
     * Properly exits the scene before switching to another scene.
     */
    @Override
    public void exitScene() {
        this.scene = null;
    }

    /**
     * Retrieves the current Win Game scene.
     * @return Win Game scene.
     */
    @Override
    public Scene getScene() {
        return scene;
    }
}
