package com.kodilla.game.view;

import com.kodilla.game.logic.CheckersBoard;

import java.util.ArrayList;
import java.util.List;

/**
 * An object of this class holds data about a game of checkers. It knows what kind of pawn is on each square of the checkerboard.
 * Note that RED moves "up" the board (i.e. row number decreases) while BLACK moves "down" the board (i.e. row number increases).
 * Methods are provided to return lists of available valid moves.
 */
public class CheckersData {
    private final int BOARD_DIM;
    /**
     * The following constants represent the possible contents of a square on the board.
     * The constants RED and BLACK also represent players in the game.
     **/
    static final int EMPTY = 0;
    public static final int RED = 1;
    public static final int RED_QUEEN = 2;
    public static final int BLACK = 3;
    public static final int BLACK_QUEEN = 4;

    static int[][] board;  /** board[row][column] is the contents of row row, column column. */

    public static CheckersResult checkersResult;

    /** Constructor.  Create the board and set it up for a new game. */
    public CheckersData() {
        BOARD_DIM = CheckersBoard.getDimOfBoard();
        board = new int[BOARD_DIM][BOARD_DIM];
        setUpPawnsOnBoard();
        checkersResult = new CheckersResult(0, 0);
    }

    /**
     * setUpPawnsOnBoard
     * Set up the board with checkers in position for the beginning of a game.
     * Note that checkers can only be found in squares that satisfy  row % 2 == column % 2.
     * At the start of the game, all such squares in the first three rows contain black squares and all
     * such squares in the last three rows contain red squares.
     */
    public void setUpPawnsOnBoard() {
        for (int row = 0; row < BOARD_DIM; row++) {
            for (int column = 0; column < BOARD_DIM; column++) {
                if ( row % 2 == column % 2) {
                    if (row >= 0 && row < 3)
//                    if (row >= 1 && row < 3)
                        board[row][column] = BLACK;
                    else if (row >= 5 && row < BOARD_DIM)
                        board[row][column] = RED;
//                        board[row][column] = RED_QUEEN; /* test */
                    else
                        board[row][column] = EMPTY;
                }
                else {
                    board[row][column] = EMPTY;
                }
            }
        }
    }

    /**
     * getOpponents
     * Description. Returns opponents for player.
     * @param player
     * @return opponents
     */
    static ArrayList<Integer> getOpponents(int player) {
        ArrayList<Integer> opponents = new ArrayList<>();
        switch (player) {
            case RED:
            case RED_QUEEN:
                opponents.add(BLACK);
                opponents.add(BLACK_QUEEN);
                break;
            case BLACK:
            case BLACK_QUEEN:
                opponents.add(RED);
                opponents.add(RED_QUEEN);
                break;
        }
        return opponents;
    }

    /**
     * pawnAt
     * Description: Return the contents of the square in the specified row and column.
     * @param row
     * @param column
     * @return board[row][column]
     */
    public int pawnAt(int row, int column) {
        if (row >= 0 && row < BOARD_DIM && column >= 0 && column < BOARD_DIM)
            return board[row][column];
        else
            return -1;
    }

    public static int[][] getBoard() {

        return board;
    }

    public static void setBoard(int[][] board) {

        CheckersData.board = board;
    }

    /**
     * getPossibleMoves
     * Description. Return an array containing all the valid CheckersMoves for the specified player on the current board.
     * If the player has no valid moves, null is returned.  The value of player should be one of the constants RED or BLACK;
     * if not, null is returned.  If the returned value is non-null, it consists entirely of jump moves or entirely of
     * regular moves, since if the player can jump, only jumps are valid moves.
     * @param player
     * @return moveArray
     */
    public ArrayList<CheckersMove> getPossibleMoves(int player) {
        if (player != RED && player != BLACK)
            return null;
        int playerQueen;  /** The constant representing a Queen belonging to player. */
        if (player == RED)
            playerQueen = RED_QUEEN;
        else /* if (player == BLACK) */
            playerQueen = BLACK_QUEEN;

        ArrayList<CheckersMove> moves = new ArrayList<CheckersMove>();  /** Moves will be stored in this list. */

        for (int row = 0; row < BOARD_DIM; row++) {
            for (int column = 0; column < BOARD_DIM; column++) {
                if (pawnAt(row, column) == player && findPossibleJumps(player, row, column).size() > 0) {
                    for (CheckersMove foundPossibleJump: findPossibleJumps(player, row, column)) {
                        moves.add(foundPossibleJump);
                    }
                } else if (pawnAt(row, column) == playerQueen && findPossibleJumps(playerQueen, row, column).size() > 0) {
                    for (CheckersMove foundPossibleJump: findPossibleJumps(playerQueen, row, column)) {
                        moves.add(foundPossibleJump);
                    }
                }
            }
        }

        if (moves.size() == 0) {
            for (int row = 0; row < BOARD_DIM; row++) {
                for (int column = 0; column < BOARD_DIM; column++) {
                    if (pawnAt(row, column) == player && findPossibleMoves(player, row, column).size() > 0) {
                        for (CheckersMove foundPossibleMove: findPossibleMoves(player, row, column)) {
                            moves.add(foundPossibleMove);
                        }
                    } else if (pawnAt(row, column) == playerQueen && findPossibleMoves(playerQueen, row, column).size() > 0) {
                        for (CheckersMove foundPossibleMove: findPossibleMoves(playerQueen, row, column)) {
                            moves.add(foundPossibleMove);
                        }
                    }
                }
            }
        }

        /**
         * If create an array just big enough to hold all the valid moves, add the possible moves from the List into the ArrayList, and return this one.
         * Otherwise, in case no possible moves have been found, return null.
         **/
        if (moves.size() > 0) {
            ArrayList<CheckersMove> moveArray = new ArrayList<>(moves.size());
            for (CheckersMove move: moves) {
                moveArray.add(move);
            }
            return moveArray;
        } else
            return null;
    }

    /**
     * getPossibleJumpsFrom
     * Description. Return a list of the possible jumps that the specified player can make starting from the specified row and column.
     * If no such jumps are possible, null is returned.  The logic is similar to the logic of the getPossibleMoves() method.
     * @param player
     * @param fromRow
     * @param fromColumn
     * @return
     */
    public ArrayList<CheckersMove> getPossibleJumpsFrom(int player, int fromRow, int fromColumn) {
        if (player != RED && player != BLACK)
            return null;
        int playerQueen = RED;  /** The constant representing a Queen belonging to player. */
        switch (player) {
            case RED:
                playerQueen = RED_QUEEN;
                break;
            case BLACK:
                playerQueen = BLACK_QUEEN;
                break;
        }
        ArrayList<CheckersMove> jumps = new ArrayList<CheckersMove>();  /** The possible jumps will be stored in this list. */
        if (pawnAt(fromRow, fromColumn) == player) {
            ArrayList<Integer> opponents = getOpponents(player);
            if (opponents.contains(pawnAt(fromRow + 1, fromColumn + 1)) && canJump(player, fromRow + 1, fromColumn + 1, fromRow + 2, fromColumn + 2))
                jumps.add(new CheckersMove(player, fromRow, fromColumn, fromRow + 2, fromColumn + 2));
            if (opponents.contains(pawnAt(fromRow -1, fromColumn + 1)) && canJump(player, fromRow - 1, fromColumn + 1, fromRow - 2, fromColumn + 2))
                jumps.add(new CheckersMove(player, fromRow, fromColumn, fromRow - 2, fromColumn + 2));
            if (opponents.contains(pawnAt(fromRow + 1, fromColumn - 1)) && canJump(player, fromRow + 1, fromColumn - 1, fromRow + 2, fromColumn - 2))
                jumps.add(new CheckersMove(player, fromRow, fromColumn, fromRow + 2, fromColumn - 2));
            if (opponents.contains(pawnAt(fromRow - 1, fromColumn - 1)) && canJump(player, fromRow - 1, fromColumn - 1, fromRow- 2 , fromColumn - 2))
                jumps.add(new CheckersMove(player, fromRow, fromColumn, fromRow - 2, fromColumn - 2));
        } else if (pawnAt(fromRow, fromColumn) == playerQueen) {
            ArrayList<Integer> opponents = getOpponents(playerQueen);
            for (int i = 1, j = 1;  i < (BOARD_DIM - fromRow -1) &&  j < (BOARD_DIM - fromColumn - 1); i++, j++) {
                int toFarOpponentRow = fromRow + i;
                int toFarOpponentColumn = fromColumn + j;
                int inFrontOfFarOpponentRow = toFarOpponentRow - 1;
                int inFrontOfFarOpponentColumn = toFarOpponentColumn - 1;
                boolean pawnIsOpponent = opponents.contains( pawnAt(toFarOpponentRow, toFarOpponentColumn));
                boolean emptyBoardInFrontOfFarOpponent = (
                        pawnAt(inFrontOfFarOpponentRow, inFrontOfFarOpponentColumn) == EMPTY ||
                                pawnAt(inFrontOfFarOpponentRow, inFrontOfFarOpponentColumn) == playerQueen
                );
                if (pawnIsOpponent && emptyBoardInFrontOfFarOpponent && canJump(playerQueen, toFarOpponentRow, toFarOpponentColumn, toFarOpponentRow + 1, toFarOpponentColumn + 1))
                    jumps.add(new CheckersMove(playerQueen, fromRow, fromColumn, toFarOpponentRow + 1, toFarOpponentColumn + 1));
            }
            for (int i = 1, j = 1; i <= (fromRow - 0 - 1) && i++ < (BOARD_DIM - fromColumn - 1); i++, j++) {
                int toFarOpponentRow = fromRow - i;
                int toFarOpponentColumn = fromColumn + j;
                int inFrontOfFarOpponentRow = toFarOpponentRow + 1;
                int inFrontOfFarOpponentColumn = toFarOpponentColumn - 1;
                boolean pawnIsOpponent = opponents.contains( pawnAt(toFarOpponentRow, toFarOpponentColumn));
                boolean emptyBoardInFrontOfFarOpponent = (
                        pawnAt(inFrontOfFarOpponentRow, inFrontOfFarOpponentColumn) == EMPTY ||
                                pawnAt(inFrontOfFarOpponentRow, inFrontOfFarOpponentColumn) == playerQueen
                );
                if (pawnIsOpponent && emptyBoardInFrontOfFarOpponent && canJump(playerQueen, toFarOpponentRow, toFarOpponentColumn, toFarOpponentRow - 1, toFarOpponentColumn + 1))
                    jumps.add(new CheckersMove(playerQueen, fromRow, fromColumn, toFarOpponentRow - 1, toFarOpponentColumn + 1));
            }
            for (int i = 1, j = 1; i < (BOARD_DIM - fromRow - 1) && j <= (fromColumn - 0 - 1); i++, j++) {
                int toFarOpponentRow = fromRow + i;
                int toFarOpponentColumn = fromColumn - j;
                int inFrontOfFarOpponentRow = toFarOpponentRow - 1;
                int inFrontOfFarOpponentColumn = toFarOpponentColumn + 1;
                boolean pawnIsOpponent = opponents.contains( pawnAt(toFarOpponentRow, toFarOpponentColumn));
                boolean emptyBoardInFrontOfFarOpponent = (
                        pawnAt(inFrontOfFarOpponentRow, inFrontOfFarOpponentColumn) == EMPTY ||
                                pawnAt(inFrontOfFarOpponentRow, inFrontOfFarOpponentColumn) == playerQueen
                );
                if (pawnIsOpponent && emptyBoardInFrontOfFarOpponent && canJump(playerQueen, toFarOpponentRow, toFarOpponentColumn, toFarOpponentRow + 1, toFarOpponentColumn - 1))
                    jumps.add(new CheckersMove(playerQueen, fromRow, fromColumn, toFarOpponentRow + 1, toFarOpponentColumn - 1));
            }
            for (int i = 1, j = 1; i <= fromRow && j <= (fromColumn - 0 - 1); i++, j++) {
                int toFarOpponentRow = fromRow - i;
                int toFarOpponentColumn = fromColumn - j;
                int inFrontOfFarOpponentRow = toFarOpponentRow + 1;
                int inFrontOfFarOpponentColumn = toFarOpponentColumn + 1;
                boolean pawnIsOpponent = opponents.contains( pawnAt(toFarOpponentRow, toFarOpponentColumn));
                boolean emptyBoardInFrontOfFarOpponent = (
                        pawnAt(inFrontOfFarOpponentRow, inFrontOfFarOpponentColumn) == EMPTY ||
                                pawnAt(inFrontOfFarOpponentRow, inFrontOfFarOpponentColumn
                        ) == playerQueen);
                if (pawnIsOpponent && emptyBoardInFrontOfFarOpponent && canJump(playerQueen, toFarOpponentRow, fromColumn, toFarOpponentRow - 1, toFarOpponentColumn - 1))
                    jumps.add(new CheckersMove(playerQueen, fromRow, fromColumn, toFarOpponentRow - 1, toFarOpponentColumn - 1));
            }
        }

        if (jumps.size() > 0)  {
            ArrayList<CheckersMove> jumpArray = new ArrayList<>(jumps.size());
            for (CheckersMove move: jumps) {
                jumpArray.add(move);
            }
            return jumpArray;
        } else
            return null;
    }

    /**
     * canJump
     * Description: This is called by the two previous methods to check whether the player can validly jump from (row,column) to (toRow,toCcolumn).
     * It is assumed that the player has a pawn at (row,column), that (toRow,toColumn) is a position that is 2 rows
     * and 2 columns distant from (row,column) and that (skippedRow,skippedColumn) is the square between (row,column) and (toRow,toColumn).
     * @param player
     * @param skippedRow
     * @param skippedColumn
     * @param toRow
     * @param toColumn
     * @return
     */
    public boolean canJump(int player, int skippedRow, int skippedColumn, int toRow, int toColumn) {
        if (toRow < 0 || toRow >= BOARD_DIM || toColumn < 0 || toColumn >= BOARD_DIM)
            return false;  /** (toRow,toColumn) is off the board. */
        if (board[toRow][toColumn] != EMPTY)
            return false;  /** (toRow,ToColumn) already contains a pawn. */
        ArrayList<Integer> opponents = getOpponents(player);
        return opponents.contains(pawnAt(skippedRow, skippedColumn));
    }

    /**
     * findPossibleMoves
     * Description: A method to find possible moves.
     * @param player
     * @param row
     * @param column
     * @return listOfMovesFound
     */
    private ArrayList<CheckersMove> findPossibleMoves(int player, int row, int column) {
        ArrayList<CheckersMove> listOfMovesFound = new ArrayList<>();
        if (player == RED_QUEEN || player == BLACK_QUEEN) {
            for(int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--,j--) {
                if (pawnAt(i, j) != EMPTY)
                    break;
                int nextEmptyRow = i;
                int nextEmptyColumn = j;
                listOfMovesFound.add(new CheckersMove(player, row, column, nextEmptyRow, nextEmptyColumn));
            }
            for(int i = row - 1, j = column + 1; i >= 0 && j < BOARD_DIM; i--, j++) {
                if (pawnAt(i, j) != EMPTY)
                    break;
                int nextEmptyRow = i;
                int nextEmptyColumn = j;
                listOfMovesFound.add(new CheckersMove(player, row, column, nextEmptyRow, nextEmptyColumn));
            }
            for(int i = row + 1, j = column - 1; i < BOARD_DIM && j >= 0; i++, j--) {
                if (pawnAt(i, j) != EMPTY)
                    break;
                int nextEmptyRow = i;
                int nextEmptyColumn = j;
                listOfMovesFound.add(new CheckersMove(player, row, column, nextEmptyRow, nextEmptyColumn));
            }
            for(int i = row + 1, j = column + 1; i < BOARD_DIM && j < BOARD_DIM; i++, j++) {
                if (pawnAt(i, j) != EMPTY)
                    break;
                int nextEmptyRow = i;
                int nextEmptyColumn = j;
                listOfMovesFound.add(new CheckersMove(player, row, column, nextEmptyRow, nextEmptyColumn));
            }
        } else if (player == RED) {
            int nextEmptyLeftColumn = column - 1;
            int nextEmptyRightColumn = column + 1;
            int nextEmptyRow = row - 1;
            if ((nextEmptyRow >= 0 && nextEmptyRow < BOARD_DIM && nextEmptyLeftColumn >= 0 && nextEmptyLeftColumn < BOARD_DIM) && board[nextEmptyRow][nextEmptyLeftColumn] == EMPTY)
                listOfMovesFound.add(new CheckersMove(player, row, column, nextEmptyRow, nextEmptyLeftColumn));
            if ((nextEmptyRow >= 0 && nextEmptyRow < BOARD_DIM && nextEmptyRightColumn >= 0 && nextEmptyRightColumn < BOARD_DIM) && board[nextEmptyRow][nextEmptyRightColumn] == EMPTY)
                listOfMovesFound.add(new CheckersMove(player, row, column, nextEmptyRow, nextEmptyRightColumn));
        } else if (player == BLACK) {
            int nextEmptyLeftColumn = column - 1;
            int nextEmptyRightColumn = column + 1;
            int nextEmptyRow = row + 1;
            if ((nextEmptyRow >= 0 && nextEmptyRow < BOARD_DIM && nextEmptyLeftColumn >= 0 && nextEmptyLeftColumn < BOARD_DIM) && pawnAt(nextEmptyRow, nextEmptyLeftColumn) == EMPTY)
                listOfMovesFound.add(new CheckersMove(player, row, column, nextEmptyRow, nextEmptyLeftColumn));
            if ((nextEmptyRow >= 0 && nextEmptyRow < BOARD_DIM && nextEmptyRightColumn >= 0 && nextEmptyRightColumn < BOARD_DIM) && pawnAt(nextEmptyRow, nextEmptyRightColumn) == EMPTY)
                listOfMovesFound.add(new CheckersMove(player, row, column, nextEmptyRow, nextEmptyRightColumn));
        }
        return listOfMovesFound;
    }

    /**
     * findPossibleJumps
     * Description: A method to find possible jumps.
     * @param player
     * @param row
     * @param column
     * @return ArrayList<CheckersMove> listOfJumpsFound
     */
    ArrayList<CheckersMove> findPossibleJumps(int player, int row, int column) {
        ArrayList<CheckersMove> listOfJumpsFound = new ArrayList<>();
        ArrayList<Integer> opponents = getOpponents(player);
        if (player == RED_QUEEN || player == BLACK_QUEEN) {
            for(int i = row - 1, j = column - 1; i >= 0 && j >= 0; i--,j--) {
                if (opponents.contains(pawnAt(i, j))) {
                    int opponentRow = i;
                    int opponentColumn = j;
                    int nextOpponentRow = opponentRow - 1;
                    int nextOpponentColumn = opponentColumn - 1;
                    int frontOfOpponentRow = opponentRow + 1;
                    int frontOfOpponentColumn = opponentColumn + 1;
                    boolean nextOpponentIsEmpty = pawnAt(nextOpponentRow, nextOpponentColumn) == EMPTY;
                    boolean frontOfOpponentIsEmpty = (
                            pawnAt(frontOfOpponentRow, frontOfOpponentColumn) == EMPTY ||
                                    pawnAt(frontOfOpponentRow, frontOfOpponentColumn) == player
                    );
                    if (nextOpponentRow >= 0 && nextOpponentColumn >= 0 && nextOpponentIsEmpty && frontOfOpponentIsEmpty)
                        listOfJumpsFound.add(new CheckersMove(player, row, column, nextOpponentRow, nextOpponentColumn));
                    break;
                } else if (pawnAt(i, j) != EMPTY)
                    break;
            }
            for(int i = row - 1, j = column + 1; i >= 0 && j < BOARD_DIM; i--, j++) {
                if (opponents.contains(pawnAt(i, j))) {
                    int opponentRow = i;
                    int opponentColumn = j;
                    int nextOpponentRow = opponentRow - 1;
                    int nextOpponentColumn = opponentColumn + 1;
                    int frontOfOpponentRow = opponentRow + 1;
                    int frontOfOpponentColumn = opponentColumn - 1;
                    boolean nextOpponentIsEmpty = pawnAt(nextOpponentRow, nextOpponentColumn) == EMPTY;
                    boolean frontOfOpponentIsEmpty = (
                            pawnAt(frontOfOpponentRow, frontOfOpponentColumn) == EMPTY ||
                                    pawnAt(frontOfOpponentRow, frontOfOpponentColumn) == player
                    );
                    if (nextOpponentRow >= 0 && nextOpponentColumn < BOARD_DIM && nextOpponentIsEmpty && frontOfOpponentIsEmpty)
                        listOfJumpsFound.add(new CheckersMove(player, row, column, nextOpponentRow, nextOpponentColumn));
                    break;
                } else if (board[i][j] != EMPTY)
                    break;
            }
            for(int i = row + 1, j = column - 1; i < BOARD_DIM && j >= 0; i++, j--) {
                if (opponents.contains(pawnAt(i, j))) {
                    int opponentRow = i;
                    int opponentColumn = j;
                    int nextOpponentRow = opponentRow + 1;
                    int nextOpponentColumn = opponentColumn - 1;
                    int frontOfOpponentRow = opponentRow - 1;
                    int frontOfOpponentColumn = opponentColumn + 1;
                    boolean nextOpponentIsEmpty = pawnAt(nextOpponentRow, nextOpponentColumn) == EMPTY;
                    boolean frontOfOpponentIsEmpty = (
                            pawnAt(frontOfOpponentRow, frontOfOpponentColumn) == EMPTY ||
                                    pawnAt(frontOfOpponentRow, frontOfOpponentColumn) == player
                    );
                    if (nextOpponentRow < BOARD_DIM && nextOpponentColumn >= 0 && nextOpponentIsEmpty && frontOfOpponentIsEmpty)
                        listOfJumpsFound.add(new CheckersMove(player, row, column, nextOpponentRow, nextOpponentColumn));
                    break;
                } else if (pawnAt(i, j) != EMPTY)
                    break;
            }
            for(int i = row + 1, j = column + 1; i < BOARD_DIM && j < BOARD_DIM; i++, j++) {
                if (opponents.contains(pawnAt(i, j))) {
                    int opponentRow = i;
                    int opponentColumn = j;
                    int nextOpponentRow = opponentRow + 1;
                    int nextOpponentColumn = opponentColumn + 1;
                    int frontOfOpponentRow = opponentRow - 1;
                    int frontOfOpponentColumn = opponentColumn - 1;
                    boolean nextOpponentIsEmpty = pawnAt(nextOpponentRow, nextOpponentColumn) == EMPTY;
                    boolean frontOfOpponentIsEmpty = (
                            pawnAt(frontOfOpponentRow, frontOfOpponentColumn) == EMPTY ||
                                    pawnAt(frontOfOpponentRow, frontOfOpponentColumn) == player
                    );
                    if (nextOpponentRow < BOARD_DIM && nextOpponentColumn < BOARD_DIM && nextOpponentIsEmpty && frontOfOpponentIsEmpty)
                        listOfJumpsFound.add(new CheckersMove(player, row, column, nextOpponentRow, nextOpponentColumn));
                    break;
                } else if (pawnAt(i, j) != EMPTY)
                    break;
            }
        } else if (player == RED || player == BLACK) {
            int nextLeftColumn = column - 1;
            int nextRightColumn = column + 1;
            int nextBellowRow = row - 1;
            int nextAboveRow = row + 1;
            int nextEmptyRow = -1;
            int nextEmptyColumn = -1;
            if (nextLeftColumn >= 0 && nextBellowRow >= 0) {
                if (opponents.contains(pawnAt(nextBellowRow, nextLeftColumn))) {
                    nextEmptyRow = nextBellowRow - 1;
                    nextEmptyColumn = nextLeftColumn - 1;
                    if (nextEmptyRow >= 0 && nextEmptyColumn >= 0 && pawnAt(nextEmptyRow, nextEmptyColumn) == EMPTY)
                        listOfJumpsFound.add(new CheckersMove(player, row, column, nextEmptyRow, nextEmptyColumn));
                }
            }
            if (nextRightColumn < BOARD_DIM && nextBellowRow >= 0) {
                if (opponents.contains(pawnAt(nextBellowRow, nextRightColumn))) {
                    nextEmptyRow = nextBellowRow - 1;
                    nextEmptyColumn = nextRightColumn + 1;
                    if (nextEmptyRow >= 0 && nextEmptyColumn < BOARD_DIM && pawnAt(nextEmptyRow, nextEmptyColumn) == EMPTY)
                        listOfJumpsFound.add(new CheckersMove(player, row, column, nextEmptyRow, nextEmptyColumn));
                }
            }
            if (nextLeftColumn >= 0 && nextAboveRow < BOARD_DIM) {
                if (opponents.contains(pawnAt(nextAboveRow, nextLeftColumn))) {
                    nextEmptyRow = nextAboveRow + 1;
                    nextEmptyColumn = nextLeftColumn - 1;
                    if (nextEmptyRow < BOARD_DIM && nextEmptyColumn >= 0 && pawnAt(nextEmptyRow, nextEmptyColumn) == EMPTY)
                        listOfJumpsFound.add(new CheckersMove(player, row, column, nextEmptyRow, nextEmptyColumn));
                }
            }
            if (nextRightColumn < BOARD_DIM && nextAboveRow < BOARD_DIM) {
                if (opponents.contains(pawnAt(nextAboveRow, nextRightColumn))) {
                    nextEmptyRow = nextAboveRow + 1;
                    nextEmptyColumn = nextRightColumn + 1;
                    if (nextEmptyRow < BOARD_DIM && nextEmptyColumn < BOARD_DIM && pawnAt(nextEmptyRow, nextEmptyColumn) == EMPTY)
                        listOfJumpsFound.add(new CheckersMove(player, row, column, nextEmptyRow, nextEmptyColumn));
                }
            }

        }
        return listOfJumpsFound;
    }

    public int[][] getCurrentBoard() {
        return getBoard();
    }
}
