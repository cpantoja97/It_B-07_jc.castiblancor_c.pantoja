package uniandes.isis2304.EPSAndes.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

@SuppressWarnings("rawtypes")
class SQLUtil {

	private final static String SQL = PersistenciaEPSAndes.SQL;

	private PersistenciaEPSAndes peps;

	public SQLUtil(PersistenciaEPSAndes persistenciaEPSAndes) {
		this.peps = persistenciaEPSAndes;
	}

	public long nextval(PersistenceManager pm) {
		Query q = pm.newQuery(SQL, "SELECT "+ peps.darSeqEPSAndes () + ".nextval FROM DUAL");
		q.setResultClass(Long.class);
		long resp = (long) q.executeUnique();
		return resp;
	}

	public long [] limpiarEPSAndes (PersistenceManager pm)
	{
		Query qAfiliado = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaAfiliados ());
		Query qRecep = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaRecepcionistas()); 
		Query qIPS = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaIPS());     
		Query qServicios = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaServicio()); 
		Query qUsuarios = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaUsuario());     
		Query qMedicos = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaMedico()); 
		Query qRolU = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaRolUsuario()); 
		Query qServIPS = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaServiciosIPS()); 
		Query qMedAbs = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaMedicosAbscritos()); 
		Query qServMeds = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaServiciosMedicos()); 
		Query qRecetas = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaReceta()); 
		Query qMedicam = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaMedicamento()); 
		Query qItemR = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaItemReceta());
		Query qOrdenes = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaOrdenDeServicio());
		Query qReservas = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaReservaServicio());
		Query qPrestaciones = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaPrestacionServicio());

		long afiliadosEliminados = (long) qAfiliado.executeUnique ();
		long recepEliminados = (long) qRecep.executeUnique ();
		long ipsEliminados = (long) qIPS.executeUnique ();
		long servEliminados = (long) qServicios.executeUnique ();
		long usuariosEliminados = (long) qUsuarios.executeUnique ();
		long medicosEliminados = (long) qMedicos.executeUnique ();
		long rolUEliminados = (long) qRolU.executeUnique ();
		long servIPSEliminados = (long) qServIPS.executeUnique ();
		long medAbsEliminados = (long) qMedAbs.executeUnique ();
		long servMedsEliminados = (long) qServMeds.executeUnique ();
		long recetasEliminados = (long) qRecetas.executeUnique ();
		long medicamEliminados = (long) qMedicam.executeUnique ();
		long itemREliminados = (long) qItemR.executeUnique ();
		long ordenesEliminados = (long) qOrdenes.executeUnique ();
		long reservasEliminados = (long) qReservas.executeUnique ();
		long prestacionesEliminados = (long) qPrestaciones.executeUnique ();

		return new long[] {afiliadosEliminados, recepEliminados,ipsEliminados, servEliminados,usuariosEliminados,medicosEliminados,
				rolUEliminados,servIPSEliminados,medAbsEliminados,medAbsEliminados,servMedsEliminados,recetasEliminados, medicamEliminados,
				itemREliminados,ordenesEliminados,reservasEliminados, prestacionesEliminados};
	}

}
