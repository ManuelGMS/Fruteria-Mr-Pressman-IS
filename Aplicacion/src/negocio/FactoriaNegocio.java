
package negocio;

import negocio.cliente.SACliente;
import negocio.marca.SAMarca;
import negocio.pais.SAPais;
import negocio.producto.SAProducto;
import negocio.venta.SAVenta;


public abstract class FactoriaNegocio {

	private static FactoriaNegocio instance;

	public static FactoriaNegocio getInstance() {
		if(instance == null) instance = new FactoriaNegocioImp();
		return instance;
	}


	public abstract SAPais generateSAPais();


	public abstract SAMarca generateSAMarca();

	public abstract SAProducto generateSAProducto();

	public abstract SAVenta generateSAVenta();


	public abstract SACliente generateSACliente();
}