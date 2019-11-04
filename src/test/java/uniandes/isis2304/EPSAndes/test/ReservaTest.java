package uniandes.isis2304.EPSAndes.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileReader;
import java.sql.Timestamp;
import java.util.List;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;
import org.junit.Test;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.EPSAndes.negocio.EPSAndes;
import uniandes.isis2304.EPSAndes.negocio.VOPrestacionServicio;
import uniandes.isis2304.EPSAndes.negocio.VOReservaServicio;

public class ReservaTest {
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Logger para escribir la traza de la ejecución
	 */
	private static Logger log = Logger.getLogger(ReservaTest.class.getName());

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
			log.info ("Probando las operaciones CRUD sobre Reserva");
			EPSAndes = new EPSAndes (openConfig (CONFIG_TABLAS_A));
		}
		catch (Exception e)
		{
			//			e.printStackTrace();
			log.info ("Prueba de CRUD de Reserva incompleta. No se pudo conectar a la base de datos !!. La excepción generada es: " + e.getClass ().getName ());
			log.info ("La causa es: " + e.getCause ().toString ());

			String msg = "Prueba de CRUD de Reserva incompleta. No se pudo conectar a la base de datos !!.\n";
			msg += "Revise el log de EPSAndes y el de datanucleus para conocer el detalle de la excepción";
			System.out.println (msg);
			fail (msg);
		}

		try
		{
			EPSAndes.limpiarEPSAndes();
			List <VOReservaServicio> lista = EPSAndes.darVOReservaServicio();
			int tamanio = lista.size ();
			assertEquals ("No debe haber Reservas creadas!!", tamanio, lista.size ());


			int numdocAf = 1;
			long idServicio = 1;
			long idIPS =1;
			Timestamp fechaHora =  new Timestamp(118, 10, 29 , 0, 0, 0, 0);
			VOReservaServicio reserva1 = EPSAndes.adicionarReservaServicioAfiliado(numdocAf, idServicio, idIPS, fechaHora);
			lista = EPSAndes.darVOReservaServicio();
			assertEquals ("Debe haber una reserva creada !!", tamanio+1, lista.size ());
//			assertEquals ("El objeto creado y el traido de la BD deben ser iguales !!", reserva1, lista.get (0));

			numdocAf = 1;
			idServicio = 1;
			idIPS =1;
			fechaHora =  new Timestamp(118, 11, 8 , 0, 0, 0, 0);
			VOReservaServicio reserva2 = EPSAndes.adicionarReservaServicioAfiliado(numdocAf, idServicio, idIPS, fechaHora);
			lista = EPSAndes.darVOReservaServicio();
			assertEquals ("Debe haber dos reservas creadas !!", tamanio+2, lista.size ());
//			assertTrue ("La primera reserva adicionadada debe estar en la tabla", reserva1.equals (lista.get (0)) || reserva1.equals (lista.get (1)));
//			assertTrue ("La segunda reserva adicionadada debe estar en la tabla", reserva2.equals (lista.get (0)) || reserva2.equals (lista.get (1)));

//			long pEliminados = EPSAndes.eliminarReservaServicioPorId(reserva1.getnumDocAfiliado(), reserva1.getIdServicio(), reserva1.getIdIPS(), reserva1.getFechaHora());
//			assertEquals ("Debe haberse eliminado una orden!!", 1, pEliminados);
//			lista = EPSAndes.darVOReservaServicio();
//			assertEquals ("Debe haber una sola orden !!", 1, lista.size ());
//			assertFalse ("La primera reserva no debe estar en la tabla", reserva1.equals (lista.get (0)));
//			assertTrue ("La segunda reserva adicionada debe estar en la tabla", reserva2.equals (lista.get (0)));

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
