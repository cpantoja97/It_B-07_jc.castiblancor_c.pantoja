package uniandes.isis2304.EPSAndes.persistencia;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.OrdenDeServicio;

@SuppressWarnings({"unchecked","rawtypes"})
class SQLOrdenDeServicio {
	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;
	
	public SQLOrdenDeServicio(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}
	
	public long adicionarOrdenDeServicio(PersistenceManager pm, int numdocMedico, int numdocAfiliado, long idServicio) {
		Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaOrdenDeServicio() + "(id_Medico, id_Afiliado, id_Servicio) values (?, ?, ?)" );
		q.setParameters(numdocMedico, numdocAfiliado, idServicio);
		return (long) q.executeUnique();
	}

	public long eliminarOrdenDeServicioPorId(PersistenceManager pm,int numdocMedico, int numdocAfiliado, long idServicio) {
		Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaOrdenDeServicio() + " WHERE id_Medico = ? AND id_Afiliado = ? AND id_Servicio = ?");
		q.setParameters(numdocMedico, numdocAfiliado, idServicio);
		return (long) q.executeUnique();  
	}

	public List<OrdenDeServicio> darOrdenes(PersistenceManager persistenceManager) {
		Query q = persistenceManager.newQuery(SQL, "SELECT * FROM " + peps.darTablaOrdenDeServicio());
		q.setResultClass(OrdenDeServicio.class);
		return (List<OrdenDeServicio>) q.executeList();
	}
}
