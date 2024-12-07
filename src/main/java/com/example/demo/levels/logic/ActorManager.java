package com.example.demo.levels.logic;

import java.util.*;
import java.util.stream.Collectors;

import com.example.demo.actors.planes.Plane;
import com.example.demo.actors.templates.ActiveActorDestructible;
import javafx.scene.Group;

public class ActorManager {

    private final Group root;

    private final List<ActiveActorDestructible> friendlyUnits;
    private final List<ActiveActorDestructible> enemyUnits;
    private final List<ActiveActorDestructible> userProjectiles;
    private final List<ActiveActorDestructible> enemyProjectiles;

    public ActorManager(Group root) {
        this.root = root;
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
    }

    public List<ActiveActorDestructible> getFriendlyUnits() {
        return friendlyUnits;
    }

    public List<ActiveActorDestructible> getEnemyUnits() {
        return enemyUnits;
    }

    public List<ActiveActorDestructible> getUserProjectiles() {
        return userProjectiles;
    }

    public List<ActiveActorDestructible> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    public int getNumberOfEnemies() {
        return enemyUnits.size();
    }

    public void addFriendlyUnits(ActiveActorDestructible friendlyUnit) {
        friendlyUnits.add(friendlyUnit);
        root.getChildren().add(friendlyUnit);
    }

    public void addEnemyUnits(ActiveActorDestructible enemyUnit) {
        enemyUnits.add(enemyUnit);
        root.getChildren().add(enemyUnit);
    }

    public void updateActors() {
        friendlyUnits.forEach(plane -> plane.updateActor());
        enemyUnits.forEach(enemy -> enemy.updateActor()); //
        userProjectiles.forEach(projectile -> projectile.updateActor());
        enemyProjectiles.forEach(projectile -> projectile.updateActor()); //
    }

    public void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream().filter(actor -> actor.isDestroyed())
                .collect(Collectors.toList());
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    public void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    public void addUserProjectiles(ActiveActorDestructible userProjectile) {
        userProjectiles.add(userProjectile);
    }

    public void generateEnemyFire() {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((Plane) enemy).fireProjectile()));
    }

    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }

}
