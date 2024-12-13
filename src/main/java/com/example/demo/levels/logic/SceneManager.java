package com.example.demo.levels.logic;

import com.example.demo.interfaces.EventChangeListener;
import com.example.demo.interfaces.SceneState;
import com.example.demo.scenes.*;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.EnumMap;

/**
 * SceneManager class handles switching between scenes in the game.
 */
public class SceneManager implements EventChangeListener {

    /**
     * Singleton SceneManager instance.
     */
    private static SceneManager instance;

    /**
     * JavaFX stage object reference that denotes the actual application stage where scenes reside.
     */
    private static Stage stage;

    /**
     * Maps SceneType enum to different SceneState objects.
     */
    private final Map<SceneType, SceneState> sceneCache = new EnumMap<>(SceneType.class);

    /**
     * Current scene of type {@code SceneState}.
     */
    private SceneState currentScene;

    /**
     * Stores the current level name for restarting functionality.
     */
    private String currentLevelName;

    private SceneManager(Stage stage) {
        SceneManager.stage = stage;
        sceneCache.put(SceneType.WIN_GAME, new WinGameScene(this));
        sceneCache.put(SceneType.GAME_OVER, new GameOverScene(this));
        sceneCache.put(SceneType.MAIN_MENU, new MainMenuScene(this));
    }

    /**
     * Retrieves the Singleton SceneManager instance.
     * @param stage Stage of the application where the scenes reside.
     * @return Singleton SceneManager instance.
     */
    public static SceneManager getInstance(Stage stage) {
        if (instance == null) {
            synchronized (SceneManager.class) {
                if (instance == null) {
                    instance = new SceneManager(stage);
                }
            }
        }
        return instance;
    }

    /**
     * Switches the scene to a new scene.
     * @param newScene Scene of type {@code SceneState} to switch to.
     */
    public void setState(SceneState newScene) {
        if (currentScene != null) {
            currentScene.exitScene();
        }
        currentScene = newScene;
        currentScene.loadScene();

        stage.setScene(currentScene.getScene());
    }

    /**
     * Retrieves the stage of the application where the scenes reside.
     * @return Stage of the application.
     */
    public static Stage getStage() {
        return stage;
    }

    /**
     * Handles switching to scenes in general.
     * @param event Scene to switch to.
     */
    @Override
    public void onEventChange(String event) {
        if (event.startsWith("com")) {
            loadLevel(event);
        } else {
            SceneType sceneType = SceneType.valueOf(event.toUpperCase());
            SceneState staticScene = sceneCache.get(sceneType);
            if (staticScene != null) {
                setState(staticScene);
            } else {
                throw new IllegalArgumentException("Unknown static scene type: " + sceneType);
            }
        }
    }

    /**
     * Specifically handles switching to level scenes.
     * @param levelClassName Level to switch to.
     */
    private void loadLevel(String levelClassName) {
        try {
            Class<?> levelClass = Class.forName(levelClassName);
            Constructor<?> constructor = levelClass.getConstructor(double.class, double.class);
            LevelParent newLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());

            GameplayScene gameplayScene = new GameplayScene(this, newLevel);
            setState(gameplayScene);
            currentLevelName = levelClassName;
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to load level: " + levelClassName, e);
        }
    }

    /**
     * Retrieves the current level name.
     * @return Level name.
     */
    public String getCurrentLevelName() {
        return currentLevelName;
    }
}
