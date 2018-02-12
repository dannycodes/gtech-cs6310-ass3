package edu.gatech;

public class Bus {
    private Integer ID;
    private Integer route;
    private Integer routeLocation;
    private Integer passengers;
    private Integer capacity;
    private Integer speed;

    public Bus() {
        this.ID = -1;
    }

    public Bus(int uniqueValue) {
        this.ID = uniqueValue;
        this.route = -1;
        this.routeLocation = -1;
        this.passengers = -1;
        this.capacity = -1;
        this.speed = -1;
    }

    public Bus(int uniqueValue, int inputRoute, int inputLocation, int inputPassengers, int inputCapacity, int inputSpeed) {
        this.ID = uniqueValue;
        this.route = inputRoute;
        this.routeLocation = inputLocation;
        this.passengers = inputPassengers;
        this.capacity = inputCapacity;
        this.speed = inputSpeed;
   }

    public void setRoute(int inputRoute) { this.route = inputRoute; }

    public void setLocation(int inputLocation) { this.routeLocation = inputLocation; }

    public void setPassengers(int inputPassengers) { this.passengers = inputPassengers; }

    public void setCapacity(int inputCapacity) { this.capacity = inputCapacity; }

    public void setSpeed(int inputSpeed) { this.speed = inputSpeed; }

    public Integer getID() { return this.ID; }

    public Integer getRouteID() { return this.route; }

    public Integer getLocation() { return this.routeLocation; }

    public Integer getPassengers() { return this.passengers; }

    public Integer getCapacity() { return this.capacity; }

    public Integer getSpeed() { return this.speed; }

    public void displayEvent() {
        System.out.println(" bus: " + Integer.toString(this.ID));
    }

    public void displayInternalStatus() {
        System.out.print("> bus - ID: " + Integer.toString(ID));
        System.out.println(" route: " + Integer.toString(route) + " location: " + Integer.toString(routeLocation));
        System.out.print(" passengers: " + Integer.toString(passengers) + " capacity: " + Integer.toString(capacity));
        System.out.println(" speed: " + Integer.toString(speed));
    }

    public void takeTurn() {
        System.out.println("drop off passengers - pickup passengers to capacity - move to next stop");
    }

    public void adjustPassengers(int differential) { passengers = passengers + differential; }

    //Override the equals method to compare the object
    @Override
    public boolean equals(Object object) {
        boolean result = false;
        if (object == null || object.getClass() != getClass()) {
            result = false;
        } else {
            Bus me = (Bus) object;
            if (this.ID == me.getID()) {
                result = true;
            }
        }
        return result;
    }

}
