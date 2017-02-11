package lab01.rmi.Client;

import lab01.rmi.Interface.ICalculator;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by tuantmtb on 2/10/17.
 */
public class MyClient {
    // Host or IP of Server
    private static final String HOST = "localhost";
    private static final int PORT = 1099;
    private static Registry registry;

    public static void main(String[] args) {

        try {
            // Search the registry Host, Port.
            registry = LocateRegistry.getRegistry(HOST, PORT);
            ICalculator calculator = (ICalculator) registry.lookup(ICalculator.class.getSimpleName());

            while (true) {
                // scanner input
                System.out.println("Enter list integer (ex: -32,3,4,5,6): ");
                Scanner reader = new Scanner(System.in);
                String inputString = reader.next();
                List<String> lstString = Arrays.asList(inputString.split("\\s*,\\s*"));

                // cast to integer
                List<Integer> lstInput = new ArrayList<Integer>();
                for (String s : lstString) lstInput.add(Integer.valueOf(s));

                System.out.println("input: " + lstInput);
                // remote method invoke
                System.out.println("output: " + calculator.findMinMax(lstInput));
                System.out.println("=======END======");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

