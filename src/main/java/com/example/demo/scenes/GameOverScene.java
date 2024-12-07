package com.example.demo.scenes;

import com.example.demo.interfaces.SceneState;
import com.example.demo.levels.logic.SceneManager;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

public class GameOverScene implements SceneState {
    private final SceneManager sceneManager;
    private Scene scene;
    private static final String GAME_OVER_IMAGE = "/com/example/demo/images/game_over.png";
    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/background2.jpg";

    public GameOverScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void loadScene() {

        StackPane root = new StackPane();

        ImageView backgroundImage = new ImageView(getClass().getResource(BACKGROUND_IMAGE).toExternalForm());
        backgroundImage.setFitHeight(backgroundImage.getFitHeight());
        backgroundImage.setFitWidth(backgroundImage.getFitWidth());

        ImageView loseImage = new ImageView(getClass().getResource(GAME_OVER_IMAGE).toExternalForm());

        root.getChildren().addAll(backgroundImage, loseImage);
        this.scene = new Scene(root, sceneManager.getStage().getWidth(), sceneManager.getStage().getHeight());
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
