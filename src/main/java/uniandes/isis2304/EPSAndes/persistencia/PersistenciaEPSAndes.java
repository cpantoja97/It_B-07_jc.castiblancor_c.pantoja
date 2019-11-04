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

package uniandes.isis2304.EPSAndes.persistencia;


import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.EPSAndes.negocio.*;


/**
 * Clase para el manejador de persistencia del proyecto Parranderos
 * Traduce la información entre objetos Java y tuplas de la base de datos, en ambos sentidos
 * Sigue un patrón SINGLETON (Sólo puede haber UN objeto de esta clase) para comunicarse de manera correcta
 * con la base de datos
 * Se apoya en las clases SQLBar, SQLBebedor, SQLBebida, SQLGustan, SQLSirven, SQLTipoBebida y SQLVisitan, que son 
 * las que realizan el acceso a la base de datos
 * 
 * @author Germán Bravo
 */
@SuppressWarnings("deprecation")
public class PersistenciaEPSAndes 
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(PersistenciaEPSAndes.class.getName());

	/**
	 * Cadena para indicar el tipo de sentencias que se va a utilizar en una consulta
	 */
	public final static String SQL = "javax.jdo.query.SQL";

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Atributo privado que es el único objeto de la clase - Patrón SINGLETON
	 */
	private static PersistenciaEPSAndes instance;

	/**
	 * Fábrica de Manejadores de persistencia, para el manejo correcto de las transacciones
	 */
	private PersistenceManagerFactory pmf;

	/**
	 * Arreglo de cadenas con los nombres de las tablas de la base de datos, en su orden:
	 * Secuenciador, tipoBebida, bebida, bar, bebedor, gustan, sirven y visitan
	 */
	private List <String> tablas;
	private SQLUtil sqlUtil;
	private SQLAfiliado sqlAfiliado;
	private SQLRecepcionista sqlRecepcionista;
	private SQLIPS sqlIPS;
	private SQLServicio sqlServicio;
	private SQLUsuario sqlUsuario;
	private SQLMedico sqlMedico;
	private SQLRolUsuario sqlRoUsuario;
	private SQLServiciosIPS sqlServiciosIPS;
	private SQLMedicosAbscritos sqlMedicosAbscritos;
	private SQLServiciosMedicos sqlServiciosMedicos;
	private SQLReceta sqlReceta;
	private SQLMedicamento sqlMedicamento;
	private SQLItemsReceta sqlItemsReceta;
	private SQLOrdenDeServicio sqlOrdenDeServicio;
	private SQLReservaServicio sqlReservaServicio;
	private SQLPrestacionServicio sqlPrestacionServicio;
	private SQLCampania sqlCampania;
	private SQLInhabilitacion sqlInhabilitacion;
	private SQLTipoServicio sqlTipoServicio;
	private SQLConsultas sqlConsulta;

	/* ****************************************************************
	 * 			Métodos del MANEJADOR DE PERSISTENCIA
	 *****************************************************************/

	/**
	 * Constructor privado con valores por defecto - Patrón SINGLETON
	 */
	private PersistenciaEPSAndes ()
	{
		pmf = JDOHelper.getPersistenceManagerFactory("EPSAndes");		
		crearClasesSQL ();

		// Define los nombres por defecto de las tablas de la base de datos
		tablas = new LinkedList<String> ();
		tablas.add ("EPSAndes_sequence");
		tablas.add ("AFILIADOS");
		tablas.add("RECEPCIONISTAS");
		tablas.add("IPS");
		tablas.add("SERVICIOS");
		tablas.add("USUARIOS");
		tablas.add("MEDICOS");
		tablas.add("ROLUSUARIO");
		tablas.add("SERVICIOSIPS");
		tablas.add("MEDICOSABSCRITOS");
		tablas.add("SERVICIOSMEDICOS");
		tablas.add("RECETAS");
		tablas.add("MEDICAMENTOS");
		tablas.add("ITEMSRECETA");
		tablas.add("ORDENES");
		tablas.add("RESERVASERVICIO");
		tablas.add("PRESTACIONSERVICIO");
		tablas.add("CAMPANIA");
		tablas.add("TIPOSERVICIO");
		tablas.add("INHABILITACION");
	}

	/**
	 * Constructor privado, que recibe los nombres de las tablas en un objeto Json - Patrón SINGLETON
	 * @param tableConfig - Objeto Json que contiene los nombres de las tablas y de la unidad de persistencia a manejar
	 */
	private PersistenciaEPSAndes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas (tableConfig);

		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}

	/**
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaEPSAndes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaEPSAndes ();
		}
		return instance;
	}

	/**
	 * Constructor que toma los nombres de las tablas de la base de datos del objeto tableConfig
	 * @param tableConfig - El objeto JSON con los nombres de las tablas
	 * @return Retorna el único objeto PersistenciaParranderos existente - Patrón SINGLETON
	 */
	public static PersistenciaEPSAndes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaEPSAndes (tableConfig);
		}
		return instance;
	}

	/**
	 * Cierra la conexión con la base de datos
	 */
	public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}

	/**
	 * Genera una lista con los nombres de las tablas de la base de datos
	 * @param tableConfig - El objeto Json con los nombres de las tablas
	 * @return La lista con los nombres del secuenciador y de las tablas
	 */
	private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;

		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}

		return resp;
	}

	/**
	 * Crea los atributos de clases de apoyo SQL
	 */
	private void crearClasesSQL ()
	{
		sqlAfiliado = new SQLAfiliado(this);
		sqlRecepcionista = new SQLRecepcionista(this);
		sqlIPS = new SQLIPS(this);
		sqlServicio = new SQLServicio(this);
		sqlUsuario = new SQLUsuario(this);
		sqlMedico = new SQLMedico(this);
		sqlRoUsuario = new SQLRolUsuario(this);
		sqlServiciosIPS = new SQLServiciosIPS(this);
		sqlMedicosAbscritos = new SQLMedicosAbscritos(this);
		sqlServiciosMedicos = new SQLServiciosMedicos(this);
		sqlReceta = new SQLReceta(this);
		sqlMedicamento = new SQLMedicamento(this);
		sqlItemsReceta = new SQLItemsReceta(this);
		sqlOrdenDeServicio = new SQLOrdenDeServicio(this);
		sqlReservaServicio = new SQLReservaServicio(this);
		sqlPrestacionServicio = new SQLPrestacionServicio(this);
		sqlCampania = new SQLCampania(this);
		sqlInhabilitacion = new SQLInhabilitacion(this);
		sqlTipoServicio = new SQLTipoServicio(this);
		sqlUtil = new SQLUtil(this);
		sqlConsulta = new SQLConsultas(this);
	}

	public String darSeqEPSAndes ()
	{
		return tablas.get (0);
	}

	public String darTablaRolUsuario() {
		return tablas.get(1);
	}

	public String darTablaIPS() {
		return tablas.get(2);
	}

	public String darTablaServicio() {
		return tablas.get(3);
	}

	public String darTablaUsuario() {
		return tablas.get(4);
	}

	public String darTablaMedico() {
		return tablas.get(5);
	}

	public String darTablaAfiliados ()
	{
		return tablas.get (6);
	}

	public String darTablaRecepcionistas() {
		return tablas.get(7);
	}

	public String darTablaServiciosIPS() {
		return tablas.get(8);
	}

	public String darTablaMedicosAbscritos() {
		return tablas.get(9);
	}

	public String darTablaReceta() {
		return tablas.get(10);
	}

	public String darTablaMedicamento() {
		return tablas.get(11);
	}

	public String darTablaItemReceta() {
		return tablas.get(12);
	}

	public String darTablaOrdenDeServicio() {
		return tablas.get(13);
	}

	public String darTablaReservaServicio() {
		return tablas.get(14);
	}

	public String darTablaServiciosMedicos() {
		return tablas.get(10);
	}

	public String darTablaPrestacionServicio() {
		return tablas.get(15);
	}

	public String darTablaCampania() {
		return  tablas.get(16);
	}

	public String darTablaTipoServicio() {
		return tablas.get(17);
	}

	public String darTablaInhabilitacion() {
		return tablas.get(18);
	}

	/**
	 * Transacción para el generador de secuencia de Parranderos
	 * Adiciona entradas al log de la aplicación
	 * @return El siguiente número del secuenciador de Parranderos
	 */
	private long nextval ()
	{
		long resp = sqlUtil.nextval (pmf.getPersistenceManager());
		log.trace ("Generando secuencia: " + resp);
		return resp;
	}

	/**
	 * Extrae el mensaje de la exception JDODataStoreException embebido en la Exception e, que da el detalle específico del problema encontrado
	 * @param e - La excepción que ocurrio
	 * @return El mensaje de la excepción JDO
	 */
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}

	/* ****************************************************************
	 * 			Métodos para manejar los AFILIADOS
	 *****************************************************************/

	public Afiliado adicionarAfiliado(int numDoc, Timestamp fechaNacimiento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlAfiliado.adicionarAfiliado(pm, numDoc, fechaNacimiento);
			tx.commit();

			log.trace ("Inserción Afiliado: " + numDoc + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Afiliado (numDoc,fechaNacimiento);
		}
		catch (Exception e)
		{
			// e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarAfiliadoPorId (int numDoc) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlAfiliado.eliminarAfiliadoPorId(pm, numDoc);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<Afiliado> darAfiliados ()
	{
		return sqlAfiliado.darAfiliados(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar los RECEPCIONISTAS
	 *****************************************************************/

	public Recepcionista adicionarRecepcionistas(int numDoc, long idIPS) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlRecepcionista.adicionarRecepcionsita(pm, numDoc, idIPS);
			tx.commit();

			log.trace ("Inserción Recepcionista: " + numDoc + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Recepcionista (numDoc,idIPS);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarRecepcionistaPorId (int numDoc) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlRecepcionista.eliminarRecepcionistaPorId(pm, numDoc);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<Recepcionista> darRecepcionistas ()
	{
		return sqlRecepcionista.darRecepcionistas(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar las IPS
	 *****************************************************************/

	public IPS adicionarIPS(String nombre, String localizacion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long idIPS = nextval ();
			long tuplasInsertadas = sqlIPS.adicionarIPS(pm, idIPS, nombre, localizacion);
			tx.commit();

			log.trace ("Inserción IPS: " + idIPS + ": " + tuplasInsertadas + " tuplas insertadas");
			return new IPS(idIPS, nombre,localizacion);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarIPSPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlIPS.eliminarIPSPorId(pm, id);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<IPS> darIPSs ()
	{
		return sqlIPS.darIPSs(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar los SERVICIOS
	 *****************************************************************/

	public Servicio adicionarServicio(String nombre, long tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long idServicio = nextval ();
			long tuplasInsertadas = sqlServicio.adicionarServicio(pm, idServicio, nombre, tipo);
			tx.commit();

			log.trace ("Inserción Servicio: " + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Servicio(idServicio, nombre);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarServicioPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlServicio.eliminarServicioPorId(pm, id);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<Servicio> darServicios ()
	{
		return sqlServicio.darServicios(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar los USUARIOS
	 *****************************************************************/

	public Usuario adicionarUsuario(int numDoc, TipoDocumento tipoDoc, String nombre, String correo, long idRol) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long tuplasInsertadas = sqlUsuario.adicionarUsuario(pm, numDoc, tipoDoc, nombre, correo, idRol);
			tx.commit();

			log.trace ("Inserción Usuario: " + numDoc + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Usuario(numDoc, tipoDoc, nombre, correo, idRol);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarUsuarioPorId (long numDoc) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlUsuario.eliminarUsuarioPorId(pm, numDoc);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<Usuario> darUsuarios ()
	{
		return sqlUsuario.darUsuarios(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar los MEDICOS
	 *****************************************************************/

	public Medico adicionarMedico(int numDoc, int registroMedico) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long tuplasInsertadas = sqlMedico.adicionarMedico(pm, numDoc, registroMedico);
			tx.commit();

			log.trace ("Inserción Medico: " + numDoc + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Medico (numDoc,registroMedico);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarMedicoPorId (long numDoc) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlMedico.eliminarMedicoPorId(pm, numDoc);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<Medico> darMedicos ()
	{
		return sqlMedico.darMedicos(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación ROLUSUARIO
	 *****************************************************************/
	public RolUsuario adicionarRolUsuario(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long idRol = nextval ();
			long tuplasInsertadas = sqlRoUsuario.adicionarRolUsuario(pm, idRol, nombre);
			tx.commit();

			log.trace ("Inserción Rol Usuario: " + idRol + ": " + tuplasInsertadas + " tuplas insertadas");
			return new RolUsuario(idRol, nombre);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarRolUsuarioPorId (long id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlRoUsuario.eliminarRolUsuarioPorId( pm,  id);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<RolUsuario> darRolUsuario()
	{
		return sqlRoUsuario.darRolUsuario(pmf.getPersistenceManager());
	}

	public List<RolUsuario> darRolPorNombre(String pNombre){
		return sqlRoUsuario.darRolPorNombre(pmf.getPersistenceManager(), pNombre);
	}
	public List<RolUsuario> darRolPorID(long id){
		return sqlRoUsuario.darRolPorID(pmf.getPersistenceManager(), id);
	}
	/* ****************************************************************
	 * 			Métodos para manejar la relación ServiciosIPS
	 *****************************************************************/

	public ServiciosIPS adicionarServiciosIPS( long idIPS, long idServicio, int capacidad, Timestamp horarioInicio, Timestamp horarioFin) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlServiciosIPS.adicionarServiciosIPS( pm,  idIPS,  idServicio,  capacidad,  horarioInicio, horarioFin);
			tx.commit();

			log.trace ("Inserción Servicios de IPS: " + idIPS + "-" + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
			return new ServiciosIPS (idIPS,  idServicio,  capacidad,  horarioInicio, horarioFin);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}


	public long eliminarServiciosIPSPorId (long idIPS, long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlServiciosIPS.eliminarServiciosIPSPorId(pm, idIPS, idServicio);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<ServiciosIPS> darServiciosIPS()
	{
		return sqlServiciosIPS.darServiciosIPS(pmf.getPersistenceManager());
	}


	/* ****************************************************************
	 * 			Métodos para manejar la relación MedicosAbscritos
	 *****************************************************************/

	public MedicosAbscritos adicionarMedicosAbscritos( int numdocMed, long id_IPS ) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlMedicosAbscritos.adicionarMedicosAbscritos(pm,  numdocMed,  id_IPS);
			tx.commit();

			log.trace ("Inserción MedicosAbscritos: " + numdocMed+"-"+  id_IPS + ": " + tuplasInsertadas + " tuplas insertadas");
			return new MedicosAbscritos( numdocMed,  id_IPS);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarMedicosAbscritosPorId(int numdocAf, long idIPS) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlMedicosAbscritos.eliminarMedicosAbscritosPorId(pm,  numdocAf,  idIPS );
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<MedicosAbscritos> darMedicosAbscritos()
	{
		return sqlMedicosAbscritos.darMedicosAbscritos(pmf.getPersistenceManager());
	}


	/* ****************************************************************
	 * 			Métodos para manejar la relación ServiciosMedicos
	 *****************************************************************/

	public ServiciosMedico adicionarServiciosMedicos(int numdocMed, long idServicio, String especialidad) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();

			long tuplasInsertadas = sqlServiciosMedicos.adicionarServiciosMedicos(pm,  numdocMed,  idServicio,  especialidad);
			tx.commit();

			log.trace ("Inserción ServiciosMedicos: " + numdocMed + "-" + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
			return new ServiciosMedico( numdocMed,  idServicio,  especialidad );
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarServiciosMedicosPorId (int numdocAf, long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlServiciosMedicos.eliminarServiciosMedicosPorId(pm,  numdocAf,  idServicio );
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<ServiciosMedico> darServiciosMedicos()
	{
		return sqlServiciosMedicos.darServiciosMedicos(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación Receta
	 *****************************************************************/

	public Receta adicionarReceta(int numdocMed, int numdocAf) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long idReceta = nextval ();
			long tuplasInsertadas = sqlReceta.adicionarReceta(pm, idReceta,  numdocMed,  numdocAf );
			tx.commit();

			log.trace ("Inserción Receta: " + idReceta + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Receta(idReceta,  numdocMed,  numdocAf );
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarRecetaPorId (long idReceta) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlReceta.eliminarRecetaPorId(pm,  idReceta );
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<Receta> darRecetas()
	{
		return sqlReceta.darRecetas(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación Medicamento
	 *****************************************************************/

	public Medicamento adicionarMedicamento( String nombre, String descripcion) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long idMedicamento = nextval ();
			long tuplasInsertadas = sqlMedicamento.adicionarMedicamento(pm, idMedicamento, nombre, descripcion);
			tx.commit();

			log.trace ("Inserción Medicamento: " + idMedicamento + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Medicamento(idMedicamento, nombre, descripcion);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarMedicamentoPorId (long idMedicamento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlMedicamento.eliminarMedicamentoPorId(pm, idMedicamento);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<Medicamento> darMedicamento()
	{
		return sqlMedicamento.darMedicamentos(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación ItemsReceta
	 *****************************************************************/

	public ItemReceta adicionarItemsReceta(long idReceta, long idMedicamento, int cantidad, String indicaciones) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlItemsReceta.adicionarItemReceta(pm,  idReceta,  idMedicamento,  cantidad,  indicaciones);
			tx.commit();

			log.trace ("Inserción ItemsReceta: " + idReceta +"-"+ idMedicamento + ": " + tuplasInsertadas + " tuplas insertadas");
			return new ItemReceta( idReceta,  idMedicamento,  cantidad,  indicaciones);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarItemsRecetaPorId (long idReceta, long idMedicamento) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlItemsReceta.eliminarItemRecetaPorId(pm,  idReceta,  idMedicamento );
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<ItemReceta> darItemsReceta()
	{
		return sqlItemsReceta.darItemsReceta(pmf.getPersistenceManager());
	}


	/* ****************************************************************
	 * 			Métodos para manejar la relación OrdenDeServicio
	 *****************************************************************/

	public OrdenDeServicio adicionarOrdenDeServicio(int numdocMedico, int numdocAfiliado, long idServicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long id = nextval ();
			long tuplasInsertadas = sqlOrdenDeServicio.adicionarOrdenDeServicio(pm,  numdocMedico,  numdocAfiliado,  idServicio, id);
			tx.commit();

			log.trace ("Inserción OrdenDeServicio: " + numdocMedico + "-"+numdocAfiliado +"-" + idServicio + ": " + tuplasInsertadas + " tuplas insertadas");
			return new OrdenDeServicio( numdocMedico,  numdocAfiliado,  idServicio, id);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarOrdenDeServicioPorId (int id) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlOrdenDeServicio.eliminarOrdenDeServicioPorId(pm, id);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<OrdenDeServicio> darOrdenes()
	{
		return sqlOrdenDeServicio.darOrdenes(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación ReservaServicio
	 *****************************************************************/

	public ReservaServicio adicionarReservaServicioAfiliado( int numdocAf, long idServicio, long idIPS, Timestamp fechaHora) throws Exception
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			// La IPS Presta el servicio?
			List<ServiciosIPS> respConsulta = sqlServiciosIPS.buscarServicioIPS(pm, idIPS, idServicio);
			if(respConsulta.size() == 0) {
				throw new Exception("NO PRESTA - La IPS de código " + idIPS + " no presta el servicio " + idServicio);
			}

			ServiciosIPS servIPS = respConsulta.get(0);
			int capacidad = servIPS.getCapacidad();
			int deltaTiempo = (int) ((servIPS.getHorarioFin().getTime() - servIPS.getHorarioInicio().getTime())/capacidad);
			Timestamp finReserva = new Timestamp(fechaHora.getTime() + deltaTiempo);

			// El servicio está habilitado?
			List<Inhabilitacion> inhabilitaciones = sqlInhabilitacion.darInhabilitacionesServicio(pm, idIPS, idServicio, fechaHora, finReserva);
			if(inhabilitaciones.size() > 0) {
				throw new Exception("INHABILITADO - El servicio "+idIPS+" está inhabilitado en la IPS "+idIPS+" temporalmente.");
			}

			long tuplasInsertadas = sqlReservaServicio.adicionarReservaServicioAfiliado(pm, numdocAf,  idServicio,  idIPS,  fechaHora);
			tx.commit();

			log.trace ("Inserción ReservaServicio: " + numdocAf + "-" +idServicio + "-" + idIPS + "-" + fechaHora + ": " + tuplasInsertadas + " tuplas insertadas");
			return new ReservaServicio(numdocAf,  idServicio,  idIPS,  fechaHora, -1);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();

			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));

			if(e.getMessage().startsWith("INHABILITADO")) {
				throw new Exception(e.getMessage());
			} else if(e.getMessage().startsWith("NO PRESTA")) {
				throw new Exception(e.getMessage());
			}
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarReservaServicioPorId (int numdocAf, long idServicio, long idIPS, Timestamp fechaHora) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlReservaServicio.eliminarReservaServicioPorId(pm, idServicio,  idIPS,  fechaHora );
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<ReservaServicio> darReservaServicio()
	{
		return sqlReservaServicio.darReservaServicio(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación PrestacionServicio
	 *****************************************************************/

	public PrestacionServicio adicionarPrestacionServicio(int numdocAf, long idServicio, long idIPS, Timestamp fechaHora, long id_Recepcionista) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();            
			long tuplasInsertadas = sqlPrestacionServicio.adicionarPrestacionServicio(pm,  numdocAf,  idServicio,  idIPS,  fechaHora,  id_Recepcionista);
			tx.commit();

			log.trace ("Inserción PrestacionServicio: " +  numdocAf+ "-" +  idServicio + "-" + idIPS + "-" +  fechaHora + "-" +  id_Recepcionista + ": " + tuplasInsertadas + " tuplas insertadas");
			return new PrestacionServicio( numdocAf,  idServicio,  idIPS,  fechaHora,  id_Recepcionista);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarPrestacionServicioPorId (int numdocAf, long idServicio, long idIPS, Timestamp fechaHora) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlPrestacionServicio.eliminarPrestacionServicioPorId(pm,  numdocAf,  idServicio,  idIPS,  fechaHora);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<PrestacionServicio> darPrestacionServicio()
	{
		return sqlPrestacionServicio.darPrestacionServicio(pmf.getPersistenceManager());
	}

	/* ****************************************************************
	 * 			Métodos para manejar la relación Campania
	 *****************************************************************/
	public Campania agregarCampaniaRF10(String nombre, int pAfiliados, Timestamp pFechaInicio, Timestamp pFechaFin, List<Long> serviciosID, List<Integer> cantidades) throws Exception {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			//tx.setIsolationLevel("serializable");

			// Insertar tupla de Campaña
			long id = nextval();
			long tuplasInsertadas = sqlCampania.adicionarCampania(pm,  id, nombre,  pAfiliados,  pFechaInicio,  pFechaFin);
			log.trace ("Inserción Campaña: " +  nombre + ": " + tuplasInsertadas + " tuplas insertadas");

			int dias = pFechaFin.getDate()-pFechaInicio.getDate();

			List<ReservaServicio> reservas = new ArrayList<ReservaServicio>();
			boolean campanaValida = true;

			// Insertar reservas de servicios
			for(int i = 0; i < serviciosID.size(); i++) {
				// Servicio:
				Servicio servicioAct = sqlServicio.buscarServicioPorID(pm, serviciosID.get(i));
				// Cantidad necesaria:
				int cantidad = cantidades.get(i);
				int contador = 0;

				// Hallar las IPS para recorrerlas
				List<IPS> IPSs = sqlIPS.darIPSs(pm);
				for(IPS ips : IPSs) {
					// La IPS Presta el servicio?
					List<ServiciosIPS> respConsulta = sqlServiciosIPS.buscarServicioIPS(pm, ips.getId_IPS(), servicioAct.getId_Servicio());
					// Si sí, se reserva hasta copar el 90% de su capacidad diaría. De lo contratio, no sucede nada
					if(respConsulta.size() > 0) {

						ServiciosIPS servIPS = respConsulta.get(0);
						int capacidad = servIPS.getCapacidad();
						int deltaTiempo = (int) ((servIPS.getHorarioFin().getTime() - servIPS.getHorarioInicio().getTime())/capacidad);
						System.out.println("dT en IPS " + ips.getId_IPS() + " es " + deltaTiempo);
						int numDia = 0;
						// Se copa el 90% de la capacidad para CADA día
						while(numDia <= dias) {
							// Se inicializa las citas del día en 0, la hora inicial en el primer horario de atencion del día correspondiente y se calcula la disponibilidad del día
							int citasDia = 0;
							long hoy = pFechaInicio.getTime() + numDia*86400000;
							long horarioInicio = servIPS.getHorarioInicio().getHours()*3600000 + servIPS.getHorarioInicio().getMinutes()*60000;
							long horarioFinal = servIPS.getHorarioFin().getHours()*3600000 + servIPS.getHorarioFin().getMinutes()*60000;
							long horaAct =  hoy + horarioInicio;
							long horaFinal = hoy + horarioFinal;

							System.out.println("Día: " + new Timestamp(hoy));
							System.out.println("llevo " + contador + " reservas de "+ cantidad);
							System.out.println("Hora inicial " + new Timestamp(horaAct));
							System.out.println("Hora final " + new Timestamp(horaFinal));

							// El servicio está habilitado? Si no lo está, no hace nada
							List<Inhabilitacion> inhabilitaciones = sqlInhabilitacion.darInhabilitacionesServicio(pm, ips.getId_IPS(), servicioAct.getId_Servicio(), new Timestamp(horaAct), new Timestamp(horaFinal));
							if(inhabilitaciones.size() == 0) {

								int reservasDelDia = sqlReservaServicio.darReservasDia(pm, servicioAct.getId_Servicio(), ips.getId_IPS(), new Timestamp(hoy)).size();
								System.out.println("ReservasDia: " + reservasDelDia);
								int disponibilidad = (int)(servIPS.getCapacidad()*0.9 - reservasDelDia);
								System.out.println("Disponibilidad del día: " + disponibilidad);
								// Se copa la disponibilidad del día o se alcanzan las citas necesitadas
								while(citasDia < disponibilidad && contador + citasDia < cantidad) {
									try {
										// Se intenta agregar 
										long reservaInsertada = sqlReservaServicio.adicionarReservaServicioCampania(pm,  servicioAct.getId_Servicio(),  ips.getId_IPS(), new Timestamp(horaAct), id);
										log.trace ("Inserción Reserva: " +  servicioAct.getId_Servicio() +" en "+ ips.getId_IPS() + ""+(new Timestamp(horaAct)).toString() + ": " + reservaInsertada + " tuplas insertadas");
										citasDia++;
										reservas.add(new ReservaServicio(-1, servicioAct.getId_Servicio(), ips.getId_IPS(), new Timestamp(horaAct), id));
									} catch(JDOException e) {
										// Si no se pudo agregar, se continua con el siguiente
									} finally {
										// En cualquier caso, se actualiza la hora
										horaAct += deltaTiempo;
										// Si se pasa el final del horario de atención se cambia de día
										if(horaAct > horaFinal) break;
									}
								}
								// Se actualiza el contador
								contador += citasDia;
							}
							numDia++;
						}
					}
					// Si ya se alcanzó la cantidad necesaria, se sale del ciclo
					System.out.println("Finalmente alcancé a hacer " + contador + " de " + cantidad);
					if(contador >= cantidad) break;
				}
				// Si terminó de revisar IPS y no alcanzó a hacer reservas suficientes, se debe hacer Rollback
				if(contador < cantidad) {
					campanaValida = false;
					tx.rollback();
					throw new Exception("INVIABLE -No hay disponibilidad para alguno de los servicios.");
				}
			}
			// Si nunca hizo rollback, debo hacer commit
			if(campanaValida) tx.commit();
			return new Campania( id, nombre,  pAfiliados,  pFechaInicio,  pFechaFin);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			if(e.getMessage().startsWith("INVIABLE")) {
				String n = e.getMessage().split("-")[1];
				throw new Exception(n);
			}
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long cancelarServicioCampaniaRF11(long idServicio, long idCampania) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlReservaServicio.eliminarReservasCampaniaPorServicio(pm, idServicio,  idCampania);
			tx.commit();
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<Campania> darCampania(){
		return sqlCampania.darCampania(pmf.getPersistenceManager());
	}
	/* ****************************************************************
	 * 			Métodos para manejar la relación Campania
	 *****************************************************************/

	public String deshabilitarServicioRF12(long idServicio, long idIPS, Timestamp fechaInicio, Timestamp fechaFin) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			// Generar la inhabilitación
			long resp = sqlInhabilitacion.adicionarInhabilitacion(pm, fechaInicio, fechaFin, idIPS, idServicio);

			// Obtener reservas en estas fechas
			List<ReservaServicio> reservas = sqlReservaServicio.darReservasParaCambiar(pm, idServicio, idIPS, fechaInicio, fechaFin);
			List<ReservaServicio> reservasCambiadas = new ArrayList<ReservaServicio>();
			List<ReservaServicio> reservasCanceladas = new ArrayList<ReservaServicio>();

			Servicio servicioAct = sqlServicio.buscarServicioPorID(pm, idServicio);

			// Cambiar las reservas
			for( ReservaServicio reserva : reservas) {
				// Si es de una campaña se cancela
				if( reserva.getCampania() != -1 ) {
					sqlReservaServicio.eliminarReservaServicioPorId(pm, idServicio, idIPS, reserva.getFechaHora());
					reservasCanceladas.add(reserva);
				}
				else {
					boolean hayDisponibilidad = false;

					// Hallar las IPS para recorrerlas
					List<IPS> IPSs = sqlIPS.darIPSs(pm);
					int diasMax = 5;
					for(IPS ips: IPSs){
						// La IPS Presta el servicio?
						List<ServiciosIPS> respConsulta = sqlServiciosIPS.buscarServicioIPS(pm, reserva.getIdIPS(), reserva.getIdServicio());
						// Si sí, se busca un espacio vacío para reacomodar la reserva
						if(respConsulta.size() > 0) {
							ServiciosIPS servIPS = respConsulta.get(0);
							int capacidad = servIPS.getCapacidad();
							int deltaTiempo = (int) ((servIPS.getHorarioFin().getTime() - servIPS.getHorarioInicio().getTime())/capacidad);
							System.out.println("dT en IPS " + ips.getId_IPS()+ " es " + deltaTiempo);
							int numDia = 0;

							// Se busca todos los días hasta encontrar un lugar para la reserva hasta máximo 5 días después
							while(!hayDisponibilidad && numDia <= diasMax) {
								// Se inicializa las citas del día en 0, la hora inicial en el primer horario de atencion del día correspondiente y se calcula la disponibilidad del día
								Timestamp ts= new Timestamp( reserva.getFechaHora().getTime() );
								ts.setHours(0);
								ts.setMinutes(0);
								ts.setSeconds(0);
								ts.setNanos(0);
								long hoy = ts.getTime() + numDia*86400000;
								long horarioInicio = servIPS.getHorarioInicio().getHours()*3600000 + servIPS.getHorarioInicio().getMinutes()*60000;
								long horarioFinal = servIPS.getHorarioFin().getHours()*3600000 + servIPS.getHorarioFin().getMinutes()*60000;
								long horaAct =  hoy + horarioInicio;
								long horaFinal = hoy + horarioFinal;

								System.out.println("Día: " + new Timestamp(hoy));
								System.out.println("Hora inicial " + new Timestamp(horaAct));
								System.out.println("Hora final " + new Timestamp(horaFinal));

								// El servicio está habilitado? Si no lo está, no hace nada
								List<Inhabilitacion> inhabilitaciones = sqlInhabilitacion.darInhabilitacionesServicio(pm, ips.getId_IPS(), servicioAct.getId_Servicio(), new Timestamp(horaAct), new Timestamp(horaFinal));
								if(inhabilitaciones.size() == 0) {

									int reservasDelDia = sqlReservaServicio.darReservasDia(pm, servicioAct.getId_Servicio(), ips.getId_IPS(), new Timestamp(hoy)).size();
									System.out.println("ReservasDia: " + reservasDelDia);
									int disponibilidad = (int)(servIPS.getCapacidad()*0.9 - reservasDelDia);
									System.out.println("Disponibilidad del día: " + disponibilidad);
									// Se copa la disponibilidad del día o se alcanzan las citas necesitadas
									while(disponibilidad > 0 && !hayDisponibilidad) {
										try {
											// Se intenta agregar 
											System.out.println("Se intentará cambiar reserva a: " + new Timestamp(horaAct));
											sqlReservaServicio.cambiarReserva(pm, reserva.getIdServicio(), reserva.getIdIPS(), reserva.getFechaHora(), ips.getId_IPS(), new Timestamp(horaAct));
											log.trace ("Actualización Reserva: " +  servicioAct.getId_Servicio() +" en "+ ips.getId_IPS() + ""+(new Timestamp(horaAct)).toString());
											hayDisponibilidad = true;
											reserva.setId_IPS(ips.getId_IPS());
											reserva.setFechaHora(new Timestamp(horaAct));
										} catch(JDOException e) {
											// Si no se pudo agregar, se continua con el siguiente
										} finally {
											// En cualquier caso, se actualiza la hora
											horaAct += deltaTiempo;
											// Si se pasa el final del horario de atención se cambia de día
											if(horaAct > horaFinal) break;
										}
									}
								}
								numDia++;
							}
							if(hayDisponibilidad) break;
						}
					}
					if(hayDisponibilidad) {
						reservasCambiadas.add(reserva);
					} else {
						sqlReservaServicio.eliminarReservaServicioPorId(pm, idServicio, idIPS, reserva.getFechaHora());
						reservasCanceladas.add(reserva);
					}
				}



			}

			System.out.println("Quedaron "+ sqlReservaServicio.darReservasParaCambiar(pm, idServicio, idIPS, fechaInicio, fechaFin).size() + " reservas" );

			tx.commit();
			log.trace ("Inserción Inhabilitación: " +  idServicio + " - " + idIPS + " - " +  fechaInicio + " hasta " +  fechaFin + ": " + resp + " tuplas insertadas");
			
			Inhabilitacion inhab = new Inhabilitacion(fechaInicio, fechaFin, idIPS, idServicio);
			
			String mensaje = "En deshabilitar Servicio\n\n";
			mensaje += "Servicio deshabilitado exitosamente: " + inhab +"\n";
			
			mensaje +="Había " + reservas.size() + " reservas\n";
			mensaje += "Se cancelaron las siguientes reservas: \n";
			for(ReservaServicio r : reservasCanceladas) {
				mensaje += "\t" + r.toString() + "\n";
			}			
			mensaje += "Se cambiaron las siguientes reservas: \n";
			for(ReservaServicio r : reservasCambiadas) {
				mensaje += "\t" + r.toString() + "\n";
			}

			mensaje += "Operación terminada\n";
			
			return mensaje;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}
	public Inhabilitacion reabrirServicioRF13(Timestamp nuevaFechaFin, Timestamp fechaInicio, long IPS, long Servicio) {
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlInhabilitacion.updateFechaFinInhabilitacion(pm, nuevaFechaFin, fechaInicio, IPS, Servicio);
			tx.commit();
			log.trace ("Actualización Inhabilitación: " +  Servicio + " - " + IPS + " - " +  fechaInicio + " hasta " +  nuevaFechaFin + ": " + resp + " tuplas actualizadas");

			return new Inhabilitacion(fechaInicio, nuevaFechaFin, IPS, Servicio);
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<Inhabilitacion> darInhabilitacion(){
		return sqlInhabilitacion.darInhabilitacion(pmf.getPersistenceManager());
	}
	/* ****************************************************************
	 * 			Métodos para manejar los Inhabilitacion
	 *****************************************************************/

	public Inhabilitacion adicionarInhabilitacion(Timestamp fechaInicio, Timestamp fechaFin, long IPS, long servicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long tuplasInsertadas = sqlInhabilitacion.adicionarInhabilitacion(pm, fechaInicio, fechaFin, IPS, servicio);
			tx.commit();

			log.trace ("Inserción Inhabilitacion: con fecha " + fechaInicio + ", del servicio " + servicio 
					+". de la IPS " + IPS + ": " + tuplasInsertadas + " tuplas insertadas");
			return new Inhabilitacion(fechaInicio, fechaFin, IPS, servicio);
		}
		catch (Exception e)
		{
			// e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return null;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public long eliminarInhabilotacion(Timestamp fechaInicio, long IPS, long servicio)
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long resp = sqlInhabilitacion.eliminarInhabilitacion(pm, fechaInicio, IPS, servicio);
			tx.commit();

			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return -1;
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}
	}

	public List<Inhabilitacion> darInhabilitaciones()
	{
		return sqlInhabilitacion.darInhabilitacion(pmf.getPersistenceManager());
	}


	/* ****************************************************************
	 * 			Métodos útiles
	 *****************************************************************/

	public List<RolUsuario> darRolPorNumDoc(int id){
		return sqlUtil.darRolPorNumDoc(pmf.getPersistenceManager(), id);
	}


	/* ****************************************************************
	 * 			Métodos para manejar los requerimientos funcionales.
	 *****************************************************************/

	public List<Object []> RFC1(Timestamp anio, Timestamp fechaHoraInicio, Timestamp fechaHoraFin)
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		log.info ("iniciando consulta");
		List<Object> tuplas = sqlConsulta.RF1(pmf.getPersistenceManager(), anio, fechaHoraInicio, fechaHoraFin);
		log.info ("consulta exitosa");
		for ( Object tupla : tuplas)
		{
			Object [] datos = (Object []) tupla;
			String nombreEPS = (String) datos [0];
			int cantidadServiciosPrestados = ((BigDecimal) datos [1]).intValue ();

			Object [] resp = new Object [2];
			resp [0] = nombreEPS;
			resp [1] = cantidadServiciosPrestados;	

			respuesta.add(resp);
		}

		return respuesta;
	}

	public List<Object []> RFC2 ()
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		log.info ("iniciando consulta");
		List<Object> tuplas = sqlConsulta.RF2(pmf.getPersistenceManager());
		log.info ("consulta exitosa");
		for ( Object tupla : tuplas)
		{
			Object [] datos = (Object []) tupla;
			long idServicio = ((BigDecimal) datos [1]).longValue ();
			String nombre = (String) datos [0];

			Object [] resp = new Object [1];
			resp [0] = new Servicio(idServicio, nombre);

			respuesta.add(resp);
		}

		return respuesta;
	}

	public List<Object []> RFC3 ()
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		log.info ("iniciando consulta");
		List<Object> tuplas = sqlConsulta.RF3(pmf.getPersistenceManager());
		log.info ("consulta exitosa");
		for ( Object tupla : tuplas)
		{
			Object [] datos = (Object []) tupla;
			String nombreservicio = (String) datos [0];
			double indiceDeUso = ((BigDecimal) datos [1]).doubleValue();

			Object [] resp = new Object [2];
			resp [0] = nombreservicio;
			resp [1] = indiceDeUso;	

			respuesta.add(resp);
		}

		return respuesta;
	}

	public List<Object []> RFC5 (int numDoc, Timestamp f1, Timestamp f2)
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		log.info ("iniciando consulta");
		List<Object> tuplas = sqlConsulta.RF5(pmf.getPersistenceManager(), numDoc, f1, f2);
		log.info ("consulta exitosa");
		for ( Object tupla : tuplas)
		{
			Object [] datos = (Object []) tupla;
			String nombreServicio = (String) datos [0];
			int cantidadServiciosPrestados = ((BigDecimal) datos [1]).intValue ();

			Object [] resp = new Object [2];
			resp [0] = nombreServicio;
			resp [1] = cantidadServiciosPrestados;	

			respuesta.add(resp);
		}

		return respuesta;
	}

	public List<Object []> RFC6 (String tiempo, String servicio)
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		log.info ("iniciando consulta");
		List<Object> tuplas = sqlConsulta.RF6(pmf.getPersistenceManager(), tiempo,servicio);
		log.info ("consulta exitosa");
		for ( Object tupla : tuplas)
		{
			Object [] datos = (Object []) tupla;
			Timestamp fecha = (Timestamp) datos [0];
			int cuenta = ((BigDecimal) datos [1]).intValue ();

			Object [] resp = new Object [2];
			resp [0] = fecha;
			resp [1] = cuenta;

			respuesta.add(resp);
		}

		return respuesta;
	}

	public List<Object []> RFC62 (String tiempo, String servicio)
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		log.info ("iniciando consulta");
		List<Object> tuplas = sqlConsulta.RF62(pmf.getPersistenceManager(), tiempo,servicio);
		log.info ("consulta exitosa");
		for ( Object tupla : tuplas)
		{
			Object [] datos = (Object []) tupla;
			Timestamp fecha = (Timestamp) datos [0];
			int cuenta = ((BigDecimal) datos [1]).intValue ();

			Object [] resp = new Object [2];
			resp [0] = fecha;
			resp [1] = cuenta;

			respuesta.add(resp);
		}

		return respuesta;
	}

	public List<Object []> RFC63 (String tiempo, String servicio)
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		log.info ("iniciando consulta");
		List<Object> tuplas = sqlConsulta.RF63(pmf.getPersistenceManager(), tiempo,servicio);
		log.info ("consulta exitosa");
		for ( Object tupla : tuplas)
		{
			Object [] datos = (Object []) tupla;
			Timestamp fecha = (Timestamp) datos [0];
			int cuenta = ((BigDecimal) datos [1]).intValue ();

			Object [] resp = new Object [2];
			resp [0] = fecha;
			resp [1] = cuenta;

			respuesta.add(resp);
		}

		return respuesta;
	}

	public List<Object []> RFC7 ()
	{
		List<Object []> respuesta = new LinkedList <Object []> ();
		log.info ("iniciando consulta");
		List<Object> tuplas = sqlConsulta.RF7(pmf.getPersistenceManager());
		log.info ("consulta exitosa");
		for ( Object tupla : tuplas)
		{
			Object [] datos = (Object []) tupla;
			int d1 = ((BigDecimal) datos [0]).intValue ();
			int d2 = ((BigDecimal) datos [1]).intValue ();
			int d3 = ((BigDecimal) datos [2]).intValue ();

			Object [] resp = new Object [3];
			resp [0] = d1;
			resp [1] = d2;
			resp [2] = d3;

			respuesta.add(resp);
		}

		return respuesta;
	}

	public List<String> RFC8 ()
	{
		List<String> respuesta = new LinkedList <String > ();
		log.info ("iniciando consulta");
		List<Object> tuplas = sqlConsulta.RF8(pmf.getPersistenceManager());
		log.info ("consulta exitosa");
		for ( Object tupla : tuplas)
		{
			String nombre = (String)tupla;
			respuesta.add(nombre);
		}

		return respuesta;
	}


	/**
	 * Elimina todas las tuplas de todas las tablas de la base de datos de Parranderos
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @return Un arreglo con 7 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarEPSAndes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
		Transaction tx=pm.currentTransaction();
		try
		{
			tx.begin();
			long [] resp = sqlUtil.limpiarEPSAndes (pm);
			tx.commit ();
			log.info ("Borrada la base de datos");
			return resp;
		}
		catch (Exception e)
		{
			//        	e.printStackTrace();
			log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
			return new long[] {-1, -1, -1, -1, -1, -1, -1};
		}
		finally
		{
			if (tx.isActive())
			{
				tx.rollback();
			}
			pm.close();
		}

	}



}
