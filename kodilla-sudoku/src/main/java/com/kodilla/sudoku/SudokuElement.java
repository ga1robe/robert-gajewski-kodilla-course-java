package com.kodilla.sudoku;

import java.util.ArrayList;

public class SudokuElement {
    public static final int EMPTY = -1;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 9;

    private int value = EMPTY;
    private ArrayList<Integer> possibleValues = getDefaultPossibleValues();


    public static ArrayList<Integer> getDefaultPossibleValues() {
        ArrayList<Integer> possibleValues = new ArrayList<>();
        for(int possibleValue = MIN_VALUE; possibleValue <= MAX_VALUE; possibleValue++) {
            if (possibleValue != EMPTY)
                possibleValues.add(possibleValue);
        }
        return possibleValues;
    }

    public int getValue() {
        return value;
    }

    public ArrayList<Integer> getPossibleValues() {
        return possibleValues;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setPossibleValues(ArrayList<Integer> possibleValues) {
        this.possibleValues = possibleValues;
    }
}
