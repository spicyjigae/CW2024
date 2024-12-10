package com.example.demo.controller;

import com.example.demo.levels.LevelOne;
import com.example.demo.levels.logic.SceneManager;
import com.example.demo.scenes.GameplayScene;
import javafx.stage.Stage;
import com.example.demo.levels.logic.LevelParent;

public class Controller {

	private final SceneManager sceneManager;

	public Controller(Stage stage) {
		this.sceneManager = SceneManager.getInstance(stage);
	}

	public void launchGame() throws SecurityException, IllegalArgumentException {
		LevelParent levelOne = new LevelOne(sceneManager.getStage().getHeight(), sceneManager.getStage().getWidth());

		GameplayScene gameplayScene = new GameplayScene(sceneManager, levelOne);
		sceneManager.setState(gameplayScene);

		sceneManager.getStage().show();
	}
}
