package com.kodilla.rps;

public class RpsRunner {
    public static void main(String[] args) {
        RpsPlaying rpsPlaying = new RpsPlaying();
        rpsPlaying.addPlayerToList(new RpsPlayer("@computer", 0));
//        rpsPlaying.addPlayerToList(new RpsPlayer("Player01", 0));
        rpsPlaying.addStuffToList(new RpsStuff(1, "Rock"));
        rpsPlaying.addStuffToList(new RpsStuff(2, "Paper"));
        rpsPlaying.addStuffToList(new RpsStuff(3, "Scissors"));
        rpsPlaying.addRelationToList(new RpsRelation(1,2,2));
        rpsPlaying.addRelationToList(new RpsRelation(2,3,3));
        rpsPlaying.addRelationToList(new RpsRelation(3,1,1));
        rpsPlaying.start();
    }
}
