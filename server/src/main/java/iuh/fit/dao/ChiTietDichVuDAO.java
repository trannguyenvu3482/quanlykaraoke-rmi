package iuh.fit.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import iuh.fit.entity.ChiTietDichVu;


public interface ChiTietDichVuDAO extends Remote {
	public boolean addChiTietDichVu(ChiTietDichVu chiTietDichVu) throws RemoteException;

	public List<ChiTietDichVu> getChiTietDichVuByMaPDPAndMaPhong(String maPDP, String maPhong) throws RemoteException;
	

	public double getTongTienDichVuByMaPDP(String maPDP) throws RemoteException;

	public List<ChiTietDichVu> getAllChiTietDichVus() throws RemoteException;

	public ChiTietDichVu updateChiTietDichVu(ChiTietDichVu chiTietDichVu) throws RemoteException;

	public boolean deleteChiTietDichVu(ChiTietDichVu chiTietDichVu) throws RemoteException;
}
