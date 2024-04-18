/**
 * 
 */
package iuh.fit.server;

import java.rmi.registry.LocateRegistry;

import javax.naming.Context;
import javax.naming.InitialContext;

import iuh.fit.dao.PhongDAO;
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

			// Bind object to rmi server
			context.rebind(URL + "PhongDAO", phongDAO);

			// Log
			System.out.println("Server started");
		} catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
	}
}
