package uniandes.isis2304.parranderos.negocio;

public class ConsultaFrecuentes implements VOConsultaFrecuentes{
	private String mes;
	private long cliente;
	
	public ConsultaFrecuentes() {
		this.setCliente(0);
		this.setMes("");
	}
	public ConsultaFrecuentes(String mes, long cliente) {
		this.setCliente(cliente);
		this.setMes(mes);
	}
	public String getMes() {
		return mes;
	}
	public void setMes(String mes) {
		this.mes = mes;
	}
	public long getCliente() {
		return cliente;
	}
	public void setCliente(long cliente) {
		this.cliente = cliente;
	}
	

}
