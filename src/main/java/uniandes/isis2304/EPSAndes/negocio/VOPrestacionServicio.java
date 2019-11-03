package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public interface VOPrestacionServicio {
	
	int getNumDoc();
	long getId_servicio();
	long getIdIPS();
	Timestamp getFechaHora();
	long getIdRecepcionista();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}