package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.ServiciosMedico;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLServiciosMedicos {
	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;
	
	public SQLServiciosMedicos(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}
	
	public long adicionarServiciosMedicos(PersistenceManager pm, int numdocMed, long idServicio, String especialidad) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaServiciosMedicos() + "(numDoc, id_Servicio, especialidad) values (?, ?, ?)" );
		q.setParameters(numdocMed, idServicio, especialidad);
		return (long) q.executeUnique();
	}

	public long eliminarServiciosMedicosPorId(PersistenceManager pm, int numdocAf, long idServicio) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaServiciosMedicos() + " WHERE numDoc = ? AND id_Servicio = ?");
		q.setParameters(numdocAf, idServicio);
		return (long) q.executeUnique();  
	}

	public List<ServiciosMedico> darServiciosMedicos(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaServiciosMedicos());
		q.setResultClass(ServiciosMedico.class);
		return (List<ServiciosMedico>) q.executeList();
	}
}
