package integracion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DAOConnectionImp extends DAOConnection {

	private Connection connection;

	public Connection getConnection() {
		if(connection==null) {

			try {
				connection = DriverManager.getConnection("jdbc:mysql://localhost/fruteria_mr_pressman?" +
	                    "user=pressman&password=1234");
			} catch (SQLException e) {
				connection = null;
			}
		}
		
		return connection;
	}
	
	protected void finalize() {
		try {
			if(connection!=null&&!connection.isClosed())
				connection.close();
		} catch (SQLException e) {
			connection = null;
		}
	}
}