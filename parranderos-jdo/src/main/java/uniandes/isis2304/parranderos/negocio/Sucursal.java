package uniandes.isis2304.parranderos.negocio;

public class Sucursal implements VOSucursal{
	private String ciudad;
	
	private String direccion;
	
	private String nombre;
	
	private long area;
	
	public Sucursal()
	{
		this.setCiudad("");
		this.setDireccion("");
		this.setNombre("");
		this.setArea(0);
	}
	
	public Sucursal(String nombre,  String direccion,String ciudad, long area) {
		this.setCiudad(ciudad);
		this.setDireccion(direccion);
		this.setNombre(nombre);
		this.setArea(area);
	}

	public String getCiudad() {
		return ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public long getArea() {
		return area;
	}

	public void setArea(long area) {
		this.area = area;
	}
	
	public String toString()
	{
		return "Sucursal [nombre=" +nombre+", direccion=" +direccion+" ciudad="+ciudad+" area="+area+"]";
	}

}
