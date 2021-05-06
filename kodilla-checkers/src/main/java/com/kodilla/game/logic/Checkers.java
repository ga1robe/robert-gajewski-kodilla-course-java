package com.kodilla.game.logic;

import com.kodilla.game.view.CheckersSetsResults;
import com.kodilla.game.view.CheckersStateOfGame;
import com.kodilla.game.view.CheckersWonGames;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.scene.layout.Pane;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This panel lets two users play checkers against each other. Red always starts the game.
 * If a player can jump an opponent's pawn, then the player must jump.  When a player can make no more moves, the game ends.
 */
public class Checkers extends Application {

    private static Label messageOfState; /** Label for displaying messages to the user. */
    private static Label messageOfRedMove; /** Label for displaying messages about the last move of RED player. */
    private static Label messageOfBlackMove; /** Label for displaying messages about the last move of BLACK player. */
    private static Label messageOfRedWins; /** Label for displaying messages about the wins games of RED player. */
    private static Label messageOfBlackWins; /** Label for displaying messages about the wins games of BLACK player. */
    private static Label messageOfSets; /** Label for displaying messages about Sets. */
    private static Label labelToSet; /** Label for displaying messages to the user. */
    private static Label labelForUsername; /** Label for displaying messages to the username. */

    private static TextField usernameField;

    static ArrayList<Integer> listOfMaxWonGamesInSet;
    static Integer defaultIndexOfListOfMaxWonGames = 1;

    CheckersStateOfGame checkersStateOfGame = new CheckersStateOfGame();

    public static void main(String[] args) {
        launch(args);
    }

    CheckersBoard checkersBoard;    /** A canvas on which a checker board is drawn, defined by a static nested subclass.
                            * Much of the game logic is defined in this class. */

    private static Button newGameButton;  /** Button for starting a new game. **/

    private static Button resignButton;     /** Button that a player can use to end the game by resigning. */

    private static Button saveSetsButton;  /** Button for save results in Sets. **/

    private static Button saveStateOfGameButton;  /** Button for save current state of a game. **/

    private static ChoiceBox<Integer> choiceBoxOfWonGamesInSet; /** create a choiceBoxOfWonGamesInSet */

    private Integer maxWonGamesInSet;

    /**
     * The constructor creates the Board (which in turn creates and manages the buttons and message label),
     * adds all the components, and sets the bounds of the components.  A null layout is used.  (This is the only thing
     * that is done in the main Checkers class.) */
    public void start(Stage stage) {
        /** Create the label that will show messages. */
        messageOfState = new Label("Click \"New Game\" to begin.");
        labelToSet = new Label("Won Games to Set:");
        labelForUsername = new Label("Username:");
        usernameField = new TextField("RED username");
        messageOfRedMove = new Label("RED move:");
        messageOfBlackMove = new Label("BLACK move:");
        messageOfRedWins = new Label("RED:\t" + "--");
        messageOfBlackWins = new Label("BLACK:\t" + "--");
        messageOfSets = new Label("No sets.");
        messageOfState.setTextFill( Color.rgb(100,255,100) ); /** Light green. */
        labelToSet.setTextFill( Color.rgb(100,255,100) ); /** Light green. */
        labelForUsername.setTextFill( Color.rgb(100,255,100) ); /** Light green. */
        messageOfRedMove.setTextFill( Color.rgb(255,100,100) ); /** Red. */
        messageOfBlackMove.setTextFill( Color.rgb(165,165,165) ); /** Grey. */
        messageOfRedWins.setTextFill( Color.rgb(255,100,100) ); /** Light red. */
        messageOfBlackWins.setTextFill( Color.rgb(165,165,165) ); /** Grey. */
        messageOfSets.setTextFill( Color.rgb(100,255,100) ); /** Light green. */
        messageOfState.setFont( Font.font(null, FontWeight.BOLD, 20) );
        labelToSet.setFont( Font.font(null, FontWeight.BOLD, 20) );
        labelForUsername.setFont( Font.font(null, FontWeight.BOLD, 20) );
        messageOfRedMove.setFont( Font.font(null, FontWeight.BOLD, 20) );
        messageOfBlackMove.setFont( Font.font(null, FontWeight.BOLD, 20) );
        messageOfRedWins.setFont( Font.font(null, FontWeight.BOLD, 28) );
        messageOfBlackWins.setFont( Font.font(null, FontWeight.BOLD, 28) );
        messageOfSets.setFont( Font.font(null, FontWeight.BOLD, 20) );
        messageOfRedMove.setWrapText(true);
        messageOfBlackMove.setWrapText(true);
        messageOfSets.setWrapText(true);
        messageOfRedMove.setMaxWidth(260);
        messageOfBlackMove.setMaxWidth(260);
        messageOfSets.setMaxWidth(430);
        messageOfRedWins.setMaxWidth(144);
        messageOfBlackWins.setMaxWidth(144);


        /**
         * Create the buttons and the board.  The buttons MUST be created first, since they are used in
         * the CheckerBoard constructor!
         **/
        newGameButton = new Button("New Game");
        resignButton = new Button("Resign");
        saveSetsButton = new Button("Save Results");
        saveStateOfGameButton = new Button("Save State of Game");

        checkersBoard = new CheckersBoard(); /** a subclass of Canvas, defined below **/
        checkersBoard.drawBoard();  /** draws the content of the checkerboard **/

        checkersBoard.loadListOfMaxWonGamesInSet();

        listOfMaxWonGamesInSet = checkersBoard.getListOfMaxWonGamesInSet();
        CheckersWonGames checkersWonGames = checkersBoard.getCheckersWonGames();

        /** create a choiceBoxOfWonGamesInSet */
        choiceBoxOfWonGamesInSet = new ChoiceBox<Integer>(FXCollections.observableArrayList(checkersWonGames.getListOfMaxWonGamesInSet()));
        choiceBoxOfWonGamesInSet.setValue(checkersWonGames.getDefaultValue());
        maxWonGamesInSet = choiceBoxOfWonGamesInSet.getValue();
        checkersBoard.changeMaxWonGamesInSet(maxWonGamesInSet);
        choiceBoxOfWonGamesInSet.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                maxWonGamesInSet = choiceBoxOfWonGamesInSet.getItems().get((Integer) newValue);
                checkersBoard.changeMaxWonGamesInSet(maxWonGamesInSet);
                if (checkersBoard.inProgress) checkersBoard.doWrongChange();
            }
        });

        /**
         * Set up ActionEvent handlers for the buttons and a MousePressed handler for the board.
         * The handlers call instance methods in the board object.
         **/
        newGameButton.setOnAction( (ActionEvent e) -> {
            checkersBoard.doNewGame();
        });
        resignButton.setOnAction( (ActionEvent e) -> checkersBoard.doResign() );
        saveSetsButton.setOnAction( (ActionEvent e) -> {
            try {
                CheckersSetsResults checkersSetsResults = new CheckersSetsResults();
                HashMap<String, String> setsToSave = new HashMap<>();
                checkersSetsResults.saveSetsResults(setsToSave);
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        saveStateOfGameButton.setOnAction( (ActionEvent e) -> {
            try {
                checkersStateOfGame.saveStateOfBoard(System.currentTimeMillis());
                /** TODO. tests. */
                checkersStateOfGame.savePossibleJumps(System.currentTimeMillis());
                checkersStateOfGame.saveStateOfMessages(System.currentTimeMillis());
                checkersStateOfGame.saveStateOfDisableButtons(System.currentTimeMillis());
                checkersStateOfGame.saveStateOfCheckFields(System.currentTimeMillis());
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
        });
        checkersBoard.setOnMousePressed((MouseEvent e) -> {
            try {
                checkersBoard.mousePressed(e);
            } catch (InterruptedException interruptedException) {
                interruptedException.printStackTrace();
            }
        });

        /** Set the location of each child by calling its relocate() method */
        checkersBoard.relocate(20,20);
        newGameButton.relocate(730, 330);
        resignButton.relocate(620, 330);
        saveSetsButton.relocate(840, 330);
        saveStateOfGameButton.relocate(970, 330);
        messageOfState.relocate(20, 370);
        labelToSet.relocate(640, 60);
        labelForUsername.relocate(640, 25);
        usernameField.relocate(750, 22);
        messageOfBlackMove.relocate(370, 30);
        messageOfRedMove.relocate(370, 190);
        messageOfBlackWins.relocate(640, 100);
        messageOfRedWins.relocate(640, 140);
        choiceBoxOfWonGamesInSet.relocate(820, 60);
        messageOfSets.relocate(640, 200);

        /**
         * Set the sizes of the buttons.  For this to have an effect, make the butons "unmanaged".
         * If they are managed, the Pane will set their sizes.
         **/
        usernameField.setManaged(false);
        usernameField.resize(125,23);
        resignButton.setManaged(false);
        resignButton.resize(100,30);
        newGameButton.setManaged(false);
        newGameButton.resize(100,30);
        saveSetsButton.setManaged(false);
        saveSetsButton.resize(120,30);
        saveStateOfGameButton.setManaged(false);
        saveStateOfGameButton.resize(160,30);

        /**
         * Create the Pane and give it a preferred size.  If the preferred size were not set,
         * the unmanaged buttons would not be included in the Pane's computed preferred size.
         **/
        Pane root = new Pane();

        root.setPrefWidth(1150);
        root.setPrefHeight(420);

        /** Add the child nodes to the Pane and set up the rest of the GUI **/
        root.getChildren().add(checkersBoard);
        root.getChildren().add(newGameButton);
        root.getChildren().add(resignButton);
        root.getChildren().add(saveSetsButton);
        root.getChildren().add(saveStateOfGameButton);
        root.getChildren().add(messageOfState);
        root.getChildren().add(messageOfRedMove);
        root.getChildren().add(messageOfBlackMove);
        root.getChildren().add(messageOfRedWins);
        root.getChildren().add(messageOfBlackWins);
        root.getChildren().add(labelToSet);
        root.getChildren().add(labelForUsername);
        root.getChildren().add(usernameField);
        root.getChildren().add(choiceBoxOfWonGamesInSet);
        root.getChildren().add(messageOfSets);

        root.setStyle("-fx-background-color: darkblue; " + "-fx-border-color: brown; -fx-border-width:3");
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.setTitle("Checkers");
        stage.show();
    }

    /** end start() */

    public static String getTextFromUsernameField() {

        return usernameField.getText();
    }

    public static Integer getMaxWonGamesInSet() {

        CheckersWonGames checkersWonGames = new CheckersWonGames();
        checkersWonGames.setDefaultIndex(defaultIndexOfListOfMaxWonGames);
        return checkersWonGames.getDefaultValue();
    }

    /**
     * Logical getters
     */
    public static boolean getDisableNewGameButton() {

        return newGameButton.isDisable();
    }

    public static boolean getDisableResignButton() {

        return resignButton.isDisable();
    }

    public static boolean getDisableSaveSetsButton() {

        return saveSetsButton.isDisable();
    }

    /**
     * Logical setters
     */
    /**
     * setDisableNewGameButton
     * @param isDissable
     */
    public static void setDisableNewGameButton(boolean isDissable) {

        newGameButton.setDisable(isDissable);
    }

    public static void setDisableResignButton(boolean isDissable) {

        resignButton.setDisable(isDissable);
    }

    public static void setDisableSaveSetsButton(boolean isDissable) {

        saveSetsButton.setDisable(isDissable);
    }

    /**
     * Text getters for message labels
     */
    public static String getTextInMessageOfState() {

        return messageOfState.getText();
    }

    public static String getTextInMessageOfRedMove() {

        return messageOfRedMove.getText();
    }

    public static String getTextInMessageOfBlackMove() {

        return messageOfBlackMove.getText();
    }

    public static String getTextInMessageOfRedWins() {

        return messageOfRedWins.getText();
    }
    public static String getTextInMessageOfBlackWins() {

        return messageOfBlackWins.getText();
    }

    public static String getTextInMessageOfSets() {

        return messageOfSets.getText();
    }

    /**
     * Text setters for message labels
     */

    public static void setTextInMessageOfState(String string) {

        messageOfState.setText(string);
    }

    public static void setTextInMessageOfRedMove(String string) {

        messageOfRedMove.setText(string);
    }

    public static void setTextInMessageOfBlackMove(String string) {

        messageOfBlackMove.setText(string);
    }

    public static void setTextInMessageOfRedWins(String string) {

        messageOfRedWins.setText(string);
    }
    public static void setTextInMessageOfBlackWins(String string) {

        messageOfBlackWins.setText(string);
    }

    public static void setTextInMessageOfSets(String string) {

        messageOfSets.setText(string);
    }

    /**
     * getters for message checkfields
     */
    public static String getUsernameFromTextField() {

        return usernameField.getText();
    }

//    public static int getChoiceBoxOfWonGamesInSet() {
//
//        return choiceBoxOfWonGamesInSet.getValue().intValue();
//    }

    /**
     * getters for message checkfields
     */
    public static void setUsernameFromTextField(String username) {

        usernameField.setText(username);
    }
}
