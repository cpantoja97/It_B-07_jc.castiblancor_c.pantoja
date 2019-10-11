package uniandes.isis2304.EPSAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.PrestacionServicio;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLPrestacionServicio {
	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;
	
	public SQLPrestacionServicio(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}
	
	public long adicionarPrestacionServicio(PersistenceManager pm, int numdocAf, long idServicio, long idIPS, Timestamp fechaHora, long id_Recepcionista) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaPrestacionServicio() + "(numDoc, id_Servicio, id_IPS, fechaHora, id_Recepcionista) values (?, ?, ?, ?, ?)" );
		q.setParameters(numdocAf, idServicio, idIPS, fechaHora, id_Recepcionista);
		return (long) q.executeUnique();
	}

	public long eliminarPrestacionServicioPorId(PersistenceManager pm, int numdocAf, long idServicio, long idIPS, Timestamp fechaHora) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaPrestacionServicio() + " WHERE numDoc = ? AND id_Servicio = ? AND id_IPS = ? AND fechaHora = ?");
		q.setParameters(numdocAf, idServicio, idIPS, fechaHora);
		return (long) q.executeUnique();  
	}

	public List<PrestacionServicio> darPrestacionServicio(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaPrestacionServicio());
		q.setResultClass(PrestacionServicio.class);
		return (List<PrestacionServicio>) q.executeList();
	}
}
