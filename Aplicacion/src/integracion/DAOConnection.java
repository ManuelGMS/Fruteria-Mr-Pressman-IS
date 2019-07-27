package integracion;

import java.sql.Connection;

public abstract class DAOConnection {

	private static DAOConnection instance;

	public static DAOConnection getInstance() {
		if(instance==null) {
			instance = new DAOConnectionImp();
		}
		
		return instance;
	}

	public abstract Connection getConnection();
}