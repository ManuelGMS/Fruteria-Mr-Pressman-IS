package negocio;

import negocio.cliente.SACliente;
import negocio.cliente.SAClienteImp;
import negocio.marca.SAMarca;
import negocio.marca.SAMarcaImp;
import negocio.pais.SAPais;
import negocio.pais.SAPaisImp;
import negocio.producto.SAProducto;
import negocio.producto.SAProductoImp;
import negocio.venta.SAVenta;
import negocio.venta.SAVentaImp;

public class FactoriaNegocioImp extends FactoriaNegocio {

	public SAPais generateSAPais() {
		return new SAPaisImp();
	}


	public SAMarca generateSAMarca() {
		return new SAMarcaImp();
	}


	public SAProducto generateSAProducto() {
		return new SAProductoImp();
	}


	public SAVenta generateSAVenta() {
		return new SAVentaImp();
	}


	public SACliente generateSACliente() {
		return new SAClienteImp();
	}

}