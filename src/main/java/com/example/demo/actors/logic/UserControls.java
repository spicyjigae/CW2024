package com.example.demo.actors.logic;

import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.levels.logic.ActorManager;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import com.example.demo.actors.planes.UserPlane;

public class UserControls {

    private final UserPlane user;
    private final Group root;
    private final ActorManager actorManager;

    public UserControls(UserPlane user, Group root, ActorManager actorManager) {
        this.user = user;
        this.root = root;
        this.actorManager = actorManager;
    }

    public void handleKeyPressed(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.W) {
            user.moveUp();
        }
        if (kc == KeyCode.S) {
            user.moveDown();
        }
        if (kc == KeyCode.SPACE) {
            fireProjectile();
        }
    }

    public void handleKeyReleased(KeyEvent e) {
        KeyCode kc = e.getCode();
        if (kc == KeyCode.W || kc == KeyCode.S) {
            user.stop();
        }
    }

    private void fireProjectile() {
        ActiveActorDestructible projectile = user.fireProjectile(); // returns object position
        root.getChildren().add(projectile);
        actorManager.addUserProjectiles(projectile);
    }
}
