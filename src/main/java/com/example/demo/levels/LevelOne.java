package com.example.demo.levels;

import com.example.demo.actors.planes.ScoutPlane;
import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.levels.logic.LevelParent;

public class LevelOne extends LevelParent {
	
	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background/background1.jpg";
	private static final String NEXT_LEVEL = "com.example.demo.levels.LevelBoss";
	private static final int TOTAL_ENEMIES = 5;
	private static final double ENEMY_SPAWN_PROBABILITY = .20;
	private static final int PLAYER_INITIAL_HEALTH = 5;

	public LevelOne(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		this.killsToAdvance = 5;
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (userHasReachedKillTarget()) {
			goToNextLevel(NEXT_LEVEL);
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		int currentNumberOfEnemies = actorManager.getNumberOfEnemies();
		for (int i = 0; i < TOTAL_ENEMIES - currentNumberOfEnemies; i++) {
			if (Math.random() < ENEMY_SPAWN_PROBABILITY) {
				double newEnemyInitialYPosition = Math.random() * getEnemyMaximumYPosition();
				ActiveActorDestructible newEnemy = new ScoutPlane(getScreenWidth(), newEnemyInitialYPosition);
				actorManager.addEnemyUnits(newEnemy);
			}
		}
	}

}
