package iuh.fit.gui.panels;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.rmi.RemoteException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;

import org.kordamp.ikonli.materialdesign2.MaterialDesignM;

import iuh.fit.client.Client;
import iuh.fit.common.MyIcon;
import iuh.fit.dao.ChiTietPhieuDatPhongDAO;
import iuh.fit.dao.PhieuDatPhongDAO;
import iuh.fit.dao.PhongDAO;
import iuh.fit.entity.ChiTietPhieuDatPhong;
import iuh.fit.entity.Phong;
import iuh.fit.gui.dialogs.ChuyenPhongDialog;
import iuh.fit.gui.dialogs.QuanLyDichVuDialog;
import iuh.fit.gui.dialogs.TaoPhieuDatPhongDialog;
import iuh.fit.gui.dialogs.ThanhToanDialog;
import iuh.fit.util.ConstantUtil;
import iuh.fit.util.MoneyFormatUtil;
import raven.toast.Notifications;
import raven.toast.Notifications.Location;
import raven.toast.Notifications.Type;

/**
 * @author Trần Nguyên Vũ, Trần Ngọc Phát, Mai Nhật Hào, Lê Phước Nguyên
 * @version 1.0
 * @created 25-Oct-2023 16:39:00
 */
public class RoomPanel extends JPanel implements MouseListener {
	private static final long serialVersionUID = 1L;

	// INTERFACE
	public interface DialogClosedListener {
		void onClosed();
	}

	// COMPONENTS
	private final JLabel lblLogo = new JLabel("");
	private final JLabel lblRoom = new JLabel("");
	private final JPanel infoPanel = new JPanel();
	private final JPanel mainPanel = new JPanel();
	private final JLabel lblNumber = new JLabel("Số lượng: 5 người");
	private final JPanel panel = new JPanel();
	private final JLabel lblType = new JLabel("Loại: Thường");
	private final JLabel lblFee = new JLabel("Phụ phí: 0 VND");
	private final JLabel lblStatus = new JLabel("Trạng thái: Trống");

	// VARIABLES
	private PhongDAO pDAO = (PhongDAO) Client.getDAO("PhongDAO");
	private PhieuDatPhongDAO pdpDAO = (PhieuDatPhongDAO) Client.getDAO("PhieuDatPhongDAO");
	private ChiTietPhieuDatPhongDAO ctpdpDAO = (ChiTietPhieuDatPhongDAO) Client.getDAO("ChiTietPhieuDatPhongDAO");
	private String roomName = "Phòng ";
	private boolean isSelected = false;
	private boolean isBooked = false;
	private Phong p = null;

	/**
	 * Instantiates a new room panel.
	 *
	 * @param p the Phong
	 */
	public RoomPanel(Phong p) throws RemoteException {
		// Set variables
		this.p = p;
		this.roomName = roomName.concat(p.getMaPhong());
		this.isBooked = pDAO.isRoomEmpty(p);

		// Create the UI
		initUI();
	}

	/**
	 * Create a PhieuDatPhong
	 */
	public boolean createPDP(DialogClosedListener listener) {
		SwingUtilities.invokeLater(() -> {
			TaoPhieuDatPhongDialog dialog = new TaoPhieuDatPhongDialog(p);

			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					System.out.println(e.getComponent());
					listener.onClosed();
				}
			});

			dialog.setVisible(true);
		});

		return true;
	}

	/**
	 * Remove a PhieuDatPhong
	 */
	public boolean removePDP() {
		int result = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn hủy phòng này", "Thông báo",
				JOptionPane.YES_NO_OPTION);

		if (result == JOptionPane.YES_OPTION) {
			ChiTietPhieuDatPhong ctpdp;
			try {
				ctpdp = ((ChiTietPhieuDatPhongDAO) Client.getDAO("ChiTietPhieuDatPhongDAO"))
						.getChiTietPhieuDatPhongByActiveMaPhong(p.getMaPhong());
				if (ctpdp.getThoiGianBatDau().until(LocalDateTime.now(), ChronoUnit.MINUTES) > 15) {
					System.out.println("FINISH!");
					if (pdpDAO.finishPhieuDatPhong(p.getMaPhong(), 0, 0)) {
						return true;
					}

				} else {
					Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT,
							"Chỉ có thể chuyển phòng sau 15 phút kể từ lúc bắt đầu");
					return false;
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}
		}

		return false;
	}

	public boolean changeRoom(DialogClosedListener listener) {
		SwingUtilities.invokeLater(() -> {
			ChuyenPhongDialog dialog = new ChuyenPhongDialog(p);

			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					listener.onClosed();
				}
			});

			dialog.setVisible(true);
		});

		return true;
	}

	public boolean addDichVu(DialogClosedListener listener) {
		SwingUtilities.invokeLater(() -> {
			try {
				if (ctpdpDAO.getChiTietPhieuDatPhongByActiveMaPhong(p.getMaPhong()) == null) {
					Notifications.getInstance().show(Type.ERROR, Location.BOTTOM_RIGHT,
							"Phòng này đã hoàn tất, vui lòng làm mới");

					return;
				}
			} catch (RemoteException e) {
				e.printStackTrace();
			}

			QuanLyDichVuDialog dialog = new QuanLyDichVuDialog(p);

			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					listener.onClosed();
				}
			});

			dialog.setVisible(true);
		});

		return true;
	}

	public boolean checkout(DialogClosedListener listener) {
		SwingUtilities.invokeLater(() -> {
			ThanhToanDialog dialog = new ThanhToanDialog(p);

			dialog.addWindowListener(new WindowAdapter() {
				public void windowClosed(WindowEvent e) {
					listener.onClosed();
				}
			});

			dialog.setVisible(true);
		});

		return true;
	}

	/**
	 * Select a Phong
	 */
	public void select() {
		mainPanel.setBackground(ConstantUtil.MAIN_LIGHT_BLUE);
		infoPanel.setBackground(ConstantUtil.MAIN_LIGHT_BLUE);

		this.setSelected(true);
	}

	/**
	 * Deselect a Phong
	 */
	public void deselect() {
		mainPanel.setBackground(Color.white);
		infoPanel.setBackground(Color.white);

		this.setSelected(false);
	}

	/**
	 * Initiate the UI
	 */
	private void initUI() {
		setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

		if (isBooked) {
			lblLogo.setIcon(MyIcon.getIcon(MaterialDesignM.MICROPHONE_VARIANT, 180, null));
		} else {
			lblLogo.setIcon(MyIcon.getIcon(MaterialDesignM.MICROPHONE_VARIANT_OFF, 180, null));
		}

		addMouseListener(this);
		setLayout(new BorderLayout(0, 0));
		panel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));

		add(panel);
		panel.setLayout(new CardLayout(0, 0));
		mainPanel.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		mainPanel.setName("mainPanel");
		panel.add(mainPanel, "mainPanel");
		mainPanel.setLayout(new BorderLayout(0, 0));
		lblRoom.setForeground(new Color(50, 102, 133));
		mainPanel.add(lblRoom, BorderLayout.SOUTH);

		mainPanel.setBackground(Color.white);
		infoPanel.setBackground(Color.white);

		lblRoom.setText(roomName);

		lblRoom.setVerticalAlignment(SwingConstants.TOP);
		lblRoom.setHorizontalAlignment(SwingConstants.CENTER);
		lblRoom.setFont(new Font("Dialog", Font.BOLD, 40));
		mainPanel.add(lblLogo, BorderLayout.CENTER);
		lblLogo.setHorizontalAlignment(SwingConstants.CENTER);
		infoPanel.setBorder(new TitledBorder(null, roomName, TitledBorder.LEADING, TitledBorder.TOP, null, null));
		infoPanel.setName("infoPanel");
		panel.add(infoPanel, "infoPanel");
		infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
		lblType.setFont(new Font("Dialog", Font.BOLD, 24));
		infoPanel.add(lblType);
		lblNumber.setFont(new Font("Dialog", Font.BOLD, 24));

		infoPanel.add(lblNumber);
		lblStatus.setFont(new Font("Dialog", Font.BOLD, 24));

		infoPanel.add(lblStatus);
		lblFee.setFont(new Font("Dialog", Font.BOLD, 24));

		infoPanel.add(lblFee);

		lblNumber.setText("Số lượng: " + String.format("%d", p.getLoaiPhong().getKichThuoc()) + " người");
		lblType.setText("Loại: " + p.getLoaiPhong().getTenLoaiPhong());
		lblFee.setText("Phụ phí: " + MoneyFormatUtil.format(p.getLoaiPhong().getPhuPhi()));
		lblStatus.setText("Trạng thái: " + (isBooked == true ? "Đã đặt" : "Trống"));
	}

	/**
	 * Mouse clicked.
	 *
	 * @param e the event
	 */
	@Override
	public void mouseClicked(MouseEvent e) {
	}

	/**
	 * Mouse entered.
	 *
	 * @param e the event
	 */
	@Override
	public void mouseEntered(MouseEvent e) {
		CardLayout cl = (CardLayout) panel.getLayout();
		cl.show(panel, "infoPanel");
	}

	/**
	 * Mouse pressed.
	 *
	 * @param e the event
	 */
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Mouse released.
	 *
	 * @param e the event
	 */
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	/**
	 * Mouse exited.
	 *
	 * @param e the event
	 */
	@Override
	public void mouseExited(MouseEvent e) {
		CardLayout cl = (CardLayout) panel.getLayout();
		cl.show(panel, "mainPanel");
	}

	/**
	 * @return the isSelected
	 */
	public boolean isSelected() {
		return isSelected;
	}

	/**
	 * @param isSelected the isSelected to set
	 */
	public void setSelected(boolean isSelected) {
		this.isSelected = isSelected;
	}

}
