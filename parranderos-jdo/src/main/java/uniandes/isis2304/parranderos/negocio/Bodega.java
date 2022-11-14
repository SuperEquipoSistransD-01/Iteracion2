package uniandes.isis2304.parranderos.negocio;

public class Bodega implements VOBodega {
	private long codigo;
	private String ciudadSucursal;
	private String direccionSucursal;
	private long capacidad;
	private String categoriaAlmacenamiento;
	
	public Bodega() {
		this.setCodigo(0);
		this.setCapacidad(0);
		this.setCiudadSucursal("");
		this.setDireccionSucursal("");
		this.setCategoriaAlmacenamiento("");
		
	}
	public Bodega(long codigo, String ciudadSucursal, String direccionSucursal, String categoriaAlmacenamiento, long capacidad) {
		this.setCodigo(codigo);
		this.setCapacidad(capacidad);
		this.setCiudadSucursal(ciudadSucursal);
		this.setCategoriaAlmacenamiento(categoriaAlmacenamiento);
		this.setDireccionSucursal(direccionSucursal);
	}
		
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public long getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(long capacidad) {
		this.capacidad = capacidad;
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
		this.ciudadSucursal= ciudadSucursal;
	}
	public String getCategoriaAlmacenamiento() {
		return categoriaAlmacenamiento;
	}
	public void setCategoriaAlmacenamiento(String categoriaAlmacenamiento) {
		this.categoriaAlmacenamiento = categoriaAlmacenamiento;
	}

	
	

}
