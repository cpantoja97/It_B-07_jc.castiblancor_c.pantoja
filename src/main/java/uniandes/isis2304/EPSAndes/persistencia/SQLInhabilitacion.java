package uniandes.isis2304.EPSAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Inhabilitacion;

public class SQLInhabilitacion {


	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;

	public SQLInhabilitacion(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}

	public long adicionarInhabilitacion(PersistenceManager pm, Timestamp fechaInicio, Timestamp fechaFin, Long IPS, Long Servicio) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaInhabilitacion() + "(fechaInicio, fechaFin, IPS, Servicio) values (?, ?, ?, ?)");
		q.setParameters(fechaInicio, fechaFin, IPS, Servicio);
		return (long) q.executeUnique();
	}

	public long eliminarInhabilitacion(PersistenceManager pm,Timestamp fechaInicio, Long IPS, Long Servicio)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaInhabilitacion() + " WHERE fechaInicio = ? AND IPS = ? AND Servicio = ?");
		q.setParameters(fechaInicio, IPS, Servicio);
		return (long) q.executeUnique();
	}

	public long updateFechaFinInhabilitacion(PersistenceManager pm, Timestamp nuevaFechaFin, Timestamp fechaInicio, Long IPS, Long Servicio) {
		Query q = pm.newQuery(SQL, "UPDATE " + peps.darTablaInhabilitacion() + " set fechaFin = ? WHERE fechaInicio = ? AND IPS = ? AND Servicio = ?");
		q.setParameters(nuevaFechaFin,fechaInicio, IPS, Servicio);
		return (long) q.executeUnique();
	}

	public List<Inhabilitacion> darInhabilitacion(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + peps.darTablaInhabilitacion());
		q.setResultClass(Inhabilitacion.class);
		return (List<Inhabilitacion>) q.executeList();
	}
	
	public List<Inhabilitacion> darInhabilitacionesServicio(PersistenceManager pm, Long IPS, Long Servicio, Timestamp fechaMin, Timestamp fechaMax)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + peps.darTablaInhabilitacion()+ " WHERE IPS = ? AND Servicio = ? AND fechainicio <= ? AND fechafin >= ?");
		q.setParameters(IPS, Servicio, fechaMin, fechaMax);
		q.setResultClass(Inhabilitacion.class);
		return (List<Inhabilitacion>) q.executeList();
	}

}
