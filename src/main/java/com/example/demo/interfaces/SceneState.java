package com.example.demo.interfaces;

import javafx.scene.Scene;

public interface SceneState {
    void loadScene();
    void exitScene();
    Scene getScene();
}
