package iuh.fit.util;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface PasswordService extends Remote {
	public String encrypt(String originalPassword) throws RemoteException;

	public boolean check(String checkPW, String hashedPW) throws RemoteException;
}
