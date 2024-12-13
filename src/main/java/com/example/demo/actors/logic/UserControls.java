package com.example.demo.actors.logic;

import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.levels.logic.ActorManager;
import com.example.demo.levels.logic.LevelParent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import com.example.demo.actors.planes.UserPlane;

/**
 * UserControls class is responsible for listening to and handling key inputs.
 */
public class UserControls {

    /**
     * UserPlane object reference.
     */
    private final UserPlane user;

    /**
     * JavaFX Group object reference.
     */
    private final Group root;

    /**
     * ActorManager object reference.
     */
    private final ActorManager actorManager;

    /**
     * LevelParent object reference.
     */
    private final LevelParent level;

    /**
     * Denotes if the game is currently paused or not.
     */
    private boolean paused;

    public UserControls(UserPlane user, Group root, ActorManager actorManager, LevelParent level) {
        this.user = user;
        this.root = root;
        this.actorManager = actorManager;
        this.level = level;
    }

    /**
     * Handles key presses for movement, projectile firing and game pausing.
     * @param e Event parameters.
     */
    public void handleKeyPressed(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.W) {
            user.moveUp();
        }
        if (kc == KeyCode.S) {
            user.moveDown();
        }

        if (kc == KeyCode.A) {
            user.moveLeft();
        }

        if (kc == KeyCode.D) {
            user.moveRight();
        }

        if (kc == KeyCode.SPACE) {
            fireProjectile();
        }

        if (kc == KeyCode.ESCAPE) {
            level.togglePause();
            paused = !paused;
        }
    }

    /**
     * Handles key releases to ensure user plane stops if keys are no longer pressed.
     * @param e Event parameters.
     */
    public void handleKeyReleased(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.W || kc == KeyCode.S) {
            user.stopVertical();
        }

        if (kc == KeyCode.A || kc == KeyCode.D) {
            user.stopHorizontal();
        }
    }

    /**
     * Fires user projectiles ONLY IF the game is not currently paused.
     */
    private void fireProjectile() {
        if (!paused) {
            ActiveActorDestructible projectile = user.fireProjectile(); // returns object position
            root.getChildren().add(projectile);
            actorManager.addUserProjectiles(projectile);
        }
    }
}
