package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public interface VOCampana {

	public long getId();

	int getAfiliadosEsperados();

	Timestamp getFechaInicio();

	Timestamp getFechaFin();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
