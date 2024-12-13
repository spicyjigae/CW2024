package com.example.demo.interfaces;

import javafx.scene.Scene;

/**
 * Implementations of this interface denote a scene in the game.
 */
public interface SceneState {
    void loadScene();
    void exitScene();
    Scene getScene();
}
