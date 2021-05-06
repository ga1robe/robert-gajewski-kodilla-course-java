package com.kodilla.game.view;

public class CheckersResult {
    private int redWins = 0;
    private int blackWins = 0;

    public CheckersResult(int redWins, int blackWins) {
        this.redWins = redWins;
        this.blackWins = blackWins;
    }

    @Override
    public String toString() {
        return "Result{" +
                "RED " + redWins + " : " + "BLACK " + blackWins +
                '}';
    }

    public String toStringGame() {
        return "Game{" +
                "RED " + redWins + " : " + "BLACK " + blackWins +
                '}';
    }

    public String toStringSet() {
        return "Set{" +
                "RED " + redWins + " : " + "BLACK " + blackWins +
                '}';
    }

    public int getRedWins() {
        return redWins;
    }

    public int getBlackWins() {
        return blackWins;
    }

    public void increaseRedWins() {
        this.redWins++;
    }

    public void increaseBlackWins() {
        this.blackWins++;
    }
}
