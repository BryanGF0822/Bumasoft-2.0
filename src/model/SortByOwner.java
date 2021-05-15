package model;

import java.util.Comparator;

public class SortByOwner implements Comparator<Owner> {

    public int compare(Owner o1, Owner o2) {
        return (o1.getFullName().compareToIgnoreCase(o2.getFullName()))==0?
                o2.getFullName().compareToIgnoreCase(o1.getFullName()):o1.getFullName().compareToIgnoreCase(o2.getFullName());
    }
}
