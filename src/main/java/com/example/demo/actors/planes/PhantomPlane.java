package com.example.demo.actors.planes;

import com.example.demo.actors.projectiles.PhantomProjectile;
import com.example.demo.actors.templates.ActiveActorDestructible;

/**
 * PhantomPlane class is a subclass of EnemyPlane.
 */
public class PhantomPlane extends EnemyPlane {
    private static final String IMAGE_NAME = "planes/phantom_plane.png";
    private static final int IMAGE_HEIGHT = 75;

    /**
     * Health of PhantomPlane.
     */
    private static final int INITIAL_HEALTH = 1;

    /**
     * Fire rate of PhantomPlane.
     */
    private static final double FIRE_RATE = .03;

    public PhantomPlane (double InitialXPosition, double InitialYPosition) {
        super(IMAGE_NAME, IMAGE_HEIGHT, InitialXPosition, InitialYPosition, INITIAL_HEALTH);
        this.HORIZONTAL_VELOCITY = -10;
    }

    /**
     * Retrieves the fire rate of PhantomPlane.
     * @return Fire rate of PhantomPlane.
     */
    protected double getFireRate() {
        return FIRE_RATE;
    }

    /**
     * Fires the projectile according to PhantomPlane's fire rate.
     * @return Either projectiles or null.
     */
    @Override
    public ActiveActorDestructible fireProjectile() {
        if (Math.random() < getFireRate()) {
            double projectileXPosition = getProjectileXPosition(PROJECTILE_X_POSITION_OFFSET);
            double projectileYPosition = getProjectileYPosition(PROJECTILE_Y_POSITION_OFFSET);
            return new PhantomProjectile(projectileXPosition, projectileYPosition);
        }
        return null;
    }
}
