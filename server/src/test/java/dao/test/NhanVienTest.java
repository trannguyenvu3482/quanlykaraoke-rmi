/**
 * 
 */
package dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.rmi.RemoteException;
import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.NhanVienDAO;
import iuh.fit.dao.impl.NhanVienImpl;
import iuh.fit.entity.ChucVu;
import iuh.fit.entity.NhanVien;
import iuh.fit.util.PasswordUtil;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 19 Apr 2024 - 3:55:32 pm
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class NhanVienTest {
	static NhanVienDAO nhanVienDAO;

	@BeforeAll
	public static void init() throws RemoteException {
		nhanVienDAO = new NhanVienImpl();
	}

	@Test
	void testAddNhanVien() throws RemoteException {
		NhanVien nhanVien = new NhanVien("Nguyễn Văn Z", 0, PasswordUtil.encrypt("123"), LocalDate.of(2003, 10, 14),
				new ChucVu(), "0903252508", "079203012315");
		boolean result = nhanVienDAO.addNhanVien(nhanVien);

		assertEquals(true, result);
	}

	@Test
	void testGetAllNhanVien() throws RemoteException {
		List<NhanVien> listNhanVien = nhanVienDAO.getAllNhanViens();

		assertNotNull(listNhanVien);
	}

	@Test
	void testUpdateNhanVien() throws RemoteException {
		NhanVien nhanVien = nhanVienDAO.getNhanVien("NV001");
		nhanVien.setHoTen("Trần Văn A");
		assertNotNull(nhanVienDAO.updateNV(nhanVien));
		NhanVien updatedNhanVien = nhanVienDAO.getNhanVien("NV001");
		assertEquals("Trần Văn A", updatedNhanVien.getHoTen());
	}

	@AfterAll
	public static void cleanup() {
		nhanVienDAO = null;
	}
}
