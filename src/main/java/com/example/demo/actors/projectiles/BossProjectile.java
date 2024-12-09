package com.example.demo.actors.projectiles;

public class BossProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "projectiles/boss_bullet.png";
	private static final int IMAGE_HEIGHT = 75;
	private static final int INITIAL_X_POSITION = 950;

	public BossProjectile(double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, initialYPos);
		this.HORIZONTAL_VELOCITY = -15;
	}
}
