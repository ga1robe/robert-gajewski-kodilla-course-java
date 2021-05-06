package com.kodilla.rps;

import java.util.Objects;

public final class RpsStuff {
    private final int positionOfStuff;
    private final String nameOfStuff;

    public RpsStuff(int positionOfStuff, String nameOfStuff) {
        this.positionOfStuff = positionOfStuff;
        this.nameOfStuff = nameOfStuff;
    }

    public int getPositionOfStuff() {
        return positionOfStuff;
    }

    public String getNameOfStuff() {
        return nameOfStuff;
    }
    public String getNameOfStuff(int positionOfStuff) {
        if (this.positionOfStuff == positionOfStuff)
            return this.nameOfStuff;
        return "";
    }

    @Override
    public String toString() {
        return "RpsStuff{" +
                "positionOfStuff=" + positionOfStuff +
                ", nameOfStuff='" + nameOfStuff + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RpsStuff)) return false;
        RpsStuff rpsStuff = (RpsStuff) o;
        return positionOfStuff == rpsStuff.positionOfStuff && Objects.equals(nameOfStuff, rpsStuff.nameOfStuff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(positionOfStuff, nameOfStuff);
    }
}
