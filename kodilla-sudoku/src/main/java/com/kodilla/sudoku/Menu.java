package com.kodilla.sudoku;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private static final Scanner scanner = new Scanner(System.in);

    private static String menuText() {
        return "SUDOKU\n" +
                "Type 3 numbers: rcv, where:\n" +
                "r is row, c is column, v is value, example: 123\n" +
                "or multiple rcv values with one string, example: 123456789\n\n" +
                "Other options:\n" +
                "sudoku - try to fill Sudoku\n" +
                "load - load example of a board\n" +
                "save - save current board\n" +
                "n - will start a new game\n" +
                "x - will exit the game";
    }

    public static ArrayList<UserChoice> getUserChoice() {
        System.out.println(menuText());
        System.out.println();

        String input = scanner.next();
        boolean isDigits = input.chars().allMatch(Character::isDigit);
        boolean digitsCorrectLength = input.length() % 3 == 0 || input.length() > 3;
        ArrayList<UserChoice> userChoices = new ArrayList<>();

        switch (input.toLowerCase()) {
            case "sudoku":
                userChoices.add(new UserChoice(UserChoiceType.RESOLVE));
                return userChoices;
            case "load":
                userChoices.add(new UserChoice(UserChoiceType.LOAD));
                return userChoices;
            case "save":
                userChoices.add(new UserChoice(UserChoiceType.SAVE));
                return userChoices;
            case "n" :
                userChoices.add(startNewGame());
                return userChoices;
            case "x" :
                userChoices.add(exitGame());
                return userChoices;
            default:
                if(isDigits && digitsCorrectLength) {
                    char[] inputTab = input.toCharArray();
                    if (inputTab.length >= 3 && inputTab.length < 6) {
                        int column = Character.getNumericValue(inputTab[0]);
                        int row = Character.getNumericValue(inputTab[1]);
                        int value = Character.getNumericValue(inputTab[2]);
                        userChoices.add(new UserChoice(row, column, value));
                        return userChoices;
                    } else {
                        int numberOfThrees = inputTab.length / 3;
                        for (int i = 0; i < numberOfThrees; i++) {
                            int column = Character.getNumericValue(inputTab[i*3]);
                            int row = Character.getNumericValue(inputTab[i*3 + 1]);
                            int value = Character.getNumericValue(inputTab[i*3 + 2]);
                            userChoices.add(new UserChoice(row, column, value));
                        }
                    }
                } else {
                    userChoices.add(new UserChoice(UserChoiceType.NONE));
                    return userChoices;
                }
        }
        return userChoices;
    }

    private static UserChoice startNewGame() {
        boolean correctInput = false;
        do {
            System.out.println("Do you want to start a new game? y/n");
            String input = scanner.next();
            if(input.equalsIgnoreCase("y")) {
                correctInput = true;
                return new UserChoice(UserChoiceType.NEW_GAME);
            }
            if(input.equalsIgnoreCase("n")) {
                correctInput = true;
                return new UserChoice(UserChoiceType.NONE);
            }
        } while (!correctInput);
        return new UserChoice(UserChoiceType.NONE);
    }

    public static UserChoice gameAgain() {
        boolean correctInput = false;
        do {
            System.out.println("Do you want to play again? y/n");
            String input = scanner.next();
            if(input.equalsIgnoreCase("y")) {
                correctInput = true;
                return new UserChoice(UserChoiceType.NEW_GAME);
            }
            if(input.equalsIgnoreCase("n")) {
                correctInput = true;
                return new UserChoice(UserChoiceType.EXIT);
            }
        } while (!correctInput);
        return new UserChoice(UserChoiceType.NONE);
    }

    private static UserChoice exitGame() {
        boolean correctInput = false;
        do {
            System.out.println("Do you want exit? y/n");
            String input = scanner.next();
            if(input.equalsIgnoreCase("y")) {
                correctInput = true;
                return new UserChoice(UserChoiceType.EXIT);
            }
            if(input.equalsIgnoreCase("n")) {
                correctInput = true;
                return new UserChoice(UserChoiceType.NONE);
            }
        } while (!correctInput);
        return new UserChoice(UserChoiceType.NONE);
    }

    public static void printIncorrectValueToSet(UserChoice choice) {
        show("Incorrect value: " + choice);
    }

    public static void show(String text) {
        System.out.println(text);
    }
}
