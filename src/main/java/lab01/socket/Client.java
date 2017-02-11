package lab01.socket;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Created by tranvietthang on 2/10/17.
 * Client class
 */
public class Client {
    public static void main(String[] args) {
//        String serverName = args[0];
        String serverName = "localhost";
//        int port = Integer.parseInt(args[1]);
        int port = 2105;
        try {
            System.out.println("Connecting to " + serverName + " on port " + port);
            Socket client = new Socket(serverName, port);

            System.out.println("Just connected to " + client.getRemoteSocketAddress());

            try {
                DataOutputStream out = new DataOutputStream(client.getOutputStream());
                DataInputStream in = new DataInputStream(client.getInputStream());

                while (true) {

                    System.out.println("Enter a number, finish the array by typing \".end\" and wait for result");
                    Scanner reader = new Scanner(System.in);
                    String inputString = reader.next();

                    // Exit app
                    if (inputString.equals(".exit")) {
                        System.out.println("Closing connection...");
                        client.close();
                        break;
                    }

                    // End of array
                    if (inputString.equals(".end")) {
                        out.writeUTF(".end");
                        String str = in.readUTF();
                        System.out.println("Result: " + str);
                        continue;
                    }

                    try {
                        Integer number = Integer.parseInt(inputString);
                        out.writeUTF(number.toString());
                    } catch (Exception e) {
                        System.out.println("Can't parse integer!");
                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
