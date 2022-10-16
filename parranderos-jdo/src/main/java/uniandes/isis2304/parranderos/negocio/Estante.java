package uniandes.isis2304.parranderos.negocio;

public class Estante implements VOEstante {
	private long idEstante;
	private String nombreSucursalAsociada;
	private String direccionSucursalAsociada;
	private long volumenLimite;
	private long pesoLimite;
	private String tipoProducto;
	
	public Estante() {
		this.setIdEstante(0);
		this.setNombreSucursalAsociada("");
		this.setDireccionSucursalAsociada("");
		this.setVolumenLimite(0);
		this.setPesoLimite(0);
		this.setTipoProducto("");
		
	}
	public Estante(long idEstante, String nombreSucursalAsociada, String direccionSucursalAsociada, long volumenLimite,
			long pesoLimite, String tipoProducto) {
		this.setIdEstante(idEstante);
		this.setNombreSucursalAsociada(nombreSucursalAsociada);
		this.setDireccionSucursalAsociada(direccionSucursalAsociada);
		this.setVolumenLimite(volumenLimite);
		this.setPesoLimite(pesoLimite);
		this.setTipoProducto(tipoProducto);
	}
		
	
	public String getNombreSucursalAsociada() {
		return nombreSucursalAsociada;
	}
	public void setNombreSucursalAsociada(String nombreSucursalAasociada) {
		this.nombreSucursalAsociada = nombreSucursalAasociada;
	}
	public String getDireccionSucursalAsociada() {
		return direccionSucursalAsociada;
	}
	public void setDireccionSucursalAsociada(String direccionSucursalAsociada) {
		this.direccionSucursalAsociada = direccionSucursalAsociada;
	}
	public long getIdEstante() {
		return idEstante;
	}
	public void setIdEstante(long idEstante) {
		this.idEstante = idEstante;
	}
	public long getVolumenLimite() {
		return volumenLimite;
	}
	public void setVolumenLimite(long volumenLimite) {
		this.volumenLimite = volumenLimite;
	}
	public long getPesoLimite() {
		return pesoLimite;
	}
	public void setPesoLimite(long pesoLimite) {
		this.pesoLimite = pesoLimite;
	}
	
	public String getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}

	
	

}
