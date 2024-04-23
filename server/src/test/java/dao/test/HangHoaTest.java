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
import iuh.fit.dao.LoaiHangHoaDAO;
import iuh.fit.dao.impl.HangHoaImpl;
import iuh.fit.dao.impl.LoaiHangHoaImpl;
import iuh.fit.entity.HangHoa;
import iuh.fit.util.HibernateUtil;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)

class HangHoaTest {
	static HangHoaDAO hangHoaDAO;
	static LoaiHangHoaDAO loaiHangHoaDAO;

	@BeforeAll
	public static void init() throws RemoteException {
		HibernateUtil.provideSessionFactory();
		hangHoaDAO = new HangHoaImpl();
		loaiHangHoaDAO = new LoaiHangHoaImpl();
	}

	@Test
	void testAddHangHoa() throws RemoteException {
		HangHoa hangHoa = new HangHoa("", "Sting", loaiHangHoaDAO.getLoaiHangHoaByname("Nước ngọt"), 100, 100000, true);
		boolean result = hangHoaDAO.addHangHoa(hangHoa);
		assertEquals(true, result);
	}

	@Test
	void testGetAllHangHoas() throws RemoteException {
		List<HangHoa> listHangHoa = hangHoaDAO.getAllHangHoas();

		for (HangHoa hangHoa : listHangHoa) {
			System.out.println(hangHoa);
		}
		assertNotNull(listHangHoa);

	}

	@Test
	void testGetHangHoa() throws RemoteException {
		HangHoa hangHoa = hangHoaDAO.getHangHoa("HH013");
		assertEquals("HH013", hangHoa.getMaHangHoa());

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
		hangHoa.setSoLuongTon(500);
		assertNotNull(hangHoaDAO.updateHangHoa(hangHoa));
		HangHoa updatedHangHoa = hangHoaDAO.getHangHoa("HH014");
		assertEquals(500, updatedHangHoa.getSoLuongTon());
	}

	@AfterAll
	public static void cleanup() {
		hangHoaDAO = null;
	}
}
