package dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.LoaiPhongDAO;
import iuh.fit.dao.PhongDAO;
import iuh.fit.dao.impl.LoaiPhongImpl;
import iuh.fit.dao.impl.PhongImpl;
import iuh.fit.entity.Phong;
import iuh.fit.util.HibernateUtil;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PhongTest {
	static PhongDAO phongDAO;
	static LoaiPhongDAO loaiPhongDAO;

	@BeforeAll
	public static void init() throws RemoteException {
		HibernateUtil.provideSessionFactory();
		phongDAO = new PhongImpl();
		loaiPhongDAO = new LoaiPhongImpl();
	}

	@Test
	void testAddPhong() throws RemoteException {
		Phong phong = new Phong("", loaiPhongDAO.getLoaiPhong("Phòng thường", 5), true);
		boolean result = phongDAO.addPhong(phong);

		assertEquals(true, result);
	}

	@Test
	void testGetAllPhongs() throws RemoteException {
		List<Phong> listPhong = phongDAO.getAllPhongs();

		for (Phong phong : listPhong) {
			System.out.println(phong.getMaPhong());
		}

		assertNotNull(listPhong);
	}

	@Test
	void testUpdatePhong() throws RemoteException {
		Phong phong = phongDAO.getPhong("P001");
		phong.setTrangThai(true);
		boolean result = phongDAO.updatePhong(phong);

		assertEquals(true, result);
		Phong updatedPhong = phongDAO.getPhong("P001");
		assertEquals(true, updatedPhong.isTrangThai());
	}

	@Test
	void testGetAllEmptyPhongs() throws RemoteException {
		List<Phong> listPhong = phongDAO.getAllEmptyPhongs();

		for (Phong phong : listPhong) {
			System.out.println(phong.getMaPhong());
		}

		assertNotNull(listPhong);
	}

	@AfterAll
	public static void cleanup() {
		phongDAO = null;
	}

}
