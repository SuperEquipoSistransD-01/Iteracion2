/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import java.util.List;



import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Sucursal;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto GUSTAN de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLCarrito 
{
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
	public SQLCarrito (PersistenciaParranderos pp)
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
	public long solicitarCarrito(PersistenceManager pm, long clienteCC, String ciudadSucursal, String direccionSucursal, long abandono) 
	{
		
        Query q = pm.newQuery(SQL, "INSERT INTO " + "carritos" + "(clienteCC, ciudadSucursal, direccionSucursal, abandono) values (?, ?, ?, ?)");
        q.setParameters(clienteCC, ciudadSucursal, direccionSucursal, abandono);
        return (long) q.executeUnique();
	}
	
	public long nuevoAbandono(PersistenceManager pm, long clienteCC, String ciudadSucursal, String direccionSucursal) 
	{
		
        Query q = pm.newQuery(SQL, "INSERT INTO " + "carritos" + "(clienteCC, ciudadSucursal, direccionSucursal, abandono) values (?, ?, ?, 1)");
        q.setParameters(clienteCC, ciudadSucursal, direccionSucursal);
        return (long) q.executeUnique();
	}
	
	public long abandonarCarrito(PersistenceManager pm, long clienteCC, String ciudadSucursal, String direccionSucursal, long abandono) 
	{
        Query q = pm.newQuery(SQL, "delete from carritos " + 
         " where clientecc = ? and ciudadSucursal = ? and direccionSucursal = ? and abandono = ?");
        q.setParameters(clienteCC, ciudadSucursal, direccionSucursal, abandono);
        
        return (long) q.executeUnique();
	}

	public long eliminarAbandonados(PersistenceManager pm, long documento, long clave) {
		Query q = pm.newQuery(SQL, "delete from carritos where abandono = ? and ciudadSucursal = (select ciudadSucursal from usuarios where numDocumento = ? and clave = ?) and direccionSucursal = (select direccionSucursal from usuarios where numDocumento = ? and clave = ?)");
		q.setParameters(documento, clave, documento, clave);
		        
		return (long) q.executeUnique();
		
	}
	

<<<<<<< HEAD
<<<<<<< HEAD

	public long terminarCompra(PersistenceManager pm, long clienteCC, String ciudadSucursal, String direccionSucursal) {
		Query q = pm.newQuery(SQL, "DELETE FROM Carritos where clienteCC = ? and ciudadSucursal = ? and direccionSucursal = ? and abandono = 0");
		q.setParameters(clienteCC, ciudadSucursal, direccionSucursal);
		return (long) q.executeUnique();
	}
=======
>>>>>>> 4208028ca2dd24e895903ff4bc831786158c5fef
=======
>>>>>>> refs/remotes/origin/master



}