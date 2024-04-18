/**
 * 
 */
package iuh.fit.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import iuh.fit.entity.Phong;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 18 Apr 2024 - 1:35:04 pm
 */
public interface PhongDAO extends Remote {
	public boolean addPhong(Phong p) throws RemoteException;

	public List<Phong> getAllPhongs() throws RemoteException;

	public List<Phong> getAllEmptyPhongs() throws RemoteException;

	public boolean isRoomEmpty(Phong p) throws RemoteException;

	public boolean updatePhong(Phong p) throws RemoteException;
}
