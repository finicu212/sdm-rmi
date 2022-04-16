package rmiserver;

import interfaces.Seat;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;

public class ServerMain {

	public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {

		MyRemote myr = new MyRemote();

		for(Seat s : myr.availableSeats()) {
			System.out.println("read seat " + s + " from db");
		}

//		0, 0, 0
//		1, 0, 2
//		2, 1, 3
//		3, 1, 4
//		4, 1, 5
//		5, 1, 6
//		6, 1, 7
//		7, 4, 7
//		8, 8, 8
//		9, 1, 2

		List<Seat> seats = new ArrayList<>();
		seats.add(new Seat(1, 1));
		seats.add(new Seat(1, 2));
		seats.add(new Seat(1, 4));
		seats.add(new Seat(1, 5));
		seats.add(new Seat(3, 6));
		seats.add(new Seat(4, 7));
		seats.add(new Seat(8, 7));
		seats.add(new Seat(9, 7));
		seats.add(new Seat(0, 14));
		myr.pushSeats(seats);

		Registry registry = LocateRegistry.createRegistry(7500);
		registry.bind("object", myr);
	}
}
