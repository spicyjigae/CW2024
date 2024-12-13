package com.example.demo.levels;

import com.example.demo.actors.planes.BossPlane;
import com.example.demo.levels.logic.EnemyFactory;
import com.example.demo.levels.logic.LevelParent;

/**
 * LevelBoss class handles all initialization of the final level, Level Boss.
 */
public class LevelBoss extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background/background3.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;

	/**
	 * BossPlane object reference.
	 */
	private final BossPlane boss;

    public LevelBoss(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);

		double bossXPosition = 800;
		double bossInitialYPosition = Math.random() * getEnemyMaximumYPosition();
		boss = (BossPlane) EnemyFactory.createEnemy("BOSS", bossXPosition, bossInitialYPosition);
	}

	/**
	 * Checks if the game is over by seeing if either user is destroyed or boss is destroyed.
	 */
	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	/**
	 * Adds boss unit to the scene.
	 */
	@Override
	protected void spawnEnemyUnits() {
		if (actorManager.getNumberOfEnemies() == 0) {
			actorManager.addEnemyUnits(boss);
			getRoot().getChildren().add(boss.getShieldImage());
		}
	}

}
