package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;;

public interface VOPedidos {
	public long getCodigo();
	
	public String getCiudadSucursal();
	
	public String getDireccionSucursal();
	public long getProveedor();
	public long getProducto();
	public long getDiasDesdePedido();
	public long getVolumenProducto();
	public long getDiasParaEntrega();
	public Timestamp getFechaPedido();
	public long getLlego();
	public long getPedidoConsolidado();
}
