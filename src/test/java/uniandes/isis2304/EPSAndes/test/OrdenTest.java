package uniandes.isis2304.EPSAndes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.EPSAndes.negocio.EPSAndes;
import uniandes.isis2304.EPSAndes.negocio.VOOrdenDeServicio;

public class OrdenTest {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(OrdenTest.class.getName());

	/**
	 * Ruta al archivo de configuración de los nombres de tablas de la base de datos: La unidad de persistencia existe y el esquema de la BD también
	 */
	private static final String CONFIG_TABLAS_A = "./src/main/resources/config/TablasBD_A.json"; 

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * Objeto JSON con los nombres de las tablas de la base de datos que se quieren utilizar
	 */
	private JsonObject tableConfig;

	/**
	 * La clase que se quiere probar
	 */
	private EPSAndes EPSAndes;

	/* ****************************************************************
	 * 			Métodos de prueba para la tabla Orden - Creación
	 *****************************************************************/
	/**
	 * Método que prueba las operaciones sobre la tabla TipoBebida
	 * 1. Adicionar un rol de usuario
	 */

	@Test
	public void CreateOrdenTest() 
	{
		try
		{
			log.info ("Probando las operaciones Create sobre Orden");
			EPSAndes = new EPSAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de Create de Orden incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de Create de Ordenes incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de EPSAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}

		try
		{

			List <VOOrdenDeServicio> lista = EPSAndes.darVOOrdenDeServicio();
			int tamanio = lista.size ();
			assertEquals ("No debe haber Ordenes creadas!!", tamanio, lista.size ());


			int numdocMedico = 106;
			int numdocAfiliado = 1;
			long idServicio = 1;
			VOOrdenDeServicio orden1 = EPSAndes.adicionarOrdenDeServicio(numdocMedico, numdocAfiliado, idServicio);
			lista = EPSAndes.darVOOrdenDeServicio();
			assertEquals ("Debe haber una orden creada !!", tamanio+1, lista.size ());
			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", orden1, lista.get (tamanio));

			numdocMedico = 105;
			numdocAfiliado = 2;
			idServicio = 1;
			VOOrdenDeServicio orden2 = EPSAndes.adicionarOrdenDeServicio(numdocMedico, numdocAfiliado, idServicio);
			lista = EPSAndes.darVOOrdenDeServicio();
			assertEquals ("Debe haber dos ordenes creadas !!", tamanio+2, lista.size ());
			assertTrue ("La primera orden adicionadada debe estar en la tabla", orden1.equals (lista.get (0)) || orden1.equals (lista.get (1)));
			assertTrue ("La segunda orden adicionadada debe estar en la tabla", orden2.equals (lista.get (0)) || orden2.equals (lista.get (1)));

			long oEliminados = EPSAndes.eliminarOrdenDeServicioPorId(orden1.getIdOrden(), orden1.getIdAfiliado(), orden1.getIdServicio());
			assertEquals ("Debe haberse eliminado una orden!!", tamanio+1, oEliminados);
			lista = EPSAndes.darVOOrdenDeServicio();
			assertEquals ("Debe haber nas sola orden !!", tamanio+1, lista.size ());
			assertFalse ("La primera orden no debe estar en la tabla", orden1.equals (lista.get (0)));
			assertTrue ("La segunda orden adicionada debe estar en la tabla", orden2.equals (lista.get (0)));

		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			String msg = "Error en la ejecución de las pruebas de operaciones sobre la tabla Orden.\n";
			msg += "Revise el log de parranderos y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);

			fail ("Error en las pruebas sobre la tabla Orden");
		}
		finally
		{
			EPSAndes.limpiarEPSAndes();
			EPSAndes.cerrarUnidadPersistencia ();    		
		}
	}

	/* ****************************************************************
	 * 			Métodos de configuración
	 *****************************************************************/
	/**
	 * Lee datos de configuración para la aplicación, a partir de un archivo JSON o con valores por defecto si hay errores.
	 * @param tipo - El tipo de configuración deseada
	 * @param archConfig - Archivo Json que contiene la configuración
	 * @return Un objeto JSON con la configuración del tipo especificado
	 * 			NULL si hay un error en el archivo.
	 */
	private JsonObject openConfig (String archConfig)
	{
		JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "OrdenTest", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}
}
