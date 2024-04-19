package iuh.fit.dao.impl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import iuh.fit.dao.LoaiPhongDAO;
import iuh.fit.entity.LoaiPhong;


import iuh.fit.util.HibernateUtil;

public class LoaiPhongImpl extends UnicastRemoteObject implements LoaiPhongDAO, Serializable{
	private static final long serialVersionUID = 6318153945323394885L;
	private SessionFactory factory = null;


	public LoaiPhongImpl() throws RemoteException {
		factory = HibernateUtil.getMySessionFactory();
	}

	public boolean addLoaiPhong(LoaiPhong lp) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			String maLP = getNextMaLP();
			lp.setMaLoaiPhong(maLP);
			session.persist(lp);
			t.commit();
			return true;

		} catch (Exception e) {
			t.rollback();
			return false;
		}
	}

	private String getNextMaLP() {
		String idPrefix = "LP";

		int count = countLoaiPhong();

		if (count < 0 || count > 999) {
			return null;
		}

		return idPrefix + String.format("%03d", count + 1);
	}

	private int countLoaiPhong() {
		Session session = factory.getCurrentSession();

		try {
			String hql = "From LoaiPhong";
			Query<LoaiPhong> query = session.createQuery(hql, LoaiPhong.class);
			List<LoaiPhong> listLoaiPhong = query.getResultList();

			return listLoaiPhong.size();

		} catch (Exception e) {
			return -1;
		}
	}

	public List<LoaiPhong> getAllLoaiPhongs() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<LoaiPhong> listLoaiPhong = null;
		try {
			String hql = "from LoaiPhong";
			listLoaiPhong = session.createQuery(hql, LoaiPhong.class).getResultList();
			t.commit();
			return listLoaiPhong;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listLoaiPhong;
		}
	}

	public LoaiPhong getLoaiPhong(String tenLP, int kt) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			Query<LoaiPhong> query = session.createNativeQuery(
					"select * FROM LoaiPhong WHERE tenLoaiPhong = N'" + tenLP + "'" + " AND kichThuoc = " + kt,
					LoaiPhong.class);

			LoaiPhong lp = query.getResultList().get(0);

			t.commit();
			return lp;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}
}
