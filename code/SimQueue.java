package edu.gatech;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Random;

public class SimQueue {
    private static PriorityQueue<SimEvent> eventQueue;
    private Comparator<SimEvent> simComparator;
    final static Integer passengerFrequency = 3;

    public SimQueue() {
        simComparator = new SimEventComparator();
        eventQueue = new PriorityQueue<SimEvent>(100, simComparator);
    }

    public static void triggerNextEvent(BusSystem busModel) {
        Random randValue = new Random();
        if (eventQueue.size() > 0) {
            SimEvent activeEvent = eventQueue.poll();
            activeEvent.displayEvent();
            switch (activeEvent.getType()) {
                case "move_bus":
                    // identify the bus that will move
                    Bus activeBus = busModel.getBus(activeEvent.getID());
                    System.out.println(" the bus being observed is: " + Integer.toString(activeBus.getID()));

                    // identify the current stop
                    BusRoute activeRoute = busModel.getRoute(activeBus.getRouteID());
                    System.out.println(" the bus is driving on route: " + Integer.toString(activeRoute.getID()));

                    int activeLocation = activeBus.getLocation();
                    int activeStopID = activeRoute.getStopID(activeLocation);
                    BusStop activeStop = busModel.getStop(activeStopID);
                    System.out.println(" the bus is currently at stop: " + Integer.toString(activeStop.getID()) + " - " + activeStop.getName());

                    // drop off and pickup new passengers at current stop
                    int currentPassengers = activeBus.getPassengers();
                    int passengerDifferential = activeStop.exchangeRiders(currentPassengers, activeBus.getCapacity());
                    System.out.println(" passengers pre-stop: " + Integer.toString(currentPassengers) + " post-stop: " + (currentPassengers + passengerDifferential));
                    activeBus.adjustPassengers(passengerDifferential);

                    // determine next stop
                    int nextLocation = activeRoute.getNextLocation(activeLocation);
                    int nextStopID = activeRoute.getStopID(nextLocation);
                    BusStop nextStop = busModel.getStop(nextStopID);
                    System.out.println(" the bus is heading to stop: " + Integer.toString(nextStopID) + " - " + nextStop.getName() + "\n");

                    // find distance to stop to determine next event time
                    Double travelDistance = activeStop.findDistance(nextStop);
                    int travelTime = 1 + travelDistance.intValue() / activeBus.getSpeed();
                    activeBus.setLocation(nextLocation);

                    // generate next event for this bus
                    eventQueue.add(new SimEvent(activeEvent.getRank() + travelTime, "move_bus", activeEvent.getID()));
                    break;
                case "riders_arrive":
                    // iterate through the stops and add new people randomly
                    for (BusStop singleStop: busModel.getStops().values()) {
                        int moreRiders = busModel.getRiders().riderArrivals();
                        singleStop.addNewRiders(moreRiders);
                        System.out.println(" stop: " + singleStop.getName() + " has " + moreRiders + " new passengers");
                    }
                    System.out.println();

                    // generate next event for passenger arrivals
                    int arrivalTime = 3 + randValue.nextInt(passengerFrequency);
                    eventQueue.add(new SimEvent(activeEvent.getRank() + arrivalTime, "riders_arrive", activeEvent.getID()));
                    break;
                default:
                    System.out.println(" event not recognized");
                    break;
            }
        } else {
            System.out.println(" event queue empty");
        }
    }

    public static void addNewEvent(Integer eventRank, String eventType, Integer eventID) {
        eventQueue.add(new SimEvent(eventRank, eventType, eventID));
    }
}
