package com.example.demo.levels.logic;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class TimelineManager {

    private static final int MILLISECOND_DELAY = 50;

    private final Timeline timeline;
    private final LevelParent level;

    public TimelineManager(LevelParent level) {
        this.level = level;
        this.timeline = new Timeline();
        initializeTimeline();
    }

    private void initializeTimeline() {
        timeline.setCycleCount(Timeline.INDEFINITE);
        KeyFrame gameLoop = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> level.updateScene());
        timeline.getKeyFrames().add(gameLoop);
    }

    public void start() {
        level.getBackground().requestFocus();
        timeline.play();
    }

    public void stopGame() {
        timeline.stop();
    }

}
