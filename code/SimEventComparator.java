package edu.gatech;

import java.util.Comparator;

public class SimEventComparator implements Comparator<SimEvent> {
    @Override
    public int compare(SimEvent x, SimEvent y) {
        if (y == null) {
            return -1;
        } else if (x == null) {
            return 1;
        } else if (x.getRank() < y.getRank()) {
            return -1;
        } else if (x.getRank() > y.getRank()) {
            return 1;
        } else {
            return 0;
        }
    }

}
