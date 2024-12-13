package com.example.demo.ui;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.Objects;

/**
 * ShieldImage class handles the initialization of the boss' shield.
 */
public class ShieldImage extends ImageView {
	
	private static final String IMAGE_NAME = "/com/example/demo/images/ui/boss_shield.png";
	private static final int SHIELD_SIZE = 75;
	
	public ShieldImage(double xPosition, double yPosition) {
		this.setLayoutX(xPosition);
		this.setLayoutY(yPosition);
		this.setImage(new Image(Objects.requireNonNull(getClass().getResource(IMAGE_NAME)).toExternalForm()));
		this.setVisible(false);
		this.setFitHeight(SHIELD_SIZE);
		this.setFitWidth(SHIELD_SIZE);
	}

	/**
	 * Sets the shield to be visible when it is active.
	 */
	public void showShield() {
		this.setVisible(true);
	}

	/**
	 * Hides the shield to be invisible when it is not active.
	 */
	public void hideShield() {
		this.setVisible(false);
	}

}
