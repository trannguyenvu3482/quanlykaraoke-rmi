package dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.LoaiPhongDAO;
import iuh.fit.dao.impl.LoaiPhongImpl;
import iuh.fit.entity.LoaiPhong;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoaiPhongTest {
	static LoaiPhongDAO loaiPhongDAO;
	
	@BeforeAll
	public static void init() throws RemoteException {
		loaiPhongDAO = new LoaiPhongImpl();
	}
	
	@Test
	void addLoaiPhongTest() throws RemoteException {
		 LoaiPhong lp = new LoaiPhong("LP001", "Phòng thường", 5, 0);
		 boolean result = loaiPhongDAO.addLoaiPhong(lp);
		 assertEquals(true, result);
		
	}
	
	@Test
	void getALlLoaiPhongTest() throws RemoteException {
		List<LoaiPhong> listLoaiPhong = loaiPhongDAO.getAllLoaiPhongs();
		assertEquals(1, listLoaiPhong.size());
	}
	
	@Test
	void getLoaiPhongTest() throws RemoteException {
		LoaiPhong lp = loaiPhongDAO.getLoaiPhong("Phòng thường", 5);
		assertEquals("LP001", lp.getMaLoaiPhong());
	}
	@AfterAll
	public static void cleanup() {
		loaiPhongDAO = null;
	}
}
