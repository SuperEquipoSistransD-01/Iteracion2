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

package uniandes.isis2304.parranderos.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;
import uniandes.isis2304.parranderos.persistencia.PersistenciaParranderos;

/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Germán Bravo
 */
public class SuperAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(SuperAndes.class.getName());
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia
	 */
	private PersistenciaParranderos pp;
	
	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * El constructor por defecto
	 */
	public SuperAndes ()
	{
		pp = PersistenciaParranderos.getInstance ();
	}
	
	/**
	 * El constructor qye recibe los nombres de las tablas en tableConfig
	 * @param tableConfig - Objeto Json con los nombres de las tablas y de la unidad de persistencia
	 */
	public SuperAndes (JsonObject tableConfig)
	{
		pp = PersistenciaParranderos.getInstance (tableConfig);
	}
	
	/**
	 * Cierra la conexión con la base de datos (Unidad de persistencia)
	 */
	public void cerrarUnidadPersistencia ()
	{
		pp.cerrarUnidadPersistencia ();
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar los TIPOS DE BEBIDA
	 *****************************************************************/
	/**
	 * Adiciona de manera persistente un tipo de bebida 
	 * Adiciona entradas al log de la aplicación
	 * @param nombre - El nombre del tipo de bebida
	 * @return El objeto TipoBebida adicionado. null si ocurre alguna Excepción
	 */
	
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación SUCURSALES
	 *****************************************************************/
	
	public Sucursal adicionarSucursal (String nombre, String direccion, String ciudad, long area)
	{
        log.info ("Adicionando sucursal [" + nombre + ", " + direccion +", "+ciudad+ ", "+area+ "]");
        Sucursal resp = pp.adicionarSucursal(nombre, direccion, ciudad, area);
        log.info ("Adicionando visitan: " + resp + " tuplas insertadas");
        return resp;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación PRODUCTOS
	 *****************************************************************/
	

	
	public Productos adicionarProducto(String nombre, String marca, String presentacion, String unidadMedida,
			String tipoProducto, String categoriaAlmacenamiento, long precioUnitario, long precioUnidadMedida,
			long cantidadPresentacion, long volumenEmpaque, long pesoEmpaque) {
		log.info ("Adicionando producto [" + nombre+ "]");
        Productos resp = pp.adicionarProducto(nombre, marca, presentacion, unidadMedida, tipoProducto, categoriaAlmacenamiento,
				precioUnitario, precioUnidadMedida, cantidadPresentacion, volumenEmpaque, pesoEmpaque);
        log.info ("Adicionando visitan: " + resp + " tuplas insertadas");
        return resp;
	}
	public Proveedores adicionarProveedor(long nit, String nombre, long calificacion) {
		log.info ("Adicionando proveedor [" + nombre+ "]");
        Proveedores resp = pp.adicionarProveedor(nit, nombre, calificacion);
        log.info ("Adicionando visitan: " + resp + " tuplas insertadas");
        return resp;
	}
	
	public VOBodega adicionarBodega(String nombreSucursal, String direccionSucursal, String categoriaAlmacenamiento, long capacidad) {
		log.info("Adicionando bodegaCapacidad ["+capacidad+"]");
		Bodega resp = pp.adicionarBodega(nombreSucursal, direccionSucursal, categoriaAlmacenamiento, capacidad);
		log.info ("Adicionando Bodega: " + resp + " tuplas insertadas");
		return resp;
	}
	
	public VOEstante adicionarEstante(String nombreSucursal, String direccionSucursal, long volumenLimite,
			long pesoLimite, String categoriaAlmacenamiento) {
		log.info("Adicionando bodegavolumenLimite ["+volumenLimite+"]");
		Estante resp = pp.adicionarEstante(nombreSucursal, direccionSucursal, volumenLimite, pesoLimite, categoriaAlmacenamiento);
		log.info ("Adicionando Bodega: " + resp + " tuplas insertadas");
		return resp;
	}
	
	public VOAcuerdoCompra adicionarAcuerdoCompra(String ciudadSucursal, String direccionSucursal, long proveedor,
			long producto, long precioCompraProducto, long precioVentaProducto, long nivelReorden) {
		log.info("Adicionando PrecioCompraProducto ["+precioCompraProducto+"]");
		AcuerdoCompra resp = pp.adicionarAcuerdoCompra(ciudadSucursal, direccionSucursal, proveedor, producto, precioCompraProducto, precioVentaProducto, nivelReorden);
		log.info ("Adicionando Acuerdo de Compra: " + resp + " tuplas insertadas");
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarParranderos ()
	{
        log.info ("Limpiando la BD de Parranderos");
        long [] borrrados = pp.limpiarParranderos();	
        log.info ("Limpiando la BD de Parranderos: Listo!");
        return borrrados;
	}







	
}
