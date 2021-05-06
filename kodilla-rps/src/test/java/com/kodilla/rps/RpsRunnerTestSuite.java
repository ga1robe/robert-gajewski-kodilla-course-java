package com.kodilla.rps;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RpsRunnerTestSuite {
    @Test
    void testAddUserToList() {
        /*
        * Given
         */
        RpsPlaying rpsPlaying = new RpsPlaying();
        /*
        * When
         */
        rpsPlaying.addPlayerToList(new RpsPlayer("TestPlayer01", 5));
        rpsPlaying.addPlayerToList(new RpsPlayer("TestPlayer02", 7));
        /*
        * Then
         */
        assertEquals(2, rpsPlaying.getPlayerList().size());
    }
    @Test
    void testAddStuffToList() {
        /*
         * Given
         */
        RpsPlaying rpsPlaying = new RpsPlaying();
        /*
         * When
         */
        rpsPlaying.addStuffToList(new RpsStuff(1, "Rock"));
        rpsPlaying.addStuffToList(new RpsStuff(2, "Paper"));
        rpsPlaying.addStuffToList(new RpsStuff(3, "Scissors"));
        /*
         * Then
         */
        assertEquals(3, rpsPlaying.getStuffList().size());
    }
    @Test
    void testAddRelationToList() {
        /*
         * Given
         */
        RpsPlaying rpsPlaying = new RpsPlaying();
        /*
         * When
         */
        rpsPlaying.addRelationToList(new RpsRelation(1,2,2));
        rpsPlaying.addRelationToList(new RpsRelation(2,3,3));
        rpsPlaying.addRelationToList(new RpsRelation(3,1,1));
        /*
         * Then
         */
        assertEquals(3, rpsPlaying.getRelationList().size());
    }
    @Test
    void testWinnerFromRelations() {
        /*
         * Given
         */
        RpsPlaying rpsPlaying = new RpsPlaying();
        /*
         * When
         */
        rpsPlaying.addStuffToList(new RpsStuff(1, "Rock"));
        rpsPlaying.addStuffToList(new RpsStuff(2, "Paper"));
        rpsPlaying.addStuffToList(new RpsStuff(3, "Scissors"));
        rpsPlaying.addRelationToList(new RpsRelation(1,2,2));
        rpsPlaying.addRelationToList(new RpsRelation(2,3,3));
        rpsPlaying.addRelationToList(new RpsRelation(3,1,1));
        /*
         * Then
         */
        assertEquals(2, rpsPlaying.getWinnerFromRelations(1, 2));
    }
}
