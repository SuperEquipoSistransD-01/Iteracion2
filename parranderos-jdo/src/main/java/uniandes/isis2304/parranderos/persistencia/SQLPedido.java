package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLPedido {
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
	public SQLPedido (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}

	public long adicionarPedido(PersistenceManager pm, long codigo, String ciudadSucursal, String direccionSucursal, long proveedor, Long producto, Timestamp fechaPedido, Long cantidad, long diasParaEntrega,long codigoConsolidado) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO Pedidos (codigo, ciudadSucursal, direccionSucursal, proveedor, producto, fechaPedido, volumenProducto, diasParaEntrega, pedidoConsolidado, diasDesdePedido, llego) VALUES (?,?,?,?,?,?,?,?,?,0,0)");
		q.setParameters(codigo, ciudadSucursal, direccionSucursal, proveedor, producto, fechaPedido, cantidad, diasParaEntrega, codigoConsolidado);
		long e = (long) q.executeUnique();
        return e;
	}

}
