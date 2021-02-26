package Client;

import Bean.Booking;
import Bean.Person;
import Common.Common;
import Constants.Constants;
import DB.DB;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Client {

    private final Common common = new Common();


    /**
     * function to book
     * @return {@link Booking}
     * @throws IOException
     */
    public Booking book () throws IOException {
        System.out.print(Constants.ROW);
        int row = Integer.parseInt(this.common.cleanSpace(this.readData()));

        System.out.print(Constants.COLUMN);
        int column = Integer.parseInt(this.common.cleanSpace(this.readData()));

        return DB.book(row, column) ?  new Booking(this.getDataOfUser(), row, column) : null;
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


}
