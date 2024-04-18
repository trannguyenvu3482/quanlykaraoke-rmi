package iuh.fit.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import iuh.fit.entity.LoaiHangHoa;

public interface LoaiHangHoaDAO  extends Remote{

	public boolean addLoaiHangHoa(LoaiHangHoa lhh) throws RemoteException;
	
	public List<LoaiHangHoa> getAllLoaiHangHoas() throws RemoteException;
	
	public LoaiHangHoa getLoaiHangHoaByname(String tenLHH) throws RemoteException;
}
