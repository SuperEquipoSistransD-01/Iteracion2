package uniandes.isis2304.parranderos.negocio;



import java.sql.Timestamp;

public class Pedidos implements VOPedidos{
    private long codigo;
    private String ciudadSucursal;
    private String direccionSucursal; 
    private long proveedor;
    private long producto;
    private Timestamp fechaPedido;
    private long diasDesdePedido;
    private long volumenProducto;
    private long diasParaEntrega;
    
    public Pedidos() {
    	this.setCiudadSucursal("");
    	this.setCodigo(0);
    	this.setDiasDesdePedido(0);
    	this.setDiasParaEntrega(0);
    	this.setDireccionSucursal("");
    	this.setFechaPedido(fechaPedido);
    	this.setProducto(0);
    	this.setProveedor(0);
    	this.setVolumenProducto(0);
    }
    
    public Pedidos(long codigo,
    String ciudadSucursal,
    String direccionSucursal, 
    long proveedor,
    long producto,
    Timestamp fechaPedido,
    long diasDesdePedido,
    long volumenProducto,
    long diasParaEntrega) {
    	this.setCiudadSucursal(ciudadSucursal);
    	this.setCodigo(codigo);
    	this.setDiasDesdePedido(diasDesdePedido);
    	this.setDiasParaEntrega(diasParaEntrega);
    	this.setDireccionSucursal(direccionSucursal);
    	this.setFechaPedido(fechaPedido);
    	this.setProducto(volumenProducto);
    	this.setProveedor(proveedor);
    	this.setVolumenProducto(volumenProducto);
    }
    
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
	public Timestamp getFechaPedido() {
		return fechaPedido;
	}
	public void setFechaPedido(Timestamp fechaPedido) {
		this.fechaPedido = fechaPedido;
	}

}
