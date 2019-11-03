package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Servicio;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLServicio {

	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;

	public SQLServicio(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}

	public long adicionarServicio(PersistenceManager pm, long idServicio, String nombre, long tipo) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaServicio() + "(idServicio, nombre) values (?, ?, ?)");
		q.setParameters(idServicio, nombre, tipo);
		return (long) q.executeUnique();  
	}

	public long eliminarServicioPorId(PersistenceManager pm, long id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaServicio() + " WHERE id = ?");
		q.setParameters(id);
		return (long) q.executeUnique(); 
	}

	public List<Servicio> darServicios(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaServicio());
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}
	
	public Servicio buscarServicioPorID(PersistenceManager pm, long id) {
		Query q = pm.newQuery(SQL, "SELECT * FROM " + peps.darTablaServicio() + " WHERE id_servicio = ?" );
		q.setParameters(id);
		q.setResultClass(Servicio.class);
		return (Servicio) q.executeUnique();
	}

}
