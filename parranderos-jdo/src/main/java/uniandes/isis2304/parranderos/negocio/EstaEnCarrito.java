package uniandes.isis2304.parranderos.negocio;

public class EstaEnCarrito implements VOEstaEnCarrito {
	private long clienteCC;
	private String ciudadSucursalAsociada;
	private String direccionSucursalAsociada;
	private long abandono;
	private long codigo;
	private long cantidad;

	
	public EstaEnCarrito() {
		this.setAbandono(1);
		this.setCiudadSucursalAsociada("");
		this.setClienteCC(0);
		this.setDireccionSucursalAsociada("");
		this.setCodigo(0);
		this.setCantidad(0);

		
	}
	public EstaEnCarrito(long clienteCC, String ciudadSucursalAsociada, String direccionSucursalAsociada,
	long abandono, long codigo, long cantidad) {
		this.setAbandono(abandono);
		this.setCiudadSucursalAsociada(ciudadSucursalAsociada);
		this.setClienteCC(clienteCC);
		this.setDireccionSucursalAsociada(direccionSucursalAsociada);
		this.setCodigo(codigo);
		this.setCantidad(cantidad);
		

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
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	

	
	

}
