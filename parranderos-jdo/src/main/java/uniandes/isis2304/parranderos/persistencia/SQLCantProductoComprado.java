package uniandes.isis2304.parranderos.persistencia;

import uniandes.isis2304.parranderos.negocio.Usuarios;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;


public class SQLCantProductoComprado {
	
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
	public SQLCantProductoComprado (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	public long adicionarCantProductoComprado(PersistenceManager pm, long compra, long producto, long cantProductos)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO CantProductosComprados (compra, producto, cantProductos) VALUES (?,?,?)");
        q.setParameters(compra, producto, cantProductos);
        return (long) q.executeUnique();
	}

}
