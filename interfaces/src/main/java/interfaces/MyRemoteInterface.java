package interfaces;

import java.io.FileWriter;
import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

public interface MyRemoteInterface extends Remote {

	List<Seat> availableSeats() throws RemoteException; // returns the seats that are available

	boolean reserveSeat(Seat s) throws RemoteException; // returns true if the seat was reserved. Seat is no longer available
}
