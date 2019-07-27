package negocio.marca;

import java.util.ArrayList;

public interface SAMarca {
	
	public int create(TMarca tMarca);

	public TMarca read(int id);

	public ArrayList<TMarca> readAll();

	public int update(TMarca tMarca);

	public int delete(int id);
	
}