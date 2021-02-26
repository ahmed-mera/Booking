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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Common {



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
    public String cleanSpace(String value){ return value.trim(); }


}
