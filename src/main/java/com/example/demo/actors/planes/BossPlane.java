package com.example.demo.actors.planes;

import com.example.demo.actors.logic.BossMovementPattern;
import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.actors.projectiles.BossProjectile;
import com.example.demo.ui.ShieldImage;

public class BossPlane extends Plane {

	private static final String IMAGE_NAME = "boss_plane.png";
	private static final double INITIAL_X_POSITION = 800.0;
	private static final double INITIAL_Y_POSITION = 400;
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;
	private static final double BOSS_FIRE_RATE = .04;
	private static final double BOSS_SHIELD_PROBABILITY = 0.01;
	private static final int IMAGE_HEIGHT = 150;
	private static final int VERTICAL_VELOCITY = 8;
	private static final int HEALTH = 1; // easier testing
	private static final int MOVE_FREQUENCY_PER_CYCLE = 5;
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;
	private static final int Y_POSITION_UPPER_BOUND = 0;
	private static final int Y_POSITION_LOWER_BOUND = 600;
	private static final int MAX_FRAMES_WITH_SHIELD = 100; // easier testing
	private static ShieldImage shieldImage;
	private final BossMovementPattern bossMovementPattern;
	private boolean isShielded;
	private int framesWithShieldActivated;

	public BossPlane() {
		super(IMAGE_NAME, IMAGE_HEIGHT, INITIAL_X_POSITION, INITIAL_Y_POSITION, HEALTH);
		framesWithShieldActivated = 0;
		this.isShielded = false;
		shieldImage = new ShieldImage(1200, 15);
		this.bossMovementPattern = new BossMovementPattern(MAX_FRAMES_WITH_SAME_MOVE, VERTICAL_VELOCITY, MOVE_FREQUENCY_PER_CYCLE);

	}

	@Override
	public void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(bossMovementPattern.getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}
	
	@Override
	public void updateActor() {
		super.updateActor();
		updateShield();
	}

	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}
	
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	private void updateShield() {
		if (isShielded) {
			framesWithShieldActivated++;
			shieldImage.showShield();
		} else if (shieldShouldBeActivated()) {
			activateShield();
		}

		if (shieldExhausted()) {
			deactivateShield();
			shieldImage.hideShield();
		}
	}

	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	private void activateShield() {
		isShielded = true;
	}

	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
	}

	public ShieldImage getShieldImage() {
		return shieldImage;
	}
}
