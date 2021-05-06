package com.kodilla.game.logic;

import com.kodilla.game.view.*;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * This canvas displays a 320-by-320 checkerboard pattern with a 2-pixel dark red border.
 * The canvas will be exactly 324-by-324 pixels. This class contains methods that are called in response to a mouse click on the canvas and
 * in response to clicks on the New Game and Resign buttons. Note that the "New Game" and "Resign" buttons must be
 * created before the Board constructor is called, since the constructor references the buttons (in the call to doNewGame()).
 */
public class CheckersBoard extends Canvas {

    private static int BOARD_DIM = 8;

//    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd'at'HHmmss");

    CheckersStateOfGame checkersStateOfGame;

    CheckersData checkersData;     /** The data for the checkers board is kept here. This board is also responsible for
                            * generating lists of legal moves. */

    boolean inProgress; /** Check if a game is currently in progress. */

    /** The next three variables are valid only when the game is in progress. */

    int currentPlayer;      /** Indicator whose turn it is now.  The possible values are CheckersData.RED and CheckersData.BLACK. */

    int selectedRow, selectedColumn;   /** If the current player has selected a pawn to move, these give the row and column
                                        * containing that pawn. If no pawn is yet selected, then selectedRow is -1. */

    List<CheckersMove> possibleJumps;  /** An array containing the valid moves for the current player. */

    CheckersResult result;

    Integer maxWonGamesInSet = 0;

    static ArrayList<Integer> listOfMaxWonGamesInSet;

    CheckersWonGames checkersWonGames;

//    HashMap<Integer, String> mapOfSets = new HashMap<Integer, String>();
    HashMap<String, String> mapOfSets = new HashMap<String, String>();

    static Integer defaultIndexOfListOfMaxWonGames = 1;

    /**
     * Constructor.  Creates a CheckersData to represent the contents of the checkerboard,
     * and calls doNewGame to start the first game.
     */
    CheckersBoard() {
        super(324,324);  /** canvas is 324-by-324 pixels */
        checkersData = new CheckersData();
        checkersStateOfGame = new CheckersStateOfGame();
        result = checkersData.checkersResult;
        loadListOfMaxWonGamesInSet();
        int numberDirectories = checkersStateOfGame.lengthOfListFiles(checkersStateOfGame.getPathname());
        if (numberDirectories > 0) {
            String question = "Whether to load a recently saved game";
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION, question + " ?", ButtonType.APPLY, ButtonType.CANCEL);
            alert.showAndWait();
            if (alert.getResult() == ButtonType.APPLY) {
                checkersStateOfGame.reloadGame();
                currentPlayer = CheckersStateOfGame.getCurrentPlayer();
                possibleJumps = CheckersStateOfGame.getPossibleJumps();
                selectedRow = -1;
                selectedColumn = -1;
                inProgress = true;
                drawBoard();
                return;
            } else {
                checkersStateOfGame.removeDirectory(checkersStateOfGame.getPathname());
            }
        }
        doNewGame();
    }

    /**
     * Start a new game.  This method is called when the Board is first created and when the "New Game" button is clicked.
     * Event handling is set up in the start() method in the main class.
     */
    void doNewGame() {
        if (inProgress == true) {
            /** This should not be possible, but it doesn't hurt to check. */
            Checkers.setTextInMessageOfState("Finish the current game first!");
            return;
        }
        checkersData.setUpPawnsOnBoard();   /** Set up the pawns. */
        currentPlayer = CheckersData.RED;   /** RED moves first. */
        possibleJumps = checkersData.getPossibleMoves(checkersData.RED);  /** Get RED's possible moves. */
        selectedRow = -1;   /** RED has not yet selected a pawn to move. */
        Checkers.setTextInMessageOfState("RED:  Make your move.");
        Checkers.setTextInMessageOfRedMove("RED move:");
        Checkers.setTextInMessageOfBlackMove("BLACK move:");
        Checkers.setTextInMessageOfRedWins("RED:\t" + result.getRedWins());
        Checkers.setTextInMessageOfBlackWins("BLACK:\t" + result.getBlackWins());

        if (mapOfSets.size() > 0) {
            Checkers.setDisableSaveSetsButton(false);
            Checkers.setTextInMessageOfSets(convertWithIteration(mapOfSets));
        } else {
            Checkers.setDisableSaveSetsButton(true);
            Checkers.setTextInMessageOfSets("No sets.");
        }
        inProgress = true;
        Checkers.setDisableNewGameButton(true);
        Checkers.setDisableResignButton(false);

        drawBoard();
    }

    /**
     * Current player resigns.  Game ends.  Opponent wins.  This method is called when the user clicks the "Resign" button.
     * Event handling is set up in the start() method in the main class.
     */
    void doResign() {
        if (inProgress == false) {  /** Should be impossible. */
            Checkers.setTextInMessageOfState("There is no game in progress!");
            return;
        }
        if (currentPlayer == checkersData.RED) {
            gameOver("RED resigns.  BLACK wins.");
            result.increaseBlackWins();
            Checkers.setTextInMessageOfBlackWins("BLACK:\t" + result.getBlackWins());
            puttingEntryToMap();
        } else if (currentPlayer == checkersData.BLACK) {
            gameOver("BLACK resigns.  RED wins.");
            result.increaseRedWins();
            Checkers.setTextInMessageOfRedWins("RED:\t" + result.getRedWins());
            puttingEntryToMap();
        }
        if (mapOfSets.size() > 0) {
            Checkers.setDisableSaveSetsButton(false);
        } else {
            Checkers.setDisableSaveSetsButton(true);
        }
    }

    /**
     * Current player resigns.  Game ends.  Opponent wins.  This method is called when the user clicks the "Resign" button.
     * Event handling is set up in the start() method in the main class.
     */
    void doWrongChange() {
        if (inProgress == false) {  /** Should be impossible. */
            Checkers.setTextInMessageOfState("There is no game in progress!");
            return;
        }
        if (currentPlayer == checkersData.RED) {
            gameOver("RED made substitution during the game.  BLACK wins.");
            result.increaseBlackWins();
            Checkers.setTextInMessageOfBlackWins("BLACK:\t" + result.getBlackWins());
            puttingEntryToMap();
        } else if (currentPlayer == checkersData.BLACK) {
            gameOver("BLACK made substitution during the game.  RED won.");
            result.increaseRedWins();
            Checkers.setTextInMessageOfRedWins("RED:\t" + result.getRedWins());
            puttingEntryToMap();
        }
        if (mapOfSets.size() > 0) {
            Checkers.setDisableSaveSetsButton(false);
        } else {
            Checkers.setDisableSaveSetsButton(true);
        }
    }

    /**
     *
     */
    void puttingEntryToMap() {
        if (maxWonGamesInSet > 0 && (result.getRedWins() >= maxWonGamesInSet || result.getBlackWins() >= maxWonGamesInSet)) {
            SimpleDateFormat formatterOfKey = new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss");
            Date date = new Date(System.currentTimeMillis());
            mapOfSets.put(
                    formatterOfKey.format(date),
                    result.toStringSet()
            );
            result = new CheckersResult(0, 0);
            if (mapOfSets.size() > 0)
                Checkers.setTextInMessageOfSets(convertWithIteration(mapOfSets));
            if (result.getRedWins() >= maxWonGamesInSet)
                gameOver("Set over. RED won.");
            else if (result.getBlackWins() >= maxWonGamesInSet)
                gameOver("Set over. BLACK won.");
        }
    }

    /**
     * The game ends.  The parameter, str, is displayed as a message to the user. The states of the buttons are adjusted
     * so players can start a new game. This method is called when the game ends at any point in this class.
     */
    void gameOver(String string) {
        Checkers.setTextInMessageOfState(string);
        Checkers.setDisableNewGameButton(false);
        Checkers.setDisableResignButton(true);
        if (mapOfSets.size() > 0) {
            Checkers.setDisableSaveSetsButton(false);
        }
        else {
            Checkers.setDisableSaveSetsButton(true);
        }
        inProgress = false;
    }

    public void loadListOfMaxWonGamesInSet() {

        checkersWonGames = new CheckersWonGames();
        checkersWonGames.setDefaultIndex(defaultIndexOfListOfMaxWonGames);
        listOfMaxWonGamesInSet = checkersWonGames.getListOfMaxWonGamesInSet();
        checkersWonGames.addNewItem(0);
        checkersWonGames.addNewItem(3);
        checkersWonGames.addNewItem(6);
        checkersWonGames.addNewItem(10);
    }

    public ArrayList<Integer> getListOfMaxWonGamesInSet() {

        return listOfMaxWonGamesInSet;
    }

    public CheckersWonGames getCheckersWonGames() {

        return checkersWonGames;
    }

    /**
     * This is called by mousePressed() when a player clicks on the square in the specified row and col.
     * It has already been checked that a game is, in fact, in progress.
     */
    void doClickSquare(int row, int column) throws InterruptedException {
        /** If the player clicked on one of the pawns that the player can move, mark this row and col as selected and return.
         * (This might change a previous selection.)  Reset the message, in case it was previously displaying an error message.
         **/
        if (possibleJumps != null) {
            for (CheckersMove possibleMove: possibleJumps) {
                if (possibleMove.getFromRow() == row && possibleMove.getFromColumn() == column) {
                    selectedRow = row;
                    selectedColumn = column;
                    if (currentPlayer == checkersData.RED) {
                        Checkers.setTextInMessageOfState("RED:  Make your move.");
                    } else if (currentPlayer == checkersData.BLACK) {
                        Checkers.setTextInMessageOfState("BLACK:  Make move.");
                    }
                    drawBoard();
                }
            }
        }

        /**
         * If no pawn has been selected to be moved, the user must first select a pawn. Show an error message and return.
         **/
        if (selectedRow < 0) {
            if (currentPlayer == checkersData.RED)
                Checkers.setTextInMessageOfState("RED: Click the pawn you want to move.");
            else if (currentPlayer == checkersData.BLACK)
                Checkers.setTextInMessageOfState("BLACK: Click the pawn to move.");
            drawBoard();
//            return;
        }
        /** If the user clicked on a square where the selected pawn can be validly moved, then make the move and return.
         **/
        if (possibleJumps != null)
            for (CheckersMove possibleMove: possibleJumps) {
                if (possibleMove.getFromRow() == selectedRow && possibleMove.getFromColumn() == selectedColumn && possibleMove.getToRow() == row && possibleMove.getToColumn() == column) {
                    makingMove(possibleMove);
                    return;
                }
            }

        /**
         * If we get to this point, there is a pawn selected, and the square where the user just clicked is not one
         * where that pawn can be legally moved. Show an error message.
         **/
        if (currentPlayer == checkersData.RED)
            Checkers.setTextInMessageOfState("RED: Click the square you want to move to.");
        else if (currentPlayer == checkersData.BLACK)
            Checkers.setTextInMessageOfState("BLACK: Click the square you want to move to.");
        drawBoard();
    }

    /**
     * This is called when the current player has chosen the specified move. Make the move, and then either end
     * or continue the game appropriately.
     */
    void makingMove(CheckersMove move) throws InterruptedException {
        /** If the move was a jump, it's possible that the player has another jump.
         * Check for legal jumps starting from the square that the player just moved to.
         * If there are any, the player must jump.  The same player continues moving.
         **/
        move.doMove();
        if (currentPlayer == checkersData.RED || currentPlayer == checkersData.RED_QUEEN)
            Checkers.setTextInMessageOfRedMove("RED " + move.toString());
        else if (currentPlayer == checkersData.BLACK || currentPlayer == checkersData.BLACK_QUEEN)
            Checkers.setTextInMessageOfBlackMove("BLACK " + move.toString());
        if (move.isJump()) {
            possibleJumps = checkersData.getPossibleJumpsFrom(currentPlayer, move.getToRow(), move.getToColumn());
            if (possibleJumps != null) {
                if (currentPlayer == checkersData.RED || currentPlayer == checkersData.RED_QUEEN)
                    Checkers.setTextInMessageOfState("RED:  You must continue jumping.");
                else if (currentPlayer == checkersData.BLACK || currentPlayer == checkersData.BLACK_QUEEN)
                    Checkers.setTextInMessageOfState("BLACK:  Must continue jumping.");
                selectedRow = move.getToRow();  /** Since only one pawn can be moved, select it. */
                selectedColumn = move.getToColumn();
                possibleJumps = checkersData.getPossibleJumpsFrom(currentPlayer, selectedRow, selectedColumn);
                drawBoard();
                if (currentPlayer == checkersData.RED || currentPlayer == checkersData.RED_QUEEN)
                    if (possibleJumps != null) {
                        List<CheckersMove> possibleJumps = this.possibleJumps.stream().filter(m -> m.isJump()).collect(Collectors.toList());
                        for (CheckersMove possibleJump: possibleJumps) {
                            selectedRow = possibleJump.getFromRow();
                            selectedColumn = possibleJump.getFromColumn();
                            this.possibleJumps = checkersData.getPossibleJumpsFrom(currentPlayer, selectedRow, selectedColumn);
                            return;

                        }
                    } else
                        return;
                else if (currentPlayer == checkersData.BLACK || currentPlayer == checkersData.BLACK_QUEEN)
                    if (possibleJumps != null) {
                        for (CheckersMove possibleMove: possibleJumps) {
                            if (currentPlayer == checkersData.BLACK && possibleMove.isJump()) {
                                selectedRow = possibleMove.getFromRow();
                                selectedColumn = possibleMove.getFromColumn();
                                TimeUnit.SECONDS.sleep((long) 9.9E-01);
                                doClickSquare(possibleMove.getToRow(), possibleMove.getToColumn());
                            }
                        }
                        return;
                    }
            }
        }

        /**
         * The current player's turn is ended, so change to the other player. Get that player's legal moves.
         * If the player has no legal moves, then the game ends.
         **/
        if (currentPlayer == CheckersData.RED) {
            currentPlayer = checkersData.BLACK;
            possibleJumps = checkersData.getPossibleMoves(currentPlayer);
            if (possibleJumps == null) {
                gameOver("BLACK has no moves. RED won.");
                result.increaseRedWins();
                Checkers.setTextInMessageOfRedWins("RED:\t" + result.getRedWins());
                puttingEntryToMap();
                if (mapOfSets.size() > 0) {
                    Checkers.setDisableSaveSetsButton(false);
                }
                else {
                    Checkers.setDisableSaveSetsButton(true);
                }
            } else if (possibleJumps.stream().filter(m -> m.isJump()).collect(Collectors.toList()).size() > 0) {
                Checkers.setTextInMessageOfBlackMove("BLACK. must " + possibleJumps.stream().filter(m -> m.isJump()).map(j -> j.toString()).collect(Collectors.joining()));
                Checkers.setTextInMessageOfState("BLACK: Make move. You must jump.");
            } else {
                Checkers.setTextInMessageOfState("BLACK: Make move.");
            }
        } else if (currentPlayer == CheckersData.BLACK) {
            currentPlayer = checkersData.RED;
            possibleJumps = checkersData.getPossibleMoves(currentPlayer);
            if (possibleJumps == null) {
                gameOver("RED has no moves. BLACK wins.");
                result.increaseBlackWins();
                Checkers.setTextInMessageOfBlackWins("BLACK:\t" + result.getBlackWins());
                puttingEntryToMap();
                if (mapOfSets.size() > 0) {
                    Checkers.setDisableSaveSetsButton(false);
                }
                else {
                    Checkers.setDisableSaveSetsButton(true);
                }
            } else {
                if (possibleJumps.stream().filter(m -> m.isJump()).collect(Collectors.toList()).size() > 0) {
                    StringBuilder stringBuilder = new StringBuilder();
                    for (CheckersMove m : possibleJumps) {
                        if (m.isJump()) {
                            int skippedRow = getOneBeforeLast(m.getFromRow(), m.getToRow());
                            int skippedColumn = getOneBeforeLast(m.getFromColumn(), m.getToColumn());
                            boolean canJump = checkersData.canJump(checkersData.pawnAt(m.getFromRow(),m.getFromColumn()), skippedRow, skippedColumn, m.getToRow(), m.getToColumn());
                            if (canJump) {
                                String strOfJump = m.toString();
                                if (strOfJump.contains("move"))
                                    strOfJump.replace("mov","jump");
                                stringBuilder.append(strOfJump);
                            }
                        }
                    }
                    if (stringBuilder.length() > 0)
                        Checkers.setTextInMessageOfRedMove("RED. must " + stringBuilder.toString());
                    /** Version before change. */
                    if (stringBuilder.length() > 0)
                        Checkers.setTextInMessageOfState("RED:  Make your move.  You must jump.");
                    else
                        Checkers.setTextInMessageOfState("RED:  Make your move.");
                } else
                    Checkers.setTextInMessageOfState("RED:  Make your move.");
//                CheckersStateOfGame.setCurrentBoard(checkersData.getCurrentBoard());
            }

            setCurrentBoard();
            setPossibleJumps();
        }
        /** Set selectedRow = -1 to record that the player has not yet selected a pawn to move. */
        if (currentPlayer == checkersData.RED) {
            selectedRow = -1;
            selectedColumn = -1;
        } else if (currentPlayer == checkersData.BLACK) {
            Random theGenerathor = new Random();
            if (possibleJumps != null) {
                int theIndex = 0 + theGenerathor.nextInt(possibleJumps.size());
                selectedRow = possibleJumps.get(theIndex).getFromRow(); /** for example */
                selectedColumn = possibleJumps.get(theIndex).getFromColumn(); /** for example */
                TimeUnit.SECONDS.sleep((long) 8.5E-01);
                doClickSquare(possibleJumps.get(theIndex).getToRow(), possibleJumps.get(theIndex).getToColumn());
            }
        }

        /** As a courtesy to the user, if all legal moves use the same pawn, then select that pawn automatically
         * so the user won't have to click on it to select it.
         **/
        if (possibleJumps != null) {
            boolean sameStartSquare = true;
            for (CheckersMove possibleMove: possibleJumps) {
                if (possibleMove.getFromRow() != possibleMove.getFromRow() || possibleMove.getFromColumn() != possibleMove.getFromColumn()) {
                    sameStartSquare = false;
                    break;
                }
            }
            if (sameStartSquare) {
                for (CheckersMove possibleMove: possibleJumps) {
                    if (currentPlayer == checkersData.BLACK && possibleMove.isJump()) {
                        selectedRow = possibleMove.getFromRow();
                        selectedColumn = possibleMove.getFromColumn();
                        TimeUnit.SECONDS.sleep((long) 9.5E-01);
                        doClickSquare(possibleMove.getToRow(), possibleMove.getToColumn());
                    }
                }
            }
            if (currentPlayer == CheckersData.BLACK) {
                possibleJumps = checkersData.getPossibleJumpsFrom(currentPlayer, selectedRow, selectedColumn);
            }
        }
        /** Make sure the board is redrawn in its new state. **/
        drawBoard();
    }

    private void setPossibleJumps() {

        CheckersStateOfGame.setCurrentPossibleJumps(possibleJumps);
        CheckersStateOfGame.setCurrentUser(currentPlayer);
    }

    public void setCurrentBoard() {
        CheckersStateOfGame.setCurrentBoard(checkersData.getCurrentBoard());
    }

    private int getOneBeforeLast(int first, int last) {
        if (last - first > 0)
            return (last - 1);
        else if (last - first < 0)
            return (last + 1);
        else
            return -1;
    }  /** end makingMove(); */

    /**
     * Draw a checkerboard pattern in gray and lightGray.  Draw the checkers.
     * If a game is in progress, highlight the legal moves.
     **/
    public void drawBoard() {
        GraphicsContext graphicsContext = getGraphicsContext2D();
        graphicsContext.setFont( Font.font(14) );

        /** Draw a two-pixel black border around the edges of the canvas. */
        graphicsContext.setStroke(Color.DARKRED);
        graphicsContext.setLineWidth(2);
        graphicsContext.strokeRect(1, 1, 322, 322);

        /** Draw the squares of the checkerboard and the checkers. */
        for (int row = 0; row < BOARD_DIM; row++) {
            for (int column = 0; column < BOARD_DIM; column++) {
                if ( row % 2 == column % 2 )
                    graphicsContext.setFill(Color.LIGHTGREEN);
                else
                    graphicsContext.setFill(Color.GREEN);
                graphicsContext.fillRect(2 + column*40, 2 + row*40, 40, 40);
                switch (checkersData.pawnAt(row,column)) {
                    case CheckersData.RED:
                        graphicsContext.setFill(Color.RED);
                        graphicsContext.fillOval(BOARD_DIM + column*40, BOARD_DIM + row*40, 28, 28);
                        break;
                    case CheckersData.BLACK:
                        graphicsContext.setFill(Color.BLACK);
                        graphicsContext.fillOval(BOARD_DIM + column*40, BOARD_DIM + row*40, 28, 28);
                        break;
                    case CheckersData.RED_QUEEN:
                        graphicsContext.setFill(Color.RED);
                        graphicsContext.fillOval(BOARD_DIM + column*40, BOARD_DIM + row*40, 28, 28);
                        graphicsContext.setFill(Color.WHITE);
                        graphicsContext.fillText("Queen", 7 + column*40, 26 + row*40);
                        break;
                    case CheckersData.BLACK_QUEEN:
                        graphicsContext.setFill(Color.BLACK);
                        graphicsContext.fillOval(BOARD_DIM + column*40, BOARD_DIM + row*40, 28, 28);
                        graphicsContext.setFill(Color.WHITE);
                        graphicsContext.fillText("Queen", 7 + column*40, 26 + row*40);
                        break;
                }
            }
        }

        /**
         * If a game is in progress, highlight the legal moves.
         * Note that validMoves is never null while a game is in progress.
         **/
        if (inProgress) {
            /** First, draw a 4-pixel cyan border around the pawns that can be moved. */
            graphicsContext.setStroke(Color.CYAN);
            graphicsContext.setLineWidth(4);
            if (possibleJumps != null)
                for (CheckersMove possibleMove: possibleJumps) {
                    graphicsContext.strokeRect(4 + possibleMove.getFromColumn() *40, 4 + possibleMove.getFromRow()*40, 36, 36);
                }

            /** If a pawn is selected for moving (i.e. if selectedRow >= 0), then draw a yellow border around
             * that pawn and draw green borders around each square that that pawn can be moved to.
             **/
            if (selectedRow >= 0) {
                graphicsContext.setStroke(Color.YELLOW);
                graphicsContext.setLineWidth(4);
                graphicsContext.strokeRect(4 + selectedColumn *40, 4 + selectedRow*40, 36, 36);
                graphicsContext.setStroke(Color.LIME);
                graphicsContext.setLineWidth(4);
                if (possibleJumps != null)
                    for (CheckersMove possibleMove: possibleJumps) {
                        if (possibleMove.getFromColumn() == selectedColumn && possibleMove.getFromRow() == selectedRow) {
                            graphicsContext.strokeRect(4 + possibleMove.getToColumn() *40, 4 + possibleMove.getToRow()*40, 36, 36);
                        }

                    }
            }
        }
    }

    /**
     * Respond to a user click on the board.  If no game is in progress, show an error message.
     * Otherwise, find the row and column that the user clicked and call doClickSquare() to handle it.
     */
    public void mousePressed(MouseEvent evt) throws InterruptedException {
        if (inProgress == false)
            Checkers.setTextInMessageOfState("Click \"New Game\" to start a new game.");
        else {
            int column = (int)((evt.getX() - 2) / 40);
            int row = (int)((evt.getY() - 2) / 40);
            if (column >= 0 && column < BOARD_DIM && row >= 0 && row < BOARD_DIM)
                if (currentPlayer != checkersData.BLACK || currentPlayer != checkersData.BLACK_QUEEN)
                    doClickSquare(row,column);
        }
    }

    public static int getDimOfBoard() {

        return BOARD_DIM;
    }

    public void changeMaxWonGamesInSet(Integer newMaxWonGamesInSet) {

        maxWonGamesInSet = newMaxWonGamesInSet;
    }

    private String convertWithIteration(HashMap<String, String> hashMap) {
        StringBuilder mapAsString = new StringBuilder("{");
        for (String key : hashMap.keySet()) {
            mapAsString.append(key + "=" + hashMap.get(key) + ", ");
        }
        mapAsString.delete(mapAsString.length()-2, mapAsString.length()).append("}");
        return mapAsString.toString();
    }

    public static int[][] getCurrentBoard() {
        CheckersData checkersData = new CheckersData();
        return checkersData.getCurrentBoard();
    }

//    public void saveStateOfBoard(long currentTimeMillis) {
//        int[][] theBoard = CheckersData.getBoard();
//        System.out.println("CheckersBoard::saveStateOfBoard(). " + "theBoard = " + theBoard);
//        for (int i = 0; i < CheckersBoard.getDimOfBoard(); i++) {
//            for (int j = 0; j < CheckersBoard.getDimOfBoard(); j++) {
//                System.out.println("CheckersBoard::saveStateOfBoard(). " + "board["+i+"]["+j+"] = " + theBoard[i][j]);
//            }
//        }
//    }
}
