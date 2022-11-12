package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLCompras {
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
	public SQLCompras (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}

	public long adicionarCompra(PersistenceManager pm, long codigo, Timestamp fecha, String ciudadSucursal,
			String direccionSucursal, long cliente) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO Compras (codigo, fecha, ciudadSucursal, direccionSucursal, cliente) values (?, ?, ?, ?, ?)");
		q.setParameters(codigo, fecha, ciudadSucursal, direccionSucursal, cliente);
        return (long) q.executeUnique(); 
	}

}
