package com.kodilla.rps;

import java.util.*;

public final class RpsPlaying {
    private static int minNumberOfRoundsWon = 3;
    private static String computerize = "@computer";
    private final List<RpsPlayer> playerList = new ArrayList<>();
    private final List<RpsStuff> stuffList = new ArrayList<>();
    private final List<RpsRelation> relationList = new ArrayList<>();
    private final List<RpsResult> resultList = new ArrayList<>();
    public RpsPlaying() { }

    public List<RpsPlayer> getPlayerList() {
        return this.playerList;
    }

    public void addPlayerToList(RpsPlayer playerList) {
        this.playerList.add(playerList);
    }

    public boolean removePlayerFromList(RpsPlayer playerList) {
        return this.playerList.remove(playerList);
    }

    public List<RpsStuff> getStuffList() {
        return this.stuffList;
    }

    public void addStuffToList(RpsStuff rpsStuff) {
        this.stuffList.add(rpsStuff);
    }

    public String stuffListToStr() {
        String strOfStuff = "[";
        for (RpsStuff stuff:
                stuffList) {
            strOfStuff += stuff.getPositionOfStuff();
            strOfStuff += " - ";
            strOfStuff += "\"".concat(stuff.getNameOfStuff()).concat("\"");
            strOfStuff += ", ";
        }
        strOfStuff += "]";
        return strOfStuff;
    }

    public String getNameOfStuff(int positionOfStuff) {
        for (RpsStuff rpsStuff: getStuffList()) {
            if (rpsStuff.getPositionOfStuff() == positionOfStuff)
                return rpsStuff.getNameOfStuff();
        }
        return "";
    }

    public boolean removeStuffFromList(RpsStuff rpsStuff) {
        return this.stuffList.remove(rpsStuff);
    }

    public List<RpsRelation> getRelationList() {
        return this.relationList;
    }

    public void addRelationToList(RpsRelation rpsRelation) {
        this.relationList.add(rpsRelation);
    }

    public boolean removeRelationFromList(RpsRelation rpsRelation) {
        return this.relationList.remove(rpsRelation);
    }

    public void addResultToList(RpsResult rpsResult) {
        this.resultList.add(rpsResult);
    }

    public boolean removeResultFromList(RpsResult rpsResult) {
        return this.resultList.remove(rpsResult);
    }

    public int getWinnerFromRelations(int firstStuff, int secondStuff) {
        for (RpsRelation rpsRelation: relationList) {
            if (rpsRelation.getWinnerOfStuff(firstStuff,secondStuff) > 0 || rpsRelation.getWinnerOfStuff(secondStuff,firstStuff) > 0) {
                if (rpsRelation.getWinnerOfStuff(firstStuff,secondStuff) > 0)
                    return rpsRelation.getWinnerOfStuff(firstStuff,secondStuff);
                else if (rpsRelation.getWinnerOfStuff(secondStuff,firstStuff) > 0)
                    return rpsRelation.getWinnerOfStuff(secondStuff,firstStuff);
            }
        }
        return 0;
    }
    public void start() {
        Scanner scanner = new Scanner(System.in);
        if (playerList.size() < 2) {
            while (playerList.size() < 2) {
                RpsPlayer rpsPlayer = promptAddNewUser();
                playerList.add(rpsPlayer);
            }
        }
        List<Integer> numberOfRoundsWonList = new ArrayList<>(Arrays.asList(0,0));
        for (RpsPlayer player: playerList) {
            if (player.getNumberOfRoundsWon() < minNumberOfRoundsWon) {
                int randomNumberOfRoundsWon = minNumberOfRoundsWon + new Random().nextInt(21 - minNumberOfRoundsWon);
                numberOfRoundsWonList.set(playerList.indexOf(player), randomNumberOfRoundsWon);
            } else {
                numberOfRoundsWonList.set(playerList.indexOf(player), player.getNumberOfRoundsWon());
            }
        }
        System.out.print(playerList.get(0).getUsername().concat("(") + numberOfRoundsWonList.get(0) + " round to win)" + " vs ");
        System.out.println(playerList.get(1).getUsername().concat("(") + numberOfRoundsWonList.get(1) + " round to win)");
        int userScanNumber;
        char userGuessAsChar;
        boolean isSuccess = false;
        List<Integer> points = new ArrayList<>(Arrays.asList(0,0));
        System.out.println("START.");
        do {
            List<Integer> choices = new ArrayList<>(Arrays.asList(0, 0));
            for (RpsPlayer rpsPlayer : playerList) {
                if (rpsPlayer.getUsername().equals(computerize)) {
                    userScanNumber = 1 + new Random().nextInt( getStuffList().size() - 1);
                    // comment. Uncomment for tests.
//                    System.out.println("Gracz \"" + rpsPlayer.getUsername() + "\" wybrał " + userScanNumber);
                } else {
                    System.out.println("Player: " + rpsPlayer.getUsername());
                    System.out.print("Wybierz ");
                    System.out.print(stuffListToStr());
                    System.out.print("\nalbo " + "\'n\' uruchomienie na nowo gry, \'x\' zakończenie gry: ");
                    userGuessAsChar = scanner.next().charAt(0);
                    if (userGuessAsChar == 'x') {
                        if (promptBeforeEnd("Czy napewno zakończyć grę?")) {
                            System.out.println("GAME OVER");
                            System.exit(0);
                        }
                        else
                            continue;
                    }
                    if (userGuessAsChar == 'n') {
                        if (promptBeforeEnd("Czy napewno zakończyć aktualną grę?")) {
                            System.out.println("RELOAD GAME");
                            points = new ArrayList<>(Arrays.asList(0, 0));
                        }
                        continue;
                    }
                    if (Character.isDigit(userGuessAsChar)) {
                        userScanNumber = Character.getNumericValue(userGuessAsChar);
                        if (userScanNumber < 1 || userScanNumber > stuffList.size())
                            continue;
                    } else
                        continue;
                }
                int indexOfPlayer = playerList.indexOf(rpsPlayer);
                choices.set(indexOfPlayer, userScanNumber);
            }
            if (choices.get(0).equals(choices.get(1))) {
                System.out.println("\nOboje gracze pomyśleli o \"" + getNameOfStuff(choices.get(0)) + "\"(" + choices.get(0) + ")\n");
            } else if (!choices.get(0).equals(choices.get(1))) {
                System.out.println("\nWybór gracza \"" + playerList.get(0).getUsername() + "\" : \"" + getNameOfStuff(choices.get(0)) + "\"(" + choices.get(0) + ")" + ", wybór gracza \"" + playerList.get(1).getUsername() + "\": \"" + getNameOfStuff(choices.get(1)) + "\"(" + choices.get(1) + ")");

                int winnerFromRelations = getWinnerFromRelations(choices.get(0), choices.get(1));
                int indexOfChoiceWon = choices.indexOf(winnerFromRelations);
                System.out.print(getNameOfStuff(choices.get(0)) + "\"(" + choices.get(0) + ")");
                if (indexOfChoiceWon == 0)
                    System.out.print(" beat ");
                else if (indexOfChoiceWon == 1)
                    System.out.print(" lost ");
                System.out.println(getNameOfStuff(choices.get(1)) + "\"(" + choices.get(1) + ")");

                if (indexOfChoiceWon >= 0 && indexOfChoiceWon < points.size()) {
                    int pointOfPlayerWon = points.get(indexOfChoiceWon);
                    points.set(indexOfChoiceWon , pointOfPlayerWon + 1);
                }
                System.out.println("\nAktualny wynik: " + points.get(0) + " : " + points.get(1) + "\n");
                if (points.get(0) >= numberOfRoundsWonList.get(0)) {
                    System.out.println("\nSuccess! Gracz \"" + playerList.get(0).getUsername() + "\" win! (won rounds " + points.get(0) + " of " + numberOfRoundsWonList.get(0) +")\n");
                    isSuccess = true;
                } else if (points.get(1) >= numberOfRoundsWonList.get(1)) {
                    System.out.println("\nSuccess! Gracz \"" + playerList.get(1).getUsername() + "\" win! (won rounds " + points.get(1) + " of " + numberOfRoundsWonList.get(1) +")\n");
                    isSuccess = true;
                }
                if (isSuccess) {
                    addResultToList(new RpsResult(points.get(0), points.get(1)));
                    System.out.println("\nresultList: " + resultList + "\n");
                    if (promptBeforeEnd("Czy rozpocząć nową grę?")) {
                        System.out.println("RELOAD GAME");
                        points = new ArrayList<>(Arrays.asList(0, 0));
                        isSuccess = false;
                        continue;
                    }
                }
            }
        } while (!(isSuccess));

    }
    private boolean promptBeforeEnd(String question) {
        Scanner scanner = new Scanner(System.in);
        char userGuessAsChar;
        do {
            System.out.print(question + " [\'t\',\'n\']: ");
            userGuessAsChar = scanner.next().toLowerCase().charAt(0);

        } while (!(userGuessAsChar == 't' || userGuessAsChar == 'n'));
        if (userGuessAsChar == 't')
            return true;
        else if (userGuessAsChar != 'n')
            return false;
        return false;
    }
    private RpsPlayer promptAddNewUser() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Podaj swoją nazwę gracza: ");
        String scanUsername = scanner.nextLine();
        if (scanUsername.length() == 0)
            return new RpsPlayer(computerize, minNumberOfRoundsWon + new Random().nextInt(21 - minNumberOfRoundsWon));
        int numberOfRoundsWon = 0;
        String userGuessAsStr;
        do {
            System.out.print("Podaj swoją liczbę wygranych rund(>3) lub \'c\' wylosował komputer: ");
            userGuessAsStr = scanner.next();
            if (userGuessAsStr.toLowerCase().charAt(0) == 'c') {
                numberOfRoundsWon = minNumberOfRoundsWon + new Random().nextInt(21 - minNumberOfRoundsWon);
                break;
            }
            if ( Integer.parseInt(userGuessAsStr) >= minNumberOfRoundsWon )
                numberOfRoundsWon = Integer.valueOf(userGuessAsStr);
        } while (!(Integer.parseInt(userGuessAsStr) >= minNumberOfRoundsWon || userGuessAsStr.equals("c")));
        return new RpsPlayer(scanUsername, numberOfRoundsWon);
    }
}
