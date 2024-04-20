package dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.KhachHangDAO;
import iuh.fit.dao.impl.KhachHangImpl;
import iuh.fit.entity.KhachHang;
import iuh.fit.util.HibernateUtil;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class KhachHangTest {

	static KhachHangDAO khachHangDAO;

	@BeforeAll
	public static void init() throws RemoteException {
		HibernateUtil.provideSessionFactory();
		khachHangDAO = new KhachHangImpl();
	}

	@Test
	void testAddKhachHang() throws RemoteException {
		KhachHang kh = new KhachHang("KH016", "Lê Văn A", "0869582145", "079205098536");
		boolean result = khachHangDAO.addKhachHang(kh);
		assertEquals(true, result);

	}

	@Test
	void testGetAllKhachHangs() throws RemoteException {
		List<KhachHang> listKhachHang = khachHangDAO.getAllKhachHangs();

		for (KhachHang kh : listKhachHang) {
			System.out.println(kh);
		}

		assertNotNull(listKhachHang);

	}

	@Test
	void testGetKhachHang() throws RemoteException {
		KhachHang kh = khachHangDAO.getKhachHang("KH001");

		System.out.println(kh);

		assertEquals("Võ Hoàng Phúc", kh.getHoTen());
	}

	@Test
	void testGetKhachHangBySDT() throws RemoteException {
		KhachHang kh = khachHangDAO.getKhachHangBySDT("0869582145");

		System.out.println(kh);

		assertEquals("KH016", kh.getMaKhachHang());

	}

	@Test
	void testUpdateKhachHang() throws RemoteException {
		KhachHang kh = khachHangDAO.getKhachHang("KH016");
		kh.setHoTen("Lê Văn B");
		assertNotNull(khachHangDAO.updateKhachHang(kh));
		KhachHang updateKhachHang = khachHangDAO.getKhachHang("KH016");
		assertEquals("Lê Văn B", updateKhachHang.getHoTen());

	}

	@AfterAll
	public static void cleanup() {
		khachHangDAO = null;
	}
}
