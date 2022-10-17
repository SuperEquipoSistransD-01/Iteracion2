package uniandes.isis2304.parranderos.persistencia;

import uniandes.isis2304.parranderos.negocio.Usuarios;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import java.util.List;

class SQLUsuarios {
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
	public SQLUsuarios (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}
	
	public long registrarUsuario(PersistenceManager pm, long numDocumento, String nombre, String correoElectronico, String clave, String rol, String ciudadSucursal, String direccionSucursal)
	{
		Query q = pm.newQuery(SQL, "INSERT INTO Usuarios (numDocumento, nombre, correoElectronico, clave, rol, ciudadSucursal, direccionSucursal) VALUES (?,?,?,?,?,?,?)");
        q.setParameters(numDocumento, nombre, correoElectronico, clave, rol, ciudadSucursal, direccionSucursal);
        return (long) q.executeUnique();
	}
	
	public List<Usuarios> obtenerUsuario(PersistenceManager pm, long numDocumento, String clave)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM Usuarios WHERE numDocumento = ? and clave = ?");
		System.out.println("segundoPunto");
		q.setResultClass(Usuarios.class);
		q.setParameters(numDocumento, clave);
		return (List<Usuarios>) q.executeList();
	}

}
