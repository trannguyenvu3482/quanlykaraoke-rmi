/**
 * 
 */
package iuh.fit.client;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.UIManager;

import org.kordamp.ikonli.materialdesign2.MaterialDesignE;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLaf;

import iuh.fit.common.MyIcon;
import iuh.fit.gui.DangNhapGUI;
import iuh.fit.gui.NhanVienGUI;
import iuh.fit.gui.QuanLyGUI;
import iuh.fit.gui.dialogs.SplashScreenDialog;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 18 Apr 2024 - 1:09:51 am
 */
public class Client {
	private static final String URL = "rmi://localhost:8001/";

	public static void main(String[] args) throws Exception {
		// Setup RMI to get DAOs from server

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

						// Login success, show main window
						if (userId.equals("NV001")) {
							QuanLyGUI main = new QuanLyGUI(userId);
							main.setLogoutListener(() -> showLoginScreen());
							main.setVisible(true);
						} else {
							NhanVienGUI main = new NhanVienGUI(userId);
							main.setLogoutListener(() -> showLoginScreen());
							main.setVisible(true);
						}
					}
				});

				loginGUI.setVisible(true);

			}
		});
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
						QuanLyGUI main = new QuanLyGUI(id);
						main.setLogoutListener(() -> showLoginScreen());
						main.setVisible(true);
					} else {
						NhanVienGUI main = new NhanVienGUI(id);
						main.setLogoutListener(() -> showLoginScreen());
						main.setVisible(true);
					}
				}
			});

			login.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
