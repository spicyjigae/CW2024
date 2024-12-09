package com.example.demo.scenes;

import com.example.demo.interfaces.SceneState;
import com.example.demo.levels.logic.SceneManager;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.util.Objects;

public class WinGameScene implements SceneState {

    private static final String WIN_IMAGE = "/com/example/demo/images/scenes/win_game.png";
    private static final String BACKGROUND_IMAGE = "/com/example/demo/images/background/background2.jpg";

    private final SceneManager sceneManager;

    private Scene scene;

    public WinGameScene(SceneManager sceneManager) {
        this.sceneManager = sceneManager;
    }

    @Override
    public void loadScene() {
        StackPane root = new StackPane();

        ImageView backgroundImage = new ImageView(Objects.requireNonNull(getClass().getResource(BACKGROUND_IMAGE)).toExternalForm());
        backgroundImage.setFitHeight(backgroundImage.getFitHeight());
        backgroundImage.setFitWidth(backgroundImage.getFitWidth());

        ImageView winImage = new ImageView(Objects.requireNonNull(getClass().getResource(WIN_IMAGE)).toExternalForm());

        root.getChildren().addAll(backgroundImage, winImage);

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
