package uniandes.isis2304.EPSAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.ItemReceta;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLItemsReceta {
	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;
	
	public SQLItemsReceta(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}
	
	public long adicionarItemReceta(PersistenceManager pm, long idReceta, long idMedicamento, int cantidad, String indicaciones) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaItemReceta() + "(id_receta, id_medicamento, cantidad, indicaciones) values (?, ?, ?, ?)" );
		q.setParameters(idReceta, idMedicamento, cantidad, indicaciones);
		return (long) q.executeUnique();
	}

	public long eliminarItemRecetaPorId(PersistenceManager pm, long idReceta, long idMedicamento) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaItemReceta() + " WHERE id_receta = ? AND id_medicamento = ?");
		q.setParameters(idReceta, idMedicamento);
		return (long) q.executeUnique();  
	}

	public List<ItemReceta> darItemsReceta(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaItemReceta());
		q.setResultClass(ItemReceta.class);
		return (List<ItemReceta>) q.executeList();
	}
	
}
