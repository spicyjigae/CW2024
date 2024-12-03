package com.example.demo.actors.logic;

import com.example.demo.actors.templates.ActiveActorDestructible;
import com.example.demo.levels.logic.ActorManagement;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import com.example.demo.actors.planes.UserPlane;

public class UserControls {

    private final UserPlane user;
    private final Group root;
    private final ActorManagement actorManagement;

    public UserControls(UserPlane user, Group root, ActorManagement actorManagement) {
        this.user = user;
        this.root = root;
        this.actorManagement = actorManagement;
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

    public void fireProjectile() {
        ActiveActorDestructible projectile = user.fireProjectile(); // returns object position
        root.getChildren().add(projectile);
        actorManagement.addUserProjectiles(projectile);
    }
}
