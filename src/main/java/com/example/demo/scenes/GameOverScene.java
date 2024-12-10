package com.example.demo.scenes;

import com.example.demo.interfaces.SceneState;
import com.example.demo.levels.logic.SceneManager;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class GameOverScene implements SceneState {

    private static final String GAME_OVER_IMAGE = "/com/example/demo/images/scenes/game_over.png";
    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/background/background2.jpg";

    private Scene scene;

    public GameOverScene() {
    }

    @Override
    public void loadScene() {

        StackPane root = new StackPane();

        ImageView backgroundImage = new ImageView(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE)).toExternalForm());
        backgroundImage.setFitHeight(backgroundImage.getFitHeight());
        backgroundImage.setFitWidth(backgroundImage.getFitWidth());

        ImageView loseImage = new ImageView(Objects.requireNonNull(getClass().getResource(GAME_OVER_IMAGE)).toExternalForm());

        root.getChildren().addAll(backgroundImage, loseImage);
        this.scene = new Scene(root, SceneManager.getStage().getWidth(), SceneManager.getStage().getHeight());
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
