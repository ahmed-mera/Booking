package Client;

import Bean.Booking;
import Bean.BookingMoreSeats;
import Bean.Coordinate;
import Bean.Person;
import Common.Common;
import Constants.Constants;
import DB.DB;
import com.fasterxml.jackson.databind.ObjectMapper;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Client {

    private final Common common = new Common();
    private boolean isRunning;
    private final Socket socket;


    public Client() throws IOException {
        this.socket = new Socket(this.common.getHost(), this.common.getPort());
        this.isRunning = true;
    }





    public void start() {
        while (this.isRunning){
            try {
                System.out.print(Constants.MENU);
                String input = this.readData();


                switch (this.common.cleanSpace(input)) {
                    case "1" -> {
                        if(!this.common.checkTime(27, 19, 30, 0)){
                            this.showSeats();
                            this.common.sendData(this.socket, input);
                            this.common.sendData(this.socket, new ObjectMapper().writeValueAsString(this.book())); // booking a seat
                            String result = this.common.readData(this.socket);
                            System.out.println("\n" + result);
                            this.isRunning = !this.common.isError(result);
                        }else{
                            System.out.println("tempo scaduto per la prenotazione");
                        }

                    }

                    case "2" -> {
                        System.out.println("\n\t\tO --> Busy \t L --> Free");
                        this.showSeats();
                    }

                    case "3" -> {

                        if (this.common.checkTime(22, 9, 0, 0)) {
                            this.showSeats();

                            BookingMoreSeats bookingMoreSeats = this.bookMoreSeat();

                            if (bookingMoreSeats != null) {
                                this.common.sendData(this.socket, input);
                                this.common.sendData(this.socket, new ObjectMapper().writeValueAsString(bookingMoreSeats)); // booking a seat
                                String result = this.common.readData(this.socket);
                                System.out.println(result);
                                this.isRunning = !this.common.isError(result);
                            } else
                                System.out.println(Constants.BOOKING_FAIL + " Oppure seats must be  (5 > seats > 1) ");
                        }else {
                            System.out.println("non puoi prenotare, l'aspettacelo inizia Lunedì 22 Febbraio alle 9:00 ");
                        }

                    }

                    case "4" -> {
                        this.common.sendData(this.socket, input);
                        System.out.println(this.common.readData(this.socket).replace("/n", "\n").replace("/t", "\t"));
                    }

                    case "0" -> {
                        this.common.sendData(this.socket, input);
                        System.out.println("Goodbye, see you later :)");
                        this.isRunning = false;
                        this.socket.close();
                    }

                    default -> System.out.println(Constants.ERROE_MSG);
                }

            }catch (IOException error){
                System.out.println("qualcosa è andata storo --> " + error.getMessage());
            }

            System.out.println("\n\n");
        }
    }



    /**
     * function to book
     * @return {@link Booking}
     * @throws IOException
     */
    public Booking book() throws IOException {
        System.out.print(Constants.ROW);
        int row = Integer.parseInt(this.common.cleanSpace(this.readData()));

        System.out.print(Constants.COLUMN);
        int column = Integer.parseInt(this.common.cleanSpace(this.readData()));

        return new Booking(this.getDataOfUser(), new Coordinate(row - 1, column - 1));
    }


    /**
     * function to booking more seats
     * per il livello 2
     */
    private BookingMoreSeats bookMoreSeat() throws IOException {
        System.out.print(Constants.MORE_SEATS);
        int seats = Integer.parseInt(this.common.cleanSpace(this.readData()));

        if (seats <= DB.FREE_SEATS && seats > 1 && seats <= 5) {
            ArrayList<Coordinate> coordinates = new ArrayList<>();
            for (int i = 0; i < seats; i++) {
                System.out.print(Constants.ROW);
                int row = Integer.parseInt(this.common.cleanSpace(this.readData()));

                System.out.print(Constants.COLUMN);
                int column = Integer.parseInt(this.common.cleanSpace(this.readData()));

                coordinates.add(new Coordinate(row - 1, column - 1));
            }

            return new BookingMoreSeats(this.getDataOfUser(), coordinates);
        }
        return null;
    }


    /**
     * function to getDataOfUser
     * @return {@link Person}
     * @throws IOException
     */
    private Person getDataOfUser() throws IOException {

        System.out.print(Constants.FIRST_NAME);
        String name = this.common.cleanSpace(this.readData());

        System.out.print(Constants.LAST_NAME);
        String surname = this.common.cleanSpace(this.readData());

        System.out.print(Constants.TEL);
        String tel = this.common.cleanSpace(this.readData());


        return new Person(name, surname, tel);
    }




    private String readData() throws IOException {
        return (new BufferedReader( new InputStreamReader( System.in ))).readLine();
    }



    private void showSeats() throws IOException {
        this.common.sendData(this.socket, "2");
        System.out.println(this.common.readData(this.socket)
                .replace("/n", "\n").replace("/t", "\t"));
    }


//    public void checkTime(Runnable command){
//        Executors
//                .newScheduledThreadPool(1)
//                    .scheduleAtFixedRate(command, 0, 1, TimeUnit.SECONDS);
//
//    }
}
