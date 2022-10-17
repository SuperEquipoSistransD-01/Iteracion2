package uniandes.isis2304.parranderos.negocio;

public class Clientes implements VOClientes{
	 private long numDocumento;
	 private String tipoDocumento;
	 private String nombre;
	 private String correoElectronico;
	 private String clave;
	 
	public Clientes() {
		this.setClave("");
		this.setCorreoElectronico("");
		this.setNombre("");
		this.setNumDocumento(0);
		this.setTipoDocumento("");
	}
	public Clientes(long numDocumento,String tipoDocumento, String nombre,
    String correoElectronico,
	String clave) {
		this.setClave(clave);
		this.setCorreoElectronico(correoElectronico);
		this.setNombre(nombre);
		this.setNumDocumento(numDocumento);
		this.setTipoDocumento(tipoDocumento);
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
	public String getClave() {
		return clave;
	}
	public void setClave(String clave) {
		this.clave = clave;
	}

}
