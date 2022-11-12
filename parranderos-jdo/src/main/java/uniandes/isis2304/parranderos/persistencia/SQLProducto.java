package uniandes.isis2304.parranderos.persistencia;

import java.util.List;



import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.gson.JsonArray;

import uniandes.isis2304.parranderos.negocio.EstaEnCarrito;
import uniandes.isis2304.parranderos.negocio.Productos;

class SQLProducto {
	private final static String SQL = PersistenciaParranderos.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaParranderos pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLProducto (PersistenciaParranderos pp)	
	{
		this.pp = pp;
	}
	
	public long adicionarProducto(PersistenceManager pm, long codigo, String nombre, String marca, String presentacion, String unidadMedida,
			String tipoProducto, String categoriaAlmacenamiento, long precioUnitario, long precioUnidadMedida,
			long cantidadPresentacion, long volumenEmpaque, long pesoEmpaque) 
	{
		System.out.println("holaEstoy en sql producto");
        Query q = pm.newQuery(SQL, "INSERT INTO " + "PRODUCTOS" + "(codigo, nombre, marca, precioUnitario, presentacion, precioUnidadMedida, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, tipoProducto, categoriaAlmacenamiento)  values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        System.out.println(q);
        q.setParameters(codigo, nombre, marca, precioUnitario, presentacion, precioUnidadMedida, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, tipoProducto, categoriaAlmacenamiento);
        return (long) q.executeUnique();
	}

	public List<Productos> obtenerProducto(PersistenceManager pm, long codigo) {
		Query q = pm.newQuery(SQL, "SELECT * FROM Productos WHERE codigo = ?");
		q.setResultClass(Productos.class);
		q.setParameters(codigo);
		return (List<Productos>) q.executeList();
	}

	public long obtenerNumProductoEnSucursal(PersistenceManager pm, long producto,String ciudadSucursal, String direccionSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT SUM(cantidad) FROM((SELECT cantidad FROM ((SELECT ciudad, direccion, codigo as codigoBodega FROM (Sucursales INNER JOIN Bodegas ON ciudad = ciudadSucursal AND direccion = direccionSucursal) WHERE ciudad = ? and direccion = ?) INNER JOIN (SELECT * FROM StockDisponible WHERE producto = ?) ON codigoBodega = bodega)) UNION ALL (SELECT cantidad FROM ((SELECT ciudad, direccion, codigo as codigoEstante FROM (Sucursales INNER JOIN Estantes ON ciudad = ciudadSucursal AND direccion = direccionSucursal) WHERE ciudad = ? and direccion = ?)INNER JOIN (SELECT *FROM EnDisplay WHERE producto = ?) ON codigoEstante = estante)))");	
		q.setResultClass(Long.class);
		q.setParameters(ciudadSucursal, direccionSucursal, producto, ciudadSucursal, direccionSucursal, producto);
		return (long) q.executeList().get(0);
	}

}
