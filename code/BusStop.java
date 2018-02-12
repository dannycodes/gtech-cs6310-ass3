package edu.gatech;

import java.util.Random;

public class BusStop {
    private Integer ID;
    private String stopName;
    private Integer riders;
    private Double xCoord;
    private Double yCoord;
    private Random randValue;

    public BusStop() {
        this.ID = -1;
    }

    public BusStop(int uniqueValue) {
        this.ID = uniqueValue;
        this.stopName = "";
        this.riders = -1;
        this.xCoord = -1.0;
        this.yCoord = -1.0;
        randValue = new Random();
    }

    public BusStop(int uniqueValue, String inputName, int inputRiders, double inputXCoord, double inputYCoord) {
        this.ID = uniqueValue;
        this.stopName = inputName;
        this.riders = inputRiders;
        this.xCoord = inputXCoord;
        this.yCoord = inputYCoord;
        randValue = new Random();
   }

    public void setName(String inputName) { this.stopName = inputName; }

    public void setRiders(int inputRiders) { this.riders = inputRiders; }

    public void setXCoord(double inputXCoord) { this.xCoord = inputXCoord; }

    public void setYCoord(double inputYCoord) { this.yCoord = inputYCoord; }

    public Integer getID() { return this.ID; }

    public String getName() { return this.stopName; }

    public Integer getRiders() { return this.riders; }

    public Double getXCoord() { return this.xCoord; }

    public Double getYCoord() { return this.yCoord; }

    public void displayEvent() {
        System.out.println(" bus stop: " + Integer.toString(this.ID));
    }

    public void takeTurn() {
        System.out.println("get new people - exchange with bus when it passes by");
    }

    public Double findDistance(BusStop destination) {
        final double distanceConversion = 1.0;
        return distanceConversion * Math.sqrt(Math.pow((this.xCoord - destination.getXCoord()), 2) + Math.pow((this.yCoord - destination.getYCoord()), 2));
    }

    public Integer exchangeRiders(int passengers, int capacity) {
        // calculate expected number riders getting on and off the bus
        int getting_off = randValue.nextInt(5);
        int getting_on = randValue.nextInt(3);

        //check bus limits against the number of riders wanting to get on and off
        getting_off = Math.min(passengers, getting_off);
        int seats_available = capacity - (passengers - getting_off);
        getting_on = Math.min(riders, Math.min(seats_available, getting_on));

        // update the number of passengers at this stop
        riders = riders - getting_on;

        return getting_on - getting_off;
    }

    public void addNewRiders(int moreRiders) { riders = riders + moreRiders; }

    public void displayInternalStatus() {
        System.out.print("> stop - ID: " + Integer.toString(ID));
        System.out.println(" name: " + stopName + " riders: " + Integer.toString(riders));
        System.out.println(" xCoord: " + Double.toString(xCoord) + " yCoord: " + Double.toString(yCoord));
    }

    //Override the equals method to compare the object
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            BusStop me = (BusStop) object;
            if (this.ID == me.getID()) {
                result = true;
            }
        }
        return result;
    }

}
