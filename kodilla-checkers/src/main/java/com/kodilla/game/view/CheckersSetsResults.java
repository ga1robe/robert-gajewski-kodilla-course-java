package com.kodilla.game.view;

import com.kodilla.game.logic.Checkers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class CheckersSetsResults {

    private static String pathnameOfCheckersResults = "resources".concat(File.separator).concat("CheckersResults");

    CheckersStateOfGame checkersStateOfGame;

    public CheckersSetsResults() {
    }

    public void saveSetsResults(HashMap<String, String> setsToSave) throws IOException {
        checkersStateOfGame = new CheckersStateOfGame();
        if (setsToSave.size() > 0) {
            File directoryOfresults = new File(pathnameOfCheckersResults);
            String absolutePath = directoryOfresults.getAbsolutePath();
            Date currentTime = new Date(System.currentTimeMillis());
            SimpleDateFormat formatter = checkersStateOfGame.getFormatter();
            String basenameFromDate = formatter.format(currentTime);
            File fileToWriteSet = new File(absolutePath, basenameFromDate.concat(".").concat("txt"));
            BufferedWriter bufferedWriter = null;
            String usernameFromTextField = Checkers.getTextFromUsernameField().trim();
            Integer numberOfREDSetWon = 0;

            for (String key : setsToSave.keySet()) {
                String theSet = setsToSave.get(key);
                String substrRED = "RED ";
                int indexOfRED = theSet.indexOf(substrRED);
                int indexOfColon = theSet.indexOf(":");
                String selectedFirstNumberInSubstring = theSet.substring(indexOfRED + substrRED.length(), indexOfColon - 1).trim();
                Integer NumberOfRed = Integer.valueOf(selectedFirstNumberInSubstring);
                boolean playerREDwonSet = NumberOfRed >= Checkers.getMaxWonGamesInSet();
                if (playerREDwonSet)
                    numberOfREDSetWon++;
            }

            try {
                bufferedWriter = new BufferedWriter(new FileWriter(fileToWriteSet));

                bufferedWriter.write("username: " + "\"".concat(usernameFromTextField).concat("\""));
                bufferedWriter.newLine();
                bufferedWriter.write("total number of sets: " + setsToSave.size());
                bufferedWriter.newLine();
                bufferedWriter.write("\"".concat(usernameFromTextField).concat("\"") + " as RED won Set number: " + numberOfREDSetWon);
                bufferedWriter.newLine();

                for (Map.Entry<String, String> entry :
                        setsToSave.entrySet()) {

                    bufferedWriter.write(entry.getKey() + ":"
                            + entry.getValue());

                    bufferedWriter.newLine();
                }

                bufferedWriter.flush();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
            finally {
                try {
                    bufferedWriter.close();
                }
                catch (Exception e) {
                    e.printStackTrace();
                }
                Checkers.setTextInMessageOfState("RED: Save results to " + directoryOfresults.toString().concat(File.separator).concat(fileToWriteSet.getName()) + " file" + ".");
            }
        }
    }
}
