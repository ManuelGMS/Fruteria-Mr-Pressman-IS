package negocio.cliente;

public class TClienteNoVIP extends TCliente {

	private int limiteCredito;

	public TClienteNoVIP(Integer id, String nombre, int telefono,
			String direccion, boolean activo, String dni, int limiteCredito) {
		super(id,nombre,telefono,direccion,activo,dni);
		this.limiteCredito = limiteCredito;
	}

	public TClienteNoVIP(String nombre, int telefono, String direccion,
			boolean activo, String dni, int limiteCredito) {
		super(nombre,telefono,direccion,activo,dni);
		this.limiteCredito = limiteCredito;
	}

	public int getLimiteCredito() {
		return this.limiteCredito;
	}

	public void setLimiteCredito(int limiteCredito) {
		this.limiteCredito = limiteCredito;
	}

}