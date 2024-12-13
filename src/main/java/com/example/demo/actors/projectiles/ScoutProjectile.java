package com.example.demo.actors.projectiles;

/**
 * ScoutProjectile class is a subclass of EnemyProjectile.
 */
public class ScoutProjectile extends EnemyProjectile {

    public ScoutProjectile(double initialXPos, double initialYPos) {
        super(initialXPos, initialYPos);
        this.HORIZONTAL_VELOCITY = -10;
    }
}
