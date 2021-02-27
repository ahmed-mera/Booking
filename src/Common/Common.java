/*
 *  Copyright (c) 2021
 *  Version: AM FREE 1.0.0
 *
 *  Copyright: Ahmed Mera
 *  https://mera.ddns.net
 *
 *  Contact: meraahmedibrahim@itis-molinari.eu
 */

package Common;

import Constants.Constants;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Calendar;
import java.util.Date;

public class Common {

    private int port = 2020; // port to server
    private String host = "localhost"; // host to server


    /**
     * un metodo per leggere la data che il server che ci ha inviato
     * @param socket {@link Socket}
     * @return String {@link String}
     * see {@link Socket}, {@link String}
     * @throws IOException genera una eccezione del tipo comunicativo
     */
    public String readData(Socket socket) throws IOException {
        return (new BufferedReader( new InputStreamReader( socket.getInputStream() ))).readLine();
    }



    /**
     * un metodo per mandare la data al server
     * @param socket {@link Socket}
     * @param data {@link String}
     * see {@link Socket}, {@link String}
     * @throws IOException genera una eccezione del tipo comunicativo
     */
    public void sendData(Socket socket, String data) throws IOException {
        (new PrintWriter( socket.getOutputStream(), true)).println(data);
    }


    /**
     * funzione per togliere gli spazi,
     * @param value type {@link String}
     * @return il resultato senza spazi
     */
    public String cleanSpace(String value){ return value == null ? null :  value.trim(); }




    /**
     * helper function to get the port
     * @return port
     */
    public int getPort() {
        return port;
    }


    public void setPort(int port) {
        this.port = port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    /**
     * helper function to get the host
     * @return host
     */
    public String getHost() {
        return host;
    }


    /**
     * function to check error
     * @param input
     * @return
     */
    public boolean isError(String input){
        return input.equalsIgnoreCase(Constants.INTERNAL_ERROR);
    }


    /**
     * function to check time
     * @return
     */
    public boolean checkTime(int day, int hour, int min, int sec){
        Calendar calendar = Calendar.getInstance();
        Calendar currentCalendar = Calendar.getInstance();
        calendar.set(2021, Calendar.FEBRUARY, day, hour, min, sec);
        currentCalendar.setTime(new Date());

       return currentCalendar.compareTo(calendar) >= 1 ||
               (currentCalendar.compareTo(calendar) == 0 &&
               currentCalendar.get(Calendar.HOUR_OF_DAY) >= calendar.get(Calendar.HOUR_OF_DAY) &&
               currentCalendar.get(Calendar.MINUTE) >= calendar.get(Calendar.MINUTE));
    }



//    /**
//     * function to check time II
//     * @return
//     */
//    public boolean checkTimeII(){
//        Calendar calendar = Calendar.getInstance();
//        Calendar currentCalendar = Calendar.getInstance();
//        calendar.set(2021, Calendar.FEBRUARY, 22, 9, 0, 0);
//        currentCalendar.setTime(new Date());
//
//        return currentCalendar.compareTo(calendar) >= 0 &&
//                calendar.get(Calendar.HOUR_OF_DAY)  <= currentCalendar.get(Calendar.HOUR_OF_DAY) &&
//                calendar.get(Calendar.MINUTE) <= currentCalendar.get(Calendar.MINUTE);
//    }
}
