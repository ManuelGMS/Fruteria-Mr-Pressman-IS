package negocio.pais;

import java.util.ArrayList;

public interface SAPais {
	
	public int create(TPais tPais);

	public TPais read(int id);

	public ArrayList<TPais> readAll();

	public int update(TPais tPais);

	public int delete(int id);

}