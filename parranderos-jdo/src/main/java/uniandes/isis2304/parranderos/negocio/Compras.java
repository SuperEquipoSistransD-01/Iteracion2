package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;



public class Compras implements VOCompras{
	private long codigo;
	private Timestamp fecha;
	private String ciudadSucursal; 
	private String direccionSucursal;
	private long cliente;
	
	public Compras() {
		this.setCodigo(0);
		this.setFecha(fecha);
		this.setCiudadSucursal("");
		this.setDireccionSucursal("");
		this.setCliente(0);
	}
	
	public Compras(long codigo, Timestamp fecha, String ciudadSucursal, String direccionSucursal, long cliente) 
	{
		this.setCodigo(codigo);
		this.setFecha(fecha);
		this.setCiudadSucursal(ciudadSucursal);
		this.setDireccionSucursal(direccionSucursal);
		this.setCliente(cliente);
	}
	
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
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
	public long getCliente() {
		return cliente;
	}
	public void setCliente(long cliente) {
		this.cliente = cliente;
	}

}
