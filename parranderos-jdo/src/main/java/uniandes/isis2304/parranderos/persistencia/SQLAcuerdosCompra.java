package uniandes.isis2304.parranderos.persistencia;

import java.util.List;



import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.AcuerdoCompra;


class SQLAcuerdosCompra {
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
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
	public SQLAcuerdosCompra (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	public long adicionarAcuerdoCompra (PersistenceManager pm, String ciudadSucursal, String direccionSucursal, long proveedor, long producto, long precioCompraProducto, long precioVentaProducto, long nivelReorden) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO AcuerdosCompra (ciudadSucursal, direccionSucursal, proveedor, producto, precioCompraProducto, precioVentaProducto, nivelReorden) VALUES (?,?,?,?,?,?,?");
        q.setParameters(ciudadSucursal, direccionSucursal, proveedor, producto, precioCompraProducto, precioVentaProducto, nivelReorden, ciudadSucursal, direccionSucursal, proveedor, producto);
        return (long) q.executeUnique();
	}

	public List<AcuerdoCompra> obtenerAcuerdosCompra(PersistenceManager pm, long producto, String ciudadSucursal, String direccionSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM AcuerdosCompra WHERE producto = ? and ciudadSucursal = ? and direccionSucursal = ?");
		q.setResultClass(AcuerdoCompra.class);
		q.setParameters(producto, ciudadSucursal, direccionSucursal);
		return (List<AcuerdoCompra>) q.executeList();
	}

}
