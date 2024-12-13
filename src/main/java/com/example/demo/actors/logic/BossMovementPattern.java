package com.example.demo.actors.logic;

import java.util.*;

/**
 * BossMovementPattern class is responsible for determining the movement pattern for the boss.
 */
public class BossMovementPattern {

    /**
     * List for storing all the movement patterns of the boss.
     */
    private final List<Integer> movePattern;

    /**
     * The maximum number of frames allowed with the same movement.
     */
    private final int maxFramesWithSameMove;

    /**
     * Acts as a counter to keep track how many moves have been made in the same direction.
     */
    private int consecutiveMovesInSameDirection;

    /**
     * Index is used to access the movePattern list.
     */
    private int indexOfCurrentMove;

    public BossMovementPattern(int maxFrames, int verticalVelocity, int movementCycles) {
        this.movePattern = new ArrayList<>();
        this.maxFramesWithSameMove = maxFrames;
        initializeMovePattern(movementCycles, verticalVelocity);
        consecutiveMovesInSameDirection = 0;
        indexOfCurrentMove = 0;
    }

    /**
     * Initializes movement pattern of the boss.
     * @param frequency The number of cycles of movement patterns to initialize.
     * @param velocity The velocity of the movement pattern.
     */
    private void initializeMovePattern(int frequency, int velocity) {
        for (int i = 0; i < frequency; i++) {
            movePattern.add(velocity);
            movePattern.add(-velocity);
            movePattern.add(0);
        }
        Collections.shuffle(movePattern);
    }

    /**
     * Accesses the movePattern list to determine the next movement pattern.
     * @return The next movement pattern for the boss.
     */
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
