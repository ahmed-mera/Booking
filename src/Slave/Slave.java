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
import Common.Common;
import Constants.Constants;

import java.io.IOException;
import java.net.Socket;

public class Slave implements Runnable{

    private final Socket socket;
    private final Common common = new Common();

    public Slave(Socket socket) {
        this.socket = socket;
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

    }



}
