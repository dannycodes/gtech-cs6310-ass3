package edu.gatech;

import java.util.HashMap;

public class BusSystem {
    private HashMap<Integer, BusStop> stops;
    private HashMap<Integer, BusRoute> routes;
    private HashMap<Integer, Bus> buses;
    private BusRiderGenerator riders;

    public BusSystem() {
        stops = new HashMap<Integer, BusStop>();
        routes = new HashMap<Integer, BusRoute>();
        buses = new HashMap<Integer, Bus>();
        riders = new BusRiderGenerator();
    }

    public BusStop getStop(int stopID) {
        if (stops.containsKey(stopID)) { return stops.get(stopID); }
        return null;
    }

    public BusRoute getRoute(int routeID) {
        if (routes.containsKey(routeID)) { return routes.get(routeID); }
        return null;
    }

    public Bus getBus(int busID) {
        if (buses.containsKey(busID)) { return buses.get(busID); }
        return null;
    }

    public BusRiderGenerator getRiders() { return riders; }

    public int makeStop(String inputName, int inputRiders, double inputXCoord, double inputYCoord) {
        int uniqueValue = stops.size();
        stops.put(uniqueValue, new BusStop(uniqueValue, inputName, inputRiders, inputXCoord, inputYCoord));
        return uniqueValue;
    }

    public int makeRoute(int inputNumber, String inputName) {
        int uniqueValue = routes.size();
        routes.put(uniqueValue, new BusRoute(uniqueValue, inputNumber, inputName));
        return uniqueValue;
    }

    public int makeBus(int inputRoute, int inputLocation, int inputPassengers, int inputCapacity, int inputSpeed) {
        int uniqueValue = buses.size();
        buses.put(uniqueValue, new Bus(uniqueValue, inputRoute, inputLocation, inputPassengers, inputCapacity, inputSpeed));
        return uniqueValue;
    }

    public void appendStopToRoute(int routeID, int nextStopID) { routes.get(routeID).addNewStop(nextStopID); }

    public HashMap<Integer, BusStop> getStops() { return stops; }

    public HashMap<Integer, BusRoute> getRoutes() { return routes; }

    public HashMap<Integer, Bus> getBuses() { return buses; }
}
