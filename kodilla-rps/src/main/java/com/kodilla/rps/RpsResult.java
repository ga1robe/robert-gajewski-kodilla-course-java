package com.kodilla.rps;

import java.util.Objects;

public class RpsResult {
    private final int leftGoals;
    private final int rightGoals;

    public RpsResult(int leftGoals, int rightGoals) {
        this.leftGoals = leftGoals;
        this.rightGoals = rightGoals;
    }

    public int getLeftGoals() {
        return leftGoals;
    }

    public int getRightGoals() {
        return rightGoals;
    }

    @Override
    public String toString() {
        return "RpsResult{"+ leftGoals + " : " + rightGoals + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RpsResult)) return false;
        RpsResult rpsResult = (RpsResult) o;
        return leftGoals == rpsResult.leftGoals && rightGoals == rpsResult.rightGoals;
    }

    @Override
    public int hashCode() {
        return Objects.hash(leftGoals, rightGoals);
    }
}
