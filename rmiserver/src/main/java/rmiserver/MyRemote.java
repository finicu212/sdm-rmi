package rmiserver;

import java.io.File;
import java.io.IOException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import interfaces.MyRemoteInterface;
import interfaces.Seat;

public class MyRemote extends UnicastRemoteObject implements MyRemoteInterface {
	ArrayList<Seat> availableSeats;

	String url = "jdbc:mysql://localhost:3306/lab3-sdm";
	String get_query = "SELECT * FROM seats;";

	protected MyRemote() throws RemoteException {
		super();
		fetchSeats();
	}

	private void fetchSeats() throws RemoteException {
		try {
			File file = new File("db.csv");
			Scanner myReader = new Scanner(file);
			availableSeats = new ArrayList<>();

			while (myReader.hasNextLine()) {
				String line = myReader.nextLine();
				String[] coords = line.split(",");
				Seat newSeat = new Seat(Integer.parseInt(coords[0]), Integer.parseInt(coords[1]));
				System.out.println("Read a seat: " + newSeat);
				availableSeats.add(newSeat);
			}
			myReader.close();

			System.out.println("Fetched from DB:");
			for (Seat s : availableSeats)
			{
				System.out.println(s);
			}

		} catch (IOException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}

		System.out.println();
	}

	private void pushSeats(List<Seat> seats) throws RemoteException {
		for (Seat s : seats) {
			String ss = s.toString();
		}
	}


	@Override
	public List<Seat> availableSeats() throws RemoteException {
		return availableSeats;
	}

	@Override
	public boolean reserveSeat(Seat s) throws RemoteException {
		boolean removed_flag = availableSeats.remove(s);
		if (removed_flag) pushSeats(availableSeats);

		return removed_flag;
	}
}
