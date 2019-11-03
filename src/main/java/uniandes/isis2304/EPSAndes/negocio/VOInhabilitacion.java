package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public interface VOInhabilitacion {


	long getIPS();

	long getServicio();

	Timestamp getFechaInicio();

	Timestamp getFechaFin();
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
