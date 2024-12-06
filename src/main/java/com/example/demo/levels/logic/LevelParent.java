package com.example.demo.levels.logic;

import java.util.*;

import com.example.demo.interfaces.LevelChangeListener;
import com.example.demo.actors.planes.UserPlane;
import com.example.demo.actors.logic.UserControls;
import com.example.demo.levels.LevelView;
import javafx.animation.*;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.image.*;
import javafx.scene.input.*;
import javafx.util.Duration;

public abstract class LevelParent {

	private static final double SCREEN_HEIGHT_ADJUSTMENT = 150;
	private static final int MILLISECOND_DELAY = 50;
	private final double screenHeight;
	private final double screenWidth;
	private final double enemyMaximumYPosition;

	private final Group root;
	private final Timeline timeline;
	private final UserPlane user;
	private final Scene scene;
	private final ImageView background;
	private final UserControls userControls;
	protected final ActorManagement actorManagement;
	private final CollisionHandling collisionHandling;

	private final List<LevelChangeListener> listeners = new ArrayList<>();

	private int currentNumberOfEnemies;
	private LevelView levelView;

	public LevelParent(String backgroundImageName, double screenHeight, double screenWidth, int playerInitialHealth) {
		this.root = new Group();
		this.scene = new Scene(root, screenWidth, screenHeight);
		this.timeline = new Timeline();
		this.user = new UserPlane(playerInitialHealth);
		this.actorManagement = new ActorManagement(root);
		this.userControls = new UserControls(user, root, actorManagement);
		this.collisionHandling = new CollisionHandling(actorManagement, user);

		this.background = new ImageView(new Image(getClass().getResource(backgroundImageName).toExternalForm()));
		this.screenHeight = screenHeight;
		this.screenWidth = screenWidth;
		this.enemyMaximumYPosition = screenHeight - SCREEN_HEIGHT_ADJUSTMENT;
		this.levelView = instantiateLevelView();
		this.currentNumberOfEnemies = 0;
		initializeTimeline();
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
		actorManagement.addFriendlyUnits(user);
		levelView.showHeartDisplay();
		return scene;
	}

	public void startGame() {
		background.requestFocus();
		timeline.play();
	}

	private void updateScene() {
		spawnEnemyUnits();
		actorManagement.updateActors();
		actorManagement.generateEnemyFire();
		updateNumberOfEnemies();
		collisionHandling.handleEnemyPenetration(screenWidth);
		collisionHandling.handleUserProjectileCollisions();
		collisionHandling.handleEnemyProjectileCollisions();
		collisionHandling.handlePlaneCollisions();
		actorManagement.removeAllDestroyedActors();
		updateKillCount();
		updateLevelView();
		checkIfGameOver();
	}

	private void initializeTimeline() {
		timeline.setCycleCount(Timeline.INDEFINITE);
		KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> updateScene());
		timeline.getKeyFrames().add(gameLoop);
	}

	private void initializeBackground() {
		background.setFocusTraversable(true);
		background.setFitHeight(screenHeight);
		background.setFitWidth(screenWidth);
		background.setOnKeyPressed(e -> handleKeyPressed(e));
		background.setOnKeyReleased(e -> handleKeyReleased(e));
		root.getChildren().add(background);
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
		int enemiesDestroyed = currentNumberOfEnemies - actorManagement.getNumberOfEnemies();
		if (enemiesDestroyed > 0) {
			user.incrementKillCount(enemiesDestroyed);
		}
	}

	protected void winGame() {
		timeline.stop();
		levelView.showWinImage();
	}

	protected void loseGame() {
		timeline.stop();
		levelView.showGameOverImage();
	}

	protected UserPlane getUser() {
		return user;
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

	protected boolean userIsDestroyed() {
		return user.isDestroyed();
	}

	private void updateNumberOfEnemies() {
		currentNumberOfEnemies = actorManagement.getNumberOfEnemies();
	}

}
