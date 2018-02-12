package edu.gatech;

// import java.util.Random;
import java.util.Scanner;

public class SimDriver {
    private static SimQueue simEngine;
    private static BusSystem martaModel;

    public SimDriver() {
        simEngine = new SimQueue();
        martaModel = new BusSystem();
    }

    public static void runInterpreter() {
        final String DELIMITER = ",";
        Scanner takeCommand = new Scanner(System.in);
        String[] tokens;

        do {
            System.out.print("# main: ");
            String userCommandLine = takeCommand.nextLine();
            tokens = userCommandLine.split(DELIMITER);

            switch (tokens[0]) {
                case "add_event":
                    simEngine.addNewEvent(Integer.parseInt(tokens[1]), tokens[2], Integer.parseInt(tokens[3]));
                    System.out.print(" new event - rank: " + Integer.parseInt(tokens[1]));
                    System.out.println(" type: " + tokens[2] + " ID: " + Integer.parseInt(tokens[3]) + " created");
                    break;
                case "add_stop":
                    int stopID = martaModel.makeStop(tokens[1], Integer.parseInt(tokens[2]), Double.parseDouble(tokens[3]), Double.parseDouble(tokens[4]));
                    System.out.println(" new stop: " + Integer.toString(stopID) + " created");
                    break;
                case "add_route":
                    int routeID = martaModel.makeRoute(Integer.parseInt(tokens[1]), tokens[2]);
                    System.out.println(" new route: " + Integer.toString(routeID) + " created");
                    break;
                case "add_bus":
                    int busID = martaModel.makeBus(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]), Integer.parseInt(tokens[3]), Integer.parseInt(tokens[4]), Integer.parseInt(tokens[5]));
                    System.out.println(" new bus: " + Integer.toString(busID) + " created");
                    break;
                case "extend_route":
                    martaModel.appendStopToRoute(Integer.parseInt(tokens[1]), Integer.parseInt(tokens[2]));
                    System.out.println(" stop: " + Integer.parseInt(tokens[2]) + " appended to route " + Integer.parseInt(tokens[1]));
                    break;
                case "step_once":
                    simEngine.triggerNextEvent(martaModel);
                    System.out.println(" queue activated for 1 event");
                    break;
                case "step_multi":
                    System.out.println(" queue activated for " + Integer.parseInt(tokens[1]) + " event(s)");
                    for (int i = 0; i < Integer.parseInt(tokens[1]); i++) { simEngine.triggerNextEvent(martaModel); }
                    break;
                case "system_report":
                    System.out.println(" system report - stops, buses and routes:");
                    for (BusStop singleStop: martaModel.getStops().values()) { singleStop.displayInternalStatus(); }
                    for (Bus singleBus: martaModel.getBuses().values()) { singleBus.displayInternalStatus(); }
                    for (BusRoute singleRoute: martaModel.getRoutes().values()) { singleRoute.displayInternalStatus(); }
                    break;
                case "quit":
                    System.out.println(" stop the command loop");
                    break;
                default:
                    System.out.println(" command not recognized");
                    break;
            }

        } while (!tokens[0].equals("quit"));

        takeCommand.close();
    }

}
