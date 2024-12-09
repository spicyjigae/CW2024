package com.example.demo.actors.projectiles;

public class UserProjectile extends Projectile {

	private static final String IMAGE_NAME = "projectiles/user_bullet.png";
	private static final int IMAGE_HEIGHT = 25;

	public UserProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
		this.HORIZONTAL_VELOCITY = 15;
	}
}
