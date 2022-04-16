package rmiserver;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import interfaces.MyRemoteInterface;
import interfaces.Seat;

public class MyRemote extends UnicastRemoteObject implements MyRemoteInterface {
	List<Seat> availableSeats;

	String url = "jdbc:mysql://localhost:3306/lab3-sdm";
	String get_query = "SELECT * FROM seats;";

	protected MyRemote() throws RemoteException {
		super();
		fetchSeats();
	}

	private void fetchSeats() throws RemoteException {
		availableSeats = new ArrayList<>();
		try (
			Connection con = DriverManager.getConnection(url, "root", "root");
			PreparedStatement ps = con.prepareStatement(get_query);
		) {
			ResultSet rs = ps.executeQuery();

			Seat nextSeat = null;
			while (rs.next())
			{
				nextSeat = new Seat(rs.getInt("pos_row"), rs.getInt("pos_column"));
				availableSeats.add(nextSeat);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	String del_query = "DELETE FROM seats;";
	String ins_query = "INSERT INTO seats VALUES "; // append '(?,?,?),' for each seat, at end append ';'
	Random random = new Random();
	public void pushSeats(List<Seat> seats) throws RemoteException {
		availableSeats = seats;
		boolean isFirstSeat = true;
		for (Seat s : seats) {
			String ss = "";
			if (!isFirstSeat) ss += ',';
			else isFirstSeat = false;
			ss += String.format("(%s,%s,%s)", random.nextInt(1, Integer.MAX_VALUE), s.getRow(), s.getCol());
			ins_query += ss;
		}
		System.out.println(ins_query);
		try (
				Connection con = DriverManager.getConnection(url, "root", "root");
				PreparedStatement deleteStatement = con.prepareStatement(del_query);
				PreparedStatement insertStatement = con.prepareStatement(ins_query);
		) {
			deleteStatement.execute();
			insertStatement.execute(); // getResult might now be BONED
		} catch (SQLException e) {
			e.printStackTrace();
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
