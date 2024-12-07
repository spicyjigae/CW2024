package com.example.demo.levels.logic;

import java.util.*;

import com.example.demo.interfaces.LevelChangeListener;
import com.example.demo.actors.planes.UserPlane;
import com.example.demo.actors.logic.UserControls;
import com.example.demo.levels.LevelView;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;

public abstract class LevelParent {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;
	private final UserControls userControls;
	protected final ActorManager actorManager;
	private final CollisionHandler collisionHandler;
	protected final TimelineManager timelineManager;

	private final List<LevelChangeListener> listeners = new ArrayList<>();

	private int currentNumberOfEnemies;
	private final LevelView levelView;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.user = new UserPlane(playerInitialHealth);
		this.actorManager = new ActorManager(root);
		this.userControls = new UserControls(user, root, actorManager);
		this.collisionHandler = new CollisionHandler(actorManager, user);
		this.timelineManager = new TimelineManager(this);

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
	}

	public void addListener(LevelChangeListener listener) {
		listeners.add(listener);
	}

	public void removeListener(LevelChangeListener levelChangeListener) {
		listeners.remove(levelChangeListener);
	}

	private void notifyListeners(String newLevel) {
		for (LevelChangeListener listener : listeners) {
			listener.onLevelChange(newLevel);
		}
	}

	public void goToNextLevel(String LevelName) {
		user.destroy();
		notifyListeners(LevelName);
	}

	protected abstract void checkIfGameOver();

	protected abstract void spawnEnemyUnits();

	protected abstract LevelView instantiateLevelView();

	public Scene initializeScene() {
		initializeBackground();
		actorManager.addFriendlyUnits(user);
		levelView.showHeartDisplay();
		return scene;
	}

	public void updateScene() {
		spawnEnemyUnits();
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
		background.setOnKeyPressed(e -> handleKeyPressed(e));
		background.setOnKeyReleased(e -> handleKeyReleased(e));
		root.getChildren().add(background);
	}

	public ImageView getBackground() {
		return background;
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

	public void startGame() {
		timelineManager.start();
	}

	protected void winGame() {
		timelineManager.stopGame();
		levelView.showWinImage();
	}

	protected void loseGame() {
		timelineManager.stopGame();
		levelView.showGameOverImage();
	}

	protected UserPlane getUser() {
		return user;
	}

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	protected Group getRoot() {
		return root;
	}

	protected double getEnemyMaximumYPosition() {
		return enemyMaximumYPosition;
	}

	protected double getScreenWidth() {
		return screenWidth;
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = actorManager.getNumberOfEnemies();
	}

}
