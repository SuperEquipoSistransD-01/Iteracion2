package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;




import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Productos;

class SQLPromociones {
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
	public SQLPromociones (PersistenciaParranderos pp)	
	{
		this.pp = pp;
	}
	
	public long adicionarPromociones(PersistenceManager pm, String nombre, Timestamp fechaInicio, long diasDuracion, String descripcion, String tipo,  long finalizada, String ciudadSucursal, String direccionSucursal, long producto) 
	{
		System.out.println("holaEstoy en sql producto");
        Query q = pm.newQuery(SQL, "INSERT INTO " + "PROMOCIONES" + "(nombre, fechainicio, diasduracion, descripcion, tipo, finalizada, ciudadSucursal, direccionsucursal, producto)  values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        System.out.println(q);
        q.setParameters(nombre, fechaInicio, diasDuracion, descripcion, tipo, finalizada, ciudadSucursal, direccionSucursal, producto);
        return (long) q.executeUnique();
	}

}
