package uniandes.isis2304.parranderos.negocio;

public class Bodega implements VOBodega {
	private long idBodega;
	private String ciudadSucursalAsociada;
	private String direccionSucursalAsociada;
	private long capacidad;
	private String categoriaAlmacenamiento;
	
	public Bodega() {
		this.setIdBodega(0);
		this.setCapacidad(0);
		this.setCiudadSucursalAsociada("");
		this.setDireccionSucursalAsociada("");
		this.setCategoriaAlmacenamiento("");
		
	}
	public Bodega(long idBodega, String nombreSucursalAsociada, String direccionSucursalAsociada, long capacidad, String categoriaAlmacenamiento) {
		this.setIdBodega(idBodega);
		this.setCapacidad(capacidad);
		this.setCiudadSucursalAsociada(nombreSucursalAsociada);
		this.setCategoriaAlmacenamiento(categoriaAlmacenamiento);
		this.setDireccionSucursalAsociada(direccionSucursalAsociada);
	}
		
	public long getIdBodega() {
		return idBodega;
	}
	public void setIdBodega(long idBodega) {
		this.idBodega = idBodega;
	}
	public long getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(long capacidad) {
		this.capacidad = capacidad;
	}

	public String getDireccionSucursalAsociada() {
		return direccionSucursalAsociada;
	}
	public void setDireccionSucursalAsociada(String direccionSucursalAsociada) {
		this.direccionSucursalAsociada = direccionSucursalAsociada;
	}
	public String getCiudadSucursalAsociada() {
		return ciudadSucursalAsociada;
	}
	public void setCiudadSucursalAsociada(String ciudadSucursalAsociada) {
		this.ciudadSucursalAsociada = ciudadSucursalAsociada;
	}
	public String getCategoriaAlmacenamiento() {
		return categoriaAlmacenamiento;
	}
	public void setCategoriaAlmacenamiento(String categoriaAlmacenamiento) {
		this.categoriaAlmacenamiento = categoriaAlmacenamiento;
	}

	
	

}
