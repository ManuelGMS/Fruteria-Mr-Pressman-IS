package negocio.pais;

public class TPais {
	
	private int id;
	
	private boolean activo;
	
	private String nombre;
	
	private int contadorMarcas;

	public TPais(int id, String nombre, boolean activo, int contador) {
		this.id = id;
		this.nombre = nombre;
		this.activo = activo;
		this.contadorMarcas = contador;
	}

	public TPais(String nombre, boolean activo, int contador) {
		this.nombre = nombre;
		this.activo = activo;
		this.contadorMarcas = contador;
	}

	public int getId() {
		return this.id;
	}

	public String getNombre() {
		return this.nombre;
	}

	public boolean getActivo() {
		return this.activo;
	}

	public int getContadorMarcas() {
		return this.contadorMarcas;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public void setContadorMarcas(int contadorMarcas) {
		this.contadorMarcas = contadorMarcas;
	}
	
}