package integracion.producto;

import negocio.producto.TProducto;
import java.util.ArrayList;

public interface DAOProducto {

	public int create(TProducto tProducto);

	public TProducto findByName(String nombre);

	public TProducto read(int id);

	public ArrayList<TProducto> readAll();

	public int update(TProducto tProducto);

	public int delete(int id);
}
