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

public class DB {
    private static final char[][] BOOKING = new char[10][15]; // matrix of booking


    public static synchronized boolean book(int row, int column){
         if (! isBooking(row, column)) {
             BOOKING[row][column] = 'O';
             return true;
         }

         return false;
    }


    public static boolean isBooking(int row, int column){
        return BOOKING[row][column] == 'O';
    }

}
