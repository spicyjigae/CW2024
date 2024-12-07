package com.example.demo.levels.logic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimelineManager {
    private final Timeline timeline;
    private final LevelParent levelParent;
    private static final int MILLISECOND_DELAY = 50;

    public TimelineManager(LevelParent levelParent) {
        this.levelParent = levelParent;
        this.timeline = new Timeline();
        initializeTimeline();
    }

    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> levelParent.updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

    public void start() {
        levelParent.getBackground().requestFocus();
        timeline.play();
    }

    public void stopGame() {
        timeline.stop();
    }

}
