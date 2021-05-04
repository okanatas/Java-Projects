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

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.TimeUnit;

/**
 * This class controls server utility methods of Client side.
 * @author Okan Atas, created on April 11, 2021
 * @version 1.0.0
 */
public class ProcessController {

    /** Socket for client */
    public static Socket socket;

    private static PrintWriter out;

    /**
     * This is the first step in creating the method client socket.
     * @throws IOException exception
     */
    public static void startClient() throws IOException {
        socket = new Socket("localhost", 4444);

        out = new PrintWriter(
                ProcessController.socket.getOutputStream(), true);
    }

    /**
     * This method sends client messages to server side.
     * @throws IOException exception
     */
    public static void messageFlowController() throws IOException {
        String text = ClientSideChatBox.textArea2.getText();

        out.println(ClientSideChatBox.clientName + " : " + text);
        out.flush();

        ClientSideChatBox.textArea2.setText("");
        ClientSideChatBox.textArea2.requestFocus();

    }

    /**
     * This method starts the thread that keeps the client side updated.
     */
    public static void startListening(){
        SessionListener sessionListener = new SessionListener();
        sessionListener.start();
    }

    /**
     * This method closes client windows.
     * @throws IOException exception
     * @throws InterruptedException exception
     */
    public static void windowClosing() throws IOException, InterruptedException {
        TimeUnit.SECONDS.sleep(1);
        ClientSideChatBox.textArea1.appendText("Window closing ");

        TimeUnit.SECONDS.sleep(1);
        ClientSideChatBox.textArea1.appendText(".");

        TimeUnit.SECONDS.sleep(1);
        ClientSideChatBox.textArea1.appendText(".");

        TimeUnit.SECONDS.sleep(1);
        ClientSideChatBox.textArea1.appendText(".");

        TimeUnit.SECONDS.sleep(1);
        socket.close();
        out.close();

        System.exit(0);

    }

}
