package edu.gatech;

import java.util.Random;

public class BusRiderGenerator {
    private Random randGenerator;
    final Integer lowerBound = 0;
    final Integer upperBound = 5;

    public BusRiderGenerator() {
        randGenerator = new Random();
    }

    public Integer riderArrivals() {
        return randGenerator.nextInt(upperBound - lowerBound) + lowerBound;
    }
}
