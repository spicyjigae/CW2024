package com.example.demo.actors.planes;

import com.example.demo.actors.logic.BossMovementPattern;
import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.actors.projectiles.BossProjectile;
import com.example.demo.ui.ShieldImage;

/**
 * BossPlane class handles all boss related mechanics such as updating the vertical position
 * based on a movement pattern, shield and firing.
 */
public class BossPlane extends EnemyPlane {

	private static final String IMAGE_NAME = "planes/boss_plane.png";

	/**
	 * Vertical projectile offset of the boss.
	 */
	private static final double PROJECTILE_Y_POSITION_OFFSET = 75.0;

	/**
	 * Fire rate of the boss.
	 */
	private static final double BOSS_FIRE_RATE = .04;

	/**
	 * Probability for the boss to be shielded.
	 */
	private static final double BOSS_SHIELD_PROBABILITY = 0.01;
	private static final int IMAGE_HEIGHT = 150;

	/**
	 * Vertical velocity of the boss.
	 */
	private static final int VERTICAL_VELOCITY = 8;

	/**
	 * Health of the boss.
	 */
	private static final int HEALTH = 25;

	/**
	 * Number of movement cycles to initialize.
	 */
	private static final int MOVEMENT_CYCLES = 5;

	/**
	 * Maximum frames with the same movement.
	 */
	private static final int MAX_FRAMES_WITH_SAME_MOVE = 10;

	/**
	 * Upper vertical bound of the boss plane.
	 */
	private static final int Y_POSITION_UPPER_BOUND = 0;

	/**
	 * Lower vertical bound of the boss plane.
	 */
	private static final int Y_POSITION_LOWER_BOUND = 600;

	/**
	 * Maximum number of frames for boss to have a shield.
	 */
	private static final int MAX_FRAMES_WITH_SHIELD = 50;

	/**
	 * ShieldImage object reference.
	 */
	private final ShieldImage shieldImage;

	/**
	 * BossMovementPattern object reference.
	 */
	private final BossMovementPattern bossMovementPattern;

	/**
	 * Denotes if boss is currently shielded or not.
	 */
	private boolean isShielded;

	/**
	 * Tracks how many frames the boss' shield have been activated.
	 */
	private int framesWithShieldActivated;

	public BossPlane(double initialXPosition, double initialYPosition) {
		super(IMAGE_NAME, IMAGE_HEIGHT, initialXPosition, initialYPosition, HEALTH);
		framesWithShieldActivated = 0;
		this.isShielded = false;
		this.shieldImage = new ShieldImage(1200, 15);
		this.bossMovementPattern = new BossMovementPattern(MAX_FRAMES_WITH_SAME_MOVE, VERTICAL_VELOCITY, MOVEMENT_CYCLES);
	}

	/**
	 * Updates the vertical position of the boss by utilizing the bossMovementPattern object.
	 */
	@Override
	protected void updatePosition() {
		double initialTranslateY = getTranslateY();
		moveVertically(bossMovementPattern.getNextMove());
		double currentPosition = getLayoutY() + getTranslateY();
		if (currentPosition < Y_POSITION_UPPER_BOUND || currentPosition > Y_POSITION_LOWER_BOUND) {
			setTranslateY(initialTranslateY);
		}
	}

	/**
	 * Updates the boss state in the current frame.
	 */
	@Override
	public void updateActor() {
		super.updateActor();
		updateShield();
	}

	/**
	 * Unused by BossPlane, implementation has to exist because BossPlane inherits abstract class EnemyPlane.
	 * @return null
	 */
	@Override
	protected double getFireRate() {
		return 0; // bossPlane doesn't actually need `getFireRate()`
	}

	/**
	 * Fires projectiles according to BOSS_FIRE_RATE.
	 * @return Either boss projectiles or null.
	 */
	@Override
	public ActiveActorDestructible fireProjectile() {
		return bossFiresInCurrentFrame() ? new BossProjectile(getProjectileInitialPosition()) : null;
	}

	/**
	 * Decreases boss health if it is not currently shielded.
	 */
	@Override
	public void takeDamage() {
		if (!isShielded) {
			super.takeDamage();
		}
	}

	/**
	 * Updates shield status according to shieldShouldBeActivated and shieldExhausted.
	 */
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

	/**
	 * Determines if boss should be firing in the current frame.
	 * @return True when the probability is rolled below BOSS_FIRE_RATE.
	 */
	private boolean bossFiresInCurrentFrame() {
		return Math.random() < BOSS_FIRE_RATE;
	}

	/**
	 * Retrieves boss projectile position.
	 * @return Boss projectile position.
	 */
	private double getProjectileInitialPosition() {
		return getLayoutY() + getTranslateY() + PROJECTILE_Y_POSITION_OFFSET;
	}

	/**
	 * Determines when shield should be activated.
	 * @return True when the probability is rolled below BOSS_SHIELD_PROBABILITY.
	 */
	private boolean shieldShouldBeActivated() {
		return Math.random() < BOSS_SHIELD_PROBABILITY;
	}

	/**
	 * Determines when shield should be deactivated.
	 * @return True when maximum frames with shield active is met.
	 */
	private boolean shieldExhausted() {
		return framesWithShieldActivated == MAX_FRAMES_WITH_SHIELD;
	}

	/**
	 * Activates shield.
	 */
	private void activateShield() {
		isShielded = true;
	}

	/**
	 * Deactivates shield.
	 */
	private void deactivateShield() {
		isShielded = false;
		framesWithShieldActivated = 0;
	}

	/**
	 * Retrieves ShieldImage object.
	 * @return ShieldImage object reference.
	 */
	public ShieldImage getShieldImage() {
		return shieldImage;
	}
}
