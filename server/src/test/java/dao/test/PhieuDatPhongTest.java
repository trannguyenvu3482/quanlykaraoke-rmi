package dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.KhachHangDAO;
import iuh.fit.dao.NhanVienDAO;
import iuh.fit.dao.PhieuDatPhongDAO;
import iuh.fit.dao.PhongDAO;
import iuh.fit.dao.impl.KhachHangImpl;
import iuh.fit.dao.impl.NhanVienImpl;
import iuh.fit.dao.impl.PhieuDatPhongImpl;
import iuh.fit.dao.impl.PhongImpl;
import iuh.fit.entity.PhieuDatPhong;
import iuh.fit.entity.Phong;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PhieuDatPhongTest {
	static PhieuDatPhongDAO phieuDatPhongDAO;
	static NhanVienDAO nhanVienDAO;
	static KhachHangDAO khachHangDAO;
	static PhongDAO phongDAO;
	
	@BeforeAll
	public static void init() throws RemoteException {
		phieuDatPhongDAO = new PhieuDatPhongImpl();
		nhanVienDAO = new NhanVienImpl();
		khachHangDAO = new KhachHangImpl();
		phongDAO = new PhongImpl();
	}
	
	@Test
	void addPhieuDatPhong() throws RemoteException {
		PhieuDatPhong phieuDatPhong = new PhieuDatPhong("", nhanVienDAO.getNhanVien("NV001"), false, khachHangDAO.getKhachHang("KH001"));
		Phong phong = phongDAO.getPhong("P001");
		
		boolean result = phieuDatPhongDAO.addPhieuDatPhong(phieuDatPhong, phong);
		assertEquals(true, result);
	}
	@Test
	void getAllPhieuDatPhongs() throws RemoteException {
		List<PhieuDatPhong> list = phieuDatPhongDAO.getAllPhieuDatPhongs();
		assertEquals(1, list.size());
	}
	
	@Test
	void getAllPhieuDatPhongFromDateByNhanVien() throws RemoteException {
		LocalDateTime from = LocalDateTime.of(2021, 5, 1, 0, 0);
		LocalDateTime to = LocalDateTime.of(2021, 5, 31, 0, 0);
		List<PhieuDatPhong> list = phieuDatPhongDAO.getAllPhieuDatPhongFromDateByNhanVien("NV001", from, to);
		assertEquals(1, list.size());
	}
	@Test
	void getAllPhieuDatPhongFromDateByKhachHang() throws RemoteException {
		LocalDateTime from = LocalDateTime.of(2021, 5, 1, 0, 0);
		LocalDateTime to = LocalDateTime.of(2021, 5, 31, 0, 0);
		List<PhieuDatPhong> list = phieuDatPhongDAO.getAllPhieuDatPhongFromDateByKhachHang("KH001", from, to);
		assertEquals(1, list.size());
	}
	@Test
	void getAllPhieuDatPhongByMonth() throws RemoteException {
        List<PhieuDatPhong> list = phieuDatPhongDAO.getAllPhieuDatPhongByMonth(5);
        assertEquals(1, list.size());
    }
	@Test
	void getAllPhieuDatPhongByYear() throws RemoteException {
		List<PhieuDatPhong> list = phieuDatPhongDAO.getAllPhieuDatPhongByYear(2021);
		assertEquals(1, list.size());
	}
	@Test
	void getAllPhieuDatPhongByYearByNhanVien() throws RemoteException {
		List<PhieuDatPhong> list = phieuDatPhongDAO.getAllPhieuDatPhongByYearByNhanVien("NV001", 2021);
		assertEquals(1, list.size());
	}
	@Test
	void getAllPhieuDatPhongByYearByKhachHang() throws RemoteException {
		List<PhieuDatPhong> list = phieuDatPhongDAO.getAllPhieuDatPhongByYearByKhachHang("KH001", 2021);
		assertEquals(1, list.size());
	}
	@Test
	void getAllPhieuDatPhongByMonthByNhanVien() throws RemoteException {
		List<PhieuDatPhong> list = phieuDatPhongDAO.getAllPhieuDatPhongByMonthByNhanVien("NV001", 5);
		assertEquals(1, list.size());
	}
	@Test
	void getAllPhieuDatPhongByMonthByKhachHang() throws RemoteException {
		List<PhieuDatPhong> list = phieuDatPhongDAO.getAllPhieuDatPhongByMonthByKhachHang("KH001", 5);
		assertEquals(1, list.size());
	}
	@AfterAll
	public static void cleanup() {
		phieuDatPhongDAO = null;
	}
}
