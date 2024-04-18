/**
 * 
 */
package iuh.fit.server;

import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;

import iuh.fit.dao.HangHoaDAO;
import iuh.fit.dao.KhachHangDAO;
import iuh.fit.dao.LoaiHangHoaDAO;
import iuh.fit.dao.NhanVienDAO;
import iuh.fit.dao.PhongDAO;
import iuh.fit.dao.impl.HangHoaImpl;
import iuh.fit.dao.impl.KhachHangImpl;
import iuh.fit.dao.impl.LoaiHangHoaImpl;
import iuh.fit.dao.impl.NhanVienImpl;
import iuh.fit.dao.impl.PhongImpl;
import iuh.fit.util.HibernateUtil;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 18 Apr 2024 - 1:31:25 pm
 */
public class Server {
	private static final String URL = "rmi://localhost:8001/";

	public static void main(String[] args) {
		try {
			// Start hibernate
			HibernateUtil.provideSessionFactory();

			// Start rmi server
			Context context = new InitialContext();
			
			LocateRegistry.createRegistry(8001);

			// Create remote objects
			PhongDAO phongDAO = new PhongImpl();
			NhanVienDAO nhanVienDAO = new NhanVienImpl();
			HangHoaDAO hangHoaDAO = new HangHoaImpl();
			KhachHangDAO khachHangDAO = new KhachHangImpl();
			LoaiHangHoaDAO loaiHangHoaDAO = new LoaiHangHoaImpl();
			
			// Bind object to rmi server
			context.rebind(URL + "PhongDAO", phongDAO);
			context.rebind(URL + "NhanVienDAO", nhanVienDAO);
			context.rebind(URL + "HangHoaDAO", hangHoaDAO);
			context.rebind(URL + "KhachHangDAO", khachHangDAO);
			context.rebind(URL + "LoaiHangHoaDAO", loaiHangHoaDAO);
			// Log
			System.out.println("Server started");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
