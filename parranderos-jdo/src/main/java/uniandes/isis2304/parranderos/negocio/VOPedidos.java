package uniandes.isis2304.parranderos.negocio;

import oracle.sql.DATE;

public interface VOPedidos {
	public long getCodigo();
	
	public String getCiudadSucursal();
	
	public String getDireccionSucursal();
	public String getProveedor();
	public long getProducto();
	public long getDiasDesdePedido();
	public long getVolumenProducto();
	public long getDiasParaEntrega();
	public DATE getFechaPedido();
}
