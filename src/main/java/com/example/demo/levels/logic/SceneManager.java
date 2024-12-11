package com.example.demo.levels.logic;

import com.example.demo.interfaces.EventChangeListener;
import com.example.demo.interfaces.SceneState;
import com.example.demo.scenes.*;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;
import java.util.Map;
import java.util.EnumMap;

public class SceneManager implements EventChangeListener {

    private static SceneManager instance;
    private static Stage stage;
    private final Map<SceneType, SceneState> sceneCache = new EnumMap<>(SceneType.class);

    private SceneState currentScene;
    private String currentLevelName;

    private SceneManager(Stage stage) {
        SceneManager.stage = stage;
        sceneCache.put(SceneType.WIN_GAME, new WinGameScene(this));
        sceneCache.put(SceneType.GAME_OVER, new GameOverScene(this));
        sceneCache.put(SceneType.MAIN_MENU, new MainMenuScene(this));
    }

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

    public void setState(SceneState newScene) {
        if (currentScene != null) {
            currentScene.exitScene();
        }
        currentScene = newScene;
        currentScene.loadScene();

        stage.setScene(currentScene.getScene());
    }

    public static Stage getStage() {
        return stage;
    }

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

    public String getCurrentLevelName() {
        return currentLevelName;
    }
}
