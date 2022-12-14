package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ConsultaDemanda;
import uniandes.isis2304.parranderos.negocio.ConsultaFrecuentes;
import uniandes.isis2304.parranderos.negocio.Pedidos;
import uniandes.isis2304.parranderos.negocio.numProductosPedidos;

class SQLPedido {
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
	public SQLPedido (PersistenciaParranderos pp)
	{
		this.pp = pp;
	}

	public long adicionarPedido(PersistenceManager pm, long codigo, String ciudadSucursal, String direccionSucursal, long proveedor, Long producto, Timestamp fechaPedido, Long cantidad, long diasParaEntrega,long codigoConsolidado) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO Pedidos (codigo, ciudadSucursal, direccionSucursal, proveedor, producto, fechaPedido, volumenProducto, diasParaEntrega, pedidoConsolidado, diasDesdePedido, llego) VALUES (?,?,?,?,?,?,?,?,?,0,0)");
		q.setParameters(codigo, ciudadSucursal, direccionSucursal, proveedor, producto, fechaPedido, cantidad, diasParaEntrega, codigoConsolidado);
		long e = (long) q.executeUnique();
        return e;
	}

	public List<ConsultaDemanda> darConsultaDemanda(PersistenceManager pm) {
		//System.out.println("En SQLA");
		Query q = pm.newQuery(SQL, "select producto from (select producto, fechapedido, "
				+ " cast( (fechapedido - lag(fechapedido) over (order by fechapedido)) as numeric) as diff "
				+ "from (select producto, fechaPedido from pedidos order by producto, fechaPedido desc) "
				+ "order by producto, fechaPedido desc) "
				+ "where diff > 60");
		//System.out.println("En SQL1");
		q.setResultClass(ConsultaDemanda.class);
		//System.out.println("En SQL2");
		return (List<ConsultaDemanda>) q.executeList();
	}
	
	public List<ConsultaDemanda> darConsultaDemandaActual(PersistenceManager pm) {
		System.out.println("En SQLA");
		Query q = pm.newQuery(SQL, "select producto from ("
				+ "select t1.producto, t2.mxdate, current_date-t2.mxdate as diff1 "
				+ "from (select producto, fechapedido, "
				+ "       cast( (fechapedido - lag(fechapedido) over (order by fechapedido)) as numeric) as diff "
				+ "from (select producto, fechaPedido from pedidos order by producto, fechaPedido desc) "
				+ "order by producto, fechaPedido desc) t1 "
				+ "inner join "
				+ "("
				+ "  select max(fechapedido) mxdate, producto "
				+ "  from (select producto, fechapedido, "
				+ "       cast( (fechapedido - lag(fechapedido) over (order by fechapedido)) as numeric) as diff "
				+ "from (select producto, fechaPedido from pedidos order by producto, fechaPedido desc) "
				+ "order by producto, fechaPedido desc) "
				+ "  group by producto "
				+ ") t2 "
				+ "  on t1.producto = t2.producto "
				+ "  and t1.fechapedido = t2.mxdate) where diff1 > 60");
		//Query q = pm.newQuery(SQL, "select max(producto) as producto from pedidos");
		System.out.println("En SQL1");
		q.setResultClass(ConsultaDemanda.class);
		System.out.println("En SQL2");
		return (List<ConsultaDemanda>) q.executeList();
	}

	public List<numProductosPedidos> obtenerPedidosDePedidoConsolidado(PersistenceManager pm, long pedidoConsolidado) 
	{
		Query q = pm.newQuery(SQL, "SELECT producto, volumenProducto FROM Pedidos WHERE pedidoConsolidado = ?");
		q.setResultClass(numProductosPedidos.class);
		q.setParameters(pedidoConsolidado);
		return (List<numProductosPedidos>) q.executeList();
	}
	
	public long registrarLlegadaPedidoConsolidado(PersistenceManager pm, String ciudadSucursal, String direccionSucursal, long pedidoConsolidado) 
	{
		Query q = pm.newQuery(SQL, "UPDATE Pedidos SET llego = 1 WHERE ciudadSucursal = ? AND direccionSucursal = ? AND pedidoConsolidado = ? AND llego = 0");
        q.setParameters(ciudadSucursal, direccionSucursal, pedidoConsolidado);
        return (long) q.executeUnique();
	}
	

}
