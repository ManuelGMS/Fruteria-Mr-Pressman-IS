package negocio.producto;

public class TProducto {
	
	private int id;
	
	private int idMarca;
	
	private String nombre;
	
	private float precio;
	
	private int unidades;
	
	private boolean activo;

	public TProducto(int id, int idMarca, String nombre, float precio, int unidades, boolean activo) {
		this.id = id;
		this.idMarca = idMarca;
		this.nombre = nombre;
		this.precio = precio;
		this.unidades = unidades;
		this.activo = activo;
	}

	public TProducto(int idMarca, String nombre, float precio, int unidades, boolean activo) {
		this.idMarca = idMarca;
		this.nombre = nombre;
		this.precio = precio;
		this.unidades = unidades;
		this.activo = activo;
	}

	public int getId() {
		return this.id;
	}

	public int getIdMarca() {
		return this.idMarca;
	}

	public String getNombre() {
		return this.nombre;
	}

	public float getPrecio() {
		return this.precio;
	}

	public int getUnidades() {
		return this.unidades;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdMarca(int idMarca) {
		this.idMarca = idMarca;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public void setUnidades(int unidades) {
		this.unidades = unidades;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
}