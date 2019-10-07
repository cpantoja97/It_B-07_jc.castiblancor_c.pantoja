package uniandes.isis2304.EPSAndes.persistencia;

import java.sql.Timestamp;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.EPSAndes.negocio.Afiliado;

@SuppressWarnings({})
class SQLAfiliado {

	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;
	
	public SQLAfiliado(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}
	
	public long adicionarAfiliado (PersistenceManager pm, int numDoc, Timestamp fechaNacimiento) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + peps.darTablaAfiliados() + "(numDoc, fechaNacimiento) values (?, ?)");
        q.setParameters(numDoc, fechaNacimiento);
        return (long) q.executeUnique();            
	}
	
	public long eliminarAfiliadoPorId(PersistenceManager pm, int numDoc)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaAfiliados() + " WHERE numDoc = ?");
        q.setParameters(numDoc);
        return (long) q.executeUnique();            
	}
	
	public List<Afiliado> darAfiliados (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + peps.darTablaAfiliados());
		q.setResultClass(Afiliado.class);
		return (List<Afiliado>) q.executeList();
	}
}
