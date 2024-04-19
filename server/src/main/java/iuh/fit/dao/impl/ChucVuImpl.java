package iuh.fit.dao.impl;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;


import iuh.fit.dao.ChucVuDAO;
import iuh.fit.entity.ChucVu;
import iuh.fit.util.HibernateUtil;

public class ChucVuImpl  extends UnicastRemoteObject implements ChucVuDAO, Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4827591042958585742L;
	/**
	 * 
	 */
	private SessionFactory factory = null;
	
	public ChucVuImpl() throws RemoteException {
		factory = HibernateUtil.getMySessionFactory();
	}
	public ChucVu getChucVu(String maChucVu) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			ChucVu cv = session.get(ChucVu.class, maChucVu);

			t.commit();
			return cv;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}

	public List<ChucVu> getAllChucVus() {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();
		List<ChucVu> listChucVu = null;
		try {
			String hql = "from ChiTietDichVu";
			listChucVu = session.createQuery(hql, ChucVu.class).getResultList();
			t.commit();
			return listChucVu;
		} catch (Exception e) {
			System.out.println("ROLLBACK!");
			t.rollback();
			return listChucVu;
		}
	}

	public ChucVu getChucVuByName(String name) {
		Session session = factory.getCurrentSession();
		Transaction t = session.beginTransaction();

		try {
			Query<ChucVu> query = session.createNativeQuery("select * FROM ChucVu WHERE tenChucVu = N'" + name + "'",
					ChucVu.class);
			ChucVu cv = query.getResultList().get(0);
			t.commit();
			return cv;

		} catch (Exception e) {
			t.rollback();
			return null;
		}
	}
}
