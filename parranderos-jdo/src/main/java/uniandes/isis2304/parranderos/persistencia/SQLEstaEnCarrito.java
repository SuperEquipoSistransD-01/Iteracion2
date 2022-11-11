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

import uniandes.isis2304.parranderos.negocio.EstaEnCarrito;
import uniandes.isis2304.parranderos.negocio.Sucursal;
//import uniandes.isis2304.parranderos.negocio.TipoBebida;
import uniandes.isis2304.parranderos.negocio.Usuarios;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto GUSTAN de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLEstaEnCarrito 
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
	public SQLEstaEnCarrito (PersistenciaParranderos pp)
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
	public long productoAlCarritoC(PersistenceManager pm, long clienteCC, String ciudadSucursal, String direccionSucursal, long abandono, long codigo, long cantidad) 
	{
		
        Query q = pm.newQuery(SQL, "insert into EstaEnCarrito (clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo, cantidad) values (?, ?, ?, ?, ?, ?)");
        q.setParameters(clienteCC, ciudadSucursal, direccionSucursal, abandono, codigo, cantidad);
        return (long) q.executeUnique();
	}

	public long devolverProductoCarritoC(PersistenceManager pm, long clienteCC, String ciudadSucursal,
			String direccionSucursal, long producto, long abandono) {
		 Query q = pm.newQuery(SQL, "delete from estaEnCarrito where clienteCC = ? and ciudadSucursal = ? and direccionSucursal = ? and codigo = ? and abandono = ?");
	     q.setParameters(clienteCC, ciudadSucursal, direccionSucursal, producto, abandono);
	     return (long) q.executeUnique();
	}
	
	public long abandonarCarrito1(PersistenceManager pm, long clienteCC, String ciudadSucursal,
			String direccionSucursal, long abandono) {
		Query q = pm.newQuery(SQL, "update estaEnCarrito " + 
		        "set abandono = ?" + " where clientecc = ? and ciudadSucursal = ? and direccionSucursal = ?");
		q.setParameters(abandono,clienteCC, ciudadSucursal, direccionSucursal);
	    return (long) q.executeUnique();
	}

	public List<EstaEnCarrito> obtenerProductosCarrito(PersistenceManager pm, long clienteCC, String ciudadSucursal, String direccionSucursal) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM EstaEnCarrito WHERE clienteCC = ? and ciudadSucursal = ? and direccionSucursal = ?");
		q.setResultClass(Usuarios.class);
		q.setParameters(clienteCC, ciudadSucursal, direccionSucursal);
		return (List<EstaEnCarrito>) q.executeList();
	}
	
	public List<EstaEnCarrito> darCarritosAbandonados (PersistenceManager pm, long documento, long clave)
	{
		System.out.println("Estoy en SQLCARRITO");
		Query q = pm.newQuery(SQL, "SELECT estaEnCarrito.* FROM estaEnCarrito, usuarios where usuarios.numdocumento = ? and usuarios.clave = ? and usuarios.ciudadSucursal = estaEnCarrito.ciudadSucursal and estaEnCarrito.abandono = 1 and estaEnCarrito.direccionSucursal = usuarios.direccionsucursal");
		q.setResultClass(EstaEnCarrito.class);
		q.setParameters(documento, clave);
		System.out.println("HolaMundo");
		
//		for (EstaEnCarrito tb : (List<EstaEnCarrito>) q.executeList())
//        {
//			System.out.println("holiwis1");
//        	System.out.println(tb.toString());
//        }
		//System.out.print(q.setResultClass(EstaEnCarrito.class));
		return (List<EstaEnCarrito>) q.executeList();
	}

}