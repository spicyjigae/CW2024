package com.example.demo.levels.logic;

import com.example.demo.interfaces.EventChangeListener;
import com.example.demo.interfaces.SceneState;
import com.example.demo.scenes.GameplayScene;
import javafx.stage.Stage;

import java.lang.reflect.Constructor;

public class SceneManager implements EventChangeListener {
    private SceneState currentScene;
    private final Stage stage;

    public SceneManager(Stage stage) {
        this.stage = stage;
    }

    public void setState(SceneState newScene) {
        if (currentScene != null) {
            currentScene.exitScene();
        }
        currentScene = newScene;
        currentScene.loadScene();
        stage.setScene(currentScene.getScene());
    }

    public Stage getStage() {
        return stage;
    }

    public void onEventChange(String nextLevel) {
        try {
            Class<?> myClass = Class.forName(nextLevel);
            Constructor<?> constructor = myClass.getConstructor(double.class, double.class);
            LevelParent newLevel = (LevelParent) constructor.newInstance(stage.getHeight(), stage.getWidth());

            GameplayScene gameplayScene = new GameplayScene(this, newLevel);

            setState(gameplayScene);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
