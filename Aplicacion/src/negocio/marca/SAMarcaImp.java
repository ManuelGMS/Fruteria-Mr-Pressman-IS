package negocio.marca;

import java.util.ArrayList;

import integracion.FactoriaIntegracion;
import integracion.marca.DAOMarca;
import integracion.pais.DAOPais;
import negocio.ComprobadorSintactico;
import negocio.pais.TPais;

public class SAMarcaImp implements SAMarca {
	
	/*
	 * Error:
	 * -1: Fallo en la comprobación sintactica.
	 * -2: Objeto transfer no valido.
	 * -3: El pais no se ha encontrado en la BDD.
	 * -4: El pais no estaba activo.
	 * -5: La marca que se intento dar de alta ya estaba activa.
	 * -100: Error en la gestión de la BDD.
	 */
	public int create(TMarca tMarca) {
		
		int id = -1;
		
		if(tMarca != null) {
			
			int idMarca = tMarca.getId();
			int idPais = tMarca.getIdPais();
			String nombreMarca = tMarca.getNombre();
			
			if(ComprobadorSintactico.isPositive(idMarca) && ComprobadorSintactico.isName(nombreMarca) && ComprobadorSintactico.isPositive(idPais)) {
				
				FactoriaIntegracion factoriaIntegracion = FactoriaIntegracion.getInstance();
						
				DAOPais daoPais = factoriaIntegracion.generateDAOPais();
						
				DAOMarca daoMarca = factoriaIntegracion.generateDAOMarca();
						
				TPais tPaisResult = daoPais.read(idPais);
						
				if(tPaisResult != null && tPaisResult.getActivo()) {
							
					TMarca tMarcaResult = daoMarca.findByName(nombreMarca);

					
					tMarca.setActivo(true);
					
					tMarca.setContadorProductos(0);
							
					if(tMarcaResult == null) {
								
						id = daoMarca.create(tMarca);
						
						if(id != -100) {
						
							tPaisResult.setContadorMarcas(tPaisResult.getContadorMarcas()+1);
								
							if(daoPais.update(tPaisResult) == -100) id = -100;
						
						}
							
					} else {
								
						if(!tMarcaResult.getActivo()) {
							
							tMarca.setId(tMarcaResult.getId());
									
							id = daoMarca.update(tMarca);
						
							if(id != -100) {
							
								tPaisResult.setContadorMarcas(tPaisResult.getContadorMarcas()+1);
									
								if( daoPais.update(tPaisResult) == -100 ) id = -100;
							
							}
									
						} else {
									
							id = -5;
									
						}
								
					}
							
				} else {
							
					if(tPaisResult == null) id = -3; else id = -4;
							
				}
						
			}
				
		} else {
			
			id = -2;
			
		}
		
		return id;
		
	}

	public TMarca read(int id) {
		
		TMarca tMarca = null;
		
		if(ComprobadorSintactico.isPositive(id)) {
		
			FactoriaIntegracion factoriaIntegracion = FactoriaIntegracion.getInstance();
			
			DAOMarca daoMarca = factoriaIntegracion.generateDAOMarca();
			
			tMarca = daoMarca.read(id);
		
		}
		
		return tMarca;
		
	}

	public ArrayList<TMarca> readAll() {
		
		ArrayList<TMarca> tMarca;
		
		FactoriaIntegracion factoriaIntegracion = FactoriaIntegracion.getInstance();
			
		DAOMarca daoMarca = factoriaIntegracion.generateDAOMarca();
			
		tMarca = daoMarca.readAll();
		
		return tMarca;
		
	}

	/*
	 * Error:
	 * -1: Fallo en la comrpobacion sintactica.
	 * -2: Objeto transfer no valido.
	 * -3: La marca no se encontro en la BDD.
	 * -4: Se ha querido actualizar el nombre al de otro ya existente
	 * -5: No se encontró el pais en la BDD.
	 * -6: El pais no estaba activo.
	 * -7: La marca que se quiere actualizar no esta activa.
	 * -8: La marca encontrada en la BDD no estaba activa.
	 * -100: Error en la gestión de la BDD.
	 */
	public int update(TMarca tMarca) {
		
		int id = -1;
		
		if(tMarca != null) {
		
			if(ComprobadorSintactico.isPositive(tMarca.getId()) && ComprobadorSintactico.isName(tMarca.getNombre())
					&& ComprobadorSintactico.isPositive(tMarca.getIdPais())) {
				
				FactoriaIntegracion factoriaIntegacion = FactoriaIntegracion.getInstance();
				
				DAOMarca daoMarca = factoriaIntegacion.generateDAOMarca();
				
				TMarca tMarcaResult = daoMarca.read(tMarca.getId());	
				if(tMarcaResult != null) {
					if (!tMarca.getNombre().equalsIgnoreCase(tMarcaResult.getNombre())) {
						TMarca tMarcaResult1 = daoMarca.findByName(tMarca.getNombre());
						if( tMarcaResult1 != null) id = -4;
					}
					if (id != -4) { // Si los nombres no coinciden y ya existe una Marca con ese nombre no entras
						DAOPais daoPais = factoriaIntegacion.generateDAOPais();					
						TPais tPais = daoPais.read(tMarca.getIdPais());						
						if(tPais != null) {	
							if (tPais.getActivo()) {
								if(tMarca.getActivo()) {
									if (tMarcaResult.getActivo()) {	
										id = daoMarca.update(tMarca);
										if(tMarcaResult.getIdPais() != tMarca.getIdPais()){ // Si se cambia el id del Pais
											tPais.setContadorMarcas(tPais.getContadorMarcas() + 1);
											int idResult = daoPais.update(tPais);
											if (idResult != -100){
												TPais tPaisResult = daoPais.read(tMarcaResult.getIdPais());
												if (tPaisResult != null) {
													tPaisResult.setContadorMarcas(tPaisResult.getContadorMarcas() - 1);
													idResult = daoPais.update(tPaisResult);
													if (idResult == -100) id = -100;
												}
												else id = -5;
											}
											else id = -100;
										}
									}
									else id = -7;		
								} 
								else id = -8;
							}
							else id = -6;		
						} 
						else id = -5;		
					}
					else id = -4;
				}
				else id = -3; 				
			}
		
		} else {
			
			id = -2;
			
		}
		
		return id;
		
	}

	/*
	 * Error:
	 * -1: Fallo en la comprobación sintactica.
	 * -2: La marca no se econtro en la base de datos.
	 * -3: La marca no esta activa y no puede eliminarse
	 * -4: La marca no puede eliminarse porque  tiene productos asociados.
	 * -100: Error en la gestión de la BDD.
	 */
	public int delete(int id) {
		
		FactoriaIntegracion factoriaIntegracion = FactoriaIntegracion.getInstance();
		
		DAOMarca daoMarca = factoriaIntegracion.generateDAOMarca();

		if(ComprobadorSintactico.isPositive(id)) {
			
			TMarca tMarcaResult = daoMarca.read(id);
			
			if(tMarcaResult != null) {
				
				if( tMarcaResult.getActivo() && tMarcaResult.getContadorProductos() == 0 ) {
					
					id = daoMarca.delete(id);
					
					if(id != -100) {
						
						DAOPais daoPais = factoriaIntegracion.generateDAOPais(); 
						
						TPais tPais = daoPais.read(tMarcaResult.getIdPais());
						
						tPais.setContadorMarcas(tPais.getContadorMarcas() - 1);
						
						daoPais.update(tPais);
						
					}
					
				} else {
					
					if(!tMarcaResult.getActivo()) id = -3; else id = -4;
					
				}
				
			} else {
				
				id = -2;
				
			}
			
		} else {
			
			id = -1;
			
		}
		
		return id;
		
	}
	
}