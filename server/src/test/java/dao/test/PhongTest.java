package dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.PhongDAO;
import iuh.fit.dao.impl.PhongImpl;
import iuh.fit.entity.LoaiPhong;
import iuh.fit.entity.Phong;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PhongTest {
	static PhongDAO phongDAO;
	
	@BeforeAll
	public static void init() throws RemoteException {
		phongDAO = new PhongImpl();
	}
	
	@Test
	void testAddPhong() throws RemoteException {
		Phong phong = new Phong("", new LoaiPhong("LP001", "Phòng thường", 5, 0), true);
		boolean result = phongDAO.addPhong(phong);

		assertEquals(true, result);
	}
	
	@Test
	void testGetAllPhongs() throws RemoteException {
		List<Phong> listPhong = phongDAO.getAllPhongs();
		
		assertNotNull(listPhong);
	}
	
	@Test
	void testUpdatePhong() throws RemoteException {
		Phong phong = phongDAO.getPhong("P001");
		phong.setTrangThai(false);
		assertNotNull(phongDAO.updatePhong(phong));
		Phong updatedPhong = phongDAO.getPhong("P001");
		assertEquals(false, updatedPhong.isTrangThai());
	}
	
	@Test
	void getAllEmptyPhong() throws RemoteException {
		List<Phong> listPhong = phongDAO.getAllEmptyPhongs();

		assertNotNull(listPhong);
	}
	@AfterAll
	public static void cleanup() {
		phongDAO = null;
	}
	
	
}
