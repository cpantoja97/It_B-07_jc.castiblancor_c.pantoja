package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public interface VOCampania {

	public long getId();

	String getNombre();

	int getAfiliadosEsperados();

	Timestamp getFechaInicio();

	Timestamp getFechaFin();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
