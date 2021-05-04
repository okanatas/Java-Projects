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

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * This class contains the Thread which updates the Server side by controlling clients.
 * @author Okan Atas, created on April 11, 2021
 * @version 1.0.0
 */
public class ClientManager extends Thread {
    private ServerSocket server = null;
    private final int i;

    /**
     * Server thread constructor.
     * @param serverSocket server socket
     * @param i client number
     */
    public ClientManager(ServerSocket serverSocket, int i)
    {
        this.server = serverSocket;
        this.i = i;
    }

    /**
     * Thread to manage each client and aware them updated messages.
     */
    @Override
    public void run() {
        try {
            Socket client = server.accept();

            System.out.println("New client connected "
                    + "[addr: " + client.getInetAddress()
                    .getHostAddress() + " , port: " + client.getPort() + " , localport: " + client.getLocalPort()
                    + "] at " + Server.humanReadableDate());

            ServerGUI.textArea1.appendText("New client connected "
                    + "[addr: " + client.getInetAddress()
                    .getHostAddress() + " , port: " + client.getPort() + " , localport: " + client.getLocalPort()
                    + "] at " + Server.humanReadableDate() + "\n");

            BufferedReader in = null;

            // get the output stream of client
            if(i == 1){
                Server.out1 = new PrintWriter(
                        client.getOutputStream(), true);
            }else{
                Server.out2 = new PrintWriter(
                        client.getOutputStream(), true);
            }


            // get the input stream of client
            in = new BufferedReader(
                    new InputStreamReader(
                            client.getInputStream()));


            String line;

            while ((line = in.readLine()) != null) {

                String[] check = line.split(" : ", 2);

                Server.communicationManager(line);

                if("exit".equalsIgnoreCase(check[1])){
                    ServerGUI.textArea1.appendText("**** Session Ended ****" + "\n");
                    ServerGUI.textArea1.appendText( "**** Server still working , ready to new connection ****" + "\n");
                    client.close();
                    in.close();

                    break;
                }
            }

            Server.allowClientLogin();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
