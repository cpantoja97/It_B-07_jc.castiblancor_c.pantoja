package uniandes.isis2304.EPSAndes.interfazDemo;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.EPSAndes.negocio.EPSAndes;
import uniandes.isis2304.EPSAndes.negocio.VOCampania;
import uniandes.isis2304.EPSAndes.negocio.VOInhabilitacion;
import uniandes.isis2304.EPSAndes.negocio.VOOrdenDeServicio;
import uniandes.isis2304.EPSAndes.negocio.VOPrestacionServicio;
import uniandes.isis2304.EPSAndes.negocio.VOReservaServicio;
import uniandes.isis2304.EPSAndes.negocio.VOUsuario;

/**
 * Clase principal de la interfaz
 * 
 * @author Camila Pantoja
 * @author Juan Camilo Castiblanco
 */
@SuppressWarnings("serial")

public class InterfazEPSAndesDemo extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazEPSAndesDemo.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigDemo.json"; 

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos
	 */
	private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * Asociación a la clase principal del negocio.
	 */
	private EPSAndes EPSAndes;

	/* ****************************************************************
	 * 			Atributos de interfaz
	 *****************************************************************/
	/**
	 * Objeto JSON con la configuración de interfaz de la app.
	 */
	private JsonObject guiConfig;

	/**
	 * Panel de despliegue de interacción para los requerimientos
	 */
	private PanelDatos panelDatos;

	/**
	 * Menú de la aplicación
	 */
	private JMenuBar menuBar;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/
	/**
	 * Construye la ventana principal de la aplicación. <br>
	 * <b>post:</b> Todos los componentes de la interfaz fueron inicializados.
	 */
	public InterfazEPSAndesDemo( )
	{
		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
		EPSAndes = new EPSAndes (tableConfig);

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );        
	}

	/* ****************************************************************
	 * 			Métodos para la configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String tipo, String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}

	/**
	 * Método para configurar el frame principal de la aplicación
	 */
	private void configurarFrame(  )
	{
		int alto = 0;
		int ancho = 0;
		String titulo = "";	

		if ( guiConfig == null )
		{
			log.info ( "Se aplica configuración por defecto" );			
			titulo = "EPSAndes APP Default";
			alto = 300;
			ancho = 500;
		}
		else
		{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
			titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
		}

		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		setLocation (50,50);
		setResizable( true );
		setBackground( Color.WHITE );

		setTitle( titulo );
		setSize ( ancho, alto);        
	}

	/**
	 * Método para crear el menú de la aplicación con base em el objeto JSON leído
	 * Genera una barra de menú y los menús con sus respectivas opciones
	 * @param jsonMenu - Arreglo Json con los menùs deseados
	 */
	private void crearMenu(  JsonArray jsonMenu )
	{    	
		// Creación de la barra de menús
		menuBar = new JMenuBar();       
		for (JsonElement men : jsonMenu)
		{
			// Creación de cada uno de los menús
			JsonObject jom = men.getAsJsonObject(); 

			String menuTitle = jom.get("menuTitle").getAsString();        	
			JsonArray opciones = jom.getAsJsonArray("options");

			JMenu menu = new JMenu( menuTitle);

			for (JsonElement op : opciones)
			{       	
				// Creación de cada una de las opciones del menú
				JsonObject jo = op.getAsJsonObject(); 
				String lb =   jo.get("label").getAsString();
				String event = jo.get("event").getAsString();

				JMenuItem mItem = new JMenuItem( lb );
				mItem.addActionListener( this );
				mItem.setActionCommand(event);

				menu.add(mItem);
			}       
			menuBar.add( menu );
		}        
		setJMenuBar ( menuBar );	
	}

	/* ****************************************************************
	 * 			Demos de Campania
	 *****************************************************************/
	/**
	 * Demostración de creación, consulta y borrado de Tipos de Bebida
	 * Muestra la traza de la ejecución en el panelDatos
	 * 
	 * Pre: La base de datos está vacía
	 * Post: La base de datos está vacía
	 */
	public void demoCampania( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código

			String datosCampania = "xd XD Xd xD";
			boolean errorAgregando = false;
			VOCampania campania = null;//EPSAndes.adicionarCampania(datosCampania);
			if (campania == null)
			{
				errorAgregando = true;
			}
			List <VOCampania> lista = null;//EPSAndes.DarVOCampania();
			long campaniaEliminados = 0;//EPSAndes.eliminarCampaniaPorId (campania.getId ());

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Campania\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorAgregando)
			{
				resultado += "*** Exception creando Campania !!\n";
				resultado += "*** Es probable que esta campania ya existiera y hay restricción de UNICIDAD sobre el nombre, la fecha y otras cositas jeje\n";
				resultado += "*** Revise el log de EPSAndes para más detalles\n";
			}
			resultado += "Adicionado la campania"  + campania + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarCampania(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += campaniaEliminados + " campania eliminadas\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}

	}

	/* ****************************************************************
	 * 			Demos de Inhabilitacion
	 *****************************************************************/
	/**
	 * Demostración de creación, consulta y borrado de Bebidas.
	 * Incluye también los tipos de bebida pues el tipo de bebida es llave foránea en las bebidas
	 * Muestra la traza de la ejecución en el panelDatos
	 * 
	 * Pre: La base de datos está vacía
	 * Post: La base de datos está vacía
	 */
	@SuppressWarnings ("unused")
	public void demoInhabilitacion( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código

			String datosCampania = "xd XD Xd xD";
			boolean errorAgregando = false;
			VOInhabilitacion inhabilitacion = null;//EPSAndes.adicionarInhabilitacion(datosCampania);
			if (inhabilitacion == null)
			{
				errorAgregando = true;
			}
			List <VOInhabilitacion> lista = null;//EPSAndes.DarVOCampania();
			long inhabilitacionEliminados = 0;//EPSAndes.eliminarInhabilitacion(campania.getId ());

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Inhabilitacion\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			if (errorAgregando)
			{
				resultado += "*** Exception creando Inhabilitacion !!\n";
				resultado += "*** Es probable que esta Inhabilitacion ya existiera y hay restricción de UNICIDAD sobre la IPS, la fecha y el servicio.\n";
				resultado += "*** Revise el log de EPSAndes para más detalles\n";
			}
			resultado += "Adicionado la Inhabilitacion"  + inhabilitacion + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado +=  "\n" + listarInhabilitacion(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += inhabilitacionEliminados + " Inhabilitacion eliminadas\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}


	}

	/* ****************************************************************
	 * 			Demos de Orden
	 *****************************************************************/

	@SuppressWarnings ("unused")
	public void demoOrden( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código

			InfoBasicUsers();
			int numdocMedico = 106;
			int numdocAfiliado = 1;
			long idServicio = 1;
			VOOrdenDeServicio orden1 = EPSAndes.adicionarOrdenDeServicio(numdocMedico, numdocAfiliado, idServicio);

			List <VOOrdenDeServicio> lista = EPSAndes.darVOOrdenDeServicio();

			long ordenesEliminadas = EPSAndes.eliminarOrdenDeServicioPorId(numdocMedico, numdocAfiliado, idServicio);

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Ordenes\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado la orden: " + orden1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarOrdenes (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += ordenesEliminadas + " ordenes eliminadas\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/* ****************************************************************
	 * 			Demos de Prestacion
	 *****************************************************************/


	@SuppressWarnings ("unused")
	public void demoPrestacion( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código

			int numdocAf = 1;
			long idServicio = 1;
			long idIPS =1;
			long id_Recepcionista = 121;
			Timestamp fechaHora =  new Timestamp(118, 10, 29 , 0, 0, 0, 0);
			VOPrestacionServicio prestacion1 = EPSAndes.adicionarPrestacionServicio(numdocAf, idServicio, idIPS, fechaHora, id_Recepcionista);

			List <VOPrestacionServicio> lista = EPSAndes.darVOPrestacionServicio();

			long prestacionesEliminadas = EPSAndes.eliminarPrestacionServicioPorId(numdocAf, idServicio, idIPS, fechaHora);

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Prestaciones\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			resultado += "Adicionado la prestacion: " + prestacion1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarPrestaciones (lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			resultado += prestacionesEliminadas + " prestaciones eliminadas\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			Demos de Reserva
	 *****************************************************************/


	@SuppressWarnings ("unused")
	public void demoReserva( )
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			// ATENCIÓN: En una aplicación real, los datos JAMÁS están en el código

			int numdocAf = 1;
			long idServicio = 1;
			long idIPS =1;
			Timestamp fechaHora =  new Timestamp(118, 10, 29 , 12, 0, 0, 0);
			//VOReservaServicio reserva1 = EPSAndes.adicionarReservaServicioAfiliado(numdocAf, idServicio, idIPS, fechaHora);

			List <VOReservaServicio> lista = EPSAndes.darVOReservaServicio();
			//long reservasEliminadas = EPSAndes.eliminarReservaServicioPorId(reserva1.getnumDocAfiliado(), reserva1.getIdServicio(), reserva1.getIdIPS(), reserva1.getFechaHora());

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "Demo de creación y listado de Reservas\n\n";
			resultado += "\n\n************ Generando datos de prueba ************ \n";
			//resultado += "Adicionado la reserva: " + reserva1 + "\n";
			resultado += "\n\n************ Ejecutando la demo ************ \n";
			resultado += "\n" + listarReservas(lista);
			resultado += "\n\n************ Limpiando la base de datos ************ \n";
			//resultado += reservasEliminadas + " reservas eliminadas\n";
			resultado += "\n Demo terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/
	/**
	 * Muestra el log de Parranderos
	 */
	public void mostrarLogEPSAndes ()
	{
		mostrarArchivo ("EPSAndes.log");
	}

	/**
	 * Muestra el log de datanucleus
	 */
	public void mostrarLogDatanuecleus ()
	{
		mostrarArchivo ("datanucleus.log");
	}

	/**
	 * Limpia el contenido del log de parranderos
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogEPSAndes ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("EPSAndes.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de EPSAndes ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia el contenido del log de datanucleus
	 * Muestra en el panel de datos la traza de la ejecución
	 */
	public void limpiarLogDatanucleus ()
	{
		// Ejecución de la operación y recolección de los resultados
		boolean resp = limpiarArchivo ("datanucleus.log");

		// Generación de la cadena de caracteres con la traza de la ejecución de la demo
		String resultado = "\n\n************ Limpiando el log de datanucleus ************ \n";
		resultado += "Archivo " + (resp ? "limpiado exitosamente" : "NO PUDO ser limpiado !!");
		resultado += "\nLimpieza terminada";

		panelDatos.actualizarInterfaz(resultado);
	}

	/**
	 * Limpia todas las tuplas de todas las tablas de la base de datos de parranderos
	 * Muestra en el panel de datos el número de tuplas eliminadas de cada tabla
	 */
	public void limpiarBD ()
	{
		try 
		{
			// Ejecución de la demo y recolección de los resultados
			long eliminados [] = EPSAndes.limpiarEPSAndes();

			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n************ Limpiando la base de datos ************ \n";
			resultado += eliminados [0] + " Rol eliminados\n";
			resultado += eliminados [1] + " IPS eliminados\n";
			resultado += eliminados [2] + " servicio eliminados\n";
			resultado += eliminados [3] + " usuarios eliminados\n";
			resultado += eliminados [4] + " medicos eliminados\n";
			resultado += eliminados [5] + " afiliados eliminados\n";
			resultado += eliminados [6] + " recepcionistas eliminados\n";
			resultado += eliminados [7] + " servicios IPS eliminados\n";
			resultado += eliminados [8] + " medicos abscritos eliminados\n";
			resultado += eliminados [9] + " servicios medicos eliminados\n";
			resultado += eliminados [10] + " recetas eliminados\n";
			resultado += eliminados [11] + " item receta eliminados\n";
			resultado += eliminados [12] + " ordenes eliminados\n";
			resultado += eliminados [13] + " reservas eliminados\n";
			resultado += eliminados [14] + " prestacion eliminados\n";
			resultado += eliminados [15] + " tipos servicios eliminados\n";
			resultado += eliminados [16] + " campanias eliminadas\n";
			resultado += eliminados [17] + " inhabilitaciones eliminadas\n";

			resultado += "\nLimpieza terminada";

			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/**
	 * Muestra el modelo conceptual de Parranderos
	 */
	public void mostrarModeloConceptual ()
	{
		mostrarArchivo ("data/Modelo Conceptual EPSAndes.pdf");
	}

	/**
	 * Muestra el esquema de la base de datos de Parranderos
	 */
	public void mostrarEsquemaBD ()
	{
		mostrarArchivo ("data/Esquema BD EPSAndes.pdf");
	}

	/**
	 * Muestra el script de creación de la base de datos
	 */
	public void mostrarScriptBD ()
	{
		mostrarArchivo ("data/EsquemaEPSAndes.sql");
	}

	/**
	 * Muestra la información acerca del desarrollo de esta apicación
	 */
	public void acercaDe ()
	{
		String resultado = "\n\n ************************************\n\n";
		resultado += " * Universidad	de	los	Andes	(Bogotá	- Colombia)\n";
		resultado += " * Departamento	de	Ingeniería	de	Sistemas	y	Computación\n";
		resultado += " * Licenciado	bajo	el	esquema	Academic Free License versión 2.1\n";
		resultado += " * \n";		
		resultado += " * Curso: isis2304 - Sistemas Transaccionales\n";
		resultado += " * Proyecto: EPSAndes Uniandes\n";
		resultado += " * @version 1.0\n";
		resultado += " * @author Camila Pantoja \n";
		resultado += " * @author Juan Camilo Castiblanco \n";
		resultado += " * Noviembre de 2019\n";
		resultado += " * \n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);
	}


	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/

	private void InfoBasicUsers() {
		//VOUsuario usr1 = EPSAndes.
	}

	private String listarCampania(List<VOCampania> lista) {
		String resp = "Las Campanias existentes son:\n";
		int i = 1;
		for (VOCampania campania : lista)
		{
			resp += i++ + ". " + campania.toString() + "\n";
		}
		return resp;
	}

	private String listarInhabilitacion(List<VOInhabilitacion> lista) {
		String resp = "Las inhabilitaciones existentes son:\n";
		int i = 1;
		for (VOInhabilitacion inhabilitacion : lista)
		{
			resp += i++ + ". " + inhabilitacion.toString() + "\n";
		}
		return resp;
	}

	/**
	 * Genera una cadena de caracteres con la lista de bares recibida: una línea por cada orden
	 * @param lista - La lista con las ordenes
	 * @return La cadena con una líea para cada orden recibida
	 */
	private String listarOrdenes (List<VOOrdenDeServicio> lista) 
	{
		String resp = "Las ordenes existentes son:\n";
		int i = 1;
		for (VOOrdenDeServicio orden : lista)
		{
			resp += i++ + ". " + orden.toString() + "\n";
		}
		return resp;
	}

	private String listarPrestaciones(List<VOPrestacionServicio> lista) {
		String resp = "Las prestaciones existentes son:\n";
		int i = 1;
		for (VOPrestacionServicio prestacion : lista)
		{
			resp += i++ + ". " + prestacion.toString() + "\n";
		}
		return resp;
	}

	private String listarReservas(List<VOReservaServicio> lista) {
		String resp = "Las reservas existentes son:\n";
		int i = 1;
		for (VOReservaServicio reserva : lista)
		{
			resp += i++ + ". " + reserva.toString() + "\n";
		}
		return resp;
	}

	/**
	 * Genera una cadena de caracteres con la descripción de la excepcion e, haciendo énfasis en las excepcionsde JDO
	 * @param e - La excepción recibida
	 * @return La descripción de la excepción, cuando es javax.jdo.JDODataStoreException, "" de lo contrario
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

	/**
	 * Genera una cadena para indicar al usuario que hubo un error en la aplicación
	 * @param e - La excepción generada
	 * @return La cadena con la información de la excepción y detalles adicionales
	 */
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}

	/**
	 * Limpia el contenido de un archivo dado su nombre
	 * @param nombreArchivo - El nombre del archivo que se quiere borrar
	 * @return true si se pudo limpiar
	 */
	private boolean limpiarArchivo(String nombreArchivo) 
	{
		BufferedWriter bw;
		try 
		{
			bw = new BufferedWriter(new FileWriter(new File (nombreArchivo)));
			bw.write ("");
			bw.close ();
			return true;
		} 
		catch (IOException e) 
		{
			//			e.printStackTrace();
			return false;
		}
	}

	/**
	 * Abre el archivo dado como parámetro con la aplicación por defecto del sistema
	 * @param nombreArchivo - El nombre del archivo que se quiere mostrar
	 */
	private void mostrarArchivo (String nombreArchivo)
	{
		try
		{
			Desktop.getDesktop().open(new File(nombreArchivo));
		}
		catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos de la Interacción
	 *****************************************************************/
	/**
	 * Método para la ejecución de los eventos que enlazan el menú con los métodos de negocio
	 * Invoca al método correspondiente según el evento recibido
	 * @param pEvento - El evento del usuario
	 */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
		try 
		{
			Method req = InterfazEPSAndesDemo.class.getMethod ( evento );			
			req.invoke ( this );
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	/* ****************************************************************
	 * 			Programa principal
	 *****************************************************************/
	/**
	 * Este método ejecuta la aplicación, creando una nueva interfaz
	 * @param args Arreglo de argumentos que se recibe por línea de comandos
	 */
	public static void main( String[] args )
	{
		try
		{

			// Unifica la interfaz para Mac y para Windows.
			UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
			InterfazEPSAndesDemo interfaz = new InterfazEPSAndesDemo( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
