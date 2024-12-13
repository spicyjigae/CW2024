package com.example.demo.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

import java.util.Objects;

/**
 * HeartDisplay class handles the logic for the user hearts display UI.
 */
public class HeartDisplay {
	
	private static final String HEART_IMAGE_NAME = "/com/example/demo/images/ui/user_heart.png";
	private static final int HEART_HEIGHT = 50;
	private static final int INDEX_OF_FIRST_ITEM = 0;

	/**
	 * Horizontal container position.
	 */
	private final double containerXPosition;

	/**
	 * Vertical container position.
	 */
	private final double containerYPosition;
	private final int numberOfHeartsToDisplay;

	/**
	 * JavaFX HBox object container.
	 */
	private HBox container;
	
	public HeartDisplay(double xPosition, double yPosition, int heartsToDisplay) {
		this.containerXPosition = xPosition;
		this.containerYPosition = yPosition;
		this.numberOfHeartsToDisplay = heartsToDisplay;
		initializeContainer();
		initializeHearts();
	}

	/**
	 * Initializes the container for the hearts.
	 */
	private void initializeContainer() {
		container = new HBox();
		container.setLayoutX(containerXPosition);
		container.setLayoutY(containerYPosition);		
	}

	/**
	 * Initializes the hearts that denotes the health of the user.
	 */
	private void initializeHearts() {
		for (int i = 0; i < numberOfHeartsToDisplay; i++) {
			ImageView heart = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(HEART_IMAGE_NAME)).toExternalForm()));

			heart.setFitHeight(HEART_HEIGHT);
			heart.setPreserveRatio(true);
			container.getChildren().add(heart);
		}
	}

	/**
	 * Removes hearts based on current health of the user.
	 */
	public void removeHeart() {
		if (!container.getChildren().isEmpty())
			container.getChildren().remove(INDEX_OF_FIRST_ITEM);
	}

	/**
	 * Retrieves the container for the hearts display.
	 * @return Hearts display container.
	 */
	public HBox getContainer() {
		return container;
	}

}
