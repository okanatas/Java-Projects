/**********************************************
 Workshop #10
 Course: JAC444 - Winter 2021
 Last Name: Atas
 First Name: Okan
 ID: 130627193
 Section: NFF
 This assignment represents my own work in accordance with Seneca Academic Policy.
 Signature: Okan Atas
 Date: April 11, 2021
 **********************************************/

package server;

import java.io.*;
import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * This class was created to control server flow.
 * @author Okan Atas, created on April 11, 2021
 * @version 1.0.0
 */
class Server {
    public static ServerSocket server = null;
    public static String actualMessage;

    public static PrintWriter out1;
    public static PrintWriter out2;

    /**
     * The first step to start the server.
     * @throws IOException exception
     */
    public static void startSession() throws IOException {

        server = new ServerSocket(4444);
        server.setReuseAddress(true);

        ServerGUI.textArea1.appendText("**** Server started ****" + "\n");

        allowClientLogin();
    }

    /**
     * This method allows new clients to login
     */
    public static void allowClientLogin() {

        ClientManager clientManager1 = new ClientManager(server, 1);
        ClientManager clientManager2 = new ClientManager(server, 2);

        clientManager1.start();
        clientManager2.start();

    }

    /**
     * This method controls the messages.
     * @param line actual message
     */
    public static void communicationManager(String line){
        // writing the received message from
        // client
        actualMessage = line;
        ServerGUI.textArea1.appendText(line + "\n");
        out1.println(line);
        out2.println(line);
    }

    /**
     * Date format
     * @return formatted date
     */
    public static String humanReadableDate(){
        SimpleDateFormat formatter= new SimpleDateFormat("EEEEE MMMMM yyyy HH:mm:ss.SSSZ");
        Date date = new Date(System.currentTimeMillis());
        return formatter.format(date);
    }
}

