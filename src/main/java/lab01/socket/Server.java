package lab01.socket;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tranvietthang on 2/10/17.
 * Server class
 */
public class Server {
    private ServerSocket serverSocket;

    /**
     * Create a server socket on specified port
     *
     * @param port port to connect
     * @throws IOException exception while creating connection
     */
    public Server(int port) throws IOException {
        serverSocket = new ServerSocket(port);
    }

    /**
     * Start listening from clients
     */
    public void listen() {
        System.out.println("Waiting for client on port " +
                serverSocket.getLocalPort() + "...");

        while (true) {
            try {
                Socket server = serverSocket.accept();

                // Start a new thread for not blocking other incoming connections
                Thread thread = new Thread(() -> {
                    try {
                        DataInputStream in = new DataInputStream(server.getInputStream());
                        DataOutputStream out = new DataOutputStream(server.getOutputStream());
                        List<Integer> numbers = new ArrayList<>();

                        while (true) {
                            String str = in.readUTF();

                            if (str.equals(".end")) {
                                // End of array
                                int min = Integer.MAX_VALUE;
                                int max = Integer.MIN_VALUE;

                                for (int number : numbers) {
                                    if (number >= max) {
                                        max = number;
                                    }

                                    if (number <= min) {
                                        min = number;
                                    }
                                }

                                out.writeUTF("[" + min + ", " + max + "]");
                                numbers = new ArrayList<>();
                            } else {
                                // Add new number to array
                                try {
                                    int number = Integer.parseInt(str);
                                    numbers.add(number);
                                } catch (Exception e) {
                                    System.out.println("Can't parse integer!");
                                }
                            }
                        }
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                });

                thread.start();

            } catch (SocketTimeoutException s) {
                System.out.println("Socket timed out!");
                break;
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }

    }

    public static void main(String[] args) {
//        int port = Integer.parseInt(args[0]);
        int port = 2105;
        try {
            Server server = new Server(port);
            server.listen();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
