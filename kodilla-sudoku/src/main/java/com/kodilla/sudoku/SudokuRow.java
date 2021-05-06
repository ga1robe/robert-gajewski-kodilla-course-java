package com.kodilla.sudoku;

import java.util.ArrayList;

public class SudokuRow {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 9;
    private final ArrayList<SudokuElement> sudokuElements = new ArrayList<>();

    public SudokuRow(){
        for(int i=0; i<9; i++) {
            sudokuElements.add(i,new SudokuElement());
        }
    }

    public ArrayList<SudokuElement> getSudokuElements() {
        return sudokuElements;
    }

    public static ArrayList<Integer> getPossibleRowValues(ArrayList<Integer> currentRowValues) {
        ArrayList<Integer> possibleRowValues = setPossibleRowValue();
        if(possibleRowValues.containsAll(currentRowValues))
            for (Integer currentRowValue: currentRowValues) {
                possibleRowValues.remove(
                        possibleRowValues.indexOf(currentRowValue)
                );
            }
        return possibleRowValues;
    }

    private static ArrayList<Integer> setPossibleRowValue() {
        ArrayList<Integer> possibleRowValues = new ArrayList<>();
        for(int i = MIN_VALUE; i <= MAX_VALUE; i++) possibleRowValues.add(i);
        return possibleRowValues;
    }
}
