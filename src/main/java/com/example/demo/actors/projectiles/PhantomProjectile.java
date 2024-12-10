package com.example.demo.actors.projectiles;

public class PhantomProjectile extends EnemyProjectile {

    public PhantomProjectile(double initialXPos, double initialYPos) {
        super(initialXPos, initialYPos);
        this.HORIZONTAL_VELOCITY = -20;
    }
}
