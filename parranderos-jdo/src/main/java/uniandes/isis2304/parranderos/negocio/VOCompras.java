package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;



public interface VOCompras {
	public long getCodigo();
	
	public Timestamp getFecha();
	
	public String getCiudadSucursal();
	
	public String getDireccionSucursal();
	
	public long getCliente();
 
}
