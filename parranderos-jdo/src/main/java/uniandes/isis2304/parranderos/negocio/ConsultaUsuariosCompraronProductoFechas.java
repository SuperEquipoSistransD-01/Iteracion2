package uniandes.isis2304.parranderos.negocio;

public class ConsultaUsuariosCompraronProductoFechas implements VOConsultaUsuariosCompraronProductoFechas{
	
	private long numDocumento;
	private String tipoDocumento;
	private String nombre;
	private String correoElectronico;
	private String ciudadSucursal;
	private String direccionSucursal;
	private long cantidadCompra;
	
	public ConsultaUsuariosCompraronProductoFechas()
	{
		this.setCantidadCompra(0);
		this.setCiudadSucursal("");
		this.setCorreoElectronico("");
		this.setDireccionSucursal("");
		this.setNombre("");
		this.setNumDocumento(0);
		this.setTipoDocumento("");		
	}
	
	public ConsultaUsuariosCompraronProductoFechas(long numDocumento, String tipoDocumento, String nombre, String correoElectronico, String ciudadSucursal, String direccionSucursal, String cantidadCompra)
	{
		this.setCantidadCompra(0);
		this.setCiudadSucursal("");
		this.setCorreoElectronico("");
		this.setDireccionSucursal("");
		this.setNombre("");
		this.setNumDocumento(0);
		this.setTipoDocumento("");		
	}
	
	
	public long getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(long numDocumento) {
		this.numDocumento = numDocumento;
	}
	public String getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCorreoElectronico() {
		return correoElectronico;
	}
	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}
	public String getDireccionSucursal() {
		return direccionSucursal;
	}
	public void setDireccionSucursal(String direccionSucursal) {
		this.direccionSucursal = direccionSucursal;
	}
	public String getCiudadSucursal() {
		return ciudadSucursal;
	}
	public void setCiudadSucursal(String ciudadSucursal) {
		this.ciudadSucursal = ciudadSucursal;
	}
	public long getCantidadCompra() {
		return cantidadCompra;
	}
	public void setCantidadCompra(long cantidadCompra) {
		this.cantidadCompra = cantidadCompra;
	}
	
}
