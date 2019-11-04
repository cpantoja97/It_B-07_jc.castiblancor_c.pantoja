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

	public long eliminarReservaServicioPorId(PersistenceManager pm, long idServicio, long idIPS, Timestamp fechaHora) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaReservaServicio() + " WHERE id_Servicio = ? AND id_IPS = ? AND fechaHora = ?");
		q.setParameters( idServicio, idIPS, fechaHora);
		return (long) q.executeUnique();  
	}

	public List<ReservaServicio> darReservaServicio(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaReservaServicio());
		q.setResultClass(ReservaServicio.class);
		return (List<ReservaServicio>) q.executeList();
	}
	
	public List<ReservaServicio> darReservasDia(PersistenceManager pm, long idServicio, long idIPS, Timestamp fechaInicio){
		Timestamp fechaFin = new Timestamp( fechaInicio.getTime() + 86399999);
		Query q = pm.newQuery(SQL, "SELECT NVL(numdoc, -1) AS NUMDOC, id_servicio, id_IPS, fechaHora FROM " + peps.darTablaReservaServicio()+ " WHERE id_Servicio = ? AND id_IPS = ? AND fechaHora >= ? AND fechaHora <= ?" );
		q.setParameters( idServicio, idIPS, fechaInicio, fechaFin);
		q.setResultClass(ReservaServicio.class);
		return (List<ReservaServicio>) q.executeList();
		
	}
	
	public long eliminarReservasCampaniaPorServicio(PersistenceManager pm, long idServicio, long idCampania) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaReservaServicio() + " WHERE id_Servicio = ? AND campania = ?");
		q.setParameters(idServicio,idCampania);
		return (long) q.executeUnique();  
	}
	
	public List<ReservaServicio> darReservasParaCambiar(PersistenceManager pm, long idServicio, long idIPS, Timestamp fechaInicio, Timestamp fechaFin){
		Query q = pm.newQuery(SQL, "SELECT NVL(numdoc, -1) AS NUMDOC, ID_SERVICIO, id_IPS, fechaHora, NVL(campania, -1) AS CAMPANIA FROM " + peps.darTablaReservaServicio()+ " WHERE id_Servicio = ? AND id_IPS = ? AND fechaHora BETWEEN ? AND ?");
		q.setParameters( idServicio, idIPS, fechaInicio, fechaFin);
		q.setResultClass(ReservaServicio.class);
		return (List<ReservaServicio>) q.executeList();	
	}
	
	public long cambiarReserva(PersistenceManager pm, long idServicio, long idIPS, Timestamp fechaInicio, long nuevoIPS, Timestamp nuevaFecha){
		Query q = pm.newQuery(SQL, "UPDATE " + peps.darTablaReservaServicio()+ " SET id_IPS=?, fechaHora=?  WHERE id_Servicio = ? AND id_IPS = ? AND fechaHora = ?" );
		q.setParameters( nuevoIPS, nuevaFecha, idServicio, idIPS, fechaInicio);
		q.setResultClass(ReservaServicio.class);
		return (long) q.executeUnique();
		
	}

}
