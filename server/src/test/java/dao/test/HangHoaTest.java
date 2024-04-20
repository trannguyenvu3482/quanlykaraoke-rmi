package dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.HangHoaDAO;
import iuh.fit.dao.impl.HangHoaImpl;
import iuh.fit.entity.HangHoa;
import iuh.fit.entity.LoaiHangHoa;
import iuh.fit.util.HibernateUtil;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class HangHoaTest {
	static HangHoaDAO hangHoaDAO;

	@BeforeAll
	public static void init() throws RemoteException {
		HibernateUtil.provideSessionFactory();
		hangHoaDAO = new HangHoaImpl();
	}

	@Test
	void testAddHangHoa() throws RemoteException {
		HangHoa hangHoa = new HangHoa("HH014", "Sting", new LoaiHangHoa("LHH014", "Nước ngọt"), 350, 35000, true);
		boolean result = hangHoaDAO.addHangHoa(hangHoa);

		assertEquals(true, result);

	}

	@Test
	void testGetAllHangHoas() throws RemoteException {
		List<HangHoa> listHangHoa = hangHoaDAO.getAllHangHoas();
		assertNotNull(listHangHoa);

	}

	@Test
	void testGetHangHoa() throws RemoteException {
		HangHoa hangHoa = hangHoaDAO.getHangHoa("HH014");
		assertEquals("LHH014", hangHoa.getLoaiHangHoa().getMaLoaiHangHoa());

	}

	@Test
	void testUpdateHangHoa() throws RemoteException {
		HangHoa hangHoa = hangHoaDAO.getHangHoa("HH014");
		hangHoa.setTenHangHoa("Sting dâu");
		assertNotNull(hangHoaDAO.updateHangHoa(hangHoa));
		HangHoa updatedHangHoa = hangHoaDAO.getHangHoa("HH014");
		assertEquals("Sting dâu", updatedHangHoa.getTenHangHoa());

	}

	@Test
	void testUpdateSoLuongTon() throws RemoteException {
		HangHoa hangHoa = hangHoaDAO.getHangHoa("HH014");
		assertNotNull(hangHoaDAO.updateSoLuongTon(hangHoa, 500));
		HangHoa updatedHangHoa = hangHoaDAO.getHangHoa("HH014");
		assertEquals(500, updatedHangHoa.getSoLuongTon());
	}

	@AfterAll
	public static void cleanup() {
		hangHoaDAO = null;
	}
}
