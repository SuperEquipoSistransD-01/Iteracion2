package uniandes.isis2304.parranderos.persistencia;

import java.util.List;



import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Sucursal;


class SQLStockDisponible {
	
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
	public SQLStockDisponible (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para adicionar un GUSTAN a la base de datos de Parranderos
	 * @param pm - El manejador de persistencia
	 * @param idBebedor - El identificador del bebedor
	 * @param idBebida - El identificador de la bebida
	 * @return EL número de tuplas insertadas
	 */
	
	public long volver0EspacioDisponible(PersistenceManager pm, long bodega, long producto) 
	{
		Query q = pm.newQuery(SQL, "UPDATE StockDisponible SET espacioDisponible = 0 WHERE producto = ? and bodega = ?");
		q.setParameters(producto, bodega);
        return (long) q.executeUnique();
	}
	
	public long sumarCantProductoABodega(PersistenceManager pm, long bodega, long producto, long cantidad) 
	{
		Query q = pm.newQuery(SQL, "UPDATE StockDisponible SET cantidad = cantidad + ?  WHERE producto = ? and bodega = ?");
		q.setParameters(cantidad, producto, bodega);
        return (long) q.executeUnique();
	}
	

}
