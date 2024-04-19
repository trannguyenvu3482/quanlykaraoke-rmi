package iuh.fit.dao.impl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import iuh.fit.dao.ChiTietPhieuDatPhongDAO;
import iuh.fit.entity.ChiTietPhieuDatPhong;
import iuh.fit.util.HibernateUtil;

public class ChiTietPhieuDatPhongImpl extends UnicastRemoteObject implements ChiTietPhieuDatPhongDAO {
	/**
	 * 
	 */
	private static final long serialVersionUID = 912345678987654321L;
	/**
	 * 
	 */
	private SessionFactory factory = null;

	public ChiTietPhieuDatPhongImpl() throws RemoteException {
		factory = HibernateUtil.getMySessionFactory();
	}

	public boolean addChiTietPhieuDatPhong(ChiTietPhieuDatPhong chiTietPhieuDatPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			session.persist(chiTietPhieuDatPhong);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	public ChiTietPhieuDatPhong getChiTietPhieuDatPhongByActiveMaPhong(String maPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			Query<ChiTietPhieuDatPhong> query = session.createNativeQuery(
					"SELECT * FROM ChiTietPhieuDatPhong WHERE maPhong = '" + maPhong + "' AND thoiGianKetThuc IS NULL",
					ChiTietPhieuDatPhong.class);

			ChiTietPhieuDatPhong ctpdp = query.getSingleResult();
			t.commit();
			return ctpdp;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

	public List<ChiTietPhieuDatPhong> getAllChiTietPhieuDatPhongByMaPhieuDatPhong(String maPhieuDatPhong) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<ChiTietPhieuDatPhong> listChiTietPhieuDatPhong = null;
		try {
			// Handle PhieuDatPhong
			Query<ChiTietPhieuDatPhong> query = session.createNativeQuery(
					"SELECT * FROM ChiTietPhieuDatPhong where maPhieuDatPhong = '" + maPhieuDatPhong + "'",
					ChiTietPhieuDatPhong.class);

			listChiTietPhieuDatPhong = query.getResultList();

			// Finish
			t.commit();
			return listChiTietPhieuDatPhong;

		} catch (Exception e) {
			t.rollback();
			return listChiTietPhieuDatPhong;
		}
	}

	public List<ChiTietPhieuDatPhong> getAllChiTietPhieuDatPhongs() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<ChiTietPhieuDatPhong> listChiTietPhieuDatPhong = null;
		try {
			String hql = "from ChiTietPhieuDatPhong";
			listChiTietPhieuDatPhong = session.createQuery(hql, ChiTietPhieuDatPhong.class).getResultList();
			t.commit();
			return listChiTietPhieuDatPhong;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listChiTietPhieuDatPhong;
		}
	}
}
