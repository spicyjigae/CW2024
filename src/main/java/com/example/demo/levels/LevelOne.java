package com.example.demo.levels;

import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.levels.logic.EnemyFactory;
import com.example.demo.levels.logic.LevelParent;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class LevelOne extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelTwo";
	private static final int TOTAL_ENEMIES = 5; // should be 10 before submitting
	private static final double ENEMY_SPAWN_PROBABILITY = .80;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	private final Timeline spawnTimeline;

	private int totalEnemiesSpawned;

	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		this.killsToAdvance = 5; // should be 10 before submitting

		spawnTimeline = new Timeline(new KeyFrame(Duration.seconds(0.5), e -> spawnEnemyUnits()));
		spawnTimeline.setCycleCount(Timeline.INDEFINITE);
	}

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

	@Override
	protected void spawnEnemyUnits() {
		if (totalEnemiesSpawned < TOTAL_ENEMIES) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = EnemyFactory.createEnemy("SCOUT", getScreenWidth(), newEnemyInitialYPosition);

				actorManager.addEnemyUnits(newEnemy);
				totalEnemiesSpawned++;
			}
		}
	}
}
