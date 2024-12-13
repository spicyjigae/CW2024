package com.example.demo.levels.logic;

import java.util.*;

import com.example.demo.actors.planes.Plane;
import com.example.demo.actors.templates.ActiveActor;
import com.example.demo.actors.templates.ActiveActorDestructible;
import javafx.scene.Group;

/**
 * ActorManager class manages addition and removal of actors.
 */
public class ActorManager {

    /**
     * JavaFX Group object reference
     */
    private final Group root;

    /**
     * List for storing all friendly units.
     */
    private final List<ActiveActorDestructible> friendlyUnits;

    /**
     * List for storing all enemy units.
     */
    private final List<ActiveActorDestructible> enemyUnits;

    /**
     * List for storing all user projectiles.
     */
    private final List<ActiveActorDestructible> userProjectiles;

    /**
     * List for storing all enemy projectiles.
     */
    private final List<ActiveActorDestructible> enemyProjectiles;

    public ActorManager(Group root) {
        this.root = root;
        this.friendlyUnits = new ArrayList<>();
        this.enemyUnits = new ArrayList<>();
        this.userProjectiles = new ArrayList<>();
        this.enemyProjectiles = new ArrayList<>();
    }

    /**
     * Retrieves friendlyUnits list.
     * @return friendlyUnits list.
     */
    public List<ActiveActorDestructible> getFriendlyUnits() {
        return friendlyUnits;
    }

    /**
     * Retrieves enemyUnits list.
     * @return enemyUnits list.
     */
    public List<ActiveActorDestructible> getEnemyUnits() {
        return enemyUnits;
    }

    /**
     * Retrieves userProjectiles list.
     * @return userProjectiles list.
     */
    public List<ActiveActorDestructible> getUserProjectiles() {
        return userProjectiles;
    }

    /**
     * Retrieves enemyProjectiles list.
     * @return enemyProjectiles list.
     */
    public List<ActiveActorDestructible> getEnemyProjectiles() {
        return enemyProjectiles;
    }

    /**
     * Retrieves current number of enemies.
     * @return Current number of enemies.
     */
    public int getNumberOfEnemies() {
        return enemyUnits.size();
    }

    /**
     * Adds friendly units to the friendlyUnits list.
     * @param friendlyUnit Friendly units object.
     */
    public void addFriendlyUnits(ActiveActorDestructible friendlyUnit) {
        friendlyUnits.add(friendlyUnit);
        root.getChildren().add(friendlyUnit);
    }

    /**
     * Adds enemy units to the enemyUnits list.
     * @param enemyUnit Enemy units object.
     */
    public void addEnemyUnits(ActiveActorDestructible enemyUnit) {
        enemyUnits.add(enemyUnit);
        root.getChildren().add(enemyUnit);
    }

    /**
     * Update all currently active actors.
     */
    public void updateActors() {
        friendlyUnits.forEach(ActiveActor::updateActor);
        enemyUnits.forEach(ActiveActor::updateActor); //
        userProjectiles.forEach(ActiveActor::updateActor);
        enemyProjectiles.forEach(ActiveActor::updateActor); //
    }

    /**
     * Removes destroyed actors from the scene.
     * @param actors List of actors.
     */
    public void removeDestroyedActors(List<ActiveActorDestructible> actors) {
        List<ActiveActorDestructible> destroyedActors = actors.stream().filter(ActiveActorDestructible::isDestroyed)
                .toList();
        root.getChildren().removeAll(destroyedActors);
        actors.removeAll(destroyedActors);
    }

    /**
     * Removes all destroyed actors.
     */
    public void removeAllDestroyedActors() {
        removeDestroyedActors(friendlyUnits);
        removeDestroyedActors(enemyUnits);
        removeDestroyedActors(userProjectiles);
        removeDestroyedActors(enemyProjectiles);
    }

    /**
     * Add user projectiles to the userProjectiles list.
     * @param userProjectile User projectile.
     */
    public void addUserProjectiles(ActiveActorDestructible userProjectile) {
        userProjectiles.add(userProjectile);
    }

    /**
     * Generates enemy projectiles.
     */
    public void generateEnemyFire() {
        enemyUnits.forEach(enemy -> spawnEnemyProjectile(((Plane) enemy).fireProjectile()));
    }

    /**
     * Individually generates enemy projectiles for each enemy type.
     * @param projectile Specific enemy projectiles for each enemy type.
     */
    private void spawnEnemyProjectile(ActiveActorDestructible projectile) {
        if (projectile != null) {
            root.getChildren().add(projectile);
            enemyProjectiles.add(projectile);
        }
    }
}
