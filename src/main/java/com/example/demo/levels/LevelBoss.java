package com.example.demo.levels;

import com.example.demo.actors.planes.BossPlane;
import com.example.demo.levels.logic.LevelParent;

public class LevelBoss extends LevelParent {

	private static final String BACKGROUND_IMAGE_NAME = "/com/example/demo/images/background/background2.jpg";
	private static final int PLAYER_INITIAL_HEALTH = 5;

	private final BossPlane boss;

    public LevelBoss(double screenHeight, double screenWidth) {
		super(BACKGROUND_IMAGE_NAME, screenHeight, screenWidth, PLAYER_INITIAL_HEALTH);
		boss = new BossPlane();
	}

	@Override
	protected void checkIfGameOver() {
		if (userIsDestroyed()) {
			loseGame();
		}
		else if (boss.isDestroyed()) {
			winGame();
		}
	}

	@Override
	protected void spawnEnemyUnits() {
		if (actorManager.getNumberOfEnemies() == 0) {
			actorManager.addEnemyUnits(boss);
			getRoot().getChildren().add(boss.getShieldImage());
		}
	}

}
