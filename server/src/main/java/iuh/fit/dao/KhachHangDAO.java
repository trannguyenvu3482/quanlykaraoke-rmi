package iuh.fit.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import iuh.fit.entity.KhachHang;



public interface KhachHangDAO extends Remote{

	public boolean addKhachHang(KhachHang kh)  throws RemoteException;

	public List<KhachHang> getAllKhachHangs() throws RemoteException ;

	public KhachHang getKhachHang(String maKH) throws RemoteException ;

	public KhachHang getKhachHangBySDT(String sdt) throws RemoteException ;

	public KhachHang updateKhachHang(KhachHang kh) throws RemoteException; 
}
