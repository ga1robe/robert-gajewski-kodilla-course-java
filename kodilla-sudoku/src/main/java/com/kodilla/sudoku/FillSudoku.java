package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.Random;

public class FillSudoku {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 9;

    private SudokuBoard board;

    public FillSudoku(SudokuBoard board) {
        this.board = board;
    }

    public void fillBoard() throws Exception {
        Random theGenerate = new Random();
        int theIndexOfPossibleRowValues = -1;
        for(int row = MIN_VALUE - 1; row < MAX_VALUE; row++) {
            ArrayList<Integer> possibleRowValues = getPossibleRowValues(row);
            Integer possibleRowValue = -1;
            for(int column = MIN_VALUE - 1; column < MAX_VALUE; column++) {
                if (this.board.getBoard()[column].getSudokuElements().get(row).getValue() == -1 && possibleRowValues.size() > 0) {
                    ArrayList<Integer> currentColumnValues = getCurrentColumnValues(column);
                    int iterationOfLoop = possibleRowValues.size() * currentColumnValues.size();
                    boolean checkValueInSquare = checkInSquare(row / 3, column / 3, possibleRowValue);
                    do{
                        theIndexOfPossibleRowValues = theGenerate.nextInt(possibleRowValues.size());
                        possibleRowValue = possibleRowValues.get(theIndexOfPossibleRowValues);
                        checkValueInSquare = checkInSquare(row / 3, column / 3, possibleRowValue);
                        if (iterationOfLoop <= 0) { possibleRowValue = -1; break; }
                        iterationOfLoop--;
                    } while(currentColumnValues.contains(possibleRowValue) || checkValueInSquare);

                    if (possibleRowValue >= MIN_VALUE && possibleRowValue <= MAX_VALUE) {
                        this.board.getBoard()[column].getSudokuElements().get(row).setValue(possibleRowValue);
                        possibleRowValues.remove(theIndexOfPossibleRowValues);
                    }
                }
            }
        }
    }

    private ArrayList<Integer> getPossibleRowValues(int row) {
        ArrayList<Integer> currentRowValues = new ArrayList<>();
        for(int i = MIN_VALUE - 1; i < MAX_VALUE; i++) {
            if (this.board.getBoard()[i].getSudokuElements().get(row).getValue() != -1)
                currentRowValues.add(this.board.getBoard()[i].getSudokuElements().get(row).getValue());
        }
        return SudokuRow.getPossibleRowValues(currentRowValues);
    }

    private ArrayList<Integer> getCurrentColumnValues(int column) {
        ArrayList<Integer> currentColumnValues = new ArrayList<>();
        for(int theRow = MIN_VALUE - 1; theRow < MAX_VALUE; theRow++) {
            if (this.board.getBoard()[column].getSudokuElements().get(theRow).getValue() != -1)
                currentColumnValues.add(this.board.getBoard()[column].getSudokuElements().get(theRow).getValue());
        }
        return currentColumnValues;
    }

    private boolean checkInSquare(int squareRow, int squareColumn, int value) {
        int startRow = squareRow * 3;
        int startColumn = squareColumn * 3;
        int range = 3;

        ArrayList<Integer> currentValues = new ArrayList<>();
        for (int r = startRow; r < startRow + range; r++) {
            for(int c = startColumn; c < startColumn + range; c++) {
                if (this.board.getBoard()[c].getSudokuElements().get(r).getValue() != -1)
                    currentValues.add(this.board.getBoard()[c].getSudokuElements().get(r).getValue());
            }
        }
        return currentValues.contains(value);
    }

    private boolean checkInRow(int row, int value) throws Exception {
        ArrayList<Integer> currentRowValues = new ArrayList<>();
        for(int theColumn = MIN_VALUE - 1; theColumn < MAX_VALUE; theColumn++) {
            if (this.board.getBoard()[theColumn].getSudokuElements().get(row).getValue() != -1)
                currentRowValues.add(this.board.getBoard()[theColumn].getSudokuElements().get(row).getValue());
        }
        return currentRowValues.contains(value);
    }

    private boolean checkInColumn(int column, int value) throws Exception {
        ArrayList<Integer> currentColumnValues = new ArrayList<>();
        for(int theRow = MIN_VALUE - 1; theRow < MAX_VALUE; theRow++) {
            if (this.board.getBoard()[column].getSudokuElements().get(theRow).getValue() != -1)
                currentColumnValues.add(this.board.getBoard()[column].getSudokuElements().get(theRow).getValue());
        }
        return currentColumnValues.contains(value);
    }
}
