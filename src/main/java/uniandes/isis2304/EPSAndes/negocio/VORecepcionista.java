package uniandes.isis2304.EPSAndes.negocio;

public interface VORecepcionista {

	int getNumDoc();

	long getIdIPS();
	
	@Override
	/**
	 * @return Una cadena de caracteres con todos los atributos de la clase.
	 */
	public String toString();
}
