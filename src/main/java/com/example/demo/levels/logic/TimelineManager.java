package com.example.demo.levels.logic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

/**
 * TimelineManager class handles the game loop in which the game state is updated every frame.
 */
public class TimelineManager {

    private static final int MILLISECOND_DELAY = 50;

    /**
     * Timeline object for handling the game loop.
     */
    private final Timeline timeline;

    /**
     * LevelParent object reference.
     */
    private final LevelParent level;

    public TimelineManager(LevelParent level) {
        this.level = level;
        this.timeline = new Timeline();

        initializeTimeline();
    }

    /**
     * Initializes the timeline that determines how frequent the frames of the game are processed.
     */
    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> level.updateGame());
        timeline.getKeyFrames().add(gameLoop);
    }

    /**
     * Starts the timeline.
     */
    public void startGame() {
        level.getBackground().requestFocus();
        timeline.play();
    }

    /**
     * Stops the timeline.
     */
    public void stopGame() {
        timeline.stop();
    }

    /**
     * Pauses the timeline which can still be used if started again.
     */
    public void pauseGame() {
        timeline.pause();
    }

}
