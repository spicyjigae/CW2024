package com.example.demo.levels;

import com.example.demo.ui.HeartDisplay;
import javafx.scene.Group;

/**
 * LevelView class handles the UI for each level.
 */
public class LevelView {
	
	private static final double HEART_DISPLAY_X_POSITION = 5;
	private static final double HEART_DISPLAY_Y_POSITION = 25;

	/**
	 * JavaFX Group object reference.
	 */
	private final Group root;

	/**
	 * HeartDisplay object reference.
	 */
	private final HeartDisplay heartDisplay;
	
	public LevelView(Group root, int heartsToDisplay) {
		this.root = root;
		this.heartDisplay = new HeartDisplay(HEART_DISPLAY_X_POSITION, HEART_DISPLAY_Y_POSITION, heartsToDisplay);
	}

	/**
	 * Adds heart display to the scene.
	 */
	public void showHeartDisplay() {
		root.getChildren().add(heartDisplay.getContainer());
	}

	/**
	 * Updates heart count based on hearts remaining.
	 * @param heartsRemaining Hearts remaining.
	 */
	public void updateHearts(int heartsRemaining) {
		int currentNumberOfHearts = heartDisplay.getContainer().getChildren().size();
		for (int i = 0; i < currentNumberOfHearts - heartsRemaining; i++) {
			heartDisplay.removeHeart();
		}
	}

}
