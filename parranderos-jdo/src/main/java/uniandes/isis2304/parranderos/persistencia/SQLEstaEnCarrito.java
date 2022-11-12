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

import uniandes.isis2304.parranderos.negocio.ConsultaFrecuentes;
import uniandes.isis2304.parranderos.negocio.EstaEnCarrito;
import uniandes.isis2304.parranderos.negocio.Sucursal;
//import uniandes.isis2304.parranderos.negocio.TipoBebida;
import uniandes.isis2304.parranderos.negocio.Usuarios;
import uniandes.isis2304.parranderos.negocio.VOConsultaFrecuentes;

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
		Query q = pm.newQuery(SQL, "SELECT * FROM EstaEnCarrito WHERE clienteCC = ? and ciudadSucursal = ? and direccionSucursal = ? and abandono = 0");
		q.setResultClass(EstaEnCarrito.class);
		q.setParameters(clienteCC, ciudadSucursal, direccionSucursal);
		return (List<EstaEnCarrito>) q.executeList();
	}
	
	public List<EstaEnCarrito> darCarritosAbandonados (PersistenceManager pm, long documento, long clave)
	{
		Query q = pm.newQuery(SQL, "SELECT estaEnCarrito.* FROM estaEnCarrito, usuarios where usuarios.numdocumento = ? and usuarios.clave = ? and usuarios.ciudadSucursal = estaEnCarrito.ciudadSucursal and estaEnCarrito.abandono = 1 and estaEnCarrito.direccionSucursal = usuarios.direccionsucursal");
		q.setResultClass(EstaEnCarrito.class);
		q.setParameters(documento, clave);
		return (List<EstaEnCarrito>) q.executeList();
	}

	
	//Consultar Clientes Frecuentes
	public List<ConsultaFrecuentes> darFrecuentesSucursal(PersistenceManager pm, long documento,
			long clave) {
		Query q = pm.newQuery(SQL, "select distinct to_char(c1.fecha, 'Month') as mes, c1.cliente from compras c1, compras c2, usuarios where usuarios.rol = 'gs' and usuarios.numdocumento = ? and clave = ? and c1.ciudadSucursal = c2.ciudadSucursal and c1.ciudadSucursal = usuarios.ciudadSucursal and c1.direccionSucursal = c2.direccionSucursal and c1.direccionSucursal = usuarios.direccionSucursal and c1.cliente = c2.cliente and to_char(c1.fecha, 'Month') = to_char(c2.fecha, 'Month') and c1.codigo != c2.codigo");
		q.setResultClass(ConsultaFrecuentes.class);
		q.setParameters(documento, clave);
		return (List<ConsultaFrecuentes>) q.executeList();
	}

	public List <ConsultaFrecuentes> darFrecuentesGeneral(PersistenceManager pm) {
		// TODO Revisar si es gerente general (quizás deba hacer esto antes). Si sí lo es, ejecuto sql que ya hice, el general
		Query q = pm.newQuery(SQL, "select distinct to_char(c1.fecha, 'Month') as mes, c1.cliente from compras c1, compras c2 where c1.cliente = c2.cliente and to_char(c1.fecha, 'Month') = to_char(c2.fecha, 'Month') and c1.codigo != c2.codigo");
		q.setResultClass(ConsultaFrecuentes.class);
		return (List<ConsultaFrecuentes>) q.executeList();
	}

}