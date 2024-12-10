package com.example.demo.actors.planes;

import com.example.demo.actors.projectiles.PhantomProjectile;
import com.example.demo.actors.templates.ActiveActorDestructible;

public class PhantomPlane extends EnemyPlane {
    private static final String IMAGE_NAME = "planes/phantom_plane.png";
    private static final int IMAGE_HEIGHT = 75;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = .03;

    public PhantomPlane (double InitialXPosition, double InitialYPosition) {
        super(IMAGE_NAME, IMAGE_HEIGHT, InitialXPosition, InitialYPosition, INITIAL_HEALTH);
        this.HORIZONTAL_VELOCITY = -10;
    }

    protected double getFireRate() {
        return FIRE_RATE;
    }

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
