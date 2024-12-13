package com.example.demo.actors.templates;

import com.example.demo.interfaces.Destructible;

/**
 * Subclasses of this class denote an active actor that is also destructible.
 */
public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	/**
	 * Denotes if the actor is destroyed or not.
	 */
	private boolean isDestroyed;

	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPosition, double initialYPosition) {
		super(imageName, imageHeight, initialXPosition, initialYPosition);
		this.isDestroyed = false;
	}

	/**
	 * Calls {@code destroy()} method by default, can be implemented to reduce health before getting destroyed.
	 */
    @Override
    public void takeDamage() {
        this.destroy();
    }

	/**
	 * Destroys actor.
	 */
	@Override
	public void destroy() {
		this.isDestroyed = true;
	}

	/**
	 * Returns a boolean to denote if actor is destroyed or not.
	 * @return True when actor is destroyed.
	 */
	public boolean isDestroyed() {
		return isDestroyed;
	}
	
}
