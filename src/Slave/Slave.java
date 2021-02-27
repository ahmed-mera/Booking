/*
 *  Copyright (c) 2021
 *  Version: AM FREE 1.0.0
 *
 *  Copyright: Ahmed Mera
 *  https://mera.ddns.net
 *
 *  Contact: meraahmedibrahim@itis-molinari.eu
 */

package Slave;

import Bean.Booking;
import Bean.BookingMoreSeats;
import Common.Common;
import Constants.Constants;
import DB.DB;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.Socket;

public class Slave implements Runnable{

    private final Socket socket;
    private boolean isRunning;
    private final Common common = new Common();

    public Slave(Socket socket) {
        this.socket = socket;
        this.isRunning = true;
    }


    /**
     * When an object implementing interface {@code Runnable} is used
     * to create a thread, starting the thread causes the object's
     * {@code run} method to be called in that separately executing
     * thread.
     * <p>
     * The general contract of the method {@code run} is that it may
     * take any action whatsoever.
     *
     * @see Thread#run()
     */
    @Override
    public void run() {
        this.start();
    }



    private void start() {
        while (this.isRunning){
            try {
                switch (this.common.cleanSpace(this.common.readData(this.socket))) {
                    case "1" -> {
                        Booking booking = new ObjectMapper().readValue(this.common.readData(this.socket), Booking.class); // booking a seat

                        if (booking != null) {
                            this.common.sendData(this.socket, DB.book(booking) ? Constants.BOOKING_DONE : Constants.BOOKING_FAIL);
                        } else
                            this.common.sendData(this.socket, Constants.ERROE_MSG);
                    }

                    case "3" -> {
                       BookingMoreSeats bookingMoreSeats = new ObjectMapper().readValue(this.common.readData(this.socket), BookingMoreSeats.class);// booking more seats
                        if (bookingMoreSeats != null) {
                            this.common.sendData(this.socket, DB.book(bookingMoreSeats) ? Constants.BOOKING_DONE : Constants.BOOKING_FAIL);
                        } else
                            this.common.sendData(this.socket, Constants.ERROE_MSG);
                    }

                    case "0" -> this.socket.close();
                }
            }catch (IOException error){
                this.isRunning = false; // stop server
                System.out.println(Constants.INTERNAL_ERROR + " 1.0 ---> " + error.getMessage());
                try {
                    this.common.sendData(this.socket, Constants.INTERNAL_ERROR);
                } catch (IOException e) {
                    System.out.println(Constants.INTERNAL_ERROR + " 1.1 ---> " + error.getMessage());
                }
            }
        }
    }



}
