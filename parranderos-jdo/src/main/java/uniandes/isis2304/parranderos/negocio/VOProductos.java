package uniandes.isis2304.parranderos.negocio;

public interface VOProductos {
	public long getCodigo();
	public String getNombre();
	public String getMarca();
	public long getPrecioUnitario();	
	public String getPresentacion();
	public long getVolumenEmpaque();	
	public long getCantidadPresentacion();	
	public long getPrecioUnidadMedida();
	public String getUnidadMedida();
	public String getTipoProducto();
	public long getPesoEmpaque();

	public String getCategoriaAlmacenamiento();

	}

