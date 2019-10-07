package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Medicamento;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLMedicamento {
	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;

	public SQLMedicamento(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}	
	
	public long adicionarMedicamento(PersistenceManager pm, long idMedicamento, String nombre, String descripcion) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaMedicamento() + "(id_medicamento, nombre, descripcion) values (?, ?, ?)" );
		q.setParameters(idMedicamento, nombre, descripcion);
		return (long) q.executeUnique();
	}

	public long eliminarMedicamentoPorId(PersistenceManager pm, long idMedicamento) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaMedicamento() + " WHERE id_Medicamento = ?");
		q.setParameters(idMedicamento);
		return (long) q.executeUnique();  
	}

	public List<Medicamento> darMedicamentos(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaMedicamento());
		q.setResultClass(Medicamento.class);
		return (List<Medicamento>) q.executeList();
	}
}
