package com.example.demo.controller;

import com.example.demo.levels.logic.SceneManager;
import com.example.demo.scenes.SceneType;
import javafx.stage.Stage;

/**
 * Controller class handles game launching and which scene to show first to the user.
 */
public class Controller {

	/**
	 * SceneManager object reference.
	 */
	private final SceneManager sceneManager;

	public Controller(Stage stage) {
		this.sceneManager = SceneManager.getInstance(stage);
	}

	/**
	 * Launches the game by showing the Main Menu Scene.
	 * @throws SecurityException Indicates a security violation.
	 * @throws IllegalArgumentException Indicates a method has been passed with an inappropriate argument.
	 */
	public void launchGame() throws SecurityException, IllegalArgumentException {

		sceneManager.onEventChange((String.valueOf(SceneType.MAIN_MENU)));

		SceneManager.getStage().show();
	}
}
