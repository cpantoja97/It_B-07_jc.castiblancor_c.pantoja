package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.TipoDocumento;
import uniandes.isis2304.EPSAndes.negocio.Usuario;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLUsuario {

	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;

	public SQLUsuario(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}

	public long adicionarUsuario(PersistenceManager pm, int numDoc, TipoDocumento tipoDoc, String nombre, String correo,
			long idRol) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaUsuario() + "(numDoc, tipoDoc, nombre, correo, rol) values (?, ?, ?, ?, ?)");
		q.setParameters(numDoc, tipoDoc.name(), nombre, correo, idRol);
		return (long) q.executeUnique();  
	}

	public long eliminarUsuarioPorId(PersistenceManager pm, long numDoc) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaUsuario() + " WHERE numDoc = ?");
		q.setParameters(numDoc);
		return (long) q.executeUnique(); 
	}

	public List<Usuario> darUsuarios(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaUsuario());
		q.setResultClass(Usuario.class);
		List<Usuario> result = (List<Usuario>) q.executeList();
		return result;
	}



}
