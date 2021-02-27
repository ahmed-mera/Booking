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

public class Booking {
    private Person person;
    private Coordinate coordinate;


    public Booking(Person person, Coordinate coordinate) {
        this.person = person;
        this.coordinate = coordinate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public Coordinate getCoordinates() {
        return coordinate;
    }

    public void setCoordinates(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    @Override
    public String toString() {
        return "Booking{" +
                "person=" + person +
                ", coordinates=" + coordinate +
                '}';
    }
}
