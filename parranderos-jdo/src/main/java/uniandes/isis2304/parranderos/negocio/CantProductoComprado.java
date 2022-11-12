package uniandes.isis2304.parranderos.negocio;

public class CantProductoComprado implements VOCantProductoCompra{
	
	private long compra;
	private long producto;
	private long cantProductos;
	
	public CantProductoComprado()
	{
		this.setCantProductos(0);
		this.setCompra(0);
		this.setProducto(0);
	}
	
	public CantProductoComprado(long compra, long producto, long cantProductos)
	{
		this.setCantProductos(cantProductos);
		this.setCompra(compra);
		this.setProducto(producto);
	}

	public long getCompra() {
		return compra;
	}

	public void setCompra(long compra) {
		this.compra = compra;
	}

	public long getProducto() {
		return producto;
	}

	public void setProducto(long producto) {
		this.producto = producto;
	}

	public long getCantProductos() {
		return cantProductos;
	}

	public void setCantProductos(long cantProductos) {
		this.cantProductos = cantProductos;
	}

}
