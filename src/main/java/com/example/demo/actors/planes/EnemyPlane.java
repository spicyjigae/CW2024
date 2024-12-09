package com.example.demo.actors.planes;

import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.actors.projectiles.EnemyProjectile;

public abstract class EnemyPlane extends Plane {

	private static final double PROJECTILE_X_POSITION_OFFSET = -80.0;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 25.0;

	public EnemyPlane(String imageName, int imageHeight, double initialXPosition, double initialYPosition, int INITIAL_HEALTH) {
		super(imageName, imageHeight, initialXPosition, initialYPosition, INITIAL_HEALTH);
	}

	protected abstract double getFireRate();

	@Override
	public ActiveActorDestructible fireProjectile() {
		if (Math.random() < getFireRate()) {
			double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
			double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
			return new EnemyProjectile(projectileXPosition, projectileYPosition);
		}
		return null;
	}
}
