package com.kodilla.game.view;

import com.kodilla.game.logic.Checkers;
import com.kodilla.game.logic.CheckersBoard;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.kodilla.game.logic.CheckersBoard.*;

public class CheckersStateOfGame {

    private static String pathnameOfCheckersStateOfGame = "resources".concat(File.separator).concat("CheckersStateOfGame");

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd'at'HHmmss");

    private static int theCurrentPlayer;

//    CheckersData checkersData;     /** The data for the checkers board is kept here. This board is also responsible for
//     * generating lists of legal moves. */

    private static int[][] theCurrentBoard;
    /** TODO. Save to file and Load from them */
    private static List<CheckersMove> thePossibleJamps;

    public CheckersStateOfGame() { }

    public static void setCurrentUser(int currentPlayer) {

        theCurrentPlayer = currentPlayer;
    }

    public static int getCurrentPlayer() {
        return theCurrentPlayer;
    }

    public static List<CheckersMove> getPossibleJumps() {
        return thePossibleJamps;
    }

    public String getPathname() {

        return pathnameOfCheckersStateOfGame;
    }

    public void reloadGame() {
        File[] directories = listFiles(pathnameOfCheckersStateOfGame);
        File lastDirectory = directories[directories.length - 1];
        File[] filesInLastDirectory = listFiles(lastDirectory.getPath());
        List<File> boardFiles = Arrays.stream(filesInLastDirectory).filter(f -> f.getName().contains("board.txt")).collect(Collectors.toList());
        File boardFile = boardFiles.get(boardFiles.size() - 1);

        List<File> possibleJumpFiles = Arrays.stream(filesInLastDirectory).filter(f -> f.getName().contains("possiblejumps.txt")).collect(Collectors.toList());
        File possibleJumpFile = possibleJumpFiles.get(possibleJumpFiles.size() - 1);

        List<File> messageFiles = Arrays.stream(filesInLastDirectory).filter(f -> f.getName().contains("messages.txt")).collect(Collectors.toList());
        File messageFile = messageFiles.get(messageFiles.size() - 1);
        List<File> buttonsFiles = Arrays.stream(filesInLastDirectory).filter(f -> f.getName().contains("buttons.txt")).collect(Collectors.toList());
        File buttonsFile = buttonsFiles.get(buttonsFiles.size() - 1);
        List<File> checkFieldsFiles = Arrays.stream(filesInLastDirectory).filter(f -> f.getName().contains("checkfields.txt")).collect(Collectors.toList());
        File checkFieldsFile = checkFieldsFiles.get(checkFieldsFiles.size() - 1);

        CheckersData.setBoard(reloadStateOfBoard(boardFile));

        List<CheckersMove> listOfPossibleJumps = reloadPossibleJumps(possibleJumpFile);
        setCurrentPossibleJumps(listOfPossibleJumps);

        HashMap<String, String> textsOfMessage = reloadStateOfMessages(messageFile);
        String valueOfTextInMessageOfState = textsOfMessage.get("textInMessageOfState");
        String valueOfTextInMessageOfRedMove = textsOfMessage.get("textInMessageOfRedMove");
        String valueOfTextInMessageOfBlackMove = textsOfMessage.get("textInMessageOfBlackMove");
        String valueOfTextInMessageOfRedWins = textsOfMessage.get("textInMessageOfRedWins");
        String valueOfTextInMessageOfBlackWins = textsOfMessage.get("textInMessageOfBlackWins");
        String valueOfTextInMessageOfSets = textsOfMessage.get("textInMessageOfSets");
        Checkers.setTextInMessageOfState(valueOfTextInMessageOfState);
        Checkers.setTextInMessageOfRedMove(valueOfTextInMessageOfRedMove);
        Checkers.setTextInMessageOfBlackMove(valueOfTextInMessageOfBlackMove);
        Checkers.setTextInMessageOfRedWins(valueOfTextInMessageOfRedWins);
        Checkers.setTextInMessageOfBlackWins(valueOfTextInMessageOfBlackWins);
        Checkers.setTextInMessageOfSets(valueOfTextInMessageOfSets);

        HashMap<String, Boolean> stateOfDisableButtons = reloadStateOfDisableButtons(buttonsFile);
        boolean valueOfIsDisableNewGameButton = stateOfDisableButtons.get("isDisableNewGameButton");
        boolean valueOfIsDisableResignButton = stateOfDisableButtons.get("isDisableResignButton");
        boolean valueOfIsDisableSaveSetsButton = stateOfDisableButtons.get("isDisableSaveSetsButton");
        Checkers.setDisableNewGameButton(valueOfIsDisableNewGameButton);
        Checkers.setDisableResignButton(valueOfIsDisableResignButton);
        Checkers.setDisableSaveSetsButton(valueOfIsDisableSaveSetsButton);

        HashMap<String, Object> stateOfCheckFields = reloadStateOfCheckFields(checkFieldsFile);
        Checkers.setUsernameFromTextField((String) stateOfCheckFields.get("usernameFromTextField"));
        Integer valueOfChoiceBoxOfWonGamesInSet = (Integer) stateOfCheckFields.get("choiceBoxOfWonGamesInSet");
        CheckersWonGames checkersWonGames = new CheckersWonGames();
        checkersWonGames.setDefaultValue(valueOfChoiceBoxOfWonGamesInSet);
    }

    private int[][] reloadStateOfBoard(File boardFile) {
        int dimOfResult = 8;
        int[][] result = new int[dimOfResult][dimOfResult];
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(boardFile.getPath()));
            String line;
            while ((line = reader.readLine()) != null) {
                int indexOfFirstOpenBrace = line.indexOf("[");
                int indexOfFirstCloseBrace = line.indexOf("]");
                String rowAsString = line.substring(indexOfFirstOpenBrace + 1, indexOfFirstCloseBrace);
                Integer rowAsInteger = Integer.valueOf(rowAsString.trim());
                String lineWithoutFirstBraces = line.substring(indexOfFirstCloseBrace + 1);
                int indexOfSecondOpenBrace = lineWithoutFirstBraces.indexOf("[");
                int indexOfSecondCloseBrace = lineWithoutFirstBraces.indexOf("]");
                String ColumnAsString = lineWithoutFirstBraces.substring(indexOfSecondOpenBrace + 1, indexOfSecondCloseBrace);
                Integer columnAsInteger = Integer.valueOf(ColumnAsString.trim());
                String lineWithoutSecondBraces = lineWithoutFirstBraces.substring(indexOfSecondCloseBrace + 1);
                int indexOfEqualsSign = lineWithoutSecondBraces.indexOf("=");
                String lineAfterEqualsSign = lineWithoutSecondBraces.substring(indexOfEqualsSign + 1);
                Integer valueAfterEqualsSign = Integer.valueOf(lineAfterEqualsSign.trim());
                result[rowAsInteger][columnAsInteger] = valueAfterEqualsSign;
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private List<CheckersMove> reloadPossibleJumps(File possibleJumpFile) {
        List<CheckersMove> possibleJumps = new ArrayList<>();
        ArrayList<String> isJumpInStrings = new ArrayList<>();
        ArrayList<String> preResult = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(possibleJumpFile.getPath()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(":")) {
                    int indexOfColon = line.indexOf(":");
                    isJumpInStrings.add(line.substring(0, indexOfColon));
                }
                preResult.add(line);
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String theContain = preResult.stream().collect(Collectors.joining("\n"));
        for (String playerIsJumpInStr: isJumpInStrings) {
            Integer numberOfPlayer = getNumberInBracesFromText(playerIsJumpInStr);
            int indexOfIsJumpKey = theContain.indexOf(playerIsJumpInStr) + playerIsJumpInStr.length();
            int indexOfSemicolon = theContain.indexOf(";");
            String theContainBetweenColons = theContain.substring(indexOfIsJumpKey + 2, indexOfSemicolon + 1);

            ArrayList<Integer> numbersContainBetweenColons = new ArrayList<>();
            while(theContainBetweenColons.contains("[") && theContainBetweenColons.contains("]")) {
                Integer numberAsInteger = getNumberInBracesFromText(theContainBetweenColons);
                numbersContainBetweenColons.add(numberAsInteger);
                theContainBetweenColons = theContainBetweenColons.substring(theContainBetweenColons.indexOf("]") + 1);
            }
            Integer fromRow = numbersContainBetweenColons.get(0) - 1;
            Integer fromColumn = numbersContainBetweenColons.get(1) - 1;
            Integer toRow = numbersContainBetweenColons.get(2) - 1;
            Integer toColumn = numbersContainBetweenColons.get(3) - 1;
            possibleJumps.add(new CheckersMove(numberOfPlayer, fromRow, fromColumn, toRow, toColumn));
            theContain = theContain.substring(indexOfSemicolon + 2);
        }
        return possibleJumps;
    }

    private Integer getNumberInBracesFromText(String theText) {
        if (theText.contains("[") && theText.contains("]")) {
            int indexOfOpenBrace = theText.indexOf("[");
            int indexOfCloseBrace = theText.indexOf("]");
            String numberAsString = theText.substring(indexOfOpenBrace + 1, indexOfCloseBrace);
            return Integer.valueOf(numberAsString.trim());
        }
        return -1;
    }

    private HashMap<String, String> reloadStateOfMessages(File messageFile) {
        HashMap<String, String> stateOfMessages = new HashMap<>();
        ArrayList<String> keys = new ArrayList<>();
        ArrayList<String> preResult = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(messageFile.getPath()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(": ")) {
                    int indexOfColon = line.indexOf(": ");
                    keys.add(line.substring(0, indexOfColon));
                }
                preResult.add(line);
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        String theContain = preResult.stream().collect(Collectors.joining("\n"));
        for (String key: keys) {
            int indexOfKey = theContain.indexOf(key) + key.length();
            String theContainAfterColon = theContain.substring(indexOfKey + 2);
            String theContainAfterColonWithoutQuote = theContain.substring(indexOfKey + 3);
            int indexOfFirstQuote = theContainAfterColon.indexOf("\"");
            int indexOfSecondQuote = theContainAfterColonWithoutQuote.indexOf("\"") + 2;
            String theContainBetweenQuotes = theContainAfterColon.substring(indexOfFirstQuote + 1, indexOfSecondQuote - 1);
            stateOfMessages.put(key, theContainBetweenQuotes);
        }
        return stateOfMessages;
    }

    private HashMap<String, Boolean> reloadStateOfDisableButtons(File buttonsFile) {
        HashMap<String, Boolean> stateOfDisableButtons = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(buttonsFile.getPath()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(": ")) {
                    int indexOfColon = line.indexOf(": ");
                    String key = line.substring(0, indexOfColon);
                    String valueAsString = line.substring(indexOfColon + 2).trim();
                    Boolean valueAsBoolean = Boolean.valueOf(valueAsString);
                    stateOfDisableButtons.put(key, valueAsBoolean);
                }
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return stateOfDisableButtons;
    }

    private HashMap<String, Object> reloadStateOfCheckFields(File checkFieldsFile) {
        HashMap<String, Object> stateOfCheckFields = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(checkFieldsFile.getPath()));
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(": ")) {
                    int indexOfColon = line.indexOf(": ");
                    String key = line.substring(0, indexOfColon);
                    String valueAsString = line.substring(indexOfColon + 2).trim();
                    if (valueAsString.contains("\""))
                        stateOfCheckFields.put(key, valueAsString.substring(1, valueAsString.length() - 1));
                    else if (!key.contains("Text") && isInteger(valueAsString)) {
                        stateOfCheckFields.put(key, Integer.parseInt(valueAsString));
                    } else
                        stateOfCheckFields.put(key, valueAsString);
                }
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return stateOfCheckFields;
    }

    private boolean isInteger(String valueAsString) {
        try {
            Integer.parseInt(valueAsString);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        return true;
    }

    public int lengthOfListFiles(String pathnameToStateOfGame) {
        return Arrays.stream(new File(pathnameToStateOfGame).listFiles())
                .filter(d -> d.isDirectory())
                .collect(Collectors.toList())
                .size();
    }

    File[] listFiles(String pathToStateOfGame) {
        return new File(pathToStateOfGame).listFiles();
    }

    public void removeDirectory(String pathname) {
        String pathnameOfCheckersStateOfGame = getPathname();
        for (File directoryOfStateOfGame:listFiles(pathnameOfCheckersStateOfGame)) {
            if (directoryOfStateOfGame.isDirectory()) {
                for (File fileOfStateOfGame:directoryOfStateOfGame.listFiles()) {
                    fileOfStateOfGame.delete();
                }
            }
            if (directoryOfStateOfGame.listFiles().length == 0) {
                directoryOfStateOfGame.delete();
            }
        }
    }


    public static void setCurrentBoard(int[][] currentBoard) {

        theCurrentBoard = currentBoard;
    }

    /** TODO. */
    public static void setCurrentPossibleJumps(List<CheckersMove> possibleJumps) {
        thePossibleJamps = possibleJumps;
//        CheckersBoard.setPossibleJumps(possibleJumps);
    }

    private static void showBoard() {
        System.out.println("CheckersStateOfGames::setCurrentBoard(). " + "theBoard = " + theCurrentBoard);
        for (int i = 0; i < getDimOfBoard(); i++) {
            System.out.println("CheckersData::showBoard(). " + "row["+i+"]");
            for (int j = 0; j < getDimOfBoard(); j++) {
                System.out.print("col["+j+"] = " + theCurrentBoard[i][j] + ", ");
            }
            System.out.println();
        }
    }

    public void saveStateOfBoard(long currentTimeMillis) throws IOException {
        Date currentTime = new Date(currentTimeMillis);
        String basenameFromDate = formatter.format(currentTime);
        File directoryOfStateOfGame = new File(pathnameOfCheckersStateOfGame.concat(File.separator).concat(basenameFromDate));
        if(!directoryOfStateOfGame.exists())
            directoryOfStateOfGame.mkdirs();
        String absolutePath = directoryOfStateOfGame.getAbsolutePath();
        File fileToWriteGame = new File(absolutePath, "board".concat(".").concat("txt"));

//        showBoard();

        BufferedWriter outputWriter = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter(fileToWriteGame));
            for (int i = 0; i < theCurrentBoard.length; i++) {
                for (int j = 0; j < theCurrentBoard.length; j++) {
                    outputWriter.write("board[" + i + "][" + j + "] = " + theCurrentBoard[i][j]);
                    outputWriter.newLine();
                }
            }
            outputWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                outputWriter.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void savePossibleJumps(long currentTimeMillis) {
        Date currentTime = new Date(currentTimeMillis);
        String basenameFromDate = formatter.format(currentTime);
        File directoryOfStateOfGame = new File(pathnameOfCheckersStateOfGame.concat(File.separator).concat(basenameFromDate));
        if(!directoryOfStateOfGame.exists())
            directoryOfStateOfGame.mkdirs();
        String absolutePath = directoryOfStateOfGame.getAbsolutePath();
        File fileToWriteGame = new File(absolutePath, "possiblejumps".concat(".").concat("txt"));

        BufferedWriter outputWriter = null;
        /** DOING. Temporary comment. */
        try {
            outputWriter = new BufferedWriter(new FileWriter(fileToWriteGame));
            for (CheckersMove thePossibleJamp: thePossibleJamps) {
                outputWriter.write(String.format("player[%d]",theCurrentPlayer).concat(" ").concat(thePossibleJamp.toString()));
                outputWriter.newLine();
            }

            outputWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                outputWriter.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveStateOfMessages(long currentTimeMillis) {
        Date currentTime = new Date(currentTimeMillis);
        String basenameFromDate = formatter.format(currentTime);
        File directoryOfStateOfGame = new File(pathnameOfCheckersStateOfGame.concat(File.separator).concat(basenameFromDate));
        if(!directoryOfStateOfGame.exists())
            directoryOfStateOfGame.mkdirs();
        String absolutePath = directoryOfStateOfGame.getAbsolutePath();
        File fileToWriteGame = new File(absolutePath, "messages".concat(".").concat("txt"));
        BufferedWriter outputWriter = null;
        String textInMessageOfState = Checkers.getTextInMessageOfState();
        String textInMessageOfRedMove = Checkers.getTextInMessageOfRedMove();
        String textInMessageOfBlackMove = Checkers.getTextInMessageOfBlackMove();
        String textInMessageOfRedWins = Checkers.getTextInMessageOfRedWins();
        String textInMessageOfBlackWins = Checkers.getTextInMessageOfBlackWins();
        String textInMessageOfSets = Checkers.getTextInMessageOfSets();
        try {
            outputWriter = new BufferedWriter(new FileWriter(fileToWriteGame));
            outputWriter.write("textInMessageOfState: " + "\"".concat(textInMessageOfState).concat("\""));
            outputWriter.newLine();
            outputWriter.write("textInMessageOfRedMove: " + "\"".concat(textInMessageOfRedMove).concat("\""));
            outputWriter.newLine();
            outputWriter.write("textInMessageOfBlackMove: " + "\"".concat(textInMessageOfBlackMove).concat("\""));
            outputWriter.newLine();
            outputWriter.write("textInMessageOfRedWins: " + "\"".concat(textInMessageOfRedWins).concat("\""));
            outputWriter.newLine();
            outputWriter.write("textInMessageOfBlackWins: " + "\"".concat(textInMessageOfBlackWins).concat("\""));
            outputWriter.newLine();
            outputWriter.write("textInMessageOfSets: " + "\"".concat(textInMessageOfSets).concat("\""));

            outputWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                outputWriter.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveStateOfDisableButtons(long currentTimeMillis) {
        Date currentTime = new Date(currentTimeMillis);
        String basenameFromDate = formatter.format(currentTime);
        File directoryOfStateOfGame = new File(pathnameOfCheckersStateOfGame.concat(File.separator).concat(basenameFromDate));
        if(!directoryOfStateOfGame.exists())
            directoryOfStateOfGame.mkdirs();
        String absolutePath = directoryOfStateOfGame.getAbsolutePath();
        File fileToWriteGame = new File(absolutePath, "buttons".concat(".").concat("txt"));
        BufferedWriter outputWriter = null;
        boolean isDisableNewGameButton = Checkers.getDisableNewGameButton();
        boolean isDisableResignButton = Checkers.getDisableResignButton();
        boolean isDisableSaveSetsButton = Checkers.getDisableSaveSetsButton();
        try {
            outputWriter = new BufferedWriter(new FileWriter(fileToWriteGame));
            outputWriter.write("isDisableNewGameButton: " + isDisableNewGameButton);
            outputWriter.newLine();
            outputWriter.write("isDisableResignButton: " + isDisableResignButton);
            outputWriter.newLine();
            outputWriter.write("isDisableSaveSetsButton: " + isDisableSaveSetsButton);

            outputWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                outputWriter.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void saveStateOfCheckFields(long currentTimeMillis) {
        Date currentTime = new Date(currentTimeMillis);
        String basenameFromDate = formatter.format(currentTime);
        File directoryOfStateOfGame = new File(pathnameOfCheckersStateOfGame.concat(File.separator).concat(basenameFromDate));
        if(!directoryOfStateOfGame.exists())
            directoryOfStateOfGame.mkdirs();
        String absolutePath = directoryOfStateOfGame.getAbsolutePath();
        File fileToWriteGame = new File(absolutePath, "checkfields".concat(".").concat("txt"));
        BufferedWriter outputWriter = null;
        String usernameFromTextField = Checkers.getUsernameFromTextField();
        try {
            outputWriter = new BufferedWriter(new FileWriter(fileToWriteGame));
            outputWriter.write("usernameFromTextField: " + "\"".concat(usernameFromTextField).concat("\""));
            outputWriter.newLine();

            outputWriter.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                outputWriter.close();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static SimpleDateFormat getFormatter() {

        return formatter;
    }
}
