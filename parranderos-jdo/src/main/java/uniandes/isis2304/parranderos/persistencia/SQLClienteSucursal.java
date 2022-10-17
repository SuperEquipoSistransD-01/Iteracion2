package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

public class SQLClienteSucursal {
	
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
	public SQLClienteSucursal (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	public long registrarClienteSucursal (PersistenceManager pm, String ciudadSucursal, String direccionSucursal, long cliente) 
	{
		System.out.println("punto1");
        Query q = pm.newQuery(SQL, "INSERT INTO ClientesSucursales (ciudadSucursal, direccionSucursal, cliente) VALUES (?,?,?)");
        q.setParameters(ciudadSucursal, direccionSucursal, cliente);
        System.out.println("punto2");
        return (long) q.executeUnique();
	}

}
