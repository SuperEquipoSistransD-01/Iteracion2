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


import java.math.BigDecimal;



import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Bodega;
import uniandes.isis2304.parranderos.negocio.Carrito;
import uniandes.isis2304.parranderos.negocio.Clientes;
import uniandes.isis2304.parranderos.negocio.EnDisplay;
import uniandes.isis2304.parranderos.negocio.EstaEnCarrito;
import uniandes.isis2304.parranderos.negocio.Estante;
import uniandes.isis2304.parranderos.negocio.Pedidos;
import uniandes.isis2304.parranderos.negocio.Productos;
import uniandes.isis2304.parranderos.negocio.Promociones;
import uniandes.isis2304.parranderos.negocio.Proveedores;
import uniandes.isis2304.parranderos.negocio.Sucursal;
import uniandes.isis2304.parranderos.negocio.Usuarios;
import uniandes.isis2304.parranderos.negocio.VOConsultaFrecuentes;
import uniandes.isis2304.parranderos.negocio.VOEstaEnCarrito;
import uniandes.isis2304.parranderos.negocio.numProductosPedidos;
import uniandes.isis2304.parranderos.negocio.AcuerdoCompra;
import uniandes.isis2304.parranderos.negocio.ClienteSucursal;
import uniandes.isis2304.parranderos.negocio.Compras;
import uniandes.isis2304.parranderos.negocio.ConsultaDemanda;
import uniandes.isis2304.parranderos.negocio.CantProductoComprado;
import uniandes.isis2304.parranderos.negocio.ConsultaFrecuentes;


/**
 * Clase para el manejador de persistencia del proyecto Parranderos
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLBar, SQLBebedor, SQLBebida, SQLGustan, SQLSirven, SQLTipoBebida y SQLVisitan, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author Germán Bravo
 */
public class PersistenciaParranderos 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaParranderos.class.getName());
	
	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaParranderos instance;
	
	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;
	
	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	
	/**
	 * Atributo para el acceso a las sentencias SQL propias a PersistenciaParranderos
	 */
	private SQLUtil sqlUtil;
	
	/**
	 * Atributo para el acceso a la tabla  de la base de datos
	 */

	
	private SQLSucursal sqlSucursal;
	
	private SQLEnDisplay sqlEnDisplay;
	
	private SQLCarrito sqlCarrito;
	
	private SQLEstaEnCarrito sqlEstaEnCarrito;
	
	private SQLProducto sqlProducto;
	
	private SQLProveedores sqlProveedor;
	
	private SQLBodega sqlBodega;
	
	private SQLEstante sqlEstante;
	
	private SQLAcuerdosCompra sqlAcuerdosCompra;
	
	private SQLClientes sqlClientes;
	
	private SQLClienteSucursal sqlClienteSucursal;
	
	private SQLUsuarios sqlUsuarios;
	
	private SQLPromociones sqlPromociones;
	
	private SQLCompras sqlCompras;
	
	private SQLCantProductoComprado sqlCantProductoComprado;
	
	private SQLPedido sqlPedido;
	
	private SQLStockDisponible sqlStockDisponible;
	
	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaParranderos ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("Parranderos");		
		crearClasesSQL ();
		
		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("Superandes_sequence");
		tablas.add ("TIPOBEBIDA");
		tablas.add ("BEBIDA");
		tablas.add ("BAR");
		tablas.add ("BEBEDOR");
		tablas.add ("GUSTAN");
		tablas.add ("SIRVEN");
		tablas.add ("VISITAN");
		tablas.add ("SUCURSALES");
		tablas.add ("CLIENTES");
		tablas.add ("CLIENTESSUCURSALES");
		tablas.add("USUARIOS");
}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaParranderos (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaParranderos getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaParranderos ();
		}
		return instance;
	}
	
	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaParranderos getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaParranderos (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
	
	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		
		return resp;
	}
	
	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
	
		sqlUtil = new SQLUtil(this);
		sqlSucursal = new SQLSucursal(this);
		sqlProducto = new SQLProducto(this);
		sqlProveedor = new SQLProveedores(this);
		sqlBodega = new SQLBodega(this);
		sqlEstante = new SQLEstante(this);
		sqlAcuerdosCompra = new SQLAcuerdosCompra(this);
		sqlClientes = new SQLClientes(this);
		sqlClienteSucursal = new SQLClienteSucursal(this);
		sqlUsuarios = new SQLUsuarios(this);
		sqlCarrito = new SQLCarrito(this);
		sqlEnDisplay = new SQLEnDisplay(this);
		sqlEstaEnCarrito = new SQLEstaEnCarrito(this);
		sqlCompras = new SQLCompras(this);
		sqlCantProductoComprado = new SQLCantProductoComprado(this);
		sqlPedido = new SQLPedido(this);
		sqlStockDisponible = new SQLStockDisponible(this);
	}

	/**
	 * @return La cadena de caracteres con el nombre del secuenciador de parranderos
	 */
	private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
    }
	
	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE BEBIDA
	 *****************************************************************/

	/**
	 * Método que inserta, de manera transaccional, una tupla en la tabla TipoBebida
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	
	/* ****************************************************************
	 * 			Métodos para manejar las SUCURSALES
	 *****************************************************************/

	public Sucursal adicionarSucursal(String nombre, String direccion, String ciudad, long area) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlSucursal.adicionarSucursal(pm, nombre, direccion, ciudad, area);
            tx.commit();
            
            log.trace ("Sucursal: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Sucursal(nombre, direccion, ciudad, area);
        }
        catch (Exception e)
        {
        	System.out.println("LAcosdn");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Carrito solicitarCarrito(long clienteCC, String ciudadSucursal, String direccionSucursal, long abandono) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlCarrito.solicitarCarrito(pm, clienteCC, ciudadSucursal, direccionSucursal, abandono);
            tx.commit();
            
            log.trace ("Carrito: " + clienteCC + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Carrito(clienteCC, ciudadSucursal, direccionSucursal, abandono);
        }
        catch (Exception e)
        {
        	System.out.println("LAcosdn");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Carrito abandonarCarrito(long clienteCC, String ciudadSucursal, String direccionSucursal, long abandono) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas1 = sqlCarrito.nuevoAbandono(pm, clienteCC, ciudadSucursal, direccionSucursal);
            long tuplasInsertadas2 = sqlEstaEnCarrito.abandonarCarrito1(pm, clienteCC, ciudadSucursal, direccionSucursal, abandono);
            long tuplasInsertadas3 = sqlCarrito.abandonarCarrito(pm, clienteCC, ciudadSucursal, direccionSucursal, 0);
            tx.commit();
            
            log.trace ("Carrito abandono: " + clienteCC + ": " + tuplasInsertadas1 + " tuplas insertadas"+tuplasInsertadas2+tuplasInsertadas3);
            return new Carrito(clienteCC, ciudadSucursal, direccionSucursal, abandono);
        }
        catch (Exception e)
        {
        	System.out.println("LAcosdn");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarParranderos ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarParranderos (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1, -1, -1, -1, -1, -1, -1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}
	
	public List<EstaEnCarrito> darCarritosAbandonados (long documento, long clave)
	{
//		for (EstaEnCarrito tb : sqlEstaEnCarrito.darCarritosAbandonados (pmf.getPersistenceManager()))
//        {
//			System.out.println("holiwis");
//        	System.out.println(tb.toString());
//        }
		return sqlEstaEnCarrito.darCarritosAbandonados (pmf.getPersistenceManager(), documento, clave);
	}

	public Productos adicionarProducto(String nombre, String marca, String presentacion, String unidadMedida,
			String tipoProducto, String categoriaAlmacenamiento, long precioUnitario, long precioUnidadMedida,
			long cantidadPresentacion, long volumenEmpaque, long pesoEmpaque) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long codigoProducto = nextval ();
     
            long tuplasInsertadas = sqlProducto.adicionarProducto(pm, codigoProducto, nombre, marca, presentacion, unidadMedida, tipoProducto, categoriaAlmacenamiento, precioUnitario, precioUnidadMedida, cantidadPresentacion, volumenEmpaque, pesoEmpaque);
           
        
            tx.commit();
          
            
            log.trace ("Producto: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Productos(codigoProducto, nombre, marca, presentacion, unidadMedida, tipoProducto, categoriaAlmacenamiento,
    				precioUnitario, precioUnidadMedida, cantidadPresentacion, volumenEmpaque, pesoEmpaque);
        }
        catch (Exception e)
        {
        
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public Proveedores adicionarProveedor(long nit, String nombre, long calificacion) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            System.out.println("Antes de long");
            long tuplasInsertadas = sqlProveedor.adicionarProveedor(pm, nit, nombre, calificacion);
            System.out.println("Antes de commit");
            tx.commit();
            System.out.println("Despues de commit");
            log.trace ("Proveedor: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Proveedores(nit, nombre, calificacion);
        }
        catch (Exception e)
        {
        	System.out.println("LAcosdn");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
		
	}

	public Bodega adicionarBodega(String nombreSucursal, String direccionSucursal, String categoriaAlmacenamiento,long capacidad) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            System.out.println("Antes de long");
            long idBodega = nextval ();
            long tuplasInsertadas = sqlBodega.adicionarBodega(pm, idBodega, nombreSucursal, direccionSucursal, categoriaAlmacenamiento, capacidad);
            System.out.println("Antes de commit");
            tx.commit();
            System.out.println("Despues de commit");
            log.trace ("Bodega: " + idBodega + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Bodega(idBodega, nombreSucursal, direccionSucursal, categoriaAlmacenamiento, capacidad);
        }
        catch (Exception e)
        {
        	System.out.println("LAcosdn");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public void terminarCompraCarrito(long clientecc, String nombreSucursal, String direccionSucursal) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            
            long tuplasEliminadas = sqlEstaEnCarrito.terminarCompra(pm, clientecc, nombreSucursal, direccionSucursal) + sqlCarrito.terminarCompra(pm, clientecc, nombreSucursal, direccionSucursal);
            
            tx.commit();
            System.out.println("Despues de commit");
            log.trace ("carrito: " + tuplasEliminadas + " tuplas eliminadas");
        }
        catch (Exception e)
        {
        	System.out.println("LAcosdn");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public Estante adicionarEstante(String nombreSucursal, String direccionSucursal,
			String tipoProducto, long capacidad) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            System.out.println("Antes de long");
            long idEstante = nextval ();
            long tuplasInsertadas = sqlEstante.adicionarEstante(pm, idEstante, nombreSucursal, direccionSucursal, tipoProducto, capacidad);
            System.out.println("Antes de commit");
            tx.commit();
            System.out.println("Despues de commit");
            log.trace ("Estante: " + idEstante + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Estante(idEstante, nombreSucursal, direccionSucursal, tipoProducto, capacidad);
        }
        catch (Exception e)
        {
        	System.out.println("LAcosdn");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Compras adicionarCompra(Timestamp fecha, String ciudadSucursal, String direccionSucursal, long cliente) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long codigo = nextval ();
            System.out.println(codigo);
            long tuplasInsertadas = sqlCompras.adicionarCompra(pm, codigo, fecha, ciudadSucursal, direccionSucursal, cliente);
            tx.commit();
            log.trace ("Compra: " + codigo + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Compras(codigo, fecha, ciudadSucursal, direccionSucursal, cliente);
        }
        catch (Exception e)
        {
        	System.out.println("falla");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public CantProductoComprado adicionarCantProductoComprado(long compra, long producto, long cantProductos) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlCantProductoComprado.adicionarCantProductoComprado(pm, compra, producto, cantProductos);
            tx.commit();
            log.trace ("CantProductoComprado: " + compra + "-" + producto + ": " + tuplasInsertadas + " tuplas insertadas");
            return new CantProductoComprado(compra, producto, cantProductos);
        }
        catch (Exception e)
        {
        	System.out.println("falla");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public AcuerdoCompra adicionarAcuerdoCompra(String ciudadSucursal, String direccionSucursal, long proveedor,
			long producto, long precioCompraProducto, long precioVentaProducto, long nivelReorden) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            System.out.println("Antes de long");
            long tuplasInsertadas = sqlAcuerdosCompra.adicionarAcuerdoCompra(pm, ciudadSucursal, direccionSucursal, proveedor, producto, precioCompraProducto, precioVentaProducto, nivelReorden);
            System.out.println("Segundo Punto");
            tx.commit();
            System.out.println("Despues de commit");
            log.trace ("Acuerdo de Compra: " + tuplasInsertadas + " tuplas modificadas");
            return new AcuerdoCompra(ciudadSucursal, direccionSucursal, proveedor, producto, precioCompraProducto, precioVentaProducto, nivelReorden);
        }
        catch (Exception e)
        {
        	System.out.println("Excepcion");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public void adicionarPedidoConsolidado(ArrayList<ArrayList<Long>> pedidos, long proveedor, String ciudadSucursal, String direccionSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            
            long diasParaEntrega = Long.parseLong(JOptionPane.showInputDialog (new JFrame(), "En cuantos días espera que llegue el pedido del proveedor de código " + proveedor + "?", "", JOptionPane.QUESTION_MESSAGE));
            long codigoConsolidado = nextval ();
            Timestamp fechaPedido = obtenerFechaActual();
            
            long tuplasInsertadas = 0;
            
            ArrayList<Long> producto;
            long codigo = 0;
            for (int i = 0; i < pedidos.size(); i++)
            {
            	producto = pedidos.get(i);
            	codigo = nextval ();
            	tuplasInsertadas= tuplasInsertadas + sqlPedido.adicionarPedido(pm, codigo, ciudadSucursal, direccionSucursal, proveedor, producto.get(0), fechaPedido, producto.get(1), diasParaEntrega, codigoConsolidado);
            	
            	//Deja los espacios disponibles del producto solicitado en 0 para que no puedan solicitarse más luego de un pedido que no ha llegado
            	tuplasInsertadas = tuplasInsertadas + volver0EspacioDisponible(producto.get(0), ciudadSucursal, direccionSucursal);
            }
            	
            tx.commit();
            log.trace ("Acuerdo de Compra: " + tuplasInsertadas + " tuplas modificadas");
            }
        catch (Exception e)
        {
        	System.out.println("Excepcion");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public long registrarLlegadaPedidoConsolidado(String ciudadSucursal, String direccionSucursal, long pedidoConsolidado)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin(); 
     
            long tuplasModificadas = sqlPedido.registrarLlegadaPedidoConsolidado(pm, ciudadSucursal, direccionSucursal, pedidoConsolidado);
            
            if (tuplasModificadas > 0)
            {
            	adicionarProductosPedidoAStock(pedidoConsolidado, ciudadSucursal, direccionSucursal);
            }
            
            tx.commit();
            
            log.trace ("Pedidos: " + tuplasModificadas + " tuplas modificadas");
            
            return tuplasModificadas;
        }
        catch (Exception e)
        {
        
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return 0;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Clientes registrarCliente(long numDocumento, String tipoDocumento, String nombre, String correoElectronico, String clave) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            System.out.println("Antes de long");
            long tuplasInsertadas = sqlClientes.registrarCliente(pm,numDocumento, tipoDocumento, nombre, correoElectronico, clave);
            tx.commit();
            System.out.println("Despues de commit");
            log.trace ("Acuerdo de Compra: " + tuplasInsertadas + " tuplas modificadas");
            return new Clientes(numDocumento, tipoDocumento, nombre, correoElectronico, clave);
        }
        catch (Exception e)
        {
        	System.out.println("Excepcion");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public ClienteSucursal registrarClienteSucursal(String ciudadSucursal, String direccionSucursal, long cliente) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            System.out.println("Antes de long");
            long tuplasInsertadas = sqlClienteSucursal.registrarClienteSucursal(pm, ciudadSucursal, direccionSucursal, cliente);
            tx.commit();
            System.out.println("Despues de commit");
            log.trace ("Acuerdo de Compra: " + tuplasInsertadas + " tuplas modificadas");
            return new ClienteSucursal(ciudadSucursal, direccionSucursal, cliente);
        }
        catch (Exception e)
        {
        	System.out.println("Excepcion");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public Usuarios registrarUsuario(long numDocumento, String nombre, String correoElectronico, String clave, String rol, String ciudadSucursal, String direccionSucursal) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            System.out.println("Antes de long");
            long tuplasInsertadas = sqlUsuarios.registrarUsuario(pm, numDocumento, nombre, correoElectronico, clave, rol, ciudadSucursal, direccionSucursal);
            tx.commit();
            System.out.println("Despues de commit");
            log.trace ("Acuerdo de Compra: " + tuplasInsertadas + " tuplas modificadas");
            return new Usuarios(numDocumento, nombre, correoElectronico, clave, rol, ciudadSucursal, direccionSucursal);
        }
        catch (Exception e)
        {
        	System.out.println("Excepcion");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<Usuarios> obtenerUsuario(long numDocumento, String clave) {
		for (Usuarios tb : sqlUsuarios.obtenerUsuario(pmf.getPersistenceManager(), numDocumento, clave))
      {
			System.out.println("holiwis");
      	//System.out.println(tb.toString());
      }
		return sqlUsuarios.obtenerUsuario(pmf.getPersistenceManager(), numDocumento, clave);
	}
	
	public Timestamp obtenerFechaActual()
	{
		Timestamp fechaActual = new Timestamp(System.currentTimeMillis());
		return fechaActual;
	}
	
	public Productos obtenerProducto(long codigo)
	{
		return sqlProducto.obtenerProducto(pmf.getPersistenceManager(), codigo).get(0);
	}
	
	public Estante obtenerEstante(String ciudadSucursal, String direccionSucursal, long producto)
	{
		return sqlEstante.obtenerEstante(pmf.getPersistenceManager(), ciudadSucursal, direccionSucursal, producto).get(0);
	}
	
	public Bodega obtenerBodega(String ciudadSucursal, String direccionSucursal, long producto)
	{
		return sqlBodega.obtenerBodega(pmf.getPersistenceManager(), ciudadSucursal, direccionSucursal, producto).get(0);
	}
	
	public List<EstaEnCarrito> obtenerProductosCarrito(long clienteCC, String ciudadSucursal, String direccionSucursal) {
		return sqlEstaEnCarrito.obtenerProductosCarrito(pmf.getPersistenceManager(), clienteCC, ciudadSucursal, direccionSucursal);
	}
	
	public long obtenerNumProductoEnSucursal(long producto, String ciudadSucursal, String direccionSucursal)
	{
		return sqlProducto.obtenerNumProductoEnSucursal(pmf.getPersistenceManager(), producto, ciudadSucursal, direccionSucursal);
	}
	
	public long obtenerDisponibilidadProductoEnSucursal(long producto, String ciudadSucursal, String direccionSucursal)
	{
		return sqlProducto.obtenerDisponibilidadProductoEnSucursal(pmf.getPersistenceManager(), producto, ciudadSucursal, direccionSucursal);
	}
	
	public AcuerdoCompra obtenerAcuerdoCompra(long producto, String ciudadSucursal, String direccionSucursal)
	{
		return sqlAcuerdosCompra.obtenerAcuerdosCompra(pmf.getPersistenceManager(), producto, ciudadSucursal, direccionSucursal).get(0);
	}
	
	public long volver0EspacioDisponible(long producto, String ciudadSucursal, String direccionSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
		long tuplasModificadas = 0;		
		
		tx.begin();
		
		try
		{
			long estante = obtenerEstante(ciudadSucursal, direccionSucursal, producto).getCodigo();
			tuplasModificadas = tuplasModificadas + sqlEnDisplay.volver0EspacioDisponible(pm, estante, producto);
		}
		catch  (Exception e)
		{
			System.out.println("no tiene estante");
		}
		try
		{
			long bodega = obtenerBodega(ciudadSucursal, direccionSucursal, producto).getCodigo();
			tuplasModificadas = tuplasModificadas + sqlStockDisponible.volver0EspacioDisponible(pm, bodega, producto);
		}
		catch  (Exception e)
		{
			System.out.println("no tiene bodega");
		}
		
		tx.commit();
		
		return tuplasModificadas;
	}
	
	public void adicionarProductosPedidoAStock(long pedidoConsolidado, String ciudadSucursal, String direccionSucursal)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            
            List<numProductosPedidos> pedidos = sqlPedido.obtenerPedidosDePedidoConsolidado(pm, pedidoConsolidado);
            
            long tuplasModificadas = 0;
            
            long producto = 0;
            long cantidad = 0;
            for(int i = 0; i < pedidos.size(); i++)
            {
            	producto = pedidos.get(i).getProducto();
            	cantidad = pedidos.get(i).getVolumenProducto();
            	
            	try
            	{
            		long bodega = obtenerBodega(ciudadSucursal, direccionSucursal, producto).getCodigo();
            		
            		tuplasModificadas = tuplasModificadas + sqlStockDisponible.sumarCantProductoABodega(pm, bodega, producto, cantidad);
            	}
            	
            	catch(Exception e)
            	{
            		long estante = obtenerEstante(ciudadSucursal, direccionSucursal, producto).getCodigo();
            		
            		tuplasModificadas = tuplasModificadas + sqlEnDisplay.sumarCantProductoAEstante(pm, estante, producto, cantidad);  		
            	}
            }
            
            tx.commit();
            
            log.trace (tuplasModificadas + " tuplas modificadas");
        }
        catch (Exception e)
        {
        	System.out.println("falla");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public List<ConsultaFrecuentes> darFrecuentesSucursal(long documento, long clave) {
		return sqlEstaEnCarrito.darFrecuentesSucursal (pmf.getPersistenceManager(), documento, clave);
	}
	
	public List <ConsultaFrecuentes> darFrecuentesGeneral() {
		return sqlEstaEnCarrito.darFrecuentesGeneral (pmf.getPersistenceManager());
	}
	
	public List <ConsultaDemanda> darConsultaDemanda() {
		return sqlPedido.darConsultaDemanda(pmf.getPersistenceManager());
	}
	
	public List <ConsultaDemanda> darConsultaDemandaActual() {
		return sqlPedido.darConsultaDemandaActual(pmf.getPersistenceManager());
	}

	public Promociones registrarPromocion(String nombrePromocion, Timestamp fechaInicio, long diasDuracion,
			String descripcion, String tipo, long finalizada, String ciudadSucursal, String direccionSucursal,
			long producto) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            System.out.println("Antes de long");
            long tuplasInsertadas = sqlPromociones.adicionarPromociones(pm, nombrePromocion, fechaInicio, diasDuracion, descripcion, tipo, finalizada, ciudadSucursal, direccionSucursal, producto);
            tx.commit();
            System.out.println("Despues de commit");
            log.trace ("Acuerdo de Compra: " + tuplasInsertadas + " tuplas modificadas");
            return new Promociones(nombrePromocion, fechaInicio, diasDuracion, descripcion, tipo, finalizada, ciudadSucursal, direccionSucursal, producto) ;       }
        catch (Exception e)
        {
        	System.out.println("Excepcion");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public EnDisplay productoAlCarritoD(long clienteCC, String ciudadSucursal, String direccionSucursal, long producto,
			long cantidad) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlEnDisplay.productoAlCarritoD(pm, clienteCC, ciudadSucursal, direccionSucursal, producto, cantidad);
            long tuplasInsertadas1 = sqlEstaEnCarrito.productoAlCarritoC(pm, clienteCC, ciudadSucursal, direccionSucursal, 0, producto, cantidad);
            tx.commit();
            
            log.trace ("Carrito: " + clienteCC + ": " + tuplasInsertadas + " tuplas insertadas" + tuplasInsertadas1);
            return new EnDisplay(producto,	0, 0, cantidad, 0);
        }
        catch (Exception e)
        {
        	System.out.println("LAcosdn");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public EstaEnCarrito productoAlCarritoC(long clienteCC, String ciudadSucursal, String direccionSucursal,
			long producto, long cantidad) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long tuplasInsertadas = sqlEstaEnCarrito.productoAlCarritoC(pm, clienteCC, ciudadSucursal, direccionSucursal, 0, producto, cantidad);
            tx.commit();
            
            log.trace ("Carrito: " + clienteCC + ": " + tuplasInsertadas + " tuplas insertadas");
            return new EstaEnCarrito(clienteCC, ciudadSucursal, direccionSucursal, 0, producto, cantidad);
        }
        catch (Exception e)
        {
        	System.out.println("LAcosdn");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}

	public EnDisplay devolverProductoCarritoD(long clienteCC, String ciudadSucursal, String direccionSucursal,
			long producto) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            long abandono = 0;
            long tuplasInsertadas = sqlEnDisplay.devolverProductoCarritoD(pm, clienteCC, ciudadSucursal, direccionSucursal, producto, abandono);
            long tuplasInsertadas1 = sqlEstaEnCarrito.devolverProductoCarritoC(pm, clienteCC, ciudadSucursal, direccionSucursal, producto, abandono);
            tx.commit();
            
            log.trace ("Carrito: " + clienteCC + ": " + tuplasInsertadas + " tuplas insertadas"+tuplasInsertadas1);
            return new EnDisplay(producto,	0, 0, 0, 0);
        }
        catch (Exception e)
        {
        	System.out.println("LAcosdn");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
	
	public String pagarCompra(long clienteCC, String ciudadSucursal, String direccionSucursal) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
        	//Información para registro de compra y facturacion
        	List<EstaEnCarrito> productosCarrito = obtenerProductosCarrito(clienteCC, ciudadSucursal, direccionSucursal);
        	Productos producto = null;
        	int cantidad = 0;
        	long precio = 0;
        	long total = 0;
        	Timestamp fechaActual = obtenerFechaActual();
        	
        	String textoFactura = "Fecha: " + fechaActual.toString() + "\n"
        			+ "Cédula del Cliente: " + Long.toString(clienteCC) + "\n"
        			+ "Producto:\tCantidad:\tPrecio:\n";
        	
        	tx.begin();
        	
        	//Información para determinar si debe realizarse pedido del producto
        	long numEnSucursal = 0;
        	long nivelReorden = 0;
        	long espacioDisponible = 0;
        	AcuerdoCompra acuerdoCompra = null;
        	
        	//Diccionario para agrupar pedidos de proveedores y hacerlos en conjunto
        	HashMap<Long, ArrayList<ArrayList<Long>>> pedidosPorProveedor = new HashMap<Long, ArrayList<ArrayList<Long>>>();
        	ArrayList<Long> numProductosPedidos = null;
        	
        	
        	Compras compra = adicionarCompra(fechaActual, ciudadSucursal, direccionSucursal, clienteCC);
        	
        	for (int i = 0; i < productosCarrito.size(); i++)
        	{		
        		cantidad = (int) productosCarrito.get(i).getCantidad();
        		producto = obtenerProducto(productosCarrito.get(i).getCodigo());
        		precio = cantidad * producto.getPrecioUnitario();
        		total = total + precio;
        		
        		textoFactura = textoFactura + 
        				producto.getNombre() + "\tX" + String.valueOf(cantidad) + "\t$" + String.valueOf(precio) + "\n";
        	
        		adicionarCantProductoComprado(compra.getCodigo(), producto.getCodigo(), cantidad);
        		
        		numEnSucursal = obtenerNumProductoEnSucursal(producto.getCodigo(), ciudadSucursal, direccionSucursal);
        		acuerdoCompra = obtenerAcuerdoCompra(producto.getCodigo(), ciudadSucursal, direccionSucursal);
        		nivelReorden = acuerdoCompra.getNivelReorden();
        		espacioDisponible = obtenerDisponibilidadProductoEnSucursal(producto.getCodigo(), ciudadSucursal, direccionSucursal);

        		if (numEnSucursal <= nivelReorden && espacioDisponible > 0)
        		{
        			if (!pedidosPorProveedor.containsKey(acuerdoCompra.getProveedor()))
        			{	
        				numProductosPedidos = new ArrayList<Long>();
            			numProductosPedidos.add(producto.getCodigo());
            			numProductosPedidos.add(espacioDisponible);
            			pedidosPorProveedor.put(acuerdoCompra.getProveedor(),new ArrayList<ArrayList<Long>>());
            			pedidosPorProveedor.get(acuerdoCompra.getProveedor()).add(numProductosPedidos);
        			}
        			else
        			{
        				numProductosPedidos = new ArrayList<Long>();
            			numProductosPedidos.add(producto.getCodigo());
            			numProductosPedidos.add(espacioDisponible);
            			pedidosPorProveedor.get(acuerdoCompra.getProveedor()).add(numProductosPedidos);
        			}
        		}	
        	}        	
        	
        	//Terminación de texto de factura de compra
        	textoFactura = textoFactura +
        			"Total: \t\t$" + String.valueOf(total);
        	
        	//Proceso de petición de pedidos del mismo proveedor en conjunto
        	Iterator<Long> pedidos = pedidosPorProveedor.keySet().iterator();
        	long proveedor = 0;
        	
        	while (pedidos.hasNext())
        	{
        		proveedor = pedidos.next();
        		adicionarPedidoConsolidado(pedidosPorProveedor.get(proveedor), proveedor, ciudadSucursal, direccionSucursal);
        	}
        	
        	//Borra elementos de EstaEnCarrito y al carrito
        	terminarCompraCarrito(clienteCC, ciudadSucursal, direccionSucursal);    
        	
            tx.commit();
            
            return textoFactura;
        }
        catch (Exception e)
        {
        	System.out.println("LAcosdn");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
        
	}

	public EstaEnCarrito devolverProductosAbandono(List<VOEstaEnCarrito> lista, long documento, long clave) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            
            for (VOEstaEnCarrito tb : lista)
            {
            	//System.out.println(tb.getClienteCC()+ "," + tb.getCiudadSucursal()+"," + tb.getDireccionSucursal()+"," + tb.getCodigo());
            	sqlEnDisplay.devolverProductoCarritoD(pm, tb.getClienteCC(), tb.getCiudadSucursal(), tb.getDireccionSucursal(), tb.getCodigo(), 1);
            	sqlEstaEnCarrito.devolverProductoCarritoC(pm, tb.getClienteCC(), tb.getCiudadSucursal(), tb.getDireccionSucursal(), tb.getCodigo(), 1);
            }
            
            sqlCarrito.eliminarAbandonados(pm, documento, clave);
            
            tx.commit();
            
            //log.trace ("Recogiendo Carritos Abandonados: " + documento + ": " + tuplasInsertadas + " tuplas insertadas"+tuplasInsertadas1);
            return new EstaEnCarrito();
        }
        catch (Exception e)
        {
        	System.out.println("LAcosdn");
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}






 }
