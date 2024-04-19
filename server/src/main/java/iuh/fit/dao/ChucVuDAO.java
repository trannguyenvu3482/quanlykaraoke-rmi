package iuh.fit.dao;


import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import iuh.fit.entity.ChucVu;

public interface ChucVuDAO extends Remote {
	public ChucVu getChucVu(String maChucVu) throws RemoteException;

	public List<ChucVu> getAllChucVus() throws RemoteException;

	public ChucVu getChucVuByName(String name) throws RemoteException;
}
