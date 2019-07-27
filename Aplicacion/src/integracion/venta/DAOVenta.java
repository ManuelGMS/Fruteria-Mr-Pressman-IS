package integracion.venta;

import negocio.venta.TVenta;
import java.util.ArrayList;

public interface DAOVenta {

	public int create(TVenta tVenta);

	public TVenta read(int id);

	public ArrayList<TVenta> readAll();

	public int update(TVenta tVenta);
}