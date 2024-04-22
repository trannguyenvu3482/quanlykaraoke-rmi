/**
 * 
 */
package iuh.fit.client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.HashMap;
import java.util.Map;

import javax.swing.UIManager;

import org.kordamp.ikonli.materialdesign2.MaterialDesignE;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;

import iuh.fit.common.MyIcon;
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
import iuh.fit.gui.DangNhapGUI;
import iuh.fit.gui.NhanVienGUI;
import iuh.fit.gui.QuanLyGUI;
import iuh.fit.gui.dialogs.SplashScreenDialog;
import iuh.fit.util.OTPService;
import iuh.fit.util.PasswordService;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 18 Apr 2024 - 1:09:51 am
 */
public class Client {
	private static final String URL = "rmi://localhost:8001/";
	protected static Map<String, Object> daos;
	protected static Map<String, Object> utils;

	public static void main(String[] args) throws Exception {
		// Setup RMI to get DAOs from server
		daos = new HashMap<>();
		utils = new HashMap<>();

		// Get DAOs from server and put into Map
		ChiTietDichVuDAO chiTietDichVuDAO = (ChiTietDichVuDAO) Naming.lookup(URL + "ChiTietDichVuDAO");
		daos.put("ChiTietDichVuDAO", chiTietDichVuDAO);

		ChiTietPhieuDatPhongDAO chiTietPhieuDatPhongDAO = (ChiTietPhieuDatPhongDAO) Naming
				.lookup(URL + "ChiTietPhieuDatPhongDAO");
		daos.put("ChiTietPhieuDatPhongDAO", chiTietPhieuDatPhongDAO);

		ChucVuDAO chucVuDAO = (ChucVuDAO) Naming.lookup(URL + "ChucVuDAO");
		daos.put("ChucVuDAO", chucVuDAO);

		HangHoaDAO hangHoaDAO = (HangHoaDAO) Naming.lookup(URL + "HangHoaDAO");
		daos.put("HangHoaDAO", hangHoaDAO);

		KhachHangDAO khachHangDAO = (KhachHangDAO) Naming.lookup(URL + "KhachHangDAO");
		daos.put("KhachHangDAO", khachHangDAO);

		LoaiHangHoaDAO loaiHangHoaDAO = (LoaiHangHoaDAO) Naming.lookup(URL + "LoaiHangHoaDAO");
		daos.put("LoaiHangHoaDAO", loaiHangHoaDAO);

		LoaiPhongDAO loaiPhongDAO = (LoaiPhongDAO) Naming.lookup(URL + "LoaiPhongDAO");
		daos.put("LoaiPhongDAO", loaiPhongDAO);

		NhanVienDAO nhanVienDAO = (NhanVienDAO) Naming.lookup(URL + "NhanVienDAO");
		daos.put("NhanVienDAO", nhanVienDAO);

		PhieuDatPhongDAO phieuDatPhongDAO = (PhieuDatPhongDAO) Naming.lookup(URL + "PhieuDatPhongDAO");
		daos.put("PhieuDatPhongDAO", phieuDatPhongDAO);

		PhongDAO phongDAO = (PhongDAO) Naming.lookup(URL + "PhongDAO");
		daos.put("PhongDAO", phongDAO);

		// Get utils from server and put into Map
		OTPService otpUtil = (OTPService) Naming.lookup(URL + "OTPUtil");
		utils.put("OTPUtil", otpUtil);

		PasswordService passwordUtil = (PasswordService) Naming.lookup(URL + "PasswordUtil");
		utils.put("PasswordUtil", passwordUtil);

		// Setup Flatlaf
		setupFlatLaf();

		// Show splash screen
		SplashScreenDialog splashGUI = new SplashScreenDialog();
		splashGUI.setVisible(true);

		// Add window listener to detect when splash screen close
		splashGUI.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosed(WindowEvent e) {

				// Splash closed, show login screen
				DangNhapGUI loginGUI = null;
				try {
					loginGUI = new DangNhapGUI();
				} catch (Exception e1) {
					e1.printStackTrace();
				}

				// Add listener for login
				loginGUI.setLoginListener(userId -> {
					if (userId != null) {
						NhanVienDAO nvDAO = (NhanVienDAO) Client.getDAO("NhanVienDAO");

						// Login success, show main window
						try {
							if (nvDAO.getNhanVien(userId).getChucVu().getMaChucVu().equals("CV001")) {
								QuanLyGUI main;
								try {
									main = new QuanLyGUI(userId);
									main.setLogoutListener(() -> showLoginScreen());
									main.setVisible(true);
								} catch (RemoteException e1) {
									e1.printStackTrace();
								}
							} else {
								NhanVienGUI main;
								try {
									main = new NhanVienGUI(userId);
									main.setLogoutListener(() -> showLoginScreen());
									main.setVisible(true);
								} catch (RemoteException e1) {
									e1.printStackTrace();
								}
							}
						} catch (RemoteException e1) {
							e1.printStackTrace();
						}
					}
				});

				loginGUI.setVisible(true);

			}
		});
	}

	public static Object getDAO(String name) {
		return daos.get(name);
	}

	public static Object getUtil(String name) {
		return utils.get(name);
	}

	/**
	 * 
	 */
	private static void setupFlatLaf() {
		FlatLaf.registerCustomDefaultsSource("com.theme");
		FlatIntelliJLaf.setup();

		UIManager.put("Table.showHorizontalLines", true);
		UIManager.put("Table.showVerticalLines", true);
		UIManager.put("JTabbedPane.showTabSeparators", true);
		UIManager.put("PasswordField.showRevealButton", true);
		UIManager.put("PasswordField.revealIcon ", MyIcon.getIcon(MaterialDesignE.EYE, 12, null));
	}

	private static void showLoginScreen() {
		DangNhapGUI login = null;
		try {
			login = new DangNhapGUI();
			login.setLoginListener(id -> {
				if (id != null) {
					if (id.equals("NV001")) {
						QuanLyGUI main;
						try {
							main = new QuanLyGUI(id);
							main.setLogoutListener(() -> showLoginScreen());
							main.setVisible(true);
						} catch (RemoteException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} else {
						NhanVienGUI main;
						try {
							main = new NhanVienGUI(id);
							main.setLogoutListener(() -> showLoginScreen());
							main.setVisible(true);
						} catch (RemoteException e) {
							e.printStackTrace();
						}
					}
				}
			});

			login.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
