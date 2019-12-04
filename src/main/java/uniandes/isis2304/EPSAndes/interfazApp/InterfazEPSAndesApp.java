/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: EPSAndes Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.EPSAndes.interfazApp;

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
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.EPSAndes.negocio.*;

/**
 * Clase principal de la interfaz
 * @author Germán Bravo
 */
@SuppressWarnings({"serial","deprecation"})

public class InterfazEPSAndesApp extends JFrame implements ActionListener
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(InterfazEPSAndesApp.class.getName());

	/**
	 * Ruta al archivo de configuración de la interfaz
	 */
	private final String CONFIG_INTERFAZ_ADMIN = "./src/main/resources/config/interfaceConfigAppADMIN.json";
	private final String CONFIG_INTERFAZ_MEDICO = "./src/main/resources/config/interfaceConfigAppMEDICO.json";
	private final String CONFIG_INTERFAZ_AFILIADO = "./src/main/resources/config/interfaceConfigAppAFILIADO.json";
	private final String CONFIG_INTERFAZ_RECEPCIONISTA = "./src/main/resources/config/interfaceConfigAppRECEPCIONISTA.json";
	private final String CONFIG_INTERFAZ_GERENTE = "./src/main/resources/config/interfaceConfigAppGERENTE.json";
	private final String CONFIG_INTERFAZ_CAMPANA = "./src/main/resources/config/interfaceConfigAppCAMPANA.json";

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

	private int rolActual;

	private int idUsuarioActual;

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
	public InterfazEPSAndesApp( )
	{

		String config = null;
		JTextField textField = new JTextField();
		Object[] inputFields = {"Ingrese su número de documento", textField};
		int option = JOptionPane.showConfirmDialog(this, inputFields, "INGRESO USUARIO", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
		if (option == JOptionPane.OK_OPTION) {
			int rol = 0;
			int numDoc = Integer.parseInt(textField.getText());
			idUsuarioActual = numDoc;

			tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
			EPSAndes = new EPSAndes (tableConfig);

			RolUsuario rolUsuario = EPSAndes.darRolDeUsuarioPorNumDoc( numDoc );

			if (rolUsuario != null) {
				rol = (int)rolUsuario.getIdRolUsuario();
			}
			else {
				JOptionPane.showMessageDialog(this, "Número inválido");
				dispose();
			}

			switch(rol) {
			case 1: 
				config = CONFIG_INTERFAZ_AFILIADO;
				break;
			case 2: 
				config = CONFIG_INTERFAZ_MEDICO;
				break;
			case 3:
				config = CONFIG_INTERFAZ_RECEPCIONISTA;
				break;
			case 4:
				config = CONFIG_INTERFAZ_ADMIN;
				break;
			case 5:
				config = CONFIG_INTERFAZ_GERENTE;
				break;
			case 6:
				config = CONFIG_INTERFAZ_CAMPANA;
				break;
			}

			rolActual = rol;

		} else {
			dispose();
		}


		// Carga la configuración de la interfaz desde un archivo JSON
		guiConfig = openConfig ("Interfaz", config);

		// Configura la apariencia del frame que contiene la interfaz gráfica
		configurarFrame ( );
		if (guiConfig != null) 	   
		{
			crearMenu( guiConfig.getAsJsonArray("menuBar") );
		}

		String path = guiConfig.get("bannerPath").getAsString();
		panelDatos = new PanelDatos ( );

		setLayout (new BorderLayout());
		add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
		add( panelDatos, BorderLayout.CENTER );        
	}

	/* ****************************************************************
	 * 			Métodos de configuración de la interfaz
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicació, a partir de un archivo JSON o con valores por defecto si hay errores.
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
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "EPSAndes App", JOptionPane.ERROR_MESSAGE);
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
	 * 			CRUD de RolUsuario
	 *****************************************************************/

	public void listarRolUsuario( )
	{
		try 
		{
			List <VORolUsuario> lista = EPSAndes.darVORoles();

			String resultado = "En listarRolUsuario";
			resultado +=  "\n" + listarRolUsuario(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de Usuario
	 *****************************************************************/

	public void listarUsuario( )
	{
		try 
		{
			List <VOUsuario> lista = EPSAndes.darVOUsuario();

			String resultado = "En listarUsuario";
			resultado +=  "\n" + listarUsuario (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de Afiliado
	 *****************************************************************/

	public void listarAfiliado( )
	{
		try 
		{
			List <VOAfiliado> lista = EPSAndes.darVOAfiliado();

			String resultado = "En listarAfiliado";
			resultado +=  "\n" + listarAfiliado (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de Recepcionista
	 *****************************************************************/

	public void listarRecepcionista( )
	{
		try 
		{
			List <VORecepcionista> lista = EPSAndes.darVORecepcionista();

			String resultado = "En listarRecepcionista";
			resultado +=  "\n" + listarRecepcionista (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/* ****************************************************************
	 * 			CRUD de Medico
	 *****************************************************************/

	public void listarMedico( )
	{
		try 
		{
			List <VOMedico> lista = EPSAndes.darVOMedico();

			String resultado = "En listarMedico";
			resultado +=  "\n" + listarMedico (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/* ****************************************************************
	 * 			CRUD de IPS
	 *****************************************************************/

	public void listarIPS( )
	{
		try 
		{
			List <VOIPS> lista = EPSAndes.darVOIPS();

			String resultado = "En listarIPS";
			resultado +=  "\n" + listarIPS (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de ServiciosIPS
	 *****************************************************************/
	public void listarServiciosIPS( )
	{
		try 
		{
			List <VOServiciosIPS> lista = EPSAndes.darVOServiciosIPS();

			String resultado = "En listarServiciosIPS";
			resultado +=  "\n" + listarServiciosIPS (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de Receta
	 *****************************************************************/

	public void adicionarReceta( )
	{
		try 
		{
			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();

			Object[] inputFields = {"identificación del médico", textField1,
					"identificación del paciente", textField2 };

			int option = JOptionPane.showConfirmDialog(this, inputFields, "Información usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{
				int numdocMed = Integer.parseInt(textField1.getText());
				int numdocAf = Integer.parseInt(textField2.getText());

				VOReceta tb = EPSAndes.adicionarReceta(numdocMed, numdocAf);
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un Receta con Medico-Afiliado: " + numdocMed + "-" + numdocAf);
				}
				String resultado = "En adicionarReceta\n\n";
				resultado += "Receta adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarReceta( )
	{
		try 
		{
			List <VOReceta> lista = EPSAndes.darVOReceta();

			String resultado = "En listarReceta";
			resultado +=  "\n" + listarReceta (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*public void eliminarRecetaPorId( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del Receta?", "Borrar Receta por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{
    			long idTipo = Long.valueOf (idTipoStr);
    			long tbEliminados = EPSAndes.eliminarRecetaPorId (idTipo);

    			String resultado = "En eliminar Receta\n\n";
    			resultado += tbEliminados + " Receta eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }*/

	/* ****************************************************************
	 * 			CRUD de Medicamento
	 *****************************************************************/
	public void listarMedicamento( )
	{
		try 
		{
			List <VOMedicamento> lista = EPSAndes.darVOMedicamento();

			String resultado = "En listarMedicamento";
			resultado +=  "\n" + listarMedicamento (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	/* ****************************************************************
	 * 			CRUD de ItemReceta
	 *****************************************************************/

	public void adicionarItemReceta( )
	{
		try 
		{
			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();
			JTextField textField3 = new JTextField();
			JTextField textField4 = new JTextField();

			Object[] inputFields = {"id de la Receta", textField1,
					"id del Medicamento", textField2,
					"cantidad", textField3,
					"indicaciones", textField4};

			int option = JOptionPane.showConfirmDialog(this, inputFields, "Información usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{
				long idReceta = Long.parseLong(textField1.getText());
				long idMedicamento = Long.parseLong(textField2.getText());
				int cantidad = Integer.parseInt(textField3.getText());
				String indicaciones = textField4.getText();

				VOItemReceta tb = EPSAndes.adicionarItemsReceta( idReceta,  idMedicamento,  cantidad,  indicaciones);
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un ItemReceta con medicamento-receta: " + idMedicamento + "-" + idReceta);
				}
				String resultado = "En adicionarItemReceta\n\n";
				resultado += "ItemReceta adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarItemReceta( )
	{
		try 
		{
			List <VOItemReceta> lista = EPSAndes.darVOItemReceta();

			String resultado = "En listarItemReceta";
			resultado +=  "\n" + listarItemReceta (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarItemsPorReceta() {
		//TODO
	}

	/*
	public void eliminar_____PorId( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del ItemReceta?", "Borrar ItemReceta por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{
    			long idTipo = Long.valueOf (idTipoStr);
    			long tbEliminados = EPSAndes.eliminarItemRecetaPorId (idTipo);

    			String resultado = "En eliminar ItemReceta\n\n";
    			resultado += tbEliminados + " ItemReceta eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }*/

	/* ****************************************************************
	 * 			CRUD de OrdenDeServicio
	 *****************************************************************/

	public void adicionarOrdenDeServicio( )
	{
		try 
		{
			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();
			JTextField textField3 = new JTextField();

			Object[] inputFields = {"identificación Médico", textField1,
					"identificación Paciente", textField2,
					"id_Servicio", textField3};

			int option = JOptionPane.showConfirmDialog(this, inputFields, "Información usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{
				int idMed = Integer.parseInt(textField1.getText());
				int idAf = Integer.parseInt(textField2.getText());
				long idServ = Long.parseLong(textField3.getText());

				VOOrdenDeServicio tb = EPSAndes.adicionarOrdenDeServicio( idMed, idAf, idServ  );
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un OrdenDeServicio con Med-Afilidad-servicio: " + idMed + "-"+ idAf + "-" + idServ);
				}
				String resultado = "En adicionarOrdenDeServicio\n\n";
				resultado += "OrdenDeServicio adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarOrdenDeServicio( )
	{
		try 
		{
			List <VOOrdenDeServicio> lista = EPSAndes.darVOOrdenDeServicio();

			String resultado = "En listarOrdenDeServicio";
			resultado +=  "\n" + listarOrdenDeServicio(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de ReservaServicio
	 *****************************************************************/

	public void adicionarReservaServicio( )
	{
		try 
		{
			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();
			JTextField textField3 = new JTextField();
			JTextField textField4 = new JTextField();
			JTextField textField5 = new JTextField();
			JTextField textField6 = new JTextField();
			JTextField textField7 = new JTextField();

			//TODO Listas para servicio e ips
			Object[] inputFields = {"identificación Afiliado", textField1,
					"código de servicio", textField2,
					"código IPS", textField3,
					"día", textField4,
					"mes", textField5,
					"año", textField6,
					"hora (HH:MM)", textField7};

			int option = JOptionPane.showConfirmDialog(this, inputFields, "Información usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{
				int numdocAf = Integer.parseInt(textField1.getText());
				long idServicio = Long.parseLong(textField2.getText());
				long idIPS = Long.parseLong(textField3.getText());
				int dia = Integer.parseInt(textField4.getText());
				int mes = Integer.parseInt(textField5.getText())-1;
				int anio =  Integer.parseInt(textField6.getText()) -1900;
				int HH = Integer.parseInt(textField7.getText().split(":")[0]);
				int MM = Integer.parseInt(textField7.getText().split(":")[1]);
				Timestamp fechaHora = new Timestamp(anio, mes, dia , HH, MM, 0, 0);



				VOReservaServicio tb = EPSAndes.adicionarReservaServicioAfiliado( numdocAf, idServicio, idIPS, fechaHora );
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un ReservaServicio con afiliado-servicio-ips-fecha: " + numdocAf+"-"+idServicio+"-"+idIPS+"-"+fechaHora.toString());
				}
				String resultado = "En adicionarReservaServicio\n\n";
				resultado += "ReservaServicio adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarReservaServicio( )
	{
		try 
		{
			List <VOReservaServicio> lista = EPSAndes.darVOReservaServicio();

			String resultado = "En listarReservaServicio";
			resultado +=  "\n" + listarReservaServicio (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarReservasPorUsuario() {
		//TODO
	}
	/*
	public void eliminar_____PorId( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del ReservaServicio?", "Borrar ReservaServicio por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{
    			long idTipo = Long.valueOf (idTipoStr);
    			long tbEliminados = EPSAndes.eliminarReservaServicioPorId (idTipo);

    			String resultado = "En eliminar ReservaServicio\n\n";
    			resultado += tbEliminados + " ReservaServicio eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }*/


	/*
	public void eliminar_____PorId( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del OrdenDeServicio?", "Borrar OrdenDeServicio por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{
    			long idTipo = Long.valueOf (idTipoStr);
    			long tbEliminados = EPSAndes.eliminarOrdenDeServicioPorId (idTipo);

    			String resultado = "En eliminar OrdenDeServicio\n\n";
    			resultado += tbEliminados + " OrdenDeServicio eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }*/

	/* ****************************************************************
	 * 			CRUD de PrestacionServicio
	 *****************************************************************/

	public void adicionarPrestacionServicio( )
	{
		try 
		{
			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();
			JTextField textField3 = new JTextField();
			JTextField textField4 = new JTextField();
			JTextField textField5 = new JTextField();
			JTextField textField6 = new JTextField();
			JTextField textField7 = new JTextField();
			JTextField textField8 = new JTextField();

			Object[] inputFields = {"identificación Afiliado", textField1,
					"código de servicio", textField2,
					"código IPS", textField3,
					"día", textField4,
					"mes", textField5,
					"año", textField6,
					"hora (HH:MM)", textField7,
					"identificacion Recepcionista", textField8};

			int option = JOptionPane.showConfirmDialog(this, inputFields, "Información usuario", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{
				int numdocAf = Integer.parseInt(textField1.getText());
				long idServicio = Long.parseLong(textField2.getText());
				long idIPS = Long.parseLong(textField3.getText());
				int dia = Integer.parseInt(textField4.getText());
				int mes = Integer.parseInt(textField5.getText());
				int anio =  Integer.parseInt(textField6.getText());
				int HH = Integer.parseInt(textField7.getText().split(":")[0]);
				int MM = Integer.parseInt(textField7.getText().split(":")[1]);
				Timestamp fechaHora = new Timestamp(anio, mes, dia , HH, MM, 0, 0);
				long idRecep = Long.parseLong(textField8.getText());

				VOPrestacionServicio tb = EPSAndes.adicionarPrestacionServicio( numdocAf,  idServicio,  idIPS,  fechaHora, idRecep );
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un PrestacionServicio con afiliado-servicio-ips-fecha: " + numdocAf+"-"+idServicio+"-"+idIPS+"-"+fechaHora.toString());
				}
				String resultado = "En adicionarPrestacionServicio\n\n";
				resultado += "PrestacionServicio adicionado exitosamente: " + tb;
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void listarPrestacionServicio( )
	{
		try 
		{
			List <VOPrestacionServicio> lista = EPSAndes.darVOPrestacionServicio();

			String resultado = "En listarPrestacionServicio";
			resultado +=  "\n" + listarPrestacionServicio (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/*
	public void eliminar_____PorId( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del PrestacionServicio?", "Borrar PrestacionServicio por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{
    			long idTipo = Long.valueOf (idTipoStr);
    			long tbEliminados = EPSAndes.eliminarPrestacionServicioPorId (idTipo);

    			String resultado = "En eliminar PrestacionServicio\n\n";
    			resultado += tbEliminados + " PrestacionServicio eliminados\n";
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }*/

	/* ****************************************************************
	 * 			CRUD de Campania
	 *****************************************************************/
	public void adicionarCampania() {
		try 
		{
			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();
			JTextField textField3 = new JTextField();
			JTextField textField4 = new JTextField();

			Object[] inputFields = {"Nombre de la campaña", textField1,
					"Afiliados esperados", textField2,
					"Fecha inicio (DD/MM/AAAA)", textField3,
					"Fecha fin (DD/MM/AAAA)", textField4
			};

			int option = JOptionPane.showConfirmDialog(this, inputFields, "Información de campaña", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{
				JTextField textField5 = new JTextField();
				JTextField textField6 = new JTextField();
				JTextField textField7 = new JTextField();
				JTextField textField8 = new JTextField();
				JTextField textField9 = new JTextField();
				JTextField textField10 = new JTextField();

				Object[] inputFields2 = {"Servicio 1", textField5,
						"Cantidad 1", textField6,
						"Servicio 2", textField7,
						"Cantidad 2", textField8,
						"Servicio 3", textField9,
						"Cantidad 3", textField10
				};

				option = JOptionPane.showConfirmDialog(this, inputFields2, "Información de servicios", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

				if (option == JOptionPane.OK_OPTION)
				{
					String nombre = textField1.getText();
					int afiliados = Integer.parseInt(textField2.getText());
					String[] fi = textField3.getText().split("/");
					String[] ff = textField4.getText().split("/");
					Timestamp fechaInicio = new Timestamp(Integer.parseInt(fi[2])-1900, Integer.parseInt(fi[1])-1, Integer.parseInt(fi[0]) , 0, 0, 0, 0);
					Timestamp fechaFin = new Timestamp(Integer.parseInt(ff[2])-1900, Integer.parseInt(ff[1])-1, Integer.parseInt(ff[0]) , 0, 0, 0, 0);


					List<Long> servicios = new ArrayList<Long>();
					List<Integer> cantidades = new ArrayList<Integer>();
					if(!textField5.getText().equals("") && textField5.getText()!=null) servicios.add(Long.parseLong(textField5.getText()));
					if(!textField7.getText().equals("") && textField7.getText()!=null) servicios.add(Long.parseLong(textField7.getText()));
					if(!textField9.getText().equals("") && textField9.getText()!=null) servicios.add(Long.parseLong(textField9.getText()));
					if(!textField6.getText().equals("") && textField6.getText()!=null) cantidades.add(Integer.parseInt(textField6.getText()));
					if(!textField8.getText().equals("") && textField8.getText()!=null) cantidades.add(Integer.parseInt(textField8.getText()));
					if(!textField10.getText().equals("") && textField10.getText()!=null) cantidades.add(Integer.parseInt(textField10.getText()));

					VOCampania tb = EPSAndes.agregarCampaniaRF10(nombre, afiliados, fechaInicio, fechaFin, servicios, cantidades);
					if (tb == null)
					{
						String m = "No se pudo crear una Campaña con nombre " + nombre + " y servicios:";
						for(Long s : servicios) {
							m += " " + s;
						}
						throw new Exception (m);
					}
					String resultado = "En adicionarCampania\n\n";
					resultado += "Campaña adicionada exitosamente: " + tb;
					resultado += "\n Operación terminada";
					panelDatos.actualizarInterfaz(resultado);
				}
				else
				{
					panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
				}
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	public void eliminarServicioCampania() {
		try 
		{
			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();

			Object[] inputFields = {"ID de la campaña", textField1,
					"ID del servicio", textField2
			};
			int option = JOptionPane.showConfirmDialog(this, inputFields, "Cancelación de servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{

				long idCampania = Long.parseLong(textField1.getText());
				long idServicio = Long.parseLong(textField2.getText());
				long resp = EPSAndes.cancelarServicioCampaniaRF11(idServicio, idCampania);

				String resultado = "En eliminar Servicio de Campaña\n\n";
				resultado += resp + " reservas del servicio eliminadas\n";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			CRUD de Inhabilitación
	 *****************************************************************/

	public void deshabilitarServicio() {
		try 
		{
			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();
			JTextField textField3 = new JTextField();
			JTextField textField4 = new JTextField();

			Object[] inputFields = {"ID del Servicio", textField1,
					"ID de la IPS", textField2,
					"Fecha inicio (DD/MM/AAAA)", textField3,
					"Fecha final (DD/MM/AAAA)", textField4
			};
			int option = JOptionPane.showConfirmDialog(this, inputFields, "Inhabilitar servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{

				long idServicio = Long.parseLong(textField1.getText());
				long idIPS = Long.parseLong(textField2.getText());
				String[] fi = textField3.getText().split("/");
				String[] ff = textField4.getText().split("/");
				Timestamp fechaInicio = new Timestamp(Integer.parseInt(fi[2])-1900, Integer.parseInt(fi[1])-1, Integer.parseInt(fi[0]) , 0, 0, 0, 0);
				Timestamp fechaFin = new Timestamp(Integer.parseInt(ff[2])-1900, Integer.parseInt(ff[1])-1, Integer.parseInt(ff[0]) , 23, 59, 59, 999999999);

				String resultado = EPSAndes.deshabilitarServicioRF12(idServicio, idIPS, fechaInicio, fechaFin);
				if (resultado == null)
				{
					throw new Exception ("No se pudo deshabilitar el servicio: " +idServicio+" de la IPS "+idIPS);
				}
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	public void habilitarServicio() {
		try 
		{
			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();
			JTextField textField3 = new JTextField();
			JTextField textField4 = new JTextField();

			Object[] inputFields = {"ID del Servicio", textField1,
					"ID de la IPS", textField2,
					"Fecha inicio (DD/MM/AAAA)", textField3,
					"Nuevo fin (DD/MM/AAAA)", textField4
			};
			int option = JOptionPane.showConfirmDialog(this, inputFields, "Reapertura de servicio", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{

				long idServicio = Long.parseLong(textField1.getText());
				long idIPS = Long.parseLong(textField2.getText());
				String[] fi = textField3.getText().split("/");
				String[] ff = textField4.getText().split("/");
				Timestamp fechaInicio = new Timestamp(Integer.parseInt(fi[2])-1900, Integer.parseInt(fi[1])-1, Integer.parseInt(fi[0]) , 0, 0, 0, 0);
				Timestamp nuevoFin = new Timestamp(Integer.parseInt(ff[2])-1900, Integer.parseInt(ff[1])-1, Integer.parseInt(ff[0]) , 0, 0, 0, 0);

				Inhabilitacion resp = EPSAndes.reabrirServicioRF13(nuevoFin, fechaInicio, idIPS, idServicio);

				String resultado = "En habilitar servicio\n\n";
				resultado += "Se cambió la fecha fianl de la inhabilitación ["+ resp + "\n";
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/

	public void requerimientoFuncional1( )
	{
		try 
		{
			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();
			JTextField textField4 = new JTextField();
			JTextField textField5 = new JTextField();

			Object[] inputFields = {
					"día inicio filtro", textField1,
					"mes inicio filtro", textField2,
					"día fin filtro", textField4,
					"mes fin filtro", textField5,
			};

			int option = JOptionPane.showConfirmDialog(this, inputFields, "Información consulta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{
				Timestamp anio = new Timestamp(119, 0, 0, 0, 0, 0, 0);
				int diaInicio = Integer.parseInt(textField1.getText());
				int mesInicio = Integer.parseInt(textField2.getText());
				Timestamp fechaHoraInicio = new Timestamp(119, mesInicio-1, diaInicio , 0, 0, 0, 0);

				int diaFin= Integer.parseInt(textField4.getText());
				int mesFin= Integer.parseInt(textField5.getText());
				Timestamp fechaHoraFin = new Timestamp(119, mesFin-1, diaFin, 0, 0, 0, 0);

				List<Object []> serviciosPrestandosPorIPS= EPSAndes.darRFC1(anio, fechaHoraInicio, fechaHoraFin);

				String resultado = "En requerimientoFuncional1\n\n";
				resultado += "\n\n************ Ejecutando RF1 ************ \n";
				resultado += "\n" + listarNumeroServiciosPrestadosPorIPS(serviciosPrestandosPorIPS);
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarNumeroServiciosPrestadosPorIPS(List<Object[]> lista) {
		String resp = "La IPS y el número de servicios prestados son:\n";
		int i = 1;
		for (Object [] tupla : lista)
		{
			String nombreIPS= (String) tupla [0];
			int numServicios= (int) tupla [1];
			String resp1 = i++ + ". " + "[";
			resp1 += "nombre IPS: " + nombreIPS;
			resp1 += ", numServicios: " + numServicios;
			resp1 += "]";
			resp += resp1 + "\n";
		}
		return resp;
	}

	public void requerimientoFuncional2( )
	{
		try 
		{
			List <Object []> lista = EPSAndes.darRFC2();

			String resultado = "En requerimientoFuncional2\n\n";
			resultado += "\n\n************ Ejecutando RF2 ************ \n";
			resultado +=  "\n" + listarServiciosMasSolicitados (lista);
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}



	private String listarServiciosMasSolicitados(List<Object[]> lista) {
		String resp = "Los servicios más solicitados se muestran en seguida: \n";
		//int i = 1;
		for (Object [] tupla : lista)
		{
			Servicio ser = (Servicio) tupla[0];
			resp += ser.toString() +"\n";
		}
		return resp;
	}

	public void requerimientoFuncional3( )
	{
		try 
		{
			List<Object []> indiceDeUsoServicios= EPSAndes.darRFC3();

			String resultado = "En requerimientoFuncional3\n\n";
			resultado += "\n\n************ Ejecutando RF3 ************ \n";
			resultado += "\n" + listarIndiceDeUsoServicios(indiceDeUsoServicios);
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarIndiceDeUsoServicios(List<Object[]> lista) {
		String resp = "Los servicio y su indice de uso son:\n";
		int i = 1;
		for (Object [] tupla : lista)
		{
			String nombreServicio= (String) tupla [0];
			double indiceDeUso= (double) tupla [1];
			String resp1 = i++ + ". " + "[";
			resp1 += "nombre Servicio: " + nombreServicio;
			resp1 += " indice de uso: " + indiceDeUso;
			resp1 += "]";
			resp += resp1 + "\n";
		}
		return resp;
	}

	public void requerimientoFuncional4( )
	{
		try 
		{
			List <VORolUsuario> lista = EPSAndes.darVORoles();

			String resultado = "En listarRolUsuario";
			resultado +=  "\n" + listarRolUsuario(lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
		catch (Exception e) 
		{
			//e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}


	public void requerimientoFuncional5( )
	{
		try 
		{
			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();
			JTextField textField3 = new JTextField();
			JTextField textField4 = new JTextField();
			JTextField textField5 = new JTextField();
			JTextField textField6 = new JTextField();
			JTextField textField7 = new JTextField();

			Object[] inputFields = {
					"día inicio filtro", textField1,
					"mes inicio filtro", textField2,
					"año inicio filtro", textField3,
					"día fin filtro", textField4,
					"mes fin filtro", textField5,
					"año fin filtro", textField6,
					"numero documento", textField7
			};

			int option = JOptionPane.showConfirmDialog(this, inputFields, "Información consulta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{
				int diaInicio = Integer.parseInt(textField1.getText());
				int mesInicio = Integer.parseInt(textField2.getText());
				int anioInicio =  Integer.parseInt(textField3.getText());
				Timestamp fechaHoraInicio = new Timestamp(anioInicio+100, mesInicio, diaInicio , 0, 0, 0, 0);

				int diaFin= Integer.parseInt(textField4.getText());
				int mesFin= Integer.parseInt(textField5.getText());
				int anioFin=  Integer.parseInt(textField6.getText());
				Timestamp fechaHoraFin = new Timestamp(anioFin+100, mesFin, diaFin, 0, 0, 0, 0);

				int numdoc = Integer.parseInt(textField7.getText());

				List<Object []> utilizacionServicios= EPSAndes.darRFC5(numdoc, fechaHoraInicio, fechaHoraFin);

				String resultado = "En requerimientoFuncional5\n\n";
				resultado += "\n\n************ Ejecutando RF5 ************ \n";
				resultado += "\n" + listarUtilizacionServicios(utilizacionServicios);
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarUtilizacionServicios(List<Object[]> lista) {
		String resp = "La IPS y el número de servicios prestados son:\n";
		int i = 1;
		for (Object [] tupla : lista)
		{
			String nombreIPS= (String) tupla [0];
			int numServicios= (int) tupla [1];
			String resp1 = i++ + ". " + "[";
			resp1 += "nombre IPS: " + nombreIPS;
			resp1 += "numServicios: " + numServicios;
			resp1 += "]";
			resp += resp1 + "\n";
		}
		return resp;
	}


	public void requerimientoFuncional6( )
	{
		try 
		{
			JTextField textField1 = new JTextField();
			JTextField textField2 = new JTextField();

			Object[] inputFields = {
					"Unidad de tiempo", textField1,
					"Servicio", textField2,

			};

			int option = JOptionPane.showConfirmDialog(this, inputFields, "Información consulta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{
				String tiempo = textField1.getText();
				String servicio = textField2.getText();
				String parametro = "";
				if(tiempo.equals("semana")) {
					parametro = "IW";
				} else if (tiempo.equals("mes")) {
					parametro = "MM";
				} else if (tiempo.equals("anio")) {
					parametro = "YYYY";
				} else if (tiempo.equals("dia")) {
					parametro = "DD";
				}

				List <Object []> lista = EPSAndes.darRFC6(parametro,servicio);

				List <Object []> listaMenorDemanda = EPSAndes.darRFC62(parametro,servicio);

				List <Object []> listaMayorActividad = EPSAndes.darRFC63(parametro,servicio);

				String resultado = "En requerimientoFuncional6\n\n";
				resultado += "\n\n************ Ejecutando RF6 ************ \n";
				resultado += "Para el servicio " +servicio + " en un periodo de tiempo "+ tiempo;
				resultado +=  "\n" + listarfechasMayorDemanda (lista);
				resultado +=  "\n" + listarfechasMenorDemanda (listaMenorDemanda);
				resultado +=  "\n" + listarfechasMayorDemanda (listaMayorActividad);
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}

		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarfechasMayorDemanda(List<Object[]> lista) {
		String resp = " las fechas con mayor demanda son:\n"; 
		int i = 1;
		for (Object [] tupla : lista)
		{
			Timestamp fecha= (Timestamp) tupla [0];
			int cantidad= (int) tupla [1];
			String resp1 = i++ + ". " + "[";
			resp1 += "fecha: " + fecha;
			resp1 += ", cantidad: " + cantidad;
			resp1 += "]";
			resp += resp1 + "\n";
		}
		return resp;
	}

	private String listarfechasMenorDemanda(List<Object[]> lista) {
		String resp = " las fechas con menor demanda son:\n"; 
		int i = 1;
		for (Object [] tupla : lista)
		{
			Timestamp fecha= (Timestamp) tupla [0];
			int cantidad= (int) tupla [1];
			String resp1 = i++ + ". " + "[";
			resp1 += "fecha: " + fecha;
			resp1 += ", cantidad: " + cantidad;
			resp1 += "]";
			resp += resp1 + "\n";
		}
		return resp;
	}

	public void requerimientoFuncional7( )
	{
		try 
		{
			List <Object []> lista = EPSAndes.darRFC7();

			String resultado = "En requerimientoFuncional7\n\n";
			resultado += "\n\n************ Ejecutando RF7 ************ \n";
			resultado +=  "\n" + listarAfiliadosExigentes (lista);
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarAfiliadosExigentes(List<Object[]> lista) {
		String resp = " Los afiliados exigentes son:\n"; 
		int i = 1;
		for (Object [] tupla : lista)
		{
			int d1= (int) tupla [0];
			int d2= (int) tupla [1];
			int d3= (int) tupla [2];
			String resp1 = i++ + ". " + "[";
			resp1 += "documento afiliado: " + d1;
			resp1 += ", cantidad tipos de servicio: " + d2;
			resp1 += ", cantidad servicios usados: " + d3;
			resp1 += "]";
			resp += resp1 + "\n";
		}
		return resp;
	}

	public void requerimientoFuncional8( )
	{
		try
		{
			List <String> lista = EPSAndes.darRFC8();

			String resultado = "En requerimientoFuncional8\n\n";
			resultado += "\n\n************ Ejecutando RF8 ************ \n";
			resultado +=  "\n" + listarServiciosConPocaDemanda(lista);
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarServiciosConPocaDemanda(List<String> lista) {
		String resp = " Los servicios con poca demanda son:\n"; 
		int i = 1;
		for (String tupla : lista)
		{
			String resp1 = i++ + ". " + "[";
			resp1 += "nombre servicio: " + tupla;
			resp1 += "]";
			resp += resp1 + "\n";
		}
		return resp;
	}

	public void requerimientoFuncional9( )
	{
		//TODO pasar bien parametros y listar obejtos de respuesta.
		try 
		{
			JTextField textField1 = new JTextField("");
			JTextField textField2 = new JTextField("");
			JTextField textField3 = new JTextField("");
			JTextField textField4 = new JTextField("");
			JTextField textField5 = new JTextField("");
			JComboBox jComboBox1 = new JComboBox<>();
			jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nada", "Afiliado (nombre)","Afiliado (documento)", "Servicio", "Tipo de Servicio", "Fecha", "IPS"}));
			jComboBox1.setSelectedIndex(0);

			JComboBox jComboBox2 = new JComboBox<>();
			jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Ascendente", "Descendente"}));
			jComboBox2.setSelectedIndex(0);

			Object[] inputFields = {
					"Fecha inicio (DD/MM/AAAA)", textField1,
					"Fecha fin (DD/MM/AAAA)", textField2,
					"Servicio", textField3,
					"Tipo de servicio", textField4,
					"IPS", textField5,
					"Ordenar por", jComboBox1,
					jComboBox2
			};



			int option = JOptionPane.showConfirmDialog(this, inputFields, "Información consulta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);
			if (option == JOptionPane.OK_OPTION)
			{
				Timestamp fechaInicio = null;
				Timestamp fechaFin = null;

				if(!textField1.getText().isEmpty() && !textField2.getText().isEmpty()) {
					String[] fi = textField1.getText().split("/");
					String[] ff = textField2.getText().split("/");
					fechaInicio = new Timestamp(Integer.parseInt(fi[2])-1900, Integer.parseInt(fi[1])-1, Integer.parseInt(fi[0]) , 0, 0, 0, 0);
					fechaFin = new Timestamp(Integer.parseInt(ff[2])-1900, Integer.parseInt(ff[1])-1, Integer.parseInt(ff[0]) , 0, 0, 0, 0);
				}else {
					//Por si ingresaron uno de los dos... quitar si se hacen los checkboxes
					fechaInicio = null;
					fechaFin = null;
				}
				long idServicio = (!textField3.getText().isEmpty()) ? Long.parseLong(textField3.getText()) : -1;
				long tipo = (!textField4.getText().isEmpty()) ? Long.parseLong(textField4.getText()) : -1;
				long ips = (!textField5.getText().isEmpty()) ? Long.parseLong(textField5.getText()) : -1;

				String orden = null;
				switch(jComboBox1.getSelectedIndex()) {
				case 1:
					orden = "usuarios.nombre";
					break;
				case 2:
					orden = "afiliados.numDoc";
					break;
				case 3:
					orden = "prestacionservicio.id_servicio";
					break;
				case 4:
					orden = "servicios.tipo";
					break;
				case 5:
					orden = "prestacionservicio.fechahora";
					break;
				case 6:
					orden = "prestacionservicio.id_ips";
					break;	
				}
				if(jComboBox2.getSelectedIndex() == 1) {
					orden += " desc";
				}

				List<Object []> utilizacionServicios= EPSAndes.darRFC9(fechaInicio, fechaFin, idServicio, tipo, ips, orden);

				String resultado = "En requerimientoFuncional9\n\n";
				resultado += "\n\n************ Ejecutando RFC9 ************ \n";
				resultado += "\n" + listarPrestacionServiciosSinAgrupar(utilizacionServicios);
				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarPrestacionServiciosSinAgrupar(List<Object[]> lista) {
		String resp = "La información de los servicios prestados es:\n";
		int i = 1;
		for (Object [] tupla : lista)
		{

			String nombre= (String) tupla [0];
			int numDoc= (int) tupla [1];
			Timestamp fechaNac = (Timestamp) tupla[2];
			int tipoServicio = (int) tupla[3];
			int servicio = (int) tupla[4];
			int ips = (int) tupla[5];
			Timestamp fechaHora = (Timestamp) tupla[6];


			String resp1 = i++ + ". " + "[";
			resp1 += "nombre usuario: " + nombre + " ";
			resp1 += "id: " + numDoc + " ";
			resp1 += "fecha nacimiento: " + fechaNac + " ";
			resp1 += "Tipo servicio: " + tipoServicio + " ";
			resp1 += "Servicio: " + servicio + " ";
			resp1 += "IPS: " + ips + " ";
			resp1 += "fecha: " + fechaHora + " ";
			resp1 += "]";
			resp += resp1 + "\n";
		}
		return resp;
	}

	public void requerimientoFuncional10( )
	{
		try 
		{
			JComboBox jComboBox0 = new JComboBox<>();
			jComboBox0.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Servicios", "Afiliados","IPS"}));
			jComboBox0.setSelectedIndex(0);

			JTextField textField1 = new JTextField("");
			JTextField textField2 = new JTextField("");
			JTextField textField3 = new JTextField("");
			JTextField textField4 = new JTextField("");
			JTextField textField5 = new JTextField("");
			JComboBox jComboBox1 = new JComboBox<>();
			jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Nada", "Afiliado (nombre)","Afiliado (documento)", "Servicio (id)", "Servicio (nombre)", "Tipo de Servicio", "Fecha", "IPS"}));
			jComboBox1.setSelectedIndex(0);

			JComboBox jComboBox2 = new JComboBox<>();
			jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] {"Ascendente", "Descendente"}));
			jComboBox2.setSelectedIndex(0);

			Object[] inputFields = {
					"Criterio", jComboBox0,
					"Fecha inicio (DD/MM/AAAA)", textField1,
					"Fecha fin (DD/MM/AAAA)", textField2,
					"Servicio", textField3,
					"Tipo de servicio", textField4,
					"IPS", textField5,
					"Ordenar por", jComboBox1,
					jComboBox2
			};


			int option = JOptionPane.showConfirmDialog(this, inputFields, "Información consulta", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE);

			if (option == JOptionPane.OK_OPTION)
			{
				Timestamp fechaInicio = null;
				Timestamp fechaFin = null;

				if(!textField1.getText().isEmpty() && !textField2.getText().isEmpty()) {
					String[] fi = textField1.getText().split("/");
					String[] ff = textField2.getText().split("/");
					fechaInicio = new Timestamp(Integer.parseInt(fi[2])-1900, Integer.parseInt(fi[1])-1, Integer.parseInt(fi[0]) , 0, 0, 0, 0);
					fechaFin = new Timestamp(Integer.parseInt(ff[2])-1900, Integer.parseInt(ff[1])-1, Integer.parseInt(ff[0]) , 0, 0, 0, 0);
				}else {
					//Por si ingresaron uno de los dos... quitar si se hacen los checkboxes
					fechaInicio = null;
					fechaFin = null;
				}
				long idServicio = (!textField3.getText().isEmpty()) ? Long.parseLong(textField3.getText()) : -1;
				long tipo = (!textField4.getText().isEmpty()) ? Long.parseLong(textField4.getText()) : -1;
				long ips = (!textField5.getText().isEmpty()) ? Long.parseLong(textField5.getText()) : -1;

				String orden = null;
				switch(jComboBox1.getSelectedIndex()) {
				case 1:
					orden = "t1.nombre";
					break;
				case 2:
					orden = "t1.numDoc";
					break;
				case 3:
					orden = "t1.id_servicio";
					break;
				case 4:
					orden = "t1.nombre";
					break;
				case 5:
					orden = "t1.tipo";
					break;
				case 6:
					orden = "t1.fechahora";
					break;
				case 7:
					orden = "t1.id_ips";
					break;	
				}
				if(jComboBox2.getSelectedIndex() == 1) {
					orden += " desc";
				}

				String resultado = "En requerimientoFuncional10\n\n";
				resultado += "\n\n************ Ejecutando RF10 ************ \n";
				List<Object[]> utilizacionServicios= EPSAndes.darRFC10(jComboBox0.getSelectedIndex(), fechaInicio, fechaFin, idServicio, tipo, ips, orden);

				if(jComboBox0.getSelectedIndex() == 0) {
					resultado += "\n" + listarNOUtilizacion0(utilizacionServicios);
				}else if(jComboBox0.getSelectedIndex() == 1) {
					resultado += "\n" + listarNOUtilizacion1(utilizacionServicios);
				} else {
					resultado += "\n" + listarNOUtilizacion2(utilizacionServicios);
				}

				resultado += "\n Operación terminada";
				panelDatos.actualizarInterfaz(resultado);
			}
			else
			{
				panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
			}
		} 
		catch (Exception e) 
		{
			// e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarNOUtilizacion0(List<Object[]> lista) {
		String resp = "La información de los servicios prestados es:\n";
		int i = 1;
		for (Object [] tupla : lista)
		{
			String idServicio= ""+ tupla [0];
			String nombre= ""+tupla [1];
			String tipo= ""+ tupla [2];

			String resp1 = i++ + ". " + "[";
			resp1 += "id servicio: " + idServicio + " ";
			resp1 += "nombre servicio: " + nombre + " ";
			resp1 += "tipo servicio: " + tipo + " ";
			resp1 += "]";
			resp += resp1 + "\n";
		}
		return resp;
	}

	private String listarNOUtilizacion1(List<Object[]> lista) {
		String resp = "La información de los servicios prestados es:\n";
		int i = 1;
		for (Object [] tupla : lista)
		{

			String numdoc= "" + tupla[0];
			String nombre= "" + tupla[1];
			String fechaNacimiento= "" + tupla[2];
			String correo= "" + tupla [3];

			String resp1 = i++ + ". " + "[";
			resp1 += "id afiliado: " + numdoc + " ";
			resp1 += "nombre: " + nombre + " ";
			resp1 += "fecha nacimiento: " + fechaNacimiento + " ";
			resp1 += "correo: " + correo + " ";
			resp1 += "]";
			resp += resp1 + "\n";
		}
		return resp;
	}

	private String listarNOUtilizacion2(List<Object[]> lista) {
		String resp = "La información de los servicios prestados es:\n";
		int i = 1;
		for (Object [] tupla : lista)
		{

			String idIPS= ""+ tupla [0];
			String nombre= ""+ tupla [1];
			String localizacion= ""+tupla [2];
			String idServicio= ""+ tupla [3];
			String nomServ = ""+tupla[4];
			String tipo = ""+ tupla[5];


			String resp1 = i++ + ". " + "[";
			resp1 += "id IPS: " + idIPS + " ";
			resp1 += "nombre IPS: " + nombre + " ";
			resp1 += "localizacion: " + localizacion + " ";
			resp1 += "id servicio: " + idServicio + " ";
			resp1 += "nombre servicio: " + nomServ + " ";
			resp1 += "tipo: " + tipo + " ";

			resp1 += "]";
			resp += resp1 + "\n";
		}
		return resp;
	}

	public void requerimientoFuncional11( )
	{
		try
		{
			List <Object[]> lista = EPSAndes.darRFC11();

			String resultado = "En requerimientoFuncional11\n\n";
			resultado += "\n\n************ Ejecutando RF11 ************ \n";
			resultado +=  "\n" + listarFuncionamientoSemanal(lista);
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarFuncionamientoSemanal(List<Object[]> listac) {
		String resp = "el funcionamiento de EPSAndes es el siguiente: \n";

		int i = 1;
		for (Object [] tupla : listac)
		{
			int numAfiliados= (int) tupla [1];

			resp+= "\n Para la semana ";
			resp+=  tupla[0] + " \n";
			resp+= " el tipo de servicio mas consumido es: "; 
			resp+=  tupla[1] + " \n";
			resp+= " el tipo de servicio menos consumido es: "; 
			resp+=  tupla[2] + " \n";
			resp+= " el servicio mas consumido es: "; 
			resp+=  tupla[3] + " \n";
			resp+= " el servicio menos consumido es: "; 
			resp+=  tupla[4] + " \n";
			resp+= " la ips mas solicitada es: "; 
			resp+=  tupla[5] + " \n";
			resp+= " la ips menos solicitada es: "; 
			resp+=  tupla[6] + " \n";
			resp+= " el afiliado que mas ha utilizado servicios es: "; 
			resp+=  tupla[7] + " \n";
			resp+= " el numero de afiliados que no usaron los servicios es: "; 
			resp+= numAfiliados +"\n";
		}
		return resp;
	}

	public void requerimientoFuncional12( )	{
		try
		{
			List <Integer> listaMeses = EPSAndes.darRFC12a();
			List <Integer> listaEspecializados = EPSAndes.darRFC12b();
			List <Integer> listaHospital = EPSAndes.darRFC12b();

			String resultado = "En requerimientoFuncional12\n\n";
			resultado += "\n\n************ Ejecutando RF12 ************ \n";
			resultado +=  "\n" + listarAfiliadosCostosos(listaMeses,0);
			resultado +=  "\n" + listarAfiliadosCostosos(listaEspecializados,1);
			resultado +=  "\n" + listarAfiliadosCostosos(listaHospital,2);
			resultado += "\n Operación terminada";
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
			//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}

	private String listarAfiliadosCostosos(List<Integer> lista, int caso) {
		String resp = "\n";
		if(caso == 0) {
			resp = " Los afiliados costosos que tienen consulta cada mes son:\n"; 
			int i = 1;
			for (Integer tupla : lista)
			{
				String resp1 = i++ + ". " + "[";
				resp1 += "numero de identificacion afiliado: " + tupla;
				resp1 += "]";
				resp += resp1 + "\n";
			}
		} else if(caso==1) {
			resp = " Los afiliados costosos que siempre requieren servicios de tipo especializado son:\n"; 
			int i = 1;
			for (Integer tupla : lista)
			{
				String resp1 = i++ + ". " + "[";
				resp1 += "numero de identificacion afiliado: " + tupla;
				resp1 += "]";
				resp += resp1 + "\n";
			}
		}else if(caso == 2) {
			resp = " Los afiliados costosos que cuando requieren de un servicio de salud terminan hospitalizados son:\n"; 
			int i = 1;
			for (Integer tupla : lista)
			{
				String resp1 = i++ + ". " + "[";
				resp1 += "numero de identificacion afiliado: " + tupla;
				resp1 += "]";
				resp += resp1 + "\n";
			}
		}
		return resp;
	}


	/* ****************************************************************
	 * 			Métodos privados para la presentación de resultados y otras operaciones
	 *****************************************************************/

	private String listarRolUsuario(List<VORolUsuario> lista) 
	{
		String resp = "Los RolUsuario existentes son:\n";
		int i = 1;
		for (VORolUsuario tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	private String listarUsuario(List<VOUsuario> lista) 
	{
		String resp = "Los Usuario existentes son:\n";
		int i = 1;
		for (VOUsuario tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}
	private String listarAfiliado(List<VOAfiliado> lista) 
	{
		String resp = "Los Afiliado existentes son:\n";
		int i = 1;
		for (VOAfiliado tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	private String listarRecepcionista(List<VORecepcionista> lista) 
	{
		String resp = "Los Recepcionista existentes son:\n";
		int i = 1;
		for (VORecepcionista tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	private String listarMedico(List<VOMedico> lista) 
	{
		String resp = "Los Medico existentes son:\n";
		int i = 1;
		for (VOMedico tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	private String listarIPS(List<VOIPS> lista) 
	{
		String resp = "Los IPS existentes son:\n";
		int i = 1;
		for (VOIPS tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	private String listarServiciosIPS(List<VOServiciosIPS> lista) 
	{
		String resp = "Los ServiciosIPS existentes son:\n";
		int i = 1;
		for (VOServiciosIPS tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	private String listarReceta(List<VOReceta> lista) 
	{
		String resp = "Los Receta existentes son:\n";
		int i = 1;
		for (VOReceta tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	private String listarMedicamento(List<VOMedicamento> lista) 
	{
		String resp = "Los Medicamento existentes son:\n";
		int i = 1;
		for (VOMedicamento tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	private String listarItemReceta(List<VOItemReceta> lista) 
	{
		String resp = "Los ItemReceta existentes son:\n";
		int i = 1;
		for (VOItemReceta tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}
	private String listarOrdenDeServicio(List<VOOrdenDeServicio> lista) 
	{
		String resp = "Los OrdenDeServicio existentes son:\n";
		int i = 1;
		for (VOOrdenDeServicio tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	private String listarReservaServicio(List<VOReservaServicio> lista) 
	{
		String resp = "Los ReservaServicio existentes son:\n";
		int i = 1;
		for (VOReservaServicio tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}


	private String listarPrestacionServicio(List<VOPrestacionServicio> lista) 
	{
		String resp = "Los PrestacionServicio existentes son:\n";
		int i = 1;
		for (VOPrestacionServicio tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
		}
		return resp;
	}

	private String listarServicio(List<VOServicio> lista) {
		String resp = "Los Servicios existentes son:\n";
		int i = 1;
		for (VOServicio tb : lista)
		{
			resp += i++ + ". " + tb.toString() + "\n";
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
		e.printStackTrace();
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y EPSAndes.log para más detalles";
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
			e.printStackTrace();
		}
	}

	/* ****************************************************************
	 * 			Métodos administrativos
	 *****************************************************************/

	/**
	 * Limpia el contenido del log de EPSAndes
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
	 * Limpia todas las tuplas de todas las tablas de la base de datos de EPSAndes
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
			resultado += eliminados [0] + " Gustan eliminados\n";
			resultado += eliminados [1] + " Sirven eliminados\n";
			resultado += eliminados [2] + " Visitan eliminados\n";
			resultado += eliminados [3] + " Bebidas eliminadas\n";
			resultado += eliminados [4] + " Tipos de bebida eliminados\n";
			resultado += eliminados [5] + " Bebedores eliminados\n";
			resultado += eliminados [6] + " Bares eliminados\n";
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
		resultado += " * @author Juan Camilo Castiblanco & Camila Pantoja\n";
		resultado += " * Julio de 2018\n";
		resultado += " * \n";
		resultado += "\n ************************************\n\n";

		panelDatos.actualizarInterfaz(resultado);		
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
			Method req = InterfazEPSAndesApp.class.getMethod ( evento );			
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
			InterfazEPSAndesApp interfaz = new InterfazEPSAndesApp( );
			interfaz.setVisible( true );
		}
		catch( Exception e )
		{
			e.printStackTrace( );
		}
	}
}
