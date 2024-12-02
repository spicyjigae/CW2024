package com.example.demo.controls;

import com.example.demo.actors.templates.ActiveActorDestructible;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.Group;
import com.example.demo.actors.planes.UserPlane;

import java.util.List;

public class UserControls {

    private final UserPlane user;
    private final Group root;
    private final List<ActiveActorDestructible> userProjectiles;

    public UserControls(UserPlane user, Group root, List<ActiveActorDestructible> userProjectiles) {
        this.user = user;
        this.root = root;
        this.userProjectiles = userProjectiles;
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
        userProjectiles.add(projectile);
    }
}
