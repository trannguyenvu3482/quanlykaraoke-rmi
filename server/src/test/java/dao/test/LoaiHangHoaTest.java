package dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.LoaiHangHoaDAO;
import iuh.fit.dao.impl.LoaiHangHoaImpl;
import iuh.fit.entity.LoaiHangHoa;
import iuh.fit.util.HibernateUtil;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoaiHangHoaTest {

	static LoaiHangHoaDAO loaiHangHoaDAO;

	@BeforeAll
	public static void init() throws RemoteException {
		HibernateUtil.provideSessionFactory();
		loaiHangHoaDAO = new LoaiHangHoaImpl();
	}

	@Test
	void testAddLoaiHangHoa() throws RemoteException {
		LoaiHangHoa loaiHangHoa = new LoaiHangHoa("", "Đồ uống có cồn", "Lon/Chai");
		boolean result = loaiHangHoaDAO.addLoaiHangHoa(loaiHangHoa);
		assertEquals(true, result);
	}

	@Test
	void testGetAllLoaiHangHoas() throws RemoteException {
		List<LoaiHangHoa> listLoaiHangHoa = loaiHangHoaDAO.getAllLoaiHangHoas();

		for (LoaiHangHoa loaiHangHoa : listLoaiHangHoa) {
			System.out.println(loaiHangHoa.getTenLoaiHangHoa());
		}
		assertNotNull(listLoaiHangHoa);
	}

	@Test
	void testGetLoaiHangHoaByname() throws RemoteException {
		LoaiHangHoa loaiHangHoa = loaiHangHoaDAO.getLoaiHangHoaByname("Nước ngọt");
		assertEquals("LHH001", loaiHangHoa.getMaLoaiHangHoa());

	}

	@AfterAll
	public static void cleanup() {
		loaiHangHoaDAO = null;
	}
}
