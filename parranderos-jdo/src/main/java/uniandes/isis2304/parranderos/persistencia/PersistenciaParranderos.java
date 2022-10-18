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
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Bodega;
import uniandes.isis2304.parranderos.negocio.Clientes;
import uniandes.isis2304.parranderos.negocio.Estante;
import uniandes.isis2304.parranderos.negocio.Productos;
import uniandes.isis2304.parranderos.negocio.Promociones;
import uniandes.isis2304.parranderos.negocio.Proveedores;
import uniandes.isis2304.parranderos.negocio.Sucursal;
import uniandes.isis2304.parranderos.negocio.Usuarios;
import uniandes.isis2304.parranderos.negocio.AcuerdoCompra;
import uniandes.isis2304.parranderos.negocio.ClienteSucursal;


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
	
	private SQLProducto sqlProducto;
	
	private SQLProveedores sqlProveedor;
	
	private SQLBodega sqlBodega;
	
	private SQLEstante sqlEstante;
	
	private SQLAcuerdosCompra sqlAcuerdosCompra;
	
	private SQLClientes sqlClientes;
	
	private SQLClienteSucursal sqlClienteSucursal;
	
	private SQLUsuarios sqlUsuarios;
	
	private SQLPromociones sqlPromociones;
	
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
            return new Bodega(idBodega, nombreSucursal, direccionSucursal, capacidad, categoriaAlmacenamiento);
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

	public Estante adicionarEstante(String nombreSucursal, String direccionSucursal, long volumenLimite, long pesoLimite,
			String tipoProducto) {
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();            
            System.out.println("Antes de long");
            long idEstante = nextval ();
            long tuplasInsertadas = sqlEstante.adicionarEstante(pm, idEstante, nombreSucursal, direccionSucursal, volumenLimite, pesoLimite, tipoProducto);
            System.out.println("Antes de commit");
            tx.commit();
            System.out.println("Despues de commit");
            log.trace ("Estante: " + idEstante + ": " + tuplasInsertadas + " tuplas insertadas");
            return new Estante(idEstante, nombreSucursal, direccionSucursal, volumenLimite, pesoLimite, tipoProducto);
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
		return sqlUsuarios.obtenerUsuario(pmf.getPersistenceManager(), numDocumento, clave);
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
 }
