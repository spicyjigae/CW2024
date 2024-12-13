package com.example.demo.actors.templates;

import javafx.scene.image.*;

import java.util.Objects;

/**
 * Subclasses of this class denote an active actor.
 */
public abstract class ActiveActor extends ImageView {
	
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";

	/**
	 * Horizontal velocity of actors used for reference for implementing reusable concrete methods.
	 */
	protected int HORIZONTAL_VELOCITY = 0;

	public ActiveActor(String imageName, int imageHeight, double initialXPosition, double initialYPosition) {

		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_LOCATION + imageName)).toExternalForm()));
		this.setLayoutX(initialXPosition);
		this.setLayoutY(initialYPosition);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	/**
	 * Updates the position of actors.
	 */
	protected void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	/**
	 * Updates actor state in the current frame.
	 */
	public void updateActor() {
		updatePosition();
	}

	/**
	 * Moves actor horizontally.
	 * @param horizontalMove Horizontal movement.
	 */
	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	/**
	 * Moves actor vertically.
	 * @param verticalMove Vertical movement.
	 */
	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}
