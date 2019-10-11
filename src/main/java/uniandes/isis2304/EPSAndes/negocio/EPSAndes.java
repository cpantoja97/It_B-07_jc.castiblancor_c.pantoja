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

import java.sql.Date;
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

	public Afiliado adicionarAfiliado (int numDoc, Timestamp fechaNacimiento)
	{
		log.info ("Adicionando Afiliado: " + numDoc);
		Afiliado afiliado = peps.adicionarAfiliado(numDoc, fechaNacimiento);		
		log.info ("Adicionando Afiliado: " + afiliado);
		return afiliado;
	}

	public long eliminarAfiliadoPorId (int numDoc)
	{
		log.info ("Eliminando Alfliado por id: " + numDoc);
		long resp = peps.eliminarAfiliadoPorId(numDoc);		
		log.info ("Eliminando Afiliado por id: " + resp + " tuplas eliminadas");
		return resp;
	}

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

	public Recepcionista adicionarRecepcionista(int numDoc, long idIps)
	{
		log.info ("Adicionando Recepcionista: " + idIps);
		Recepcionista recepcionista = peps.adicionarRecepcionistas(numDoc, idIps);		
		log.info ("Adicionando Recepcionista: " + recepcionista);
		return recepcionista;
	}

	public long eliminarRecepcionistaPorId (int numDoc)
	{
		log.info ("Eliminando Recepcionista por id: " + numDoc);
		long resp = peps.eliminarRecepcionistaPorId(numDoc);		
		log.info ("Eliminando Recepcionista por id: " + resp + " tuplas eliminadas");
		return resp;
	}

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

	public IPS adicionarIPS(String nombre, String localizacion)
	{
		log.info ("Adicionando IPS: " + nombre);
		IPS ips = peps.adicionarIPS(nombre, localizacion);		
		log.info ("Adicionando IPS: " + ips);
		return ips;
	}

	public long eliminarIPSPorId (long id)
	{
		log.info ("Eliminando IPS por id: " + id);
		long resp = peps.eliminarIPSPorId(id);		
		log.info ("Eliminando IPS por id: " + resp + " tuplas eliminadas");
		return resp;
	}

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

	public Servicio adicionarServicio(String nombre)
	{
		log.info ("Adicionando Servicio: " + nombre);
		Servicio servicio = peps.adicionarServicio(nombre);		
		log.info ("Adicionando Servicio: " + servicio);
		return servicio;
	}

	public long eliminarServicioPorId (long id)
	{
		log.info ("Eliminando Servicio por id: " + id);
		long resp = peps.eliminarServicioPorId(id);		
		log.info ("Eliminando Servicio por id: " + resp + " tuplas eliminadas");
		return resp;
	}

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

	public Usuario adicionarUsuario(int numDoc, TipoDocumento tipoDoc, String nombre, String correo, long rol)
	{
		log.info ("Adicionando Usuario: " + nombre);
		Usuario usuario = peps.adicionarUsuario(numDoc, tipoDoc, nombre, correo, rol);		
		log.info ("Adicionando Usuario: " + usuario);
		return usuario;
	}

	public long eliminarUsuarioPorId (long numDoc)
	{
		log.info ("Eliminando Usuario por numDoc: " + numDoc);
		long resp = peps.eliminarUsuarioPorId(numDoc);		
		log.info ("Eliminando Usuario por numDoc: " + resp + " tuplas eliminadas");
		return resp;
	}

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

	public Medico adicionarMedico(int numDoc, int registroMedico)
	{
		log.info ("Adicionando Medico: " + numDoc);
		Medico medico= peps.adicionarMedico(numDoc, registroMedico);		
		log.info ("Adicionando Medico: " + medico);
		return medico;
	}

	public long eliminarMedicoPorId (long numDoc)
	{
		log.info ("Eliminando Medico por numDoc: " + numDoc);
		long resp = peps.eliminarMedicoPorId(numDoc);		
		log.info ("Eliminando Medico por numDoc: " + resp + " tuplas eliminadas");
		return resp;
	}

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

	public RolUsuario adicionarRolUsuario(String nombre)
	{
		log.info ("Adicionando RolUsuario: " + nombre);
		RolUsuario rol= peps.adicionarRolUsuario(nombre);		
		log.info ("Adicionando RolUsuario: " + rol);
		return rol;
	}

	public long eliminarRolUsuarioPorId (long id)
	{
		log.info ("Eliminando Rol usuario por id: " + id);
		long resp = peps.eliminarRolUsuarioPorId(id);		
		log.info ("Eliminando Rol Usuario por id: " + resp + " tuplas eliminadas");
		return resp;
	}

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

	public ServiciosIPS adicionarServiciosIPS(long idIPS, long idServicio, int capacidad, String horarioDeAtencion)
	{
		log.info ("Adicionando Servicios IPS: idIPS-" + idIPS+ ", idServicio-" + idServicio);
		ServiciosIPS sips= peps.adicionarServiciosIPS(idIPS, idServicio, capacidad, horarioDeAtencion);		
		log.info ("Adicionando Servicios IPS: " + sips);
		return sips;
	}

	public long eliminarSeriviciosIPSPorId (long idIPS, long idServicio)
	{
		log.info ("Eliminando Servicio IPS por id: idIPs-" + idIPS+ ", idServicio-" +idServicio);
		long resp = peps.eliminarServiciosIPSPorId(idIPS, idServicio);		
		log.info ("Eliminando Servicio IPS por id: " + resp + " tuplas eliminadas");
		return resp;
	}

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

	public MedicosAbscritos adicionarMedicoAbscrito( int numdocMed, long id_IPS)
	{
		log.info ("Adicionando MedicosAbscritos: idMedico-" + numdocMed+ ", idIPS-" + id_IPS);
		MedicosAbscritos med= peps.adicionarMedicosAbscritos(numdocMed, id_IPS);	
		log.info ("Adicionando MedicosAbscritos: " + med);
		return med;
	}

	public long eliminarRolUsuarioPorId (int numdocAf, long idIPS)
	{
		log.info ("Eliminando MedicosAbscritos por id: idMedico-" + numdocAf+ ", idIPS-" + idIPS);
		long resp = peps.eliminarMedicosAbscritosPorId(numdocAf, idIPS);		
		log.info ("Eliminando MedicosAbscritos por id: " + resp + " tuplas eliminadas");
		return resp;
	}

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

	public ServiciosMedico adicionarServiciosMedico( int numdocMed, long idServicio, String especialidad)
	{
		log.info ("Adicionando ServiciosMedico: idMedico-" + numdocMed+ ", idServicio-" + idServicio);
		ServiciosMedico sMed= peps.adicionarServiciosMedicos(numdocMed, idServicio,especialidad);	
		log.info ("Adicionando ServiciosMedico: " + sMed);
		return sMed;
	}

	public long eliminarServiciosMedicosPorId(int numdocAf, long idServicio) 
	{
		log.info ("Eliminando ServiciosMedicos por id: idMedico-" + numdocAf+ ", idServicio-" + idServicio);
		long resp = peps.eliminarServiciosMedicosPorId(numdocAf, idServicio);		
		log.info ("Eliminando ServiciosMedicos por id: " + resp + " tuplas eliminadas");
		return resp;
	}

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

	public Medicamento adicionarMedicamento( String nombre, String descripcion) {
		log.info ("Adicionando Medicamento: " + nombre);
		Medicamento medicamento= peps.adicionarMedicamento(nombre, descripcion);	
		log.info ("Adicionando Medicamento: " + medicamento);
		return medicamento;
	}

	public long eliminarMedicamentoPorId(long idMedicamento) 
	{
		return (long)0;
	}

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

	public ReservaServicio adicionarReservaServicio( int numdocAf, long idServicio, long idIPS, Timestamp fechaHora)   {
		log.info ("Adicionando ReservaServicio: numdocAf-" + numdocAf + ", idServicio- "+ idServicio);
		ReservaServicio rs= peps.adicionarReservaServicio(numdocAf, idServicio, idIPS, fechaHora);	
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
