package uniandes.isis2304.parranderos.negocio;

import oracle.sql.DATE;

public class Compras implements VOCompras{
	private long codigo;
	private DATE fecha ;
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public DATE getFecha() {
		return fecha;
	}
	public void setFecha(DATE fecha) {
		this.fecha = fecha;
	}

}
