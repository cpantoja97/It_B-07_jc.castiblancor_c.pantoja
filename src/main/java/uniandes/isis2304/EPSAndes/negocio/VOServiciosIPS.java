package uniandes.isis2304.EPSAndes.negocio;

import java.sql.Timestamp;

public interface VOServiciosIPS {

	int getCapacidad();
	
	Timestamp getHorarioInicio();
	Timestamp getHorarioFin();
	
	long getId_Servicio();
	
	long getId_IPS();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
