package com.example.demo.levels;

import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.levels.logic.EnemyFactory;
import com.example.demo.levels.logic.LevelParent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * LevelTwo class handles all initialization of the second level, Level Two.
 */
public class LevelTwo extends LevelParent {

    private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background/background2.jpg";
    private static final String NEXT_LEVEL = "com.example.demo.levels.LevelBoss";

    /**
     * Total enemies to be spawned for the entirety of the level.
     */
    private static final int TOTAL_ENEMIES = 10;

    /**
     * Probability of the enemy to spawn in the current spawnTimeline frame.
     */
    private static final double ENEMY_SPAWN_PROBABILITY = .70;
    private static final int PLAYER_INITIAL_HEALTH = 5;

    /**
     * spawnTimeline timeline object reference creates a delay in spawning enemies.
     */
    private final Timeline spawnTimeline;

    /**
     * Keeps track of total enemies spawned so far.
     */
    private int totalEnemiesSpawned;

    public LevelTwo(double screenHeight, double screenWidth) {
        super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
        this.killsToAdvance = 10;

        spawnTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> spawnEnemyUnits()));
        spawnTimeline.setCycleCount(Timeline.INDEFINITE);
    }

    /**
     * Checks if the game is over by seeing if either user is destroyed or all enemies have been killed.
     */
    @Override
    protected void checkIfGameOver() {
        if (userIsDestroyed()) {
            loseGame();
        }
        else if (userHasReachedKillTarget()) {
            stopSpawning();
            goToNextLevel(NEXT_LEVEL);
        }
    }

    /**
     * Adds enemy plane units to the scene.
     */
    @Override
    protected void spawnEnemyUnits() {
        if (totalEnemiesSpawned < TOTAL_ENEMIES) {
            if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
                double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
                ActiveActorDestructible newEnemy = EnemyFactory.createEnemy("PHANTOM", getScreenWidth(), newEnemyInitialYPosition);

                actorManager.addEnemyUnits(newEnemy);
                totalEnemiesSpawned++;
            }
        }
    }
}