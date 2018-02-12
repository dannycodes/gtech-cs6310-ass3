package edu.gatech;

public class Main {

    public static void main(String[] args) {
        System.out.println("Mass Transit Simulation System Starting...");

        SimDriver commandInterpreter = new SimDriver();
        commandInterpreter.runInterpreter();
    }
}
