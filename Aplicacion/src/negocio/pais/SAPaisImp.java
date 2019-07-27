package negocio.pais;

import java.util.ArrayList;
import integracion.FactoriaIntegracion;
import integracion.pais.DAOPais;
import negocio.ComprobadorSintactico;

public class SAPaisImp implements SAPais {
	
	/*
	 * TABLA DE ERRORES
	 * 
	 * -1 - tPais == null
	 * -2 - Validez sintáctica
	 * -3 - El pais está activo y no se puede volver a crear
	 * -100 - Error de Base de Datos
	 */
	public int create(TPais tPais) {
		
		int id = -1;
		if (tPais != null) {
			String nombre = tPais.getNombre();
			if(ComprobadorSintactico.isName(nombre)) {
				DAOPais daoPais = FactoriaIntegracion.getInstance().generateDAOPais();
				TPais tPaisResult = daoPais.findByName(nombre);
				tPais.setContadorMarcas(0);
				tPais.setActivo(true);
				if (tPaisResult == null) {
					id = daoPais.create(tPais);
				}
				else {
					if (!tPaisResult.getActivo()) {
						tPais.setId(tPaisResult.getId());
						id = daoPais.update(tPais);
					}
					else id = -3;
				}
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
	public TPais read(int id) {
		
		TPais tPais = null;
		if(ComprobadorSintactico.isPositive(id)){
			DAOPais daoPais = FactoriaIntegracion.getInstance().generateDAOPais();
			tPais = daoPais.read(id);
		}
		return tPais;
	}

	public ArrayList<TPais> readAll() {
		DAOPais daoPais = FactoriaIntegracion.getInstance().generateDAOPais();
		ArrayList<TPais> listaPaises = daoPais.readAll();
		return listaPaises;
	}

	/*
	 * TABLA DE ERRORES
	 * 
	 * -1 - tPais == null
	 * -2 - Validez sintáctica
	 * -3 - El País que se quiere actualizar no existe
	 * -4 - El País no está activo
	 * -5 - Se ha querido actualizar el nombre al de otro ya existente
	 * -100 - Error de Base de Datos
	 */
	public int update(TPais tPais) {
		
		int id = -1;
		if (tPais != null) {
			String nombre = tPais.getNombre();
			int idPais = tPais.getId();
			if(ComprobadorSintactico.isName(nombre) && ComprobadorSintactico.isPositive(idPais)) {
				DAOPais daoPais = FactoriaIntegracion.getInstance().generateDAOPais();
				TPais tPaisResult = daoPais.read(idPais);
				if (tPaisResult != null) {
					if (!tPais.getNombre().equalsIgnoreCase(tPaisResult.getNombre())) {
						TPais tPaisResult1 = daoPais.findByName(tPais.getNombre());
						if (tPaisResult1 != null) id = -5;
					}
					if(id != -5) {
						boolean activoResult = tPaisResult.getActivo();
						boolean activo = tPais.getActivo();
						if(activoResult && activo){
							id = daoPais.update(tPais);
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
	 * Error:
	 * -1: Error de sintaxis.
	 * -2: No se encontró el pais en la base de datos.
	 * -3: El pais no esta activo y por tanto no se puede borrar.
	 * -4: El pais no puede borrarse, tiene marcas asociadas.
	 * -100: Error de Base de Datos
	 */
	public int delete(int id) {
		
		int idRetorno = -1;
		DAOPais daoPais = FactoriaIntegracion.getInstance().generateDAOPais();	 
		if(ComprobadorSintactico.isPositive(id)) {
			TPais tPaisResult = daoPais.read(id);
			if(tPaisResult != null) {
				if (tPaisResult.getActivo()){
					if(tPaisResult.getContadorMarcas() == 0) {		
						idRetorno = daoPais.delete(id);			
					}
					else idRetorno = -4;
				}
				else idRetorno = -3;
				  	
			} 
			else  idRetorno = -2;
		}
		return idRetorno;
	}
	
}