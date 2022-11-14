package uniandes.isis2304.parranderos.persistencia;

import java.util.List;



import javax.jdo.PersistenceManager;
import javax.jdo.Query;

 class SQLBodega {
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
		public SQLBodega (PersistenciaParranderos pp)
		{
			this.pp = pp;
		}
		
		public long adicionarBodega (PersistenceManager pm, long idBodega, String nombreSucursalAsociada, String direccionSucursalAsociada, String categoriaAlmacenamiento, long capacidad) 
		{
	        Query q = pm.newQuery(SQL, "INSERT INTO " + "BODEGAS" + "	(codigo, ciudadSucursal, direccionSucursal, categoriaAlmacenamiento, capacidad) values (?, ?, ?, ?, ?)");
	        q.setParameters(idBodega, nombreSucursalAsociada, direccionSucursalAsociada, categoriaAlmacenamiento, capacidad);
	        return (long) q.executeUnique();            
		}

}
