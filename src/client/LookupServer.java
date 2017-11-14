package client;



import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.Naming;
import java.rmi.RMISecurityManager;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;

public class LookupServer extends UnicastRemoteObject implements Lookup {
    private Vector save = new Vector();

    public LookupServer(String db) throws RemoteException
    {
        try {
            FileReader fr = new FileReader(db);
            BufferedReader br = new BufferedReader(fr);
            String s = null;
            while ((s = br.readLine()) != null)
                save.addElement(s);
            fr.close();
        }
        catch (Throwable e) {
            System.err.println("exception");
            System.exit(1);
        }
    }

    public String findInfo(String info)
    {
        if (info == null)
            return null;

        info = info.toLowerCase();
        int n = save.size();
        for (int i = 0; i < n; i++) {
            String dbs = (String)save.elementAt(i);
            if (dbs.toLowerCase().indexOf(info) != -1)
                return dbs;
        }

        return null;
    }

    public static void main(String args[])
    {
        try {
            RMISecurityManager security =
                    new RMISecurityManager();
            System.setSecurityManager(security);
            String db = args[0];
            LookupServer server = new LookupServer(db);
            Naming.rebind("LookupServer", server);
            System.err.println("LookupServer ready...");
        }
        catch (Throwable e) {
            System.err.println("exception: " + e);
            System.exit(1);
        }
    }
}
