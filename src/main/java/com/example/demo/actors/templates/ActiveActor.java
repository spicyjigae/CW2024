package com.example.demo.actors.templates;

import javafx.scene.image.*;

import java.util.Objects;

public abstract class ActiveActor extends ImageView {
	
	private static final String IMAGE_LOCATION = "/com/example/demo/images/";
	protected int HORIZONTAL_VELOCITY = 0;

	public ActiveActor(String imageName, int imageHeight, double initialXPosition, double initialYPosition) {

		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_LOCATION + imageName)).toExternalForm()));
		this.setLayoutX(initialXPosition);
		this.setLayoutY(initialYPosition);
		this.setFitHeight(imageHeight);
		this.setPreserveRatio(true);
	}

	protected void updatePosition() {
		moveHorizontally(HORIZONTAL_VELOCITY);
	}

	public void updateActor() {
		updatePosition();
	}

	protected void moveHorizontally(double horizontalMove) {
		this.setTranslateX(getTranslateX() + horizontalMove);
	}

	protected void moveVertically(double verticalMove) {
		this.setTranslateY(getTranslateY() + verticalMove);
	}

}
