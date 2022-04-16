package rmiclient;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import interfaces.MyRemoteInterface;
import interfaces.Seat;

public class ClientMain {

	public static void main(String[] args) throws RemoteException, NotBoundException, InterruptedException {

		Registry registry = LocateRegistry.getRegistry(7500);
		for (String s : registry.list()) {
			System.out.println(s);
		}
		MyRemoteInterface remote = (MyRemoteInterface) registry.lookup("object");

		// The client tries to reserve seats (1, 0), (1, 1), ..., (1, 9)
		for (int i = 0; i < 10; i++) {
			Seat seatWanted = new Seat(1, i);
			boolean reservationSuccess = remote.reserveSeat(seatWanted);
			System.out.println((reservationSuccess ? "Success " : "Failure ") + "for seat (1, " + i + ")!");
			Thread.sleep(200);
		}
	}
}
