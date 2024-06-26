package iuh.fit.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import iuh.fit.entity.NhanVien;

public class ConstantUtil {
	public static final Color DARKER_BLUE = new Color(3, 4, 94);
	public static final Color DARK_BLUE = new Color(2, 62, 138);
	public static final Color MAIN_BLUE = new Color(0, 119, 182);
	public static final Color MAIN_LIGHT_BLUE = new Color(72, 202, 228);
	public static final Color MAIN_LIGHTER_BLUE = new Color(144, 224, 239);
	public static final Color MAIN_LIGHTEST_BLUE = new Color(202, 240, 248);

	public static final int MAXIMUM_PAGE_SIZE = 8;

	public static final int DAYTIME_BEGIN_HOUR = 0;
	public static final int DAYTIME_END_HOUR = 18;
	public static final double DAYTIME_HOUR_PRICE = 45000;

	public static final int NIGHTTIME_BEGIN_HOUR = 18;
	public static final int NIGHTTIME_END_HOUR = 23;
	public static final double NIGHTTIME_HOUR_PRICE = 60000;

	public static NhanVien currentNhanVien = null;

	public static final double getStandardHourPrice(LocalDateTime time) {
		if (time.getHour() < NIGHTTIME_BEGIN_HOUR) {
			return DAYTIME_HOUR_PRICE;
		} else {
			return NIGHTTIME_HOUR_PRICE;
		}
	}

	public static BufferedImage scaleImage(int WIDTH, int HEIGHT, BufferedImage img) {
		BufferedImage bi = null;
		try {
			ImageIcon ii = new ImageIcon(img);
			bi = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
			Graphics2D g2d = (Graphics2D) bi.createGraphics();
			g2d.addRenderingHints(
					new RenderingHints(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY));
			g2d.drawImage(ii.getImage(), 0, 0, WIDTH, HEIGHT, null);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return bi;
	}

	public static final ImageIcon byteArrayToImageIcon(byte[] data) {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new ByteArrayInputStream(data));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int width = img.getWidth(null);
		int height = img.getHeight(null);

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int circleDiameter = Math.min(width, height);
		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, circleDiameter, circleDiameter);
		g2.setClip(circle);
		g2.drawImage(img, 0, 0, null);

		// Display circular image
		return new ImageIcon(bi.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
	}

	public static final byte[] getDefaultMaleAvatar() {
		try {
			return Files.readAllBytes(Paths.get("src/main/resources/images/male-avatar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static final ImageIcon getDefaultMaleAvatarIcon() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/main/resources/images/male-avatar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		int width = img.getWidth(null);
		int height = img.getHeight(null);

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int circleDiameter = Math.min(width, height);
		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, circleDiameter, circleDiameter);
		g2.setClip(circle);
		g2.drawImage(img, 0, 0, null);

		// Display circular image
		return new ImageIcon(bi.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
	}

	public static final byte[] getDefaultFemaleAvatar() {
		try {
			return Files.readAllBytes(Paths.get("src/main/resources/images/female-avatar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static final ImageIcon getDefaultFemaleAvatarIcon() {
		BufferedImage img = null;
		try {
			img = ImageIO.read(new File("src/main/resources/images/female-avatar.png"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int width = img.getWidth(null);
		int height = img.getHeight(null);

		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		Graphics2D g2 = bi.createGraphics();

		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int circleDiameter = Math.min(width, height);
		Ellipse2D.Double circle = new Ellipse2D.Double(0, 0, circleDiameter, circleDiameter);
		g2.setClip(circle);
		g2.drawImage(img, 0, 0, null);

		// Display circular image
		return new ImageIcon(bi.getScaledInstance(200, 200, Image.SCALE_SMOOTH));
	}

	public static final boolean isStringInteger(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}

	}

}
