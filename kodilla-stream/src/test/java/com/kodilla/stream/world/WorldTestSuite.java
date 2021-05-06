package com.kodilla.stream.world;

import com.kodilla.stream.sand.Africa;
import com.kodilla.stream.sand.Asia;
import com.kodilla.stream.sand.Europe;
import com.kodilla.stream.sand.SandStorage;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WorldTestSuite {
    @Test
    void testGetPeopleQuantity() {
        /*
        * Given
         */
        World world = new World();
        world.addContinentToList(new Continent("Asia"));
        Continent firstContinent = world.getListOfContinents().get(0);
        firstContinent.addCountryToList(new Country("Chiny", BigDecimal.valueOf(1401585247L)));
        firstContinent.addCountryToList(new Country("Indie", BigDecimal.valueOf(1359527235L)));
        firstContinent.addCountryToList(new Country("Indonezja", BigDecimal.valueOf(262787403L)));
        firstContinent.addCountryToList(new Country("Pakistan", BigDecimal.valueOf(207862518L)));
        firstContinent.addCountryToList(new Country("Bangladesz", BigDecimal.valueOf(159453001L)));
        firstContinent.addCountryToList(new Country("Japonia", BigDecimal.valueOf(126168156L)));
        firstContinent.addCountryToList(new Country("Filipiny", BigDecimal.valueOf(105893381L)));
        firstContinent.addCountryToList(new Country("Wietnam", BigDecimal.valueOf(97040334L)));
        world.addContinentToList(new Continent("Europe"));
        Continent secondContinent = world.getListOfContinents().get(1);
        secondContinent.addCountryToList(new Country("Rosja", BigDecimal.valueOf(142122776L)));
        secondContinent.addCountryToList(new Country("UE", BigDecimal.valueOf(517111319L)));
        world.addContinentToList(new Continent("America"));
        Continent thirdContinent = world.getListOfContinents().get(2);
        thirdContinent.addCountryToList(new Country("USA", BigDecimal.valueOf(329256465L)));
        thirdContinent.addCountryToList(new Country("Brazylia", BigDecimal.valueOf(208846892L)));
        thirdContinent.addCountryToList(new Country("Meksyk", BigDecimal.valueOf(125959205L)));
        world.addContinentToList(new Continent("Africa"));
        Continent fourthContinent = world.getListOfContinents().get(3);
        fourthContinent.addCountryToList(new Country("Nigeria", BigDecimal.valueOf(203452505L)));
        fourthContinent.addCountryToList(new Country("Etiopia", BigDecimal.valueOf(108386391L)));
        fourthContinent.addCountryToList(new Country("Egipt", BigDecimal.valueOf(99413317L)));
        fourthContinent.addCountryToList(new Country("Kongo", BigDecimal.valueOf(85281024L)));
        /*
        * When
         */
        BigDecimal totalPeopleQuantity = world.getPeopleQuantity();
        /*
        * Then
         */
        assertEquals(BigDecimal.valueOf(5540147169L), totalPeopleQuantity);
    }
}
