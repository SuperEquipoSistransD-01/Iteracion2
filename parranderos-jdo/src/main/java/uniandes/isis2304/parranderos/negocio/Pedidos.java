package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;

public class Pedidos {
    private long codigo;
    private String ciudadSucursal;
    private String direccionSucursal; 
    private String proveedor;
    private long producto;
    private long fechaPedido;
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

}
