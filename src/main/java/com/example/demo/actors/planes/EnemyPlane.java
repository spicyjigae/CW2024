package com.example.demo.actors.planes;

import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.actors.projectiles.EnemyProjectile;

public class EnemyPlane extends Plane {

	private static final String IMAGE_NAME = "enemy_plane.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final double PROJECTILE_X_POSITION_OFFSET = -80.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 25.0;
	private static final int INITIAL_HEALTH = 1;
	private static final double FIRE_RATE = .01;

	public EnemyPlane(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos, INITIAL_HEALTH);
		this.HORIZONTAL_VELOCITY = -6;
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < FIRE_RATE) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}

}
