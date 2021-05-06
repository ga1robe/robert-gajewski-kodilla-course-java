package com.kodilla.testing.weather.mock;

import com.kodilla.testing.weather.stub.Temperatures;
import com.kodilla.testing.weather.stub.WeatherForecast;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WeatherForecastTestSuite {
    private static int testCounter = 0;
    @Mock
    private Temperatures temperaturesMock;
    private static Map<String, Double> temperaturesMap = new HashMap<>();
    @BeforeEach
    public void beforeEveryTest() {
        testCounter++;
        System.out.println("Preparing to execute test #" + testCounter);
        temperaturesMap.put("Rzeszów", 25.5);
        temperaturesMap.put("Kraków", 26.2);
        temperaturesMap.put("Wrocław", 24.8);
        temperaturesMap.put("Warszawa", 25.2);
        temperaturesMap.put("Gdańsk", 26.1);
        when(temperaturesMock.getTemperatures()).thenReturn(temperaturesMap);
    }
    @Test
    void testCalculateForecastWithMock() {
        /*
         * Given
         */
        WeatherForecast weatherForecast = new WeatherForecast(temperaturesMock);
        /*
         * When
         */
        int quantityOfSensors = weatherForecast.calculateForecast().size();
        /*
         * Then
         */
        Assertions.assertEquals(5, quantityOfSensors);
    }
    @Test
    void testCalculateAverageWithMock() {
        /*
         * Given
         */
        WeatherForecast weatherForecast = new WeatherForecast(temperaturesMock);
        /*
         * When
         */
        double averageOfSensors = weatherForecast.calculateAverage();
        /*
         * Then
         */
        Assertions.assertEquals(25.56, averageOfSensors);
    }
    @Test
    void testCalculateMedianWithMock() {
        /*
         * Given
         */
        WeatherForecast weatherForecast = new WeatherForecast(temperaturesMock);
        /*
         * When
         */
        double medianOfSensors = weatherForecast.calculateMedian();
        /*
         * Then
         */
        Assertions.assertEquals(25.5, medianOfSensors);
    }
}
