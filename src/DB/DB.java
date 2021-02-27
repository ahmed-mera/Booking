/*
 *  Copyright (c) 2021
 *  Version: AM FREE 1.0.0
 *
 *  Copyright: Ahmed Mera
 *  https://mera.ddns.net
 *
 *  Contact: meraahmedibrahim@itis-molinari.eu
 */

package DB;

import Bean.Booking;
import Bean.BookingMoreSeats;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class DB {

    private static char[][] ROOM = new char[10][15]; // matrix of booking
    private static final List<Booking> BOOKINGS = new ArrayList<>(); // list of booking
    private static final List<BookingMoreSeats> BOOKINGS_MORE = new ArrayList<>(); // list of booking More seats
    private static int FREE_SEATS = ROOM[0].length;


    /**
     * function to booking
     * @param row
     * @param column
     * @return
     */
    public static synchronized boolean book(Booking booking){
         if (!isBooking(booking.getCoordinates().getRow(), booking.getCoordinates().getColumn())) {
             ROOM[booking.getCoordinates().getRow()][booking.getCoordinates().getColumn()] = 'O';
             FREE_SEATS--;
             return savaData(booking);
         }

         return false;
    }


    public static synchronized boolean book(BookingMoreSeats bookingMoreSeats){
        bookingMoreSeats.getCoordinates().forEach(b -> {
            if (!isBooking(b.getRow(), b.getColumn())) {
                ROOM[b.getRow()][b.getColumn()] = 'O';
            }
        });

        return savaData(bookingMoreSeats);

    }

    /**
     * function to check booking
     * @param row
     * @param column
     * @return boolean vlaue
     */
    public static synchronized boolean isBooking(int row, int column){
        return ROOM[row][column] == 'O';
    }


    /**
     * function to save bookings and data of users
     * @param booking
     * @return boolean vlaue
     */
    private synchronized static boolean savaData(Booking booking){
        return !BOOKINGS.contains(booking) && BOOKINGS.add(booking);
    }


    /**
     * function to save bookings and data of users
     * @param BookingMoreSeats
     * @return boolean vlaue
     */
    private synchronized static boolean savaData(BookingMoreSeats BookingMoreSeats){
        return !BOOKINGS_MORE.contains(BookingMoreSeats) && BOOKINGS_MORE.add(BookingMoreSeats);
    }


    /**
     * get room of cienma
     * @return char[][]
     */
    public synchronized static char[][] getROOM() {
        return ROOM;
    }


    /**
     * set room of cienma
     */
    public synchronized static void setROOM(char[][] room) {
        ROOM = room;
    }


    /**
     * function to calc seats free
     * @return frea seats
     */
    public synchronized static int getFreeSeats() {
        return FREE_SEATS;
    }

    public synchronized static void setFreeSeats(int freeSeats) {
        FREE_SEATS = freeSeats;
    }



    /**
     * get all bookings
     * @return char[][]
     */
    public synchronized static List<Booking> getBOOKINGS() {
        return BOOKINGS;
    }


    /**
     * function to show the room
     *
     *       1 2 3 4  5 6 7 8 9 10 11 12 13 14 15
     *   01) L L L O O L L L L  L  L  O   O  O  O
     *   02) L L L O O L L L L  L  L  O   L  L  L
     *   03) L L L O O L L L L  L  L  O   O  L  O
     *   04) L L L O O L L L L  L  L  O   O  O  O
     *   05) L L L O O L L L L  L  L  O   L  O  O
     *   06) L L L O O L L L L  L  L  O   O  O  O
     *   07) L L L O O L L L L  L  L  O   O  O  O
     *   08) L L L O O L L L L  L  L  O   O  L  O
     *   09) L L L O O L L L L  L  L  O   O  O  O
     *   10) L L L O O L L O O  L  L  O   O  O  O
     */
    public static void showSeats(){

        System.out.print("\n\n   \t"); // to print the  first column
        for (int i = 0; i < ROOM[0].length; i++)
            System.out.print(i + 1 + "\t");


        for (int i = 0; i < ROOM.length; i++) {
            System.out.print(i < 9 ? "0" + i + 1 + ")\t" : i + 1 + ")\t");
            for (int j = 0; j < ROOM[i].length; j++) {
                System.out.print(ROOM[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("\n");
    }



}
