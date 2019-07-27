package integracion.pais;

import negocio.pais.TPais;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import integracion.DAOConnection;

public class DAOPaisImp implements DAOPais {
	
	public int create(TPais tPais) {
		int id = -100; 
		DAOConnection daoconnection = DAOConnection.getInstance();
		Connection connection = daoconnection.getConnection();
		
		if(connection!=null) {
			try{
				Statement statement = connection.createStatement();
				String query = "INSERT INTO pais (nombre, activo, contador_marcas)" +
				                " VALUES ('" +
						         tPais.getNombre() + "'," +
				                 (tPais.getActivo() ? 1 : 0) + "," +
						         tPais.getContadorMarcas() + ");";
				statement.executeUpdate(query);
				query = "SELECT last_insert_id() as last_id from pais";
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

	public TPais read(int id) {
		TPais tPais = null;
		DAOConnection daoconnection = DAOConnection.getInstance();
		Connection connection = daoconnection.getConnection();
		
		if(connection!=null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM pais WHERE id=" + id;
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next()) {
					tPais = new TPais(
							id,
							resultSet.getString("nombre"),
							resultSet.getBoolean("activo"),
							resultSet.getInt("contador_marcas")
							);
				}
			} catch (SQLException e) {
				tPais = null;
			}
		}

		return tPais;
	}

	public TPais findByName(String nombre) {
		TPais tPais = null;
		DAOConnection daoconnection = DAOConnection.getInstance();
		Connection connection = daoconnection.getConnection();
		
		if(connection!=null) {
			try{
				Statement statement =  connection.createStatement();
				String query = "SELECT * FROM pais WHERE nombre='" + nombre + "'";
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next()){
					tPais = new TPais(resultSet.getInt("id"), 
							nombre,
							resultSet.getBoolean("activo"),
							resultSet.getInt("contador_marcas"));
				}
					
			}catch(SQLException e){
				tPais = null;
			}
		}
			
		return tPais;
	}

	public ArrayList<TPais> readAll() {
		TPais tPais = null;
		ArrayList<TPais> listapais = new ArrayList<>();
		DAOConnection daoconnection = DAOConnection.getInstance();
		Connection connection = daoconnection.getConnection();
		
		if(connection!=null) {
			try{
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM pais WHERE activo=1";
				ResultSet resultSet = statement.executeQuery(query);
				while(resultSet.next()){
					tPais = new TPais(resultSet.getInt("id"), 
							resultSet.getString("nombre"),
							resultSet.getBoolean("activo"),
							resultSet.getInt("contador_marcas"));
					listapais.add(tPais);
				}
				
			}catch(SQLException e){
				listapais = null;
			}
		}
		
		return listapais;
	}

	public int update(TPais tPais) {
		int id = -100;
		DAOConnection daoconnection = DAOConnection.getInstance();
		Connection connection = daoconnection.getConnection();
		
		if(connection!=null) {
			try{
				Statement statement = connection.createStatement();
				String query = "UPDATE pais SET "
				+ "nombre = '" + tPais.getNombre()
				+ "', activo = " + (tPais.getActivo()?1:0)
				+ ", contador_marcas = " + tPais.getContadorMarcas()
				+ " WHERE id=" + tPais.getId();
				statement.executeUpdate(query);
				id = tPais.getId();
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
				String query = "UPDATE pais SET activo=0 WHERE id = " + id;
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