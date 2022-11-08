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

package uniandes.isis2304.parranderos.interfazDemo;

import java.awt.BorderLayout;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;
import uniandes.isis2304.parranderos.interfazApp.PanelDatos;
import uniandes.isis2304.parranderos.negocio.SuperAndes;
import uniandes.isis2304.parranderos.negocio.Usuarios;
import uniandes.isis2304.parranderos.negocio.VOBodega;
import uniandes.isis2304.parranderos.negocio.VOCarrito;
import uniandes.isis2304.parranderos.negocio.VOClienteSucursal;
import uniandes.isis2304.parranderos.negocio.VOClientes;
import uniandes.isis2304.parranderos.negocio.VOEnDisplay;
import uniandes.isis2304.parranderos.negocio.VOEstaEnCarrito;
import uniandes.isis2304.parranderos.negocio.VOEstante;
import uniandes.isis2304.parranderos.negocio.VOProductos;
import uniandes.isis2304.parranderos.negocio.VOPromociones;
import uniandes.isis2304.parranderos.negocio.VOProveedores;
import uniandes.isis2304.parranderos.negocio.VOSucursal;
import uniandes.isis2304.parranderos.negocio.VOUsuarios;
import uniandes.isis2304.parranderos.negocio.VOAcuerdoCompra;


/**
 * Clase principal de la interfaz
 * 
 * @author Germán Bravo
 */
@SuppressWarnings("serial")

public class InterfazSuperandes extends JFrame implements ActionListener
{
	
	private String ciudadSucursal = null;
	private String direccionSucursal = null;
	private String rol = null;
	
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazSuperandes.class.getName());
	
	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigDemo.json"; 
	
	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 
	
	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
    /**
     * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
     */
    private JsonObject tableConfig;
    
    /**
     * Asociación a la clase principal del negocio.
     */
    private SuperAndes parranderos;
    
	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
    /**
     * Objeto JSON con la configuración de interfaz de la app.
     */
    private JsonObject guiConfig;
    
    /**
     * Panel de despliegue de interacción para los requerimientos
     */
    private PanelDatos panelDatos;
    
    /**
     * Menú de la aplicación
     */
    private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
    /**
     * Construye la ventana principal de la aplicación. <br>
     * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
     */
    public InterfazSuperandes( )
    {
        // Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        parranderos = new SuperAndes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }
    
	/* ****************************************************************
	 * 			Métodos para la configuración de la interfaz
	 *****************************************************************/
    /**
     * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
     * @param tipo - El tipo de configuración deseada
     * @param archConfig - Archivo Json que contiene la configuración
     * @return Un objeto JSON con la configuración del tipo especificado
     * 			NULL si hay un error en el archivo.
     */
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    
    /**
     * Método para configurar el frame principal de la aplicación
     */
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }

    /**
     * Método para crear el menú de la aplicación con base em el objeto JSON leído
     * Genera una barra de menú y los menús con sus respectivas opciones
     * @param jsonMenu - Arreglo Json con los menùs deseados
     */
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
    
	/* ****************************************************************
	 * 			Demos de TipoBebida
	 *****************************************************************/
    /**
     * Demostración de creación, consulta y borrado de Tipos de Bebida
     * Muestra la traza de la ejecución en el panelDatos
     * 
     * Pre: La base de datos está vacía
     * Post: La base de datos está vacía
     */
    
    //Empezamos con superandes...
    public void adicionarSucursal( )
    {
    	try 
    	{
    		String nombreSucursal = JOptionPane.showInputDialog (this, "Nombre de la sucursal?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String ciudadSucursal1 = JOptionPane.showInputDialog (this, "Ciudad de la sucursal?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String direccionSucursal1 = JOptionPane.showInputDialog (this, "Direccion de la sucursal?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		long areaSucursal = Long.parseLong(JOptionPane.showInputDialog (this, "Area de la sucursal?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		
    		if (nombreSucursal != null && ciudadSucursal1 != null && direccionSucursal1 != null && areaSucursal != 0 && rol.equals("a"))
    		{
        		VOSucursal tb = parranderos.adicionarSucursal(nombreSucursal, direccionSucursal1, ciudadSucursal1, areaSucursal);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo crear una sucursal con nombre, ciudad, direccion y area : " + nombreSucursal+", "+ciudadSucursal1+", "+direccionSucursal1+", "+areaSucursal);
        		}
        		String resultado = "En adicionarSucursal\n\n";
        		resultado += "Sucursal adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void adicionarProducto( )
    {
    	try 
    	{
    		String nombre = JOptionPane.showInputDialog (this, "Nombre del producto?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String marca = JOptionPane.showInputDialog (this, "Marca del producto?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String presentacion = JOptionPane.showInputDialog (this, "Presentación del producto?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String unidadMedida = JOptionPane.showInputDialog (this, "Unidad de midad del producto?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String tipoProducto = JOptionPane.showInputDialog (this, "Tipo del prducto?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String categoriaAlmacenamiento = JOptionPane.showInputDialog (this, "Categoría del producto?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		long precioUnitario = Long.parseLong(JOptionPane.showInputDialog (this, "Precio Unitario?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		long precioUnidadMedida = Long.parseLong(JOptionPane.showInputDialog (this, "Precio unidad de medida?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		long cantidadPresentacion = Long.parseLong(JOptionPane.showInputDialog (this, "Cantidad Presentación?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		long volumenEmpaque = Long.parseLong(JOptionPane.showInputDialog (this, "Volumen empaque?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		long pesoEmpaque = Long.parseLong(JOptionPane.showInputDialog (this, "Peso empaque?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		
    		if (nombre != null && marca != null && presentacion != null && unidadMedida != null && tipoProducto!=null && categoriaAlmacenamiento!=null
    				&& precioUnitario != 0 && precioUnidadMedida != 0 && cantidadPresentacion != 0 && volumenEmpaque != 0 && pesoEmpaque != 0 && rol.equals("gs"))
    		{
        		VOProductos tb = parranderos.adicionarProducto(nombre, marca, presentacion, unidadMedida, tipoProducto, categoriaAlmacenamiento,
        				precioUnitario, precioUnidadMedida, cantidadPresentacion, volumenEmpaque, pesoEmpaque);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo crear una producto con nombre: " + nombre);
        		}
        		String resultado = "En adicionarSucursal\n\n";
        		resultado += "Sucursal adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void adicionarProveedor( )
    {
    	try 
    	{
    		String nombre = JOptionPane.showInputDialog (this, "Nombre del proveedor?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		long nit = Long.parseLong(JOptionPane.showInputDialog (this, "Nit del proveedor?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		long calificacion = Long.parseLong(JOptionPane.showInputDialog (this, "Calificacion del proveedor?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		
    		if (nombre != null && nit != 0 && calificacion != 0 && rol.equals("gs"))
    		{
        		VOProveedores tb = parranderos.adicionarProveedor(nit, nombre, calificacion);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo crear un proveedor con nit: "+nit);
        		}
        		String resultado = "En adicionarProveedor\n\n";
        		resultado += "Proveedor adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
    public void adicionarPromocion( )
    {
    	try 
    	{
    		String nombrePromocion = JOptionPane.showInputDialog (this, "Nombre de la Promocion?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String ciudadSucursal1 = JOptionPane.showInputDialog (this, "Ciudad de la sucursal asociada?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String direccionSucursal1 = JOptionPane.showInputDialog (this, "Direccion de la sucursal asociada?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String descripcion = JOptionPane.showInputDialog (this, "Descripcion?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String tipo = JOptionPane.showInputDialog (this, "Tipo? Este puede ser 1,2,3,4,5", "Ok", JOptionPane.QUESTION_MESSAGE);
    		long diasDuracion = Long.parseLong(JOptionPane.showInputDialog (this, "Dias duracion de la promo?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		long finalizada = Long.parseLong(JOptionPane.showInputDialog (this, "Está finalizada? Escriba 1 si sí, 2 si no", "Ok", JOptionPane.QUESTION_MESSAGE));
    		long producto = Long.parseLong(JOptionPane.showInputDialog (this, "Codigo del producto", "Ok", JOptionPane.QUESTION_MESSAGE));
    		Timestamp fechaInicio = Timestamp.valueOf(JOptionPane.showInputDialog (this, "Fecha de inicio? Formato DD/MM/YYYY", "Ok", JOptionPane.QUESTION_MESSAGE));
    		
    		if (nombrePromocion != null && ciudadSucursal1 != null && direccionSucursal1 != null && descripcion != null && tipo != null&& producto != 0 && diasDuracion != 0 && finalizada != 0)
    		{
        		VOPromociones tb = parranderos.adicionarPromocion(nombrePromocion, fechaInicio, diasDuracion, descripcion, tipo, finalizada, ciudadSucursal, direccionSucursal, producto);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo crear una sucursal con nombre, ciudad, direccion y area : " + nombrePromocion);
        		}
        		String resultado = "En adicionarSucursal\n\n";
        		resultado += "Sucursal adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void adicionarBodega( )
    {
    	try 
    	{
    		String categoriaAlmacenamiento = JOptionPane.showInputDialog (this, "Categoría Almacenamiento?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		long capacidad = Long.parseLong(JOptionPane.showInputDialog (this, "Capacidad volumétrica de la Bodega?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		if (ciudadSucursal != null && capacidad != 0 && direccionSucursal != null && rol.equals("gs"))
    		{
        		VOBodega tb = parranderos.adicionarBodega(ciudadSucursal, direccionSucursal, categoriaAlmacenamiento,capacidad);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo crear una bodega con capacidad: "+capacidad);
        		}
        		String resultado = "En adicionarBodega\n\n";
        		resultado += "Bodega adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void adicionarEstante( )
    {
    	try 
    	{
    		String categoriaAlmacenamiento = JOptionPane.showInputDialog (this, "Tipo de Producto?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		
    		long volumenLimite = Long.parseLong(JOptionPane.showInputDialog (this, "Capacidad volumétrica del Estante?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		long pesoLimite = Long.parseLong(JOptionPane.showInputDialog (this, "Peso Límite del estante?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		if (ciudadSucursal != null && direccionSucursal != null && volumenLimite != 0 && pesoLimite != 0 && rol.equals("gs"))
    		{
        		VOEstante tb = parranderos.adicionarEstante(ciudadSucursal, direccionSucursal, volumenLimite, pesoLimite, categoriaAlmacenamiento);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo crear un estante con volumen Limite: "+volumenLimite);
        		}
        		String resultado = "En adicionarEstante\n\n";
        		resultado += "Estante adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void adicionarAcuerdoCompra( )
    {
    	try 
    	{
    		long proveedor = Long.parseLong(JOptionPane.showInputDialog (this, "Código del proveedor asociado?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		long producto = Long.parseLong(JOptionPane.showInputDialog (this, "Código del producto?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		long precioCompraProducto = Long.parseLong(JOptionPane.showInputDialog (this, "¨Precio de compra acordado para el producto?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		long precioVentaProducto = Long.parseLong(JOptionPane.showInputDialog (this, "Precio al que se va a vender el producto?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		long nivelReorden = Long.parseLong(JOptionPane.showInputDialog (this, "Nivel de reorden del producto?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		if (ciudadSucursal != null && direccionSucursal != null && proveedor != 0 && producto != 0 && precioCompraProducto != 0 && precioVentaProducto != 0 && nivelReorden != 0 && rol.equals("gs"))
    		{
        		VOAcuerdoCompra tb = parranderos.adicionarAcuerdoCompra(ciudadSucursal, direccionSucursal, proveedor, producto, precioCompraProducto, precioVentaProducto, nivelReorden);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo crear el acuerdo de compra");
        		}
        		String resultado = "En adicionarAcuerdoCompra\n\n";
        		resultado += "Acuerdo de Compra adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void registrarCliente( )
    {
    	try 
    	{
    		long numDocumento = Long.parseLong(JOptionPane.showInputDialog (this, "Número de documento del cliente?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		
    		String tipoDocumento = JOptionPane.showInputDialog (this, "Tipo del documento del cliente?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String nombre = JOptionPane.showInputDialog (this, "nombre del cliente?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String correoElectronico = JOptionPane.showInputDialog (this, "Correo electronico del cliente?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String clave = JOptionPane.showInputDialog (this, "Clave del cliente?", "Ok", JOptionPane.QUESTION_MESSAGE);
    		    		
    		if (numDocumento != 0 && tipoDocumento != null && nombre != null && correoElectronico != null && clave != null && rol.equals("gs"))
    		{
        		VOClientes tb = parranderos.registrarCliente(numDocumento, tipoDocumento, nombre, correoElectronico, clave);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo crear el acuerdo de compra");
        		}
        		String resultado = "En adicionarAcuerdoCompra\n\n";
        		resultado += "Acuerdo de Compra adicionada exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void registrarClienteSucursal( )
    {
    	try 
    	{
    		long cliente = Long.parseLong(JOptionPane.showInputDialog (this, "Número de documento del cliente?", "Ok", JOptionPane.QUESTION_MESSAGE));
    		    		
    		if (cliente != 0 && ciudadSucursal != null && direccionSucursal != null && rol.equals("gs"))
    		{
        		VOClienteSucursal tb = parranderos.registrarClienteSucursal(ciudadSucursal, direccionSucursal, cliente);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo crear el acuerdo de compra");
        		}
        		String resultado = "En adicionarAcuerdoCompra\n\n";
        		resultado += "cliente registrado exitosamente en sucursal: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void iniciarSesion( )
    {
    	try 
    	{
    		long numDocumento = Long.parseLong(JOptionPane.showInputDialog (this, "Ingrése su número de documento", "Ok", JOptionPane.QUESTION_MESSAGE));
    		String clave = JOptionPane.showInputDialog (this, "Ingrese su contraseña", "Ok", JOptionPane.QUESTION_MESSAGE);
    		    		
    		if (numDocumento != 0 && clave != null)
    		{
        		Usuarios tb = parranderos.obtenerUsuario(numDocumento, clave).get(0);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo iniciar sesion");
        		}
        		String resultado = "En iniciarSesion\n\n";
        		resultado += "Sesion exitosamente iniciada: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    			ciudadSucursal = tb.getCiudadSucursal();
    			direccionSucursal = tb.getDireccionSucursal();
    			rol = tb.getRol();
    			System.out.print(rol);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void registrarUsuario( )
    {
    	try 
    	{
    		long numDocumento = Long.parseLong(JOptionPane.showInputDialog (this, "Ingrése el número de documento del usuario", "Ok", JOptionPane.QUESTION_MESSAGE));
    		String nombre = JOptionPane.showInputDialog (this, "Ingrese el nombre del usuario", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String correoElectronico = JOptionPane.showInputDialog (this, "Ingrese el correo electrónico del usuario", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String clave = JOptionPane.showInputDialog (this, "Ingrese su contraseña", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String rol1 = JOptionPane.showInputDialog (this, "Ingrese el rol del usuario", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String ciudadSucursal1 = JOptionPane.showInputDialog (this, "Ingrese la ciudad de la sucursal del usuario", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String direccionSucursal1 = JOptionPane.showInputDialog (this, "Ingrese la dirección de la sucursal del usuario", "Ok", JOptionPane.QUESTION_MESSAGE);
    		
    		if (numDocumento != 0 && clave != null && nombre != null && correoElectronico != null && rol1 != null && ciudadSucursal1 != null && direccionSucursal1 != null  && rol.equals("a"))
    		{
    			VOUsuarios tb = parranderos.registrarUsuario(numDocumento, nombre, correoElectronico, clave, rol1, ciudadSucursal1, direccionSucursal1);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo crear el acuerdo de compra");
        		}
        		String resultado = "En adicionarAcuerdoCompra\n\n";
        		resultado += "cliente registrado exitosamente en sucursal: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
    public void solicitarCarrito( )
    {
    	try 
    	{
    		long clienteCC  = Long.parseLong(JOptionPane.showInputDialog (this, "Querido usuario, por favor digite su cedula", "Ok", JOptionPane.QUESTION_MESSAGE));
    		String ciudadSucursal = JOptionPane.showInputDialog (this, "Elija la ciudad de la sucursal. Por ejemplo, Bogota", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String direccionSucursal = JOptionPane.showInputDialog (this, "Elija la direccion de la sucursal. Por ejemplo, Calle 140", "Ok", JOptionPane.QUESTION_MESSAGE);
    		long abandono = 0;
    		if (ciudadSucursal != null && direccionSucursal != null && clienteCC != 0)
    		{
        		VOCarrito tb = parranderos.solicitarCarrito(clienteCC, ciudadSucursal, direccionSucursal, abandono);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo crear el carrito para el cliente de cedula"+clienteCC);
        		}
        		String resultado = "En solicitarCarrito\n\n";
        		resultado += "Carrito solicitado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void abandonarCarrito( )
    {
    	try 
    	{
    		long clienteCC  = Long.parseLong(JOptionPane.showInputDialog (this, "Querido usuario, por favor digite su cedula", "Ok", JOptionPane.QUESTION_MESSAGE));
    		String ciudadSucursal = JOptionPane.showInputDialog (this, "Elija la ciudad de la sucursal de la cual quiere abandonar su carrito. \n Por ejemplo, Bogota", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String direccionSucursal = JOptionPane.showInputDialog (this, "Elija la direccion de la sucursalde la cual quiere abandonar su carrito. \n Por ejemplo, Calle 140", "Ok", JOptionPane.QUESTION_MESSAGE);
    		long abandono = 1;
    		if (ciudadSucursal != null && direccionSucursal != null && clienteCC != 0)
    		{
        		VOCarrito tb = parranderos.abandonarCarrito(clienteCC, ciudadSucursal, direccionSucursal, abandono);
        		if (tb == null)
        		{    		
        			throw new Exception ("No se pudo abandonar el carrito para el cliente de cedula"+clienteCC);
        		}
        		String resultado = "En abandonarCarrito\n\n";
        		resultado += "Carrito abandonado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    public void productoAlCarrito( )
    {
    	try 
    	{
    		long clienteCC  = Long.parseLong(JOptionPane.showInputDialog (this, "Querido usuario, por favor digite su cedula", "Ok", JOptionPane.QUESTION_MESSAGE));
    		String ciudadSucursal = JOptionPane.showInputDialog (this, "Elija la ciudad de la sucursal de la cual es su carrito. \n Por ejemplo, Bogota", "Ok", JOptionPane.QUESTION_MESSAGE);
    		String direccionSucursal = JOptionPane.showInputDialog (this, "Elija la direccion de la sucursalde la cual es su carrito. \n Por ejemplo, Calle 140", "Ok", JOptionPane.QUESTION_MESSAGE);
    		long producto = Long.parseLong(JOptionPane.showInputDialog (this, "Por favor digite el codigo del producto que desea adicionar al carrito. \n Por ejemplo, 169 para el cereal Zucaritas", "Ok", JOptionPane.QUESTION_MESSAGE));;
    		long cantidad = Long.parseLong(JOptionPane.showInputDialog (this, "Por favor digite la cantidad de producto que desea llevar. \n Por ejemplo, 2", "Ok", JOptionPane.QUESTION_MESSAGE));;
    		if (ciudadSucursal != null && direccionSucursal != null && clienteCC != 0)
    		{
        		VOEnDisplay tb = parranderos.productoALCarritoD(clienteCC, ciudadSucursal, direccionSucursal, producto, cantidad);
        		VOEstaEnCarrito tc = parranderos.productosAlCarritoC(clienteCC, ciudadSucursal, direccionSucursal, producto, cantidad);
        		if (tb == null || tc == null)
        		{    		
        			throw new Exception ("No se pudo abandonar el carrito para el cliente de cedula"+clienteCC);
        		}
        		String resultado = "En productoAlCarrito\n\n";
        		resultado += "Producto adicionado exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
    
    
    
    
   
	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogParranderos ()
	{
		mostrarArchivo ("parranderos.log");
	}
	
	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}
	
	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogParranderos ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("parranderos.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de parranderos ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}
	
	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			long eliminados [] = parranderos.limpiarParranderos();
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + " Gustan eliminados\n";
			resultado += eliminados [1] + " Sirven eliminados\n";
			resultado += eliminados [2] + " Visitan eliminados\n";
			resultado += eliminados [3] + " Bebidas eliminadas\n";
			resultado += eliminados [4] + " Tipos de bebida eliminados\n";
			resultado += eliminados [5] + " Bebedores eliminados\n";
			resultado += eliminados [6] + " Bares eliminados\n";
			resultado += "\nLimpieza terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	
	/**
	 * Muestra la presentación general del proyecto
	 */
	public void mostrarPresentacionGeneral ()
	{
		mostrarArchivo ("data/00-ST-ParranderosJDO.pdf");
	}
	
	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual Parranderos.pdf");
	}
	
	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD Parranderos.pdf");
	}
	
	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaParranderos.sql");
	}
	
	/**
	 * Muestra la arquitectura de referencia para Parranderos
	 */
	public void mostrarArqRef ()
	{
		mostrarArchivo ("data/ArquitecturaReferencia.pdf");
	}
	
	/**
	 * Muestra la documentación Javadoc del proyectp
	 */
	public void mostrarJavadoc ()
	{
		mostrarArchivo ("doc/index.html");
	}
	
    /**
     * Muestra la información acerca del desarrollo de esta apicación
     */
    public void acercaDe ()
    {
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: Parranderos Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Germán Bravo\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += " * Revisado por: Claudia Jiménez, Christian Ariza\n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);
    }
    

	
    private String listarSucursales (List<VOSucursal> lista) 
    {
    	String resp = "Los visitan existentes son:\n";
    	int i = 1;
        for (VOSucursal vis : lista)
        {
        	resp += i++ + ". " + vis.toString() + "\n";
        }
        return resp;
	}

    /**
     * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
     * @param e - La excepción recibida
     * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
    /**
     * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
     * Invoca al método correspondiente según el evento recibido
     * @param pEvento - El evento del usuario
     */
    @Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazSuperandes.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
    
	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz
     * @param args Arreglo de argumentos que se recibe por línea de comandos
     */
    public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazSuperandes interfaz = new InterfazSuperandes( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
