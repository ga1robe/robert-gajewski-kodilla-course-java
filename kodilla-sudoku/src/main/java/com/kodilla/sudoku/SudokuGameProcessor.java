package com.kodilla.sudoku;

import java.util.ArrayList;

public class SudokuGameProcessor {
    private SudokuBoard board;
    private FillSudoku fillSudoku;

    public boolean resolveSudoku() throws Exception {
        board = new SudokuBoard();
        fillSudoku = new FillSudoku(board);

        boolean finishGame = false;
        do {
            ArrayList<UserChoice> userChoices = Menu.getUserChoice();
            UserChoice choice = userChoices.get(0);
            switch (choice.getChoiceType()) {
                case EXIT:
                    finishGame = true;
                    break;
                case NONE:
                    break;
                case LOAD:
                    board = new SudokuBoard();
                    board.load();
                    fillSudoku = new FillSudoku(board);
                    Menu.show(board.toString());
                    break;
                case SAVE:
                    board.save();
                    break;
                case NEW_GAME:
                    board = new SudokuBoard();
                    fillSudoku = new FillSudoku(board);
                case SET_VALUE:
                    if (userChoices.size() >= 1) {
                        for (UserChoice userChoice: userChoices) {
                            setValue(userChoice);
                        }
                    }
                    Menu.show(board.toString());
                    break;
                case RESOLVE:
                    try {
                        fillSudoku.fillBoard();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    Menu.show(board.toString());
                    Menu.gameAgain();
                    break;
            }
        } while (!finishGame);
        return true;
    }

    private void setValue(UserChoice choice) {
        try {
            board.setValueToCell(choice.getRow() - 1, choice.getColumn() - 1, choice.getValue());
        } catch (Exception e) {
            Menu.printIncorrectValueToSet(choice);
        }
    }
}
