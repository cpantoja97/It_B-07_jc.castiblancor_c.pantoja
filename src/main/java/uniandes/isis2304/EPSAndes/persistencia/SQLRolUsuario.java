package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import org.apache.log4j.Logger;

import uniandes.isis2304.EPSAndes.negocio.RolUsuario;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLRolUsuario {
	private final static String SQL = PersistenciaEPSAndes.SQL;
	
	private static Logger log = Logger.getLogger(SQLRolUsuario.class.getName());

	private PersistenciaEPSAndes peps;

	public SQLRolUsuario(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}

	public long adicionarRolUsuario(PersistenceManager pm, long idRol, String nombre) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaRolUsuario() + "(ID_ROL,NOMBRE) values (?, ?)");
		q.setParameters(idRol, nombre);
		return (long) q.executeUnique();
	}

	public long eliminarRolUsuarioPorId(PersistenceManager pm, long id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaRolUsuario() + " WHERE ID_ROL = ?");
		q.setParameters(id);
		return (long) q.executeUnique();  
	}

	public List<RolUsuario> darRolUsuario(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaRolUsuario());
		q.setResultClass(RolUsuario.class);
		log.info ("persistence manager " + q.executeList()); 
		return (List<RolUsuario>) q.executeList();
	}

}
