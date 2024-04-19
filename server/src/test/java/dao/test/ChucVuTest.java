package dao.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.ChucVuDAO;
import iuh.fit.dao.impl.ChucVuImpl;
import iuh.fit.entity.ChucVu;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChucVuTest {
	static ChucVuDAO chucVuDAO;
	
	@BeforeAll
	public static void init() throws RemoteException {
		chucVuDAO = new ChucVuImpl();
	}
	@Test
	void testGetAllChucVu() throws RemoteException {
		List<ChucVu> listChucVu = chucVuDAO.getAllChucVus();
		assertNotNull(listChucVu);
	}
	@Test
	void testGetChucVuByName() throws RemoteException {
		ChucVu chucVu = chucVuDAO.getChucVuByName("Nhân viên");
		assertNotNull(chucVu);
	}
}
