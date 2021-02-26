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

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class DB {

    private static char[][] ROOM = new char[10][15]; // matrix of booking
    private static final List<Booking> BOOKINGS = new ArrayList<>(); // list of booking


    /**
     *
     * @param row
     * @param column
     * @return
     */
    public static synchronized boolean book(int row, int column){
         if (! isBooking(row, column)) {
             ROOM[row][column] = 'O';
             return true;
         }

         return false;
    }



    public static synchronized boolean isBooking(int row, int column){
        return ROOM[row][column] == 'O';
    }


    public static boolean savaData(Booking booking){
        return !BOOKINGS.contains(booking) && BOOKINGS.add(booking);
    }


    public static char[][] getROOM() {
        return ROOM;
    }

    public static List<Booking> getBOOKINGS() {
        return BOOKINGS;
    }


    public static void setROOM(char[][] room) {
         ROOM = room;
    }




}
