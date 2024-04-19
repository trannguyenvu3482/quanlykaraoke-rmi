package dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.ChiTietDichVuDAO;
import iuh.fit.dao.impl.ChiTietDichVuImpl;
import iuh.fit.entity.ChiTietDichVu;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChiTietDichVuTest {
	static ChiTietDichVuDAO chiTietDichVuDAO;
	
	@BeforeAll
	public static void init() throws RemoteException {
		chiTietDichVuDAO = new ChiTietDichVuImpl();
	}
	
	@Test
	void testAddChiTietDichVu() throws RemoteException {
		ChiTietDichVu chiTietDichVu = new ChiTietDichVu();
		boolean result = chiTietDichVuDAO.addChiTietDichVu(chiTietDichVu);
		assertEquals(true, result);
	}
	@Test
	void testGetChiTietDichVuByMaPDPAndMaPhong() throws RemoteException {
		assertEquals(0, chiTietDichVuDAO.getChiTietDichVuByMaPDPAndMaPhong("PDP001", "P001").size());
	}
	@Test
	void testGetTongTienDichVuByMaPDP() throws RemoteException {
		assertEquals(0, chiTietDichVuDAO.getTongTienDichVuByMaPDP("PDP001"));
	}
	@Test
	void testGetAllChiTietDichVus() throws RemoteException {
		assertEquals(0, chiTietDichVuDAO.getAllChiTietDichVus().size());
	}
	@Test
	void testUpdateChiTietDichVu() throws RemoteException {
		ChiTietDichVu chiTietDichVu = new ChiTietDichVu();
		assertEquals(null, chiTietDichVuDAO.updateChiTietDichVu(chiTietDichVu));
	}
	@Test
	void testDeleteChiTietDichVu() throws RemoteException {
		ChiTietDichVu chiTietDichVu = new ChiTietDichVu();
		assertEquals(true, chiTietDichVuDAO.deleteChiTietDichVu(chiTietDichVu));
	}

}
