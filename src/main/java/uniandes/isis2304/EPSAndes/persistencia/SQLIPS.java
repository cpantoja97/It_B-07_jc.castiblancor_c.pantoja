package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.IPS;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLIPS {

	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;

	public SQLIPS(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}

	public long adicionarIPS(PersistenceManager pm, long idIPS, String nombre, String localizacion) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaIPS() + "(idIPS, nombre, localizacion) values (?, ?, ?)");
		q.setParameters(idIPS, nombre, localizacion);
		return (long) q.executeUnique();
	}

	public long eliminarIPSPorId(PersistenceManager pm, long id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaIPS() + " WHERE idIPS = ?");
		q.setParameters(id);
		return (long) q.executeUnique();  
	}

	public List<IPS> darIPSs(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaIPS());
		q.setResultClass(IPS.class);
		return (List<IPS>) q.executeList();
	}

}
