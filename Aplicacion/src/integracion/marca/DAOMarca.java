package integracion.marca;

import negocio.marca.TMarca;
import java.util.ArrayList;

public interface DAOMarca {

	public int create(TMarca tMarca);

	public TMarca findByName(String nombre);

	public TMarca read(int id);

	public ArrayList<TMarca> readAll();

	public int update(TMarca tMarca);

	public int delete(int id);
}