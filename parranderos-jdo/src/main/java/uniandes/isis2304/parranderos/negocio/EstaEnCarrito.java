package uniandes.isis2304.parranderos.negocio;

public class EstaEnCarrito implements VOEstaEnCarrito {
	private long clienteCC;
	private String ciudadSucursal;
	private String direccionSucursal;
	private long abandono;
	private long codigo;
	private long cantidad;

	
	public EstaEnCarrito() {
		this.setAbandono(1);
		this.setCiudadSucursal("");
		this.setClienteCC(0);
		this.setDireccionSucursal("");
		this.setCodigo(0);
		this.setCantidad(0);

		
	}
	public EstaEnCarrito(long clienteCC, String ciudadSucursal, String direccionSucursal,
	long abandono, long codigo, long cantidad) {
		this.setAbandono(abandono);
		this.setCiudadSucursal(ciudadSucursal);
		this.setClienteCC(clienteCC);
		this.setDireccionSucursal(direccionSucursal);
		this.setCodigo(codigo);
		this.setCantidad(cantidad);
	}
	public long getClienteCC() {
		return clienteCC;
	}
	public void setClienteCC(long clienteCC) {
		this.clienteCC = clienteCC;
	}
	public String getCiudadSucursal() {
		return ciudadSucursal;
	}
	public void setCiudadSucursal(String ciudadSucursal) {
		this.ciudadSucursal = ciudadSucursal;
	}
	public String getDireccionSucursal() {
		return direccionSucursal;
	}
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
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
