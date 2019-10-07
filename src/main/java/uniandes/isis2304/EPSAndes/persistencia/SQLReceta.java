package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Receta;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLReceta {
	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;
	
	public SQLReceta(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}
	
	public long adicionarReceta(PersistenceManager pm, long idReceta, int numdocMed, int numdocAf) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaReceta() + "(id_Receta, id_Medico, id_Afiliado) values (?, ?, ?)" );
		q.setParameters(idReceta, numdocMed, numdocAf);
		return (long) q.executeUnique();
	}

	public long eliminarRecetaPorId(PersistenceManager pm, long idReceta) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaReceta() + " WHERE id_Receta = ?");
		q.setParameters(idReceta);
		return (long) q.executeUnique();  
	}

	public List<Receta> darRecetas(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaReceta());
		q.setResultClass(Receta.class);
		return (List<Receta>) q.executeList();
	}
}
