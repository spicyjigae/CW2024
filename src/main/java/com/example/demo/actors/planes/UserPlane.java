package com.example.demo.actors.planes;

import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.actors.projectiles.UserProjectile;

/**
 * UserPlane class handles all user related mechanics such as vertical and horizontal movement, projectile firing and kill incrementing.
 */
public class UserPlane extends Plane {

	private static final String IMAGE_NAME = "planes/user_plane.png";

	/**
	 * Upper vertical bound of UserPlane.
	 */
	private static final double Y_UPPER_BOUND = 0;

	/**
	 * Lower vertical bound of UserPlane.
	 */
	private static final double Y_LOWER_BOUND = 650;

	/**
	 * Left horizontal bound of UserPlane.
	 */
	private static final double X_LEFT_BOUND = 0;

	/**
	 * Right horizontal bound of UserPlane.
	 */
	private static final double X_RIGHT_BOUND = 400;

	/**
	 * Initial horizontal position of UserPlane.
	 */
	private static final double INITIAL_X_POSITION = 5.0;

	/**
	 * Initial vertical position of UserPlane.
	 */
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 45;

	/**
	 * General velocity of UserPlane.
	 */
	private static final int VELOCITY = 8;

	/**
	 * Horizontal projectile offset relative to the user plane.
	 */
	private static final int PROJECTILE_X_POSITION_OFFSET = 150;

	/**
	 * Vertical projectile offset relative to the user plane.
	 */
	private static final int PROJECTILE_Y_POSITION_OFFSET = 15;

	/**
	 * Vertical velocity multiplier.
	 */
    private int verticalVelocityMultiplier;

	/**
	 * Horizontal velocity multiplier.
	 */
	private int horizontalVelocityMultiplier;

	/**
	 * Tracks number of kills by user in the current level.
	 */
	private int numberOfKills;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		this.verticalVelocityMultiplier = 0;
		this.horizontalVelocityMultiplier = 0;
	}

	/**
	 * Updates the position of the user plane based on user movements.
	 */
	@Override
	protected void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();

			this.moveVertically(VELOCITY * verticalVelocityMultiplier);

			double newVerticalPosition = getLayoutY() + getTranslateY();
			if (newVerticalPosition < Y_UPPER_BOUND || newVerticalPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}

			double initialTranslateX = getTranslateX();

			this.moveHorizontally(VELOCITY * horizontalVelocityMultiplier);

			double newHorizontalPosition = getLayoutX() + getTranslateX();
			if (newHorizontalPosition < X_LEFT_BOUND || newHorizontalPosition > X_RIGHT_BOUND) {
				this.setTranslateX(initialTranslateX);
			}


		}
	}

	/**
	 * Fires projectiles when user inputs are detected.
	 * @return User projectiles.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	private boolean isMoving() {
		return verticalVelocityMultiplier != 0 || horizontalVelocityMultiplier != 0;
	}

	public void moveUp() {
		this.verticalVelocityMultiplier = -1;
	}

	public void moveDown() {
		this.verticalVelocityMultiplier = 1;
	}

	public void moveLeft() {
		this.horizontalVelocityMultiplier = -1;
	}

	public void moveRight() {
		this.horizontalVelocityMultiplier = 1;
	}

	public void stopVertical() {
		this.verticalVelocityMultiplier = 0;
	}

	public void stopHorizontal() {
		this.horizontalVelocityMultiplier = 0;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	/**
	 * Increments the user kill count.
	 * @param killsInLastFrame User kills in the last frame.
	 */
	public void incrementKillCount(int killsInLastFrame) {
		this.numberOfKills += killsInLastFrame;
	}

}
