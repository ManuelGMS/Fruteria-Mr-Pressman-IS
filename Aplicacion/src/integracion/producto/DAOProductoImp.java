package integracion.producto;

import negocio.producto.TProducto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import integracion.DAOConnection;

public class DAOProductoImp implements DAOProducto {

	public int create(TProducto tProducto) {
		int id = -100;
		DAOConnection daoConnection = DAOConnection.getInstance();
		Connection connection = daoConnection.getConnection();
		
		if(connection!=null) {
			try {
				Statement statement = connection.createStatement();
				String query = "INSERT INTO producto (nombre, precio, unidades, activo, marca_id)"
						+ " VALUES ('"+ tProducto.getNombre() +"', "
						+ tProducto.getPrecio() +", "
						+ tProducto.getUnidades() +", "
						+ (tProducto.getActivo() ? 1 : 0) +", "
						+ tProducto.getIdMarca() +");";
				statement.executeUpdate(query);
				query = "SELECT last_insert_id() as last_id from producto";
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next()) {
					id = resultSet.getInt("last_id");
				}
			} catch (SQLException e) {
				id = -100;
			}
		}
		
		return id;
	}
	
	@Override
	public TProducto findByName(String nombre) {
		TProducto tProducto = null;
		DAOConnection daoconnection = DAOConnection.getInstance();
		Connection connection = daoconnection.getConnection();
		
		if(connection!=null) {
			try{
				Statement statement =  connection.createStatement();
				String query = "SELECT * FROM producto WHERE nombre='" + nombre + "'";
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next()) {
					tProducto = new TProducto(
							resultSet.getInt("id"),
							resultSet.getInt("marca_id"),
							nombre,
							resultSet.getFloat("precio"),
							resultSet.getInt("unidades"),
							resultSet.getBoolean("activo")
							);
				}
			}catch(SQLException e){
				tProducto = null;
			}
		}
		
		return tProducto;
	}

	public TProducto read(int id) {
		TProducto tProducto = null;
		DAOConnection daoConnection = DAOConnection.getInstance();
		Connection connection = daoConnection.getConnection();
		
		if(connection!=null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM producto WHERE id=" +id;
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next()) {
					tProducto = new TProducto(
							id,
							resultSet.getInt("marca_id"),
							resultSet.getString("nombre"),
							resultSet.getFloat("precio"),
							resultSet.getInt("unidades"),
							resultSet.getBoolean("activo")
							);
				}
			} catch (SQLException e) {
				tProducto = null;
			}
		}
		
		return tProducto;
	}

	public ArrayList<TProducto> readAll() {
		ArrayList<TProducto>  listaProductos = new ArrayList<>();
		DAOConnection daoConnection = DAOConnection.getInstance();
		Connection connection = daoConnection.getConnection();
		
		if(connection!=null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM producto WHERE activo=1";
				ResultSet resultSet = statement.executeQuery(query);
				TProducto tProducto;
				while(resultSet.next()) {
					tProducto = new TProducto(
							resultSet.getInt("id"),
							resultSet.getInt("marca_id"),
							resultSet.getString("nombre"),
							resultSet.getFloat("precio"),
							resultSet.getInt("unidades"),
							resultSet.getBoolean("activo")
							);
					listaProductos.add(tProducto);
				}
			} catch (SQLException e) {
				listaProductos = null;
			}
		}
		
		return listaProductos;
	}

	public int update(TProducto tProducto) {
		int id = -100;
		DAOConnection daoConnection = DAOConnection.getInstance();
		Connection connection = daoConnection.getConnection();
		
		if(connection!=null) {
			try {
				Statement statement = connection.createStatement();
				String query = "UPDATE producto SET "
						+ "nombre='" + tProducto.getNombre() + "', "
						+ "precio=" + tProducto.getPrecio() + ", "
						+ "unidades=" + tProducto.getUnidades() + ", "
						+ "activo=" + (tProducto.getActivo() ? 1 : 0) + ", "
						+ "marca_id=" + tProducto.getIdMarca() + " "
						+ "WHERE id=" + tProducto.getId();
				statement.executeUpdate(query);
				id = tProducto.getId();
			} catch (SQLException e) {
				id = -100;
			}
		}
		
		return id;
	}

	public int delete(int id) {
		int r = -100;
		DAOConnection daoConnection = DAOConnection.getInstance();
		Connection connection = daoConnection.getConnection();
		
		if(connection!=null) {
			try {
				Statement statement = connection.createStatement();
				String query = "UPDATE producto SET activo=0 WHERE id=" + id;
				statement.executeUpdate(query);
				r = id;
			} catch (SQLException e) {
				r = -100;
			}
		}
		
		return r;
	}
}
