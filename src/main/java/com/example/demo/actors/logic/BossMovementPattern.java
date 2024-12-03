package com.example.demo.actors.logic;

import java.util.*;

public class BossMovementPattern {
    private final List<Integer> movePattern;
    private final int maxFramesWithSameMove;
    private int consecutiveMovesInSameDirection;
    private int indexOfCurrentMove;

    public BossMovementPattern(int maxFrames, int verticalVelocity, int moveFrequencyPerCycle) {
        this.movePattern = new ArrayList<>();
        this.maxFramesWithSameMove = maxFrames;
        initializeMovePattern(moveFrequencyPerCycle, verticalVelocity);
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
    }

    private void initializeMovePattern(int frequency, int velocity) {
        for (int i = 0; i < frequency; i++) {
            movePattern.add(velocity);
            movePattern.add(-velocity);
            movePattern.add(0);
        }
        Collections.shuffle(movePattern);
    }

    public int getNextMove() {
        int currentMove = movePattern.get(indexOfCurrentMove);
        consecutiveMovesInSameDirection++;
        if (consecutiveMovesInSameDirection == maxFramesWithSameMove) {
            Collections.shuffle(movePattern);
            consecutiveMovesInSameDirection = 0;
            indexOfCurrentMove++;
        }
        if (indexOfCurrentMove == movePattern.size()) {
            indexOfCurrentMove = 0;
        }
        return currentMove;
    }
}
