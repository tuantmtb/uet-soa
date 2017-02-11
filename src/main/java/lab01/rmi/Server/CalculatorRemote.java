package lab01.rmi.Server;

import lab01.rmi.Interface.ICalculator;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tuantmtb on 2/10/17.
 */
public class CalculatorRemote extends UnicastRemoteObject implements ICalculator {

    public CalculatorRemote() throws RemoteException {
        super();
    }

    /**
     * Method execute in server side
     *
     * @param lstArray
     * @return
     * @throws RemoteException
     */
    public synchronized List<Integer> findMinMax(List<Integer> lstArray) throws RemoteException {
        System.out.println("Calculating " + lstArray.toString());
        int min, max;
        List<Integer> lstOutput = new ArrayList<Integer>();
        if (lstArray != null && lstArray.size() > 0) {
            min = lstArray.get(0);
            max = lstArray.get(0);
            for (int e : lstArray) {
                if (min > e) {
                    min = e;
                }
                if (max < e) {
                    max = e;
                }
            }
            lstOutput.add(min);
            lstOutput.add(max);
        }
        return lstOutput;
    }
}
