package com.example.demo.scenes;

import com.example.demo.interfaces.SceneState;
import com.example.demo.levels.logic.LevelParent;
import com.example.demo.levels.logic.SceneManager;
import javafx.scene.Scene;

/**
 * GameplayScene class that implements SceneState interface handles the dynamic creation of each level.
 */
public class GameplayScene implements SceneState {

    /**
     * SceneManager object reference.
     */
    private final SceneManager sceneManager;

    /**
     * LevelParent object reference of the current level.
     */
    private LevelParent currentLevel;

    /**
     * JavaFX scene object for storing the scene.
     */
    private Scene scene;

    public GameplayScene(SceneManager sceneManager, LevelParent level) {
        this.sceneManager = sceneManager;
        setLevel(level);
    }

    /**
     * Removes the current level's listeners and setups the new level by adding listeners.
     * @param level New level.
     */
    private void setLevel(LevelParent level) {
        if (currentLevel != null) {
            currentLevel.removeListener(sceneManager);
        }

        this.currentLevel = level;
        currentLevel.addListener(sceneManager);
    }

    /**
     * Dynamically creates the gameplay scene depending on the level.
     */
    @Override
    public void loadScene() {
        this.scene = new Scene(currentLevel.getRoot(), SceneManager.getStage().getWidth(), SceneManager.getStage().getHeight());
        currentLevel.initializeLevel();
    }

    /**
     * Retrieves the current gameplay scene.
     * @return Gameplay scene.
     */
    @Override
    public Scene getScene() {
        return this.scene;
    }

    /**
     * Properly exits the level by clearing level resources before switching to a new scene.
     */
    @Override
    public void exitScene() {
        currentLevel.stopLevel();
        this.scene = null;
    }
}
