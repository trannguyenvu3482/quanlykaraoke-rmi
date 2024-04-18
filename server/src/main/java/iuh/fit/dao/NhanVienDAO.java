/**
 * 
 */
package iuh.fit.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import iuh.fit.entity.NhanVien;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 18 Apr 2024 - 4:27:59 pm
 */
public interface NhanVienDAO extends Remote {
	public boolean addNhanVien(NhanVien nv) throws RemoteException;

	public NhanVien getNhanVien(String maNV) throws RemoteException;

	public NhanVien getNhanVienBySDT(String sdt) throws RemoteException;

	public List<NhanVien> getAllNhanViens() throws RemoteException;

	public boolean updateMatKhauNhanVien(NhanVien nv) throws RemoteException;

	public NhanVien updateNV(NhanVien nv) throws RemoteException;

	public List<NhanVien> getNhanViensByDOB(String dateFrom, String dateTo) throws RemoteException;
}