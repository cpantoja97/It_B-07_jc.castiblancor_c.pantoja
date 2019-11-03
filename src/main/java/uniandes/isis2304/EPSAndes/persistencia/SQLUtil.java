package uniandes.isis2304.EPSAndes.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.EPSAndes.negocio.RolUsuario;

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

		Query qRol= pm.newQuery(SQL, "DELETE FROM " + peps.darTablaRolUsuario());
		Query qIPS = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaIPS()); 
		Query qServicio = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaServicio());     
		Query qUsuario = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaUsuario()); 
		Query qMedico = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaMedico()); 
		Query qAfiliado = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaAfiliados()); 
		Query qRecepcionista = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaRecepcionistas()); 
		Query qServiciosIPS = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaServiciosIPS()); 
		Query qMedicosAbs = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaMedicosAbscritos()); 
		Query qServiciosMedicos = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaServiciosMedicos()); 
		Query qReceta = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaReceta()); 
		Query qMedicamentos = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaMedicamento()); 
		Query qItemR = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaItemReceta());
		Query qOrden = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaOrdenDeServicio());
		Query qReserva = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaReservaServicio());
		Query qPrestacion = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaPrestacionServicio());
		Query qTipo = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaTipoServicio());
		Query qCampania = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaCampania());
		Query qInhabilitacion = pm.newQuery(SQL, "DELETE FROM " + peps.darTablaInhabilitacion());

		long afiliadosEliminados = (long) qRol.executeUnique ();
		long recepEliminados = (long) qIPS.executeUnique ();
		long ipsEliminados = (long) qServicio.executeUnique ();
		long servEliminados = (long) qUsuario.executeUnique ();
		long usuariosEliminados = (long) qMedico.executeUnique ();
		long medicosEliminados = (long) qAfiliado.executeUnique ();
		long rolUEliminados = (long) qRecepcionista.executeUnique ();
		long servIPSEliminados = (long) qServiciosIPS.executeUnique ();
		long medAbsEliminados = (long) qMedicosAbs.executeUnique ();
		long servMedsEliminados = (long) qServiciosMedicos.executeUnique ();
		long recetasEliminados = (long) qReceta.executeUnique ();
		long medicamEliminados = (long) qMedicamentos.executeUnique ();
		long itemREliminados = (long) qItemR.executeUnique ();
		long ordenesEliminados = (long) qOrden.executeUnique ();
		long reservasEliminados = (long) qReserva.executeUnique ();
		long prestacionesEliminados = (long) qPrestacion.executeUnique ();
		long tiposEliminados = (long) qTipo.executeUnique ();
		long campaniaEliminados = (long) qCampania.executeUnique ();
		long inhabilitacionesEliminados = (long) qInhabilitacion.executeUnique ();

		return new long[] {afiliadosEliminados, recepEliminados,ipsEliminados, servEliminados,usuariosEliminados,medicosEliminados,
				rolUEliminados,servIPSEliminados,medAbsEliminados,medAbsEliminados,servMedsEliminados,recetasEliminados, medicamEliminados,
				itemREliminados,ordenesEliminados,reservasEliminados, prestacionesEliminados, tiposEliminados, campaniaEliminados, inhabilitacionesEliminados};
	}

	public List<RolUsuario> darRolPorNumDoc( PersistenceManager pm, int id) {
		Query q = pm.newQuery(SQL, "SELECT R.id_rol, R.nombre FROM " + peps.darTablaRolUsuario() + " R, "+peps.darTablaUsuario()+" U WHERE R.id_rol = U.id_rol and U.numdoc = ?");
		q.setResultClass(RolUsuario.class);
		q.setParameters(id);
		return (List<RolUsuario>) q.executeList();
	}
}
