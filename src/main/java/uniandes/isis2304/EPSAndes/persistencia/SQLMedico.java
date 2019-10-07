package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Medico;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLMedico {

	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;

	public SQLMedico(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}

	public long adicionarMedico(PersistenceManager pm, int numDoc, int regMedico) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaMedico() + "(numDoc, regMedico) values (?, ?)" );
		q.setParameters(numDoc, regMedico);
		return (long) q.executeUnique();
	}

	public long eliminarMedicoPorId(PersistenceManager pm, long numDoc) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaMedico() + " WHERE numDoc = ?");
		q.setParameters(numDoc);
		return (long) q.executeUnique();  
	}

	public List<Medico> darMedicos(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaMedico());
		q.setResultClass(Medico.class);
		return (List<Medico>) q.executeList();
	}

}
