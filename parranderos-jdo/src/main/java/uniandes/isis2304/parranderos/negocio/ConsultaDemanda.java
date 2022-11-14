package uniandes.isis2304.parranderos.negocio;



public class ConsultaDemanda implements VOConsultaDemanda{
	private long producto;
	
	public ConsultaDemanda() {
		this.setProducto(0);
	}
	
	public ConsultaDemanda(long producto) {
		this.setProducto(producto);
	}
	public long getProducto() {
		return producto;
	}
	public void setProducto(long producto) {
		this.producto = producto;
	}


}
