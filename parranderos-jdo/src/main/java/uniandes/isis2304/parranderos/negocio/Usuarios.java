package uniandes.isis2304.parranderos.negocio;

public class Usuarios implements VOUsuarios{
	private long numDocumento;
	private String nombre;
	private String correoElectronico;
    private String clave;
    private String rol ;
    private String ciudadSucursal; 
    private String direccionSucursal;
    
    public Usuarios() {
    	this.setCiudadSucursal("");
    	this.setClave("");
    	this.setCorreoElectronico("");
    	this.setDireccionSucursal("");
    	this.setNombre("");
    	this.setNumDocumento(0);
    	this.setRol("");
    }
    
    public Usuarios(long numDocumento, String nombre, String correoElectronico,
    String clave, String rol, String ciudadSucursal, 
    String direccionSucursal) {
    	this.setCiudadSucursal(ciudadSucursal);
    	this.setClave(clave);
    	this.setCorreoElectronico(correoElectronico);
    	this.setDireccionSucursal(direccionSucursal);
    	this.setNombre(nombre);
    	this.setNumDocumento(numDocumento);
    	this.setRol(rol);
    }
	public long getNumDocumento() {
		return numDocumento;
	}
	public void setNumDocumento(long numDocumento) {
		this.numDocumento = numDocumento;
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
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}
	public String getRol() {
		return rol;
	}
	public void setRol(String rol) {
		this.rol = rol;
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

}
