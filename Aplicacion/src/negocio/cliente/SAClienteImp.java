package negocio.cliente;

import java.util.ArrayList;

import integracion.FactoriaIntegracion;
import integracion.cliente.DAOCliente;
import negocio.ComprobadorSintactico;

public class SAClienteImp implements SACliente {

	/*
	 * Error:
	 * -1: La validez sintactica no es correcta.
	 * -2: Objeto transfer no valido.
	 * -3: El cliente ya estaba dado de alta y por tanto no se puede volver a crear.
	 * -4: El tipo de cliente es incompatible para la reactivacion
	 * -100: Error en la gestión de la BDD.
	 */
	public int create(TCliente tCliente) {
		
		int id = -1;
		
		if(tCliente != null) {					
			if( ComprobadorSintactico.isPositive(tCliente.getId()) && ComprobadorSintactico.isName(tCliente.getNombre()) 
					&& ComprobadorSintactico.isPositive(tCliente.getTelefono())) {
				if(tCliente instanceof TClienteVIP) {
					int codigoTarjeta = ((TClienteVIP) tCliente).getCodTarjetaOro();
					if (ComprobadorSintactico.isPositive(codigoTarjeta)) {
						FactoriaIntegracion factoriaIntegracion = FactoriaIntegracion.getInstance();
						
						DAOCliente daoCliente = factoriaIntegracion.generateDAOCliente();
								
						TCliente tClienteResult = daoCliente.findClienteByDNI(tCliente.getDNI());
						
						tCliente.setActivo(true);
								
						if(tClienteResult == null) {
									
							id = daoCliente.create(tCliente);
									
						} else {
							
							if(!tClienteResult.getActivo()) {
								if(tClienteResult instanceof TClienteVIP) {
									tCliente.setId(tClienteResult.getId());
											
									id = daoCliente.update(tCliente);
								}
								else
									id = -4;
										
							} else {
										
								id = -3;
										
							}
									
						}
					}	
				} else {
					int limiteCredito = ((TClienteNoVIP) tCliente).getLimiteCredito();
					if(ComprobadorSintactico.isPositive(limiteCredito)) {
						FactoriaIntegracion factoriaIntegracion = FactoriaIntegracion.getInstance();
						
						DAOCliente daoCliente = factoriaIntegracion.generateDAOCliente();
								
						TCliente tClienteResult = daoCliente.findClienteByDNI(tCliente.getDNI());
								
						if(tClienteResult == null) {
									
							tCliente.setActivo(true);
									
							id = daoCliente.create(tCliente);
									
						} else {
							
							if(!tClienteResult.getActivo()) {
								if(tClienteResult instanceof TClienteNoVIP) {
									tCliente.setActivo(true);
									
									tCliente.setId(tClienteResult.getId());
											
									id = daoCliente.update(tCliente);
								}
								else
									id = -4;
										
							} else {
										
								id = -3;
										
							}
									
						}
					}
				}
						
			}
					
		}
		
		return id;
		
	}

	public TCliente read(int id) {
		
		TCliente tCliente = null;
		
		if(ComprobadorSintactico.isPositive(id)) {
			
			tCliente = FactoriaIntegracion.getInstance().generateDAOCliente().read(id);
			
		}
		
		return tCliente;
		
	}

	public ArrayList<TCliente> readAll() {
		
		return FactoriaIntegracion.getInstance().generateDAOCliente().readAll();
		
	}

	public int update(TCliente tCliente) {
		
		/*
		 * Error:
		 * -1: No se cumple la validez sintactica.
		 * -2: Objeto transfer no valido.
		 * -3: No se encontró al cliente en la BDD.
		 * -4: El cliente buscado en a BDD no estaba activo.
		 * -5: El cliente solicitado no estaba activo.
		 * -6: Se ha querido actualizar el DNI al de otro ya existente
		 * -100: Error en la gestión de la BDD. 
		 */
		
		int id = -1;
		
		if(tCliente != null) {
		
			if(ComprobadorSintactico.isPositive(tCliente.getId()) && ComprobadorSintactico.isName(tCliente.getNombre())
					&& ComprobadorSintactico.isPositive(tCliente.getTelefono())) {
			
				if(tCliente instanceof TClienteVIP) {
					int codigoTarjeta = ((TClienteVIP) tCliente).getCodTarjetaOro();
					if (ComprobadorSintactico.isPositive(codigoTarjeta)) { 
						FactoriaIntegracion factoriaIntegracion = FactoriaIntegracion.getInstance();
						
						DAOCliente daoCliente = factoriaIntegracion.generateDAOCliente();
						
						TCliente tClienteResult = daoCliente.read(tCliente.getId());
						
						if(tClienteResult != null) {
							if (!tCliente.getDNI().equalsIgnoreCase(tClienteResult.getDNI()) ) {
								TCliente tClienteResult1 = daoCliente.findClienteByDNI(tCliente.getDNI());
								if (tClienteResult1 != null) id = -6;
							}
							if (id != -6) {
								if(tClienteResult.getActivo() && tCliente.getActivo()) {
									
									id = daoCliente.update(tCliente);
								
								} else {
									
									if(!tClienteResult.getActivo()) id = -4; else id = -5;
									
								}
							}
						} else {
							
							id = -3;
							
						}
					} 
				}
				else {
					int limiteCredito = ((TClienteNoVIP) tCliente).getLimiteCredito();
					if(ComprobadorSintactico.isPositive(limiteCredito)) {
						FactoriaIntegracion factoriaIntegracion = FactoriaIntegracion.getInstance();
						
						DAOCliente daoCliente = factoriaIntegracion.generateDAOCliente();
						
						TCliente tClienteResult = daoCliente.read(tCliente.getId());
						
						if(tClienteResult != null) {
							if (!tCliente.getDNI().equalsIgnoreCase(tClienteResult.getDNI())) {
								TCliente tClienteResult1 = daoCliente.findClienteByDNI(tCliente.getDNI());
								if (tClienteResult1 != null) id = -6;
							}
							if (id != -6) {
								if(tClienteResult.getActivo() && tCliente.getActivo()) {
									
									id = daoCliente.update(tCliente);
								
								} else {
									
									if(!tClienteResult.getActivo()) id = -4; else id = -5;
									
								}
							}
						} else {
							
							id = -3;
							
						}
					}
				}
				
			}	
		} else {		
			id = -2;	
		}
		return id;
		
	}

	/*
	 * Error:
	 * -1: No se cumple la validez sintactica.
	 * -2: El cliente no existe y no se le puede dar de baja.
	 * -100: Error en la gestión de la BDD.
	 */
	public int delete(int id) {
		
		if(ComprobadorSintactico.isPositive(id)) {
			
			FactoriaIntegracion factoriaIntegracion = FactoriaIntegracion.getInstance();
			
			DAOCliente daoCliente = factoriaIntegracion.generateDAOCliente();
			
			TCliente tClienteResult = daoCliente.read(id);
			
			if(tClienteResult != null) id = daoCliente.delete(id); else id = -2;
			
		} else {
			
			id = -1;
			
		}
		
		return id;
		
	}
	
}