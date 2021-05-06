package com.kodilla.rps;

import java.util.Objects;

public class RpsPlayer {
    private final String username;
    private final int numberOfRoundsWon;

    public RpsPlayer(final String username, final int numberOfRoundsWon) {
        this.username = username;
        this.numberOfRoundsWon = numberOfRoundsWon;
    }

    public String getUsername() {
        return username;
    }

    public int getNumberOfRoundsWon() {
        return numberOfRoundsWon;
    }

    @Override
    public String toString() {
        return "RpsPlayer{" +
                "username='" + username + '\'' +
                ", numberOfRoundsWon=" + numberOfRoundsWon +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RpsPlayer)) return false;
        RpsPlayer rpsPlayer = (RpsPlayer) o;
        return numberOfRoundsWon == rpsPlayer.numberOfRoundsWon && Objects.equals(username, rpsPlayer.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(username, numberOfRoundsWon);
    }
}
