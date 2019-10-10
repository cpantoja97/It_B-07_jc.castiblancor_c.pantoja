package uniandes.isis2304.EPSAndes.negocio;

public interface VOItemReceta {

	long getIdReceta();
	long getIdMedicamento();
	int getCantidad();
	String getIndicaciones();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
