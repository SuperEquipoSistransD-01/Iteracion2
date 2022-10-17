package uniandes.isis2304.parranderos.negocio;



import oracle.sql.DATE;

public class Pedidos implements VOPedidos{
    private long codigo;
    private String ciudadSucursal;
    private String direccionSucursal; 
    private String proveedor;
    private long producto;
    private DATE fechaPedido;
    private long diasDesdePedido;
    private long volumenProducto;
    private long diasParaEntrega;
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
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
	public String getProveedor() {
		return proveedor;
	}
	public void setProveedor(String proveedor) {
		this.proveedor = proveedor;
	}
	public long getProducto() {
		return producto;
	}
	public void setProducto(long producto) {
		this.producto = producto;
	}
	public long getDiasDesdePedido() {
		return diasDesdePedido;
	}
	public void setDiasDesdePedido(long diasDesdePedido) {
		this.diasDesdePedido = diasDesdePedido;
	}
	public long getVolumenProducto() {
		return volumenProducto;
	}
	public void setVolumenProducto(long volumenProducto) {
		this.volumenProducto = volumenProducto;
	}
	public long getDiasParaEntrega() {
		return diasParaEntrega;
	}
	public void setDiasParaEntrega(long diasParaEntrega) {
		this.diasParaEntrega = diasParaEntrega;
	}
	public DATE getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(DATE fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

}
