package uniandes.isis2304.parranderos.persistencia;

import java.util.List;



import javax.jdo.PersistenceManager;
import javax.jdo.Query;


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
        Query q = pm.newQuery(SQL, "UPDATE AcuerdosCompra SET ciudadSucursal = ?, direccionSucursal = ?, proveedor = ?, producto = ?, precioCompraProducto = ?, precioVentaProducto = ?, nivelReorden = ? WHERE ciudadSucursal = ? and direccionSucursal = ? and proveedor = ? and producto = ?");
        q.setParameters(ciudadSucursal, direccionSucursal, proveedor, producto, precioCompraProducto, precioVentaProducto, nivelReorden, ciudadSucursal, direccionSucursal, proveedor, producto);
        System.out.println("hola");
        return (long) q.executeUnique();
	}

}
