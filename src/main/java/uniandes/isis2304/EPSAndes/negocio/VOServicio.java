package uniandes.isis2304.EPSAndes.negocio;

public interface VOServicio {

	long getId_Servicio();

	String getNombre();

	long getTipo();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
