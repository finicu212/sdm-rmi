package rmiserver;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ServerMain {

	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {

		MyRemote myr = new MyRemote();
		Registry registry = LocateRegistry.createRegistry(7500);
		registry.bind("object", myr);
	}
}
