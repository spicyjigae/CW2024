package com.example.demo.levels.logic;

import com.example.demo.actors.planes.BossPlane;
import com.example.demo.actors.planes.PhantomPlane;
import com.example.demo.actors.planes.ScoutPlane;
import com.example.demo.actors.templates.ActiveActorDestructible;

public class EnemyFactory {
    public static ActiveActorDestructible createEnemy(String type, double x, double y) {
        return switch (type.toUpperCase()) {
            case "SCOUT" -> new ScoutPlane(x, y);
            case "BOSS" -> new BossPlane(x, y);
            case "PHANTOM" -> new PhantomPlane(x, y);
            default -> throw new IllegalArgumentException("Unknown enemy type: " + type);
        };
    }
}
