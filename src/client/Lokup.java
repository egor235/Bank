package client;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Created by admin on 05.07.17.
 */
interface Lookup extends Remote {
    public String findInfo(String info) throws RemoteException;
}