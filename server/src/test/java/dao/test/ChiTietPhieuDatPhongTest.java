package dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.ChiTietPhieuDatPhongDAO;
import iuh.fit.dao.impl.ChiTietPhieuDatPhongImpl;
import iuh.fit.entity.ChiTietPhieuDatPhong;
import iuh.fit.util.HibernateUtil;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChiTietPhieuDatPhongTest {
	static ChiTietPhieuDatPhongDAO chiTietPhieuDatPhongDAO;

	@BeforeAll
	public static void init() throws RemoteException {
		HibernateUtil.provideSessionFactory();

		chiTietPhieuDatPhongDAO = new ChiTietPhieuDatPhongImpl();
	}

	@Test
	void addChiTietPhieuDatPhong() throws RemoteException {
		ChiTietPhieuDatPhong chiTietPhieuDatPhong = new ChiTietPhieuDatPhong();
		boolean result = chiTietPhieuDatPhongDAO.addChiTietPhieuDatPhong(chiTietPhieuDatPhong);
		chiTietPhieuDatPhongDAO.addChiTietPhieuDatPhong(chiTietPhieuDatPhong);
		assertEquals(true, result);
	}

	@Test
	void getAllChiTietPhieuDatPhongByMaPhieuDatPhong() throws RemoteException {
		ChiTietPhieuDatPhong chiTietPhieuDatPhong = new ChiTietPhieuDatPhong();
		chiTietPhieuDatPhongDAO.addChiTietPhieuDatPhong(chiTietPhieuDatPhong);
		assertEquals(1, chiTietPhieuDatPhongDAO.getAllChiTietPhieuDatPhongByMaPhieuDatPhong("PD001").size());
	}

	@Test
	void getAllChiTietPhieuDatPhongs() throws RemoteException {
		ChiTietPhieuDatPhong chiTietPhieuDatPhong = new ChiTietPhieuDatPhong();
		chiTietPhieuDatPhongDAO.addChiTietPhieuDatPhong(chiTietPhieuDatPhong);
		assertEquals(1, chiTietPhieuDatPhongDAO.getAllChiTietPhieuDatPhongs().size());
	}

}
