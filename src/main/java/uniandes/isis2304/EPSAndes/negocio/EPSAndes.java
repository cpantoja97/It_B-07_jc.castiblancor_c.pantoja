/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

import uniandes.isis2304.EPSAndes.persistencia.PersistenciaEPSAndes;

/**
 * Clase principal del negocio
 * Sarisface todos los requerimientos funcionales del negocio
 *
 * @author Germán Bravo
 */
public class EPSAndes 
{
	private static Logger log = Logger.getLogger(EPSAndes.class.getName());

	private PersistenciaEPSAndes peps;

	public EPSAndes ()
	{
		peps = PersistenciaEPSAndes.getInstance ();
	}

	public EPSAndes(JsonObject tableConfig) {
		peps = PersistenciaEPSAndes.getInstance (tableConfig);
	}

	public void cerrarUnidadPersistencia ()
	{
		peps.cerrarUnidadPersistencia ();
	}


	/* ****************************************************************
	 * 			Métodos para manejar los AFILIADOS
	 *****************************************************************/

	public List<Afiliado> darAfiliados ()
	{
		log.info ("Consultando Afiliados");
		List<Afiliado> afiliados = peps.darAfiliados();	
		log.info ("Consultando Afiliados: " + afiliados.size() + " existentes");
		return afiliados;
	}

	public List<VOAfiliado> darVOAfiliado ()
	{
		log.info ("Generando los VO de Afiliado");        
		List<VOAfiliado> voAfiliados = new LinkedList<VOAfiliado> ();
		for (Afiliado a : peps.darAfiliados())
		{
			voAfiliados.add (a);
		}
		log.info ("Generando los VO de Afiliados: " + voAfiliados.size() + " existentes");
		return voAfiliados;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los RECEPCIONISTAS
	 *****************************************************************/

	public List<Recepcionista> darRecepcionistas ()
	{
		log.info ("Consultando Recepcionistas");
		List<Recepcionista> recepcionista = peps.darRecepcionistas();	
		log.info ("Consultando Recepcionistas: " + recepcionista.size() + " existentes");
		return recepcionista;
	}

	public List<VORecepcionista> darVORecepcionista()
	{
		log.info ("Generando los VO de Recepcionista");        
		List<VORecepcionista> voRecepcionista= new LinkedList<VORecepcionista> ();
		for (Recepcionista r : peps.darRecepcionistas())
		{
			voRecepcionista.add (r);
		}
		log.info ("Generando los VO de Recepcionista: " + voRecepcionista.size() + " existentes");
		return voRecepcionista;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las IPS
	 *****************************************************************/

	public List<IPS> darIPS ()
	{
		log.info ("Consultando IPSs");
		List<IPS> ips = peps.darIPSs();	
		log.info ("Consultando IPSs: " + ips.size() + " existentes");
		return ips;
	}

	public List<VOIPS> darVOIPS()
	{
		log.info ("Generando los VO de IPS");        
		List<VOIPS> voIPS= new LinkedList<VOIPS> ();
		for (IPS ips : peps.darIPSs())
		{
			voIPS.add (ips);
		}
		log.info ("Generando los VO de IPS: " + voIPS.size() + " existentes");
		return voIPS;
	}

	/* ****************************************************************
	 * 			Métodos para manejar las SERVICIOS
	 *****************************************************************/

	public List<Servicio> darServicios()
	{
		log.info ("Consultando Servicios");
		List<Servicio> servicios = peps.darServicios();	
		log.info ("Consultando Servicios: " + servicios.size() + " existentes");
		return servicios;
	}

	public List<VOServicio> darVOServicio(){
		log.info ("Generando los VO de Servicios");        
		List<VOServicio> voServicio= new LinkedList<VOServicio> ();
		for (Servicio s: peps.darServicios())
		{
			voServicio.add (s);
		}
		log.info ("Generando los VO de Servicio: " + voServicio.size() + " existentes");
		return voServicio;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los USUARIOS
	 *****************************************************************/

	public List<Usuario> darUsuario()
	{
		log.info ("Consultando Usuarios");
		List<Usuario> usuarios= peps.darUsuarios();	
		log.info ("Consultando Usuarios: " + usuarios.size() + " existentes");
		return usuarios;
	}

	public List<VOUsuario> darVOUsuario(){
		log.info ("Generando los VO de Usuarios");        
		List<VOUsuario> voUsuario= new LinkedList<VOUsuario> ();
		for (Usuario s: peps.darUsuarios())
		{
			voUsuario.add (s);
		}
		log.info ("Generando los VO de Usuario: " + voUsuario.size() + " existentes");
		return voUsuario;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los MEDICOS
	 *****************************************************************/

	public List<Medico> darMedicos()
	{
		log.info ("Consultando Medicos");
		List<Medico> medico= peps.darMedicos();	
		log.info ("Consultando Medicos: " + medico.size() + " existentes");
		return medico;
	}

	public List<VOMedico> darVOMedico(){
		log.info ("Generando los VO de Medicos");        
		List<VOMedico> voMedico= new LinkedList<VOMedico> ();
		for (Medico m: peps.darMedicos())
		{
			voMedico.add (m);
		}
		log.info ("Generando los VO de Medico: " + voMedico.size() + " existentes");
		return voMedico;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación ROLUSUARIO
	 *****************************************************************/

	public List<RolUsuario> darRoles()
	{
		log.info ("Consultando roles");
		List<RolUsuario> rol= peps.darRolUsuario();	
		log.info ("Consultando roles: " + rol.size() + " existentes");
		return rol;
	}

	public List<VORolUsuario> darVORoles(){
		log.info ("Generando los VO de rol usuario");        
		List<VORolUsuario> voRol= new LinkedList<VORolUsuario> ();
		for (RolUsuario r: peps.darRolUsuario())
		{
			voRol.add (r);
		}
		log.info ("Generando los VO de Rol Usuario: " + voRol.size() + " existentes");
		return voRol;
	}
	public RolUsuario buscarRolPorNombre( String pnombre) {
		/*List<RolUsuario> roles = darRoles();
		RolUsuario resp = null;
		for(int i = 0; i < roles.size(); i++) {
			RolUsuario act = roles.get(i);
			if(act.getNombre().equals(pnombre)) {
				resp = act;
				break;
			}
		}
		return resp;*/
		log.info ("Buscando Tipo de bebida por nombre: " + pnombre);
		List<RolUsuario> ru = peps.darRolPorNombre (pnombre);
		return !ru.isEmpty () ? ru.get (0) : null;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación SERVICIOSIPS
	 *****************************************************************/

	public List<ServiciosIPS> darServiciosIPS()
	{
		log.info ("Consultando ServiciosIPS");
		List<ServiciosIPS> sips= peps.darServiciosIPS();	
		log.info ("Consultando ServiciosIPS: " + sips.size() + " existentes");
		return sips;
	}

	public List<VOServiciosIPS> darVOServiciosIPS(){
		log.info ("Generando los VO de ServiciosIPS");        
		List<VOServiciosIPS> voServiciosIPS= new LinkedList<VOServiciosIPS> ();
		for (ServiciosIPS sips: peps.darServiciosIPS())
		{
			voServiciosIPS.add (sips);
		}
		log.info ("Generando los VO de ServiciosIPS: " + voServiciosIPS.size() + " existentes");
		return voServiciosIPS;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación MEDICOSABSCRITOS
	 *****************************************************************/

	public List<MedicosAbscritos> darMedicosAbscritos()
	{
		log.info ("Consultando MedicosAbscritos");
		List<MedicosAbscritos> medico= peps.darMedicosAbscritos();	
		log.info ("Consultando MedicosAbscritos: " + medico.size() + " existentes");
		return medico;
	}

	public List<VOMedicosAbscritos> darVOMedicosAbscritos(){
		log.info ("Generando los VO de MedicosAbscritos");        
		List<VOMedicosAbscritos> voMedico= new LinkedList<VOMedicosAbscritos> ();
		for (MedicosAbscritos m: peps.darMedicosAbscritos())
		{
			voMedico.add (m);
		}
		log.info ("Generando los VO de MedicosAbscritos: " + voMedico.size() + " existentes");
		return voMedico;
	}


	/* ****************************************************************
	 * 			Métodos para manejar la relación SERVICIOSMEDICOS
	 *****************************************************************/

	public List<ServiciosMedico> darServiciosMedicos()
	{
		log.info ("Consultando ServiciosMedicos");
		List<ServiciosMedico> sMed= peps.darServiciosMedicos();	
		log.info ("Consultando ServiciosMedicos: " + sMed.size() + " existentes");
		return sMed;
	}

	public List<VOServiciosMedico> darVOServiciosMedico(){
		log.info ("Generando los VO de ServiciosMedico");        
		List<VOServiciosMedico> voMedico= new LinkedList<VOServiciosMedico> ();
		for (ServiciosMedico m: peps.darServiciosMedicos())
		{
			voMedico.add (m);
		}
		log.info ("Generando los VO de ServiciosMedico: " + voMedico.size() + " existentes");
		return voMedico;
	}

	// TODO desde aqui falta eliminar y get.
	/* ****************************************************************
	 * 			Métodos para manejar la relación RECETA
	 *****************************************************************/

	public Receta adicionarReceta(int numdocMed, int numdocAf) 
	{
		log.info ("Adicionando Receta: " + numdocMed);
		Receta receta= peps.adicionarReceta(numdocMed, numdocAf);	
		log.info ("Adicionando Receta: " + receta);
		return receta;
	}

	public long eliminarRecetaPorId(long idReceta) 
	{
		return (long)0;
	}

	public List<Receta> darReceta()
	{
		return null;
	}

	public List<VOReceta> darVOReceta(){
		return null;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación Medicamento
	 *****************************************************************/

	public List<Medicamento> darMedicamento()
	{
		return null;
	}

	public List<VOMedicamento> darVOMedicamento(){
		return null;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación ITEMSRECETA
	 *****************************************************************/

	public ItemReceta adicionarItemsReceta(long idReceta, long idMedicamento, int cantidad, String indicaciones) {
		log.info ("Adicionando ItemReceta: idReceta-" + idReceta + ", idMedicamento- "+ idMedicamento);
		ItemReceta ir= peps.adicionarItemsReceta(idReceta, idMedicamento, cantidad, indicaciones);	
		log.info ("Adicionando ItemReceta: " + ir);
		return ir;
	}

	public long eliminarItemRecetaPorId(long idReceta, long idMedicamento) 
	{
		return (long)0;
	}

	public List<ItemReceta> darItemReceta()
	{
		return null;
	}

	public List<VOItemReceta> darVOItemReceta(){
		return null;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación ORDENDESERVICIO
	 *****************************************************************/

	public OrdenDeServicio adicionarOrdenDeServicio(int numdocMedico, int numdocAfiliado, long idServicio)  {
		log.info ("Adicionando OrdenDeServicio: numdocMedico-" + numdocMedico + ", numdocAfiliado- "+ numdocAfiliado);
		OrdenDeServicio os= peps.adicionarOrdenDeServicio(numdocMedico, numdocAfiliado, idServicio);	
		log.info ("Adicionando OrdenDeServicio: " + os);
		return os;
	}

	public long eliminarOrdenDeServicioPorId(int numdocMedico, int numdocAfiliado, long idServicio) 
	{
		return (long)0;
	}

	public List<OrdenDeServicio> darOrdenDeServicio()
	{
		log.info("Consultando Ordenes de Servicio");
		List<OrdenDeServicio> ordenesServicio = peps.darOrdenes();
		log.info("Consultando Ordenes de Servicio: " + ordenesServicio.size() + " existentes");
		return ordenesServicio;
	}

	public List<VOOrdenDeServicio> darVOOrdenDeServicio(){
		log.info("Generando los VO de Orden De Servicio");
		List<VOOrdenDeServicio> voOrdenes = new LinkedList<VOOrdenDeServicio>();
		for(VOOrdenDeServicio item : peps.darOrdenes()) {
			voOrdenes.add(item);
		}
		log.info("Generando los VO de Orden De Servicio: " + voOrdenes.size() + " existentes");
		return voOrdenes;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación RESERVASERVICIO
	 *****************************************************************/

	public ReservaServicio adicionarReservaServicioAfiliado( int numdocAf, long idServicio, long idIPS, Timestamp fechaHora) throws Exception  {
		log.info ("Adicionando ReservaServicio: numdocAf-" + numdocAf + ", idServicio- "+ idServicio);
		ReservaServicio rs= peps.adicionarReservaServicioAfiliado(numdocAf, idServicio, idIPS, fechaHora);	
		log.info ("Adicionando ReservaServicio: " + rs);
		return rs;
	}

	public long eliminarReservaServicioPorId(int numdocAf, long idServicio, long idIPS, Timestamp fechaHora) 
	{
		return (long)0;
	}

	public List<ReservaServicio> darReservaServicio()
	{
		log.info("Consultando Reservas de Servicios");
		List<ReservaServicio> reservas = peps.darReservaServicio();
		log.info("Consultando Reservas de Servicios: " + reservas.size() + " existentes");
		return reservas;
	}

	public List<VOReservaServicio> darVOReservaServicio(){
		log.info("Generando los VO de Reservas de Servicios");
		List<VOReservaServicio> voReservas = new LinkedList<VOReservaServicio>();
		for(VOReservaServicio item : peps.darReservaServicio()) {
			voReservas.add(item);
		}
		log.info("Generando los VO de Reservas de Servicios: " + voReservas.size() + " existentes");
		return voReservas;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación PRESTACIONSERVICIO
	 *****************************************************************/

	public PrestacionServicio adicionarPrestacionServicio(int numdocAf, long idServicio, long idIPS, Timestamp fechaHora, long id_Recepcionista)    {
		log.info ("Adicionando PrestacionServicio: numdocAf-" + numdocAf + ", idServicio- "+ idServicio);
		PrestacionServicio ps= peps.adicionarPrestacionServicio(numdocAf, idServicio, idIPS, fechaHora, id_Recepcionista);	
		log.info ("Adicionando PrestacionServicio: " + ps);
		return ps;
	}

	public long eliminarPrestacionServicioPorId(int numdocAf, long idServicio, long idIPS, Timestamp fechaHora) 
	{
		return (long)0;
	}

	public List<PrestacionServicio> darPrestacionServicio()
	{
		log.info("Consultando Prestaciones de servicio");
		List<PrestacionServicio> prestaciones = peps.darPrestacionServicio();
		log.info("Consultando Prestaciones de servicio: " + prestaciones.size() + " existentes");
		return prestaciones;
	}

	public List<VOPrestacionServicio> darVOPrestacionServicio(){
		log.info("Generando los VO de Prestacion de Servicios");
		List<VOPrestacionServicio> voPrestaciones = new LinkedList<VOPrestacionServicio>();
		for(VOPrestacionServicio item : peps.darPrestacionServicio()) {
			voPrestaciones.add(item);
		}
		log.info("Generando los VO de Prestacion de Servicios: " + voPrestaciones.size() + " existentes");
		return voPrestaciones;
	}
	/* ****************************************************************
	 * 			Métodos para manejar la relación Campania
	 *****************************************************************/

	public Campania agregarCampaniaRF10(String nombre, int pAfiliados, Timestamp pFechaInicio, Timestamp pFechaFin, List<Long> servicios, List<Integer> cantidades) throws Exception {
		log.info ("Adicionando Campania: " + nombre );
		Campania campania= peps.agregarCampaniaRF10(nombre, pAfiliados, pFechaInicio, pFechaFin, servicios, cantidades);	
		log.info ("Adicionando Campania: " + campania);
		return campania;
	}

	public long cancelarServicioCampaniaRF11(long idServicio, long idCampania) {
		log.info ("Eliminando Servicio "+ idServicio + " de Campania: " + idCampania );
		long numReservasEliminadas= peps.cancelarServicioCampaniaRF11(idServicio, idCampania);	
		log.info ("Se eliminaron: " + numReservasEliminadas + " reservas");
		return numReservasEliminadas;
	}
	public List<Campania> darCampania()
	{
		log.info("Consultando Campañas");
		List<Campania> campanias = peps.darCampania();
		log.info("Consultando Campañas: " + campanias.size() + " existentes");
		return campanias;
	}

	public List<VOCampania> darVOCampania(){
		log.info("Generando los VO de Campaña");
		List<VOCampania> voCampanias = new LinkedList<VOCampania>();
		for(VOCampania item : peps.darCampania()) {
			voCampanias.add(item);
		}
		log.info("Generando los VO de Campaña: " + voCampanias.size() + " existentes");
		return voCampanias;
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación Inhabilitación
	 *****************************************************************/
	public String deshabilitarServicioRF12(long idServicio, long idIPS, Timestamp fechaInicio, Timestamp fechaFin) {
		log.info ("Adicionando Inhabilitación: servicio " + idServicio + " - IPS " + idIPS );
		String resultado = peps.deshabilitarServicioRF12(idServicio, idIPS, fechaInicio, fechaFin);
		log.info ("Adicionando Inhabilitación: " + resultado);
		return resultado;
	}
	public Inhabilitacion reabrirServicioRF13(Timestamp nuevaFechaFin, Timestamp fechaInicio, long IPS, long Servicio) {
		log.info ("Modificando Inhabilitación: servicio " + Servicio + " - IPS " + IPS );
		Inhabilitacion resultado = peps.reabrirServicioRF13(nuevaFechaFin, fechaInicio, IPS, Servicio);
		log.info ("Adicionando Inhabilitación: " + resultado);
		return resultado;
	}
	public List<Inhabilitacion> darInhabilitacion()
	{
		log.info("Consultando Campañas");
		List<Inhabilitacion> Inhabilitacions = peps.darInhabilitacion();
		log.info("Consultando Campañas: " + Inhabilitacions.size() + " existentes");
		return Inhabilitacions;
	}

	public List<VOInhabilitacion> darVOInhabilitacion(){
		log.info("Generando los VO de Campaña");
		List<VOInhabilitacion> voInhabilitaciones = new LinkedList<VOInhabilitacion>();
		for(VOInhabilitacion item : peps.darInhabilitacion()) {
			voInhabilitaciones.add(item);
		}
		log.info("Generando los VO de Campaña: " + voInhabilitaciones.size() + " existentes");
		return voInhabilitaciones;
	}


	/* ****************************************************************
	 * 			Metodos útiles
	 *****************************************************************/

	public RolUsuario darRolDeUsuarioPorNumDoc( int numDoc) {
		log.info("Consultando Rol del usuario ingresado");
		List<RolUsuario> roles = peps.darRolPorNumDoc(numDoc);

		RolUsuario rol = null;

		if (roles.size() == 1) {
			rol = roles.get(0);
			log.info("Rol consultado: " + rol.getNombre() + " existente");
		}

		return rol;
	}



	/* ****************************************************************
	 * 			Metodos para requerimientos funcionales
	 *****************************************************************/

	public List<Object []> darRFC1 (Timestamp anio, Timestamp fechaHoraInicio,Timestamp fechaHoraFin)
	{
		log.info ("Listando cosas");
		List<Object []> tuplas = peps.RFC1(anio, fechaHoraInicio, fechaHoraFin);
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	public List<Object []> darRFC2 ()
	{
		log.info ("Listando cosas");
		List<Object []> tuplas = peps.RFC2();
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	public List<Object []> darRFC3 ()
	{
		log.info ("Listando cosas");
		List<Object []> tuplas = peps.RFC3();
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	public List<Object []> darRFC5 (int numDoc, Timestamp f1, Timestamp f2)
	{
		log.info ("Listando cosas");
		List<Object []> tuplas = peps.RFC5(numDoc, f1, f2);
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	public List<Object []> darRFC6 (String tiempo, String servicio)
	{
		log.info ("Listando cosas");
		List<Object []> tuplas = peps.RFC6(tiempo, servicio);
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	public List<Object []> darRFC62 (String tiempo, String servicio)
	{
		log.info ("Listando cosas");
		List<Object []> tuplas = peps.RFC62(tiempo, servicio);
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	public List<Object []> darRFC63 (String tiempo, String servicio)
	{
		log.info ("Listando cosas");
		List<Object []> tuplas = peps.RFC63(tiempo, servicio);
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	public List<Object []> darRFC7 ()
	{
		log.info ("Listando cosas");
		List<Object []> tuplas = peps.RFC7();
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	public List<String> darRFC8 ()
	{
		log.info ("Listando cosas");
		List<String> tuplas = peps.RFC8();
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	public List<Object []> darRFC9 ()
	{
		//TODO pasar parametros.
		log.info ("Listando cosas");
		List<Object []> tuplas = peps.RFC9();
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	public List<Object []> darRFC10 ()
	{
		//TODO pasar parametros.
		log.info ("Listando cosas");
		List<Object []> tuplas = peps.RFC10();
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	public List<Object []> darRFC11 ()
	{
		//TODO pasar parametros.
		log.info ("Listando cosas");
		List<Object []> tuplas = peps.RFC11();
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	public List<Object []> darRFC12 ()
	{
		//TODO pasar parametros.
		log.info ("Listando cosas");
		List<Object []> tuplas = peps.RFC12();
		log.info ("Listando cosas: Listo!");
		return tuplas;
	}

	/* ****************************************************************
	 * 			Métodos para administración
	 *****************************************************************/

	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarEPSAndes ()
	{
		log.info ("Limpiando la BD de Parranderos");
		long [] borrrados = peps.limpiarEPSAndes();	
		log.info ("Limpiando la BD de Parranderos: Listo!");
		return borrrados;
	}
}
