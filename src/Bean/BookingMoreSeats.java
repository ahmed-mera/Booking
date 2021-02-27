/*
 *  Copyright (c) 2021
 *  Version: AM FREE 1.0.0
 *
 *  Copyright: Ahmed Mera
 *  https://mera.ddns.net
 *
 *  Contact: meraahmedibrahim@itis-molinari.eu
 */

package Bean;

import java.util.ArrayList;

public class BookingMoreSeats {
    private Person person;
    private ArrayList<Coordinate> coordinates;

    public BookingMoreSeats() {
        super();
    }

    public BookingMoreSeats(Person person, ArrayList<Coordinate> coordinateArrayList) {
        this.person = person;
        this.coordinates = coordinateArrayList;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public ArrayList<Coordinate> getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArrayList<Coordinate> coordinates) {
        this.coordinates = coordinates;
    }

    @Override
    public String toString() {
        return "BookingMoreSeats{" +
                "person=" + person +
                ", coordinates=" + coordinates +
                '}';
    }
}
