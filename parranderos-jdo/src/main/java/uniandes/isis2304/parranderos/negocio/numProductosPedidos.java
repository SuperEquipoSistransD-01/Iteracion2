package uniandes.isis2304.parranderos.negocio;

public class numProductosPedidos {
	
	private long producto;
	private long volumenProducto;
	
	public numProductosPedidos()
	{
		this.setProducto(0);
		this.setVolumenProducto(0);
	}
	
	public numProductosPedidos(long producto, long volumenProducto)
	{
		this.setProducto(producto);
		this.setVolumenProducto(volumenProducto);
	}
	public long getProducto() {
		return producto;
	}
	public void setProducto(long producto) {
		this.producto = producto;
	}
	public long getVolumenProducto() {
		return volumenProducto;
	}
	public void setVolumenProducto(long volumenProducto) {
		this.volumenProducto = volumenProducto;
	}

}
