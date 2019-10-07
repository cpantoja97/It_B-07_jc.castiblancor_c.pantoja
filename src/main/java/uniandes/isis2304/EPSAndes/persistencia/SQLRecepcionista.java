package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.Recepcionista;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLRecepcionista {

	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;

	public SQLRecepcionista(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}

	public long adicionarRecepcionsita(PersistenceManager pm, int numDoc, long idIPS) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaRecepcionistas() + "(numDoc, idIPS) values (?, ?)");
		q.setParameters(numDoc, idIPS);
		return (long) q.executeUnique();
	}

	public long eliminarRecepcionistaPorId(PersistenceManager pm, int numDoc)
	{
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaRecepcionistas() + " WHERE numDoc = ?");
		q.setParameters(numDoc);
		return (long) q.executeUnique();            
	}

	public List<Recepcionista> darRecepcionistas (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + peps.darTablaRecepcionistas());
		q.setResultClass(Recepcionista.class);
		return (List<Recepcionista>) q.executeList();
	}
}
