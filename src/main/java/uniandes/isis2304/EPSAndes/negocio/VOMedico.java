package uniandes.isis2304.EPSAndes.negocio;

public interface VOMedico {

	int getRegMedico();

	long getNumDoc();

	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}