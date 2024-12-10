package com.example.demo.actors.planes;

import com.example.demo.actors.templates.ActiveActorDestructible;

public abstract class EnemyPlane extends Plane {

	protected static final double PROJECTILE_X_POSITION_OFFSET = -80.0;
	protected static final double PROJECTILE_Y_POSITION_OFFSET = 25.0;

	public EnemyPlane(String imageName, int imageHeight, double initialXPosition, double initialYPosition, int INITIAL_HEALTH) {
		super(imageName, imageHeight, initialXPosition, initialYPosition, INITIAL_HEALTH);
	}

	protected abstract double getFireRate();

	public abstract ActiveActorDestructible fireProjectile();
}
