package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;



public class Compras implements VOCompras{
	private long codigo;
	private Timestamp fecha ;
	
	public Compras() {
		this.setCodigo(0);
		this.setFecha(fecha);
	}
	public Compras(long codigo, Timestamp fecha) {
		this.setCodigo(codigo);
		this.setFecha(fecha);
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public Timestamp getFecha() {
		return fecha;
	}
	public void setFecha(Timestamp fecha) {
		this.fecha = fecha;
	}

}
