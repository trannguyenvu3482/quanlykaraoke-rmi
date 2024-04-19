package iuh.fit.dao;

import java.util.List;
import java.rmi.Remote;
import java.rmi.RemoteException;

import iuh.fit.entity.LoaiPhong;

public interface LoaiPhongDAO extends Remote{
	
	public boolean addLoaiPhong(LoaiPhong lp) throws RemoteException;
	public List<LoaiPhong> getAllLoaiPhongs() throws RemoteException;
	
	public LoaiPhong getLoaiPhong(String tenLP, int kt) throws RemoteException;
}
