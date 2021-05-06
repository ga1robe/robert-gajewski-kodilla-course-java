package com.kodilla.sudoku;

public class SudokuGame {
    public static void main(String[] argc) throws Exception {
        boolean gameFinished = false;

        while(!gameFinished) {
            SudokuGameProcessor theGame = new SudokuGameProcessor();
            gameFinished = theGame.resolveSudoku();
        }
    }
}
