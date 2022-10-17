package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

class SQLClientes {
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
	public SQLClientes (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	public long registrarCliente(PersistenceManager pm, long numDocumento, String tipoDocumento, String nombre, String correoElectronico, String clave)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO Clientes (numDocumento, tipoDocumento, nombre, correoElectronico, clave) VALUES (?,?,?,?,?)");
        q.setParameters(numDocumento, tipoDocumento, nombre, correoElectronico, clave);
        return (long) q.executeUnique();
	}
}
