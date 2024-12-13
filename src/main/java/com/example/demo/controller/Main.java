package com.example.demo.controller;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Main class handles the launching of the actual application.
 */
public class Main extends Application {

	/**
	 * Screen width of the application.
	 */
	private static final int SCREEN_WIDTH = 1300;

	/**
	 * Screen height of the application.
	 */
	private static final int SCREEN_HEIGHT = 750;

	/**
	 * Title of the application.
	 */
	private static final String TITLE = "Sky Battle";

	/**
	 * Start method handles stage and Controller object initialization.
	 * @param stage JavaFX stage parameters denote the actual application stage where scenes reside.
	 * @throws SecurityException Indicates a security violation.
	 * @throws IllegalArgumentException Indicates a method has been passed with an inappropriate argument.
	 */
    @Override
	public void start(Stage stage) throws SecurityException, IllegalArgumentException {
		stage.setTitle(TITLE);
		stage.setResizable(false);
		stage.setHeight(SCREEN_HEIGHT);
		stage.setWidth(SCREEN_WIDTH);
        Controller myController = new Controller(stage);
		myController.launchGame();
	}

	/**
	 * Default java main method handles program running.
	 * @param args Default java main method args.
	 */
	public static void main(String[] args) {
		launch();
	}
}