package iuh.fit.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import iuh.fit.entity.ChiTietPhieuDatPhong;


public interface ChiTietPhieuDatPhongDAO extends Remote {
	public boolean addChiTietPhieuDatPhong(ChiTietPhieuDatPhong chiTietPhieuDatPhong) throws RemoteException;

	public ChiTietPhieuDatPhong getChiTietPhieuDatPhongByActiveMaPhong(String maPhong) throws RemoteException;

	public List<ChiTietPhieuDatPhong> getAllChiTietPhieuDatPhongByMaPhieuDatPhong(String maPhieuDatPhong) throws RemoteException;
	
	public List<ChiTietPhieuDatPhong> getAllChiTietPhieuDatPhongs() throws RemoteException;
}
