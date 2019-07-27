package negocio.marca;

public class TMarca {
	
	private int id;
	
	private int idPais;
	
	private boolean activo;
	
	private String nombre;
	
	private int contadorProductos;

	public TMarca(int id, int idPais, boolean activo, String nombre,
			int contadorProductos) {
		this.id = id;
		this.idPais = idPais;
		this.activo = activo;
		this.nombre = nombre;
		this.contadorProductos = contadorProductos;
	}

	public TMarca(int idPais, boolean activo, String nombre,
			int contadorProductos) {
		this.idPais = idPais;
		this.activo = activo;
		this.nombre = nombre;
		this.contadorProductos = contadorProductos;
	}

	public int getId() {
		return this.id;
	}

	public int getIdPais() {
		return this.idPais;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public int getContadorProductos() {
		return this.contadorProductos;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setIdPais(int idPais) {
		this.idPais = idPais;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setContadorProductos(int contadorProductos) {
		this.contadorProductos = contadorProductos;
	}
	
}