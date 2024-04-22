/**
 * 
 */
package iuh.fit.server;

import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;

import iuh.fit.dao.ChiTietDichVuDAO;
import iuh.fit.dao.ChiTietPhieuDatPhongDAO;
import iuh.fit.dao.ChucVuDAO;
import iuh.fit.dao.HangHoaDAO;
import iuh.fit.dao.KhachHangDAO;
import iuh.fit.dao.LoaiHangHoaDAO;
import iuh.fit.dao.LoaiPhongDAO;
import iuh.fit.dao.NhanVienDAO;
import iuh.fit.dao.PhieuDatPhongDAO;
import iuh.fit.dao.PhongDAO;
import iuh.fit.dao.impl.ChiTietDichVuImpl;
import iuh.fit.dao.impl.ChiTietPhieuDatPhongImpl;
import iuh.fit.dao.impl.ChucVuImpl;
import iuh.fit.dao.impl.HangHoaImpl;
import iuh.fit.dao.impl.KhachHangImpl;
import iuh.fit.dao.impl.LoaiHangHoaImpl;
import iuh.fit.dao.impl.LoaiPhongImpl;
import iuh.fit.dao.impl.NhanVienImpl;
import iuh.fit.dao.impl.PhieuDatPhongImpl;
import iuh.fit.dao.impl.PhongImpl;
import iuh.fit.util.HibernateUtil;
import iuh.fit.util.OTPService;
import iuh.fit.util.OTPUtil;
import iuh.fit.util.PasswordService;
import iuh.fit.util.PasswordUtil;

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

			// Create remote DAOs
			PhongDAO phongDAO = new PhongImpl();
			NhanVienDAO nhanVienDAO = new NhanVienImpl();
			HangHoaDAO hangHoaDAO = new HangHoaImpl();
			KhachHangDAO khachHangDAO = new KhachHangImpl();
			LoaiHangHoaDAO loaiHangHoaDAO = new LoaiHangHoaImpl();
			LoaiPhongDAO loaiPhongDAO = new LoaiPhongImpl();
			PhieuDatPhongDAO phieuDatPhongDAO = new PhieuDatPhongImpl();
			ChucVuDAO chucVuDAO = new ChucVuImpl();
			ChiTietDichVuDAO chiTietDichVuDAO = new ChiTietDichVuImpl();
			ChiTietPhieuDatPhongDAO chiTietPhieuDatPhong = new ChiTietPhieuDatPhongImpl();

			// Create remote server-side utils
			PasswordService passwordUtil = new PasswordUtil();
			OTPService OTPUtil = new OTPUtil();

			// Bind DAOs to rmi server
			context.rebind(URL + "PhongDAO", phongDAO);
			context.rebind(URL + "NhanVienDAO", nhanVienDAO);
			context.rebind(URL + "HangHoaDAO", hangHoaDAO);
			context.rebind(URL + "KhachHangDAO", khachHangDAO);
			context.rebind(URL + "LoaiHangHoaDAO", loaiHangHoaDAO);
			context.rebind(URL + "LoaiPhongDAO", loaiPhongDAO);
			context.rebind(URL + "PhieuDatPhongDAO", phieuDatPhongDAO);
			context.rebind(URL + "ChucVuDAO", chucVuDAO);
			context.rebind(URL + "ChiTietDichVuDAO", chiTietDichVuDAO);
			context.rebind(URL + "ChiTietPhieuDatPhongDAO", chiTietPhieuDatPhong);

			// Bind utils to rmi server
			context.rebind(URL + "OTPUtil", OTPUtil);
			context.rebind(URL + "PasswordUtil", passwordUtil);

			// Log
			System.out.println("Server started");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
