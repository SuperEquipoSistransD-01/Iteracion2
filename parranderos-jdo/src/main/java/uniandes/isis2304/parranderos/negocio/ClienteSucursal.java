package uniandes.isis2304.parranderos.negocio;

public class ClienteSucursal implements VOClienteSucursal{
	private String ciudadSucursal; 
	private String direccionSucursal;
    private long cliente;
    
    public ClienteSucursal()
    {
    	this.setCiudadSucursal(ciudadSucursal);
    	this.setDireccionSucursal(direccionSucursal);
    	this.setCliente(cliente);
    }
    
    public ClienteSucursal(String ciudadSucursal, String direccionSucursal, long cliente)
    {
    	this.setCiudadSucursal(ciudadSucursal);
    	this.setDireccionSucursal(direccionSucursal);
    	this.setCliente(cliente);
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
	public long getCliente() {
		return cliente;
	}
	public void setCliente(long cliente) {
		this.cliente = cliente;
	}	

}
