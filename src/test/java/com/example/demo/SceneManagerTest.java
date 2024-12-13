package com.example.demo;

import com.example.demo.levels.logic.SceneManager;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class SceneManagerTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testSingletonInstance() {
        Stage stage = mock(Stage.class);
        SceneManager sceneManager1 = SceneManager.getInstance(stage);
        SceneManager sceneManager2 = SceneManager.getInstance(stage);

        assertSame(sceneManager1, sceneManager2);
    }
}
