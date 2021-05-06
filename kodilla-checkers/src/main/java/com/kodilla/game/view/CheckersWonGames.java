package com.kodilla.game.view;

import java.util.ArrayList;

public class CheckersWonGames {

    private ArrayList<Integer> listOfMaxWonGamesInSet = new ArrayList<>();
    private Integer defaultIndex;

//    public CheckersWonGames(Integer defaultIndex) { this.defaultIndex = defaultIndex; }

    public CheckersWonGames() {

    }

    public ArrayList<Integer> getListOfMaxWonGamesInSet() {
        return listOfMaxWonGamesInSet;
    }

    public Integer getDefaultValue() {

        if (listOfMaxWonGamesInSet == null)
            return -1;
        return listOfMaxWonGamesInSet.get(defaultIndex);
    }

    public void addNewItem(Integer newItem) {

        listOfMaxWonGamesInSet.add(newItem);
    }

    public void setDefaultIndex(Integer defaultIndex) {

        this.defaultIndex = defaultIndex;
    }

    public void setDefaultValue(Integer newValue) {
        if (listOfMaxWonGamesInSet.contains(newValue)) {
            this.defaultIndex = listOfMaxWonGamesInSet.indexOf(newValue);
            listOfMaxWonGamesInSet.set(this.defaultIndex, newValue);
        }
    }
}
