package uniandes.isis2304.EPSAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.TipoServicio;

public class SQLTipoServicio {

	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;

	public SQLTipoServicio(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}

	public long adicionarTipoServicio(PersistenceManager pm, long id, String nombre) 
	{
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaTipoServicio() + "(id, nombre) values (?, ?)");
		q.setParameters(id, nombre);
		return (long) q.executeUnique();            
	}

	public long eliminarTipoServicioPorId(PersistenceManager pm, long id)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaTipoServicio() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique();            
	}

	public List<TipoServicio> darTipoServicio(PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + peps.darTablaTipoServicio());
		q.setResultClass(TipoServicio.class);
		return (List<TipoServicio>) q.executeList();
	}
}
