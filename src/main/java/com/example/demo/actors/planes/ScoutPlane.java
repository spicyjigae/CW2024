package com.example.demo.actors.planes;

public class ScoutPlane extends EnemyPlane {
    private static final String IMAGE_NAME = "planes/scout_plane.png";
    private static final int IMAGE_HEIGHT = 75;
    private static final int INITIAL_HEALTH = 1;
    private static final double FIRE_RATE = .01;

    public ScoutPlane (double InitialXPosition, double InitialYPosition) {
        super(IMAGE_NAME, IMAGE_HEIGHT, InitialXPosition, InitialYPosition, INITIAL_HEALTH);
        this.HORIZONTAL_VELOCITY = -6;
    }

    @Override
    protected double getFireRate() {
        return FIRE_RATE;
    }
}
