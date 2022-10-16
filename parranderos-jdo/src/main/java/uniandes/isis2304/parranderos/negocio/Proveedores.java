package uniandes.isis2304.parranderos.negocio;

public class Proveedores implements VOProveedores {
	private long nit;
	private String nombre;
	private long calificacion;
	
	public Proveedores() {
		this.setCalificacion(0);
		this.setNit(0);
		this.setNombre("");
	}
	public Proveedores(long nit, String nombre, long calificacion) {
		this.setCalificacion(calificacion);
		this.setNit(nit);
		this.setNombre(nombre);
	}
	public long getNit() {
		return nit;
	}
	public void setNit(long nit) {
		this.nit = nit;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public long getCalificacion() {
		return calificacion;
	}
	public void setCalificacion(long calificacion) {
		this.calificacion = calificacion;
	}
	
}
