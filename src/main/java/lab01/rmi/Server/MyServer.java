package lab01.rmi.Server;

import lab01.rmi.Interface.ICalculator;
import lab01.rmi.Server.CalculatorRemote;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by tuantmtb on 2/10/17.
 */
public class MyServer {
    private static final int PORT = 1099;
    private static Registry registry;

    public static void startRegistry() throws RemoteException {
        // (Registry) in Server.
        registry = LocateRegistry.createRegistry(PORT);
    }

    public static void registerObject(String name, Remote remoteObj) throws RemoteException, AlreadyBoundException {

        registry.bind(name, remoteObj);
        System.out.println("Registered: " + name + " -> " + remoteObj.getClass().getName());
    }

    public static void main(String[] args) {
        try {
            System.out.println("Server starting...");
            startRegistry();
            registerObject(ICalculator.class.getSimpleName(), new CalculatorRemote());

            // Server started, and wait request from client side
            System.out.println("Server started!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
