package uniandes.isis2304.parranderos.negocio;

public class EnDisplay implements VOEnDisplay {
	private long producto;
	private long estante;
	private long nivelAbastecimiento;
	private long cantidad;
	private long espacioDisponible;
	
	public EnDisplay() {
		this.setProducto(0);
		this.setEstante(0);
		this.setNivelAbastecimiento(0);
		this.setCantidad(0);
		this.setEspacioDisponible(0);
	}
	public EnDisplay(long producto,	long estante, long	nivelAbastecimiento, long cantidad, long espacioDisponible) {
		this.setProducto(producto);
		this.setEstante(estante);
		this.setNivelAbastecimiento(nivelAbastecimiento);
		this.setCantidad(cantidad);	
		this.setEspacioDisponible(espacioDisponible);
		
	}
	public long getProducto() {
		return producto;
	}
	public void setProducto(long producto) {
		this.producto = producto;
	}
	public long getEstante() {
		return estante;
	}
	public void setEstante(long estante) {
		this.estante = estante;
	}
	
	
	public long getNivelAbastecimiento() {
		return nivelAbastecimiento;
	}
	public void setNivelAbastecimiento(long nivelAbastecimiento) {
		this.nivelAbastecimiento = nivelAbastecimiento;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
	
	public long getEspacioDisponible() {
		return espacioDisponible;
	}
	public void setEspacioDisponible(long espacioDisponible) {
		this.espacioDisponible = espacioDisponible;
	}
	

	
	

}
