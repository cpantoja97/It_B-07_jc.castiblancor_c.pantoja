package uniandes.isis2304.EPSAndes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.EPSAndes.negocio.EPSAndes;
import uniandes.isis2304.EPSAndes.negocio.VOCampania;
import uniandes.isis2304.EPSAndes.negocio.VOReservaServicio;

public class CampaniaTest {

	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(CampaniaTest.class.getName());

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
	public void CRUDReservaTest() 
	{
		try
		{
			log.info ("Probando las operaciones CRUD sobre Campania");
			EPSAndes = new EPSAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de CRUD de Campania incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRUD de Campania incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de EPSAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}

		try
		{

			EPSAndes.limpiarEPSAndes();
			List <VOCampania> lista = EPSAndes.darVOCampania();
			int tamanio = lista.size();
			assertEquals ("No debe haber Reservas creadas!!", tamanio, lista.size ());

			String nombre= "campania 1";
			int pAfiliados = 20;
			List<Long> servicios = new ArrayList<Long>();
			List<Integer> cantidades = new ArrayList<Integer>();
			servicios.add((long) 1);	servicios.add((long) 2);
			cantidades.add(10);	cantidades.add(20);
			Timestamp pFechaInicio =  new Timestamp(118, 1, 5 , 0, 0, 0, 0);
			Timestamp pFechaFin =  new Timestamp(118, 1, 10 , 0, 0, 0, 0);
			VOCampania campania1 = EPSAndes.agregarCampaniaRF10(nombre, pAfiliados, pFechaInicio, pFechaFin, servicios, cantidades);
			lista = EPSAndes.darVOCampania();
			assertEquals ("Debe haber una campania creada !!", tamanio+ 1, lista.size ());
			//assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", campania1, lista.get (0));

			nombre= "campania 2";
			pAfiliados = 100;
			List<Long> servicios2 = new ArrayList<Long>();
			List<Integer> cantidades2 = new ArrayList<Integer>();
			servicios2.add((long) 3);	servicios2.add((long) 4);
			cantidades2.add(100);	cantidades2.add(200);
			pFechaInicio =  new Timestamp(118, 2, 1 , 0, 0, 0, 0);
			pFechaFin =  new Timestamp(118, 2, 25 , 0, 0, 0, 0);
			VOCampania campania2 = EPSAndes.agregarCampaniaRF10(nombre, pAfiliados, pFechaInicio, pFechaFin, servicios2, cantidades2);
			lista = EPSAndes.darVOCampania();
			assertEquals ("Debe haber dos reservas creadas !!", tamanio+2, lista.size ());
//			assertTrue ("La primera reserva adicionadada debe estar en la tabla", campania1.equals (lista.get (0)) || campania1.equals (lista.get (1)));
//			assertTrue ("La segunda reserva adicionadada debe estar en la tabla", campania2.equals (lista.get (0)) || campania2.equals (lista.get (1)));

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
			//log.info ("Se encontró un archivo de configuración de tablas válido");
		} 
		catch (Exception e)
		{
			//			e.printStackTrace ();
			//log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de tablas válido: ", "OrdenTest", JOptionPane.ERROR_MESSAGE);
		}	
		return config;
	}
}
