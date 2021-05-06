package com.kodilla.game.view;

import static com.kodilla.game.view.CheckersData.*;


public class CheckersMove {
    private int player;
    private int fromRow, fromColumn;
    private int toRow, toColumn;
    CheckersMove(int player, int row1, int column1, int row2, int column2) {
        this.player = player;
        this.fromRow = row1;
        this.fromColumn = column1;
        this.toRow = row2;
        this.toColumn = column2;
    }

    public int getFromRow() {

        return fromRow;
    }

    public int getFromColumn() {

        return fromColumn;
    }

    public int getToRow() {

        return toRow;
    }

    public int getToColumn() {

        return toColumn;
    }

    public boolean isJump() {
        boolean canJump = true;
        if (player == RED || player == BLACK) {
            return ((Math.abs(fromRow - toRow) == 2 || Math.abs(fromColumn - toColumn) == 2) && canJump);
        } else if (player == RED_QUEEN || player == BLACK_QUEEN) {
            return ((Math.abs(fromRow - toRow) >= 2 || Math.abs(fromColumn - toColumn) >= 2) && canJump);
        } else
            return false;
    }

    public void doMove() {
        int fromRow = getFromRow();
        int fromColumn = getFromColumn();
        int toRow = getToRow();
        int toColumn = getToColumn();
        int[][] board = CheckersData.getBoard();
        board[toRow][toColumn] = board[fromRow][fromColumn];
        board[fromRow][fromColumn] = EMPTY;
        if (Math.abs(toRow - fromRow) >= 2 && Math.abs(toColumn - fromColumn) >= 2) {
            int jumpedRow = -1, jumpedColumn = -1;
            if (fromRow - toRow > 0)
                jumpedRow = toRow + 1;
            else if (fromRow - toRow < 0)
                jumpedRow = toRow - 1;
            else
                jumpedRow = (fromRow + toRow) / 2;

            if (fromColumn - toColumn > 0)
                jumpedColumn = toColumn + 1;
            else if (fromColumn - toColumn < 0)
                jumpedColumn = toColumn - 1;
            else
                jumpedColumn = (fromColumn + toColumn) / 2;
            board[jumpedRow][jumpedColumn] = EMPTY;
        }
        if (toRow == 0 && board[toRow][toColumn] == RED)
            board[toRow][toColumn] = RED_QUEEN;
        if (toRow == 7 && board[toRow][toColumn] == BLACK)
            board[toRow][toColumn] = BLACK_QUEEN;
    }

    @Override
    public String toString() {
        if (isJump() && (player == RED || player == BLACK))
            return "jumped:\n" +
                    "from[" + (fromRow + 1) +
                    "][" + (fromColumn + 1) +
                    "] => to[" + (toRow + 1) +
                    "][" + (toColumn + 1) +
                    "];\t";
        else
            return "moved:\n" +
                    "from[" + (fromRow + 1) +
                    "][" + (fromColumn + 1) +
                    "] -> to[" + (toRow + 1) +
                    "][" + (toColumn + 1) +
                    "];\t";
    }
}
