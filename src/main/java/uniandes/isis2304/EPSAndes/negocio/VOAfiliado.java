package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public interface VOAfiliado {

	Timestamp getFechaNacimiento();
	
	int getNumDoc();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
