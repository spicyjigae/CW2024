package com.example.demo.actors.templates;

import com.example.demo.interfaces.Destructible;

public abstract class ActiveActorDestructible extends ActiveActor implements Destructible {

	private boolean isDestroyed;

	public ActiveActorDestructible(String imageName, int imageHeight, double initialXPosition, double initialYPosition) {
		super(imageName, imageHeight, initialXPosition, initialYPosition);
		this.isDestroyed = false;
	}

    @Override
    public void takeDamage() {
        this.destroy();
    }

	@Override
	public void destroy() {
		this.isDestroyed = true;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}
	
}
