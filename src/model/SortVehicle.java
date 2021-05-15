package model;

import java.util.Comparator;

public class SortVehicle implements Comparator<Vehicle> {

    public int compare(Vehicle o1, Vehicle o2) {
        return (o1.getLicensePlate().compareToIgnoreCase(o2.getLicensePlate()))==0?
                o2.getLicensePlate().compareToIgnoreCase(o1.getLicensePlate()):o1.getLicensePlate().compareToIgnoreCase(o2.getLicensePlate());
    }
}
