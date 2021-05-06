package com.kodilla.sudoku;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class SudokuBoard extends Prototype<SudokuBoard> {

    private static final int MAX_COLUMN = 9;
    private static final int MIN_COLUMN = 0;
    private static final int MAX_ROWS = 9;
    private static final int MIN_ROWS = 0;
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 9;

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_RESET = "\u001B[0m";

    private static SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd'at'HHmmss");
    private static String pathnameOfSudokuBacktrack = "kodilla-sudoku".concat(File.separator).concat("src").concat(File.separator).concat("main").concat(File.separator).concat("resources").concat(File.separator).concat("SudokuBacktrack");

    private SudokuRow[] board = new SudokuRow[MAX_ROWS + 1];

    public SudokuBoard() {
        for (int i = MIN_VALUE - 1; i < MAX_ROWS; i++) {
            board[i] = new SudokuRow();
        }
    }

    public SudokuRow[] getBoard() {
        return board;
    }

    public void setValueToCell(int row, int column, int value) throws Exception {
        if(!canSetValue(row, column, value)) {
            throw new IncorrectValueException("Incorrect value " + value + " in row " + row + " column " + column);
        }
        if (!checkInRow(row, value) && !checkInColumn(column, value) && !checkInSquare(row / 3, column / 3, value))
            board[column].getSudokuElements().get(row).setValue(value);
        else
            Menu.show(ANSI_RED + "Incorrect value. " + "Value \'" + value + "\' exists in row, in column or in square(3x3).\n" +
                            "This value cannot be set " + "to [" + (column + 1) + "," + (row + 1) + "]." +
                            "Try elsewhere or a different value.\n" + ANSI_RESET);
    }

    public int getValue(int row, int column) throws IncorrectValueException {
        if(!correctColumnAndRow(row, column)) {
            throw new IncorrectValueException("Number for row and column must be between 1 and 9.");
        }
        return board[column].getSudokuElements().get(row).getValue();
    }

//    public boolean canResolve() {
//        return true;
//    }

    private boolean checkInSquare(int squareRow, int squareColumn, int value) throws Exception {
        int startRow = squareRow * 3;
        int startColumn = squareColumn * 3;
        int range = 3;

        ArrayList<Integer> currentValues = new ArrayList<>();
        for (int r = startRow; r < startRow + range; r++) {
            for(int c = startColumn; c < startColumn + range; c++) {
                if (getValue(r, c) != -1)
                    currentValues.add(getValue(r, c));
            }
        }
        return currentValues.contains(value);
    }

    private boolean checkInRow(int row, Integer value) throws Exception {
        ArrayList<Integer> currentRowValues = new ArrayList<>();
        for(int theColumn = MIN_VALUE - 1; theColumn < MAX_VALUE; theColumn++) {
            if (board[theColumn].getSudokuElements().get(row).getValue() != -1)
                currentRowValues.add(board[theColumn].getSudokuElements().get(row).getValue());
        }
        return currentRowValues.contains(value);
    }

    private boolean checkInColumn(int column, int value) throws Exception {
        ArrayList<Integer> currentColumnValues = new ArrayList<>();
        for(int theRow = MIN_VALUE - 1; theRow < MAX_VALUE; theRow++) {
            if (board[column].getSudokuElements().get(theRow).getValue() != -1)
                currentColumnValues.add(board[column].getSudokuElements().get(theRow).getValue());
        }
        return currentColumnValues.contains(value);
    }

    private boolean canSetValue(int column, int row, int value) {
        boolean correctValue = value >= MIN_VALUE && value <= MAX_VALUE;
        return correctValue && correctColumnAndRow(column, row);
    }

    private boolean correctColumnAndRow(int column, int row) {
        boolean correctColumn = column >= MIN_COLUMN && column <= MAX_COLUMN;
        boolean correctRow = row >= MIN_ROWS && row <= MAX_ROWS;
        return correctColumn && correctRow;
    }

    @Override
    protected SudokuBoard clone() throws CloneNotSupportedException {
        SudokuBoard sudokuBoard = this.clone();
        sudokuBoard.board = new SudokuRow[MAX_ROWS + 1];
        for(int i = 0; i < MAX_ROWS; i++) {
            List<SudokuElement> elements = board[i].getSudokuElements();
            for (int j = 0; j < elements.size(); j++) {
                sudokuBoard.board[i].getSudokuElements().get(j).setValue(elements.get(j).getValue());
            }
        }
        return sudokuBoard;
    }

    @Override
    public String toString() {
        StringBuilder printBoard = new StringBuilder("    " +"1"+"   "+"2"+"   "+"3"+"   "+"4"+"   "+"5"+"   "+"6"+"   "+"7"+"   "+"8"+"   "+"9" + "\n");

        for(int i=0; i<MAX_ROWS; i++) {
            printBoard.append(1 + i).append(" |");
            for(int n=0; n<MAX_ROWS; n++) {
                if(getBoard()[i].getSudokuElements().get(n).getValue() == -1) {
                    printBoard.append(" - ");
                } else {
                    printBoard.append(" ");
                    printBoard.append(getBoard()[i].getSudokuElements().get(n).getValue());
                    printBoard.append(" ");
                }
                printBoard.append("|");
            }
            printBoard.append("\n");
        }
        return printBoard.toString();
    }

    public String toStringWithUnicode() {
        StringBuilder printBoard = new StringBuilder("    " +"1"+"   "+"2"+"   "+"3"+"   "+"4"+"   "+"5"+"   "+"6"+"   "+"7"+"   "+"8"+"   "+"9" + "\n");

        printBoard.append("  ").append("\u250C");
        for(int j = 0; j < MAX_COLUMN - 1; j++) {
            for (int i = 0; i < 3; i++) printBoard.append("\u2500");
            printBoard.append("\u252C");
        }
        for(int i = 0; i < 3; i++) printBoard.append("\u2500");
        printBoard.append("\u2510").append("\n");
        for(int n = 0; n < MAX_ROWS; n++) {
            ArrayList<SudokuElement> currentRowValues = new ArrayList<>();
            printBoard.append(n + 1).append(" ").append("\u2502");
            for(int i = 0; i < MAX_COLUMN; i++) {
                if(getBoard()[i].getSudokuElements().get(n).getValue() == -1) {
                    printBoard.append(" - ");
                } else {
                    currentRowValues.add(getBoard()[i].getSudokuElements().get(n));
                    printBoard.append(" ");
                    printBoard.append(getBoard()[i].getSudokuElements().get(n).getValue());
                    printBoard.append(" ");
                }
                printBoard.append("\u2502");
            }
            printBoard.append("\n");
            if (n < MAX_COLUMN -1) {
                printBoard.append("  ").append("\u251C");
                for(int j = 0; j < MAX_COLUMN - 1; j++) {
                    for (int i = 0; i < 3; i++) printBoard.append("\u2500");
                    printBoard.append("\u253C");
                }
                for(int i = 0; i < 3; i++) printBoard.append("\u2500");
                printBoard.append("\u2524");
            } else {
                printBoard.append("  ").append("\u2514");
                for(int j = 0; j < MAX_COLUMN - 1; j++) {
                    for (int i = 0; i < 3; i++) printBoard.append("\u2500");
                    printBoard.append("\u2534");
                }
                for(int i = 0; i < 3; i++) printBoard.append("\u2500");
                printBoard.append("\u2518");
            }
            printBoard.append("\n");
        }


        return printBoard.toString();
    }

    public void load() {
        String pathnameOfSudokuLoad = "kodilla-sudoku".concat(File.separator).concat("src").concat(File.separator).concat("main").concat(File.separator).concat("resources").concat(File.separator).concat("SudokuLoads").concat(File.separator).concat("2").concat(File.separator).concat("board.txt");
        File boardFile = new File(pathnameOfSudokuLoad);
        reloadStateOfBoard(boardFile);
    }

    private void reloadStateOfBoard(File boardFile) {
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(boardFile.getPath()));
            String line;
            while ((line = reader.readLine()) != null) {
                int indexOfFirstOpenBrace = line.indexOf("[");
                int indexOfFirstCloseBrace = line.indexOf("]");
                String rcvAsString = line.substring(indexOfFirstOpenBrace + 1, indexOfFirstCloseBrace);
                ArrayList<Integer> rcvAsList = new ArrayList<>();
                for (String rcvItem: rcvAsString.split(",")) {
                    rcvAsList.add(Integer.valueOf(rcvItem));
                }
                int column = rcvAsList.get(0);
                int row = rcvAsList.get(1);
                int value = rcvAsList.get(2);
                if (value != -1)
                    setValueToCell(row, column, value);
                String lineWithoutFirstBraces = line.substring(indexOfFirstCloseBrace + 1);
                int indexOfSecondOpenBrace = lineWithoutFirstBraces.indexOf("[");
                int indexOfSecondCloseBrace = lineWithoutFirstBraces.indexOf("]");
                String possibleValuesAsString = lineWithoutFirstBraces.substring(indexOfSecondOpenBrace + 1, indexOfSecondCloseBrace);
                ArrayList<Integer> possibleValuesAsList = new ArrayList<>();
                for (String possibleValueItem: possibleValuesAsString.split(",")) {
                    possibleValuesAsList.add(Integer.valueOf(possibleValueItem.trim()));
                }
                ArrayList<Integer> possibleValues = possibleValuesAsList;
            }
            reader.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void save() throws Exception, IOException {

        long currentTimeMillis = System.currentTimeMillis();
        Date currentTime = new Date(currentTimeMillis);
        String basenameFromDate = formatter.format(currentTime);
        File directoryOfStateOfGame = new File(pathnameOfSudokuBacktrack.concat(File.separator).concat(basenameFromDate));
        if(!directoryOfStateOfGame.exists())
            directoryOfStateOfGame.mkdirs();
        String absolutePath = directoryOfStateOfGame.getAbsolutePath();
        File fileToWriteGame = new File(absolutePath, "board".concat(".").concat("txt"));

        BufferedWriter outputWriter = null;
        try {
            outputWriter = new BufferedWriter(new FileWriter(fileToWriteGame));
            SudokuRow[] rowsOfBoard = board.clone();
            for(int theRow = MIN_VALUE - 1; theRow < MAX_VALUE; theRow++) {
                for (int theColumn = MIN_VALUE - 1; theColumn < MAX_VALUE; theColumn++) {
                    if (rowsOfBoard[theColumn].getSudokuElements().get(theRow).getValue() != -1){
                        outputWriter.write("SudokuElement["+theColumn +"," + theRow+","+rowsOfBoard[theColumn].getSudokuElements().get(theRow).getValue() + "], PossibleValues=["+rowsOfBoard[theColumn].getSudokuElements().get(theRow).getValue()+"]");
                        outputWriter.newLine();
                    } else {
                        outputWriter.write("SudokuElement["+theColumn +"," + theRow+","+rowsOfBoard[theColumn].getSudokuElements().get(theRow).getValue() + "], PossibleValues="+getPossibleValues(theRow,theColumn));
                        outputWriter.newLine();
                    }
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

    private ArrayList<Integer> getPossibleValues(int row, int column) throws Exception {
        ArrayList<Integer> possibleValues = new ArrayList<>();
        ArrayList<Integer> possibleRowValues = getPossibleRowValues(row);
        for (Integer possibleRowValue: possibleRowValues) {
            Integer thePossibleRowValue = possibleRowValue.intValue();
            boolean thePossibleRowValueInColumn = checkInColumn(column, possibleRowValue);
            boolean thePossibleRowValueInSquare = checkInSquare(row / 3, column / 3, thePossibleRowValue);
            if (!thePossibleRowValueInColumn &&  !thePossibleRowValueInSquare)
                possibleValues.add(thePossibleRowValue);
        }
        return possibleValues;
    }

    private ArrayList<Integer> getPossibleRowValues(int row) {
        ArrayList<Integer> currentRowValues = new ArrayList<>();
        for(int i = MIN_VALUE - 1; i < MAX_VALUE; i++) {
            if (board[i].getSudokuElements().get(row).getValue() != -1)
                currentRowValues.add(board[i].getSudokuElements().get(row).getValue());
        }
        return SudokuRow.getPossibleRowValues(currentRowValues);
    }

    private boolean checkInColumn(int column, Integer value) throws Exception {
        ArrayList<Integer> currentColumnValues = new ArrayList<>();
        for(int theRow = MIN_VALUE - 1; theRow < MAX_VALUE; theRow++) {
            if (board[column].getSudokuElements().get(theRow).getValue() != -1)
                currentColumnValues.add(board[column].getSudokuElements().get(theRow).getValue());
        }
        return currentColumnValues.contains(value);
    }
}
