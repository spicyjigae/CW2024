package com.example.demo;

import com.example.demo.levels.logic.EnemyFactory;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class EnemyFactoryTest {

    @Test
    public void testInvalidEnemyType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            EnemyFactory.createEnemy("UNKNOWN", 0, 0);
        });
        assertEquals("Unknown enemy type: UNKNOWN", exception.getMessage());
    }
}