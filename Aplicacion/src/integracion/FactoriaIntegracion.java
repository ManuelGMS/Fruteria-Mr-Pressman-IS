package integracion;

import integracion.cliente.DAOCliente;
import integracion.marca.DAOMarca;
import integracion.pais.DAOPais;
import integracion.producto.DAOProducto;
import integracion.venta.DAOVenta;

public abstract class FactoriaIntegracion {

	private static FactoriaIntegracion instance;

	public static FactoriaIntegracion getInstance() {
		
		if(instance==null) {
			instance = new FactoriaIntegracionImp();
		}
		
		return instance;
	}

	public abstract DAOPais generateDAOPais();

	public abstract DAOMarca generateDAOMarca();

	public abstract DAOProducto generateDAOProducto();

	public abstract DAOVenta generateDAOVenta();

	public abstract DAOCliente generateDAOCliente();
}