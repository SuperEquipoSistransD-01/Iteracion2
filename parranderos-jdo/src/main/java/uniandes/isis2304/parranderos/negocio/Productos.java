package uniandes.isis2304.parranderos.negocio;

public class Productos implements VOProductos{
	private long codigo;
	private String nombre;
	private String marca;
	private long precioUnitario;
	private String presentacion; 
	private long precioUnidadMedida;
	private long cantidadPresentacion;
	private String unidadMedida;
	private long volumenEmpaque;
	private long pesoEmpaque;
	private String tipoProducto;
	private String categoriaAlmacenamiento;
	
	public Productos()
	{
		this.setCodigo(0);
		this.setNombre("");
		this.setMarca("");
		this.setPrecioUnitario(0);
		this.setPresentacion("");
		this.setPrecioUnidadMedida(0);
		this.setCantidadPresentacion(0);
		this.setUnidadMedida("");
		this.setVolumenEmpaque(0);
		this.setPesoEmpaque(0);
		this.setTipoProducto("");
		this.setCategoriaAlmacenamiento("");
	}
	
	public Productos(long codigo, String nombre, String marca, String presentacion, String unidadMedida,
			String tipoProducto, String categoriaAlmacenamiento, long precioUnitario, long precioUnidadMedida,
			long cantidadPresentacion, long volumenEmpaque, long pesoEmpaque)
	{
		this.setCodigo(codigo);
		this.setNombre(nombre);
		this.setMarca(marca);
		this.setPrecioUnitario(precioUnitario);
		this.setPresentacion(presentacion);
		this.setPrecioUnidadMedida(precioUnidadMedida);
		this.setCantidadPresentacion(cantidadPresentacion);
		this.setUnidadMedida(unidadMedida);
		this.setVolumenEmpaque(volumenEmpaque);
		this.setPesoEmpaque(pesoEmpaque);
		this.setTipoProducto(tipoProducto);
		this.setCategoriaAlmacenamiento(categoriaAlmacenamiento);
	}
	public long getCodigo() {
		return codigo;
	}
	public void setCodigo(long codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public long getPrecioUnitario() {
		return precioUnitario;
	}
	public void setPrecioUnitario(long precioUnitario) {
		this.precioUnitario = precioUnitario;
	}
	public String getPresentacion() {
		return presentacion;
	}
	public void setPresentacion(String presentacion) {
		this.presentacion = presentacion;
	}
	public long getVolumenEmpaque() {
		return volumenEmpaque;
	}
	public void setVolumenEmpaque(long volumenEmpaque) {
		this.volumenEmpaque = volumenEmpaque;
	}
	public long getCantidadPresentacion() {
		return cantidadPresentacion;
	}
	public void setCantidadPresentacion(long cantidadPresentacion) {
		this.cantidadPresentacion = cantidadPresentacion;
	}
	public long getPrecioUnidadMedida() {
		return precioUnidadMedida;
	}
	public void setPrecioUnidadMedida(long precioUnidadMedida) {
		this.precioUnidadMedida = precioUnidadMedida;
	}
	public String getUnidadMedida() {
		return unidadMedida;
	}
	public void setUnidadMedida(String unidadMedida) {
		this.unidadMedida = unidadMedida;
	}
	public String getTipoProducto() {
		return tipoProducto;
	}
	public void setTipoProducto(String tipoProducto) {
		this.tipoProducto = tipoProducto;
	}
	public long getPesoEmpaque() {
		return pesoEmpaque;
	}
	public void setPesoEmpaque(long pesoEmpaque) {
		this.pesoEmpaque = pesoEmpaque;
	}
	public String getCategoriaAlmacenamiento() {
		return categoriaAlmacenamiento;
	}
	public void setCategoriaAlmacenamiento(String categoriaAlmacenamiento) {
		this.categoriaAlmacenamiento = categoriaAlmacenamiento;
	}
	public String toString()
	{
		return "Producto [codigo="+codigo+", nombre="+nombre+", marca="+marca+", precioUnitario="+precioUnitario+
				", presentacion" +presentacion+", precioUnidadMedida="+precioUnidadMedida+", cantidadPresentacion"
				+cantidadPresentacion+", unidadMedida="+unidadMedida+", volumenEmpaque="+volumenEmpaque+", pesoEmpaque="
				+pesoEmpaque+", tipoProducto="+tipoProducto+"categoriaAlmacenamiento"+categoriaAlmacenamiento+"]";
	}

}
