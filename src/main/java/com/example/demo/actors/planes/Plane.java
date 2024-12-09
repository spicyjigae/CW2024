package com.example.demo.actors.planes;

import com.example.demo.actors.templates.ActiveActorDestructible;

public abstract class Plane extends ActiveActorDestructible {

	private int health;

	public Plane(String imageName, int imageHeight, double initialXPosition, double initialYPosition, int health) {
		super(imageName, imageHeight, initialXPosition, initialYPosition);
		this.health = health;
	}

	public abstract ActiveActorDestructible fireProjectile();

	@Override
	public void takeDamage() {
		this.health--;
		if (healthAtZero()) {
			super.takeDamage();
		}
	}

	protected double getProjectileXPosition(double xPositionOffset) {
		return getLayoutX() + getTranslateX() + xPositionOffset;
	}

	protected double getProjectileYPosition(double yPositionOffset) {
		return getLayoutY() + getTranslateY() + yPositionOffset;
	}

	private boolean healthAtZero() {
		return health == 0;
	}

	public int getHealth() {
		return health;
	}
}
