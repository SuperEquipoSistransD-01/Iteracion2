package uniandes.isis2304.parranderos.persistencia;

import java.util.List;



import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import com.google.gson.JsonArray;

import uniandes.isis2304.parranderos.negocio.EstaEnCarrito;
import uniandes.isis2304.parranderos.negocio.Productos;

class SQLProducto {
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
	public SQLProducto (PersistenciaParranderos pp)	
	{
		this.pp = pp;
	}
	
	public long adicionarProducto(PersistenceManager pm, long codigo, String nombre, String marca, String presentacion, String unidadMedida,
			String tipoProducto, String categoriaAlmacenamiento, long precioUnitario, long precioUnidadMedida,
			long cantidadPresentacion, long volumenEmpaque, long pesoEmpaque) 
	{
		System.out.println("holaEstoy en sql producto");
        Query q = pm.newQuery(SQL, "INSERT INTO " + "PRODUCTOS" + "(codigo, nombre, marca, precioUnitario, presentacion, precioUnidadMedida, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, tipoProducto, categoriaAlmacenamiento)  values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        System.out.println(q);
        q.setParameters(codigo, nombre, marca, precioUnitario, presentacion, precioUnidadMedida, cantidadPresentacion, unidadMedida, volumenEmpaque, pesoEmpaque, tipoProducto, categoriaAlmacenamiento);
        return (long) q.executeUnique();
	}

	public List<Productos> obtenerProducto(PersistenceManager pm, long codigo) {
		Query q = pm.newQuery(SQL, "SELECT * FROM Productos WHERE codigo = ?");
		q.setResultClass(Productos.class);
		q.setParameters(codigo);
		return (List<Productos>) q.executeList();
	}

}
