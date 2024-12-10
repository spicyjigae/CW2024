package com.example.demo.controller;

import com.example.demo.levels.logic.SceneManager;
import com.example.demo.scenes.MainMenuScene;
import javafx.stage.Stage;

public class Controller {

	private final SceneManager sceneManager;

	public Controller(Stage stage) {
		this.sceneManager = SceneManager.getInstance(stage);
	}

	public void launchGame() throws SecurityException, IllegalArgumentException {

		MainMenuScene mainMenuScene = new MainMenuScene(sceneManager);
		sceneManager.setState(mainMenuScene);

		SceneManager.getStage().show();
	}
}
