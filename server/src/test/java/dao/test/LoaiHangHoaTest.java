package dao.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.rmi.RemoteException;
import java.util.List;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import iuh.fit.dao.LoaiHangHoaDAO;
import iuh.fit.dao.impl.LoaiHangHoaImpl;
import iuh.fit.entity.LoaiHangHoa;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class LoaiHangHoaTest {
	
	
	static LoaiHangHoaDAO loaiHangHoaDAO;
	
	@BeforeAll
	public static void init() throws RemoteException {
		loaiHangHoaDAO = new LoaiHangHoaImpl();
	}
	
	@Test
	void testAddLoaiHangHoa() throws RemoteException{
		LoaiHangHoa loaiHangHoa = new LoaiHangHoa("LH015", "Đồ uống có cồn", "Lon/Chai");
		boolean result = loaiHangHoaDAO.addLoaiHangHoa(loaiHangHoa);
		assertEquals(true, result);
	}
	
	@Test
	void testGetAllLoaiHangHoas() throws RemoteException {
		List<LoaiHangHoa> listLoaiHangHoa = loaiHangHoaDAO.getAllLoaiHangHoas();
		assertEquals(15, listLoaiHangHoa.size());
	}
	
	@Test
	void  testGetLoaiHangHoaByname( ) throws RemoteException {
		LoaiHangHoa loaiHangHoa = loaiHangHoaDAO.getLoaiHangHoaByname("Đồ uống có cồn");
		assertEquals("LH015", loaiHangHoa.getMaLoaiHangHoa());
		
	}
	
	@AfterAll
	public void cleanup() {
		loaiHangHoaDAO = null;
	}
}
