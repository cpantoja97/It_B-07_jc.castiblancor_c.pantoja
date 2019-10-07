package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.MedicosAbscritos;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLMedicosAbscritos {
	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;
	
	public SQLMedicosAbscritos(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}
	
	public long adicionarMedicosAbscritos(PersistenceManager pm, int numdocMed, long id_IPS) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaMedicosAbscritos() + "(numDoc, id_IPS) values (?, ?)" );
		q.setParameters(numdocMed, id_IPS);
		return (long) q.executeUnique();
	}

	public long eliminarMedicosAbscritosPorId(PersistenceManager pm, int numdocAf, long idIPS) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaMedicosAbscritos() + " WHERE numDoc = ? AND id_IPS = ?");
		q.setParameters(numdocAf, idIPS);
		return (long) q.executeUnique();  
	}

	public List<MedicosAbscritos> darMedicosAbscritos(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaMedicosAbscritos());
		q.setResultClass(MedicosAbscritos.class);
		return (List<MedicosAbscritos>) q.executeList();
	}
}
