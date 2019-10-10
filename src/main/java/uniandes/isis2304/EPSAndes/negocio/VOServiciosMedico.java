package uniandes.isis2304.EPSAndes.negocio;

public interface VOServiciosMedico {

	String getEspecialidad();

	long getIdServicio();

	int getNumeroDocumento();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
