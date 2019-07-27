/**
 * 
 */
package presentacion;

import java.util.ArrayList;

import negocio.FactoriaNegocio;
import negocio.cliente.SACliente;
import negocio.cliente.TCliente;
import negocio.cliente.TClienteNoVIP;
import negocio.cliente.TClienteVIP;
import negocio.marca.SAMarca;
import negocio.marca.TMarca;
import negocio.pais.SAPais;
import negocio.pais.TPais;
import negocio.producto.SAProducto;
import negocio.producto.TProducto;
import negocio.venta.SAVenta;
import negocio.venta.TVenta;
import presentacion.cliente.GUICliente;
import presentacion.marca.GUIMarca;
import presentacion.pais.GUIPais;
import presentacion.producto.GUIProducto;
import presentacion.venta.GUIVenta;

public class ControladorImp extends Controlador {
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private SAPais sAPais;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private SAMarca sAMarca;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private SAProducto sAProducto;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private SAVenta sAVenta;
	/** 
	 * <!-- begin-UML-doc -->
	 * <!-- end-UML-doc -->
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	private SACliente sACliente;
	
	public ControladorImp(){
		FactoriaNegocio factoriaNegocio = FactoriaNegocio.getInstance();
		sAPais = factoriaNegocio.generateSAPais();
		sAMarca = factoriaNegocio.generateSAMarca();
		sAProducto = factoriaNegocio.generateSAProducto();
		sACliente = factoriaNegocio.generateSACliente();
		sAVenta = factoriaNegocio.generateSAVenta();
	}
	/** 
	 * (sin Javadoc)
	 * @see Controlador#accion(int evento, Object datos)
	 * @generated "UML a Java (com.ibm.xtools.transform.uml2.java5.internal.UML2JavaTransform)"
	 */
	@SuppressWarnings("unchecked")
	public void accion(int evento, Object datos) {
		TPais tP;
		TMarca tM;
		TProducto tPr;
		TVenta tV;
		TCliente TC;
		TClienteVIP TClV;
		TClienteNoVIP TClNV;
		int res;
		switch(evento){
		case Events.GUI_MOSTRAR:
			GUIMain.getInstance();
			break;
		case Events.CREAR_GUI_PAIS:
			GUIPais.getInstance();
			GUIPais.getInstance().update(Events.UPDATE_PAIS_COMBO_BOX, null);
			break;
		case Events.ALTA_PAIS:
			tP = (TPais) datos;
			res = this.sAPais.create(tP);
			if(res > 0)
				GUIPais.getInstance().update(Events.RES_ALTA_PAIS_OK, res);
			else
				GUIPais.getInstance().update(Events.RES_ALTA_PAIS_KO, res);
			break;
		case Events.ACTUALIZAR_PAIS:
			tP = (TPais) datos;
			res = this.sAPais.update(tP);
			if(res > 0)
				GUIPais.getInstance().update(Events.RES_ACTUALIZAR_PAIS_OK, res);
			else
				GUIPais.getInstance().update(Events.RES_ACTUALIZAR_PAIS_KO, res);
			break;
		case Events.LISTAR_PAIS:
			ArrayList<TPais> respP = this.sAPais.readAll();
			if(respP != null)
				GUIPais.getInstance().update(Events.RES_LISTAR_PAIS_OK, respP);
			else
				GUIPais.getInstance().update(Events.RES_LISTAR_PAIS_KO, respP);
			break;
		case Events.BUSCAR_PAIS:
			tP = this.sAPais.read((int)datos);
			if(tP != null)
				GUIPais.getInstance().update(Events.RES_BUSCAR_PAIS_OK, tP);
			else
				GUIPais.getInstance().update(Events.RES_BUSCAR_PAIS_KO, (int)datos);
			break;
		case Events.BAJA_PAIS:
			res = this.sAPais.delete((int)datos);
			if(res > 0)
				GUIPais.getInstance().update(Events.RES_BAJA_PAIS_OK, res);
			else
				GUIPais.getInstance().update(Events.RES_BAJA_PAIS_KO, res);
			break;
		case Events.CREAR_GUI_MARCA:
			GUIMarca.getInstance();
			GUIMarca.getInstance().update(Events.UPDATE_MARCA_COMBO_BOX, null);
			break;
		case Events.ALTA_MARCA:
			tM = (TMarca) datos;
			res = this.sAMarca.create(tM);
			if(res > 0)
				GUIMarca.getInstance().update(Events.RES_ALTA_MARCA_OK, res);
			else
				GUIMarca.getInstance().update(Events.RES_ALTA_MARCA_KO, res);
			break;
		case Events.ACTUALIZAR_MARCA:
			tM = (TMarca) datos;
			res = this.sAMarca.update(tM);
			if(res > 0)
				GUIMarca.getInstance().update(Events.RES_ACTUALIZAR_MARCA_OK, res);
			else
				GUIMarca.getInstance().update(Events.RES_ACTUALIZAR_MARCA_KO, res);
			break;
		case Events.LISTAR_MARCA:
			ArrayList<TMarca> respM = this.sAMarca.readAll();
			if(respM != null)
				GUIMarca.getInstance().update(Events.RES_LISTAR_MARCA_OK, respM);
			else
				GUIMarca.getInstance().update(Events.RES_LISTAR_MARCA_KO, respM);
			break;
		case Events.BUSCAR_MARCA:
			tM = this.sAMarca.read((int)datos);
			if(tM != null)
				GUIMarca.getInstance().update(Events.RES_BUSCAR_MARCA_OK, tM);
			else
				GUIMarca.getInstance().update(Events.RES_BUSCAR_MARCA_KO, (int)datos);
			break;
		case Events.BAJA_MARCA:
			res = this.sAMarca.delete((int)datos);
			if(res > 0)
				GUIMarca.getInstance().update(Events.RES_BAJA_MARCA_OK, res);
			else
				GUIMarca.getInstance().update(Events.RES_BAJA_MARCA_KO, res);
			break;
		case Events.CREAR_GUI_PRODUCTO:
			GUIProducto.getInstance();
			GUIProducto.getInstance().update(Events.UPDATE_PRODUCTO_COMBO_BOX, null);
			break;
		case Events.ALTA_PRODUCTO:
			tPr = (TProducto) datos;
			res = this.sAProducto.create(tPr);
			if(res > 0)
				GUIProducto.getInstance().update(Events.RES_ALTA_PRODUCTO_OK, res);
			else
				GUIProducto.getInstance().update(Events.RES_ALTA_PRODUCTO_KO, res);
			break;
		case Events.ACTUALIZAR_PRODUCTO:
			tPr = (TProducto) datos;
			res = this.sAProducto.update(tPr);
			if(res > 0)
				GUIProducto.getInstance().update(Events.RES_ACTUALIZAR_PRODUCTO_OK, res);
			else
				GUIProducto.getInstance().update(Events.RES_ACTUALIZAR_PRODUCTO_KO, res);
			break;
		case Events.LISTAR_PRODUCTO:
			ArrayList<TProducto> resPr = this.sAProducto.readAll();
			if(resPr != null)
				GUIProducto.getInstance().update(Events.RES_LISTAR_PRODUCTO_OK, resPr);
			else
				GUIProducto.getInstance().update(Events.RES_LISTAR_PRODUCTO_KO, resPr);
			break;
		case Events.BUSCAR_PRODUCTO:
			tPr = this.sAProducto.read((int)datos);
			if(tPr != null)
				GUIProducto.getInstance().update(Events.RES_BUSCAR_PRODUCTO_OK, tPr);
			else
				GUIProducto.getInstance().update(Events.RES_BUSCAR_PRODUCTO_KO, tPr);
			break;
		case Events.BAJA_PRODUCTO:
			res = this.sAProducto.delete((int)datos);
			if(res > 0)
				GUIProducto.getInstance().update(Events.RES_BAJA_PRODUCTO_OK, res);
			else
				GUIProducto.getInstance().update(Events.RES_BAJA_PRODUCTO_KO, res);
			break;
		case Events.CREAR_GUI_VENTA:
			GUIVenta.getInstance();
			GUIVenta.getInstance().update(Events.UPDATE_VENTA_COMBO_BOX, null);
			break;
		case Events.ABRIR_VENTA:
			int id = (int) datos;
			tV = this.sAVenta.openSale(id);
			if(tV != null)
				GUIVenta.getInstance().update(Events.RES_ABRIR_VENTA_OK, tV);
			else
				GUIVenta.getInstance().update(Events.RES_ABRIR_VENTA_KO, id);
			break;
		
		case Events.LISTAR_VENTA:
			ArrayList<TVenta> respV = this.sAVenta.readAll();
			if(respV != null)
				GUIVenta.getInstance().update(Events.RES_LISTAR_VENTA_OK, respV);
			else
				GUIVenta.getInstance().update(Events.RES_LISTAR_VENTA_KO, respV);
			break;
		case Events.BUSCAR_VENTA:
			tV = this.sAVenta.read((int)datos);
			if(tV != null)
				GUIVenta.getInstance().update(Events.RES_BUSCAR_VENTA_OK, tV);
			else
				GUIVenta.getInstance().update(Events.RES_BUSCAR_VENTA_KO, (int)datos);
			break;
		case Events.DEVOLUCION_VENTA:
			ArrayList<Integer> data = (ArrayList<Integer>)datos;
			res = this.sAVenta.devolution(data.get(0),data.get(1),data.get(2));
			if(res > 0)
				GUIVenta.getInstance().update(Events.RES_DEVOLUCION_VENTA_OK, res);
			else
				GUIVenta.getInstance().update(Events.RES_DEVOLUCION_VENTA_KO, res);
			break;
		case Events.CERRAR_VENTA:
			res = this.sAVenta.closeSale((TVenta)datos);
			if(res > 0)
				GUIVenta.getInstance().update(Events.RES_CERRAR_VENTA_OK, res);
			else
				GUIVenta.getInstance().update(Events.RES_CERRAR_VENTA_KO, res);
			GUIVenta.getInstance().setVenta(null);
			break;
		case Events.CREAR_GUI_CLIENTE:
			GUICliente.getInstance();
			GUICliente.getInstance().update(Events.UPDATE_CLIENTE_COMBO_BOX, null);
			break;
		case Events.ALTA_CLIENTE_VIP:
			TClV = (TClienteVIP) datos;
			res = this.sACliente.create(TClV);
			if(res > 0)
				GUICliente.getInstance().update(Events.RES_ALTA_CLIENTE_VIP_OK, res);
			else
				GUICliente.getInstance().update(Events.RES_ALTA_CLIENTE_VIP_KO, res);
			break;
		case Events.ALTA_CLIENTE_NO_VIP:
			TClNV = (TClienteNoVIP) datos;
			res = this.sACliente.create(TClNV);
			if(res > 0)
				GUICliente.getInstance().update(Events.RES_ALTA_CLIENTE_NO_VIP_OK, res);
			else
				GUICliente.getInstance().update(Events.RES_ALTA_CLIENTE_NO_VIP_KO, res);
			break;
		case Events.ACTUALIZAR_CLIENTE:
			TC= (TCliente) datos;
			res = this.sACliente.update(TC);
			if(res > 0)
				GUICliente.getInstance().update(Events.RES_ACTUALIZAR_CLIENTE_OK, res);
			else
				GUICliente.getInstance().update(Events.RES_ACTUALIZAR_CLIENTE_KO, res);
			break;
		case Events.BUSCAR_ACTUALIZAR_CLIENTE:
			TC = this.sACliente.read((int)datos);
			if(TC != null)
				GUICliente.getInstance().update(Events.RES_BUSCAR_ACTUALIZAR_CLIENTE_OK, TC);
			else
				GUICliente.getInstance().update(Events.RES_ACTUALIZAR_CLIENTE_KO, -3);
			break;
		case Events.LISTAR_CLIENTE:
			ArrayList<TCliente> resC = this.sACliente.readAll();
			if(resC != null)
				GUICliente.getInstance().update(Events.RES_LISTAR_CLIENTE_OK, resC);
			else
				GUICliente.getInstance().update(Events.RES_LISTAR_CLIENTE_KO,resC);
			break;
		case Events.BUSCAR_CLIENTE:
			TC = this.sACliente.read((int)datos);
			if(TC != null)
				GUICliente.getInstance().update(Events.RES_BUSCAR_CLIENTE_OK, TC);
			else
				GUICliente.getInstance().update(Events.RES_BUSCAR_CLIENTE_KO, (int)datos);
			break;
		case Events.BAJA_CLIENTE:
			res = this.sACliente.delete((int)datos);
			if(res > 0)
				GUICliente.getInstance().update(Events.RES_BAJA_CLIENTE_OK, res);
			else
				GUICliente.getInstance().update(Events.RES_BAJA_CLIENTE_KO, res);
			break;
		}
	}
}