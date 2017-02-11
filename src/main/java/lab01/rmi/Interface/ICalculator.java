package lab01.rmi.Interface;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Created by tuantmtb on 2/10/17.
 */
public interface ICalculator extends Remote {
    /**
     * Interface method findMinMax for Client and Server
     *
     * @param lstArray
     * @return
     * @throws RemoteException
     */
    List<Integer> findMinMax(List<Integer> lstArray) throws RemoteException;
}
