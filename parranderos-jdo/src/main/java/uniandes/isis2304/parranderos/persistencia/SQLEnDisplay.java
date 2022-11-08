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
class SQLEnDisplay 
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
	public SQLEnDisplay (PersistenciaParranderos pp)
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
	public long productoAlCarritoD(PersistenceManager pm, long clienteCC, String ciudadSucursal, String direccionSucursal, long producto, long cantidad) 
	{
		
        Query q = pm.newQuery(SQL, "update enDisplay set endisplay.cantidad = endisplay.cantidad - ? where endisplay.producto in (select productos.codigo from endisplay, estantes, productos, carritos where productos.codigo = ? and productos.codigo = endisplay.producto and endisplay.estante = estantes.codigo and estantes.ciudadSucursal = carritos.ciudadSucursal and estantes.direccionSucursal = carritos.direccionSucursal and carritos.clienteCC = ? and carritos.abandono = 0 and carritos.ciudadSucursal = ? and carritos.direccionSucursal = ?)");
        q.setParameters(cantidad, producto, clienteCC, ciudadSucursal, direccionSucursal);
        return (long) q.executeUnique();
	}
	
	public long devolverProductoCarritoD(PersistenceManager pm, long clienteCC, String ciudadSucursal, String direccionSucursal, long producto) 
	{
        Query q = pm.newQuery(SQL, "update enDisplay "
        		+ "set endisplay.cantidad = endisplay.cantidad + (select c.cantidad from estaEnCarrito c where c.clienteCC = ? and c.abandono = 0 and c.ciudadSucursal = ? and c.direccionSucursal = ?) "
        		+ "where endisplay.producto in (select productos.codigo from endisplay, estantes, productos, carritos where productos.codigo = ? and productos.codigo = endisplay.producto and endisplay.estante = estantes.codigo and estantes.ciudadSucursal = carritos.ciudadSucursal "
        		+ "and estantes.direccionSucursal = carritos.direccionSucursal and carritos.clienteCC = ? and carritos.abandono = 0 and carritos.ciudadSucursal = ? and carritos.direccionSucursal = ?)");
        q.setParameters(clienteCC, ciudadSucursal, direccionSucursal, producto, clienteCC, ciudadSucursal, direccionSucursal);
        return (long) q.executeUnique();
	}
	
	
	

}