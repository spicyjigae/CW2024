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

public class GameOverScene implements SceneState {

    private static final String GAME_OVER_IMAGE = "/com/example/demo/images/scenes/game_over.png";
    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/background/background2.jpg";

    private final SceneManager sceneManager;

    private Scene scene;

    public GameOverScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

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

    @Override
    public void exitScene() {
    this.scene = null;
    }

    @Override
    public Scene getScene() {
        return scene;
    }
}
