package integracion.pais;

import negocio.pais.TPais;
import java.util.ArrayList;

public interface DAOPais {

	public int create(TPais tPais);

	public TPais read(int id);

	public TPais findByName(String nombre);

	public ArrayList<TPais> readAll();

	public int update(TPais tPais);

	public int delete(int id);
}