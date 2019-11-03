package uniandes.isis2304.EPSAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Campania;

public class SQLCampania {

	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;

	public SQLCampania(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}

	public long adicionarCampania(PersistenceManager pm, long id, String nombre, int afiliadosEseprados, Timestamp fechaInicio, Timestamp fechaFin) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaCampania() + "(id, nombre, afiliadosEsperados, fechaInicio, fechaFin) values (?, ?, ?, ?, ?)");
		q.setParameters(id, nombre, afiliadosEseprados, fechaInicio, fechaFin);
		return (long) q.executeUnique();            
	}

	public long eliminarCampaniaPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaCampania() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();            
	}

	public List<Campania> darCampania(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + peps.darTablaCampania());
		q.setResultClass(Campania.class);
		return (List<Campania>) q.executeList();
	}
}
