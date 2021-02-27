package Server;


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
        this.isAlive = true;
        this.populate();
    }


    public void start() throws IOException {
        System.out.println("Server is running on port --> " + this.common.getPort());

        while (this.isAlive) {
            Socket socket = this.serverSocket.accept();
            System.out.println("New Connection " + socket.toString());
            Thread thread = new Thread(new Slave(socket));
            this.listOfThread.add(thread);
            System.out.println("list of threads ==> " + Arrays.toString(listOfThread.toArray()));
            thread.start();
            System.out.println("list of Bookings ==> " + Arrays.toString(DB.getBOOKINGS().toArray()));
            this.isAlive = !this.common.isError("no error");
        }
    }




    public static Server INSTANCE() throws IOException {
        if (INSTANCE == null)
            INSTANCE = new Server();

        return INSTANCE;
    }



    /**
     * clean room
     */
    private void populate(){
        for (int i = 0; i < DB.ROOM.length; i++)
            Arrays.fill(DB.ROOM[i], 'L');
    }

}
