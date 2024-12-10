package com.example.demo.actors.projectiles;

public class EnemyProjectile extends Projectile {
	
	private static final String IMAGE_NAME = "projectiles/enemy_bullet.png";
	private static final int IMAGE_HEIGHT = 25;

	public EnemyProjectile(double initialXPos, double initialYPos) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPos, initialYPos);
	}
}
