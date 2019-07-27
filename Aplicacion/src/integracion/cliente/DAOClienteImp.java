package integracion.cliente;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import integracion.DAOConnection;
import negocio.cliente.TCliente;
import negocio.cliente.TClienteNoVIP;
import negocio.cliente.TClienteVIP;

public class DAOClienteImp implements DAOCliente {

	public int create(TCliente tCliente) {
		int id = -100;
		DAOConnection daoConnection = DAOConnection.getInstance();
		Connection connection = daoConnection.getConnection();
		
		if(connection!=null) {
			try {
				Statement statement = connection.createStatement();
				String query = "INSERT INTO cliente (dni,nombre,telefono,direccion,activo)"
						+ " VALUES ('"+ tCliente.getDNI() +"', '"
						+ tCliente.getNombre() +"', "
						+ tCliente.getTelefono() +", '"
						+ tCliente.getDireccion() +"', "
						+ tCliente.getActivo() +");";
				statement.executeUpdate(query);
				query = "SELECT last_insert_id() as last_id from cliente";
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next()) {
					id = resultSet.getInt("last_id");
					if(tCliente instanceof TClienteVIP) {
						query = "INSERT INTO cliente_vip (id,cod_tarjeta_oro)"
								+ " VALUES ("+ id +", "
								+ ((TClienteVIP) tCliente).getCodTarjetaOro() +");";
						statement.executeUpdate(query);
					} else {
						query = "INSERT INTO cliente_novip (id,limite_credito)"
								+ " VALUES ("+ id +", "
								+ ((TClienteNoVIP) tCliente).getLimiteCredito() +");";
						statement.executeUpdate(query);
					}
				}
			} catch (SQLException e) {
				id = -100;
			}
		}
		
		return id;
	}

	/**
	 * @see DAOCliente#read(int id)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	public TCliente read(int id) {
		TCliente tCliente = null;
		DAOConnection daoConnection = DAOConnection.getInstance();
		Connection connection = daoConnection.getConnection();
		
		if(connection!=null) {
			try {
				Statement statement = connection.createStatement();
				String query = "SELECT * FROM cliente WHERE id=" + id;
				ResultSet resultSet = statement.executeQuery(query);
				if(resultSet.next()) {
					TCliente tClienteResult = new TCliente(
							id,
							resultSet.getString("nombre"),
							resultSet.getInt("telefono"),
							resultSet.getString("direccion"),
							resultSet.getBoolean("activo"),
							resultSet.getString("dni")
							);
					query = "SELECT * FROM cliente_vip WHERE id=" + id;
					resultSet = statement.executeQuery(query);
					if(resultSet.next()) {
						tCliente = new TClienteVIP(
								resultSet.getInt("id"),
								tClienteResult.getNombre(),
								tClienteResult.getTelefono(),
								tClienteResult.getDireccion(),
								tClienteResult.getActivo(),
								tClienteResult.getDNI(),
								resultSet.getInt("cod_tarjeta_oro")
								);
					} else {
						query = "SELECT * FROM cliente_novip WHERE id=" + id;
						resultSet = statement.executeQuery(query);
						if(resultSet.next()) {
							tCliente = new TClienteNoVIP(
									resultSet.getInt("id"),
									tClienteResult.getNombre(),
									tClienteResult.getTelefono(),
									tClienteResult.getDireccion(),
									tClienteResult.getActivo(),
									tClienteResult.getDNI(),
									resultSet.getInt("limite_credito")
									);
						}
					}
				}
				
			} catch (SQLException e) {
				tCliente = null;
			}
		}
		
		return tCliente;
	}

	public TCliente findClienteByDNI(String dni) {
	TCliente tCliente = null;
	DAOConnection daoConnection = DAOConnection.getInstance();
	Connection connection = daoConnection.getConnection();
	
	if(connection!=null) {
		try {
			Statement statement = connection.createStatement();
			String query = "SELECT * FROM cliente WHERE dni='" + dni + "'";
			ResultSet resultSet = statement.executeQuery(query);
			if(resultSet.next()) {
				TCliente tClienteResult = new TCliente(
						resultSet.getInt("id"),
						resultSet.getString("nombre"),
						resultSet.getInt("telefono"),
						resultSet.getString("direccion"),
						resultSet.getBoolean("activo"),
						dni
						);
				query = "SELECT * FROM cliente_vip WHERE id=" + tClienteResult.getId();
				resultSet = statement.executeQuery(query);
				if(resultSet.next()) {
					tCliente = new TClienteVIP(
							resultSet.getInt("id"),
							tClienteResult.getNombre(),
							tClienteResult.getTelefono(),
							tClienteResult.getDireccion(),
							tClienteResult.getActivo(),
							dni,
							resultSet.getInt("cod_tarjeta_oro")
							);
				} else {
					query = "SELECT * FROM cliente_novip WHERE id=" + tClienteResult.getId();
					resultSet = statement.executeQuery(query);
					if(resultSet.next()) {
						tCliente = new TClienteNoVIP(
								resultSet.getInt("id"),
								tClienteResult.getNombre(),
								tClienteResult.getTelefono(),
								tClienteResult.getDireccion(),
								tClienteResult.getActivo(),
								dni,
								resultSet.getInt("limite_credito")
								);
					}
				}
			}
			
		} catch (SQLException e) {
			tCliente = null;
		}
	}
	
	return tCliente;
}

	public ArrayList<TCliente> readAll() {
		ArrayList<TCliente> listaClientes = new ArrayList<>();
		TCliente tCliente = null;
		DAOConnection daoConnection = DAOConnection.getInstance();
		Connection connection = daoConnection.getConnection();
		
		if(connection!=null) {
			try {
				Statement statement1 = connection.createStatement();
				Statement statement2 = connection.createStatement();
				String query = "SELECT * FROM cliente WHERE activo=1";
				ResultSet resultSet1 = statement1.executeQuery(query);
				ResultSet resultSet2;
				TCliente tClienteResult;
				while(resultSet1.next()) {
					tClienteResult = new TCliente(
							resultSet1.getInt("id"),
							resultSet1.getString("nombre"),
							resultSet1.getInt("telefono"),
							resultSet1.getString("direccion"),
							resultSet1.getBoolean("activo"),
							resultSet1.getString("dni")
							);
					query = "SELECT * FROM cliente_vip WHERE id=" + resultSet1.getInt("id");
					resultSet2 = statement2.executeQuery(query);
					if(resultSet2.next()) {
						tCliente = new TClienteVIP(
								resultSet2.getInt("id"),
								tClienteResult.getNombre(),
								tClienteResult.getTelefono(),
								tClienteResult.getDireccion(),
								tClienteResult.getActivo(),
								tClienteResult.getDNI(),
								resultSet2.getInt("cod_tarjeta_oro")
								);
					} else {
						query = "SELECT * FROM cliente_novip WHERE id=" + resultSet1.getInt("id");
						resultSet2 = statement2.executeQuery(query);
						if(resultSet2.next()) {
							tCliente = new TClienteNoVIP(
									resultSet2.getInt("id"),
									tClienteResult.getNombre(),
									tClienteResult.getTelefono(),
									tClienteResult.getDireccion(),
									tClienteResult.getActivo(),
									tClienteResult.getDNI(),
									resultSet2.getInt("limite_credito")
									);
						}
					}
					listaClientes.add(tCliente);
				}
				
			} catch (SQLException e) {
				tCliente = null;
			}
		}
		
		return listaClientes;
	}

	public int update(TCliente tCliente) {
		int id = -100;
		DAOConnection daoConnection = DAOConnection.getInstance();
		Connection connection = daoConnection.getConnection();
		
		if(connection!=null) {
			try {
				Statement statement = connection.createStatement();
				String query = "UPDATE cliente SET "
				+ "dni='" + tCliente.getDNI() + "', "
				+ "nombre='" + tCliente.getNombre() + "', "
				+ "telefono=" + tCliente.getTelefono() + ", "
				+ "direccion='" + tCliente.getDireccion() + "', "
				+ "activo=" + (tCliente.getActivo() ? 1 : 0) + " "
				+ "WHERE id=" + tCliente.getId();
				statement.executeUpdate(query);
				if(tCliente instanceof TClienteVIP) {
					query = "UPDATE cliente_vip SET "
							+ "cod_tarjeta_oro=" + ((TClienteVIP) tCliente).getCodTarjetaOro() + " "
							+ "WHERE id=" + tCliente.getId();
					statement.executeUpdate(query);
				} else {
					query = "UPDATE cliente_novip SET "
							+ "limite_credito=" + ((TClienteNoVIP) tCliente).getLimiteCredito() + " "
							+ "WHERE id=" + tCliente.getId();
					statement.executeUpdate(query);
				}
				id = tCliente.getId();
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
				String query = "UPDATE cliente SET activo=0 WHERE id=" + id;
				statement.executeUpdate(query);
				r = id;
			} catch (SQLException e) {
				r = -100;
			}
		}
		
		return r;
	}
}