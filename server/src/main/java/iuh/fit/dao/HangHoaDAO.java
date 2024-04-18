package iuh.fit.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import iuh.fit.entity.HangHoa;



public interface HangHoaDAO extends Remote {
	

	public boolean addHangHoa(HangHoa hh) throws RemoteException ;

	public List<HangHoa> getAllHangHoas()  throws RemoteException;

	public HangHoa getHangHoa(String maHH) throws RemoteException;

	public boolean updateHangHoa(HangHoa hh) throws RemoteException;

	public boolean updateSoLuongTon(HangHoa hh, int sl) throws RemoteException;
	
}
