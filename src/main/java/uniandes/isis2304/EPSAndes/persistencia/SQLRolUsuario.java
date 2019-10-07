package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.RolUsuario;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLRolUsuario {
	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;

	public SQLRolUsuario(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}

	public long adicionarRolUsuario(PersistenceManager pm, long idRol, String nombre) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaRolUsuario() + "(id_Rol, nombre) values (?, ?)");
		q.setParameters(idRol, nombre);
		return (long) q.executeUnique();
	}

	public long eliminarRolUsuarioPorId(PersistenceManager pm, long id) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaRolUsuario() + " WHERE id_Rol = ?");
		q.setParameters(id);
		return (long) q.executeUnique();  
	}

	public List<RolUsuario> darRolUsuario(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaRolUsuario());
		q.setResultClass(RolUsuario.class);
		return (List<RolUsuario>) q.executeList();
	}

}
