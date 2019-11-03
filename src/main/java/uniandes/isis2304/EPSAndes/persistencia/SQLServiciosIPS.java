package uniandes.isis2304.EPSAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.ServiciosIPS;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLServiciosIPS {
	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;
	
	public SQLServiciosIPS(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}
	
	public long adicionarServiciosIPS(PersistenceManager pm, long idIPS, long idServicio, int capacidad, Timestamp horarioInicio, Timestamp horarioFin) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaServiciosIPS() + "(id_IPS, id_Servicio, capacidad, horarioInicio, horarioFin) values (?, ?, ?, ?, ?)" );
		q.setParameters(idIPS, idServicio, capacidad, horarioInicio, horarioFin);
		return (long) q.executeUnique();
	}

	public long eliminarServiciosIPSPorId(PersistenceManager pm, long idIPS, long idServicio) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaServiciosIPS() + " WHERE id_IPS = ? AND id_Servicio = ?");
		q.setParameters(idIPS, idServicio);
		return (long) q.executeUnique();  
	}

	public List<ServiciosIPS> darServiciosIPS(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaServiciosIPS());
		q.setResultClass(ServiciosIPS.class);
		return (List<ServiciosIPS>) q.executeList();
	}
	
	public List<ServiciosIPS> buscarServicioIPS(PersistenceManager pm, long idIPS, long idServicio){
		Query q = pm.newQuery(SQL, "SELECT * FROM " + peps.darTablaServiciosIPS()+ " WHERE id_IPS = ? AND id_Servicio = ?");
		q.setParameters(idIPS, idServicio);
		q.setResultClass(ServiciosIPS.class);
		return (List<ServiciosIPS>) q.executeList();
	}
}
