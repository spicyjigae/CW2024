package com.example.demo.actors.planes;

import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.actors.projectiles.UserProjectile;

public class UserPlane extends Plane {

	private static final String IMAGE_NAME = "planes/user_plane.png";
	private static final double Y_UPPER_BOUND = 0;
	private static final double Y_LOWER_BOUND = 650;
	private static final double INITIAL_X_POSITION = 5.0;
	private static final double INITIAL_Y_POSITION = 300.0;
	private static final int IMAGE_HEIGHT = 45;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int PROJECTILE_X_POSITION_OFFSET = 150;
	private static final int PROJECTILE_Y_POSITION_OFFSET = 15;

	private int velocityMultiplier;
	private int numberOfKills;

	public UserPlane(int initialHealth) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, initialHealth);
		this.velocityMultiplier = 0;
	}
	
	@Override
	protected void updatePosition() {
		if (isMoving()) {
			double initialTranslateY = getTranslateY();
			this.moveVertically(VERTICAL_VELOCITY * velocityMultiplier);
			double newPosition = getLayoutY() + getTranslateY();
			if (newPosition < Y_UPPER_BOUND || newPosition > Y_LOWER_BOUND) {
				this.setTranslateY(initialTranslateY);
			}
		}
	}

	
	@Override
	public ActiveActorDestructible fireProjectile() {
		return new UserProjectile(getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET), getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET));
	}

	private boolean isMoving() {
		return velocityMultiplier != 0;
	}

	public void moveUp() {
		this.velocityMultiplier = -1;
	}

	public void moveDown() {
		this.velocityMultiplier = 1;
	}

	public void stop() {
		this.velocityMultiplier = 0;
	}

	public int getNumberOfKills() {
		return numberOfKills;
	}

	public void incrementKillCount(int killsInLastFrame) {
		this.numberOfKills += killsInLastFrame;
	}

}
