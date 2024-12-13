package com.example.demo.scenes;

import com.example.demo.interfaces.SceneState;
import com.example.demo.levels.logic.SceneManager;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * GameOverScene class that implements SceneState interface handles the creation of the Game Over scene.
 */
public class GameOverScene implements SceneState {

    private static final String GAME_OVER_IMAGE = "/com/example/demo/images/scenes/game_over.png";
    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/background/background2.jpg";

    /**
     * SceneManager object reference.
     */
    private final SceneManager sceneManager;

    /**
     * JavaFX scene object for storing the scene.
     */
    private Scene scene;

    public GameOverScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    /**
     * Creates the Game Over scene and stores it in {@code scene}.
     */
    @Override
    public void loadScene() {

        ImageView backgroundImage = new ImageView(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE)).toExternalForm());
        backgroundImage.setFitHeight(backgroundImage.getFitHeight());
        backgroundImage.setFitWidth(backgroundImage.getFitWidth());
        backgroundImage.setPreserveRatio(false);

        VBox root = new VBox(30);
        root.setAlignment(Pos.CENTER);

        ImageView loseImage = new ImageView(Objects.requireNonNull(getClass().getResource(GAME_OVER_IMAGE)).toExternalForm());
        loseImage.setFitHeight(450);
        loseImage.setFitWidth(600);

        Button menuButton = new Button("Main Menu");
        menuButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4caf93; -fx-text-fill: white;");
        menuButton.setOnAction(event -> sceneManager.onEventChange((String.valueOf(SceneType.MAIN_MENU))));

        Button exitButton = new Button("Exit");
        exitButton.setStyle("-fx-font-size: 18px; -fx-background-color: #4caf93; -fx-text-fill: white;");
        exitButton.setOnAction(event -> System.exit(0));

        root.getChildren().addAll(loseImage, menuButton, exitButton);

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
     * Retrieves the current Game Over scene.
     * @return Game Over scene.
     */
    @Override
    public Scene getScene() {
        return scene;
    }
}
