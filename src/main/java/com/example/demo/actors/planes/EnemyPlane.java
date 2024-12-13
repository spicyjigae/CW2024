package com.example.demo.actors.planes;

import com.example.demo.actors.templates.ActiveActorDestructible;

/**
 * Subclasses of this class denote an enemy plane actor.
 */
public abstract class EnemyPlane extends Plane {

	/**
	 * Horizontal projectile offset relative to the enemy plane.
	 */
	protected static final double PROJECTILE_X_POSITION_OFFSET = -80.0;

	/**
	 * Vertical projectile offset relative to the enemy plane.
	 */
	protected static final double PROJECTILE_Y_POSITION_OFFSET = 25.0;

	public EnemyPlane(String imageName, int imageHeight, double initialXPosition, double initialYPosition, int health) {
		super(imageName, imageHeight, initialXPosition, initialYPosition, health);
	}

	/**
	 * Abstract method for retrieving unique implementation of fire rates across all enemy plane types.
	 * @return Fire rate of a particular enemy plane.
	 */
	protected abstract double getFireRate();

	/**
	 * Fires projectiles according to the fire rate of any particular enemy plane.
	 * @return Either projectiles or null.
	 */
	public abstract ActiveActorDestructible fireProjectile();
}
