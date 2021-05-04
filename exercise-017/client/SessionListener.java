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

package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * This class contains the Thread which updates the Client side.
 * @author Okan Atas, created on April 11, 2021
 * @version 1.0.0
 */
public class SessionListener extends Thread{
    /**
     * Thread to listen server side.
     */
    @Override
    public void run() {
        try {
            BufferedReader in;

            in = new BufferedReader(new InputStreamReader(
                    ProcessController.socket.getInputStream()));

            String line;

            while ((line = in.readLine()) != null) {

                String[] check = line.split(" : ", 2);

                if("exit".equalsIgnoreCase(check[1])){
                    ClientSideChatBox.textArea1.appendText("**** Session Ended ****" + "\n");
                    in.close();
                    break;
                }

                ClientSideChatBox.textArea1.appendText(line + "\n");
            }

            ProcessController.windowClosing();

        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

