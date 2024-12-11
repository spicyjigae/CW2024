package com.example.demo.actors.logic;

import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.levels.logic.ActorManager;
import com.example.demo.levels.logic.LevelParent;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import com.example.demo.actors.planes.UserPlane;

public class UserControls {

    private final UserPlane user;
    private final Group root;
    private final ActorManager actorManager;
    private final LevelParent level;

    private boolean paused;

    public UserControls(UserPlane user, Group root, ActorManager actorManager, LevelParent level) {
        this.user = user;
        this.root = root;
        this.actorManager = actorManager;
        this.level = level;
    }

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

    public void handleKeyReleased(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.W || kc == KeyCode.S) {
            user.stopVertical();
        }

        if (kc == KeyCode.A || kc == KeyCode.D) {
            user.stopHorizontal();
        }
    }

    private void fireProjectile() {
        if (!paused) {
            ActiveActorDestructible projectile = user.fireProjectile(); // returns object position
            root.getChildren().add(projectile);
            actorManager.addUserProjectiles(projectile);
        }
    }
}
