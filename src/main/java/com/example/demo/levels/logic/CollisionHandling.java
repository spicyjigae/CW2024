package com.example.demo.levels.logic;

import com.example.demo.actors.planes.UserPlane;
import com.example.demo.actors.templates.ActiveActorDestructible;

import java.util.List;

public class CollisionHandling {
    private final ActorManagement actorManagement;
    private final UserPlane user;

    public CollisionHandling(ActorManagement actorManagement, UserPlane user) {
        this.actorManagement = actorManagement;
        this.user = user;
    }

    public void handlePlaneCollisions() {
        handleCollisions(actorManagement.getFriendlyUnits(), actorManagement.getEnemyUnits());

    }

    public void handleUserProjectileCollisions() {
        handleCollisions(actorManagement.getUserProjectiles(), actorManagement.getEnemyUnits());

    }

    public void handleEnemyProjectileCollisions() {
        handleCollisions(actorManagement.getEnemyProjectiles(), actorManagement.getFriendlyUnits());
    }

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

    public void handleEnemyPenetration(double screenWidth) {
        for (ActiveActorDestructible enemy : actorManagement.getEnemyUnits()) {
            if (enemyHasPenetratedDefenses(enemy, screenWidth)) {
                user.takeDamage();
                enemy.destroy();
            }
        }
    }

    private boolean enemyHasPenetratedDefenses(ActiveActorDestructible enemy, double screenWidth) {
        return Math.abs(enemy.getTranslateX()) > screenWidth;
    }
}
