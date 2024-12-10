package com.example.demo.actors.projectiles;

public class ScoutProjectile extends EnemyProjectile {

    public ScoutProjectile(double initialXPos, double initialYPos) {
        super(initialXPos, initialYPos);
        this.HORIZONTAL_VELOCITY = -10;
    }
}
