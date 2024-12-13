package com.example.demo.actors.planes;

import com.example.demo.actors.templates.ActiveActorDestructible;

/**
 * Subclasses of this class denote a plane actor.
 */
public abstract class Plane extends ActiveActorDestructible {

	/**
	 * Health of plane used for reference for implementing reusable concrete methods.
	 */
	private int health;

	public Plane(String imageName, int imageHeight, double initialXPosition, double initialYPosition, int health) {
		super(imageName, imageHeight, initialXPosition, initialYPosition);
		this.health = health;
	}

	/**
	 * Fires projectiles of any particular plane.
	 * @return Either projectiles or null.
	 */
	public abstract ActiveActorDestructible fireProjectile();

	/**
	 * Decreases health and destroys object if at zero health.
	 */
	@Override
	public void takeDamage() {
		this.health--;
		if (healthAtZero()) {
			super.takeDamage();
		}
	}

	/**
	 * Calculates the horizontal position of the projectile.
	 * @param xPositionOffset Horizontal projectile offset relative to the plane.
	 * @return Horizontal position of the projectile.
	 */
	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	/**
	 * Calculates the vertical position of the projectile
	 * @param yPositionOffset Vertical projectile offset relative to the plane.
	 * @return Vertical position of the projectile.
	 */
	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	/**
	 * Denotes health is at zero.
	 * @return True when health is zero.
	 */
	private boolean healthAtZero() {
		return health == 0;
	}

	/**
	 * Retrieves current health of plane.
	 * @return Health of plane.
	 */
	public int getHealth() {
		return health;
	}
}
