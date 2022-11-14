package uniandes.isis2304.parranderos.negocio;

public class Estante implements VOEstante {
	private long codigo;
	private String ciudadSucursal;
	private String direccionSucursal;
	private String tipoProducto;
	private long capacidad;
	
	public Estante() {
		this.setCodigo(0);
		this.setCiudadSucursal("");
		this.setDireccionSucursal("");
		this.setTipoProducto("");
		this.setCapacidad(0);
		
	}
	public Estante(long codigo, String ciudadSucursal, String direccionSucursal, String tipoProducto, long capacidad) {
		this.setCodigo(codigo);
		this.setCiudadSucursal(ciudadSucursal);
		this.setDireccionSucursal(direccionSucursal);
		this.setTipoProducto(tipoProducto);
		this.setCapacidad(capacidad);
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
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	
	public String getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public long getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(long capacidad) {
		this.capacidad = capacidad;
	}

	
	

}
