package uniandes.isis2304.parranderos.negocio;

public class Carrito implements VOCarrito {
	private long clienteCC;
	private String ciudadSucursalAsociada;
	private String direccionSucursalAsociada;
	private long abandono;

	
	public Carrito() {
		this.setAbandono(1);
		this.setCiudadSucursalAsociada("");
		this.setClienteCC(0);
		this.setDireccionSucursalAsociada("");

		
	}
	public Carrito(long clienteCC, String ciudadSucursalAsociada, String direccionSucursalAsociada,
	long abandono) {
		this.setAbandono(abandono);
		this.setCiudadSucursalAsociada(ciudadSucursalAsociada);
		this.setClienteCC(clienteCC);
		this.setDireccionSucursalAsociada(direccionSucursalAsociada);
		

	}
	public long getClienteCC() {
		return clienteCC;
	}
	public void setClienteCC(long clienteCC) {
		this.clienteCC = clienteCC;
	}
	public String getCiudadSucursalAsociada() {
		return ciudadSucursalAsociada;
	}
	public void setCiudadSucursalAsociada(String ciudadSucursalAsociada) {
		this.ciudadSucursalAsociada = ciudadSucursalAsociada;
	}
	public String getDireccionSucursalAsociada() {
		return direccionSucursalAsociada;
	}
	public void setDireccionSucursalAsociada(String direccionSucursalAsociada) {
		this.direccionSucursalAsociada = direccionSucursalAsociada;
	}
	public long getAbandono() {
		return abandono;
	}
	public void setAbandono(long abandono) {
		this.abandono = abandono;
	}
	

	
	

}
