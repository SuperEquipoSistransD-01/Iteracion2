package uniandes.isis2304.parranderos.negocio;

public class AcuerdoCompra implements VOAcuerdoCompra{
	private String ciudadSucursal; 
	private String direccionSucursal;
    private long proveedor;
    private long producto; 
    private long precioCompraProducto;
    private long precioVentaProducto;
    private long nivelReorden;
    
    public AcuerdoCompra()
    {
    	this.setCiudadSucursal("");
    	this.setDireccionSucursal("");
    	this.setNivelReorden(0);
    	this.setPrecioCompraProducto(0);
    	this.setPrecioVentaProducto(0);
    	this.setProducto(0);
    	this.setProveedor(0);
    }
    
    public AcuerdoCompra(String ciudadSucursal, String direccionSucursal, long proveedor,
			long producto, long precioCompraProducto, long precioVentaProducto, long nivelReorden)
    {
    	this.setCiudadSucursal(ciudadSucursal);
    	this.setDireccionSucursal(direccionSucursal);
    	this.setNivelReorden(nivelReorden);
    	this.setPrecioCompraProducto(precioCompraProducto);
    	this.setPrecioVentaProducto(precioVentaProducto);
    	this.setProducto(producto);
    	this.setProveedor(proveedor);
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

	public long getProveedor() {
		return proveedor;
	}

	public void setProveedor(long proveedor) {
		this.proveedor = proveedor;
	}

	public long getProducto() {
		return producto;
	}

	public void setProducto(long producto) {
		this.producto = producto;
	}

	public long getPrecioCompraProducto() {
		return precioCompraProducto;
	}

	public void setPrecioCompraProducto(long precioCompraProducto) {
		this.precioCompraProducto = precioCompraProducto;
	}

	public long getPrecioVentaProducto() {
		return precioVentaProducto;
	}

	public void setPrecioVentaProducto(long precioVentaProducto) {
		this.precioVentaProducto = precioVentaProducto;
	}

	public long getNivelReorden() {
		return nivelReorden;
	}

	public void setNivelReorden(long nivelReorden) {
		this.nivelReorden = nivelReorden;
	}
}
