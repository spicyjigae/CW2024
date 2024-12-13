package com.example.demo.actors.projectiles;

import com.example.demo.actors.templates.ActiveActorDestructible;

/**
 * Subclasses of this class denote a projectile class.
 */
public abstract class Projectile extends ActiveActorDestructible {

	public Projectile(String imageName, int imageHeight, double initialXPos, double initialYPos) {
		super(imageName, imageHeight, initialXPos, initialYPos);
	}

}
