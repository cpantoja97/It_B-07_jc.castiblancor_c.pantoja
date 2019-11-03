package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public interface VOReservaServicio {

	int getnumDocAfiliado();
	long getIdServicio();
	long getIdIPS();
	long getCampania();
	
	Timestamp getFechaHora();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
