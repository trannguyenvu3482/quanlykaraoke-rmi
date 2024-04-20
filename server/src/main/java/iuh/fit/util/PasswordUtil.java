package iuh.fit.util;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import org.mindrot.jbcrypt.BCrypt;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Trần Thanh Vy
 * @version 1.0
 * @created 10-Oct-2023 15:20:00
 */
public class PasswordUtil extends UnicastRemoteObject implements PasswordService {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordUtil() throws RemoteException {
		super();
	}

	public String encrypt(String originalPassword) {
		return BCrypt.hashpw(originalPassword, BCrypt.gensalt(12));
	}

	public boolean check(String checkPW, String hashedPW) {
		return BCrypt.checkpw(checkPW, hashedPW);
	}
}
