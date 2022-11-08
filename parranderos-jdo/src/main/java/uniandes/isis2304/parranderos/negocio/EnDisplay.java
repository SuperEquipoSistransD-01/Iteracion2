package uniandes.isis2304.parranderos.negocio;

public class EnDisplay implements VOEnDisplay {
	private long producto;
	private long estante;
	private long volumenEnEstante;
	private long pesoEnEstante;
	private long nivelAbastecimiento;
	private long cantidad;
	
	public EnDisplay() {
		this.setProducto(0);
		this.setEstante(0);
		this.setVolumenEnEstante(0);
		this.setPesoEnEstante(0);
		this.setNivelAbastecimiento(0);
		this.setCantidad(0);
	}
	public EnDisplay(long producto,	long estante,	long volumenEnEstante, long	pesoEnEstante, long	nivelAbastecimiento, long cantidad) {
		this.setProducto(producto);
		this.setEstante(estante);
		this.setVolumenEnEstante(volumenEnEstante);
		this.setPesoEnEstante(pesoEnEstante);
		this.setNivelAbastecimiento(nivelAbastecimiento);
		this.setCantidad(cantidad);		
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
	public long getVolumenEnEstante() {
		return volumenEnEstante;
	}
	public void setVolumenEnEstante(long volumenEnEstante) {
		this.volumenEnEstante = volumenEnEstante;
	}
	public long getNivelAbastecimiento() {
		return nivelAbastecimiento;
	}
	public void setNivelAbastecimiento(long nivelAbastecimiento) {
		this.nivelAbastecimiento = nivelAbastecimiento;
	}
	public long getPesoEnEstante() {
		return pesoEnEstante;
	}
	public void setPesoEnEstante(long pesoEnEstante) {
		this.pesoEnEstante = pesoEnEstante;
	}
	public long getCantidad() {
		return cantidad;
	}
	public void setCantidad(long cantidad) {
		this.cantidad = cantidad;
	}
		
	

	
	

}
