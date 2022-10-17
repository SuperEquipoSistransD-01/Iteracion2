package uniandes.isis2304.parranderos.negocio;

public interface VOAcuerdoCompra {
	public String getCiudadSucursal(); 
	public String getDireccionSucursal();
	public long getProveedor();
	public long getProducto(); 
	public long getPrecioCompraProducto();
	public long getPrecioVentaProducto();
	public long getNivelReorden();

}
