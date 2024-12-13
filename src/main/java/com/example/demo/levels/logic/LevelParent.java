package com.example.demo.levels.logic;

import java.util.*;

import com.example.demo.interfaces.EventChangeListener;
import com.example.demo.actors.planes.UserPlane;
import com.example.demo.actors.logic.UserControls;
import com.example.demo.levels.LevelView;
import com.example.demo.scenes.SceneType;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Group;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

/**
 * LevelParent is the superclass of all level classes, handles all level-related mechanics, such as units
 * initialization, game pausing, scene updating, starting and stopping the level.
 */
public abstract class LevelParent {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;

	/**
	 * Screen height.
	 */
	private final double screenHeight;

	/**
	 * Screen width.
	 */
	private final double screenWidth;

	/**
	 * Maximum vertical position for the enemy.
	 */
	private final double enemyMaximumYPosition;

	/**
	 * JavaFX Group object reference.
	 */
	private final Group root;

	/**
	 * UserPlane object reference.
	 */
	private final UserPlane user;

	/**
	 * ImageView background object reference.
	 */
	private final ImageView background;

	/**
	 * UserControls object reference.
	 */
	private final UserControls userControls;

	/**
	 * ActorManager object reference.
	 */
	protected final ActorManager actorManager;

	/**
	 * CollisionHandler object reference.
	 */
	private final CollisionHandler collisionHandler;

	/**
	 * TimelineManager object reference.
	 */
	protected final TimelineManager timelineManager;

	/**
	 * LevelView object reference.
	 */
	private final LevelView levelView;

	/**
	 * List for storing listeners.
	 */
	private final List<EventChangeListener> listeners;

	/**
	 * Initial player health.
	 */
	private final int playerInitialHealth;

	/**
	 * PauseMenuManager object reference.
	 */
	private final PauseMenuManager pauseMenuManager;

	/**
	 * spawnTimeline timeline object reference creates a delay in spawning enemies.
	 */
	private final Timeline spawnTimeline;

	/**
	 * Kills to advance the current level.
	 */
	protected int killsToAdvance;

	/**
	 * Current number of enemies.
	 */
	private int currentNumberOfEnemies;

	/**
	 * Denotes if the pause menu has been added to the scene.
	 */
	private boolean pauseMenuAdded = false;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();

		this.user = new UserPlane(playerInitialHealth);
		this.actorManager = new ActorManager(root);
		this.collisionHandler = new CollisionHandler(actorManager, user);
		this.timelineManager = new TimelineManager(this);
		this.userControls = new UserControls(user, root, actorManager, this);
		this.listeners = new ArrayList<>();
		this.background = new ImageView(new Image(Objects.requireNonNull(getClass().getResource(backgroundImageName)).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.currentNumberOfEnemies = 0;
		this.playerInitialHealth = playerInitialHealth;
		this.levelView = instantiateLevelView();
		this.killsToAdvance = 0;
		this.pauseMenuManager = new PauseMenuManager(this.timelineManager);
		this.spawnTimeline = new Timeline(new KeyFrame(Duration.seconds(1), e -> spawnEnemyUnits()));
		this.spawnTimeline.setCycleCount(Timeline.INDEFINITE);
		initializeBackground();
	}

	/**
	 * Adds listeners to the current level for scene changing.
	 * @param listener Listeners that get notified to change scenes.
	 */
	public void addListener(EventChangeListener listener) {
		listeners.add(listener);
	}

	/**
	 * Removes listeners from the current level to clear resources.
	 * @param listener Listeners that get notified to change scenes.
	 */
	public void removeListener(EventChangeListener listener) {
		listeners.remove(listener);
	}

	/**
	 * Notifies listeners to change scenes.
	 * @param event The scene to change to.
	 */
	private void notifyListeners(String event) {
		for (EventChangeListener listener : listeners) {
			listener.onEventChange(event);
		}
	}

	/**
	 * Initializes the level with friendly units, heart UI and starts the game timeline.
	 */
	public void initializeLevel() {
		actorManager.addFriendlyUnits(user);
		levelView.showHeartDisplay();
		timelineManager.startGame();
	}

	/**
	 * Stops the level by clearing all the resources.
	 */
	public void stopLevel() {
		timelineManager.stopGame();
		root.getChildren().clear();
		background.setImage(null);
		user.destroy();
	}

	/**
	 * Changes the current scene to the next level.
	 * @param LevelName Name of the next level.
	 */
	protected void goToNextLevel(String LevelName) {
		notifyListeners(LevelName);
	}

	/**
	 * Toggles the game pause mechanic and adds the pause menu.
	 */
	public void togglePause() {
		if (!pauseMenuAdded) {
			root.getChildren().add(pauseMenuManager.getPauseMenu());
			pauseMenuAdded = true;
		}
		pauseMenuManager.togglePause();
	}

	/**
	 * Changes the scene to the Win Game scene.
	 */
	protected void winGame() {
		notifyListeners(String.valueOf(SceneType.WIN_GAME));
	}

	/**
	 * Changes scene to the Game Over scene.
	 */
	protected void loseGame() {
		notifyListeners(String.valueOf(SceneType.GAME_OVER));
	}

	/**
	 * Abstract method that checks if the game is over.
	 */
	protected abstract void checkIfGameOver();

	/**
	 * Abstract method that spawns enemy units.
	 */
	protected abstract void spawnEnemyUnits();

	/**
	 * Starts the spawnTimeline object that creates a delay in spawning enemies.
	 */
	protected void startSpawning() {
		spawnTimeline.play();
	}

	/**
	 * Stops the spawnTimeline object that creates a delay in spawning enemies.
	 */
	protected void stopSpawning() {
		spawnTimeline.stop();
	}

	/**
	 * Instantiate level view UI.
	 * @return Level view UI.
	 */
	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), playerInitialHealth);
	}

	/**
	 * Periodically updates the game state every frame.
	 */
	public void updateGame() {
		startSpawning();
		actorManager.updateActors();
		actorManager.generateEnemyFire();
		updateNumberOfEnemies();
		collisionHandler.handleEnemyPenetration(screenWidth);
		collisionHandler.handleUserProjectileCollisions();
		collisionHandler.handleEnemyProjectileCollisions();
		collisionHandler.handlePlaneCollisions();
		actorManager.removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	/**
	 * Initializes level background.
	 */
	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(this::handleKeyPressed);
		background.setOnKeyReleased(this::handleKeyReleased);
		root.getChildren().add(background);
	}

	/**
	 * Retrieves background.
	 * @return Background object.
	 */
	public ImageView getBackground() {
		return background;
	}

	/**
	 * Retrieves JavaFX Group object {@code root}.
	 * @return {@code root}.
	 */
	public Group getRoot() {
		return root;
	}

	/**
	 * Retrieves UserPlane object.
	 * @return UserPlane object.
	 */
	public UserPlane getUser() {
		return user;
	}

	/**
	 * Retrieves screen width.
	 * @return Screen width.
	 */
	public double getScreenWidth() {
		return screenWidth;
	}

	/**
	 * Handles user key presses.
	 * @param e Event parameters.
	 */
	private void handleKeyPressed(KeyEvent e)  {
		userControls.handleKeyPressed(e);
	}

	/**
	 * Handles user key releases.
	 * @param e Event parameters.
	 */
	private void handleKeyReleased(KeyEvent e)  {
		userControls.handleKeyReleased(e);
	}

	/**
	 * Updates heart count UI based on the user's health in the last frame.
	 */
	private void updateLevelView() {
		levelView.updateHearts(user.getHealth());
	}

	/**
	 * Updates kill count based on the enemies killed in the last frame.
	 */
	private void updateKillCount() {
		int enemiesDestroyed = currentNumberOfEnemies - actorManager.getNumberOfEnemies();
		if (enemiesDestroyed > 0) {
			user.incrementKillCount(enemiesDestroyed);
		}
	}

	/**
	 * Denotes if user is destroyed.
	 * @return True when user is destroyed.
	 */
	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	/**
	 * Retrieves the maximum vertical position for the enemy.
	 * @return The maximum vertical position for the enemy.
	 */
	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	/**
	 * Updates current number of enemies.
	 */
	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = actorManager.getNumberOfEnemies();
	}

	/**
	 * Denotes if user has killed all enemies.
	 * @return True when user has killed all enemies.
	 */
	protected boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= killsToAdvance;
	}
}