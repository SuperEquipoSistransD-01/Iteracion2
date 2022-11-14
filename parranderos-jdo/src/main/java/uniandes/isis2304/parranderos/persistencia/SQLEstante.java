package uniandes.isis2304.parranderos.persistencia;

import java.util.List;


import javax.jdo.PersistenceManager;
import javax.jdo.Query;

 class SQLEstante {
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
		public SQLEstante (PersistenciaParranderos pp)
		{
			this.pp = pp;
		}
		
		public long adicionarEstante (PersistenceManager pm, long idEstante, String nombreSucursalAsociada, String direccionSucursalAsociada, String tipoProducto, long capacidad) 
		{
			System.out.println("Segundo Punto");
	        Query q = pm.newQuery(SQL, "INSERT INTO " + "ESTANTES" + "	(codigo, ciudadSucursal, direccionSucursal, tipoProducto, capacidad) values (?, ?, ?, ?, ?)");
	        q.setParameters(idEstante, nombreSucursalAsociada, direccionSucursalAsociada, tipoProducto, capacidad);
	        return (long) q.executeUnique();            
		}

}
