package com.kodilla.rps;

import java.util.Objects;

public final class RpsRelation {
    private final int firstStuff;
    private final int secondStuff;
    private final int winnerOfStuff;

    public RpsRelation(int firstStuff, int secondStuff, int winnerOfStuff) {
        this.firstStuff = firstStuff;
        this.secondStuff = secondStuff;
        this.winnerOfStuff = winnerOfStuff;
    }

    public int getFirstStuff() {
        return firstStuff;
    }

    public int getSecondStuff() {
        return secondStuff;
    }

    public int getWinnerOfStuff() {
        return winnerOfStuff;
    }

    public int getWinnerOfStuff(int firstStuff, int secondStuff) {
        if (this.firstStuff == firstStuff && this.secondStuff ==secondStuff)
            return this.winnerOfStuff;
        return -1;
    }

    @Override
    public String toString() {
        return "RpsRelations{" +
                "firstStuff=" + firstStuff +
                ", secondStuff=" + secondStuff +
                ", winnerOfStuff=" + winnerOfStuff +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RpsRelation)) return false;
        RpsRelation that = (RpsRelation) o;
        return firstStuff == that.firstStuff && secondStuff == that.secondStuff && winnerOfStuff == that.winnerOfStuff;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstStuff, secondStuff, winnerOfStuff);
    }
}
