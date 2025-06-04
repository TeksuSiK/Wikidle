package pl.teksusik.wikidle.game;

import java.util.concurrent.ThreadLocalRandom;

public enum DifficultyLevel {
    EASY(3, 5),
    MEDIUM(6, 8),
    HARD(9, 13);

    private final int lower;
    private final int upper;

    DifficultyLevel(int lower, int upper) {
        this.lower = lower;
        this.upper = upper;
    }

    public int getLower() {
        return lower;
    }

    public int getUpper() {
        return upper;
    }

    public int getRandomSteps() {
        return ThreadLocalRandom.current().nextInt(lower, upper + 1);
    }
}
