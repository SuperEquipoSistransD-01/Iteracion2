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

import javax.jdo.PersistenceManager;

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
	
	public VOEstante adicionarEstante(String nombreSucursal, String direccionSucursal, long capacidad, String categoriaAlmacenamiento) {
		log.info("Adicionando bodegavolumenLimite ["+capacidad+"]");
		Estante resp = pp.adicionarEstante(nombreSucursal, direccionSucursal, categoriaAlmacenamiento, capacidad);
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
	
	public VOClientes registrarCliente(long numDocumento, String tipoDocumento, String nombre, String correoElectronico, String clave){
		log.info("Registrando numDocumento ["+numDocumento+"]");
		Clientes resp = pp.registrarCliente(numDocumento, tipoDocumento, nombre, correoElectronico, clave);
		log.info ("Registrado cliente: " + resp + " tuplas insertadas");
		return resp;
	}
	
	public VOClienteSucursal registrarClienteSucursal(String ciudadSucursal, String direccionSucursal, long cliente){
		log.info("Registrando cliente ["+cliente+"]");
		ClienteSucursal resp = pp.registrarClienteSucursal(ciudadSucursal, direccionSucursal, cliente);
		log.info ("Registrado cliente: " + resp + " tuplas insertadas");
		return resp;
	}
	
	public VOUsuarios registrarUsuario(long numDocumento, String nombre, String correoElectronico, String clave, String rol, String ciudadSucursal, String direccionSucursal){
		log.info("Registrando Usuario ["+numDocumento+"]");
		Usuarios resp = pp.registrarUsuario(numDocumento, nombre, correoElectronico, clave, rol, ciudadSucursal, direccionSucursal);
		log.info ("Registrado cliente: " + resp + " tuplas insertadas");
		return resp;
	}
	
	public List<Usuarios> obtenerUsuario(long numDocumento, String clave) {
		log.info("Iniciando sesión del usuario ["+numDocumento+"]");
		List<Usuarios> resp = pp.obtenerUsuario(numDocumento, clave);
		log.info ("Obtenido usuario: " + resp);
		return resp;
	}
	
	/* ****************************************************************
	 * 			Métodos para manejar la relación CARRITO
	 *****************************************************************/
	
	
	

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

	public VOPromociones adicionarPromocion(String nombrePromocion, Timestamp fechaInicio, long diasDuracion,
			String descripcion, String tipo, long finalizada, String ciudadSucursal, String direccionSucursal,
			long producto) {
		log.info("Registrando numDocumento ["+nombrePromocion+"]");
		Promociones resp = pp.registrarPromocion(nombrePromocion, fechaInicio, diasDuracion, descripcion, tipo, finalizada, ciudadSucursal, direccionSucursal, producto);
		log.info ("Registrado cliente: " + resp + " tuplas insertadas");
		return resp;
		
	}
	
	//ITERACION 3 :)
	
	public VOCarrito solicitarCarrito(long clienteCC, String ciudadSucursal, String direccionSucursal, long abandono) {
		log.info ("Adicionando carrito [" + clienteCC + ", " + ciudadSucursal +", "+direccionSucursal+ ", "+abandono+ "]");
        Carrito resp = pp.solicitarCarrito(clienteCC, ciudadSucursal, direccionSucursal, abandono);
        log.info ("Adicionando carrito: " + resp + " tuplas insertadas");
        return resp;
	}
	
	public VOCarrito abandonarCarrito(long clienteCC, String ciudadSucursal, String direccionSucursal, long abandono) {
		log.info ("Abanadonando carrito [" + clienteCC + ", " + ciudadSucursal +", "+direccionSucursal+ ", "+abandono+ "]");
		System.out.println("EnSuperAndes");
        Carrito resp = pp.abandonarCarrito(clienteCC, ciudadSucursal, direccionSucursal, abandono);
        log.info ("Abandonando carrito: " + resp + " tuplas insertadas");
        return resp;
	}

	public VOEnDisplay productoALCarritoD(long clienteCC, String ciudadSucursal, String direccionSucursal, long producto,
			long cantidad) {
		log.info ("Adicionando producto [" + clienteCC + ", " + ciudadSucursal +", "+direccionSucursal+ ", "+producto+ "]");
        EnDisplay resp = pp.productoAlCarritoD(clienteCC, ciudadSucursal, direccionSucursal, producto, cantidad);
        log.info ("Abandonando carrito: " + resp + " tuplas insertadas");
		return resp;
	}

	public VOEstaEnCarrito productosAlCarritoC(long clienteCC, String ciudadSucursal, String direccionSucursal,
			long producto, long cantidad) {
		log.info ("Adicionando producto [" + clienteCC + ", " + ciudadSucursal +", "+direccionSucursal+ ", "+producto+ "]");
        EstaEnCarrito resp = pp.productoAlCarritoC(clienteCC, ciudadSucursal, direccionSucursal, producto, cantidad);
        log.info ("Abandonando carrito: " + resp + " tuplas insertadas");
		return resp;
	}

	public VOEnDisplay devolverProductoCarritoD(long clienteCC, String ciudadSucursal, String direccionSucursal,
			long producto) {
		log.info ("Devolviendo producto [" + clienteCC + ", " + ciudadSucursal +", "+direccionSucursal+ ", "+producto+ "]");
        EnDisplay resp = pp.devolverProductoCarritoD(clienteCC, ciudadSucursal, direccionSucursal, producto);
        log.info ("Devolviendo producto: " + resp + " tuplas insertadas");
		return resp;
	}

	public VOEstaEnCarrito devolverProductoCarritoC(long clienteCC, String ciudadSucursal, String direccionSucursal,
			long producto) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<VOEstaEnCarrito> darVOEstaEnCarrito (long documento, long clave)
	{
		log.info ("Generando los VO de Tipos de bebida");        
        List<VOEstaEnCarrito> voTipos = new LinkedList<VOEstaEnCarrito> ();
        for (EstaEnCarrito tb : pp.darCarritosAbandonados (documento, clave))
        {
        	System.out.println(tb.toString());
        	voTipos.add (tb);
        }
        log.info ("Generando los VO de Tipos de bebida: " + voTipos.size() + " existentes");
        return voTipos;
	}
	
	public List<VOConsultaFrecuentes> darFrecuentesSucursal(long documento, long clave) {
		log.info ("Generando los VO de Tipos de bebida");        
        List<VOConsultaFrecuentes> voTipos = new LinkedList<VOConsultaFrecuentes> ();
        for (ConsultaFrecuentes tb : pp.darFrecuentesSucursal(documento, clave))
        {
        	System.out.println(tb.toString());
        	voTipos.add(tb);
        }
        log.info ("Generando los VO de Tipos de bebida: " + voTipos.size() + " existentes");
        return voTipos;
	}
	
	public List <VOConsultaFrecuentes> darFrecuentesGeneral() {
		log.info ("Generando los VO de Tipos de bebida");        
        List<VOConsultaFrecuentes> voTipos = new LinkedList<VOConsultaFrecuentes> ();
        for (ConsultaFrecuentes tb : pp.darFrecuentesGeneral())
        {
        	System.out.println(tb.toString());
        	voTipos.add(tb);
        }
        log.info ("Generando los VO de Tipos de bebida: " + voTipos.size() + " existentes");
        
        return voTipos;
		
	}
	
	public List <VOConsultaDemanda> darDemanda() {
		log.info ("Generando los VO de Tipos de bebida");        
        List<VOConsultaDemanda> voTipos = new LinkedList<VOConsultaDemanda> ();
        List<Integer> listilla = new LinkedList<Integer> ();
        if(pp.darConsultaDemanda().size() != 0) {
        for (ConsultaDemanda tb : pp.darConsultaDemanda())
        {
        	//listilla.add(tb.getProducto());
        	
        	if(!listilla.contains((int) tb.getProducto())) {
        		//System.out.println("Holiwis");
        		System.out.println(tb.getProducto());
        		voTipos.add(tb);
        		listilla.add((int) tb.getProducto());
        	}
        	
        }
        }
        if(pp.darConsultaDemandaActual().size() != 0) {
        	for (ConsultaDemanda tc : pp.darConsultaDemandaActual())
            {
        		System.out.println("Holiwis entra");
        		//System.out.println(tc.getProducto());
            	if(!listilla.contains((int) tc.getProducto())) {
            		System.out.println(tc.getProducto());
            		voTipos.add(tc);
            		listilla.add((int) tc.getProducto());
            	}
            	
            }
        }

        
        log.info ("Generando los VO de Tipos de bebida: " + voTipos.size() + " existentes");
        
        return voTipos;
		
	}
	
	public String pagarCompra(long clienteCC, String ciudadSucursal, String direccionSucursal) 
	{
		log.info ("Pagando Compra [" + clienteCC + ", " + ciudadSucursal +", "+direccionSucursal+ "]");
        String resp = pp.pagarCompra(clienteCC, ciudadSucursal, direccionSucursal);
        log.info ("Compra Facturada: \n" + resp);
		return resp;
	}

	public VOEstaEnCarrito devolverProductosAbandono(List<VOEstaEnCarrito> lista, long documento, long clave) {
		log.info ("Devolviendo producto [" + documento + ", " + clave+ "]");
        EstaEnCarrito resp = pp.devolverProductosAbandono(lista, documento, clave);
        log.info ("Devolviendo producto: " + resp + " tuplas insertadas");
		return resp;
	}













	
}
