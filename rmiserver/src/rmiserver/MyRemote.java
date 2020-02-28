package rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import interfaces.MyRemoteInterface;

public class MyRemote extends UnicastRemoteObject implements MyRemoteInterface {
	int sum = 0;

	protected MyRemote() throws RemoteException {
		super();
	}

	public void add(int d) throws RemoteException {
		sum += d;
	}

	public int getResult() throws RemoteException {
		return sum;
	}
}
