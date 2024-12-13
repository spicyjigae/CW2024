package com.example.demo.levels.logic;

import com.example.demo.actors.planes.UserPlane;
import com.example.demo.actors.templates.ActiveActorDestructible;

import java.util.List;

/**
 * CollisionHandler class handles any collisions between actors.
 */
public class CollisionHandler {

    /**
     * ActorManager object reference.
     */
    private final ActorManager actorManager;

    /**
     * UserPlane object reference.
     */
    private final UserPlane user;

    public CollisionHandler(ActorManager actorManager, UserPlane user) {
        this.actorManager = actorManager;
        this.user = user;
    }

    /**
     * Handles collisions between user planes and enemy planes.
     */
    public void handlePlaneCollisions() {
        handleCollisions(actorManager.getFriendlyUnits(), actorManager.getEnemyUnits());
    }

    /**
     * Handles collisions between user projectiles and enemy units.
     */
    public void handleUserProjectileCollisions() {
        handleCollisions(actorManager.getUserProjectiles(), actorManager.getEnemyUnits());
    }

    /**
     * Handles collisions between enemy projectiles and friendly units.
     */
    public void handleEnemyProjectileCollisions() {
        handleCollisions(actorManager.getEnemyProjectiles(), actorManager.getFriendlyUnits());
    }

    /**
     * Logic for handling collisions between any two actors.
     * @param actors1 First actor.
     * @param actors2 Second actor.
     */
    private void handleCollisions(List<ActiveActorDestructible> actors1,
                                  List<ActiveActorDestructible> actors2) {
        for (ActiveActorDestructible actor : actors2) {
            for (ActiveActorDestructible otherActor : actors1) {
                if (actor.getBoundsInParent().intersects(otherActor.getBoundsInParent())) {
                    actor.takeDamage();
                    otherActor.takeDamage();
                }
            }
        }
    }

    /**
     * Handles enemy penetration by destroying enemy planes and making user take damage.
     *
     * @param screenWidth Screen width.
     */
    public void handleEnemyPenetration(double screenWidth) {
        for (ActiveActorDestructible enemy : actorManager.getEnemyUnits()) {
            if (enemyHasPenetratedDefenses(enemy, screenWidth)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    /**
     * Denotes if enemy has penetrated user defenses (left screen border) or not.
     *
     * @param enemy Enemy unit.
     * @param screenWidth Screen width.
     * @return True when an enemy has penetrated user defenses.
     */
    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy, double screenWidth) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }
}
