package iuh.fit.dao;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

import iuh.fit.entity.PhieuDatPhong;
import iuh.fit.entity.Phong;


public interface PhieuDatPhongDAO extends Remote{
	public boolean addPhieuDatPhong(PhieuDatPhong pdp, Phong p) throws RemoteException;

	public List<PhieuDatPhong> getAllPhieuDatPhongs() throws RemoteException;

	public boolean finishPhieuDatPhong(String maPhong, double tienDichVu, double tienPhong) throws RemoteException;

	public List<PhieuDatPhong> getAllPhieuDatPhongFromDate(LocalDateTime fromDate, LocalDateTime toDate) throws RemoteException;

	public List<PhieuDatPhong> getAllPhieuDatPhongFromDateByNhanVien(String maNV, LocalDateTime fromDate,
			LocalDateTime toDate) throws RemoteException;

	public List<PhieuDatPhong> getAllPhieuDatPhongFromDateByKhachHang(String maKH, LocalDateTime fromDate,
			LocalDateTime toDate) throws RemoteException;

	public List<PhieuDatPhong> getAllPhieuDatPhongByMonth(int month) throws RemoteException;

	public List<PhieuDatPhong> getAllPhieuDatPhongByMonthByNhanVien(String maNV, int month) throws RemoteException;

	public List<PhieuDatPhong> getAllPhieuDatPhongByMonthByKhachHang(String maKH, int month) throws RemoteException;

	public List<PhieuDatPhong> getAllPhieuDatPhongByYear(int year) throws RemoteException;

	public List<PhieuDatPhong> getAllPhieuDatPhongByYearByNhanVien(String maNV, int year) throws RemoteException;

	public List<PhieuDatPhong> getAllPhieuDatPhongByYearByKhachHang(String maKH, int year) throws RemoteException;

//	public boolean changeRoomForPhieuDatPhong(String currentMaPhong, String moveToMaPhong) {
//	ChiTietPhieuDatPhong ctpdp = ctpdpDAO.getChiTietPhieuDatPhongByActiveMaPhong(currentMaPhong);
//	Phong p = pDAO.getPhong(moveToMaPhong);
//
//	if (ctpdpDAO.addChiTietPhieuDatPhong(
//			new ChiTietPhieuDatPhong(p, ctpdp.getPhieuDatPhong(), LocalDateTime.now(), null))) {
//		return pdpDAO.finishPhieuDatPhong(currentMaPhong, 0, 0);
//	}
//
//	return false;
//}
}
