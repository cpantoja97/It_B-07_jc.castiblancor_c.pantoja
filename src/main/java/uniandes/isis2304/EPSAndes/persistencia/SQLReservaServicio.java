package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;
import java.sql.Timestamp;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.ReservaServicio;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLReservaServicio {
	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;
	
	public SQLReservaServicio(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}
	
	public long adicionarReservaServicioAfiliado(PersistenceManager pm, int numdocAf, long idServicio, long idIPS, Timestamp fechaHora) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaReservaServicio() + " (numDoc, id_Servicio, id_IPS, fechaHora) values (?, ?, ?, ?)" );
		q.setParameters(numdocAf, idServicio, idIPS, fechaHora);
		return (long) q.executeUnique();
	}
	
	public long adicionarReservaServicioCampania(PersistenceManager pm, long idServicio, long idIPS, Timestamp fechaHora, long idCampania) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaReservaServicio() + " (campania, id_Servicio, id_IPS, fechaHora) values (?, ?, ?, ?)" );
		q.setParameters(idCampania, idServicio, idIPS, fechaHora);
		return (long) q.executeUnique();
	}

	public long eliminarReservaServicioPorId(PersistenceManager pm, int numdocAf, long idServicio, long idIPS, Timestamp fechaHora) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaReservaServicio() + " WHERE numDoc = ? AND id_Servicio = ? AND id_IPS = ? AND fechaHora = ?");
		q.setParameters(numdocAf, idServicio, idIPS, fechaHora);
		return (long) q.executeUnique();  
	}

	public List<ReservaServicio> darReservaServicio(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaReservaServicio());
		q.setResultClass(ReservaServicio.class);
		return (List<ReservaServicio>) q.executeList();
	}
}
