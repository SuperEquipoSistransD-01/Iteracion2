package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;

public class Promociones implements VOPromociones {
	private String nombre;
	private Timestamp fechaInicio;
	private long diasDuracion;
	private String descripcion;
	private String tipo;
	private long finalizada;
	private String ciudadSucursal;
	private String direccionSucursal;
	private long producto;
	
	public Promociones(String nombre, Timestamp fechaInicio, long diasDuracion, String descripcion, String tipo,  long finalizada, String ciudadSucursal, String direccionSucursal, long producto) {
		this.setCiudadSucursal(ciudadSucursal);
		this.setDescripcion(descripcion);
		this.setDiasDuracion(diasDuracion);
		this.setDireccionSucursal(direccionSucursal);
		this.setFechaInicio(fechaInicio);
		this.setFinalizada(finalizada);
		this.setNombre(nombre);
		this.setProducto(producto);
		this.setTipo(tipo);	
	}
	
	public Promociones() {
		this.setCiudadSucursal("");
		this.setDescripcion("");
		this.setDiasDuracion(0);
		this.setDireccionSucursal("");
		this.setFechaInicio(fechaInicio);
		this.setFinalizada(0);
		this.setNombre("");
		this.setProducto(0);
		this.setTipo("");	
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public Timestamp getFechaInicio() {
		return fechaInicio;
	}
	public void setFechaInicio(Timestamp fechaInicio) {
		this.fechaInicio = fechaInicio;
	}
	public long getDiasDuracion() {
		return diasDuracion;
	}
	public void setDiasDuracion(long diasDuracion) {
		this.diasDuracion = diasDuracion;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public long getFinalizada() {
		return finalizada;
	}
	public void setFinalizada(long finalizada) {
		this.finalizada = finalizada;
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
	public long getProducto() {
		return producto;
	}
	public void setProducto(long producto) {
		this.producto = producto;
	}
	
	
	

}
