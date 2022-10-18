package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;



public interface VOPromociones {
	
	public String getNombre() ;
	public Timestamp getFechaInicio();
	public long getDiasDuracion();
	public String getDescripcion() ;
	public String getTipo() ;
	public long getFinalizada();
	public String getCiudadSucursal();
	public String getDireccionSucursal();
	public long getProducto();
	
	
}
