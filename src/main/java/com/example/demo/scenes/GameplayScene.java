package com.example.demo.scenes;

import com.example.demo.interfaces.SceneState;
import com.example.demo.levels.logic.LevelParent;
import com.example.demo.levels.logic.SceneManager;
import javafx.scene.Scene;

public class GameplayScene implements SceneState {

    private final SceneManager sceneManager;

    private LevelParent currentLevel;
    private Scene scene;

    public GameplayScene(SceneManager sceneManager, LevelParent level) {
        this.sceneManager = sceneManager;
        setLevel(level);
    }

    private void setLevel(LevelParent level) {
        if (currentLevel != null) {
            currentLevel.removeListener(sceneManager);
        }

        this.currentLevel = level;
        currentLevel.addListener(sceneManager);
    }

    @Override
    public void loadScene() {
        this.scene = new Scene(currentLevel.getRoot(), SceneManager.getStage().getWidth(), SceneManager.getStage().getHeight());
        currentLevel.initializeLevel();
    }

    @Override
    public Scene getScene() {
        return this.scene;
    }

    @Override
    public void exitScene() {
        currentLevel.stopLevel();
        this.scene = null;
    }
}
