package com.kodilla.testing.weather.stub;

import java.lang.reflect.Array;
import java.util.*;

public class WeatherForecast {
    private Temperatures temperatures;

    public WeatherForecast(Temperatures temperatures) {
        this.temperatures = temperatures;
    }
    public Map<String, Double> calculateForecast() {
        Map<String, Double> resultMap = new HashMap<>();
        for (Map.Entry<String, Double> temperature:
             temperatures.getTemperatures().entrySet()) {
            /*
            * adding 1 celsius degree to current value
            * as a temporary weather forecast
             */
            resultMap.put(temperature.getKey(), temperature.getValue() + 1.0);
        }
        return resultMap;
    }
    public double calculateAverage() {
        double average = 0d;
        double sumOfItems = 0d;
        Collection<Double> collectionOfTemperatures = temperatures.getTemperatures().values();
        if(collectionOfTemperatures.size() > 0) {
            for (Double itemOfTemperature :
                    collectionOfTemperatures) {
                sumOfItems += itemOfTemperature;
            }
            average = sumOfItems / collectionOfTemperatures.size();
        }

        return average;
    }
    public Double calculateMedian() {
        Double median = 0d;
        Object[] temperatores = temperatures.getTemperatures().values().toArray();
        //Arrays.sort(collectionOfTemperatures);
        Arrays.sort(temperatores);
        if(temperatores.length % 2 == 0) {
            median = ((double)temperatores[temperatores.length/2] + (double)temperatores[temperatores.length/2 - 1])/2;;
        } else {
            median = (double) temperatores[temperatores.length/2];
        }
        return median;
    }
}
