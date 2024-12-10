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

public abstract class LevelParent {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;

	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;
	private final Group root;
	private final UserPlane user;
	private final ImageView background;
	private final UserControls userControls;
	protected final ActorManager actorManager;
	private final CollisionHandler collisionHandler;
	protected final TimelineManager timelineManager;
	private final LevelView levelView;
	private final List<EventChangeListener> listeners;
	private final int playerInitialHealth;
	private final PauseMenuManager pauseMenuManager;
	private final Timeline spawnTimeline;

	protected int killsToAdvance;
	private int currentNumberOfEnemies;
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

	public void addListener(EventChangeListener listener) {
		listeners.add(listener);
	}

	public void removeListener(EventChangeListener listener) {
		listeners.remove(listener);
	}

	private void notifyListeners(String event) {
		for (EventChangeListener listener : listeners) {
			listener.onEventChange(event);
		}
	}

	public void initializeLevel() {
		actorManager.addFriendlyUnits(user);
		levelView.showHeartDisplay();
		timelineManager.startGame();
	}

	public void stopLevel() {
		timelineManager.stopGame();
		root.getChildren().clear();
		background.setImage(null);
		user.destroy();
	}

	protected void goToNextLevel(String LevelName) {
		notifyListeners(LevelName);
	}

	public void togglePause() {
		if (!pauseMenuAdded) {
			root.getChildren().add(pauseMenuManager.getPauseMenu());
			pauseMenuAdded = true;
		}
		pauseMenuManager.togglePause();
	}

	protected void winGame() {
		notifyListeners(String.valueOf(SceneType.WIN_GAME));
	}

	protected void loseGame() {
		notifyListeners(String.valueOf(SceneType.GAME_OVER));
	}

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected void startSpawning() {
		spawnTimeline.play();
	}

	protected void stopSpawning() {
		spawnTimeline.stop();
	}

	protected LevelView instantiateLevelView() {
		return new LevelView(getRoot(), playerInitialHealth);
	}

	public void updateScene() {
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

	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(this::handleKeyPressed);
		background.setOnKeyReleased(this::handleKeyReleased);
		root.getChildren().add(background);
	}

	public ImageView getBackground() {
		return background;
	}

	public Group getRoot() {
		return root;
	}

	public UserPlane getUser() {
		return user;
	}

	public double getScreenWidth() {
		return screenWidth;
	}

	private void handleKeyPressed(KeyEvent e)  {
		userControls.handleKeyPressed(e);
	}

	private void handleKeyReleased(KeyEvent e)  {
		userControls.handleKeyReleased(e);
	}

	private void updateLevelView() {
		levelView.removeHearts(user.getHealth());
	}

	private void updateKillCount() {
		int enemiesDestroyed = currentNumberOfEnemies - actorManager.getNumberOfEnemies();
		if (enemiesDestroyed > 0) {
			user.incrementKillCount(enemiesDestroyed);
		}
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = actorManager.getNumberOfEnemies();
	}

	protected boolean userHasReachedKillTarget() {
		return getUser().getNumberOfKills() >= killsToAdvance;
	}
}