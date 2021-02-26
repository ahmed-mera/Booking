package Server;


import Bean.Booking;
import Common.Common;
import DB.DB;
import Slave.Slave;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Server {

    private final Common common = new Common();
    private final ServerSocket serverSocket;
    private boolean isAlive;
    private static Server INSTANCE = null;
    private final List<Thread> listOfThread = new ArrayList<>();


    private Server() throws IOException {
        this.serverSocket = new ServerSocket(this.common.getPort());
    }


    public void start() throws IOException {
        while (this.isAlive) {
            Socket socket = this.serverSocket.accept();
            System.out.println("New Connection " + socket.toString());

            Thread thread = new Thread(new Slave(socket));
            this.listOfThread.add(thread);
            System.out.println("list of threads ==> " + Arrays.toString(listOfThread.toArray()));
            thread.start();
        }
    }




    public static Server INSTANCE() throws IOException {
        if (INSTANCE == null)
            INSTANCE = new Server();

        return INSTANCE;
    }



    private void populate(){
        char[][] room = new char[10][15];

        for (int i = 0; i < 10; i++)
            for (int j = 0; j < 15; j++)
                room[i][j] = 'L';

        DB.setROOM(room);
    }



}
