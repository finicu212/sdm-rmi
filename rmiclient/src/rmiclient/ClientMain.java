package rmiclient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.MyRemoteInterface;

public class ClientMain {

	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {

		Registry registry = LocateRegistry.getRegistry(7500);
		for (String s : registry.list()) {
			System.out.println(s);
		}
		MyRemoteInterface remote = (MyRemoteInterface) registry.lookup("object");
		for (int i = 0; i < 10; i++) {
			remote.add(15);
			System.out.println(remote.getResult());
			Thread.sleep(1500);
		}
	}
}
