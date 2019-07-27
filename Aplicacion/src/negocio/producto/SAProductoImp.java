package negocio.producto;

import java.util.ArrayList;

import integracion.FactoriaIntegracion;
import integracion.marca.DAOMarca;
import integracion.producto.DAOProducto;
import negocio.ComprobadorSintactico;
import negocio.marca.TMarca;

public class SAProductoImp implements SAProducto {
	
	/*
	 * TABLA DE ERRORES
	 * 
	 * -1 - tProducto == null
	 * -2 - Validez sintáctica
	 * -3 - No existe ninguna marca para ese producto
	 * -4 - La marca de ese producto no está activa
	 * -5 - El producto ya está activo y no se puede volver a crear
	 * -100 - Error de Base de Datos
	 */
	public int create(TProducto tProducto) {
		
		int id = -1;
		if (tProducto != null) {
			String nombre = tProducto.getNombre();
			int idMarca = tProducto.getIdMarca();
			int unidades = tProducto.getUnidades();
			float precio = tProducto.getPrecio();
			if(ComprobadorSintactico.isName(nombre) && ComprobadorSintactico.isPositive(idMarca)
					&& ComprobadorSintactico.isPositive(unidades) && ComprobadorSintactico.isPositive(precio)) {
				DAOProducto daoProducto = FactoriaIntegracion.getInstance().generateDAOProducto();
				DAOMarca daoMarca = FactoriaIntegracion.getInstance().generateDAOMarca();
				TMarca tMarcaResult = daoMarca.read(idMarca);
				if (tMarcaResult != null) {
					if(tMarcaResult.getActivo()){
						TProducto tProductoResult = daoProducto.findByName(nombre);
						tProducto.setActivo(true);
						if (tProductoResult == null) {
							id = daoProducto.create(tProducto);
							if (id != -100) {
								tMarcaResult.setContadorProductos(tMarcaResult.getContadorProductos() + 1);
								int idResult = daoMarca.update(tMarcaResult);
								if (idResult == -100) id = -100;
							}
						}
						else {
							if (!tProductoResult.getActivo()) {
								tProducto.setId(tProductoResult.getId());
								id = daoProducto.update(tProducto);
								if (id != -100) {
									tMarcaResult.setContadorProductos(tMarcaResult.getContadorProductos() + 1);
									int idResult = daoMarca.update(tMarcaResult);
									if (idResult == -100) id = -100;
								}
							}
							else id = -5;
						}
					}
					else id = -4;
				}
				else id = -3;
			}
			else id = -2;
		}
		return id;
	}
	
	/*
	 * TABLA DE ERRORES
	 * 
	 * null - Dependencia sintáctica
	 */
	public TProducto read(int id) {
		
		TProducto tProducto = null;
		if(ComprobadorSintactico.isPositive(id)){
			DAOProducto daoProducto = FactoriaIntegracion.getInstance().generateDAOProducto();
			tProducto = daoProducto.read(id);
		}
		return tProducto;
	}

	public ArrayList<TProducto> readAll() {
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generateDAOProducto();
		ArrayList<TProducto> listaProductos = daoProducto.readAll();
		return listaProductos;
	}

	/*
	 * TABLA DE ERRORES
	 * 
	 * -1 - tProducto == null
	 * -2 - Validez sintáctica
	 * -3 - No existe el producto que se quiere actualizar
	 * -4 - El producto no está activo
	 * -5 - No existe la marca de ese producto
	 * -6 - La marca de ese producto no está activa
	 * -7 - Se ha querido actualizar el nombre al de otro ya existente
	 * -100 - Error de Base de Datos
	 */
	public int update(TProducto tProducto) {
		
		int id = -1;
		if (tProducto != null) {
			String nombre = tProducto.getNombre();
			int idMarca = tProducto.getIdMarca();
			int unidades = tProducto.getUnidades();
			float precio = tProducto.getPrecio();
			int idProducto = tProducto.getId();
			if(ComprobadorSintactico.isName(nombre) && ComprobadorSintactico.isPositive(idMarca)
					&& ComprobadorSintactico.isPositive(unidades) && ComprobadorSintactico.isPositive(precio)
					&& ComprobadorSintactico.isPositive(idProducto)) {
				DAOProducto daoProducto = FactoriaIntegracion.getInstance().generateDAOProducto();
				TProducto tProductoResult = daoProducto.read(idProducto);
				if (tProductoResult != null) {
					if (!tProducto.getNombre().equalsIgnoreCase(tProductoResult.getNombre())) {
						TProducto tProductoResult1 = daoProducto.findByName(tProducto.getNombre());
						if (tProductoResult1 != null) id = -7;
					}
					if (id != -7){
						boolean activoResult = tProductoResult.getActivo();
						boolean activo = tProducto.getActivo();
						if(activoResult && activo){
							DAOMarca daoMarca = FactoriaIntegracion.getInstance().generateDAOMarca();
							TMarca tMarca = daoMarca.read(idMarca);
							if (tMarca != null) {
								if(tMarca.getActivo()){
									id = daoProducto.update(tProducto);
									if (tProducto.getIdMarca() != tProductoResult.getIdMarca()) {
										tMarca.setContadorProductos(tMarca.getContadorProductos() + 1);
										int idResult = daoMarca.update(tMarca);
										if (idResult != -100) {
											TMarca tMarcaResult = daoMarca.read(tProductoResult.getIdMarca());
											if (tMarcaResult != null) {
												tMarcaResult.setContadorProductos(tMarcaResult.getContadorProductos() - 1);
												idResult = daoMarca.update(tMarcaResult);
												if (idResult == -100) id = -100;
											}
											else id = -5;
										}
										else id = -100;
									}
								}
								else id = -6;
							}
							else id = -5;
						}
						else id = -4;
					}
				}
				else id = -3;
			}
			else id = -2;
		}	
		return id;
	}
	
	/*
	 * TABLA DE ERRORES
	 * 
	 * -1 - Validez sintáctica
	 * -2 - El producto que se quiere borrar no existe
	 * -3 - El producto que se quiere borrar ya está borrado
	 * -100 - Error de Base de Datos
	 */
	public int delete(int id) {
		
		int idRetorno = -1;
		DAOProducto daoProducto = FactoriaIntegracion.getInstance().generateDAOProducto();
		if(ComprobadorSintactico.isPositive(id)){
			TProducto tProductoResult = daoProducto.read(id);
			if(tProductoResult != null) {
				if (tProductoResult.getActivo()) {
					idRetorno = daoProducto.delete(id);
					if(idRetorno != -100){
						DAOMarca daoMarca = FactoriaIntegracion.getInstance().generateDAOMarca();
						TMarca tMarcaResult = daoMarca.read(tProductoResult.getIdMarca());
						tMarcaResult.setContadorProductos(tMarcaResult.getContadorProductos()-1);
						int idRetorno2 = daoMarca.update(tMarcaResult);
						if (idRetorno2 == -100) idRetorno = -100;
					}
				}
				else idRetorno = -3;
			}
			else idRetorno = -2;
		}
		return idRetorno;
	}
	
}