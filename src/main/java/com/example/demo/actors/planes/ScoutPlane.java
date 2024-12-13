package com.example.demo.actors.planes;

import com.example.demo.actors.projectiles.ScoutProjectile;
import com.example.demo.actors.templates.ActiveActorDestructible;

/**
 * ScoutPlane class is a subclass of EnemyPlane.
 */
public class ScoutPlane extends EnemyPlane {
    private static final String IMAGE_NAME = "planes/scout_plane.png";
    private static final int IMAGE_HEIGHT = 75;

    /**
     * Health of ScoutPlane.
     */
    private static final int INITIAL_HEALTH = 3;

    /**
     * Fire rate of ScoutPlane.
     */
    private static final double FIRE_RATE = .01;

    public ScoutPlane (double InitialXPosition, double InitialYPosition) {
        super(IMAGE_NAME, IMAGE_HEIGHT, InitialXPosition, InitialYPosition, INITIAL_HEALTH);
        this.HORIZONTAL_VELOCITY = -6;
    }

    /**
     * Retrieves the fire rate of ScoutPlane.
     * @return Fire rate of ScoutPlane.
     */
    @Override
    protected double getFireRate() {
        return FIRE_RATE;
    }

    /**
     * Fires the projectile according to ScoutPlane's fire rate.
     * @return Either projectiles or null.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < getFireRate()) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return new ScoutProjectile(projectileXPosition, projectileYPosition);
        }
        return null;
    }
}
