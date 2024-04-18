/**
 * 
 */
package iuh.fit.dao.impl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import iuh.fit.dao.PhongDAO;
import iuh.fit.entity.Phong;
import iuh.fit.util.HibernateUtil;

/**
 * @author Trần Nguyên Vũ
 * @version 1.0
 * @created 18 Apr 2024 - 1:35:10 pm
 */
public class PhongImpl extends UnicastRemoteObject implements PhongDAO, Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 6318153945323394885L;
	private SessionFactory factory = null;

	public PhongImpl() throws RemoteException {
		factory = HibernateUtil.getMySessionFactory();
	}

	public boolean addPhong(Phong p) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			String maP = getNextMaPhong();

			p.setMaPhong(maP);
			session.persist(p);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	private String getNextMaPhong() {
		String idPrefix = "P";

		int count = countPhong();

		if (count < 0 || count > 999) {
			return null;
		}

		return idPrefix + String.format("%03d", count + 1);
	}

	private int countPhong() {
		Session session = factory.getCurrentSession();

		try {
			String hql = "From Phong";
			Query<Phong> query = session.createQuery(hql, Phong.class);
			List<Phong> listPhong = query.getResultList();

			return listPhong.size();

		} catch (Exception e) {
			return -1;
		}
	}

	public List<Phong> getAllPhongs() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<Phong> listPhong = null;
		try {
			String hql = "from Phong";
			listPhong = session.createQuery(hql, Phong.class).getResultList();

			t.commit();
			return listPhong;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listPhong;
		}
	}

	public List<Phong> getAllEmptyPhongs() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<Phong> listPhong = null;
		try {
			Query<Phong> query = session.createNativeQuery(
					"SELECT * from Phong p where p.maPhong not in (SELECT maPhong from ChiTietPhieuDatPhong ctpdp where thoiGianKetThuc is null)",
					Phong.class);

			listPhong = query.getResultList();

			t.commit();
			return listPhong;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return new ArrayList<Phong>();
		}
	}

	public boolean isRoomEmpty(Phong p) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<Phong> listPhong = null;
		try {
			Query<Phong> query = session.createNativeQuery("SELECT * from Phong p where p.maPhong = '" + p.getMaPhong()
					+ "' AND p.maPhong not in (SELECT maPhong from ChiTietPhieuDatPhong ctpdp where thoiGianKetThuc is null)",
					Phong.class);

			listPhong = query.getResultList();

			t.commit();
			return listPhong.contains(p);
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return false;
		}
	}

	public boolean updatePhong(Phong phong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			int tt = phong.isTrangThai() ? 1 : 0;
			Query<Phong> qr = session
					.createNativeQuery(
							"update Phong set maLoaiPhong = '" + phong.getLoaiPhong().getMaLoaiPhong()
									+ "',trangThai = " + tt + " where maPhong = '" + phong.getMaPhong() + "'",
							Phong.class);
			int p = qr.executeUpdate();

			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	public Phong getPhong(String maPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			Phong p = session.get(Phong.class, maPhong);

			t.commit();
			return p;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}
}
