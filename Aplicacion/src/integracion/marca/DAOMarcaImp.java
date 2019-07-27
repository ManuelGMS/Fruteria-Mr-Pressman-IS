package integracion.marca;

import negocio.marca.TMarca;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import integracion.DAOConnection;

public class DAOMarcaImp implements DAOMarca {

	public int create(TMarca tMarca) {
		int id = -100;
		DAOConnection daoconnection = DAOConnection.getInstance();
		Connection connection = daoconnection.getConnection();
		
		if(connection!=null) {
			try{
				Statement statement = connection.createStatement();
				String query = "INSERT INTO marca (nombre, activo, contador_productos, pais_id) " +
				                "VALUES ('" +
						         tMarca.getNombre() + "'," +
				                 (tMarca.getActivo() ? 1:0) + "," +
						         tMarca.getContadorProductos() + "," +
				                 tMarca.getIdPais() +");";
				statement.executeUpdate(query);
				query = "SELECT last_insert_id() as last_id from marca";
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next()) {
					id = resultSet.getInt("last_id");
				}
			}catch(SQLException e){
				id = -100;
			}
		}
		
		return id;
	}

	public TMarca findByName(String nombre) {
		TMarca tMarca = null;
		DAOConnection daoconnection = DAOConnection.getInstance();
		Connection connection = daoconnection.getConnection();
		
		if(connection!=null) {
			try{
				Statement statement =  connection.createStatement();
				String query = "SELECT * FROM marca WHERE nombre = '" + nombre + "'";
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next()){
					tMarca = new TMarca(
							resultSet.getInt("id"), 
							resultSet.getInt("pais_id"),
							resultSet.getBoolean("activo"),
							nombre,
							resultSet.getInt("contador_productos")
							);
				}
					
			}catch(SQLException e){
				tMarca = null;
			}
		}
		
		return tMarca;
	}

	public TMarca read(int id) {
		TMarca tMarca= null;
		DAOConnection daoconnection = DAOConnection.getInstance();
		Connection connection = daoconnection.getConnection();
		
		if(connection!=null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM marca WHERE id=" + id;
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next()) {
					tMarca = new TMarca(
							id, 
							resultSet.getInt("pais_id"),
							resultSet.getBoolean("activo"),
							resultSet.getString("nombre"),
							resultSet.getInt("contador_productos"));
				}
			} catch (SQLException e) {
				tMarca = null;
			}
		}

		return tMarca;
	}

	public ArrayList<TMarca> readAll() {
		TMarca tMarca = null;
		ArrayList<TMarca> listamarcas = new ArrayList<>();
		DAOConnection daoconnection = DAOConnection.getInstance();
		Connection connection = daoconnection.getConnection();
		
		if(connection!=null) {
			try{
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM marca WHERE activo=1";
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next()){
					tMarca = new TMarca(resultSet.getInt("id"), 
							resultSet.getInt("pais_id"),
							resultSet.getBoolean("activo"),
							resultSet.getString("nombre"),
							resultSet.getInt("contador_productos"));
					listamarcas.add(tMarca);
				}
				
			}catch(SQLException e){
				listamarcas = null;
			}
		}
		
		return listamarcas;
	}

	public int update(TMarca tMarca) {
		int id = -100;
		DAOConnection daoconnection = DAOConnection.getInstance();
		Connection connection = daoconnection.getConnection();
		
		if(connection!=null) {
			try{
				Statement statement = connection.createStatement();
				String query = "UPDATE marca SET "
						+ "nombre = '" + tMarca.getNombre() 
						+ "', activo = " + (tMarca.getActivo() ? 1:0)
						+ ", pais_id = " + tMarca.getIdPais()
						+ ", contador_productos = " + tMarca.getContadorProductos()
						+ " WHERE id = " + tMarca.getId();
				statement.executeUpdate(query);
				id = tMarca.getId();
			}catch(SQLException e){
				id = -100;
			}
		}
		
		return id;
	}

	public int delete(int id) {
		int result = -100;
		DAOConnection daoconnection = DAOConnection.getInstance();
		Connection connection = daoconnection.getConnection();
		
		if(connection!=null) {
			try{
				Statement statement = connection.createStatement();
				String query = "UPDATE marca SET activo=0 WHERE id = " + id;
				statement.executeUpdate(query);
				result = id;
			}
			catch(SQLException e){
				result = -100;
			}
		}
		
		return result;
	}
}